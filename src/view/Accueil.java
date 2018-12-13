package view;

import java.lang.reflect.Array;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Accueil extends JPanel {
	public Accueil() {
		Conteneur Conteneur = new Conteneur();
		
		TitrePrincipale bienvenue = new TitrePrincipale("Bienvenue Copain Suisse");
		
		this.add(bienvenue);
		
		// this.add(Conteneur);
	}
}