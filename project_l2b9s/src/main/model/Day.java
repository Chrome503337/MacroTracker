package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Foods can be sorted into breakfast, lunch, and dinner. Food can be eaten, stored in a list, and all macros are tracked
public class Day implements Writable {
    private ArrayList<Food> allFoods;
    private int day;
    private int calories;
    private int carbohydrates;
    private int protein;
    private int fat;


    public Day(int day) {
        this.day = day;
        allFoods = new ArrayList<>();
        calories = 0;
        carbohydrates = 0;
        fat = 0;
        protein = 0;
    }


    //MODIFIES: this
    //EFFECT: adds food all foods
    public void addFood(Food food) {
        boolean add = true;
        for (Food allFood : allFoods) {
            if (food.getName().equalsIgnoreCase(allFood.getName())) {
                add = false;
                break;
            }
        }
        if (add) {
            this.allFoods.add(food);
            EventLog.getInstance().logEvent(new Event(food.getName() + " added to Day "
                                                        + day + " foods"));
        }
    }

    //EFFECT: returns all foods
    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    //EFFECT: returns calories
    public int getCalories() {
        return calories;
    }

    //EFFECT: returns carbohydrates
    public int getCarbohydrate() {
        return carbohydrates;
    }

    //EFFECT: returns fat
    public int getFat() {
        return fat;
    }

    //EFFECT: returns protein
    public int getProtein() {
        return protein;
    }

    //EFFECT: returns day
    public int getDay() {
        return day;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }


    //REQUIRES: grams >= 0
    //MODIFIES: this
    //EFFECT: Adds calories, carbs, protein, fat, and the food in all foods
    public void eat(Food food, int grams) {
        calories += (int)Math.round(food.getCaloriesPerGram() * grams);
        fat += (int)Math.round(food.fatPerGram() * grams);
        carbohydrates += (int)Math.round(food.carbohydratesPerGram() * grams);
        protein += (int)Math.round(food.proteinPerGram() * grams);

        addFood(food);
        EventLog.getInstance().logEvent(new Event("Ate: " + food.getName() + " x " + grams + "g"));
    }
// Ate: White Rice x 100g

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("calories", calories);
        json.put("carbohydrates", carbohydrates);
        json.put("fat", fat);
        json.put("protein", protein);
        json.put("Today'sFoods", foodsToJson());

        return json;
    }

    //EFFECT: returns foods eaten in this day as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food food : allFoods) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }
}
