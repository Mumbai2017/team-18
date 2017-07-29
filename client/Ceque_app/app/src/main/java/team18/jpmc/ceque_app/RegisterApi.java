package team18.jpmc.ceque_app;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by twinkle dhanak on 7/29/2017.
 */

  public interface RegisterApi
{
    @FormUrlEncoded
    @POST("/insert.php") //???
    public void insertUser(
            @Field("username") String username,
            @Field("password") String password,


            Callback<Response> callback);
}