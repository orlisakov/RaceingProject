package gui;


import factory.RacingClassesFinder;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ControlsPanel extends JPanel implements ActionListener{
    private final JTextField tfArenaLength;
    private final JTextField tfMaxRacers;
    private final JTextField tfRacerName;
    private final JTextField tfMaxSpeed;
    private final JTextField tfAcceleration;
    private final JComboBox<String> cmbArenas;
    private final JComboBox<String> cmbRacers;
    private final JComboBox<String> colors;

    private ArenaPanel arenaPanel = null;
 
    
    
    public ControlsPanel(ArenaPanel arenaPanel) {
        this.arenaPanel = arenaPanel;
        setLayout(null);
        setPreferredSize(new Dimension(140,arenaPanel.getArenaHeight()));

        
        cmbArenas = new JComboBox<>();
        int i=0;
	for (String string : RacingClassesFinder.getInstance().getArenasNamesList()) {
		cmbArenas.addItem(string);
                if (i==0) cmbArenas.setSelectedItem(string);
                i++;
	}
        
        if (arenaPanel.getChosenArena() != null)
            cmbArenas.setSelectedItem(arenaPanel.getChosenArena());
        
        //controlsPanel.setAlignmentX(0.0f);
        JLabel l1 = new JLabel("Choose arena:");
        add(l1);
        l1.setLocation(10,20);
        l1.setSize(100, 10);
        add(cmbArenas);
        cmbArenas.setLocation(10,40);
        cmbArenas.setSize(100,20);
        
        JLabel l2 = new JLabel("Arena length:");
        l2.setLocation(10,75);
        l2.setSize(100, 10);
        add(l2);
        
        tfArenaLength = new JTextField(""+arenaPanel.getArenaLength());
        tfArenaLength.setLocation(10,95);
        tfArenaLength.setSize(100, 25);
        add(tfArenaLength);
        
        JLabel l3 = new JLabel("Max racers number:");
        l3.setLocation(10,135);
        l3.setSize(150, 10);
        add(l3);
        
        tfMaxRacers = new JTextField(""+arenaPanel.getMaxRacers());
        tfMaxRacers.setLocation(10,155);
        tfMaxRacers.setSize(100, 25);
        add(tfMaxRacers);

        JButton buildDefault = new JButton("Default");
        buildDefault.setLocation(10,175);
        buildDefault.setSize(100, 20);
        buildDefault.addActionListener(this);
        add(buildDefault);

        JButton buildArenaBut = new JButton("Build arena");
        buildArenaBut.setLocation(10,200);
        buildArenaBut.setSize(100, 20);
        buildArenaBut.addActionListener(this);
        add(buildArenaBut);

        JButton quickBuild = new JButton("Quick build");
        quickBuild.setLocation(10,220);
        quickBuild.setSize(100, 20);
        quickBuild.addActionListener(this);
        add(quickBuild);
        
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setLocation(0,260);
        sep.setSize(150, 10);
        add(sep);
        
        cmbRacers = new JComboBox<>();
	for (String string : RacingClassesFinder.getInstance().getRacersNamesList()) {
		cmbRacers.addItem(string);
	}
        JLabel l4 = new JLabel("Choose racer:");
        add(l4);
        l4.setLocation(10,260);
        l4.setSize(100, 10);
        
        add(cmbRacers);
        cmbRacers.setLocation(10,280);
        cmbRacers.setSize(100,20);
        
        JLabel l5 = new JLabel("Choose color:");
        add(l5);
        l5.setLocation(10,315);
        l5.setSize(100, 10);
        
        colors = new JComboBox<>();
        colors.addItem("Black");
        colors.addItem("Green");
        colors.addItem("Blue");
        colors.addItem("Red");
        colors.addItem("Yellow");
        add(colors);
        colors.setLocation(10,335);
        colors.setSize(100,20);
        
        JLabel l6 = new JLabel("Racer name:");
        l6.setLocation(10,370);
        l6.setSize(150, 10);
        add(l6);
        
        tfRacerName = new JTextField("");
        tfRacerName.setLocation(10,390);
        tfRacerName.setSize(100, 25);
        add(tfRacerName);
        
        JLabel l7 = new JLabel("Max speed:");
        l7.setLocation(10,425);
        l7.setSize(150, 14);
        add(l7);
        
        tfMaxSpeed = new JTextField("");
        tfMaxSpeed.setLocation(10,445);
        tfMaxSpeed.setSize(100, 25);
        add(tfMaxSpeed);
        
        JLabel l8 = new JLabel("Acceleration:");
        l8.setLocation(10,485);
        l8.setSize(150, 10);
        add(l8);
        
        tfAcceleration = new JTextField("");
        tfAcceleration.setLocation(10,505);
        tfAcceleration.setSize(100, 25);
        add(tfAcceleration);
        
        JButton addRacerBut = new JButton("Add racer");
        addRacerBut.setLocation(10,535);
        addRacerBut.setSize(100, 30);
        addRacerBut.addActionListener(this);
        add(addRacerBut);

        JButton cloneRacerBut = new JButton("Clone");
        cloneRacerBut.setLocation(10,565);
        cloneRacerBut.setSize(100, 20);
        cloneRacerBut.addActionListener(this);
        add(cloneRacerBut);
        
        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setLocation(0,590);
        sep2.setSize(150, 10);
        add(sep2);
        
        JButton startRaceBut = new JButton("Start race");
        startRaceBut.setLocation(10,605);
        startRaceBut.setSize(100, 30);
        startRaceBut.addActionListener(this);
        add(startRaceBut);
        
        JButton printInfoBut = new JButton("Show info");
        printInfoBut.setLocation(10,650);
        printInfoBut.setSize(100, 30);
        printInfoBut.addActionListener(this);
        add(printInfoBut);


    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
               
		case "Build arena":  
                    int arenaLength = arenaPanel.getArenaLength();
                    int maxRacers = arenaPanel.getMaxRacers();
                    if (arenaPanel.isRaceStarted() && !arenaPanel.isRaceFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "Race started! Please wait.");
                        return;
                    }
                    try{
                        arenaLength = Integer.parseInt(tfArenaLength.getText());
                        arenaPanel.setArenaLength(arenaLength);
                        maxRacers = Integer.parseInt(tfMaxRacers.getText());
                        arenaPanel.setMaxRacers(maxRacers); 
                        if (arenaLength<100 || arenaLength>3000 || maxRacers<=0 || maxRacers > 20) throw new Exception();
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }                  
                                                        
                    arenaPanel.buildArena((String)cmbArenas.getSelectedItem());          
                    break;                 
                    
                case "Add racer":
                    
                    if (arenaPanel.isRaceFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "Race finished! Please build a new arena.");
                        return;
                    }
                    if (arenaPanel.isRaceStarted()){
                        JOptionPane.showMessageDialog(arenaPanel, "Race started! No racers can be added.");
                        return;
                    }
                    if (arenaPanel.noArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first!");
                        return;
                    }                    
                    if (arenaPanel.fullArena()){
                        JOptionPane.showMessageDialog(arenaPanel, "No more racers can be added!");
                        return;
                    }
                    String name;
                    double maxSpeed;
                    double acceleration;    
                    try {
                        name = tfRacerName.getText();
                        maxSpeed = Double.parseDouble(tfMaxSpeed.getText());
                        acceleration = Double.parseDouble(tfAcceleration.getText());
                        if (name.isEmpty() || maxSpeed <=0 || acceleration <=0) throw new Exception();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(arenaPanel, "Invalid input values! Please try again.");
                        return;
                    }
                    
                    String racerType = (String)cmbRacers.getSelectedItem();
                    String color = (String)colors.getSelectedItem();

                    arenaPanel.addRacer(racerType, color, name, maxSpeed, acceleration);       
                    break;
                    
                case "Start race":
                    
                    if (arenaPanel.noArena() || arenaPanel.noRacers()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first and add racers!");
                        return;
                    } 
                    if (arenaPanel.isRaceFinished()){
                        JOptionPane.showMessageDialog(arenaPanel, "Race finished! Please build a new arena and add racers.");
                        return;
                    }
                    if (arenaPanel.isRaceStarted()){
                        JOptionPane.showMessageDialog(arenaPanel, "Race already started!");
                        return;
                    }                  
                    
                    arenaPanel.startRace();
                    break;
                    
                case "Show info":
                    
                    if (arenaPanel.noArena() || arenaPanel.noRacers()){
                        JOptionPane.showMessageDialog(arenaPanel, "Please build arena first and add racers!");
                        return;
                    } 
                    
                    arenaPanel.showInfo();
                    break;

            case "Clone":
//                if (arenaPanel.noArena() || arenaPanel.noRacers()){
//                    JOptionPane.showMessageDialog(arenaPanel, "Please build arena first and add racers!");
//                    return;
//                }
                arenaPanel.showCloneRacer();
                break;

            case "Quick build":
                if (arenaPanel.isRaceStarted() && !arenaPanel.isRaceFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Race started! Please wait.");
                    return;
                }

                arenaPanel.quickBuildArena((String)cmbArenas.getSelectedItem());
                break;

            case "Default":
                if (arenaPanel.isRaceStarted() && !arenaPanel.isRaceFinished()){
                    JOptionPane.showMessageDialog(arenaPanel, "Race started! Please wait.");
                    return;
                }

                maxRacers = Integer.parseInt(tfMaxRacers.getText());
                arenaPanel.setMaxRacers(maxRacers);

                arenaPanel.buildDefault();

        }
    }

}
