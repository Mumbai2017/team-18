package team18.c4g.ceque_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
import team18.c4g.ceque_app.retrofit_model.Units;

/
 * Created by Soundwave on 30-Jul-17.
 */

public class SelectUnit1 extends AppCompatActivity{

    RecyclerView recyclerView;
    Retrofit retrofit;
    LinearLayoutManager linearLayoutManager;
    ApiInterface apiInterface;
    String unitid;
    UnitAdapter1 unitAdapter1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_unit);

        Intent intent = getIntent();
        unitid  = intent.getStringExtra("unitid");

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
                unitAdapter1 = new UnitAdapter1(response.body());
                recyclerView.setAdapter(unitAdapter1);
            }

            @Override
            public void onFailure(Call<List<Units>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
