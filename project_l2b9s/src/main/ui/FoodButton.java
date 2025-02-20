package ui;

import model.Food;
import model.MApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creates a food button with its food name
public class FoodButton extends JButton implements ActionListener {
    private Food food;
    private MApp mapp;
    private Dimension buttonSize = new Dimension(150, 100);
    private Font font = new Font("Comic Sans MS", Font.PLAIN, 17);

    //EFFECTS: constructs a button with the food's name
    public FoodButton(Food food, MApp mapp) {
        this.mapp = mapp;
        this.food = food;

        this.setPreferredSize(buttonSize);
        this.setText(food.getName());
        this.setFont(font);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame newFrame = new JFrame(food.getName());
        FoodInfo info = new FoodInfo(food, mapp);
        newFrame.add(info);
        newFrame.setSize(500,500);
        newFrame.setLayout(null);
        newFrame.setResizable(false);
        newFrame.setContentPane(info);
        newFrame.setVisible(true);
    }
}
