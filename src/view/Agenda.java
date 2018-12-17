package view;

import javax.swing.JPanel;


public class Agenda extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		// Il ne reste qu'a importer la vue de l'agenda ici (view agenda)
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
