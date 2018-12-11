package view;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreationPowerPoint extends JPanel {
	public CreationPowerPoint() {
		Conteneur conteneurTitre = new Conteneur();
		Conteneur conteneurPowerpointUI = new Conteneur();
		conteneurPowerpointUI.setPreferredSize(new Dimension(1000, 500));
		
		JPanel title_panel =  new JPanel();
		JPanel panel2 =  new JPanel();

		// Liste des dispositions
		JPanel dispositions = new JPanel();
		dispositions.setPreferredSize(new Dimension(1000, 50));

		// Liste des mdicaments
		JPanel liste_medicaments = new JPanel();
		liste_medicaments.setPreferredSize(new Dimension(250, 300));

		// Powerpoint builder
		JPanel powerpoint_builder = new JPanel();
		powerpoint_builder.setPreferredSize(new Dimension(650, 300));

		JPanel panel3 =  new JPanel(new GridLayout());

		JButton buttonGenerate = new JButton("Générer un PowerPoint");

		buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Debut generation PowerPoint");
			} 
		}); 
		
		TitrePrincipale title = new TitrePrincipale("PowerPoint Generator");
		title_panel.setOpaque(false);
		title_panel.add(title);

		JLabel disposition_label = new JLabel("Disposition");
		disposition_label.setOpaque(false);
		dispositions.add(disposition_label);
		
		Checkbox disposition_1 = new Checkbox("1", null, true);
		dispositions.add(disposition_1);

		Checkbox disposition_2 = new Checkbox("2");
		dispositions.add(disposition_2);

		Checkbox disposition_3 = new Checkbox("3");
		dispositions.add(disposition_3);

		Checkbox disposition_4 = new Checkbox("4");
		dispositions.add(disposition_4);

		Character[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		JComboBox<?> liste = new JComboBox<Character>(alphabet);

		JLabel label_medicament_selectionne = new JLabel();
		
		liste.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String x = String.valueOf(liste.getSelectedItem());
				JFrame popup = new JFrame("popup");
				Conteneur conteneur_popup = new Conteneur();
				List<String> medicaments_list = new ArrayList<String>();
				for (int i = 0; i < 10; i++) {
					medicaments_list.add(x + "oliprane " + i);
				}
				JComboBox<?> liste_medicaments = new JComboBox<String>();
				liste_medicaments.setModel(new DefaultComboBoxModel(medicaments_list.toArray()));

				liste_medicaments.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						String medicament_selectionne = String.valueOf(liste_medicaments.getSelectedItem());
						label_medicament_selectionne.setText(medicament_selectionne);
					}
				});
				conteneur_popup.add(liste_medicaments);
				popup.add(conteneur_popup);
			}
		});

		JLabel nom = new JLabel("Inspecteur Gadget");
		panel2.setOpaque(false);
		panel2.add(nom); panel2.add(buttonGenerate);
		
		panel3.setOpaque(false);
		panel3.add(liste);
		
		/* Ajout titre */  
		conteneurTitre.add(title_panel);
		liste_medicaments.add(liste);
		liste_medicaments.add(label_medicament_selectionne);
		conteneurPowerpointUI.add(dispositions);
		conteneurPowerpointUI.add(liste_medicaments);
		conteneurPowerpointUI.add(powerpoint_builder);
		conteneurPowerpointUI.add(panel2);
		conteneurPowerpointUI.add(panel3);
		this.add(conteneurTitre);
		this.add(conteneurPowerpointUI);
	}
}
