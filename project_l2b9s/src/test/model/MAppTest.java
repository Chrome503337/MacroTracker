package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MAppTest {
    private MApp mapp;

    @BeforeEach
    public void runBefore() {
        mapp = new MApp();
    }

    @Test
    public void testConstructor() {
        Day day = mapp.getDay();
        FoodList fl = mapp.getFoodList();

        assertEquals(1, day.getDay());
        assertEquals(14, fl.getSize());
    }

    @Test
    public void testSetters() {
        mapp.setAge(10);
        mapp.setHeight(100);
        mapp.setWeight(80);

        assertEquals(10, mapp.getAge());
        assertEquals(100,mapp.getHeight());
        assertEquals(80, mapp.getWeight());

        Day day = new Day(5);
        FoodList fl = new FoodList();

        mapp.setDay(day);
        mapp.setFoodList(fl);

        assertEquals(day, mapp.getDay());
        assertEquals(fl, mapp.getFoodList());
    }

}
