package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public final class RaceFrame extends JFrame {
    private ArenaPanel arenaPanel = null;
    private static final long serialVersionUID = 1L;
    
    
    public RaceFrame() {
        super("Race");     
        updateFrame();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    

    public JPanel getMyContentPane(){ 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); 
        if (arenaPanel==null) {
            arenaPanel = new ArenaPanel();
            arenaPanel.setRaceFrame(this); 
        }
        else arenaPanel.initArena();
        mainPanel.add(arenaPanel,BorderLayout.WEST); ///
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.CENTER);
        mainPanel.add(new ControlsPanel(arenaPanel),BorderLayout.EAST);
        return mainPanel;
    }
    
    
    
    public void updateFrame(){
        this.setContentPane(getMyContentPane());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true); 
    }
    
    	public static void main(String[] args) {
            RaceFrame raceFrame = new RaceFrame();
	}

   
}
