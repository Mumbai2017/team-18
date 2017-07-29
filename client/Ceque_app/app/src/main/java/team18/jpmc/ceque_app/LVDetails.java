package team18..ceque_app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LVDetails extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    public static final String ROOT_URL = "http://cfg.hphost.in/apis/";
    private List<LessonPlan> person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvdetails);
        getUser();
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

    private void showList()
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       String id = sharedPreferences.getString("id","0");
        int pos = 0, ctr = 0;
        String[] items = new String[person.size()];
        for(int i=0; i<person.size(); i++)
        {
            if(Integer.parseInt(id)==i) {
                items[i] = String.valueOf(person.get(i).getId());
                pos = i;
                break;
            }

        }
        Button b1 = (Button)findViewById(R.id.b1);
        b1.setText("L E S S O N - P L A N   F O R :" +String.valueOf(person.get(pos).getId()) );


        TextView details = (TextView)findViewById(R.id.detail);
        details.setText(
                "USER:"+ String.valueOf(person.get(pos).getUser_id())+ "\n" +
                        "TOPIC: "+String.valueOf(person.get(pos).getTopic_id())+ "\n" +
                        "DESCRIPTION: "+String.valueOf(person.get(pos).getDescription())+ "\n" +
                        "TIME-IN-MILLIS: "+String.valueOf(person.get(pos).getTimestamp())+ "\n" +
                        "IMAGE-URL: "+  String.valueOf(person.get(pos).getImageurl())
        );





    }



}
