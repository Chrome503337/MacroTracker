package ui;

import model.Day;
import model.MApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//JPanel that changes the day
public class NewDay extends JPanel implements ActionListener {
    private MApp mapp;
    private TextField dayTF;
    private JButton create;
    private JLabel confirm;
    private JLabel imageLB;
    private ImageIcon image;

    //EFFECTS: constructs a J panel to change the current day
    public NewDay(MApp mapp) {
        this.mapp = mapp;
        this.setBounds(0,0,MApp.WIDTH,MApp.HEIGHT);
        this.setLayout(null);

        JLabel dayTxt = new JLabel("Enter New Day: ");
        dayTxt.setBounds(150,200,300,50);
        dayTxt.setFont(new Font("Comic Sans MS", Font.ITALIC,21));

        dayTF = new TextField("1");
        dayTF.setBounds(150,250,100,40);
        dayTF.setFont(new Font("Roboto Mono", Font.BOLD,17));

        create = new JButton("Create");
        create.setBounds(255,250,80,40);
        create.setFont(new Font("Comic Sans MS",Font.PLAIN,18));

        confirm = new JLabel();
        confirm.setBounds(150,305,300,50);
        confirm.setFont(new Font("Comic Sans MS", Font.ITALIC,15));
        confirm.setVisible(false);

        this.loadImage();

        create.addActionListener(this);

        this.add(dayTxt);
        this.add(dayTF);
        this.add(create);
        this.add(confirm);

    }

    //EFFECTS: creates icon and adds it to this
    private void loadImage() {

        image = new ImageIcon("/Users/jadlu/CPSC 210/project_l2b9s/schedule128.png");
        imageLB = new JLabel();

        imageLB.setIcon(image);
        imageLB.setBounds(186,330,150,150);
        this.add(imageLB);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            int dayNum = Integer.parseInt(dayTF.getText());
            mapp.setDay(new Day(dayNum));
            confirm.setText("Added Day " + dayNum);
            confirm.setVisible(true);
        }
    }
}
