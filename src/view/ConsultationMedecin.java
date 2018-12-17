package view;

import javax.swing.JPanel;


public class ConsultationMedecin extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Medecin");
		TitreTertiaire titre3 = new TitreTertiaire("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
