package team18.c4g.ceque_app.retrofit_client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class ApiClient {
    public static final String BASE_URL="http://cfg.hphost.in/apis/";

    public static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
