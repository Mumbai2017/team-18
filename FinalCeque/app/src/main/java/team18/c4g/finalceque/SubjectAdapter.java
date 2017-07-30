package team18.c4g.finalceque;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import team18.c4g.finalceque.retrofit_model.Subjects;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    List<Subjects> subjectList;

    public SubjectAdapter(List<Subjects> subjectList){
        this.subjectList = subjectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(subjectList.get(position).getSubName());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
        //return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
             text = (TextView)itemView.findViewById(R.id.item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SelectUnit.class);
            String unitid = subjectList.get(getAdapterPosition()).getId();

            intent.putExtra("unitid", unitid);
            view.getContext().startActivity(intent);
        }
    }
}
