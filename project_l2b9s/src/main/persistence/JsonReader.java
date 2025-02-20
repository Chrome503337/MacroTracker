package persistence;

import model.Day;
import model.Food;
import model.FoodList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

// Represents a reader that reads the food list and current day
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads food list from file and returns it
    // throws IOEXCEPTION if an error occurs reading data from file
    public FoodList readFoodList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseFoodList(jsonObject);
    }

    //EFFECTS: reads day from workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day readDay() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseDay(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses food list from JSON object and returns it
    private FoodList parseFoodList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("FoodList");
        FoodList fl = new FoodList();
        for (Object json: jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fl, nextFood);
        }
        return fl;
    }

    //MODIFIES: fl
    //EFFECTS: parses food from JSON object and add it to food list
    private void addFood(FoodList fl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double grams = jsonObject.getDouble("grams");
        int carbohydrates = jsonObject.getInt("carbohydrates");
        int fat = jsonObject.getInt("fat");
        int protein = jsonObject.getInt("protein");

        Food f1 = new Food(name, (int) grams, carbohydrates, fat, protein);
        fl.addNewFood(f1);
    }

    //EFFECTS: parses day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        int currentDay = jsonObject.getInt("day");
        Day d1 = new Day(currentDay);
        setDay(d1, jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("Today'sFoods");
        for (Object json: jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            eatFood(d1, nextFood);
        }

        return d1;
    }

    //MODIFIES: d1
    //EFFECTS: parses foods from JSON object and adds it to day
    private void eatFood(Day d1, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double grams = jsonObject.getDouble("grams");
        int carbohydrates = jsonObject.getInt("carbohydrates");
        int fat = jsonObject.getInt("fat");
        int protein = jsonObject.getInt("protein");

        Food f1 = new Food(name, (int) grams, carbohydrates, fat, protein);
        d1.addFood(f1);
    }

    //EFFECT: sets calories, carbohydrates, fat, and protein to day object
    private void setDay(Day d1, JSONObject jsonObject) {
        int calories = jsonObject.getInt("calories");
        int carbohydrates = jsonObject.getInt("carbohydrates");
        int fat = jsonObject.getInt("fat");
        int protein = jsonObject.getInt("protein");

        d1.setCalories(calories);
        d1.setCarbohydrates(carbohydrates);
        d1.setFat(fat);
        d1.setProtein(protein);
    }




}
