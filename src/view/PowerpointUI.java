package view;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;;

public class PowerpointUI extends JFrame {
	
	public PowerpointUI() {
		Fenetre fenetrePowerpointUI = new Fenetre();
		Conteneur conteneurTitre = new Conteneur();
		Conteneur conteneurPowerpointUI = new Conteneur();
		conteneurPowerpointUI.setPreferredSize(new Dimension(1000, 500));
		
		JPanel title_panel =  new JPanel();
		JPanel panel2 =  new JPanel();

		// Liste des dispositions
		JPanel dispositions = new JPanel();
		dispositions.setPreferredSize(new Dimension(1000, 50));

		// Liste des médicaments
		JPanel liste_medicaments = new JPanel();
		liste_medicaments.setPreferredSize(new Dimension(250, 300));

		// Powerpoint builder
		JPanel powerpoint_builder = new JPanel();
		powerpoint_builder.setPreferredSize(new Dimension(650, 300));

		JPanel panel3 =  new JPanel(new GridLayout());

		JButton buttonGenerate = new JButton("Generer un PowerPoint");

		buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Debut generation PowerPoint");

			}
		}); 
		
		/* Ajout menu */
		Menu menu = new Menu();
		JPanel panel_menu = new JPanel();
		panel_menu.add(menu);
		

		
		
		TitrePrincipale title = new TitrePrincipale("PowerPoint Generator");
		title_panel.setOpaque(false);
		title_panel.add(title);



		JLabel disposition_label = new JLabel("Disposition");
		disposition_label.setOpaque(false);
		dispositions.add(disposition_label);
		
		JRadioButton disposition1 = new JRadioButton("1");
		disposition1.setOpaque(false);
		dispositions.add(disposition1);
		JRadioButton disposition2 = new JRadioButton("2");
		disposition2.setOpaque(false);
		dispositions.add(disposition2);
		JRadioButton disposition3 = new JRadioButton("3");
		disposition3.setOpaque(false);
		dispositions.add(disposition3);
		JRadioButton disposition4 = new JRadioButton("4");
		disposition4.setOpaque(false);
		dispositions.add(disposition4);


		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		JComboBox<?> liste = new JComboBox<Character>(alphabet);




		JLabel nom = new JLabel("Inspecteur Gadget");
		panel2.setOpaque(false);
		panel2.add(nom); panel2.add(buttonGenerate);
		
		// Créer un fichier UIComponents dans lequel on mettre TitrePrincipal, Secondaire, Fenetre etc...
		
		
		// Menu déroulant
		

		Checkbox test = new Checkbox("Generer page Intro / Outro", null, true);
		panel2.add(test);
		
		panel3.setOpaque(false);
		panel3.add(liste);
		
		
		
		
		/* Ajout titre */

		conteneurTitre.add(title_panel);
		liste_medicaments.add(liste);
		conteneurPowerpointUI.add(dispositions);
		conteneurPowerpointUI.add(liste_medicaments);
		conteneurPowerpointUI.add(powerpoint_builder);
		conteneurPowerpointUI.add(panel2);
		conteneurPowerpointUI.add(panel3);
		fenetrePowerpointUI.add(panel_menu);
		fenetrePowerpointUI.add(conteneurTitre);
		fenetrePowerpointUI.add(conteneurPowerpointUI);
		
	}
}
