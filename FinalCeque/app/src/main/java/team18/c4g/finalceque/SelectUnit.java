package team18.c4g.finalceque;

import android.content.Intent;
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
import team18.c4g.finalceque.retrofit_client.ApiClient;
import team18.c4g.finalceque.retrofit_client.ApiInterface;
import team18.c4g.finalceque.retrofit_model.Units;

public class SelectUnit extends AppCompatActivity {

    RecyclerView recyclerView;
    Retrofit retrofit;
    UnitAdapter unitAdapter;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    List<Units> unitlist;

    String unitid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_unit);

        Intent intent = getIntent();
        unitid = intent.getStringExtra("unitid");
        //Toast.makeText(this, "Selected unit:" + unitid,Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView = (RecyclerView)findViewById(R.id.unit_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Units>> call = apiInterface.getAllUnits(unitid);
        call.enqueue(new Callback<List<Units>>() {
            @Override
            public void onResponse(Call<List<Units>> call, Response<List<Units>> response) {
                Toast.makeText(getApplicationContext(), "Call success", Toast.LENGTH_SHORT).show();
                unitAdapter = new UnitAdapter(response.body());
                Toast.makeText(getApplicationContext(), "" + response.body().get(0).getUnitName(),Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(unitAdapter);
                unitlist = response.body();
            }

            @Override
            public void onFailure(Call<List<Units>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed:" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
