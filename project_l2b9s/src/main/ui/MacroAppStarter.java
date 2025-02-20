package ui;

import model.Event;
import model.EventLog;
import model.MApp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

//Creates a Macro App
public class MacroAppStarter extends JFrame implements ActionListener {

    private MApp mapp;
    private JMenuBar menuBar;
    private JMenu general;
    private JMenuItem foodGrid;
    private JMenuItem logs;
    private JMenu addNew;
    private JMenuItem addFood;
    private JMenuItem newDay;
    private JMenu other;
    private JMenuItem saveAndLoad;
    private JMenuItem personalInfo;
    private JScrollPane gridScrollPane;
    private FoodGrid fg;
    private AddFood addFoodPanel;
    private FoodLogs foodLogs;
    private NewDay newDayPanel;
    private SaveNLoadPanel saveNLoadPanel;
    private PersonalInfoPanel personalInfoPanel;

    //EFFECTS: constructs a macro app
    public MacroAppStarter() {
        super("Macros");
        mapp = new MApp();

        fg = new FoodGrid(mapp);
        gridScrollPane = new JScrollPane(fg,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuBar();
        foodLogs = new FoodLogs(mapp);
        newDayPanel = new NewDay(mapp);
        saveNLoadPanel = new SaveNLoadPanel(mapp);
        personalInfoPanel = new PersonalInfoPanel(mapp);

        this.closingWindow();

        setSize(MApp.WIDTH, MApp.HEIGHT);
        setResizable(false);
        setLayout(null);
        this.setContentPane(personalInfoPanel);
        setVisible(true);
    }

    //EFFECTS: creates a menu bar and adds it to this
    private void menuBar() {
        menuBar = new JMenuBar();

        general = new JMenu("General");
        foodGrid = new JMenuItem("Grid");
        logs = new JMenuItem("Logs");
        addNew = new JMenu("New");
        newDay = new JMenuItem("Day");
        addFood = new JMenuItem("Add Food");
        other = new JMenu("Other");
        saveAndLoad = new JMenuItem("Save/Load");
        personalInfo = new JMenuItem("Personal Info");

        general.add(foodGrid);
        general.add(logs);
        addNew.add(newDay);
        addNew.add(addFood);
        other.add(saveAndLoad);
        other.add(personalInfo);

        menuBar.setPreferredSize(new Dimension(100,40));

        setMenuBarListener();

        menuBar.add(general);
        menuBar.add(addNew);
        menuBar.add(other);

        this.setJMenuBar(menuBar);
    }

    //EFFECTS: sets all listeners to menu items in menu bar
    private void setMenuBarListener() {
        foodGrid.addActionListener(this);
        addFood.addActionListener(this);
        logs.addActionListener(this);
        newDay.addActionListener(this);
        saveAndLoad.addActionListener(this);
        personalInfo.addActionListener(this);
    }

    //EFFECTS: prints event log when closing the jframe
    private void closingWindow() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Iterator<Event> it = EventLog.getInstance().iterator();
                while (it.hasNext()) {
                    System.out.println(it.next() + "\n");
                }
                System.exit(0);
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFood) {
            addFoodPanel = new AddFood(mapp);
            this.setContentPane(addFoodPanel);
        }
        if (e.getSource() == foodGrid) {
            fg = new FoodGrid(mapp);
            gridScrollPane = new JScrollPane(fg,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            this.setContentPane(gridScrollPane);
        }
        if (e.getSource() == logs) {
            foodLogs = new FoodLogs(mapp);
            this.setContentPane(foodLogs);
        }
        if (e.getSource() == newDay) {
            newDayPanel = new NewDay(mapp);
            this.setContentPane(newDayPanel);
        }

        this.checkOther(e);

        this.revalidate();
        this.repaint();
    }

    //EFFECTS: Checks action for other menu
    private void checkOther(ActionEvent e) {
        if (e.getSource() == saveAndLoad) {
            saveNLoadPanel = new SaveNLoadPanel(mapp);
            this.setContentPane(saveNLoadPanel);
        }
        if (e.getSource() == personalInfo) {
            personalInfoPanel = new PersonalInfoPanel(mapp);
            this.setContentPane(personalInfoPanel);
        }
    }

}

