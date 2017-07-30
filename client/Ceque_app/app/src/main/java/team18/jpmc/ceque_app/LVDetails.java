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

    SharedPreferences sharedPreferences;
    public static final String ROOT_URL = "http://cfg.hphost.in/apis/";
    private List<LessonPlan> person;
    private List<Video> person2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvdetails);
        getUser();
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

    private void showList() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String id = sharedPreferences.getString("id", "0");

        int pos = 0, ctr = 0;

        String[] items = new String[person.size()];
        for (int i = 0; i < person.size(); i++) {
            if (Integer.parseInt(id) == i) {
                items[i] = String.valueOf(person.get(i).getId());
                pos = i;
                break;
            }

        }
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setText("L E S S O N - P L A N   F O R :" + String.valueOf(person.get(pos).getId()));


        TextView details = (TextView) findViewById(R.id.detail);
        details.setText(
                "USER:" + String.valueOf(person.get(pos).getUser_id()) + "\n" +
                        "TOPIC: " + String.valueOf(person.get(pos).getTopic_id()) + "\n" +
                        "DESCRIPTION: " + String.valueOf(person.get(pos).getDescription()) + "\n" +
                        "TIME-IN-MILLIS: " + String.valueOf(person.get(pos).getTimestamp()) + "\n" +
                        "IMAGE-URL: " + String.valueOf(person.get(pos).getImageurl())
        );
    }



        private void showList2()
        {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String id = sharedPreferences.getString("id", "0");
            int pos = 0;
            String[] items = new String[person2.size()];
            for (int i = 0; i < person2.size(); i++) {
                if (Integer.parseInt(id) == i) {
                    items[i] = String.valueOf(person2.get(i).getId());
                    pos = i;
                    break;
                }

            }
            Button b2 = (Button) findViewById(R.id.b2);
            b2.setText("V I D E O   F O R :" + String.valueOf(person2.get(pos).getId()));


            TextView details2 = (TextView) findViewById(R.id.detail);
            details2.setText(
                    "USER:" + String.valueOf(person2.get(pos).getUser_id()) + "\n" +
                            "YOUTUBE ID : " + String.valueOf(person2.get(pos).getYoutube_id()) + "\n" +
                            "IP ID: " + String.valueOf(person2.get(pos).getIp_id()) + "\n" +
                            "TIME-IN-MILLIS: " + String.valueOf(person2.get(pos).getTimestamp()) + "\n"

            );

        }





    }




