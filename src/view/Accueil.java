package view;

import javax.swing.JPanel;


public class Accueil extends JPanel {
	
	public Accueil() {		
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue");
		
		this.add(bienvenue);
		// this.add(Conteneur);
	}

	public Accueil(String nom) {
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue " + nom);
		
		this.add(bienvenue);
		// this.add(Conteneur);
	}
}