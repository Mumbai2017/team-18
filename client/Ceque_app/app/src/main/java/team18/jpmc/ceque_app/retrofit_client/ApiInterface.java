package team18.jpmc.ceque_app.retrofit_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import team18.jpmc.ceque_app.retrofit_model.AllSubject;

/**
 * Created by Soundwave on 29-Jul-17.
 */

public interface ApiInterface {

    @GET("getAllSubjects.php")
    Call<List<AllSubject>> getAllSubjects();

}
