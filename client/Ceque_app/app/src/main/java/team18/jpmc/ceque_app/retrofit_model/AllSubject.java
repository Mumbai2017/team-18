package team18.c4g.ceque_app.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/
 * Created by Soundwave on 29-Jul-17.
 */

public class AllSubject {
    @SerializedName("all_subjects")
    @Expose
    private List<Subjects> allSubjects = null;

    public List<Subjects> getAllSubjects() {
        return allSubjects;
    }

    public void setAllSubjects(List<Subjects> allSubjects) {
        this.allSubjects = allSubjects;
    }
}
