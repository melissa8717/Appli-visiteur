package view;

import javax.swing.JPanel;

public class Agenda extends JPanel {
	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
