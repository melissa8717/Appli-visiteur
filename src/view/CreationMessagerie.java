package view;

import javax.swing.JPanel;


public class CreationMessagerie extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreationMessagerie() {
		TitrePrincipale bienvenue = new TitrePrincipale("Creation Message");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
