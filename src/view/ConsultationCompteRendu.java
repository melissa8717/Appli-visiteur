package view;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.*;

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
        mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));
        System.out.println(User.id_utilisateur);
       int debut=0;
       JPanel espacement= new JPanel();
       JPanel panelOffset=new JPanel();
       JLabel JlabelOffset=new JLabel(debut+"-6");
       panelOffset.setPreferredSize(new Dimension(450, 35));
       panelOffset.add(JlabelOffset);
       panelOffset.setBackground(Color.white);
       this.add(mesSaisiesCompteRendu);
       this.add(espacement);
      
       this.add(jcombo);
       this.add(panelOffset);
       
       JPanel carte[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
       JPanel CardsPanel[]= {new JPanel()};
       
       for(int i = 1;i<19;i++) {
    	 CardsPanel = ajoutemoi(CardsPanel, new JPanel());
       	
       }
       
       JLabel date[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel medoc[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel Medecin[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       
       CRChangeLabel(Integer.parseInt(dateFormat.format(new Date())),0,date,medoc,Medecin,User.id_utilisateur);
       for (int i = 0; i < carte.length; i++) {
    	   carte[i].setBackground(Color.white);
    	   carte[i].setPreferredSize(new Dimension(1300,50));
    	   //CartePanel date
    	   CardsPanel[i].add(date[i]);
    	   CardsPanel[i].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i]);
    	   //
    	   CardsPanel[i+6].add(medoc[i]);
    	   CardsPanel[i+6].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+6].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i+6]);
    	   //
    	   CardsPanel[i+12].add(Medecin[i]);
    	   CardsPanel[i+12].setPreferredSize(new Dimension(1200/3,50));
    	   CardsPanel[i+12].setBackground(Color.white);
    	   carte[i].add(CardsPanel[i+12]);
    	   
    	   this.add(carte[i]);
	}
   	
       
       espacement.setPreferredSize(new Dimension(1000,100));
       

// select des dates
       		
		 jcombo.addActionListener(e -> {	

			 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,Medecin,User.id_utilisateur);
		    });
		 JButton Suivant=new JButton("Suivant");
		 JButton Precedent=new JButton("Précédent");
		 
		 Suivant.addActionListener(new ActionListener() { 
			    public void actionPerformed(ActionEvent e) {
			    	int indexOffset=JlabelOffset.getText().indexOf("-");
			    	int offset=Integer.parseInt(JlabelOffset.getText().substring(0,indexOffset));
			    	offset+=6;
			    	int finOffset=offset+6;
			        CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,Medecin,User.id_utilisateur);
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
			        CRChangeLabel((int) jcombo.getSelectedIndex()+1,offset,date,medoc,Medecin,User.id_utilisateur);
			        JlabelOffset.setText(Integer.toString(offset)+"-"+finOffset);
			        
			    	}
			    }
			});
		 this.add(Precedent);
		 this.add(Suivant);
     
    }
	public void CRChangeLabel(int Mois,int debut,JLabel[] labelDate,JLabel[] labelMedoc,JLabel[] labelMedecin,int idUser){
		
		List<List> List_CR= compteRenduControleur.consultationCompteRendu(Mois,debut,idUser);
		System.out.println(List_CR.size());
		if(List_CR.size()<6) {
			for (int i = List_CR.size(); i < 6; i++) {
				labelDate[i].setText("");
				labelMedoc[i].setText("");
				labelMedecin[i].setText("");	
			}
			labelDate[List_CR.size()].setText("Il n'y a pas d'autre compte rendu.");
		} else {
			for (int i = 0; i < List_CR.size(); i++) {
				String dateBrute= (String) List_CR.get(i).get(1);
	        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
	        	String Medoc=(String) List_CR.get(i).get(6);
	        	String Medecin=(String) List_CR.get(i).get(5);
				labelDate[i].setText("Date: "+DdMmAaaa);
				labelMedoc[i].setText(Medoc);
				labelMedecin[i].setText(Medecin);
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