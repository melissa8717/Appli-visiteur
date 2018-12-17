package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.CnxBDD;
import model.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;


public class Connexion extends JPanel {
	/**
	 * 	Explication de cette vue
	 */
	private static final long serialVersionUID = 1L;
	public Connexion(User User, Fenetre f, Boolean error) {
		int largeurConteneur = 600;

		// Création des espacements
		JPanel[] espacement = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
		for(int $i = 0; $i < 5; $i++) {
			espacement[$i].setPreferredSize(new Dimension(largeurConteneur, 75));
			espacement[$i].setOpaque(false);
		}
		espacement[0].setPreferredSize(new Dimension(largeurConteneur, 150));
		
		// Création des differents éléments de la vue
		TitrePrincipale titrePrincipale = new TitrePrincipale("Connexion");
		Paragraphe paragrapheId = new Paragraphe("Identifiant : ");
		JTextFieldModif textFieldId = new JTextFieldModif(10, 12);
		Paragraphe paragrapheMdp = new Paragraphe("Mot de passe : ");
		JPasswordField textFieldMdp = new JPasswordField();

		// Création du bouton "Valider" ainsi que des EventListener permettant de detecter la touche Entrer pour simuler le clique sur le bouton "Valider"
		JButton boutonValider = new JButton("Connexion");
		boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
					f.refreshConnexion(User.isConnected());
				}
				catch(Exception exception) {
					System.out.println(exception);
				}
			}
		});
		textFieldId.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
						f.refreshConnexion(User.isConnected());
					}
					catch(Exception exception) {
						System.out.println(exception);
					}
				}
			}
		});
		textFieldMdp.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						CnxBDD.connect(textFieldId.getText(), textFieldMdp.getText(), User);
						f.refreshConnexion(User.isConnected());
					}
					catch(Exception exception) {
						System.out.println(exception);
					}
				}
			}
		});
		
		JPanel ligneUne =  new JPanel();
		ligneUne.setLayout(new BorderLayout());
		ligneUne.setOpaque(false);
		ligneUne.setPreferredSize(new Dimension(largeurConteneur, 150));
		
		//ligneUne.add(titrePrincipale);
		
		JPanel messageError = new AlertError("Nom de compte ou mot de passe incorrect !");
		
		JPanel ligneDeux = new JPanel();
		ligneDeux.setLayout(new BorderLayout());
		ligneDeux.setOpaque(false);
		ligneDeux.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneDeux.add(paragrapheId);
		ligneDeux.add(textFieldId);
		
		JPanel ligneTrois = new JPanel();
		ligneTrois.setLayout(new BorderLayout());
		ligneTrois.setOpaque(false);
		ligneTrois.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneTrois.add(paragrapheMdp);
		ligneTrois.add(textFieldMdp);
		
		JPanel ligneQuatre = new JPanel();
		ligneQuatre.setLayout(new BorderLayout());
		ligneQuatre.setOpaque(false);
		ligneQuatre.setPreferredSize(new Dimension(largeurConteneur, 75));
		
		ligneQuatre.add(boutonValider);
		
		// Ajout de tout les éléments à la vue
		this.add(ligneUne);
		this.add(espacement[0]);
		if(error == true) {
			this.add(messageError);
		}
		this.add(ligneDeux);
		this.add(espacement[1]);
		this.add(ligneTrois);
		this.add(espacement[2]);
		this.add(ligneQuatre);
	}

	// public boolean isConnected(String login, String mdp) {
	// 	if((login instanceof String) && (mdp instanceof String)) {
	// 		try {
	// 			User u = new User(login, mdp);
	// 			return true;
	// 		}
	// 		catch(Exception excep) {
	// 			System.out.println(excep);
	// 			return false;
	// 		}
	// 	}
	// 	else {
	// 		return false;
	// 	}
	// }
}
