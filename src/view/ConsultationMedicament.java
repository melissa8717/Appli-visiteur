package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import oracle.net.aso.a;

public class ConsultationMedicament extends JPanel {
	public  ConsultationMedicament()  {
		Conteneur conteneur = new Conteneur();
		TitrePrincipale bienvenue = new TitrePrincipale("Consultation Medicaments");
		JPanel select = new JPanel();
		//voir le dernier medoc ajouté
		List<List> ListLastMedoc = controller.Medicament.consultationLastMedoc();
		//depot legal, nom, effet secondaire, contre indcation, composition, quantite, unite, type individu, famille, prix, notice
		String depotStr = null;
		String nomMedoc = null;
		String effetStr = null;
		String contreIndStr = null;
		String compoStr = null;
		String qteStr = null;
		String uniteStr = null;
		String typeStr = null;
		String familleStr = null;
		String prixStr = null;
		String noticeStr = null;
		JButton notice = new JButton("Voir la notice");
		JButton modifierMedoc = new JButton("Modifier le médicament");
		JButton suppressionMedoc = new JButton("Supprimer le médicament");

		
		for(int k = 0; k<ListLastMedoc.size();k++) {
		
			String idMedoc = (String) ListLastMedoc.get(k).get(0);
			depotStr = (String) ListLastMedoc.get(k).get(1);
			nomMedoc = (String) ListLastMedoc.get(k).get(2);
			contreIndStr = (String) ListLastMedoc.get(k).get(3);
			compoStr = (String) ListLastMedoc.get(k).get(4);
			qteStr = (String) ListLastMedoc.get(k).get(5);
			uniteStr = (String) ListLastMedoc.get(k).get(6);
			typeStr = (String) ListLastMedoc.get(k).get(7);
			familleStr = (String) ListLastMedoc.get(k).get(8);
			prixStr = (String) ListLastMedoc.get(k).get(9);
			noticeStr = (String) ListLastMedoc.get(k).get(10);
			effetStr = (String) ListLastMedoc.get(k).get(11);
			
			notice.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					Popup noticePopup = new Popup("Notice" , 1500, 900);
					JPanel noticePanel = new JPanel();
					noticePanel.setPreferredSize(new Dimension(1500,1000));
					List<List> ListNote = controller.Medicament.consultationNotice(idMedoc);
					String noticeThis =null;
					for(int n=0;n<ListNote.size();n++) {
						noticeThis = (String) ListNote.get(n).get(1);
					}
					 JTextArea noticeArea = new JTextArea(noticeThis, 50,100);
		             noticePanel.add(noticeArea);
		             JScrollPane scrollPane = new JScrollPane(noticeArea);

		             scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		             scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		             noticePopup.add(scrollPane, BorderLayout.CENTER);

		         

				}
			});
			
	
		
		JPanel all = new JPanel();
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		all.setFont(font);
		all.setBackground(new Color(255, 255, 255));
		all.setPreferredSize(new Dimension(1500,800));
		JLabel last = new JLabel("Dernier médicament ajouté :");
		JPanel lastPanel = new JPanel();
		all.add(lastPanel);
		lastPanel.setPreferredSize(new Dimension(1500,50));
		lastPanel.setFont(font);
		lastPanel.add(last);
		JPanel noticePanel = new JPanel();
		JPanel contreInd = new JPanel();
		JPanel effetPanel = new JPanel();
		noticePanel.setPreferredSize(new Dimension(1500,515));
		noticePanel.setBackground(new Color(255, 255, 255));
		effetPanel.setPreferredSize(new Dimension(1500,75));
		effetPanel.setBackground(new Color(255, 255, 255));
		contreInd.setPreferredSize(new Dimension(1500,215));
		contreInd.setBackground(new Color(255, 255, 255));
	
		JLabel depot = new JLabel("Dépôt légal :");
		JTextArea depotArea = new JTextArea(depotStr);
		JLabel nom = new JLabel("Nom du médicament");
		JTextArea nomArea = new JTextArea(nomMedoc);
		JButton effet = new JButton("Voir les effet indésirables");
		JLabel cIndication = new JLabel("Contre indications");
		JTextArea cindArea = new JTextArea(contreIndStr);
		JLabel composition = new JLabel("Composition :");
		JTextArea compoArea = new JTextArea(compoStr);
		JLabel qte = new JLabel("Quanité :");
		JTextArea qteArea = new JTextArea(qteStr);
		JLabel unite = new JLabel("Unité :");
		JTextArea uniteArea = new JTextArea(uniteStr);
		JLabel typeIndividu = new JLabel("Type individu");
		JTextArea typeIndividuArea = new JTextArea(typeStr);
		JLabel famille = new JLabel("Famille");
		JTextArea familleArea = new JTextArea(familleStr);
		JLabel prix = new JLabel("Prix");
		JTextArea prixArea = new JTextArea(prixStr+" " +"euros");

		effet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				Popup effetPopup = new Popup("Effet secondaires", 1500, 900);
				JPanel effetPanel = new JPanel();
				effetPanel.setPreferredSize(new Dimension(1500,1000));
				List<List> ListEffet= controller.Medicament.consultationEffet(idMedoc);
				String effetThis =null;
				for(int e=0;e<ListEffet.size();e++) {
					effetThis = (String) ListEffet.get(e).get(1);
				}
				JTextArea effetArea = new JTextArea(effetThis, 50,100);
				effetPopup.add(effetPanel);
				effetPanel.setBackground(Color.RED);
				effetPanel.add(effetArea);
		        JScrollPane scrollPane1 = new JScrollPane(effetArea);
		        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		        effetPopup.add(scrollPane1, BorderLayout.CENTER);
			}
		});
		
		modifierMedoc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				String depotU = depotArea.getText();
				String nomU = nomArea.getText();
				String cindU = cindArea.getText();
				String compoU = compoArea.getText();
				String qteU = qteArea.getText();
				String uniteU = uniteArea.getText();
				String typeU =  typeIndividuArea.getText();
				String familleU = familleArea.getText();
				String prixU = prixArea.getText();
				controller.Medicament.updateMedoc(idMedoc, depotU, nomU,  cindU, compoU, qteU, uniteU, typeU, familleU, prixU);
			}
		});
		
		suppressionMedoc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				controller.Medicament.suppressionMedoc(idMedoc);
			}
		});
		JPanel compoPanel = new JPanel();


		all.add(nom);
		all.add(nomArea);
		all.add(depot);
		all.add(depotArea);
		all.add(qte);
		all.add(qteArea);
		all.add(unite);
		all.add(uniteArea);
		all.add(typeIndividu);
		all.add(typeIndividuArea);
		all.add(famille);
		all.add(familleArea);
		all.add(prix);
		all.add(prixArea);
		effetPanel.add(effet);
		contreInd.add(cIndication);
		contreInd.add(cindArea);
		select.setPreferredSize(new Dimension(1500,50));
		select.setBackground(Color.white);
		this.add(bienvenue);
		this.add(select);
		this.add(all);
		all.add(effetPanel);
		all.add(contreInd);
		all.add(compoPanel);
		all.add(composition);
		compoPanel.add(composition);
		compoPanel.add(compoArea);
		compoPanel.setPreferredSize(new Dimension(1500,215));
		compoPanel.setBackground(new Color(255, 255, 255));
		all.add(noticePanel);
		noticePanel.add(notice);
		noticePanel.add(modifierMedoc);
		noticePanel.add(suppressionMedoc);
		
	
		}
		
		List<List> ListMedoc = controller.Medicament.consultationMedoc();
		List<String> ListIdMedoc = new ArrayList<String>();
		List<String> ListNomMedoc = new ArrayList<String>();

		
		for (int m = 0; m<ListMedoc.size();m++) {
			ListIdMedoc.add((String) ListMedoc.get(m).get(0));
			ListNomMedoc.add((String) ListMedoc.get(m).get(2));			
		}
		
		String medoc[] = ListNomMedoc.toArray(new String[0]); 
		JComboBox<?> comboMedoc = new JComboBox<Object>(medoc);
		select.add(comboMedoc);

		int medocSelectId = comboMedoc.getSelectedIndex();
		String medocSelectStr = Integer.toString(medocSelectId);

		comboMedoc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				Popup popupMedoc = new Popup("Medicament", 1700,900);
				List<List> ListThisMedoc = controller.Medicament.consultationThisMedoc(medocSelectStr);

				//depot legal, nom, effet secondaire, contre indcation, composition, quantite, unite, type individu, famille, prix, notice
				String depotStr = null;
				String nomMedocThis = null;
				String effetStr = null;
				String contreIndStr = null;
				String compoStr = null;
				String qteStr = null;
				String uniteStr = null;
				String typeStr = null;
				String familleStr = null;
				String prixStr = null;
				String noticeStr = null;
				
				for(int j = 0; j<ListThisMedoc.size();j++) {
				
					depotStr = (String) ListThisMedoc.get(j).get(1);
					nomMedocThis = (String) ListThisMedoc.get(j).get(2);
					effetStr = (String) ListThisMedoc.get(j).get(3);
					contreIndStr = (String) ListThisMedoc.get(j).get(4);
					qteStr = (String) ListThisMedoc.get(j).get(5);
					uniteStr = (String) ListThisMedoc.get(j).get(6);
					typeStr = (String) ListThisMedoc.get(j).get(7);
					familleStr = (String) ListThisMedoc.get(j).get(8);
					prixStr = (String) ListThisMedoc.get(j).get(9);
					noticeStr = (String) ListThisMedoc.get(j).get(10);
					
				}
				TitrePrincipale titre = new TitrePrincipale(nomMedocThis);

				JPanel all = new JPanel();
				Font font = new Font("Open Sans", Font.PLAIN, 18);
				all.setFont(font);
				all.setPreferredSize(new Dimension(1000,100));
				JPanel noticePanel = new JPanel();
				JPanel contreInd = new JPanel();
				JPanel effetPanel = new JPanel();
				popupMedoc.add(titre);
				popupMedoc.add(all);
				popupMedoc.add(noticePanel);
				popupMedoc.add(contreInd);
				popupMedoc.add(effetPanel);
				noticePanel.setPreferredSize(new Dimension(1000,75));
				effetPanel.setPreferredSize(new Dimension(1000,215));
				contreInd.setPreferredSize(new Dimension(1000,215));
				JLabel depot = new JLabel("Dépôt légal :");
				JTextArea depotArea = new JTextArea(depotStr);
				JLabel nom = new JLabel("Nom du médicament");
				JTextArea nomArea = new JTextArea(nomMedocThis);
				JLabel effet = new JLabel("Effet indésirables");
				JTextArea effetArea = new JTextArea(effetStr);
				JLabel cIndication = new JLabel("Contre indication");
				JTextArea cindArea = new JTextArea(contreIndStr);
				JLabel composition = new JLabel("Composition :");
				JTextArea compoArea = new JTextArea(compoStr);
				JLabel qte = new JLabel("Quanité :");
				JTextArea qteArea = new JTextArea(qteStr);
				JLabel unite = new JLabel("Unité :");
				JTextArea uniteArea = new JTextArea(uniteStr);
				JLabel typeIndividu = new JLabel("Type individu");
				JTextArea typeIndividuArea = new JTextArea(typeStr);
				JLabel famille = new JLabel("Famille");
				JTextArea familleArea = new JTextArea(familleStr);
				JLabel prix = new JLabel("Prix");
				JTextArea prixArea = new JTextArea(prixStr);
				JButton notice = new JButton("Notice");
				notice.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						Popup noticePopup = new Popup("Notice", 1500, 900);
						JPanel noticePanel = new JPanel();
						noticePanel.setPreferredSize(new Dimension(1500,1000));
						List<List> ListNote = controller.Medicament.consultationNotice(medocSelectStr);
						String noticeThis =null;
						for(int n=0;n<ListNote.size();n++) {
							noticeThis = (String) ListNote.get(n).get(1);
						}
						JTextArea noticeArea = new JTextArea(noticeThis);
						noticePopup.add(noticePanel);
						noticePanel.add(noticeArea);
						JScrollPane jScrollPane = new JScrollPane( noticePanel);
						// only a configuration to the jScrollPane...
						jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

						// Then, add the jScrollPane to your frame
						noticePopup.getContentPane().add(jScrollPane);
						
						JScrollPane scroll=new JScrollPane(noticePanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						noticePopup.getContentPane().add(scroll);
						
						


					}
				});
				
				modifierMedoc.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						String depotU = depotArea.getText();
						String nomU = nomArea.getText();
						String effetU = effetArea.getText();
						String cindU = cindArea.getText();
						String compoU = compoArea.getText();
						String qteU = qteArea.getText();
						String uniteU = uniteArea.getText();
						String typeU =  typeIndividuArea.getText();
						String familleU = familleArea.getText();
						String prixU = prixArea.getText();
						controller.Medicament.updateMedoc(medocSelectStr, depotU, nomU,  cindU, compoU, qteU, uniteU, typeU, familleU, prixU);
					}
				});
				
				suppressionMedoc.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent ae) {
						controller.Medicament.suppressionMedoc(medocSelectStr);
					}
				});
				JPanel compoPanel = new JPanel();
				JLabel compoLabel = new JLabel("Composition");
				compoPanel.setPreferredSize(new Dimension(1500,215));
				compoPanel.setBackground(new Color(255, 255, 255));
				all.add(nom);
				all.add(nomArea);
				all.add(depot);
				all.add(depotArea);
				all.add(compoPanel);
				all.add(qte);
				all.add(qteArea);
				all.add(unite);
				all.add(uniteArea);
				all.add(typeIndividu);
				all.add(typeIndividuArea);
				all.add(famille);
				all.add(familleArea);
				all.add(prix);
				all.add(prixArea);
				effetPanel.add(effet);
				effetPanel.add(effetArea);
				contreInd.add(cIndication);
				compoPanel.add(compoLabel);
				compoPanel.add(composition);
				compoPanel.add(compoArea);
				contreInd.add(cindArea);
				noticePanel.add(notice);
				noticePanel.add(modifierMedoc);
				noticePanel.add(suppressionMedoc);
				
			}
		});
		




	}
}
