package team18..ceque_app;

import android.content.Intent;
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
}
