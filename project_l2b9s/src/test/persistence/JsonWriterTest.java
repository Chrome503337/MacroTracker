package persistence;

import model.Day;
import model.Food;
import model.FoodList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testInvalidFile() {
        try {
            FoodList f = new FoodList();
            Day d = new Day(1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }

    }

    @Test
    void testWriterEmptyDay() {
        try {
            Day d = new Day(1);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.writeDay(d);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            d = reader.readDay();
            assertEquals(1, d.getDay());
            assertEquals(0, d.getCalories());
            assertEquals(0, d.getProtein());
            assertEquals(0, d.getCarbohydrate());
            assertEquals(0, d.getFat());
            List<Food> foods = d.getAllFoods();
            assertEquals(0, foods.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyFoodList() {
        FoodList f = new FoodList();
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodList");
            writer.open();
            writer.writeFoodList(f);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodList");
            f = reader.readFoodList();
            assertEquals(14, f.getSize());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralDay() {
        Day d = new Day(5);
        d.eat(new Food("Spaghetti",100,75,2,13), 100);
        d.eat(new Food("Chicken Breast",100,0,4,31), 100);
        d.setCalories(700);
        d.setCarbohydrates(100);
        d.setFat(20);
        d.setProtein(80);

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDay.json");
            writer.open();
            writer.writeDay(d);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDay.json");
            d = reader.readDay();

            assertEquals(5, d.getDay());
            assertEquals(700, d.getCalories());
            assertEquals(80, d.getProtein());
            assertEquals(100, d.getCarbohydrate());
            assertEquals(20, d.getFat());
            List<Food> foods = d.getAllFoods();
            assertEquals(2, foods.size());
            checkFood("Spaghetti", 100, 75,2,13, foods.get(0));
            checkFood("Chicken Breast", 100, 0, 4, 31, foods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodList() {
        FoodList f = new FoodList();
        f.addNewFood(new Food("test1", 100, 20,30,40));
        f.addNewFood(new Food("test2", 200,10,5,8));

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodList.json");
            writer.open();
            writer.writeFoodList(f);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodList.json");
            f = reader.readFoodList();

            assertEquals(16, f.getSize());
            assertEquals("test1", f.getFood(14).getName());
            assertEquals("test2", f.getFood(15).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





















}
