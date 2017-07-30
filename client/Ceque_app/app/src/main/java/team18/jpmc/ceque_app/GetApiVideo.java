package team18.jpmc.ceque_app;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by twinkle dhanak on 7/30/2017.
 */

public interface GetApiVideo
{
    @GET("/video.json")
    public void getVideo(Callback<List<Video>> response);
}
