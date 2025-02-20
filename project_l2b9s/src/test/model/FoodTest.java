package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FoodTest {

    Food f1;
    Food f2;
    Food f3;
    Food f4;


    @BeforeEach
    void runBefore() {
        f1 = new Food("Banana", 200,46,1,2);
        f2 = new Food("TesterFood", 100,50,10,10);
        f3 = new Food("White Rice", 100, 28, 0, 3);
        f4 = new Food("TesterFood2", 100,10,1,10);
    }


    @Test
    void testConstructor() {
        assertEquals("Banana", f1.getName());
        assertEquals(200, f1.getGrams());
        assertEquals(46, f1.getCarbohydrates());
        assertEquals(1, f1.getFat());
        assertEquals(2, f1.getProtein());
    }

    @Test
    void testGetCaloriesPerGram() {
        assertEquals(1.0, f1.getCaloriesPerGram());
        assertEquals(3.3, f2.getCaloriesPerGram());
        assertEquals(1.2, f3.getCaloriesPerGram());
        assertEquals(0.9,f4.getCaloriesPerGram());
    }

    @Test
    void testProteinPerGram() {
        assertEquals(0, f1.proteinPerGram());
        assertEquals(0.1,f2.proteinPerGram());
        assertEquals(0, f3.proteinPerGram());
        assertEquals(0.1, f4.proteinPerGram());
    }

    @Test
    void testFatPerGram() {
        Food food1 = new Food("orange", 150,12,35,32);
        Food food2 = new Food("apple", 176,90,44,0);
        assertEquals(0,f1.fatPerGram());
        assertEquals(0.1, f2.fatPerGram());
        assertEquals(0.2,food1.fatPerGram());
        assertEquals(0.3,food2.fatPerGram());
    }

    @Test
    void testCarbohydratesPerGram() {
        Food food1 = new Food("tester", 100,4,20,30);
        assertEquals(0.2,f1.carbohydratesPerGram());
        assertEquals(0.5, f2.carbohydratesPerGram());
        assertEquals(0.3,f3.carbohydratesPerGram());
        assertEquals(0,food1.carbohydratesPerGram());
    }

    @Test
    void testSetters() {
        f1.setName("yellow");
        f1.setGrams(100);
        f1.setCarbohydrates(30);
        f1.setFat(0);
        f1.setProtein(10);
        assertEquals("yellow", f1.getName());
        assertEquals(100, f1.getGrams());
        assertEquals(30, f1.getCarbohydrates());
        assertEquals(0, f1.getFat());
        assertEquals(10, f1.getProtein());

    }

}
