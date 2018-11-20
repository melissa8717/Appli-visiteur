package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Connexion {

	public Connexion() {
		Fenetre fenetre = new Fenetre();
	
		Conteneur conteneur = new Conteneur();
		
		TitrePrincipale titrePrincipale = new TitrePrincipale("Connexion");
		
		Paragraphe paragrapheId = new Paragraphe("Identifiant : ");
		
		JTextField textFieldId = new JTextField(10);
		
		Paragraphe paragrapheMdp = new Paragraphe("Mot de passe : ");
		
		JTextField textFieldMdp = new JTextField(10);
		
		JButton boutonValider = new JButton("Connexion");
		
		
		JPanel ligneUne =  new JPanel();
		ligneUne.setOpaque(false);
		
		ligneUne.add(titrePrincipale);
		
		
		JPanel ligneDeux = new JPanel();
		ligneDeux.setOpaque(false);
		
		ligneDeux.add(paragrapheId);
		ligneDeux.add(textFieldId);
		
		
		JPanel ligneTrois = new JPanel();
		ligneTrois.setOpaque(false);
		
		ligneTrois.add(paragrapheMdp);
		ligneTrois.add(textFieldMdp);
		
		
		JPanel ligneQuatre = new JPanel();
		ligneQuatre.setOpaque(false);
		
		ligneQuatre.add(boutonValider);
		
		conteneur.add(ligneUne);
		conteneur.add(ligneDeux);
		conteneur.add(ligneTrois);
		conteneur.add(ligneQuatre);
		
		fenetre.add(conteneur);
		
	}
}
