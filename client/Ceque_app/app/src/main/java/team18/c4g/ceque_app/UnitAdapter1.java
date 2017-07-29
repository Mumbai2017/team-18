package team18.c4g.ceque_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import team18.c4g.ceque_app.retrofit_model.Units;

/
 * Created by Soundwave on 30-Jul-17.
 */

public class UnitAdapter1 extends RecyclerView.Adapter<UnitAdapter1.ViewHolder> {

    List<Units> unitsList;

    public UnitAdapter1(List<Units> unitsList){
        this.unitsList = unitsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_select_unit,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Toast.makeText(holder.itemView.getContext(), "bindview " + unitsList.get(position).getUnitName(), Toast.LENGTH_SHORT).show();
        holder.textView.setText(unitsList.get(position).getUnitName());
    }

    @Override
    public int getItemCount() {
        return unitsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.item);
        }
    }
}
