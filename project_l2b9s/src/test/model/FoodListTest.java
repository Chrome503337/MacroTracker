package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodListTest {

    FoodList f1;

    @BeforeEach
    void runBefore() {
        f1 = new FoodList();
    }

    @Test
    void testConstructor() {
        assertEquals("Spaghetti",f1.getFood(0).getName());
        assertEquals("Beef",f1.getFood(3).getName());
    }

    @Test
    void testGetList() {
        List<Food> foods = f1.getList();

        assertEquals("White Rice", foods.get(1).getName());
    }
}
