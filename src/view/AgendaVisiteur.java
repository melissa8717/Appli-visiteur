package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.xmlbeans.impl.soap.Node;

import com.mysql.fabric.xmlrpc.base.Array;

import controller.AgendaC;

import java.util.List;
import view.agenda.*;

import java.util.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AgendaVisiteur extends JPanel {

	public AgendaVisiteur() {
		TitrePrincipale titre = new TitrePrincipale("Agenda des visiteurs");
		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
        List<String> ListNomVisiteur=new ArrayList<String>();


		
		for(int i=0; i<ListVisiteur.size(); i++) {
			ListNomVisiteur.add((String) ListVisiteur.get(i).get(0));

		}
		
        String nomVisiteur[]=ListNomVisiteur.toArray(new String[0]);

        
		JComboBox<?> visiteurs = new JComboBox<Object>(nomVisiteur);
		visiteurs.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });   

		
		JPanel top = new JPanel();
	    top.setPreferredSize(new Dimension(1800, 100));

		JPanel global = new JPanel();
		global.setBackground(new Color(102, 163, 211));
		global.setForeground(Color.blue);
		
		JPanel tableau = new JPanel();
		JPanel tableauII = new JPanel();
		JPanel tableauIII = new JPanel();
		JPanel tableauIV = new JPanel();
		JPanel tableauV = new JPanel();


	
		global.add(tableau);
		global.add(tableauII);
		global.add(tableauIII);
		global.add(tableauIV);
		global.add(tableauV);

	    tableau.setPreferredSize(new Dimension(300, 650));
	    tableauII.setPreferredSize(new Dimension(300, 650));
	    tableauIII.setPreferredSize(new Dimension(300, 650));
	    tableauIV.setPreferredSize(new Dimension(300, 650));
	    tableauV.setPreferredSize(new Dimension(300, 650));
	    
		tableau.setBackground(Color.white);
		tableauII.setBackground(Color.white);
		tableauIII.setBackground(Color.white);
		tableauIV.setBackground(Color.white);
		tableauV.setBackground(Color.white);
		
		

	    this.setSize(300,200);
	    this.setLayout(new BorderLayout());
	    this.setVisible(true);
	    DefaultTableModel model;
		GregorianCalendar cal = new GregorianCalendar();
		JLabel label;
	 
	    label = new JLabel();
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	 
	    JButton b1 = new JButton("<-");
	    b1.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        cal.add(Calendar.MONTH, -1);
	        updateMonth();
	      }
	    });
	 
	    JButton b2 = new JButton("->");
	    b2.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        cal.add(Calendar.MONTH, +1);
	        updateMonth();
	      }
	    });
	 
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
	    panel.add(b1,BorderLayout.WEST);
	    panel.add(label,BorderLayout.CENTER);
	    panel.add(b2,BorderLayout.EAST);
	 
	 
	    String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	    model = new DefaultTableModel(null,columns);
	    JTable table = new JTable(model);
	    JScrollPane pane = new JScrollPane(table);
	 
	    this.add(panel,BorderLayout.NORTH);
	    this.add(pane,BorderLayout.CENTER);
	 
	    this.updateMonth();
	 
	  }
	 
	  void updateMonth() {
		DefaultTableModel model;
		GregorianCalendar cal = new GregorianCalendar();
		JLabel label;
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	 
	    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	    int year = cal.get(Calendar.YEAR);
	    label.setText(month + " " + year);
	 
	    int startDay = cal.get(Calendar.DAY_OF_WEEK);
	    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
	 
	    model.setRowCount(0);
	    model.setRowCount(weeks);
	 
	    int i = startDay-1;
	    for(int day=1;day<=numberOfDays;day++){
	      model.setValueAt(day, i/7 , i%7 );    
	      i = i + 1;
	    }
	 
	  }


		this.add(titre);
		this.add(top);
		top.add(visiteurs);
		this.add(global);
		
	}

}
