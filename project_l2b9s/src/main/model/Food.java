package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents food with its name how many carbohydrates, fat, protein, and total grams of food.
public class Food implements Writable {
    private static final int CALERIES_PER_PROTEIN = 4;
    private static final int CALERIES_PER_FAT = 9;
    private static final int CALERIES_PER_CARBS = 4;
    private int fat;
    private int protein;
    private double grams;
    private int carbohydrates;
    private String name;

    //REQUIRES: name != null, grams, carbohydrates, protein >= 0
    //EFFECTS: constructs a Food object with a name, grams, carbohydrates, fat, and protein
    public Food(String name, int grams, int carbohydrates, int fat, int protein) {
        this.name = name;
        this.grams = grams;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
    }

    //REQUIRES: name cannot be empty
    //MODIFIES: this
    //EFFECTS: changes name
    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES: grams > 0
    //EFFECTS: changes grams
    public void setGrams(double grams) {
        this.grams = grams;
    }

    //REQUIRES: carbs >= 0
    //EFFECTS: changes carbohydrates
    public void setCarbohydrates(int carbs) {
        this.carbohydrates = carbs;
    }

    //REQUIRES: fat >= 0
    //EFFECTS: changes fat
    public void setFat(int fat) {
        this.fat = fat;
    }

    //REQUIRES: protein >= 0
    //EFFECTS: changes protein
    public void setProtein(int protein) {
        this.protein = protein;
    }

    //EFFECTS: return name
    public String getName() {
        return this.name;
    }

    //EFFECTS: return grams
    public double getGrams() {
        return this.grams;
    }

    //EFFECTS: return carbohydrates
    public int getCarbohydrates() {
        return this.carbohydrates;
    }

    //EFFECTS: return fat
    public int getFat() {
        return this.fat;
    }

    //EFFECTS: return protein
    public  int getProtein() {
        return this.protein;
    }

    //EFFECTS: returns the total calories of fat, protein, and carbohydrates
    private int getCalories() {
        int caloriesOfFat = this.fat * CALERIES_PER_FAT;
        int caloriesOfProtein = this.protein * CALERIES_PER_PROTEIN;
        int caloriesOfCarbohydrates = this.carbohydrates * CALERIES_PER_CARBS;

        return caloriesOfCarbohydrates + caloriesOfFat + caloriesOfProtein;
    }

    //EFFECTS: returns the total calories divided by the grams
    public double getCaloriesPerGram() {
        return Math.round((getCalories() * 10)  / this.grams) / 10.0;
    }

    //EFFECTS: returns the protein per gram
    public double proteinPerGram() {
        return Math.round((this.protein * 10)  / this.grams) / 10.0;
    }

    //EFFECTS: returns the fat per gram
    public double fatPerGram() {
        return Math.round((this.fat * 10)  / this.grams) / 10.0;
    }

    //EFFECTS: returns the carbohydrates per gram
    public double carbohydratesPerGram() {
        return Math.round((this.carbohydrates * 10)  / this.grams) / 10.0;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("grams", grams);
        json.put("carbohydrates", carbohydrates);
        json.put("fat", fat);
        json.put("protein", protein);

        return json;
    }
}
