package team18.c4g.finalceque;

/
 * Created by Niknom on 29/07/2017.
 */



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class SeekAdapter extends RecyclerView.Adapter<SeekAdapter.MyViewHolder> {
    LayoutInflater inflater;
    public final int key = 12;
    ArrayList<String> seek_values,comments;
    Context context;
    //YouTubePlayer player;
    public SeekAdapter(Context context, ArrayList<String> seek_values, ArrayList<String> comments){
        inflater = LayoutInflater.from(context);
        this.seek_values = seek_values;
        this.comments =comments;
        //this.player = player;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.seek_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.b.setTag();
        holder.b.setText(seek_values.get(position));
        Log.d("today",seek_values.get(position));
        holder.comment.setText(comments.get(position));
        /*holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UploadImageOrVideo().seek(seek_values());
            }
        });*/
        //Log.d("result","null"+position);

    }

    @Override
    public int getItemCount() {
        return comments.size();//cab_name.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button b;
        TextView comment;
        public MyViewHolder(View itemView) {
            super(itemView);
            b = (Button) itemView.findViewById(R.id.seek_value);
            comment = (TextView) itemView.findViewById(R.id.comments);
            b.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            new AnnotationsActivity().seek(seek_values.get(getAdapterPosition()).toString());
        }
    }
    public void update(){
        notifyDataSetChanged();
    }
}

