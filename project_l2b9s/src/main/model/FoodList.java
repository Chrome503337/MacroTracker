package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//List of foods that have been added,
public class FoodList implements Writable {
    private ArrayList<Food> allFoods = new ArrayList<>();

    public FoodList() {
        data();
    }

    //EFFECT: data of some popular foods
    private void data() {
        allFoods.add(new Food("Spaghetti",100,75,2,13));
        allFoods.add(new Food("White Rice",250,70,1,7));
        allFoods.add(new Food("Banana",170,39,1,2));
        allFoods.add(new Food("Beef",100,0,17,26));
        allFoods.add(new Food("Chicken Breast",100,0,4,31));
        allFoods.add(new Food("Pork",100,0,14,26));
        allFoods.add(new Food("Lamb",85,0,18,21));
        allFoods.add(new Food("Egg",100,1,10,13));
        allFoods.add(new Food("Fish",170,0,5,44));
        allFoods.add(new Food("Salmon",227,0,28,50));
        allFoods.add(new Food("Milk",244,12,5,8));
        allFoods.add(new Food("Peanut Butter",32,8,16,7));
        allFoods.add(new Food("Bread",29,14,1,3));
        allFoods.add(new Food("Veggies",140,10,1,2));
    }

    //MODIFIES: this
    //EFFECT: adds food to list only if it has different name
    public void addNewFood(Food food) {
        boolean add = true;
        for (Food allFood : allFoods) {
            if (allFood.getName().equals(food.getName())) {
                add = false;
                break;
            }
        }
        if (add) {
            allFoods.add(food);
            EventLog.getInstance().logEvent(new Event(food.getName() + " added to Food List"));
        }
    }

    //EFFECT: returns list
    public ArrayList<Food> getList() {
        return allFoods;
    }

    //REQUIRES: num < allFoods.size()
    //EFFECT: returns foods
    public Food getFood(int num) {
        return allFoods.get(num);
    }

    //EFFECT: returns size
    public int getSize() {
        return allFoods.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("FoodList", foodListToJson());
        return json;
    }

    //EFFECTS: returns all foods in the list as a JSON array
    private JSONArray foodListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Food food: allFoods) {
            jsonArray.put(food.toJson());
        }

        return jsonArray;
    }
}
