package team18.c4g.finalceque.twinkle;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/
 * Created by Soundwave on 30-Jul-17.
 */

public interface GetApiVideo {
    @GET("/video.json")
    public void getVideo(Callback<List<Video>> response);
}
