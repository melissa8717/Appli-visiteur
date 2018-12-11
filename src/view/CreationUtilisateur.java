package view;

import javax.swing.JPanel;

public class CreationUtilisateur extends JPanel {
	public CreationUtilisateur() {
		TitrePrincipale bienvenue = new TitrePrincipale("Ajouter Utilisateur");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
