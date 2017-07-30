package team18.c4g.finalceque.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/
 * Created by Soundwave on 30-Jul-17.
 */

public class Topics {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("topic_name")
    @Expose
    private String topicName;
    @SerializedName("unit_id")
    @Expose
    private String unitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }


}
