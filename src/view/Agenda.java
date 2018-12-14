package view;

import javax.swing.JPanel;

public class Agenda extends JPanel {
	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		// il ne reste qu'a importer la vue de lagenda ici (view agenda)
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
