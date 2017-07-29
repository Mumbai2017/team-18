package team18.c4g.ceque_app;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.bitmap;
import static android.R.attr.data;

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
        Spinner s1=(Spinner) findViewById(R.id.subjectspin);
        Spinner s2=(Spinner) findViewById(R.id.unitspin);
        Spinner s3=(Spinner) findViewById(R.id.topicspin);
        videoresult = (VideoView) findViewById(R.id.videoupload);
        mediaC= new MediaController(this);
        s1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fetchData1();
            }
        });
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
public void fetchData1(){
    RequestQueue queue = Volley.newRequestQueue(this); // this = context
    String url = "http://cfg.hphost.in/apis/getAllSubjects.php";
    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   Log.d("today",response);



                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                    Log.d("Error.Response", "error");
                }
            }
    );

}
    }
