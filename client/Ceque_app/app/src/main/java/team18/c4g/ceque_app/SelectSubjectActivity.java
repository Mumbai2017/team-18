package team18.c4g.ceque_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import team18.c4g.ceque_app.retrofit_client.ApiClient;
import team18.c4g.ceque_app.retrofit_client.ApiInterface;
import team18.c4g.ceque_app.retrofit_model.Subjects;

public class SelectSubjectActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Retrofit retrofit;
    SubjectAdapter subjectAdapter;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView = (RecyclerView)findViewById(R.id.subject_recycler_View);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Subjects>> res = apiInterface.getAllSubjects();
        Toast.makeText(this, "Doing call", Toast.LENGTH_SHORT).show();

        res.enqueue(new Callback<List<Subjects>>() {
            @Override
            public void onResponse(Call<List<Subjects>> call, Response<List<Subjects>> response) {
                Toast.makeText(getApplicationContext(), "Call success", Toast.LENGTH_SHORT).show();
                subjectAdapter = new SubjectAdapter(response.body());
                recyclerView.setAdapter(subjectAdapter);
            }

            @Override
            public void onFailure(Call<List<Subjects>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
