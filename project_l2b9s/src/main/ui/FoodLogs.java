package ui;

import model.Day;
import model.Food;
import model.MApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//JPanel that displays the information of the current day and the foods you have ate
public class FoodLogs extends JPanel {
    private MApp mapp;
    private Day curDay;

    //EFFECTS: Constructs a Day info and Day list of foods panel
    public FoodLogs(MApp mapp) {
        this.mapp = mapp;
        curDay = mapp.getDay();
        this.setBounds(0,0,MApp.WIDTH,MApp.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        displayDayInfo();
        displayDayFoods();

    }

    //EFFECTS: adds the day number and information of that day to this
    private void displayDayInfo() {
        JLabel dayNumber = new JLabel("Day: " + curDay.getDay());
        dayNumber.setFont(new Font("Comic Sans", Font.BOLD,21));
        dayNumber.setBounds(100,50,400,50);

        JTextArea dayInfo = new JTextArea();
        dayInfo.setEditable(false);
        dayInfo.setBounds(100,100,400,100);
        dayInfo.setBackground(Color.WHITE);
        dayInfo.append("Calories: " + curDay.getCalories() + "\n");
        dayInfo.append("Fat: " + curDay.getFat() + "\n");
        dayInfo.append("Protein: " + curDay.getProtein() + "\n");
        dayInfo.setFont(new Font("Comic Sans",Font.PLAIN,18));

        this.add(dayNumber);
        this.add(dayInfo);
    }

    //EFFECTS: creates a JLabel and JTextArea to display a list of foods
    private void displayDayFoods() {

        JLabel listTxt = new JLabel("Logs: ");
        listTxt.setBounds(100,200,400,50);
        listTxt.setFont(new Font("Comic Sans",Font.BOLD,21));

        JTextArea list = new JTextArea();
        list.setEditable(false);
        list.setBounds(100,250,300,400);
        list.setBackground(Color.WHITE);
        ArrayList<Food> listOfFoods = curDay.getAllFoods();
        for (Food food : listOfFoods) {
            list.append(food.getName() + "\n");
        }
        list.setFont(new Font("Comic Sans",Font.PLAIN,18));

        this.add(listTxt);
        this.add(list);
    }




}
