package view;

import view.TitrePrincipale;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;


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
		//jcombo.setSelectedIndex(Integer.parseInt(dateFormat.format(new Date()))-1);
		
		//TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
        //mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));
        System.out.println(User.id_utilisateur);

       JPanel espacement= new JPanel();
       //this.add(mesSaisiesCompteRendu);
       this.add(espacement);
       this.add(jcombo);
       JPanel carte[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
       
       JLabel date[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel medoc[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       JLabel Medecin[]= {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
       CRChangeLabel(Integer.parseInt(dateFormat.format(new Date())),0,date,medoc,Medecin,User.id_utilisateur);
       for (int i = 0; i < carte.length; i++) {
    	   carte[i].setBackground(Color.white);
    	   carte[i].setPreferredSize(new Dimension(1000,50));
    	   carte[i].add(date[i]);
    	   carte[i].add(medoc[i]);
    	   carte[i].add(Medecin[i]);
    	   this.add(carte[i]);
	}
   	
       
       espacement.setPreferredSize(new Dimension(1000,100));
       
       
      
      
		    

// select des dates
		 jcombo.addActionListener(e -> {	
			 //TODO faire une fonction qui change le texte des labels qu'on aurait éventuellement définie plus tot
			 //Peut être changer le systeme de boucle actuelle du coup et passer sur des JPanel fixe et des labels définie
			 
		     // List_CR= compteRenduControleur.consultationCompteRendu((int) jcombo.getSelectedIndex()+1);
			 CRChangeLabel((int) jcombo.getSelectedIndex()+1,0,date,medoc,Medecin,User.id_utilisateur);
		    });


     

        	
        	
        	//String dateBrute= (String) List_CR.get(i).get(1);
        	//String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
        	
        	/*JLabel date= new JLabel("Date: "+DdMmAaaa);
        	JLabel bilan= new JLabel((String) List_CR.get(i).get(2));
        	JLabel medoc= new JLabel((String) List_CR.get(i).get(6));
        	JLabel Medecin=new JLabel((String) List_CR.get(i).get(5));*/
        	
        	
        
     
    }
	public void CRChangeLabel(int Mois,int debut,JLabel[] labelDate,JLabel[] labelMedoc,JLabel[] labelMedecin,int idUser){
		DateFormat dateFormat = new SimpleDateFormat("MM");
		List<List> List_CR= compteRenduControleur.consultationCompteRendu(Mois,debut,idUser);
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