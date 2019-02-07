package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AgendaC;
import controller.compteRenduControleur;

/**
 * Bean to display a month calendar in a JPanel. Only works for the Western
 * calendar.
 * 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Cal.java,v 1.5 2004/02/09 03:33:45 ian Exp $
 */
public class AgendaVisiteur extends JPanel {
	public AgendaVisiteur() {
		TitrePrincipale titre = new TitrePrincipale("Agenda des visiteurs");
		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
        List<String> ListNomVisiteur=new ArrayList<String>();

		
		for(int i=0; i<ListVisiteur.size(); i++) {
			ListNomVisiteur.add((String) ListVisiteur.get(i).get(0));
			 String nomOnly= (String) ListVisiteur.get(i).get(0);
			 JButton nomLabel = new JButton();
	         nomLabel.setText(nomOnly);


		}
		
        String[] nomVisiteur=ListNomVisiteur.toArray(new String[0]);


        
		JComboBox<?> visiteurs = new JComboBox<Object>(nomVisiteur);
		visiteurs.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				Popup choixVisiteur = new Popup("", 500,800);
				
				
				
			}
        });   

		
		JPanel top = new JPanel();
	    top.setPreferredSize(new Dimension(1800, 1200));
	    
	    JPanel tableau = new JPanel();
	    JPanel tableauII = new JPanel();
	    JPanel tableauIII = new JPanel();
	    JPanel tableauIV = new JPanel();
	    JPanel tableauV = new JPanel();
	    
	    tableau.setPreferredSize(new Dimension(300, 750));
	    tableauII.setPreferredSize(new Dimension(300, 750));
	    tableauIII.setPreferredSize(new Dimension(300, 750));
	    tableauIV.setPreferredSize(new Dimension(300, 750));
	    tableauV.setPreferredSize(new Dimension(300, 750));
	    tableau.setBackground(Color.white);
	    tableauII.setBackground(Color.gray);
	    tableauIII.setBackground(Color.white);
	    tableauIV.setBackground(Color.gray);
	    tableauV.setBackground(Color.white);
	    JPanel nom = new JPanel();


	    nom.setBackground(Color.cyan);
	    nom.setPreferredSize(new Dimension(300, 75));
	
	    JPanel lundi = new JPanel();
	    JPanel mardi = new JPanel();
	    JPanel mercredi = new JPanel();
	    JPanel jeudi = new JPanel();
	    JPanel vendredi = new JPanel();
	    JPanel samedi = new JPanel();
	    lundi.setPreferredSize(new Dimension(300, 108));
	    mardi.setPreferredSize(new Dimension(300, 108));
	    mercredi.setPreferredSize(new Dimension(300, 108));
	    jeudi.setPreferredSize(new Dimension(300, 108));
	    vendredi.setPreferredSize(new Dimension(300, 108));
	    samedi.setPreferredSize(new Dimension(300, 108));
	    lundi.setBackground(Color.white);
	    mardi.setBackground(Color.lightGray);
	    mercredi.setBackground(Color.white);
	    jeudi.setBackground(Color.lightGray);
	    vendredi.setBackground(Color.white);
	    samedi.setBackground(Color.lightGray);




	

		this.add(titre);
		this.add(visiteurs);
		this.add(top);
		top.add(tableau);
		top.add(tableauII);
		top.add(tableauIII);
		top.add(tableauIV);
		top.add(tableauV);
		tableau.add(nom);
		tableauII.add(nom);
		tableauIII.add(nom);
		tableauIV.add(nom);
		tableauV.add(nom);
		tableau.add(lundi);
	    tableau.add(mardi);
	    tableau.add(mercredi);
	    tableau.add(jeudi);
	    tableau.add(vendredi);
	    tableau.add(samedi);

	}

}
           
         
    
    
  