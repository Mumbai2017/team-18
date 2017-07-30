package team18.c4g.finalceque.twinkle;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/
 * Created by Soundwave on 30-Jul-17.
 */

public interface GetApi {
    @GET("/lesson_plan.json")
    public void getUser(Callback<List<LessonPlan>> response);
}
