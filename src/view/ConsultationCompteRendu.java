package view;

import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import javax.swing.JComboBox;
import  java.util.Date;
import java.util.Calendar;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import controller.*;


public class ConsultationCompteRendu extends JPanel {
    public ConsultationCompteRendu() {
        //DateFormat dateFormat = new SimpleDateFormat("MM/yyy");
        //Date date = new Date();
    	final  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
  		      "Juillet", "Ao�t", "Septembre", "Octobre", "Novembre", "Decembre" };
  
		    JPanel someFrame = new JPanel();
		    someFrame.setSize(250, 250);

		    JComboBox<Month> jcombo = new JComboBox<>(Month.values());
		    this.add(jcombo);
		    jcombo.addActionListener(e -> {
		   
				try {
				     Month selMonth = (Month) ((JComboBox<Month>) e.getSource()).getSelectedItem();
				        System.out.println(selMonth);
					controller.compteRenduControleur.consultationCompteRendu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		    });

   

        
        TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Consultation des comptes rendus");
        mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));

      

       // Date[] dates = {formater};
        String[] medecins = {"Jean", "Michel", "Daniel", "Melissa", "Arthur", "Bernard", "Jeannot", "Nicolas", "Raoul", "Adrien", "Florian", "Lucas"};
        String[] medicaments = {"Lexomil", "Texomil", "Oxomil", "Otexomil", "Rexomil", "Yexomil", "Jexomil", "Mexomil", "Xexomil", "Ixomil", "Uxomil"};

       // JComboBox<Date> select = new JComboBox<Date>((ComboBoxModel<Date>) formater);
        
		
	

        JPanel cartes = new JPanel();

       /* for (Integer i = 0; i < 4; i++) {
            this.add(new CarteCompteRendu( medecins[i], medicaments[i]));
        }*/


      //  CarteCompteRendu carteCompteRendu = new CarteCompteRendu(datePicker, "Michel Boujenah", "Lexomil");

        this.add(mesSaisiesCompteRendu);
       // this.add(datePicker);
        //this.add(pickDate);
        //this.add(carteCompteRendu);
        this.add(someFrame);
    }
}
