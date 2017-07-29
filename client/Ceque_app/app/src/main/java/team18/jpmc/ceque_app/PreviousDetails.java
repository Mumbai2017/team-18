package team18.jpmc.ceque_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class PreviousDetails extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String ROOT_URL = "http://cfg.hphost.in/apis/";
    List<LessonPlan> lessonList = new ArrayList<>();
    public List<LessonPlan> list;
    private List<LessonPlan> person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_details);

        getUser(); // get the lesson plan first


    }


    private void getUser() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();
        Toast.makeText(PreviousDetails.this, "GET WORKING1", Toast.LENGTH_LONG).show();
        GetApi api = adapter.create(GetApi.class);
        Toast.makeText(PreviousDetails.this, "GET WORKING2", Toast.LENGTH_LONG).show();
        api.getUser(new Callback<List<LessonPlan>>() {

            @Override
            public void success(List<LessonPlan> persons, retrofit.client.Response response) {
//Storing the data in our list
                list = persons;
                Toast.makeText(PreviousDetails.this, "GET WORKING", Toast.LENGTH_LONG).show();
                //Calling a method to show the list
                setLayout();
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(PreviousDetails.this, " " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setLayout()
    {

        String detail = new String();
int j=0;
        //String array to store all the book names
        String[] items = new String[person.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<person.size(); i++){
            //Storing names to string array
             j = person.get(i).getId();
            detail = detail + items[i];
        }
      Toast.makeText(PreviousDetails.this," "+j,Toast.LENGTH_SHORT).show();


    }
}




/*
    public void setLayout()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view); // WORKING FOR RECYLCER VIEW , SETTING LAYOUT
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MyAdapter(lessonList);
        mRecyclerView.setAdapter(mAdapter);

         // retrieve values from database for lesson plans

        String detail = new String();

        //String array to store all the book names
        String[] items = new String[list.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<list.size(); i++){
            //Storing names to string array
            //items[i] = String.valueOf(j);
            //detail = detail + items[i];

            int j = list.get(i).getId();

            LessonPlan lessonPlan = new LessonPlan(j); // for time-being, setting default values
            lessonList.add(lessonPlan);
        }





    }


}
/*

class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{

    private List<LessonPlan> questionList;

                        class ViewHolder extends RecyclerView.ViewHolder // class inside class---subclass
                        {

                            TextView itemTitle;
                            Button itemName;

                            public ViewHolder(View view) // constrcutor of inner class
                            {
                                super(view);

                                itemTitle = (TextView)view.findViewById(R.id.item_title);
                                itemName = (Button) view.findViewById(R.id.item_name);

                            }
                        }

    public MyAdapter(List<LessonPlan> questionList) // constructor of outer class
    {
        this.questionList = questionList;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historydetails, parent , false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        LessonPlan lp = questionList.get(position); // i is the position here---BE CAREFUL
        holder.itemTitle.setText(lp.getId());
        holder.itemName.setText("VIEW");


    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
*/
