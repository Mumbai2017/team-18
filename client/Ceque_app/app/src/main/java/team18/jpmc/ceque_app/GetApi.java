package team18..ceque_app;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/
 * Created by twinkle dhanak on 7/29/2017.
 */

public interface GetApi
{
    @GET("/lesson_plan.json")
    public void getUser(Callback<List<LessonPlan>> response);
}
