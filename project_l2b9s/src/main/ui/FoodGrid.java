package ui;

import model.Food;
import model.FoodList;
import model.MApp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//JPanel that contains a grid of food buttons
public class FoodGrid extends JPanel {
    private MApp mapp;
    private Dimension buttonSize = new Dimension(150, 100);
    private JPanel grid;
    private FoodList foodList;

    //EFFECTS: Constructs a food grid
    public FoodGrid(MApp mapp) {
        this.mapp = mapp;
        grid = new JPanel();
        foodList = mapp.getFoodList();

        grid.setPreferredSize(new Dimension(MApp.WIDTH,2000));
        grid.setLayout(new FlowLayout(FlowLayout.LEADING));
        for (int i = 0; i < foodList.getSize(); i++) {
            FoodButton fb = new FoodButton(foodList.getFood(i), mapp);
            grid.add(fb);
        }
        this.add(grid);
    }

    //EFFECTS: creates button for the food name;
    private void foodButton(Food food) {
        JButton button = new JButton(food.getName());
        button.setPreferredSize(buttonSize);
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        grid.add(button);
    }
}
