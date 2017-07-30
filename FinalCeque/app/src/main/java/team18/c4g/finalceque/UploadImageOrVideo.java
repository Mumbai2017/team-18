package team18.c4g.finalceque;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UploadImageOrVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_img_vid);
    }

    public void onClickUploadLessonPlan(View view) {
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }

    public void onClickUploadVideo(View view) {
        Intent in = new Intent(UploadImageOrVideo.this,VideoActivity.class);
        startActivity(in);
    }

    public void onClickFeedback(View view) {
        Intent in = new Intent(UploadImageOrVideo.this,AnnotationsActivity.class);
        startActivity(in);
    }
}
