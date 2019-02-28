package view;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Utilisateur;


public class CreerLireUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public CreerLireUtilisateur() {
		
		List<List> data = controller.Utilisateur.getUtilisateur();
		
		System.out.println(data);
		
		TitrePrincipale bienvenue = new TitrePrincipale("Ajouter Utilisateur");
		
		this.add(bienvenue);
		
		String idUtilisateur;
		String nom;
		String prenom;
		String adresse;
		String ville;
		String codePostal;
		String role;
		String login;
		
		/*JLabel jlabel_idUtilisateur = null;
		JLabel jlabel_nom = null;
		JLabel jlabel_prenom = null;
		JLabel jlabel_adresse = null;
		JLabel jlabel_ville = null;
		JLabel jlabel_codePostal = null;
		JLabel jlabel_role = null;
		JLabel jlabel_login = null;*/
		
		for (int i = 0; i < data.size(); i++) {	
			idUtilisateur = (String) data.get(i).get(0);
			nom = (String) data.get(i).get(1);
			prenom = (String) data.get(i).get(2);
			adresse = (String) data.get(i).get(3);
			ville = (String) data.get(i).get(4);
			codePostal = (String) data.get(i).get(5);
			role = (String) data.get(i).get(7);
			login = (String) data.get(i).get(8);
			
			JLabel jlabel_nom = new JLabel(nom);
			JLabel jlabel_prenom = new JLabel(prenom);
			JLabel jlabel_adresse = new JLabel(adresse);
			JLabel jlabel_ville = new JLabel(ville);
			JLabel jlabel_codePostal = new JLabel(codePostal);
			JLabel jlabel_role = new JLabel(role);
			JLabel jlabel_login = new JLabel(login);
			
			
			this.add(jlabel_nom);
			this.add(jlabel_prenom);
			this.add(jlabel_adresse);
			this.add(jlabel_ville);
			this.add(jlabel_codePostal);
			this.add(jlabel_role);
			this.add(jlabel_login);
		}
		
		
		
	}
}
