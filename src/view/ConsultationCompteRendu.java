package view;
import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import controller.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import javax.swing.JComboBox;
import  java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import controller.*;


public class ConsultationCompteRendu extends JPanel {
    public ConsultationCompteRendu() {
   
    	final  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
  		      "Juillet", "Ao�t", "Septembre", "Octobre", "Novembre", "Decembre" };
  
		    JPanel someFrame = new JPanel();
		    someFrame.setSize(250, 250);

		    JComboBox<Month> jcombo = new JComboBox<>(Month.values());
		    this.add(jcombo);

// select des dates
		 jcombo.addActionListener(e -> {

				Month selMonth = (Month) ((JComboBox<Month>) e.getSource()).getSelectedItem();
				    System.out.println(selMonth);
				    //final String medicament = controller.compteRenduControleur.resultat;

				    //compteRenduControleur cr =new compteRenduControleur();  // Ta Classe1
				    

				controller.compteRenduControleur.consultationCompteRendu(  );
		        List<List> List_CR= compteRenduControleur.consultationCompteRendu();


		    });

   
        
        TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
        mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));

      

       List<List> List_CR= compteRenduControleur.consultationCompteRendu();
       
       List<String> ListDate=new ArrayList<String>();
       List<String> ListMedecin=new ArrayList<String>();
       List<String> ListMedoc=new ArrayList<String>();
       
       
       
        for (int i = 0; i < List_CR.size(); i++) {
            
			 ListDate.add((String) List_CR.get(i).get(1));
			
			ListMedoc.add((String) List_CR.get(i).get(6));
			
			ListMedecin.add((String) List_CR.get(i).get(5));
			
			String medicament = ListMedoc.toString();
			 JLabel labelMedicament = new JLabel(medicament);	
			 this.add(labelMedicament);
			 String date = ListDate.toString();
			 String medecin = ListMedecin.toString();
		     
			 JLabel labelMedecin = new JLabel(medecin);
			 JLabel labelDate = new JLabel(date);
			 this.add(labelDate );
			 
	
		}
       
        JPanel espacement[]= {new JPanel(new FlowLayout(FlowLayout.LEFT))};

        JPanel[] panel = {new JPanel(new FlowLayout(FlowLayout.LEFT))};
        
        for(int i = 1;i<10;i++) {
        	panel = ajoutemoi(panel, new JPanel(new FlowLayout(FlowLayout.LEFT)));
        	espacement = ajoutemoi(espacement, new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }

        
      
        
      /*  for (Integer j = 0; j < 4; j++) {
            this.add(new CarteCompteRendu(medicament, medecin));
        }*/
        
      

        // container du CR
        JPanel cartes = new JPanel();
   
      
      
      //CarteCompteRendu carteCompteRendu = new CarteCompteRendu(medicament,medecin);
      
      //ajout des variables dans la fenetre
        this.add(mesSaisiesCompteRendu);
        this.add(someFrame);
       // this.add(datePicker);
        //this.add(pickDate);
      // this.add(carteCompteRendu);

     
    }

	private JPanel[] ajoutemoi(JPanel[] panel, JPanel jPanel) {
		// TODO Auto-generated method stub
		return null;
	}

	
    

}
