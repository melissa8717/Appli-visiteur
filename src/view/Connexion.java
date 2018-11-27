package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Connexion {

	public Connexion() {
		Fenetre fenetre = new Fenetre();
	
		Conteneur conteneur = new Conteneur();
		
		TitrePrincipale titrePrincipale = new TitrePrincipale("Connexion");
		
		Paragraphe paragrapheId = new Paragraphe("Identifiant : ");
		
		JTextField textFieldId = new JTextField(10);
		textFieldId.setText("jdebelle");
		
		Paragraphe paragrapheMdp = new Paragraphe("Mot de passe : ");
		
		JTextField textFieldMdp = new JTextField(10);
		textFieldMdp.setText("nvwqq");
		
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
