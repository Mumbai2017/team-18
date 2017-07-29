package team18.c4g.ceque_app.retrofit_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class Foods {

    @SerializedName("likes")
    @Expose
    private List<String> likes = null;
    @SerializedName("dislikes")
    @Expose
    private List<String> dislikes = null;

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }

}
