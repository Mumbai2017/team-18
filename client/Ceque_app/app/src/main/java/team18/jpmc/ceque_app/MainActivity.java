package team18..ceque_app;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                        Intent in = new Intent(MainActivity.this,PreviousDetails.class);
                        startActivity(in);

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(MainActivity.this," "+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                }

        );


    }

}
