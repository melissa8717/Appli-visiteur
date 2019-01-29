package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class launchview extends JFrame {
	public launchview() {
	    // Definition du style de la fenetre
	    setBounds(50, 50, 1000, 500);
	    setMinimumSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        add(new CreationMessagerie(), BorderLayout.CENTER);
        
        setVisible(true);  
	}	
	
	public static void main(String[] args) {
		launchview l = new launchview();
	}

}
