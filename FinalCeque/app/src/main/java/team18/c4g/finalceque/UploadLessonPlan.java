package team18.c4g.finalceque;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadLessonPlan extends AppCompatActivity {

    Button btnChoose, btnUpload;
    ImageView imgUpload;
    String url="http://cfg.hphost.in/apis/uploadImageUsingVolley.php";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_lesson_plan);

        btnChoose = (Button)findViewById(R.id.btnChoose);
        btnUpload = (Button)findViewById(R.id.Uploadimage);
        imgUpload = (ImageView)findViewById(R.id.imageUploadimg);
    }

    public void onClickChoose(View view) {
        ActivityCompat.requestPermissions(
                UploadLessonPlan.this,
                new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                999
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==999){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 999);
            }else{
                Toast.makeText(getApplicationContext(), "No permissions", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==999 && resultCode==RESULT_OK && data!=null){
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgUpload.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    public void onClickUpload(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UploadLessonPlan.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UploadLessonPlan.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                String imageData = imageTostring(bitmap);
                params.put("image",imageData);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(UploadLessonPlan.this);
        requestQueue.add(stringRequest);
    }

    private String imageTostring(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
