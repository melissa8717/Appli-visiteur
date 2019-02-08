package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.AgendaC;


public class ConsultationUtilisateur extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationUtilisateur() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Utilisateur");
		
		JButton equipe = new JButton("Créer une équipe");
		equipe.addActionListener(new ActionListener() {


		      public void actionPerformed(ActionEvent ae) {
		    	  Popup equipe = new Popup("Créer une équipe", 1200,700);
		    	  TitreSecondaire titrePopup = new TitreSecondaire("Créer une équipe");
		    	  JPanel ajoutEquipe = new JPanel();
		    	  JLabel delegueLabel = new JLabel("Délégués :");
		    	  ajoutEquipe.setPreferredSize(new Dimension(1200,150));
		    	  
		    	  List<List> ListDelegue = (List<List>) AgendaC.selectDelegue();
		          List<String> ListNomDelegue=new ArrayList<String>();

		  		
		  		for(int i=0; i<ListDelegue.size(); i++) {
		  			ListNomDelegue.add((String) ListDelegue.get(i).get(0));
		  			 String nomOnly= (String) ListDelegue.get(i).get(0);
		  			 JButton nomLabel = new JButton();
		  	         nomLabel.setText(nomOnly);


		  		}
		  		
		        String[] nomDelegue=ListNomDelegue.toArray(new String[0]);
		  		JComboBox<?> delegues = new JComboBox<Object>(nomDelegue);
		  		Object nomDelegueSelected = delegues.getSelectedItem();
		  		String nomDelegueStr = String.valueOf(nomDelegueSelected);

		    	JLabel visiteurLabel = new JLabel("Visiteurs :");

		  		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
		        List<String> ListNomVisiteur=new ArrayList<String>();

		  		
		  		for(int i=0; i<ListVisiteur.size(); i++) {
		  			ListNomVisiteur.add((String) ListVisiteur.get(i).get(0));
		  			 String nomOnly= (String) ListVisiteur.get(i).get(0);
		  			 JButton nomLabel = new JButton();
		  	         nomLabel.setText(nomOnly);


		  		}
		  		
		          String[] nomVisiteur=ListNomVisiteur.toArray(new String[0]);


		          
		  		JComboBox<?> visiteurs = new JComboBox<Object>(nomVisiteur);
		  		Object nomVisiteurSelected = visiteurs.getSelectedItem();
		  		String nomVisiteurStr = String.valueOf(nomVisiteurSelected);
		  		JLabel nomEquipe = new JLabel("Nom/Numéro équipe");
		  		JTextArea nomEquipeArea = new JTextArea(2,30);
		  		JButton ajouter = new JButton("Ajouter un visiteur");
		  		
		  		JPanel equipeEntier = new JPanel();
		  		equipeEntier.setPreferredSize(new Dimension(1200,400));
		  		equipeEntier.setBackground(Color.white);
		    	  equipe.add(titrePopup);
		    	  equipe.add(ajoutEquipe);
		    	  ajoutEquipe.add(delegueLabel);
		    	  ajoutEquipe.add(delegues);
		    	  ajoutEquipe.add(nomEquipe);
		    	  ajoutEquipe.add(nomEquipeArea);
		    	  ajoutEquipe.add(visiteurLabel);
		    	  ajoutEquipe.add(visiteurs);
		    	  ajoutEquipe.add(ajouter);
		    	  equipe.add(equipeEntier);
		    	
		      }
		});
		
		
		this.add(bienvenue);
		this.add(equipe);
	}
}
