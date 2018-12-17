package view;

import javax.swing.JPanel;


public class ConsultationUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationUtilisateur() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Utilisateur");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
