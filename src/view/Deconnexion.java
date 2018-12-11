package view;

import javax.swing.JPanel;

public class Deconnexion extends JPanel {
	public Deconnexion() {
		TitrePrincipale bienvenue = new TitrePrincipale("Deconnexion");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
