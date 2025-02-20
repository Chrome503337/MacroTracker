package ui;

import model.Day;
import model.Food;
import model.FoodList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Macro App where users can eat, check daily macros, logs, and add different foods
public class MacroApp {

    private static final String JSON_STORE_FOODLIST = "./data/FoodList.json";
    private static final String JSON_STORE_DAY = "./data/Day.json";
    private FoodList f1;
    private int age;
    private int height;
    private int weight;
    boolean keepgoing = true;
    private Day currentDay;
    private Scanner input;
    private JsonWriter jsonWriterDay;
    private JsonWriter jsonWriterFoodList;
    private JsonReader jsonReaderDay;
    private JsonReader jsonReaderFoodList;

    //EFFECTS: runs Macro App
    public MacroApp() {
        runMacro();
    }

    //EFFECTS: calls personal info and initializes it
    private void runMacro() {
        String command = null;
        init();

        personalInfoMenu();
        command = input.nextLine();
        if (command.equals("1")) {
            personalInfo();
        } else {
            System.out.println("Goodbye");
            keepgoing = false;
        }

        while (keepgoing) {
            displayInfo();
            command = input.next();
            runCommands(command);
        }
    }

    //EFFECTS: takes command and calls the assigned methods
    private void runCommands(String command) {
        switch (command) {
            case "1":
                eat();
                break;
            case "2":
                viewMacros();
                break;
            case "3":
                listOfFoods();
                break;
            case "q":
                keepgoing = false;
                System.out.println("Quitting Program");
                break;
            case "l":
                load();
                break;
            default:
                runCommands2(command);
                break;
        }
    }

    //EFFECTS: takes command and calls the assign methods
    private void runCommands2(String command) {
        switch (command) {
            case "4":
                addNewFood();
                break;
            case "5":
                dailyLog();
                break;
            case "6":
                newDay();
                break;
            case "s":
                save();
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    //EFFECTS: creates the scanner object and day object
    private void init() {
        input = new Scanner(System.in);
        currentDay = new Day(1);
        f1 = new FoodList();
        jsonWriterDay = new JsonWriter(JSON_STORE_DAY);
        jsonWriterFoodList = new JsonWriter(JSON_STORE_FOODLIST);
        jsonReaderDay = new JsonReader(JSON_STORE_DAY);
        jsonReaderFoodList = new JsonReader(JSON_STORE_FOODLIST);
    }

    //EFFECTS: starting screen
    private void personalInfoMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Enter Personal Info");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: personal info screen
    private void personalInfo() {
        System.out.println("Enter age: ");
        age = input.nextInt();
        System.out.println("Enter height: ");
        height = input.nextInt();
        System.out.println("Enter weight: ");
        weight = input.nextInt();
    }

    //EFFECTS: prints the menu
    private void displayInfo() {
        System.out.println("\nSelect from");
        System.out.println("\t1 -> Eat");
        System.out.println("\t2 -> View Macros");
        System.out.println("\t3 -> List");
        System.out.println("\t4 -> Add New Food");
        System.out.println("\t5 -> Daily Log");
        System.out.println("\t6 -> New Day");
        System.out.println("\ts -> Save Profile");
        System.out.println("\tl -> Load Profile");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: selects food and how much to eat
    private void eat() {
        int num;
        int amount;
        System.out.println("Choose a Food: ");
        listOfFoods();
        num = input.nextInt();
        System.out.println("Enter the amount(g): ");
        amount = input.nextInt();

        currentDay.eat(f1.getFood(num), amount);

        System.out.println(f1.getFood(num).getName() + " and " + amount + "g has been recorded");

    }

    //EFFECTS: view Macros
    private void viewMacros() {
        System.out.println("Day " + currentDay.getDay() + ":");
        System.out.println("Calories: " + currentDay.getCalories());
        System.out.println("Fat: " + currentDay.getFat());
        System.out.println("Protein: " + currentDay.getProtein());
    }

    //EFFECTS: view list of foods
    private void listOfFoods() {
        for (int i = 0; i < f1.getSize(); i++) {
            System.out.println("\t" + i + ". " + f1.getFood(i).getName());
        }
    }

    //EFFECTS: adds new food
    private void addNewFood() {
        String name;
        int grams;
        int carbohydrates;
        int fat;
        int protein;

        System.out.println("Enter Food Name: ");
        name = input.next();
        System.out.println("Enter serving size(g): ");
        grams = input.nextInt();
        System.out.println("Enter carbohydrates(g): ");
        carbohydrates = input.nextInt();
        System.out.println("Enter fat(g): ");
        fat = input.nextInt();
        System.out.println("Enter protein(g): ");
        protein = input.nextInt();

        System.out.println("The food " + name + " has been added");
        f1.addNewFood(new Food(name,grams,carbohydrates,fat,protein));

    }

    //EFFECTS: see the foods you've eaten
    private void dailyLog() {
        for (Food f: currentDay.getAllFoods()) {
            System.out.println(f.getName());
        }
    }

    //EFFECTS: creates new day
    private void newDay() {
        int dayNumber;
        System.out.println("Enter new day number: ");
        dayNumber = input.nextInt();

        Day newDay = new Day(dayNumber);
        currentDay = newDay;
    }

    //EFFECTS: saves food list and day to json file
    private void save() {
        try {
            jsonWriterDay.open();
            jsonWriterFoodList.open();
            jsonWriterFoodList.writeFoodList(f1);
            jsonWriterDay.writeDay(currentDay);
            jsonWriterDay.close();
            jsonWriterFoodList.close();

            System.out.println("Profile Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: "
                    + JSON_STORE_FOODLIST + ", or " + JSON_STORE_DAY);
        }
    }

    //EFFECTS: loads day and food list from json file
    private void load() {
        try {
            f1 = jsonReaderFoodList.readFoodList();
            currentDay = jsonReaderDay.readDay();
            System.out.println("Loaded Day: " + currentDay.getDay() + " and Food List");
        } catch (IOException e) {
            System.out.println("Unable to write to file: "
                    + JSON_STORE_FOODLIST + ", or " + JSON_STORE_DAY);
        }
    }

}
