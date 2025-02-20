package ui;

import model.Food;
import model.MApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Display a panel to add a new food to the food list
public class AddFood extends JPanel implements ActionListener {
    private MApp mapp;
    private Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
    private Font fontTF = new Font("Comic Sans MS", Font.ITALIC, 17);
    private TextField foodNameTF;
    private TextField servingSizeTF;
    private TextField carbohydratesTF;
    private TextField fatTF;
    private TextField proteinTF;
    private JLabel foodNameTxT;
    private JLabel servingSizeTxT;
    private JLabel carbohydratesTxT;
    private JLabel fatTxT;
    private JLabel proteinTxT;
    private JButton addButton;
    private JLabel confirm;


    //EFFECTS: constructs a panel with JLabel and JTextField
    public AddFood(MApp mapp) {
        this.mapp = mapp;
        this.setBounds(0,0, MApp.WIDTH,MApp.HEIGHT);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        displayFoodName();
        displayServingSize();
        displayCarbohydrates();
        displayFat();
        displayProtein();

        addButton = new JButton("~ Add ~");
        addButton.setBounds(175,575,150,50);
        addButton.setFont(new Font("Open Sans", Font.PLAIN,16));

        confirm = new JLabel();
        confirm.setBounds(148,620,500,50);
        confirm.setFont(new Font("Comic Sans",Font.ITALIC,15));
        confirm.setVisible(false);

        addButton.addActionListener(this);

        this.add(addButton);
        this.add(confirm);

    }

    //EFFECTS: adds food name text and text field
    private void displayFoodName() {
        foodNameTxT = new JLabel("Enter Food Name: ");
        foodNameTxT.setBounds(50,50,400,50);
        foodNameTxT.setFont(font);

        foodNameTF = new TextField();
        foodNameTF.setBounds(50,100,220,50);
        foodNameTF.setFont(fontTF);

        this.add(foodNameTxT);
        this.add(foodNameTF);

    }

    //EFFECTS: adds serving size text and text field
    private void displayServingSize() {
        servingSizeTxT = new JLabel("Serving Size(g): ");
        servingSizeTxT.setBounds(50,150,400,50);
        servingSizeTxT.setFont(font);

        servingSizeTF = new TextField();
        servingSizeTF.setBounds(50,200,220,50);
        servingSizeTF.setFont(fontTF);

        this.add(servingSizeTxT);
        this.add(servingSizeTF);
    }

    //EFFECTS: adds carbohydrates text and text field
    private void displayCarbohydrates() {
        carbohydratesTxT = new JLabel("Carbohydrates(g): ");
        carbohydratesTxT.setBounds(50,250,400,50);
        carbohydratesTxT.setFont(font);

        carbohydratesTF = new TextField();
        carbohydratesTF.setBounds(50,300,220,50);
        carbohydratesTF.setFont(fontTF);

        this.add(carbohydratesTxT);
        this.add(carbohydratesTF);
    }

    //EFFECTS: adds fat text and text field
    private void displayFat() {
        fatTxT = new JLabel("Fat(g): ");
        fatTxT.setBounds(50,350,400,50);
        fatTxT.setFont(font);

        fatTF = new TextField();
        fatTF.setBounds(50,400,220,50);
        fatTF.setFont(fontTF);

        this.add(fatTxT);
        this.add(fatTF);
    }

    //EFFECTS: adds protein text and text field
    private void displayProtein() {
        proteinTxT = new JLabel("Protein(g): ");
        proteinTxT.setBounds(50,450,400,50);
        proteinTxT.setFont(font);

        proteinTF = new TextField();
        proteinTF.setBounds(50,500,220,50);
        proteinTF.setFont(fontTF);

        this.add(proteinTxT);
        this.add(proteinTF);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String name = foodNameTF.getText();
        int servingSize = Integer.parseInt(servingSizeTF.getText());
        int carbs = Integer.parseInt(carbohydratesTF.getText());
        int fat = Integer.parseInt(fatTF.getText());
        int protein = Integer.parseInt(proteinTF.getText());

        Food temp = new Food(name,servingSize,carbs,fat,protein);
        mapp.getFoodList().addNewFood(temp);

        confirm.setText(name + " has been added");
        confirm.setVisible(true);
    }
}
