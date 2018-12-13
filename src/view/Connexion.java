package view;

import javax.swing.JPanel;

import controller.CnxBDDLocalhost;


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


public class Connexion extends JPanel {
	
	/*
	 * 
	 * 
	 * 
	 * vous pouvez lancer Connexion.java via launchView, ceci n'est que temporaire, le temps qu'on code tout comme il faut
	 * 
	 * 
	 * 
	 */
	
	public Connexion() {
		int largeurConteneur = 600;
		
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
					CnxBDDLocalhost.connect(login,mdp);
					
					/*if(controller.connectionControleur.testCredancial(login, mdp)) {
						User currentUser = connectionControleur.setConnection(login, mdp);
						new Fenetre();
						//fenetre.setVisible(false);
						System.out.println("on est connecté ");

					}
					else {
						System.out.println("La méthode isConnected retourne FALSE");
					}*/
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
		
		
		this.add(ligneUne);
		this.add(ligneDeux);
		this.add(ligneTrois);
		this.add(ligneQuatre);
		
		
		
		 
	}
	public boolean isConnected(String login, String mdp) {
		if((login instanceof String) && (mdp instanceof String)) {
			System.out.println("t'es passé");
			
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
			System.out.println("A�e a�e a�e t'es pas pass�");
			return false;
		}
	}
}
