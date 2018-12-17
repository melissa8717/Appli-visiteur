package view;
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
    	List<List> List_CR= compteRenduControleur.consultationCompteRendu(Integer.parseInt(dateFormat.format(new Date())));
		JComboBox<String> jcombo = new JComboBox<>(months);
		TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
        mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));

       JPanel espacement= new JPanel();
       
       espacement.setPreferredSize(new Dimension(1000,100));
       
       this.add(mesSaisiesCompteRendu);
       this.add(jcombo);
       this.add(espacement);
		    

// select des dates
		 jcombo.addActionListener(e -> {	
			 //TODO faire une fonction qui change le texte des labels qu'on aurait éventuellement définie plus tot
			 //Peut être changer le systeme de boucle actuelle du coup et passer sur des JPanel fixe et des labels définie
			 
		     // List_CR= compteRenduControleur.consultationCompteRendu((int) jcombo.getSelectedIndex()+1);
		    });


     
        for (int i = 0; i < List_CR.size(); i++) {
        	JPanel carte= new JPanel(); 	
        	carte.setBackground(Color.white);
        	carte.setPreferredSize(new Dimension(1000,50));
        	
        	
        	
        	/*Les espaces sont temporaires, ne pas faire attention, mettre en rouge si vous voulez savoir à quoi ca correspond*/
        	JPanel espace= new JPanel();
        	espace.setBackground(Color.white);
        	espace.setPreferredSize(new Dimension(350,50));
        	JPanel espace2= new JPanel();
        	espace2.setBackground(Color.white);
        	espace2.setPreferredSize(new Dimension(350,50));
        	/*****************************************************************************************************************/
        	
        	
        	
        	String dateBrute= (String) List_CR.get(i).get(1);
        	String DdMmAaaa=dateBrute.substring(8,10)+"/"+dateBrute.substring(5,7)+"/"+dateBrute.substring(0, 4);
        	
        	JLabel date= new JLabel("Date: "+DdMmAaaa);
        	//JLabel bilan= new JLabel((String) List_CR.get(i).get(2));
        	JLabel medoc= new JLabel((String) List_CR.get(i).get(6));
        	JLabel Medecin=new JLabel((String) List_CR.get(i).get(5));
        	carte.add(date);
        	carte.add(espace);
        	//carte.add(bilan);
        	carte.add(medoc);
        	carte.add(espace2);
        	carte.add(Medecin);
        	this.add(carte);
        	
        }
     
    }
	
    }