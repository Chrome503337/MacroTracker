package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTest {

    FoodList f1;
    Day d1;

    @BeforeEach
    void runBefore() {
        f1 = new FoodList();
        d1 = new Day(1);
    }

    @Test
    void testEat() {
        Food whiteRice = f1.getFood(1);
        d1.eat(whiteRice, 250);

        assertEquals(325,d1.getCalories());
        assertEquals(0,d1.getProtein());
        assertEquals(0, d1.getFat());
        assertEquals(75,d1.getCarbohydrate());

        Food beef = f1.getFood(3);

        d1.eat(beef, 200);
        assertEquals((520 + 325), d1.getCalories());
        assertEquals(60 , d1.getProtein());
        assertEquals(40,d1.getFat());
        assertEquals(75, d1.getCarbohydrate());
    }

    @Test
    void testAddFood() {
        d1.addFood(new Food("tester", 1,1,1,1));
        List<Food> d1List = d1.getAllFoods();

        assertEquals(1, d1List.size());

        Day d2 = new Day(1);
        Food wr = new Food("White Rice", 1,1,1,1);
        d2.eat(wr, 100);
        d2.addFood(new Food("White Rice",2,2,2,2));
        List<Food> d2List = d2.getAllFoods();
        assertEquals(1,d2List.size());
    }

}
