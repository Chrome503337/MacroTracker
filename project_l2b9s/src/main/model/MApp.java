package model;

//This class represents the Macro app with its list of foods and the current day;
public class MApp {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    private int height;
    private int weight;
    private int age;
    private FoodList foodList;
    private Day day;

    public MApp() {
        foodList = new FoodList();
        day = new Day(1);
    }

    //EFFECTS: returns food list
    public FoodList getFoodList() {
        return foodList;
    }

    //EFFECTS: returns day
    public Day getDay() {
        return day;
    }

    //REQUIRES: num > 0
    //EFFECTS: sets day to new day
    public void setDay(Day day1) {
        this.day = day1;
    }

    //EFFECTS: sets food list
    public void setFoodList(FoodList fl) {
        this.foodList = fl;
    }

    //EFFECTS: sets the height
    public void setHeight(int height) {
        this.height = height;
    }

    //EFFECTS: sets the weight
    public void setWeight(int weight) {
        this.weight = weight;
    }

    //EFFECTS: sets the age
    public void setAge(int age) {
        this.age = age;
    }

    //EFFECTS: returns age
    public int getAge() {
        return this.age;
    }

    //EFFECTS: returns height
    public int getHeight() {
        return this.height;
    }

    //EFFECTS: returns weight
    public int getWeight() {
        return this.weight;
    }

}
