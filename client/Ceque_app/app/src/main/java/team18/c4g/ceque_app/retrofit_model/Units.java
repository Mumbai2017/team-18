package team18.c4g.ceque_app.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class Units {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("sub_id")
    @Expose
    private String subId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

}
