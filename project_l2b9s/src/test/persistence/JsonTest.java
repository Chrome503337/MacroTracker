package persistence;

import model.Day;
import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String name, int grams, int carbs,
                            int fat, int protein, Food d){
        assertEquals(name, d.getName());
        assertEquals(grams, d.getGrams());
        assertEquals(carbs, d.getCarbohydrates());
        assertEquals(fat, d.getFat());
        assertEquals(protein, d.getProtein());
    }
}
