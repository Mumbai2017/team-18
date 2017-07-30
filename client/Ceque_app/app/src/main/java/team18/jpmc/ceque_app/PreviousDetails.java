package team18..ceque_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PreviousDetails extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String ROOT_URL = "http://cfg.hphost.in/apis/";
    private List<LessonPlan> person;
    private List<Video> person2;


  SharedPreferences sharedPreferences ;
    SharedPreferences.Editor e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_details);

        getUser(); // get the lesson plan first
      //  listView.setOnItemClickListener(PreviousDetails.this);
        getVideo();

    }


    private void getUser() {
        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        GetApi api = adapter.create(GetApi.class);

        //Defining the method
        api.getUser(new Callback<List<LessonPlan>>() {

            @Override
            public void success(List<LessonPlan> lessonPlen, Response response) {

                person = lessonPlen;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }


    private void getVideo() {
        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        GetApiVideo api = adapter.create(GetApiVideo.class);

        //Defining the method
        api.getVideo(new Callback<List<Video>>() {

            @Override
            public void success(List<Video> lessonPlen, Response response) {

                person2 = lessonPlen;
                 showList2();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
    private void showList()
    {
        String detail = new String();
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e = sharedPreferences.edit();
        int i = 0;

            String[] items = new String[person.size()]; // for id

            //Traversing through the whole list to get all the names
            for (i = 0; i < person.size(); i++) {

                items[i] = String.valueOf(person.get(i).getId());
                final Button b = new Button(this);
                b.setText("L E S S O N - P L A N:" + items[i]);
                b.setId(i);

                LinearLayout ll1 = (LinearLayout) findViewById(R.id.activity_previous_details);
                ll1.addView(b);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int pos = b.getId();
                        e.putString("id", String.valueOf(pos));
                        e.putInt("type",0);
                        e.apply();
                        Intent in = new Intent(PreviousDetails.this, LVDetails.class);
                        startActivity(in);
                    }
                });

            }
        }

    private void showList2()
        {
            int i = 0;
            String[] items = new String[person2.size()]; // for id
            for (i = 0; i < person2.size(); i++) {

                items[i] = String.valueOf(person2.get(i).getId());
               final Button b2 = new Button(this);
                b2.setText("V I D E O S:" + items[i]);
                b2.setId(i);

                LinearLayout ll2 = (LinearLayout) findViewById(R.id.activity_previous_details);
                ll2.addView(b2);

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int pos = b2.getId();
                        e.putString("id", String.valueOf(pos));
                        e.putInt("type",1);
                        e.apply();
                        Intent in = new Intent(PreviousDetails.this, LVDetails.class);
                        startActivity(in);
                    }
                });

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
