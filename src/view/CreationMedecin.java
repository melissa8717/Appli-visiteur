package view;

import javax.swing.JPanel;

public class CreationMedecin extends JPanel {
	public CreationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Creation de medecins");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
