package team18.c4g.ceque_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int PICK_FROM_GALLERY = 1;
    private static final String TAG = VideoActivity.class.getSimpleName();
    ;
    VideoView videoresult;
    MediaController mediaC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Button b1 = (Button) findViewById(R.id.capture);
        Button b2 = (Button) findViewById(R.id.upload);
        videoresult = (VideoView) findViewById(R.id.videoupload);
        mediaC = new MediaController(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Calls the function to start recording videos when button is clicked.
                videoviewer();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) { // Calls the WebView
                // TODO Auto-generated method stub
                Intent intent = new Intent();

               /* intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);*/
            }
        });

    }


    public void videoviewer() {
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

            }

        }

    }
