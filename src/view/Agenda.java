package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.text.html.HTML;

import com.mysql.jdbc.Connection;

import controller.AgendaC;
import controller.compteRenduControleur;
import model.User;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import view.agenda.Date;
import model.Connecteur;

 
public class Agenda<Spanned> extends JPanel  {

	/**
         * 
         */
	private static final long serialVersionUID = 1L;


	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		
		this.add(bienvenue);
		 
		    buildGUI();
		    recompute();
	}
	
	
	
	 /** The buttons to be displayed */
	  protected JButton labs[][];


	  private Date date=new Date();

	  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
		      "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" };
	  
	  public int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
			  31, 30, 31, 31, /* may jun jul aug */
			  30, 31, 30, 31 /* sep oct nov dec */
			  };
	  /** The month choice */
	  public JComboBox<String> monthChoice;

	  


	  /** The year choice */
	  public JComboBox<String> yearChoice;
	  
	


	protected int recompute() {
		// TODO Auto-generated method stub
		// Blank out all
		for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) 
		         labs[i][j].setText("");

		 int daysInMonth = dom[date.getMonth()];
		 if (isLeap(date.getYear()) && date.getMonth() == 1)
		      daysInMonth++;
		//case vide avant le premier jour
		    for (int i = 0; i < date.firstDayOfTheMonth(); i++) {
		    	labs[0][i].setText("");
				labs[0][i].setSize(getMaximumSize());
				this.setVisible(true);
				labs[0][i].setOpaque(true);
				Font p = new Font("open-sans", Font.PLAIN, 18);
				labs[0][i].setFont(p);
				labs[0][i].setBackground(new Color(229,236,246));
			  
		    }
			 

		 // Case des dates jour du mois.
		
		    for ( int  i = 1; i <= daysInMonth; i++) {
		    	final int  iNew = i;

		  
		      JButton b = labs[(date.firstDayOfTheMonth() + i - 2) / 7][(date.firstDayOfTheMonth() + i - 2) % 7];

		      b.setText(Integer.toString(i));
		      b.getText();
			  Font p = new Font("open-sans", Font.PLAIN, 18);
			  b.setFont(p);
			  b.setBackground(Color.white);
			  b.setForeground(new Color (0,63,128));


			  b.setSize(getMaximumSize());
			  

			    String moisSelect = (String) monthChoice.getSelectedItem();
				String anneeSelect = (String) yearChoice.getSelectedItem();
			    java.util.Date cal=Calendar.getInstance().getTime();
				SimpleDateFormat moisSelected = new SimpleDateFormat("MM");
				String month_number = moisSelected.format(cal.getTime());
				String jour =  b.getText();
				SimpleDateFormat jourFormat = new SimpleDateFormat("dd");
				String jourF = jourFormat.format(cal.getTime());
				String debut = anneeSelect+"-"+month_number+"-"+jourF;
				String moisAnSelect = anneeSelect+"-"+month_number;

				List<List> List_CE= AgendaC.consultationEvenement(User.id_utilisateur);
				

				try {

				for(int y=0; y<List_CE.size();y++) {
					 String event= (String) List_CE.get(y).get(0);
					 String dateDebut= (String) List_CE.get(y).get(1);
					 String dateFin= (String) List_CE.get(y).get(2);
					 String heureDebut = (String) List_CE.get(y).get(4);

					 String heureFin = (String) List_CE.get(y).get(5);
					 String moisAn = GetDateSansJour(dateDebut);
					 String dateJour = GetDateJour(dateDebut);
					 int dateJourInt = Integer.parseInt(dateJour);
					 String idAgenda = (String) List_CE.get(y).get(6);
					 int idAgendaInt = Integer.parseInt(idAgenda);
					
					 
					 
					 JPanel jourPanel = new JPanel();
					 jourPanel.setPreferredSize(new Dimension(250,50));
					 JPanel eventPanel = new JPanel();
					 eventPanel.setBackground(Color.white);
					 JLabel eventLabel = new JLabel(Integer.toString(i)+event); 
					 eventPanel.setFont(p);
					 eventPanel.add(jourPanel);
					 JButton buttonEvent = new JButton();
					 buttonEvent.setBackground(Color.blue);
					 buttonEvent.setForeground(Color.white);
					 buttonEvent.setPreferredSize(new Dimension(200,50));
					 buttonEvent.setFont(p);
					 JButton countevent = new JButton();

					//int countEvent = controller.AgendaC.countEvenement(User.id_utilisateur, debut);
					
					 
						

					 if(moisAnSelect.equals(moisAn)) {
						
						 if(dateJourInt == i){
							 ArrayList<Integer> tabJour = new ArrayList<Integer>();
							 tabJour.add(dateJourInt);
							 
							
							 JLabel jourText = new JLabel(jour);
							 eventPanel.add(jourPanel);
							
							 if(tabJour.size() == 1)  {
								 b.removeAll(); 
								 b.updateUI();
								 b.add(eventPanel);
								 jourPanel.add(buttonEvent);
								 buttonEvent.setText(event);
							 }
							 else if (tabJour.size() > 1) {
								 for(int m = 0; m < tabJour.size(); m++) {
										 countevent.setBackground(Color.orange);
										 countevent.setForeground(Color.black);
										 countevent.setPreferredSize(new Dimension(200,50));
										 countevent.setFont(p);
										
										 int newY = y+1;
										

										 b.removeAll(); 
										 b.updateUI();
										 b.add(eventPanel);
										 countevent.setText(newY+" évenements");
										 jourPanel.add(countevent);

									 }
								 //bouton du few event
								 countevent.addActionListener(new ActionListener() {

								      public void actionPerformed(ActionEvent ae) {
								    	  Popup fewEvent = new Popup("Evenements multiples", 700,500);		
								    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènements multiples "+ jour + " "+ moisSelect+" " + anneeSelect);
								    	  fewEvent.add(titreOpened );
								    	  JPanel event = new JPanel();
								    	  JLabel eventLAbel = new JLabel("Evenements :");
								    	  
								      }
								 
								 });

							 //ouverture de l evenement choisi
							 buttonEvent.addActionListener(new ActionListener() {

							      public void actionPerformed(ActionEvent ae) {
							    	  
							    	  Popup voirEvenement = new Popup("Evènement", 600, 500);
									  voirEvenement.setAlwaysOnTop(true);
									  

							    	  String moisSelect = (String) monthChoice.getSelectedItem();
									  String anneeSelect = (String) yearChoice.getSelectedItem();
							    	  String jour =  b.getText();
							    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènement du "+ jour + " "+ moisSelect+" " + anneeSelect);
							    	  JPanel eventOpen= new JPanel();
							    	  eventOpen.setPreferredSize(new Dimension(500,70));
							    	  JLabel eventOpenLabel = new JLabel(event);
									  Font font = new Font("open-sans", Font.BOLD, 22);
							    	  eventOpenLabel.setFont(font);
							    	  JPanel heureEvent = new JPanel();
							    	  if(heureDebut == null) {
							    		  JLabel heureVide = new JLabel("Pas d'heure pour cet évènement");
							    		  heureEvent.add(heureVide);
							    		  heureVide.setFont(p);


							    	  }
							    	  else {
							    		  JLabel heureDebutEvent = new JLabel("De :" + heureDebut);
								    	  JLabel heureFinEvent = new JLabel("A :" + heureFin);
								    	  heureDebutEvent.setFont(p);
								    	  heureFinEvent.setFont(p);
								    	  heureEvent.add(heureDebutEvent);
								    	  heureEvent.add(heureFinEvent);

							    	  }
							    	  JPanel fin = new JPanel();
									  Font fontP = new Font("open-sans", Font.PLAIN, 18);

							    	  if(dateFin.equals(dateDebut)) {
							    		  JLabel finVide = new JLabel("Evènement de ce jour");

							    		  finVide.setFont(fontP);
							    		  fin.add(finVide);
							    		  finVide.setPreferredSize(new Dimension(500,70));
							    	  }
							    	  else {
								    	  JLabel finLabel = new JLabel("Fin le : "+dateFin);
								    	  fin.add(finLabel);
							    		  finLabel.setFont(fontP);
								    	  finLabel.setPreferredSize(new Dimension(500,70));

							    	  }
							    	  JButton modifier = new JButton ("Modifier l'évenement");
							    	  //modification de l'evenement
							    	  modifier.addActionListener(new ActionListener() {

									      public void actionPerformed(ActionEvent ae) {

									    	  //fermeture de la premiere fenetre de vue de levenement
									    	  voirEvenement.dispose();				
									    	  
									    		  Popup modifPopup = new Popup("Modifier l'évenement", 800, 500);
									    	   	  String moisSelect = (String) monthChoice.getSelectedItem();
												  String anneeSelect = (String) yearChoice.getSelectedItem();
										    	  String jour =  b.getText();
										    	  
										    	  TitreSecondaire titreModif = new TitreSecondaire("Evènement du "+ jour + " "+ moisSelect+" " + anneeSelect);
										    	 
										    	  JPanel eventOpenModif = new JPanel();
										    	  eventOpenModif.setPreferredSize(new Dimension(500,100));
										    	  JLabel eventOpenLabelModif = new JLabel("Evenement :");
										          JTextArea inputEvent = new JTextArea(event,5,25);
										          String TextEvent = inputEvent.getText();
										          
										      
										         
										          JPanel dateModif = new JPanel();
										          dateModif.setPreferredSize(new Dimension(500,100));
										          JLabel heureDModif = new JLabel("De :");
										          JTextArea inputHeureDeb = new JTextArea(heureDebut,1,10);
										          JTextArea inputHeureFin = new JTextArea(heureFin,1,10);
										          JLabel heureFinModif = new JLabel("A :");
										          
										          JPanel tempsModif = new JPanel();
										          tempsModif.setPreferredSize(new Dimension(500,100));
										          JLabel tempsLabel = new JLabel("Heure de début :");
										          JTextArea inputDateDeb = new JTextArea(dateDebut, 1, 10);
										          JLabel tempsFinLabel = new JLabel("Heure de fin :");
										          JTextArea inputFinDeb = new JTextArea(dateFin, 1, 10);
										          
										          JPanel buttonModif = new JPanel();
										          buttonModif.setPreferredSize(new Dimension(500,100));
										          JButton buttonValiderModif = new JButton("Valider");
										          
										          buttonValiderModif.addActionListener(new ActionListener() {

												      public void actionPerformed(ActionEvent ae) {
												    	  String TextEvent = inputEvent.getText();
												    	  String TextDateDebut = inputDateDeb.getText();
												    	  String TextDateFin = inputFinDeb.getText();
												    	  String TextHeureDeb = inputHeureDeb.getText();
												    	  String TextHeureFin = inputHeureFin.getText();


												    	  controller.AgendaC.updateEvent(idAgendaInt, TextEvent, TextDateDebut, TextDateFin, User.id_utilisateur, TextHeureDeb, TextHeureFin);
												    	  modifPopup .dispose();
												    	
												    	  Object connecteur = Connecteur.connecteurUL;
														  List<List> eventA= AgendaC.consultationEvenement(User.id_utilisateur);
														  try {
															for(int j=0; j<eventA.size();j++) {
																 String eventAfterU= (String) eventA.get(j).get(0);
																 String dateDebutU= (String) eventA.get(j).get(1);
																 String dateFinU= (String) eventA.get(j).get(2);
																 String heureDebutU = (String) eventA.get(j).get(4);
																 String heureFinU = (String) eventA.get(j).get(5);
																 String moisAnU = GetDateSansJour(dateDebut);
																 String dateJourU = GetDateJour(dateDebut);
																 int dateJourIntU = Integer.parseInt(dateJour);
																 String idAgendaU = (String) eventA.get(j).get(6);
																 int idAgendaIntU = Integer.parseInt(idAgendaU);
																 
																 
																 if(idAgendaIntU == idAgendaInt) {																		
																		
																		 String h = b.getText();
																		 final  String i = h;
																		 JLabel eventLabel = new JLabel(i); 
																		 int iNew = Integer.parseInt(i);
																		 JPanel jourPanel = new JPanel();
																		 jourPanel.setPreferredSize(new Dimension(300,50));
																		 JPanel eventPanel = new JPanel();
																		 eventPanel.setBackground(Color.white);
																		 eventPanel.setFont(p);
																		 eventPanel.add(jourPanel);
																		 JButton buttonEvent = new JButton();
																		 buttonEvent.setBackground(Color.blue);
																		 buttonEvent.setForeground(Color.white);
																		 buttonEvent.setPreferredSize(new Dimension(300,50));
																		 buttonEvent.setFont(p);
																		 
																		 if(moisAnSelect.equals(moisAn)) {

																			 if(dateJourInt == iNew){
																				 //pour recharger le jpanel et changer les elements
																				 b.removeAll(); 
																				 b.updateUI();
																				 b.add(eventPanel);
																				 eventPanel.add(eventLabel);
																				 JLabel jourText = new JLabel(h);
																				 b.add(jourText);
																				 eventPanel.add(jourPanel);
																				 jourPanel.add(buttonEvent);
																				 buttonEvent.setText(eventAfterU);
																				 
																				//ouverture de l evenement choisi
																				 buttonEvent.addActionListener(new ActionListener() {

																				      public void actionPerformed(ActionEvent ae) {
																				    	  
																				    	  Popup voirEvenement = new Popup("Evènement", 600, 500);
																						  voirEvenement.setAlwaysOnTop(true);
																						  

																				    	  String moisSelect = (String) monthChoice.getSelectedItem();
																						  String anneeSelect = (String) yearChoice.getSelectedItem();
																				    	  String jour =  b.getText();
																				    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènement du "+ jour + " "+ moisSelect+" " + anneeSelect);
																				    	  JPanel eventOpen= new JPanel();
																				    	  eventOpen.setPreferredSize(new Dimension(500,70));
																				    	  JLabel eventOpenLabel = new JLabel(eventAfterU);
																						  Font font = new Font("open-sans", Font.BOLD, 22);
																				    	  eventOpenLabel.setFont(font);
																				    	  JPanel heureEvent = new JPanel();
																				    	  if(heureDebut == null) {
																				    		  JLabel heureVide = new JLabel("Pas d'heure pour cet évènement");
																				    		  heureEvent.add(heureVide);
																				    		  heureVide.setFont(p);

																				    	  }
																				    	  else {
																				    		  JLabel heureDebutEvent = new JLabel("De :" + heureDebutU);
																					    	  JLabel heureFinEvent = new JLabel("A :" + heureFinU);
																					    	  heureDebutEvent.setFont(p);
																					    	  heureFinEvent.setFont(p);
																					    	  heureEvent.add(heureDebutEvent);
																					    	  heureEvent.add(heureFinEvent);

																				    	  }
																				    	  JPanel fin = new JPanel();
																						  Font fontP = new Font("open-sans", Font.PLAIN, 18);

																				    	  if(dateFinU.equals(dateDebutU)) {
																				    		  JLabel finVide = new JLabel("Evènement de ce jour");

																				    		  finVide.setFont(fontP);
																				    		  fin.add(finVide);
																				    		  finVide.setPreferredSize(new Dimension(500,70));
																				    	  }
																				    	  else {
																					    	  JLabel finLabel = new JLabel("Fin le : "+dateFinU);
																					    	  fin.add(finLabel);
																				    		  finLabel.setFont(fontP);
																					    	  finLabel.setPreferredSize(new Dimension(500,70));

																				    	  }
																				    	  JButton modifier = new JButton ("Modifier l'évenement");
																				    	  //modification de l'evenement
																				    	  modifier.addActionListener(new ActionListener() {

																						      public void actionPerformed(ActionEvent ae) {

																						    	  //fermeture de la premiere fenetre de vue de levenement
																						    	  voirEvenement.dispose();				
																						    	  
																						    		  Popup modifPopup = new Popup("Modifier l'évenement", 800, 500);
																						    	   	  String moisSelect = (String) monthChoice.getSelectedItem();
																									  String anneeSelect = (String) yearChoice.getSelectedItem();
																							    	  String jour =  b.getText();
																							    	  
																							    	  TitreSecondaire titreModif = new TitreSecondaire("Evènement du "+ jour + " "+ moisSelect+" " + anneeSelect);
																							    	 
																							    	  JPanel eventOpenModif = new JPanel();
																							    	  eventOpenModif.setPreferredSize(new Dimension(500,100));
																							    	  JLabel eventOpenLabelModif = new JLabel("Evenement :");
																							          JTextArea inputEvent = new JTextArea(eventAfterU,5,25);
																							          String TextEvent = inputEvent.getText();
																							          
																							      
																							         
																							          JPanel dateModif = new JPanel();
																							          dateModif.setPreferredSize(new Dimension(500,100));
																							          JLabel heureDModif = new JLabel("De :");
																							          JTextArea inputHeureDeb = new JTextArea(heureDebut,1,10);
																							          JTextArea inputHeureFin = new JTextArea(heureFin,1,10);
																							          JLabel heureFinModif = new JLabel("A :");
																							          
																							          JPanel tempsModif = new JPanel();
																							          tempsModif.setPreferredSize(new Dimension(500,100));
																							          JLabel tempsLabel = new JLabel("Heure de début :");
																							          JTextArea inputDateDeb = new JTextArea(dateDebut, 1, 10);
																							          JLabel tempsFinLabel = new JLabel("Heure de fin :");
																							          JTextArea inputFinDeb = new JTextArea(dateFin, 1, 10);
																							          
																							          JPanel buttonModif = new JPanel();
																							          buttonModif.setPreferredSize(new Dimension(500,100));
																							          JButton buttonValiderModif = new JButton("Valider");
																							          
																							          buttonValiderModif.addActionListener(new ActionListener() {

																									      public void actionPerformed(ActionEvent ae) {
																									    	  String TextEvent = inputEvent.getText();
																									    	  String TextDateDebut = inputDateDeb.getText();
																									    	  String TextDateFin = inputFinDeb.getText();
																									    	  String TextHeureDeb = inputHeureDeb.getText();
																									    	  String TextHeureFin = inputHeureFin.getText();


																									    	  controller.AgendaC.updateEvent(idAgendaInt, TextEvent, TextDateDebut, TextDateFin, User.id_utilisateur, TextHeureDeb, TextHeureFin);
																									    	  modifPopup .dispose();
																									    	  Object connecteur = Connecteur.connecteurUL;
																											  List<List> eventA= AgendaC.consultationEvenement(User.id_utilisateur);
																											  try {
																												for(int j=0; j<eventA.size();j++) {
																													 String eventAfterU= (String) eventA.get(j).get(0);
																													 String dateDebutU= (String) eventA.get(j).get(1);
																													 String dateFinU= (String) eventA.get(j).get(2);
																													 String heureDebutU = (String) eventA.get(j).get(4);
																													 String heureFinU = (String) eventA.get(j).get(5);
																													 String moisAnU = GetDateSansJour(dateDebut);
																													 String dateJourU = GetDateJour(dateDebut);
																													 int dateJourIntU = Integer.parseInt(dateJour);
																													 String idAgendaU = (String) eventA.get(j).get(6);
																													 int idAgendaIntU = Integer.parseInt(idAgendaU);
																													 
																													
																													 
																													 if(idAgendaIntU == idAgendaInt) {
																															
																															
																															 String h = b.getText();
																															 final  String i = h;
																															 JLabel eventLabel = new JLabel(i); 
																															 int iNew = Integer.parseInt(i);
																															 JPanel jourPanel = new JPanel();
																															 jourPanel.setPreferredSize(new Dimension(300,50));
																															 JPanel eventPanel = new JPanel();
																															 eventPanel.setBackground(Color.white);
																															 eventPanel.setFont(p);
																															 eventPanel.add(jourPanel);
																															 JButton buttonEvent = new JButton();
																															 buttonEvent.setBackground(Color.blue);
																															 buttonEvent.setForeground(Color.white);
																															 buttonEvent.setPreferredSize(new Dimension(300,50));
																															 buttonEvent.setFont(p);
																															 
																															 if(moisAnSelect.equals(moisAn)) {

																																 if(dateJourInt == iNew){
																																	 //pour recharger le jpanel et changer les elements
																																	 b.removeAll(); 
																																	 b.updateUI();
																																	 b.add(eventPanel);
																																	 eventPanel.add(eventLabel);
																																	 JLabel jourText = new JLabel(h);
																																	 b.add(jourText);
																																	 eventPanel.add(jourPanel);
																																	 jourPanel.add(buttonEvent);
																																	 buttonEvent.setText(eventAfterU);
																																 }
																																
																															 }
																														}
																															
																														 
																														 else {

																														 }
																												}
																											  }catch(Exception e) {
																												  
																											  }


																									    	  
																									      }
																							          });

																							          
																						    		  modifPopup.add(titreModif);
																							          modifPopup.add(eventOpenModif);
																						    		  eventOpenModif.add(eventOpenLabelModif);
																						    		  eventOpenModif.add(inputEvent);
																						    		  modifPopup.add(dateModif);
																						    		  dateModif.add(heureDModif);
																						    		  dateModif.add(inputHeureDeb);
																						    		  dateModif.add(heureFinModif);
																						    		  dateModif.add(tempsLabel);
																						    		  dateModif.add(inputHeureFin);
																						    		  modifPopup.add(tempsModif);
																						    		  tempsModif.add(tempsLabel);
																						    		  tempsModif.add(inputDateDeb);
																						    		  tempsModif.add(tempsFinLabel);
																						    		  tempsModif.add(inputFinDeb);
																						    		  modifPopup.add(buttonModif);
																						    		  buttonModif.add(buttonValiderModif);


																						    	  
																						      }
																				    		  
																				    	  });
																				    	  
																				    	  JButton suppression = new JButton("Supprimer l'évenement");

																				    	  //suppression de l'evenement
																				    	  suppression.addActionListener(new ActionListener() {

																						      public void actionPerformed(ActionEvent ae) {
																						    	  try {
																						    		  controller.AgendaC.suppressionEvent(idAgendaInt);
																						    		  voirEvenement.dispose();

																							    	  Object connecteur = Connecteur.connecteurUL;
																									  List<List> eventA= AgendaC.consultationEvenement(User.id_utilisateur);
																									  try {
																										for(int j=0; j<eventA.size();j++) {
																											 String eventAfter= (String) eventA.get(j).get(0);
																											 String dateDebutA= (String) eventA.get(j).get(1);
																											 String dateFinA= (String) eventA.get(j).get(2);
																											 String heureDebutA = (String) eventA.get(j).get(4);
																											 String heureFin = (String) eventA.get(j).get(5);
																											 String moisAn = GetDateSansJour(dateDebut);
																											 String dateJour = GetDateJour(dateDebut);
																											 int dateJourInt = Integer.parseInt(dateJour);
																											 String idAgenda = (String) eventA.get(j).get(6);
																											 int idAgendaInt = Integer.parseInt(idAgenda);
																											
																											 JPanel jourPanel = new JPanel();
																											 jourPanel.setPreferredSize(new Dimension(300,50));
																											 JPanel eventPanel = new JPanel();
																											 eventPanel.setBackground(Color.red);
																											 String h = b.getText();
																											 final  String i = h;
																											 JLabel eventLabel = new JLabel(i); 
																											 int iNew = Integer.parseInt(i);
																											 eventPanel.setFont(p);
																											 eventPanel.add(jourPanel);
																										

																											 if(moisAnSelect.equals(moisAn)) {

																												 if(dateJourInt == iNew){

																													 b.removeAll(); 
																													 b.updateUI();
																													 b.add(eventPanel);
																													 b.add(eventLabel);
																													 JLabel jourText = new JLabel(h);
																													 b.add(jourText);
																													 eventPanel.setVisible(false);
																												 }
																												 
																											 }
																										}
																									  }catch(Exception e) {
																										  
																									  }
																										
																									} catch (Exception e2) {
																										

																									}
																								
																						    	  
																						      }
																				    		  
																				    	  });
																				    	  
																				    	  
																				    	  voirEvenement.add(titreOpened);
																				    	  voirEvenement.add(eventOpen);
																				    	  eventOpen.add(eventOpenLabel);
																				    	  voirEvenement.add(heureEvent);
																				    	  voirEvenement.add(fin);
																				    	  voirEvenement.add(modifier);
																				    	  voirEvenement.add(suppression);
																				    	  
																				      	}
																				    	  
																				 });
																				 
																			
																			 }
																			 else {

																			 }
																		 }
																		 else {
																			
																		 }
																		
																		
																	}
																			 
																	 
																	 else {

																	 }
															}
														  }catch(Exception e) {
															  
														  }
														 


												    	  
												      }
										          });

										          
									    		  modifPopup.add(titreModif);
										          modifPopup.add(eventOpenModif);
									    		  eventOpenModif.add(eventOpenLabelModif);
									    		  eventOpenModif.add(inputEvent);
									    		  modifPopup.add(dateModif);
									    		  dateModif.add(heureDModif);
									    		  dateModif.add(inputHeureDeb);
									    		  dateModif.add(heureFinModif);
									    		  dateModif.add(tempsLabel);
									    		  dateModif.add(inputHeureFin);
									    		  modifPopup.add(tempsModif);
									    		  tempsModif.add(tempsLabel);
									    		  tempsModif.add(inputDateDeb);
									    		  tempsModif.add(tempsFinLabel);
									    		  tempsModif.add(inputFinDeb);
									    		  modifPopup.add(buttonModif);
									    		  buttonModif.add(buttonValiderModif);


									    	  
									      }
							    		  
							    	  });
							    	  
							    	  JButton suppression = new JButton("Supprimer l'évenement");

							    	  //suppression de l'evenement
							    	  suppression.addActionListener(new ActionListener() {

									      public void actionPerformed(ActionEvent ae) {
									    	  try {
									    		  controller.AgendaC.suppressionEvent(idAgendaInt);
									    		
									    		  voirEvenement.dispose();

										    	  Object connecteur = Connecteur.connecteurUL;
												  List<List> eventA= AgendaC.consultationEvenement(User.id_utilisateur);
												  

													for(int j=0; j<eventA.size();j++) {
														 String eventAfter= (String) eventA.get(j).get(0);
														 String dateDebutA= (String) eventA.get(j).get(1);
														 String dateFinA= (String) eventA.get(j).get(2);
														 String heureDebutA = (String) eventA.get(j).get(4);
														 String heureFin = (String) eventA.get(j).get(5);
														 String moisAn = GetDateSansJour(dateDebut);
														 String dateJour = GetDateJour(dateDebut);
														 int dateJourInt = Integer.parseInt(dateJour);
														 String idAgenda = (String) eventA.get(j).get(6);
														 int idAgendaInt = Integer.parseInt(idAgenda);

														 JPanel jourPanel = new JPanel();
														 jourPanel.setPreferredSize(new Dimension(300,50));
														 JPanel eventPanel = new JPanel();
														 eventPanel.setBackground(Color.white);
														 String h = b.getText();
														 final  String i = h;
														 JLabel eventLabel = new JLabel(i); 
														 int iNew = Integer.parseInt(i);
														 eventPanel.setFont(p);
														 eventPanel.add(jourPanel);
														 JButton buttonEvent = new JButton();
														 buttonEvent.setBackground(Color.red);
														 buttonEvent.setForeground(Color.yellow);
														 buttonEvent.setPreferredSize(new Dimension(100,1));
														 jourPanel.setPreferredSize(new Dimension(100,1));
														 eventPanel.setPreferredSize(new Dimension(1,1));


														 buttonEvent.setFont(p);
														 

														 if(moisAnSelect.equals(moisAn)) {

															 if(dateJourInt == iNew){

																 b.add(eventPanel);
																 JLabel jourText = new JLabel(jour);
																 b.add(jourText);
																 eventPanel.add(jourPanel);
																
																 b.removeAll(); 
																 b.updateUI();
																 buttonEvent.setVisible(false);
															 }
															 else {

																
															 }
														 }
													}
															 
														 
													
												
													
												} catch (Exception e2) {
													

												}
											
									    	  
									      }
							    		  
							    	  });
							    	  
							    	  
							    	  
							    	  
							    	  voirEvenement.add(titreOpened);
							    	  voirEvenement.add(eventOpen);
							    	  eventOpen.add(eventOpenLabel);
							    	  voirEvenement.add(heureEvent);
							    	  voirEvenement.add(fin);
							    	  voirEvenement.add(modifier);
							    	  voirEvenement.add(suppression);
							    	  
							      	}
							    	  
							 });
							 
						
						 }
						 else {

						 }
					 }
					 else {
						
					 }
					
					
					 }
				}
				
			}
				catch(Exception e) {
					
				}

			  //ajout evenement pour le jour selectionné

			    b.addActionListener(new ActionListener() {

				      public void actionPerformed(ActionEvent ae) {
						  String moisSelect = (String) monthChoice.getSelectedItem();
						  String anneeSelect = (String) yearChoice.getSelectedItem();

				    	  
				    	  String jour =  b.getText();

				    	  Popup ajoutEvenement = new Popup("Ajouter un évènement", 800, 500);
				    	 	
						  ajoutEvenement.setAlwaysOnTop(true);
						  
						  TitreSecondaire titrePopup = new TitreSecondaire("Ajouter un évènement pour le "+ jour + " "+ moisSelect+" " + anneeSelect);
						  
						  UtilDateModel model = new UtilDateModel();
						  JDatePanelImpl datePanel = new JDatePanelImpl(model);
						  UtilDateModel model2 = new UtilDateModel();
						  JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
						  JDatePickerImpl datePickerDeb = new JDatePickerImpl(datePanel);
						  JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanel2);
						

						  JPanel dateFin = new JPanel();
						  dateFin.setPreferredSize(new Dimension(600, 100));

						  JPanel heureDebut = new JPanel();
						  JPanel heureFin = new JPanel();
						  JPanel evenement = new JPanel();
						  evenement.setPreferredSize(new Dimension(600, 100));
						  JPanel valider = new JPanel();
						
						  JLabel dateFinLabel = new JLabel("* Date de fin :");
					      JLabel heureDebutLabel = new JLabel("* Heure début de l'évènement"); 
					      String[]  heureItems = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
					      JComboBox<?> heureDebutSelect = new JComboBox<Object>(heureItems);

					      String[]  minItems = {"00", "15", "30", "45"};
					      JComboBox<?> minDebutSelect = new JComboBox<Object>(minItems);

					      JLabel heureFinLabel = new JLabel("* Heure fin de l'évènement"); 
					      JComboBox<?> heureFinSelect = new JComboBox<Object>(heureItems);

					      JComboBox<?> minFinSelect = new JComboBox<Object>(minItems);


					      JLabel evenementLabel = new JLabel("* Evènement");     
					      JTextArea inputArea = new JTextArea(1,15);
					      
					      JButton validerButton = new JButton("Valider");
					      JPanel obligatoirePanel = new JPanel();
					      JLabel obligatoire = new JLabel(" * Tous les champs sont obligatoires");
					      obligatoire.setForeground(Color.red);
					      Font font = new Font("Open Sans", Font.PLAIN, 24);
						  obligatoire.setFont(font);
					      valider.setPreferredSize(new Dimension(1000, 100));

					     
						  dateFin.add(dateFinLabel);
						  dateFin.add(datePickerFin);

					      
					      heureDebut.add(heureDebutLabel);
						  heureDebut.add(heureDebutSelect);
						  heureDebut.add(minDebutSelect);
						  
						  heureFin.add(heureFinLabel);
						  heureFin.add(heureFinSelect);
						  heureFin.add(minFinSelect);

						  evenement.add(evenementLabel);
						  evenement.add(inputArea);
						  obligatoirePanel.add(obligatoire);
						  valider.add(obligatoirePanel);
						  valider.add(validerButton);
						  

						  validerButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
								   
								String dateDebut = anneeSelect+"-"+month_number+"-"+jour;

								String dateFin;
								String evenement;
								Calendar cal=Calendar.getInstance();
								SimpleDateFormat moisSelect = new SimpleDateFormat("MM");
								String month_number = moisSelect.format(cal.getTime());
							    String heureDeb = (String)heureDebutSelect.getSelectedItem();
							    String minDeb = (String)minDebutSelect.getSelectedItem();
							    String heureEnd = (String)heureFinSelect.getSelectedItem();
							    String minFin = (String)minFinSelect.getSelectedItem();

									try {
										
										evenement = inputArea.getText();
										String heureDebut = heureDeb+":"+minDeb;
										String heureFinC = heureEnd+":"+minFin;
										DateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
										
										java.util.Date selectedDateFin = (java.util.Date) datePickerFin.getModel().getValue();
										dateFin = formatDate.format(selectedDateFin);
										evenement = inputArea.getText();
										if(dateFin == null) {
											dateFin = dateDebut;
										}


										controller.AgendaC.ajoutEvenement(evenement,dateDebut, dateFin, heureDebut, heureFinC );
										ajoutEvenement.dispose();
										
								    	  Object connecteur = Connecteur.connecteurUL;
										List<List> eventA= AgendaC.consultationLastEvenement(User.id_utilisateur);
										
									try {
										for(int j=0; j<eventA.size();j++) {
											 String eventAfter= (String) eventA.get(j).get(0);
											 String dateDebutA= (String) eventA.get(j).get(1);
											 String dateFinA= (String) eventA.get(j).get(2);
											 String heureDebutA = (String) eventA.get(j).get(4);
											 String heureFin = (String) eventA.get(j).get(5);
											 String moisAn = GetDateSansJour(dateDebut);
											 String dateJour = GetDateJour(dateDebut);
											 int dateJourInt = Integer.parseInt(dateJour);
											 String idAgenda = (String) eventA.get(j).get(6);
											 int idAgendaInt = Integer.parseInt(idAgenda);
											 
											

											 JPanel jourPanel = new JPanel();
											 jourPanel.setPreferredSize(new Dimension(300,50));
											 JPanel eventPanel = new JPanel();
											 eventPanel.setBackground(Color.white);
											 String h = b.getText();
											 final  String i = h;
											 JLabel eventLabel = new JLabel(i+eventAfter); 
											 int iNew = Integer.parseInt(i);
											 eventPanel.setFont(p);
											 eventPanel.add(jourPanel);
											 JButton buttonEvent = new JButton();
											 buttonEvent.setBackground(Color.blue);
											 buttonEvent.setForeground(Color.white);
											 buttonEvent.setPreferredSize(new Dimension(300,50));
											 buttonEvent.setFont(p);
											 
										
											 if(moisAnSelect.equals(moisAn)) {
													
												 if(dateJourInt == iNew){

													 b.add(eventPanel);
													 JLabel jourText = new JLabel(jour);
													 eventPanel.add(jourText);
													 eventPanel.add(jourPanel);
													 jourPanel.add(buttonEvent);
													 buttonEvent.setText(eventAfter);
													 
												 }
											 }
										}
									}catch(Exception e2) {
										
									}
									
							            


										
									} catch (Exception e2) {
										

									}
									
								}
				  			      
						  });
						

						  ajoutEvenement.add(titrePopup);
						  ajoutEvenement.add(dateFin);
						  ajoutEvenement.add(heureDebut);
						  ajoutEvenement.add(heureFin);
						  ajoutEvenement.add(evenement);
						  ajoutEvenement.add(valider);

						  


				      }
			    });
			    
		    
		    }
		    


		    repaint();
			return daysInMonth;
		    
	}

	  public static String GetDateSansJour(String date){
		    // String to be scanned to find the pattern.
		    String line = date;
		    String pattern = "((?:[0-9]){4}(?:-){1}(?:[0-9]){2})(?:(?:-){1}(?:[0-9]){2})";

		    // Create a Pattern object
		    Pattern r = Pattern.compile(pattern);

		    // Now create matcher object.
		    Matcher m = r.matcher(line);

		    if (m.find( )) {
		      return( m.group(1) );
		    }
		    else {
		      return("NO MATCH");
		    }
		}
	  
	  public static String GetDateJour(String date){
	        Matcher m = Pattern.compile("((?:[0-9]){4}(?:-){1}(?:[0-9]){2})(?:-){1}((?:[0-9]){2})").matcher(date);

	        if(m.find()) {
	            return( m.group(2) );
	        }
	        else {
	            return("NO MATCH");
	        }
	    }

	private boolean isLeap(int year) {
		// TODO Auto-generated method stub
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		      return true;
		return false;
	}

	private void buildGUI() {
		
		getAccessibleContext().setAccessibleDescription(
		        "Calendar not accessible yet. Sorry!");
		
		    setBorder(BorderFactory.createEtchedBorder());

		    setLayout(new BorderLayout());

		    JPanel tp = new JPanel();
		 



		  //barre ou il ya les deux listes d�roulantes
			BoxLayout b= new BoxLayout(tp, BoxLayout.Y_AXIS);
			tp.setBounds(500,50,500,500/16*9);
			
			tp.setBackground(new Color (229,236,246));
			Font p = new Font("open-sans", Font.PLAIN, 24);
			tp.setFont(p);
			tp.setSize(getMaximumSize());
			this.setVisible(true);
			tp.setOpaque(true);
		    tp.add(monthChoice = new JComboBox<String>());

		      monthChoice.setBackground(Color.white);
		      monthChoice.setForeground(new Color (0,63,128));
		      Font ptx = new Font("open-sans", Font.PLAIN, 18);
		      monthChoice.setFont(ptx);
		    for (int m = 0; m < months.length; m++)

		     monthChoice.addItem(months[m]);

		     monthChoice.setSelectedItem(months[date.getMonth()]);
		     Object moisSelect = monthChoice.getSelectedItem();


		     monthChoice.addActionListener(new ActionListener() {

		      public void actionPerformed(ActionEvent ae) {


		        int m = monthChoice.getSelectedIndex();

		        if (m >= 0) {
		          date.setMonth(m);
		          //ajout de reload par mois des evenement
		          
		          
		          recompute();
		          
		          
		        }
		      }
		    });
		     Object moisSelected = monthChoice.getSelectedItem();

		    monthChoice.getAccessibleContext().setAccessibleName("Months");

		    monthChoice.getAccessibleContext().setAccessibleDescription(
		        "Choose a month of the year");
		    
		    tp.add(yearChoice = new JComboBox<String>());
		    yearChoice.setBackground(Color.white);
		      yearChoice.setForeground(new Color (0,63,128));
		      Font font = new Font("open-sans", Font.PLAIN, 18);
		      yearChoice.setFont(font);
		    yearChoice.setEditable(true);
		    for (int i = date.getYear() - 5; i < date.getYear() + 5; i++)
		      yearChoice.addItem(Integer.toString(i));
		    yearChoice.setSelectedItem(Integer.toString(date.getYear()));
		    yearChoice.getSelectedItem();

		    yearChoice.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        int i = yearChoice.getSelectedIndex();
		        if (i >= 0) {
		          date.setYear(Integer.parseInt(yearChoice.getSelectedItem().toString()));
		          recompute();
		        }
		      }
		    });
		    add(BorderLayout.CENTER, tp);


		    JPanel bp = new JPanel();
			bp.setBackground(new Color (0,63,128));
		
		  
			Font pt = new Font("open-sans", Font.PLAIN, 24);
			bp.setFont(pt);
			bp.setSize(getMaximumSize());
			bp.setVisible(true);
		    bp.setLayout(new GridLayout(7, 7));
			bp.setSize(1000,1000);

		    labs = new JButton[6][7]; // ligne des jours
		    JButton lundi = new JButton("Lundi");
		    JButton mardi = new JButton("Mardi"); 
		    JButton mercredi = new JButton("Mercredi");
		    JButton jeudi = new JButton("Jeudi");
		    JButton vendredi = new JButton("Vendredi");
		    JButton samedi = new JButton("Samedi");
		    JButton dimanche = new JButton("Dimanche");
		    bp.add(lundi);
		    bp.add(mardi);
		    bp.add(mercredi);
		    bp.add(jeudi);
		    bp.add(vendredi);
		    bp.add(samedi);
		    bp.add(dimanche);
		    
		    lundi.setBackground(new Color (0,63,128));
		    mardi.setBackground(new Color (0,63,128));
		    mercredi.setBackground(new Color (0,63,128));
		    jeudi.setBackground(new Color (0,63,128));
		    vendredi.setBackground(new Color (0,63,128));
		    samedi.setBackground(new Color (0,63,128));
		    dimanche.setBackground(new Color (0,63,128));
		    
		    lundi.setForeground(Color.white);
		    mardi.setForeground(Color.white);
		    mercredi.setForeground(Color.white);
		    jeudi.setForeground(Color.white);
		    vendredi.setForeground(Color.white);
		    samedi.setForeground(Color.white);
		    dimanche.setForeground(Color.white);
		    
		    Font fontM = new Font("open-sans", Font.BOLD, 18);
			lundi.setFont(fontM);
			mardi.setFont(fontM);
			mercredi.setFont(fontM);
			jeudi.setFont(fontM);
			vendredi.setFont(fontM);
			samedi.setFont(fontM);
			dimanche.setFont(fontM);

		    
		    
		    //taille du calendrier
		    bp.setPreferredSize(new Dimension(1700, 900));


		    ActionListener dateSetter = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        String num = e.getActionCommand();
		        if (!num.equals("")) {
		          // set the current day highlighted
		          setDayActive(Integer.parseInt(num));


		          // When this becomes a Bean, you can
		          // fire some kind of DateChanged event here.
		          // Also, build a similar daySetter for day-of-week btns.
		        }
		      }

		    };



		    //Case vide aprés le mois
		   for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) {

		        bp.add(labs[i][j] = new JButton(""));
		        labs[i][j].addActionListener(dateSetter);
		        labs[i][j].setBackground(new Color(229,236,246));
		      }

		    add(BorderLayout.SOUTH, bp);

	}
	
	
	

	protected void setDayActive(int parseInt) {
		// TODO Auto-generated method stub

	}
	
	
}
