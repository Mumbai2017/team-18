package team18.c4g.ceque_app;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team18.c4g.ceque_app.retrofit_model.Units;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitHolder> {

    List<Units> unitlist;
    List<Integer> temp;

    public UnitAdapter(List<Units> unitlist){
        this.unitlist = unitlist;
        temp = new ArrayList<>();
        temp.add(1);
        temp.add(2);
        temp.add(3);
    }

    @Override
    public UnitAdapter.UnitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        UnitHolder holder = new UnitHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(UnitHolder holder, int position) {
        holder.textView.setText(unitlist.get(position).getUnitName());
        //holder.textView.setText(temp.get(position));
    }

    @Override
    public int getItemCount() {
        return unitlist.size();
        //return temp.size();
    }

    public class UnitHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public UnitHolder(View itemView){
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "Clicked:" + unitlist.get(getAdapterPosition()).getUnitName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), SelectTopic.class);
            intent.putExtra("topicid", unitlist.get(getAdapterPosition()).getId());
            view.getContext().startActivity(intent);
        }
    }
}
