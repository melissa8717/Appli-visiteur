package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.xmlbeans.impl.soap.Node;

import com.mysql.fabric.xmlrpc.base.Array;

import controller.AgendaC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.Arrays;

public class AgendaVisiteur extends JPanel{
	public Agenda  agenda = new Agenda();

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


		this.add(titre);
		this.add(visiteurs);
		
	}

}
