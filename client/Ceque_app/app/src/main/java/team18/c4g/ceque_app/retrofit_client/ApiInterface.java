package team18.c4g.ceque_app.retrofit_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import team18.c4g.ceque_app.retrofit_model.Subjects;

/
 * Created by Soundwave on 29-Jul-17.
 */

public interface ApiInterface {

    @GET("getAllSubjects.php")
    Call<List<Subjects>> getAllSubjects();

    @GET("https://learnwebcode.github.io/json-example/animals-1.json")
    Call<List<Example>> getAnimals();

}
