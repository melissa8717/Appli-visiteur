package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ConsultationMedecin extends JPanel {
	/**
	 * 	Explication de cette classe
	 */
	private static final long serialVersionUID = 1L;

	public ConsultationMedecin() {
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Medecins");
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		List<List> List_Med = controller.Medecin.listMedecin();
		
		String idMed = null;
		String idSpe = null;
		String nomMed = null;
		String prenom = null;
		String adresse = null;
		String ville = null;
		String cp = null;
		String tel = null;
		String idType = null;
		String nomSpe = null;
		this.add(bienvenue);

		
		for (int i =0; i<List_Med.size(); i++) {
			idMed = (String) List_Med.get(i).get(0);
			final String idMedU = idMed;
			idSpe = (String) List_Med.get(i).get(1);
			nomMed = (String) List_Med.get(i).get(2);
			prenom = (String) List_Med.get(i).get(3);
			adresse = (String) List_Med.get(i).get(4);
			ville = (String) List_Med.get(i).get(5);
			cp = (String) List_Med.get(i).get(6);
			tel = (String) List_Med.get(i).get(7);
		
		JPanel contenu = new JPanel();
		contenu.setBackground(Color.white);
		contenu.setPreferredSize(new Dimension(2000,215));

		JPanel nomMedecin = new JPanel();
		JLabel nomMedecinLabel = new JLabel("Nom du medecin :");
		JTextArea nomMedecinArea = new JTextArea(1,15);
		nomMedecinArea.setText(nomMed);
		nomMedecin.setPreferredSize(new Dimension(2000,50));
		nomMedecin.setFont(font);
		JLabel prenomMedecinLabel = new JLabel("Prénom du medecin :");
		JTextArea prenomMedecinArea = new JTextArea(1,15);
		prenomMedecinArea.setText(prenom);

		
		List<List> List_Spe = controller.Medecin.listTypeMedecin(idSpe);
		for(int y =0; y<List_Spe.size(); y++) {
			idType = (String) List_Spe.get(y).get(0);
			nomSpe = (String) List_Spe.get(y).get(1);

		}
		
		JLabel speMedecinLabel = new JLabel("Spécialité du medecin :");
		JLabel speMedecinArea = new JLabel(nomSpe);

		
		JPanel adresseMedecin = new JPanel();
		JLabel adresseMedecinLabel = new JLabel("Adresse du medecin :");
		JTextArea adresseMedecinArea = new JTextArea(2,15);	
		adresseMedecinArea.setText(adresse);
		adresseMedecin.setPreferredSize(new Dimension(2000,50));
		adresseMedecin.setFont(font);
		JLabel villeMedecinLabel = new JLabel("Ville du medecin :");
		JTextArea villeMedecinArea = new JTextArea(1,15);
		villeMedecinArea.setText(ville);


		JLabel cpMedecinLabel = new JLabel("Code postal du medecin :");
		JTextArea cpMedecinArea = new JTextArea(1,15);
		cpMedecinArea.setText(cp);


		
		JPanel telMedecin = new JPanel();
		JLabel telMedecinLabel = new JLabel("Téléphone du medecin :");
		JTextArea telMedecinArea = new JTextArea(1,15);
		telMedecinArea.setText(tel);

		telMedecin.setPreferredSize(new Dimension(2000,50));
		telMedecin.setFont(font);
		
		JPanel modifier = new JPanel();
		JButton modifierButton = new JButton("Modifier");
		
		modifierButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				String adresseU = adresseMedecinArea.getText();
				String cpU = cpMedecinArea.getText();
				String prenomU = prenomMedecinArea.getText();
				String nomMedecinU = nomMedecinArea.getText();
				String telU = telMedecinArea.getText();
				String villeU = villeMedecinArea.getText();

				
				controller.Medecin.updateMedecin(idMedU, nomMedecinU, prenomU, adresseU, villeU, cpU, telU);

				
			}
		});
		
		this.add(contenu);
		contenu.add(nomMedecin);
		nomMedecin.add(nomMedecinLabel);
		nomMedecin.add(nomMedecinArea);
		nomMedecin.add(prenomMedecinLabel);
		nomMedecin.add(prenomMedecinArea);
		nomMedecin.add(speMedecinLabel);
		nomMedecin.add(speMedecinArea);
		contenu.add(adresseMedecin);
		adresseMedecin.add(adresseMedecinLabel);
		adresseMedecin.add(adresseMedecinArea);
		adresseMedecin.add(villeMedecinLabel);
		adresseMedecin.add(villeMedecinArea);
		adresseMedecin.add(cpMedecinLabel);
		adresseMedecin.add(cpMedecinArea);
		contenu.add(telMedecin);
		telMedecin.add(telMedecinLabel);
		telMedecin.add(telMedecinArea);
		contenu.add(modifier);
		modifier.add(modifierButton);
		}
		

	}
}
