package ui;

import model.Day;
import model.Food;
import model.MApp;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JPanel that displays the food's information and contains a eat button
public class FoodInfo extends JPanel implements ActionListener {
    private Food food;
    private MApp mapp;
    private JPanel topPane;
    private JPanel bottomPane;
    private JTextField textField;
    private JLabel confirmed;
    private Font font = new Font("Comic Sans MS", Font.PLAIN, 20);

    //EFFECTS: creates a panel with top and bottom panel
    public FoodInfo(Food food, MApp mapp) {
        this.mapp = mapp;
        this.food = food;
        this.setLayout(null);

        topPane = new JPanel();
        topPane.setLayout(null);
        topPane.setBounds(0,0,500,200);
        topPane.setBackground(Color.black);
        foodText(food);

        bottomPane = new JPanel();
        bottomPane.setLayout(null);
        bottomPane.setBounds(0,200,500,300);
        bottomPane.setBackground(new Color(0xE6E3D3));

        loadBottomPane();

        this.add(topPane);
        this.add(bottomPane);

    }

    //EFFECTS: creates a text area with the food's info and adds
    //         it to the top pane
    private void foodText(Food food) {
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.append("\n\t" + food.getName() + ": \n");
        info.append("\tServing Size: " + food.getGrams() + " g\n");
        info.append("\tCarbohydrates: " + food.getCarbohydrates() + " g\n");
        info.append("\tFat: " + food.getFat() + " g\n");
        info.append("\tProtein: " + food.getProtein() + " g\n");
        info.setBounds(0,0,500,200);
        info.setFont(font);
        info.setBackground(new Color(0xE6E3D3));

        topPane.add(info);
    }

    //EFFECTS: loads the bottom panel
    private void loadBottomPane() {
        bottomPaneLabel();

        textField = new JTextField("0");
        textField.setFont(new Font("Roboto Mono", Font.BOLD,16));
        textField.setBounds(100,75,300,50);

        JButton button = new JButton("Eat");
        button.addActionListener(this);
        button.setBounds(195,125,110,50);
        button.setFont(new Font("Comic Sans", Font.BOLD,17));

        confirmed = new JLabel();
        confirmed.setText(food.getName() + " has been added");
        confirmed.setBounds(148,170,500,100);
        confirmed.setFont(new Font("Comic Sans", Font.ITALIC,15));
        confirmed.setVisible(false);

        bottomPane.add(confirmed);
        bottomPane.add(button);
        bottomPane.add(textField);
    }

    //EFFECTS: adds label to bottom pane
    private void bottomPaneLabel() {
        JLabel txt = new JLabel();
        txt.setText("Enter the amount of grams");
        txt.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
        txt.setBounds(140,5,500,100);
        bottomPane.add(txt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int amount = Integer.parseInt(textField.getText());
        Day day = mapp.getDay();
        day.eat(food,amount);
        confirmed.setText(amount + "g of " + food.getName() + " has been added");
        confirmed.setVisible(true);
    }
}
