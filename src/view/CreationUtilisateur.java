package view;

import javax.swing.JPanel;


public class CreationUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationUtilisateur() {
		TitrePrincipale bienvenue = new TitrePrincipale("Ajouter Utilisateur");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
