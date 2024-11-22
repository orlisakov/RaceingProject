
package gui;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.MyObservable;
import utilities.MyObserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Arrays;

/**
 * This class represents the Info table frame that pops out when you click on Show Table
 */
public class InfoTable extends JFrame implements MyObserver {

    private JTable table;
    private Arena arena;
    private String[] columnNames = {"Racer name",
            "Current speed",
            "Max speed",
            "Current X location",
            "Status"};

    private JPanel tabPan;
    private JScrollPane scrollPane;

    private int racersNumber;

    /**
     * Constructor for this class
     * @param arena
     * @param racersNumber
     */
    public InfoTable(Arena arena, int racersNumber){

        super("Racers information");
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(600, 300));
        this.racersNumber = racersNumber;
        this.arena = arena;

        arena.clearObservers();
        arena.addObserver(this);
        setAlwaysOnTop(true);

        String[][] data = getRacerData();

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        scrollPane = new JScrollPane(table);

        tabPan = new JPanel();
        tabPan.add(scrollPane);                   

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true); 
    }

    /**
     * Invoked upon an observable observed by this class
     * @param o
     * @param arg
     */
    @Override
    public void update(MyObservable o, Object arg) {
        updateTableWithCurrentParameters();
    }


    /**
     * Updates the table with the current racer info
     * @return
     */
    private String[][] getRacerData(){
        String[][] data = new String[racersNumber][5];
        if(table != null )table.removeAll();

        int i=0;

        if(arena.getCompleatedRacers().size() > 0) {
            for (Racer r : arena.getCompleatedRacers()) {
                if(r != null) {
                    try{
                        if(i == 8){
                            System.out.println("IN COMPLETED RACERS LOOP AND I IS 8");
                            System.out.println("THERE ARE " + arena.getCompleatedRacers().size() + " Racers");

                            for(Racer ra : arena.getCompleatedRacers()){
                                System.out.println(r.describeRacer() + "\t" + ra.getState().getStateMessage());
                            }
                        }
                        data[i][0] = r.getName();
                        data[i][1] = "" + r.getCurrentSpeed();
                        data[i][2] = "" + r.getMaxSpeed();
                        data[i][3] = "" + r.getLocation().getX();
                        data[i][4] = r.getState().getStateMessage();
                        i++;
                        if(i == 8) i = 7;
                    } catch (Exception e){
                        System.out.println(i);
                    }

                }
            }
        }

        if(arena.getActiveRacers().size() > 0) {
            for (Racer r : arena.getActiveRacers()) {
                if(r != null) {
                    try {
                        data[i][0] = r.getName();
                        data[i][1] = "" + r.getCurrentSpeed();
                        data[i][2] = "" + r.getMaxSpeed();
                        data[i][3] = "" + r.getLocation().getX();
                        data[i][4] = r.getState().getStateMessage();
                        i++;
                        if(i == 8) i = 7;
                    } catch (Exception e){
                        System.out.println();
                    }
                }
            }

        }

        sortData(data);
        return data;
    }

    /**
     * Refreshes the table
     */
    private void updateTableWithCurrentParameters() {

        String[][] data = getRacerData();
        TableModel tm = new DefaultTableModel(data, columnNames);
        try{
            table.setModel(tm);
        } catch (Exception e){
            System.out.println("testing");
        }

        table.repaint();
        table.revalidate();
        tabPan.repaint();
        tabPan.revalidate();
    }

    /**
     * Parse string to double
     * @param str
     * @param defaultValue
     * @return
     */
    private static double parseDoubleOrDefault(String str, double defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * Sorts the racers according to their place
     * @param data
     */
    public static void sortData(String[][] data) {
        Thread sortThread = new Thread(() -> {
            Arrays.sort(data, (row1, row2) -> {
                String value1Str = row1[3];
                String value2Str = row2[3];

                double value1 = parseDoubleOrDefault(value1Str, Double.MIN_VALUE);
                double value2 = parseDoubleOrDefault(value2Str, Double.MIN_VALUE);

                return Double.compare(value2, value1); // Descending order
            });
        });

        sortThread.setDaemon(true);
        sortThread.start();

        try {
            sortThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
