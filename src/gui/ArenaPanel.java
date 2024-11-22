
package gui;

import factory.ArenaFactory;
import factory.CarRaceBuilder;
import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utilities.EnumContainer;


public class ArenaPanel extends JPanel implements Runnable{
    private int arenaLength = 1000;
    private int arenaHeight = 700;
    private int maxRacers = 8;
    private String chosenArena = null;
    private int racersNumber = 0;
    private ImageIcon racersImages[] = null;
    private static ArrayList<Racer> racers;
    private Arena arena = null;
    private RaceFrame raceFrame = null;
    private boolean raceStarted = false;
    private boolean raceFinished = false; 
    private static final RaceBuilder builder = RaceBuilder.getInstance();
    private InfoTable infoTable = null;
    private CloningFrame clonePanel = null;
    private ArenaFactory arenaFactory = null;
    private CarRaceBuilder defaultBuilder = null;
    
    
    public void initArena(){

        this.removeAll();
        setPreferredSize(new Dimension(arenaLength+80,arenaHeight));
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("icons/"+chosenArena+".jpg").getImage().getScaledInstance(arenaLength+80, arenaHeight, Image.SCALE_DEFAULT));
        JLabel picLabel1 = new JLabel(imageIcon1);
        picLabel1.setLocation(0, 0);
        picLabel1.setSize(arenaLength+80, arenaHeight);
        add(picLabel1);
        for (int i=0; i<racersNumber; i++){
            JLabel picLabel2 = new JLabel(racersImages[i]);
            picLabel2.setLocation((int) racers.get(i).getLocation().getX()+5, (int) racers.get(i).getLocation().getY());
            picLabel2.setSize(70, 70);
            picLabel1.add(picLabel2);
        }
    }
    
    
    public ArenaPanel()  {
        setLayout(null);
        initArena();
    }
    
    
    
    public void buildArena(String chosenArena){
        raceStarted = raceFinished = false; 
        racersNumber = 0;
        this.chosenArena = chosenArena;
        int newHeight = (maxRacers+1)*60;
        
        if (newHeight>700)
            this.arenaHeight = newHeight;
        else
            this.arenaHeight = 700;
                    
        racers = new ArrayList<>();
        racersImages = new ImageIcon[maxRacers];
        
        try {
            switch (chosenArena) {
                case "AerialArena":
                    arena = builder.buildArena("game.arenas.air.AerialArena",  arenaLength, maxRacers);
                    break;
                case "LandArena":
                    arena = builder.buildArena("game.arenas.land.LandArena",  arenaLength, maxRacers);
                    break;
                case "NavalArena":
                    arena = builder.buildArena("game.arenas.naval.NavalArena",  arenaLength, maxRacers);
                    break;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println(ex);
        } 
        raceFrame.updateFrame();
    }
    
    
    
    public void addRacer(String racerType, String color, String name, double maxSpeed, double acceleration){
        EnumContainer.Color col = null;
        switch (color) {
            case "Red":
                col = EnumContainer.Color.RED;
                break;
            case "Black":
                col = EnumContainer.Color.BLACK;
                break;
            case "Green":
                col = EnumContainer.Color.GREEN;
                break;
            case "Blue":
                col = EnumContainer.Color.BLUE;
                break;
            case "Yellow":
                col = EnumContainer.Color.YELLOW;
                break;
        }
                    
        String racerClass = null;
        switch (racerType) {
            case "Helicopter":
                racerClass = "game.racers.air.Helicopter";
                break;
            case "Airplane":
                racerClass = "game.racers.air.Airplane";
                break;
            case "Car":
                racerClass = "game.racers.land.Car";
                break;
            case "Horse":
                racerClass = "game.racers.land.Horse";
                break;
            case "Bicycle":
                racerClass = "game.racers.land.Bicycle";
                break;
            case "SpeedBoat":
                racerClass = "game.racers.naval.SpeedBoat";
                break;
            case "RowBoat":
                racerClass = "game.racers.naval.RowBoat";
                break;
        }
                    
        try {
            Racer racer = builder.buildRacer(racerClass, name, maxSpeed, acceleration, col);
            arena.addRacer(racer);
            arena.initRace();
            racers.add(racer);

        } catch (RacerTypeException ex) {
            JOptionPane.showMessageDialog(this, "Recer does not fit to arena! Choose another racer.");
            return;
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | RacerLimitException ex) {
            System.out.println(ex);
        } 
                    
        racersImages[racersNumber] = new ImageIcon(new ImageIcon("icons/"+racerType+color+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        racersNumber++;
        raceFrame.updateFrame();
    }
    
    
    
    public void startRace(){
        raceStarted = true;
        try {                    
            new Thread(this).start();
	        arena.startRace();
        } catch (InterruptedException ex) {
	    ex.printStackTrace();
        }
    }
    
    
    
    public void showInfo(){
        if (infoTable != null)
            infoTable.dispose();
                    
        infoTable = new InfoTable(arena,racersNumber); 
    }
    
    
    public void setArenaLength(int arenaLength){
        this.arenaLength = arenaLength;
    }
    
    public int getArenaLength(){
        return arenaLength;
    }
          
    
    public void setArenaHeight(int arenaHeight){
        this.arenaHeight = arenaHeight;
    }
    
    public int getArenaHeight(){
        return arenaHeight;
    }
    
    
    public String getChosenArena(){
        return this.chosenArena;
    }
    
    
    public void setMaxRacers(int maxRacers){
        this.maxRacers = maxRacers;
    }
    
    public int getMaxRacers(){
        return this.maxRacers;
    }
    
    public boolean noArena(){
        return arena == null;
    }
    
    public boolean fullArena(){
        return racersNumber == maxRacers;
    }
    
    
    public boolean noRacers(){
        return racersNumber == 0;
    }
    

    public void setRaceFrame(RaceFrame raceFrame){
        this.raceFrame = raceFrame;
    }
    
    public boolean isRaceStarted(){
        return this.raceStarted;
    }
    
    public boolean isRaceFinished(){
        return this.raceFinished;
    }
    

    
    
    @Override
    public void run() {
        while (arena.hasActiveRacers()){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                raceFrame.updateFrame();
            }catch(Exception e){}
        }
        raceFrame.updateFrame();
        raceFinished = true;
    }


    public Arena getArena() {
        return arena;
    }

    /**
     * This method shows the cloning frame from which you can clone a racer
     */
    public void showCloneRacer() {
        clonePanel = new CloningFrame(this);
    }

    /**
     *
     * @param selectedItem
     */
    public void quickBuildArena(String selectedItem) {
        if(arenaFactory == null) arenaFactory = new ArenaFactory();
        raceStarted = raceFinished = false;
        racersNumber = 0;
        this.chosenArena = selectedItem;


        racers = new ArrayList<>();
        racersImages = new ImageIcon[maxRacers];
        switch (selectedItem){
            case "AerialArena": arena = arenaFactory.makeArena("Air"); break;
            case "LandArena": arena = arenaFactory.makeArena("Land"); break;
            case "NavalArena": arena = arenaFactory.makeArena("Navy"); break;
        }
        raceFrame.updateFrame();
    }

    public void buildDefault() {
        raceStarted = raceFinished = false;
        if(defaultBuilder == null) defaultBuilder = new CarRaceBuilder();
        arena = defaultBuilder.buildDefault(maxRacers);
        this.chosenArena = "LandArena";


        racersImages = new ImageIcon[maxRacers];
        racers = new ArrayList<>();
        for(Racer r : arena.getActiveRacers())
            racers.add(r);
        arena.initRace();
        racersNumber = racers.size();
        for(int i = 0; i < racers.size(); i++){
            Racer r = racers.get(i);
            racersImages[i] = new ImageIcon(new ImageIcon("icons/"+r.className()+r.getColor().toString()+".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        }
        int newHeight = (maxRacers+1)*60;

        setArenaLength((int)arena.getLength());
        if (newHeight>700)
            this.arenaHeight = newHeight;
        else
            this.arenaHeight = 700;
        raceFrame.updateFrame();
    }
}
