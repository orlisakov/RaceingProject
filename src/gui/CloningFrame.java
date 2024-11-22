package gui;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This frame represents the frame used for cloning racers
 */
public class CloningFrame extends JFrame implements ActionListener {

    private JComboBox<String> racerJComboBox;
    private JComboBox<EnumContainer.Color> colors;
    private JButton addBtn;
    private JButton exitBtn;
    private Arena arena;
    private ArenaPanel panel;

    /**
     * Constructor for this class, receives a panel that represents the arena
     * @param panel
     */
    public CloningFrame(ArenaPanel panel){
        this.arena = panel.getArena();
        this.panel = panel;
        this.setLayout(null);
        this.setSize(new Dimension(600, 400));

        racerJComboBox = new JComboBox<String>();
        updateRacerComboBox();


        racerJComboBox.setLocation(10, 10);
        racerJComboBox.setSize(250, 30);
        add(racerJComboBox);

        colors = new JComboBox<>();
        colors.addItem(EnumContainer.Color.BLUE);
        colors.addItem(EnumContainer.Color.RED);
        colors.addItem(EnumContainer.Color.GREEN);
        colors.addItem(EnumContainer.Color.BLACK);
        colors.addItem(EnumContainer.Color.YELLOW);
        colors.setSelectedIndex(0);
        colors.setLocation(10, 50);
        colors.setSize(250, 30);
        add(colors);

        addBtn = new JButton("Clone");
        addBtn.setLocation(50, 90);
        addBtn.setSize(100, 30);
        addBtn.addActionListener(this);
        add(addBtn);

        exitBtn = new JButton("Done");
        exitBtn.setLocation(50, 130);
        exitBtn.setSize(100, 30);
        exitBtn.addActionListener(this);
        add(exitBtn);


        this.setVisible(true);

    }


    /**
     * Updates the available racers to clone
     */
    public void updateRacerComboBox(){
        racerJComboBox.removeAllItems();
        for(Racer racer : arena.getActiveRacers()){
            racerJComboBox.addItem(racer.getName() + " " + racer.getClass().toString() + " " + racer.getColor());
        }
        racerJComboBox.setSelectedIndex(0);
    }

    /**
     * This method implements the logic for every button press
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Clone":

                Racer original = arena.getActiveRacers().get(racerJComboBox.getSelectedIndex());

                EnumContainer.Color cloneColor = (EnumContainer.Color) colors.getSelectedItem();
                Racer clone = (Racer) original.makeCopy(cloneColor);
                panel.addRacer(
                        clone.className(),
                        cloneColor.toString(),
                        clone.getName(),
                        clone.getMaxSpeed(),
                        clone.getAcceleration()
                );
                updateRacerComboBox();


                break;

            case "Done":
                this.dispose();
                break;
        }
    }
}
