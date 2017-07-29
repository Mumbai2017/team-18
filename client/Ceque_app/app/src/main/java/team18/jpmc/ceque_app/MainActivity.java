package team18.jpmc.ceque_app;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    EditText n,p;
    String username , password ;

    public static final String ROOT_URL = "http://cfg.hphost.in/apis/";

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int PICK_FROM_GALLERY=1;
    VideoView videoresult;
    MediaController mediaC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.upload);
        Button b2 = (Button) findViewById(R.id.browse);
        videoresult = (VideoView) findViewById(R.id.videoupload);
        mediaC= new MediaController(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoviewer();
            }
    });
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();

                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);
            }
        })  ;
    }

    void fetchData1(){

    }


public void videoviewer(){
        {
            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (takeVideoIntent.resolveActivity(this.getPackageManager()) != null) {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            }
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoresult.setVideoURI(videoUri);
            videoresult.setMediaController(mediaC);
            mediaC.setAnchorView(videoresult);
            videoresult.start();

            if (resultCode != RESULT_OK) return;

            if (requestCode == PICK_FROM_GALLERY) {
                Uri mVideoURI = data.getData();
                videoresult.setVideoURI(mVideoURI);
                videoresult.setMediaController(mediaC);
                mediaC.setAnchorView(videoresult);
                videoresult.start();
            }

        }

    }


    public void onClickSelectTopic(View view) {
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }

    public void login(View v)
    {
        n = (EditText)findViewById(R.id.username);
        p = (EditText)findViewById(R.id.id);

        username = n.getText().toString();
        password = p.getText().toString();
        insertUser();

    }


    public void insertUser()
    {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterApi api = adapter.create(RegisterApi.class);


        api.insertUser(

                //Passing the values by getting it from editTexts

                username,
                password,

                new Callback<Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2)
                    {
                        BufferedReader reader = null;

                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();

                       // Intent in = new Intent(MainActivity.this,PreviousDetails.class);
                       // startActivity(in);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(MainActivity.this," "+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                }

        );


    }

}
