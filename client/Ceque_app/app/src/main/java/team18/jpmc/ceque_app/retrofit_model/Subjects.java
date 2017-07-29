package team18..ceque_app.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class Subjects {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sub_name")
    @Expose
    private String subName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

}
