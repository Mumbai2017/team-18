package team18.c4g.finalceque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickUploadLessonPlan(View view) {
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }

    public void onClickUploadVideo(View view) {
        Intent in = new Intent(MainActivity.this,VideoActivity.class);
        startActivity(in);
    }
}
