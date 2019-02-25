package view;

import view.TitrePrincipale;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.*;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;
import java.util.List;


public class ConsultationCompteRendu extends JPanel {
 

	private static final long serialVersionUID = 1L;

	public ConsultationCompteRendu() {
   
    	final  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
  		      "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" };
  
    	
    	DateFormat dateFormat = new SimpleDateFormat("MM");
    	List<List> List_CR= compteRenduControleur.consultationCompteRendu(Integer.parseInt(dateFormat.format(new Date())),0,User.id_utilisateur);
    	
		JComboBox<String> jcombo = new JComboBox<>(months);
		jcombo.setSelectedIndex(Integer.parseInt(dateFormat.format(new Date()))-1);
		
		TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
        System.out.println(User.id_utilisateur);
       int debut=0;
       JPanel espacement= new JPanel();
       JPanel cache = new JPanel(); 
       JPanel droite = new JPanel();
       droite.setPreferredSize(new Dimension(300, 100));

       jcombo.setLocation(10,200);
       espacement.add(jcombo, BorderLayout.CENTER);
       JPanel panelOffset=new JPanel();
  

       JLabel JlabelOffset=new JLabel(debut+"-6");
       cache.setPreferredSize(new Dimension(300, 100));
       espacement.setPreferredSize(new Dimension(300, 30));
       
       panelOffset.setPreferredSize(new Dimension(500, 35));
       panelOffset.add(JlabelOffset, BorderLayout.CENTER);
       panelOffset.setBackground(Color.white);
       this.add(mesSaisiesCompteRendu);
       this.add(cache);
       this.add(espacement);
       this.add(droite);

       this.add(panelOffset, BorderLayout.CENTER);
       
       JPanel carte[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
       JPanel CardsPanel[]= {new JPanel()};
       
       for(int i = 1;i<40;i++) {
    	 CardsPanel = ajoutemoi(CardsPanel, new JPanel());
       	
       }
       JLabel date[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel medoc[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel Medecin[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel bilan[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel motif[] = {new JLabel()};
       
       JButton boutonVoir[]= {new JButton("Voir"),new JButton("Voir"),new JButton("Voir"),
    		   new JButton("Voir"),new JButton("Voir"),new JButton("Voir")};
       
       CRChangeLabel(Integer.parseInt(dateFormat.format(new Date())),0,date,medoc,Medecin,bilan,boutonVoir,User.id_utilisateur);
       for (int i = 0; i < carte.length; i++) {
    	   carte[i].setBackground(Color.white);
    	   carte[i].setPreferredSize(new Dimension(1820,50));
    	   
    	   final int iTmp = i;
    	   //Panel date
    	   CardsPanel[i].add(date[i]);
    	   CardsPanel[i].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i]);
    	   
    	   
    	   //Panel medoc
    	   CardsPanel[i+7].add(medoc[i]);
    	   CardsPanel[i+7].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+7].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i+7]);

    	   //Panel medecin
    	   CardsPanel[i+14].add(Medecin[i]);
    	   CardsPanel[i+14].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+14].setBackground(Color.white);    	   
    	   carte[i].add(CardsPanel[i+14]);
    	   
    	   //Panel invisible bilan
    	   CardsPanel[i+20].add(bilan[i]);
    	   CardsPanel[i+20].setVisible(false);
    	   carte[i].add(CardsPanel[i+20]);
    	   if(Medecin[i].getText()!=null) {
    		  
	    	   carte[i].add(boutonVoir[i]);
	    	   boutonVoir[i].addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	int widthPopup=900;
				    	int HeightPopup=500;
				    	Popup popup = new Popup("Compte Rendu", widthPopup, HeightPopup);
					
						JPanel panelCompteRendu= new JPanel();
						
						
						JPanel[] espacement= {new JPanel(),new JPanel(),new JPanel()};
						for (int j = 0; j < espacement.length; j++) {
							espacement[j].setPreferredSize(new Dimension(550,50));
							espacement[j].setBackground(Color.WHITE);
						}
						
						
						JLabel labelBilan=new JLabel("Bilan: "+bilan[iTmp].getText());
						JLabel labelDate=new JLabel(date[iTmp].getText());
						JLabel labelPraticien=new JLabel("Praticien: "+Medecin[iTmp].getText());
						int idMedoc=Integer.parseInt(medoc[iTmp].getText());
						String nomMedoc=compteRenduControleur.selectNomMedoc(idMedoc);
						JLabel labelMedicament=new JLabel("Medicament: "+nomMedoc);
						JLabel labelMotif =new JLabel("Motif: "+motif[iTmp].getText());
						System.out.println(labelMotif);
						panelCompteRendu.setPreferredSize(new Dimension(700,400));
						panelCompteRendu.setBackground(Color.white);
	
						panelCompteRendu.add(labelDate);
						panelCompteRendu.add(espacement[0]);
						panelCompteRendu.add(labelPraticien);
						panelCompteRendu.add(espacement[1]);
						panelCompteRendu.add(labelMedicament);
						panelCompteRendu.add(espacement[2]);
						panelCompteRendu.add(labelBilan);
						
						
						popup.add(panelCompteRendu);
				    }
	    	   });
    	   } 
    	   
    	   this.add(carte[i]);
	}
   	
       
       espacement.setPreferredSize(new Dimension(1000,100));
       

// select des dates
       		
		 jcombo.addActionListener(e -> {	
			 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,Medecin,bilan,boutonVoir,User.id_utilisateur);
			 JlabelOffset.setText("0-6");
		    });
		 JButton Suivant=new JButton("Suivant");
		 JButton Precedent=new JButton("Précédent");
		 
		 Suivant.addActionListener(new ActionListener() { 
			    public void actionPerformed(ActionEvent e) {
			    	int indexOffset=JlabelOffset.getText().indexOf("-");
			    	int offset=Integer.parseInt(JlabelOffset.getText().substring(0,indexOffset));
			    	offset+=6;
			    	int finOffset=offset+6;
			        CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,Medecin,bilan,boutonVoir,User.id_utilisateur);
			        JlabelOffset.setText(Integer.toString(offset)+"-"+finOffset);
			       
			    }
			});
		 
		 Precedent.addActionListener(new ActionListener() {
			 
			    public void actionPerformed(ActionEvent e) {
			    	int indexOffset=JlabelOffset.getText().indexOf("-");
			    	int offset=Integer.parseInt(JlabelOffset.getText().substring(0,indexOffset));
			    	if(offset!=0) {
			    	offset-=6;
				    int finOffset=offset+6;
			        CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,Medecin,bilan,boutonVoir,User.id_utilisateur);
			        JlabelOffset.setText(Integer.toString(offset)+"-"+finOffset);
			        
			    	}
			    }
			});
		 this.add(Precedent);
		 this.add(Suivant);
     
    }
	public void CRChangeLabel(int Mois,int debut,JLabel[] labelDate,JLabel[] labelMedoc,JLabel[] labelMedecin,JLabel[] labelBilan,JButton[] bouton,int idUser){
		
		List<List> List_CR= compteRenduControleur.consultationCompteRendu(Mois,debut,idUser);
		System.out.println(List_CR.size());
		if(List_CR.size()<6) {
			for (int i = 0; i < 6; i++) {
				
				if(i>List_CR.size()-1) {
					labelDate[i].setText("");
					labelMedoc[i].setText("");
					labelMedecin[i].setText("");
					labelBilan[i].setText("");
					bouton[i].setVisible(false);
					
				}else {
					String dateBrute= (String) List_CR.get(i).get(1);
		        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
		        	
		        	int idMedoc=Integer.parseInt((String) List_CR.get(i).get(6));
					String nomMedoc=compteRenduControleur.selectNomMedoc(idMedoc);
		        	String Medecin=(String) List_CR.get(i).get(5);
		        	String Bilan=(String) List_CR.get(i).get(2);
					labelDate[i].setText("Date: "+DdMmAaaa);
					labelMedoc[i].setText(nomMedoc);
					labelMedecin[i].setText(Medecin);
					labelBilan[i].setText(Bilan);
					bouton[i].setVisible(true);
					
				}
				labelDate[List_CR.size()].setText("Il n'y a pas d'autre compte rendu.");
			}
			
		} else {
			for (int i = 0; i < List_CR.size(); i++) {
				String dateBrute= (String) List_CR.get(i).get(1);
	        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
	        	String Medoc=(String) List_CR.get(i).get(6);
	        	String Medecin=(String) List_CR.get(i).get(5);
	        	String Bilan=(String) List_CR.get(i).get(2);
				labelDate[i].setText("Date: "+DdMmAaaa);
				labelMedoc[i].setText(Medoc);
				labelMedecin[i].setText(Medecin);
				labelBilan[i].setText(Bilan);
				bouton[i].setVisible(true);
			}
		}
	}
	private JPanel[] ajoutemoi(JPanel[] MonArray, JPanel NouveauPanel) {
		/*
		 * 
		 * Fonction qui sert � rajouter des �l�ments dans un tableau. (ici nous ajoutons des JPanels) 
		 * 
		 */
		int newSize = MonArray.length + 1;
		JPanel[] tempArray = new JPanel[ newSize ];
		//Nous cr��ons un array temporaire avec la taille de l'array actuelle
		
		for (int i=0; i < MonArray.length; i++)
		{
			tempArray[i] = MonArray[i];
			//Pour chaque �l�ment dans mon array, on ajoute l'�l�ment dans la nouvelle array temporaire
		}
		tempArray[newSize- 1] = NouveauPanel; //Ici on ajoute le nouveau Jpanel dans l'array
		return tempArray;   // on retourne notre nouvelle array
	}
}