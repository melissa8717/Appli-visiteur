package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
/*
import appliVisiteur_interfaceGraphique.Conteneur;
import appliVisiteur_interfaceGraphique.Fenetre;
import appliVisiteur_interfaceGraphique.JTextFieldModif;
import appliVisiteur_interfaceGraphique.Paragraphe;
import appliVisiteur_interfaceGraphique.TitrePrincipale;*/
import model.User;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Connexion {

	public Connexion() {
Fenetre fenetre = new Fenetre();
		
		int largeurConteneur = 600;
		
		Conteneur conteneur = new Conteneur();
		conteneur.setPreferredSize(new Dimension(largeurConteneur,375));
		
		TitrePrincipale titrePrincipale = new TitrePrincipale("Connexion");
		
		Paragraphe paragrapheId = new Paragraphe("Identifiant : ");
		
		JTextFieldModif textFieldId = new JTextFieldModif(10, 12);
		
		Paragraphe paragrapheMdp = new Paragraphe("Mot de passe : ");
		
		JTextFieldModif textFieldMdp = new JTextFieldModif(10, 12);
		
		JButton boutonValider = new JButton("Connexion");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String login = textFieldId.getText();
					String mdp = textFieldMdp.getText();
					if(isConnected(login, mdp)) {
						System.out.println("La méthode isConnected retourne TRUE");
					}
					else {
						System.out.println("La méthode isConnected retourne FALSE");
					}
				}
				catch(Exception exception) {
					System.out.println(exception);
				}
				
			}
		});
		
		JPanel ligneUne =  new JPanel();
		ligneUne.setOpaque(false);
		ligneUne.setPreferredSize(new Dimension(largeurConteneur, 150));
		
		ligneUne.add(titrePrincipale);
		
		
		JPanel ligneDeux = new JPanel();
		ligneDeux.setOpaque(false);
		ligneDeux.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneDeux.add(paragrapheId);
		ligneDeux.add(textFieldId);
		
		
		JPanel ligneTrois = new JPanel();
		ligneTrois.setOpaque(false);
		ligneTrois.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneTrois.add(paragrapheMdp);
		ligneTrois.add(textFieldMdp);
		
		
		JPanel ligneQuatre = new JPanel();
		ligneQuatre.setOpaque(false);
		ligneQuatre.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneQuatre.add(boutonValider);
		
		
		conteneur.add(ligneUne);
		conteneur.add(ligneDeux);
		conteneur.add(ligneTrois);
		conteneur.add(ligneQuatre);
		fenetre.add(conteneur);
		
		
		
		 
	}
	public boolean isConnected(String login, String mdp) {
		if((login instanceof String) && (mdp instanceof String)) {
			System.out.println("Aïe aïe aïe t'es passé");
			
			try {
				User u = new User(login, mdp);
				return true;
			}
			catch(Exception excep) {
				System.out.println(excep);
				return false;
			}
			
		}
		else {
			System.out.println("Aïe aïe aïe t'es pas passé");
			return false;
		}
	}
}
