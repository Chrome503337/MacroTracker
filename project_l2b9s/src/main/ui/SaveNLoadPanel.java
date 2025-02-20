package ui;

import model.MApp;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Creates a JPanel with a save and load button to save the file
public class SaveNLoadPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE_FOODLIST = "./data/FoodList.json";
    private static final String JSON_STORE_DAY = "./data/Day.json";
    private JsonWriter jsonWriterDay;
    private JsonWriter jsonWriterFoodList;
    private JsonReader jsonReaderDay;
    private JsonReader jsonReaderFoodList;
    private MApp mapp;
    private JLabel saveLB;
    private JLabel loadLB;
    private JButton saveBT;
    private JButton loadBT;
    private JLabel confirm;

    //EFFECTS: constructs a JPanel with 2 buttons to save or load previous profile
    public SaveNLoadPanel(MApp mapp) {
        this.mapp = mapp;
        this.init();

        this.saveField();
        this.loadField();

        confirm = new JLabel();
        confirm.setBounds(100,310,300,50);
        confirm.setFont(new Font("Comic Sans MS", Font.ITALIC,15));
        confirm.setVisible(false);

        saveBT.addActionListener(this);
        loadBT.addActionListener(this);

        this.add(saveLB);
        this.add(saveBT);
        this.add(loadLB);
        this.add(loadBT);
        this.add(confirm);
    }

    //EFFECTS: initializes fields
    private void init() {
        this.jsonReaderDay = new JsonReader(JSON_STORE_DAY);
        this.jsonReaderFoodList = new JsonReader(JSON_STORE_FOODLIST);
        this.jsonWriterDay = new JsonWriter(JSON_STORE_DAY);
        this.jsonWriterFoodList = new JsonWriter(JSON_STORE_FOODLIST);
        this.setBounds(0,0,MApp.WIDTH,MApp.HEIGHT);
        this.setLayout(null);
    }

    //EFFECTS: creates save text and save button
    private void saveField() {
        saveLB = new JLabel("Save Profile: ");
        saveLB.setBounds(100,100,300,50);
        saveLB.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        saveBT = new JButton("Save");
        saveBT.setBounds(100,150,80,40);
        saveBT.setFont(new Font("Comic Sans MS", Font.PLAIN,19));
    }

    //EFFECTS: creates load text and load button
    private void loadField() {
        loadLB = new JLabel("Load Profile: ");
        loadLB.setBounds(100,200,300,50);
        loadLB.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        loadBT = new JButton("Load");
        loadBT.setBounds(100,250,80,40);
        loadBT.setFont(new Font("Comic Sans MS", Font.PLAIN,19));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBT) {
            try {
                jsonWriterDay.open();
                jsonWriterFoodList.open();
                jsonWriterFoodList.writeFoodList(mapp.getFoodList());
                jsonWriterDay.writeDay(mapp.getDay());
                jsonWriterDay.close();
                jsonWriterFoodList.close();

                confirm.setText("Profile Saved");
            } catch (FileNotFoundException exception) {
                confirm.setText("Unable to Save Profile");
            }
            confirm.setVisible(true);
        }
        this.checkLoad(e);

    }

    //EFFECTS: checks if load button has been selected and loads profile
    private void checkLoad(ActionEvent e) {
        if (e.getSource() == loadBT) {
            try {
                mapp.setFoodList(jsonReaderFoodList.readFoodList());
                mapp.setDay(jsonReaderDay.readDay());
                confirm.setText("Loaded Day: " + mapp.getDay().getDay() + " and Food List");
            } catch (IOException exception) {
                confirm.setText("Unable to Load profile");
            }
            confirm.setVisible(true);
        }
    }
}
