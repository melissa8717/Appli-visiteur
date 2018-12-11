package view;

import javax.swing.JPanel;

public class ConsultationPowerPoint extends JPanel {
	public ConsultationPowerPoint() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Power Point");
		TitreTertiare titre3 = new TitreTertiare("A faire");
		
		this.add(bienvenue);
		this.add(titre3);	
	}
}
