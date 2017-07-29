package team18.c4g.ceque_app.retrofit_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import team18.c4g.ceque_app.retrofit_model.Subjects;
import team18.c4g.ceque_app.retrofit_model.Topics;
import team18.c4g.ceque_app.retrofit_model.Units;

/
 * Created by Soundwave on 29-Jul-17.
 */

public interface ApiInterface {

    @GET("getAllSubjects.php")
    Call<List<Subjects>> getAllSubjects();

    @FormUrlEncoded
    @POST("getAllUnits.php")
    Call<List<Units>> getAllUnits(@Field("id")String id);

    @FormUrlEncoded
    @POST("getAllTopic.php")
    Call<List<Topics>> getAllTopics(@Field("id") String id);

}
