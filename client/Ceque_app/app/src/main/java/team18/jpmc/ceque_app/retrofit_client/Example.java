package team18.jpmc.ceque_app.retrofit_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Soundwave on 29-Jul-17.
 */

public class Example {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("foods")
    @Expose
    private Foods foods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

}
