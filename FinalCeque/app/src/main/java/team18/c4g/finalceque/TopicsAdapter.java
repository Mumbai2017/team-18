package team18.c4g.finalceque;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import team18.c4g.finalceque.retrofit_model.Topics;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    List<Topics> topicsList;
    public TopicsAdapter(List<Topics> topicList){
        this.topicsList = topicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(topicsList.get(position).getTopicName());
    }

    @Override
    public int getItemCount() {
        return topicsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), UploadLessonPlan.class);
            view.getContext().startActivity(intent);
        }
    }
}
