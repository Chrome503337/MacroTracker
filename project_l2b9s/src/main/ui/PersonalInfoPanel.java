package ui;

import model.MApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Displays a panel for users to set their age, height, and weight
public class PersonalInfoPanel extends JPanel implements ActionListener {

    private MApp mapp;
    private Font font = new Font("Comic Sans MS", Font.PLAIN, 19);
    private Font fontTF = new Font("Comic Sans MS", Font.ITALIC, 17);

    private JLabel ageLB;
    private JLabel heightLB;
    private JLabel weightLB;
    private JTextField ageTF;
    private JTextField heightTF;
    private JTextField weightTF;
    private JButton saveBT;
    private JLabel confirm;

    public PersonalInfoPanel(MApp mapp) {
        this.mapp = mapp;
        this.setBounds(0,0,MApp.WIDTH,MApp.HEIGHT);
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);

        displayAge();
        displayHeight();
        displayWeight();

        saveBT = new JButton("~ Save ~");
        saveBT.setBounds(175,380,150,50);
        saveBT.setFont(new Font("Open Sans", Font.PLAIN,16));

        confirm = new JLabel("Saved");
        confirm.setBounds(230,430,500,50);
        confirm.setFont(new Font("Comic Sans",Font.ITALIC,15));
        confirm.setVisible(false);

        saveBT.addActionListener(this);

        this.add(saveBT);
        this.add(confirm);

    }

    //EFFECTS: adds age label and text field
    private void displayAge() {
        ageLB = new JLabel("Enter Your Age: ");
        ageLB.setBounds(50,50,400,50);
        ageLB.setFont(font);

        ageTF = new JTextField();
        ageTF.setBounds(50,100,200,50);
        ageTF.setFont(fontTF);

        this.add(ageLB);
        this.add(ageTF);
    }

    //EFFECTS: adds height label and text field
    private void displayHeight() {
        heightLB = new JLabel("Enter Your Height (cm): ");
        heightLB.setBounds(50,150,400,50);
        heightLB.setFont(font);

        heightTF = new JTextField();
        heightTF.setBounds(50,200,200,50);
        heightTF.setFont(fontTF);

        this.add(heightLB);
        this.add(heightTF);
    }

    //EFFECTS: adds weight label and text field
    private void displayWeight() {
        weightLB = new JLabel("Enter Your Weight (kg): ");
        weightLB.setBounds(50,250,400,50);
        weightLB.setFont(font);

        weightTF = new JTextField();
        weightTF.setBounds(50,300,200,50);
        weightTF.setFont(fontTF);

        this.add(weightLB);
        this.add(weightTF);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int age = Integer.parseInt(ageTF.getText());
        int height = Integer.parseInt(heightTF.getText());
        int weight = Integer.parseInt(weightTF.getText());

        mapp.setAge(age);
        mapp.setHeight(height);
        mapp.setWeight(weight);

        confirm.setVisible(true);
    }
}
