package view;

import javax.swing.JPanel;


public class CreationMedecin extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Creation de medecins");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
