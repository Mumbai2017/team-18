package team18.c4g.ceque_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import team18.c4g.ceque_app.retrofit_client.ApiClient;
import team18.c4g.ceque_app.retrofit_client.ApiInterface;
import team18.c4g.ceque_app.retrofit_model.Topics;

public class SelectTopic extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;
    TopicsAdapter topicsAdapter;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    String topicid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic);

        Intent intent = getIntent();
        topicid = intent.getStringExtra("topicid");
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = (RecyclerView)findViewById(R.id.topic_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Topics>> call = apiInterface.getAllTopics(topicid);
        call.enqueue(new Callback<List<Topics>>() {
            @Override
            public void onResponse(Call<List<Topics>> call, Response<List<Topics>> response) {
                
            }

            @Override
            public void onFailure(Call<List<Topics>> call, Throwable t) {

            }
        });
    }
}
