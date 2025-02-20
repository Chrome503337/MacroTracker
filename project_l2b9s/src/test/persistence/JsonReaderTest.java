package persistence;

import model.Day;
import model.Food;
import model.FoodList;
import java.util.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodList f1 = reader.readFoodList();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }

        try {
            Day d = reader.readDay();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyDay() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDay.json");

        try {
            Day d = reader.readDay();
            assertEquals(1, d.getDay());
            assertEquals(0, d.getCalories());
            assertEquals(0, d.getProtein());
            assertEquals(0, d.getCarbohydrate());
            assertEquals(0, d.getFat());
            List<Food> foods = d.getAllFoods();
            assertEquals(0, foods.size());
        } catch (IOException e) {
            fail("Could not read file");
        }
    }

    @Test
    void testReaderEmptyFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodList.json");

        try {
            FoodList list = reader.readFoodList();

            assertEquals(14, list.getSize());
        } catch (IOException e) {
            fail("Could not read file");
        }

    }

    @Test
    void testReaderGeneralDay() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDay.json");

        try {
            Day d = reader.readDay();
            assertEquals(5, d.getDay());
            assertEquals(500, d.getCalories());
            assertEquals(40, d.getProtein());
            assertEquals(80, d.getCarbohydrate());
            assertEquals(10, d.getFat());
            List<Food> foods = d.getAllFoods();
            assertEquals(3, foods.size());
            checkFood("Milk", 244, 12,5,8,foods.get(0));
            checkFood("Peanut Butter", 32,8,16,7, foods.get(1));
            checkFood("Bread", 29,14,1,3, foods.get(2));

        } catch (IOException e) {
            fail("Could not read file");
        }
    }

    @Test
    void testReaderGeneralFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodList.json");

        try {
            FoodList f = reader.readFoodList();
            assertEquals(16, f.getSize());
            assertEquals("Tester", f.getFood(14).getName());
            assertEquals("Test2", f.getFood(15).getName());

        } catch (IOException e) {
            fail("Could not read list");
        }

    }




















}
