package view;

import javax.swing.JPanel;
import java.awt.Color;


public class Conteneur extends JPanel {
	private static final long serialVersionUID = 1L;
	public Conteneur() {
        this.setSize(1500, 1000);
        this.setBackground(new Color(255, 255, 255));
        this.setOpaque(true);
        this.setVisible(true);
    }
}
