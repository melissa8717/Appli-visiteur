package view;

import javax.swing.JPanel;

public class ConsultationMedecin extends JPanel {
	public ConsultationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Medecin");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
