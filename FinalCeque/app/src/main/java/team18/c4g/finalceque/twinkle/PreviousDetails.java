package team18.c4g.finalceque.twinkle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import team18.c4g.finalceque.R;

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


    private void getUser()
    {
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
                Toast.makeText(PreviousDetails.this," "+error.toString(),Toast.LENGTH_SHORT).show();
                Log.d("error0",error.toString());
            }
        });

    }


    private void getVideo()
    {
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
                Toast.makeText(PreviousDetails.this," "+error.toString(),Toast.LENGTH_SHORT).show();
                Log.d("error1",error.toString());
            }
        });

    }

    private void showList()
    {
        String detail = new String();
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e = sharedPreferences.edit();
        int i = 0;

            List<String> items = new ArrayList<String>(); // for id

            //Traversing through the whole list to get all the names
            for (i = 0; i < 1; i++) {

                items.add(String.valueOf(person.get(i).getId()));
                final Button b = new Button(this);
                b.setText("L E S S O N - P L A N:" + items.get(i));
                b.setId(i);

                LinearLayout ll1 = (LinearLayout) findViewById(R.id.activity_previous_details);
                ll1.addView(b);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int pos = b.getId();
                        Intent in = new Intent(PreviousDetails.this, LVDetails.class);
                        in.putExtra("idl",String.valueOf(String.valueOf(person.get(pos).getId())));
                        startActivity(in);
                    }
                });

            }
        }

    private void showList2() // traverses through the list
        {
            int i = 0;
            List<String> items = new ArrayList<String>(); // for id
            for (i = 0; i < 1; i++) {

                items.add(String.valueOf(person2.get(i).getId()));
               final Button b2 = new Button(this);
                b2.setText("V I D E O   N U M B E R:" + items.get(i));
                b2.setId(i);

                LinearLayout ll2 = (LinearLayout) findViewById(R.id.activity_previous_details);
                ll2.addView(b2);

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int pos = b2.getId();
                        e.putString("idv", String.valueOf(pos));
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
