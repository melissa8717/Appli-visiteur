package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import view.agenda.Date;
import model.Connecteur;

 
public class Agenda<Spanned> extends JPanel  {

	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	public static String IDCal;
	public static String List_Event;


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
	  JPanel tp = new JPanel();
	  



	protected int recompute() {
		// TODO Auto-generated method stub
		// Blank out all
		
	
		  
		for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) 
		         labs[i][j].setText("");

		 int daysInMonth = dom[date.getMonth()];
		 if (isLeap(date.getYear()) && date.getMonth() == 1)
		      daysInMonth++;
		      final int jourTaille = daysInMonth;
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
		    
		    Object connecteur = Connecteur.connecteurUL;
			List<List> List_CE= AgendaC.consultationEvenement(User.id_utilisateur);
			 

		 // Case des dates jour du mois.
		
		    for ( int  i = 1; i <= daysInMonth; i++) {

		    	final int  iNew = i;
		    	int[] iArray  = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		  
		        JButton b = labs[(date.firstDayOfTheMonth() + i - 2) / 7][(date.firstDayOfTheMonth() + i - 2) % 7];
		        String moisSelect = (String) monthChoice.getSelectedItem();
				String anneeSelect = (String) yearChoice.getSelectedItem();
			    java.util.Date cal=Calendar.getInstance().getTime();
				SimpleDateFormat moisSelected = new SimpleDateFormat("MM");
				String month_number = moisSelected.format(cal.getTime());
				String jour =  b.getText();
				SimpleDateFormat jourFormat = new SimpleDateFormat("dd");
				String jourF = jourFormat.format(cal.getTime());
				String debut = anneeSelect+"-"+month_number+"-"+jourF;
				int moisInt = Integer.parseInt(month_number);
				
				// tout ceci pour avoir les event du mois précédent
				String moisAnSelect = anneeSelect+"-"+month_number;
				Font p = new Font("open-sans", Font.PLAIN, 18);
		        int moisSelectPrev = moisInt - 1;
		        int moisSelectPrevZero =0+moisSelectPrev;
	            String  datePrev = anneeSelect+"-"+0+moisSelectPrevZero;


		        int size = labs.length-1;
		        int sizeTotal = labs.length;
		        
 	            labs[5][size].setBackground(new Color(62,101,146));
 	            labs[5][sizeTotal].setBackground(Color.cyan);
		        labs[5][size].setText("0");
 	            labs[5][size].addActionListener(e ->{
		        	JPanel moisCal = new JPanel();
		        	String moisAnPrev = null;
		            if(moisSelectPrev == 12) {
			        	String moisPrevStr = Integer.toString(moisSelectPrevZero);
					    moisAnPrev = anneeSelect+"-"+01;
			    		String moisLettre = GetMoisLettreToDate(moisPrevStr);
		        		JButton mois = new JButton(moisLettre);
		        		moisCal.add(mois);


			        }
			        else {
				        String moisPrevStr = Integer.toString(moisSelectPrevZero);
				        moisAnPrev = anneeSelect+"-"+0+moisPrevStr;
			    		String moisLettre = GetMoisLettreToDate(moisPrevStr);
		        		JButton mois = new JButton(moisLettre);
		        		moisCal.add(mois);
		        }
		        	
		        	Object moisSelectedObj = monthChoice.getSelectedItem();
		    		String moisSrt = String.valueOf(moisSelectedObj);
		    		Font h3 = new Font("open-sans", Font.BOLD, 22);
		    		moisCal.setFont(h3);
		    		moisCal.setBackground(new Color(62,101,146));
		    		this.add(moisCal);
	        	

						b.removeAll(); 
						b.updateUI();
						Object connecteur1 = Connecteur.connecteurUL;

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
								 JLabel eventLabel = new JLabel(Integer.toString(iNew)+event); 
								 eventPanel.setFont(p);
								 eventPanel.add(jourPanel);
								 JButton buttonEvent = new JButton();
								 buttonEvent.setBackground(Color.blue);
								 buttonEvent.setForeground(Color.white);
								 buttonEvent.setPreferredSize(new Dimension(200,50));
								 buttonEvent.setFont(p);
								 JButton countevent = new JButton();
								// for( int n=0; n<=jourTaille; n++) {
									 
									
								    if(datePrev.equals(moisAn)) {
									   if(dateJourInt == iNew){
										List<Integer> elements=new ArrayList<>();
										elements.add(dateJourInt);
										JLabel jourText = new JLabel(jour);
										eventPanel.add(jourPanel);
										countevent.setBackground(Color.blue);
										countevent.setForeground(Color.white);
										countevent.setPreferredSize(new Dimension(200,50));
										countevent.setFont(p);
										b.removeAll(); 
										b.updateUI();
										b.add(eventPanel);
										countevent.setText("Voir détail");
										jourPanel.add(countevent);
										countevent.addActionListener(new ActionListener() {
								
										public void actionPerformed(ActionEvent ae) {
											System.out.println("i am fucking in");
											System.out.println("mois evn"+datePrev+moisAn);
											 System.out.println("jour event"+dateJourInt+iNew);
											if(datePrev.equals(moisAn)) {

													if(dateJourInt == iNew){

											    	  Popup fewEvent = new Popup("Evenements multiples", 1000,1000);

											    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènement(s) du "+ jour + " "+ moisSelect+" " + anneeSelect);
											    	  fewEvent.add(titreOpened );
											          JPanel eventPanel[]= {new JPanel()};
											          
											          String heureListe[]= {"07:00","07:15","07:30","07:45","08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45","12:00"};
											          JLabel heureLanelNext[] = {new JLabel("12:00") ,new JLabel("12:15"),new JLabel("12:30"),new JLabel("12:45"),new JLabel("13:00"),new JLabel("13:15"),new JLabel("13:30"),new JLabel("13:45"),new JLabel("14:00"),new JLabel("14:15")
											        		  ,new JLabel("14:30"),new JLabel("14:45"),new JLabel("15:00"),new JLabel("15:15"),new JLabel("15:30"),new JLabel("15:45"),new JLabel("16:00"),new JLabel("16:15"),new JLabel("16:30"),new JLabel("16:45"),new JLabel("17:00"),new JLabel("17:15"),new JLabel("17:30"),new JLabel("17:45"),new JLabel("18:00")
											        		  ,new JLabel("18:15"),new JLabel("18:30"),new JLabel("18:45"),new JLabel("19:00"),new JLabel("19:15"),new JLabel("19:30"),new JLabel("19:45"),new JLabel("20:00"),new JLabel("20:15"),new JLabel("20:30"),new JLabel("21:00"),new JLabel("21:15"),new JLabel("21:30"),new JLabel("21:45"),new JLabel("22:00")
											        		  ,new JLabel("22:15"),new JLabel("22:30"),new JLabel("22:45"),new JLabel("23:00")};
											          
											          JLabel eventLabel[] = {new JLabel()};
											    	  JPanel evenement[]= {new JPanel()};
											    	  JLabel evenementLabel[]= {new JLabel(event)};
											    	  JButton textEvent[] = {new JButton()};


											          for(int p = 0;p<65;p++) {
											        	  evenement = ajoutemoi(evenement, new JPanel());
											          }
											          
											         
											          Object[][] data = {  
											        	{"","07:00", "", "",""},
											            {"","07:15","","",""},
											            {"","07:30","","",""},
											            {"","07:45","","",""},
											            {"","08:00","","",""},
											            {"","08:15","","",""},
											            {"","08:30","","",""},
											            {"","08:45","","",""},
											            {"","09:00","","",""},
											            {"","09:15","","",""},
											            {"","09:30","","",""},
											            {"","09:45","","",""},
											            {"","10:00","","",""},
											            {"","10:15","","",""},
											            {"","10:30","","",""},
											            {"","10:45","","",""},
											            {"","11:00","","",""},
											            {"","11:15","","",""},
											            {"","11:30","","",""},
											            {"","11:45","","",""},
											            {"","12:00","","",""},
											            {"","12:00","","",""},
											            {"","12:15","","",""},
											            {"","12:30","","",""},
											            {"","12:45","","",""},
											            {"","13:00","","",""},
											            {"","13:15","","",""},
											            {"","13:30","","",""},
											            {"","13:45","","",""},
											            {"","14:00","","",""},
											            {"","14:15","","",""},
											            {"","14:30","","",""},
											            {"","14:45","","",""},
											            {"","15:00","","",""},
											            {"","15:15","","",""},
											            {"","15:30","","",""},
											            {"","15:45","","",""},
											            {"","16:00","","",""},
											            {"","16:15","","",""},
											            {"","16:30","","",""},
											            {"","16:45","","",""},
											            {"","17:00","","",""},
											            {"","17:15","","",""},
											            {"","17:30","","",""},
											            {"","17:45","","",""},
											            {"","18:00","","",""},
											            {"","18:15","","",""},
											            {"","18:30","","",""},
											            {"","18:45","","",""},
											            {"","19:00","","",""},
											            {"","19:15","","",""},
											            {"","19:30","","",""},
											            {"","19:45","","",""},
											            {"","20:00","","",""},
											            {"","20:15","","",""},
											            {"","20:30","","",""},
											            {"","20:45","","",""},
											            {"","21:00","","",""},
											            {"","21:15","","",""},
											            {"","21:30","","",""},
											            {"","21:45","","",""},
											            {"","22:00","","",""},
											            {"","22:15","","",""},
											            {"","22:30","","",""},
											            {"","22:45","","",""},
											            {"","23:00","","",""},
											            {"","23:15","","",""},
											            {"","23:30","","",""},
											            {"","23:45","","",""},

											          };
											          
											         

											          //Les titres des colonnes
											          String  title[] = {"ID","Heure de début", "Evenement", "Heure de fin", "Voir l'évenement"};
											          JTable tableau = new JTable(data, title);
											          //Nous ajoutons notre tableau à notre contentPane dans un scroll
											          //Sinon les titres des colonnes ne s'afficheront pas !
											          
											          tableau.setRowHeight(20);
											          tableau.getTableHeader().setBackground(new Color (0,63,128));
											          tableau.getTableHeader().setForeground(Color.white);
											          int ligneS = tableau.getSelectedRow();//Si tu veut la cellule selectionnée, sinon une autre valeur
											          int colonneS = tableau.getSelectedColumn();//Si tu veut la cellule selectionnée, sinon une autre valeur
											          Object cellule = tableau.getValueAt(4,0);
											          Object statusValue = tableau.getModel().getValueAt(1, 0);
											     
										        	  

										        	  int ligne = 0;

											          while(ligne < 69) {

											        	  Object col = tableau.getValueAt(ligne,1);
											        	  ligne ++;
											        	  String colString = String.valueOf(col);
												          if(colString.equals(heureDebut)) {

												        	  for (int e=0; e<69;e++) {
												        		  tableau.setValueAt(idAgenda, ligne-1,0 );
												        		  tableau.setValueAt(event, ligne-1,3 );
												        		  IDCal = (String) tableau.getValueAt(ligne-1,0 );
													        		
												        	  tableau.setDefaultRenderer(JButton.class, new ButtonRenderer());
												        	  tableau.getColumn("Voir l'évenement").setCellEditor(new ButtonEditor());
													
												        	  }//fin du for
												          }//fin du if
												       
										        	  }//fin du while
											      
											          fewEvent.getContentPane().add(new JScrollPane(tableau));

												}//fin du if jour dans action listener
											}//fin if du mois dans action listener
													  

					/*@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}*/
										}

										public void actionPerformed1(ActionEvent e) {
											// TODO Auto-generated method stub
											
										}

									});// fin count event

								}// fin du if jour
						
							} // fin if mois
						// }//fin for event prev
						}// fin for event*/
				//	}//fin action labs

					/*@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					} */ 
			});// fin event labs
		   
					

		      b.setText(Integer.toString(i));
		      b.getText();
			  b.setFont(p);
			  b.setBackground(Color.white);
			  b.setForeground(new Color (0,63,128));


			
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

				 	 int countEvent = controller.AgendaC.countEvenement(User.id_utilisateur, dateDebut);

					 if(moisAnSelect.equals(moisAn)) {
						 if(dateJourInt == i){


							List<Integer> elements=new ArrayList<>();
							elements.add(dateJourInt);
							JLabel jourText = new JLabel(jour);
							eventPanel.add(jourPanel);
							countevent.setBackground(Color.blue);
							countevent.setForeground(Color.white);
							countevent.setPreferredSize(new Dimension(200,50));
							countevent.setFont(p);
							b.removeAll(); 
							b.updateUI();
							b.add(eventPanel);
							countevent.setText(" Voir détail");
							jourPanel.add(countevent);


									 
								 //bouton du few event
								 countevent.addActionListener(new ActionListener() {


								      public void actionPerformed(ActionEvent ae) {
								
												  if(moisAnSelect.equals(moisAn)) {

														 if(dateJourInt == iNew){
															 

								    	  Popup fewEvent = new Popup("Evenements multiples", 1000,1000);

								    	  TitreSecondaire titreOpened = new TitreSecondaire("Evènement(s) du "+ jour + " "+ moisSelect+" " + anneeSelect);
								    	  fewEvent.add(titreOpened );
								          JPanel eventPanel[]= {new JPanel()};
								          
								          String heureListe[]= {"07:00","07:15","07:30","07:45","08:00","08:15","08:30","08:45","09:00","09:15","09:30","09:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45","12:00"};
								          JLabel heureLanelNext[] = {new JLabel("12:00") ,new JLabel("12:15"),new JLabel("12:30"),new JLabel("12:45"),new JLabel("13:00"),new JLabel("13:15"),new JLabel("13:30"),new JLabel("13:45"),new JLabel("14:00"),new JLabel("14:15")
								        		  ,new JLabel("14:30"),new JLabel("14:45"),new JLabel("15:00"),new JLabel("15:15"),new JLabel("15:30"),new JLabel("15:45"),new JLabel("16:00"),new JLabel("16:15"),new JLabel("16:30"),new JLabel("16:45"),new JLabel("17:00"),new JLabel("17:15"),new JLabel("17:30"),new JLabel("17:45"),new JLabel("18:00")
								        		  ,new JLabel("18:15"),new JLabel("18:30"),new JLabel("18:45"),new JLabel("19:00"),new JLabel("19:15"),new JLabel("19:30"),new JLabel("19:45"),new JLabel("20:00"),new JLabel("20:15"),new JLabel("20:30"),new JLabel("21:00"),new JLabel("21:15"),new JLabel("21:30"),new JLabel("21:45"),new JLabel("22:00")
								        		  ,new JLabel("22:15"),new JLabel("22:30"),new JLabel("22:45"),new JLabel("23:00")};
								          
								          JLabel eventLabel[] = {new JLabel()};
								    	  JPanel evenement[]= {new JPanel()};
								    	  JLabel evenementLabel[]= {new JLabel(event)};
								    	  JButton textEvent[] = {new JButton()};


								          for(int p = 0;p<65;p++) {
								        	  evenement = ajoutemoi(evenement, new JPanel());
								          }
								          
								         
								          Object[][] data = {  
								        	{"","07:00", "", "",""},
								            {"","07:15","","",""},
								            {"","07:30","","",""},
								            {"","07:45","","",""},
								            {"","08:00","","",""},
								            {"","08:15","","",""},
								            {"","08:30","","",""},
								            {"","08:45","","",""},
								            {"","09:00","","",""},
								            {"","09:15","","",""},
								            {"","09:30","","",""},
								            {"","09:45","","",""},
								            {"","10:00","","",""},
								            {"","10:15","","",""},
								            {"","10:30","","",""},
								            {"","10:45","","",""},
								            {"","11:00","","",""},
								            {"","11:15","","",""},
								            {"","11:30","","",""},
								            {"","11:45","","",""},
								            {"","12:00","","",""},
								            {"","12:00","","",""},
								            {"","12:15","","",""},
								            {"","12:30","","",""},
								            {"","12:45","","",""},
								            {"","13:00","","",""},
								            {"","13:15","","",""},
								            {"","13:30","","",""},
								            {"","13:45","","",""},
								            {"","14:00","","",""},
								            {"","14:15","","",""},
								            {"","14:30","","",""},
								            {"","14:45","","",""},
								            {"","15:00","","",""},
								            {"","15:15","","",""},
								            {"","15:30","","",""},
								            {"","15:45","","",""},
								            {"","16:00","","",""},
								            {"","16:15","","",""},
								            {"","16:30","","",""},
								            {"","16:45","","",""},
								            {"","17:00","","",""},
								            {"","17:15","","",""},
								            {"","17:30","","",""},
								            {"","17:45","","",""},
								            {"","18:00","","",""},
								            {"","18:15","","",""},
								            {"","18:30","","",""},
								            {"","18:45","","",""},
								            {"","19:00","","",""},
								            {"","19:15","","",""},
								            {"","19:30","","",""},
								            {"","19:45","","",""},
								            {"","20:00","","",""},
								            {"","20:15","","",""},
								            {"","20:30","","",""},
								            {"","20:45","","",""},
								            {"","21:00","","",""},
								            {"","21:15","","",""},
								            {"","21:30","","",""},
								            {"","21:45","","",""},
								            {"","22:00","","",""},
								            {"","22:15","","",""},
								            {"","22:30","","",""},
								            {"","22:45","","",""},
								            {"","23:00","","",""},
								            {"","23:15","","",""},
								            {"","23:30","","",""},
								            {"","23:45","","",""},

								          };
								          
								         

								          //Les titres des colonnes
								          String  title[] = {"ID","Heure de début", "Evenement", "Heure de fin", "Voir l'évenement"};
								          JTable tableau = new JTable(data, title);
								          //Nous ajoutons notre tableau à notre contentPane dans un scroll
								          //Sinon les titres des colonnes ne s'afficheront pas !
								          
								          tableau.setRowHeight(20);
								          tableau.getTableHeader().setBackground(new Color (0,63,128));
								          tableau.getTableHeader().setForeground(Color.white);
								          int ligneS = tableau.getSelectedRow();//Si tu veut la cellule selectionnée, sinon une autre valeur
								          int colonneS = tableau.getSelectedColumn();//Si tu veut la cellule selectionnée, sinon une autre valeur
								          Object cellule = tableau.getValueAt(4,0);
								          Object statusValue = tableau.getModel().getValueAt(1, 0);
								     
							        	  

							        	  int ligne = 0;

								          while(ligne < 69) {

								        	  Object col = tableau.getValueAt(ligne,1);
								        	  ligne ++;
								        	  String colString = String.valueOf(col);
									          if(colString.equals(heureDebut)) {

									        	  for (int e=0; e<69;e++) {
									        		  tableau.setValueAt(idAgenda, ligne-1,0 );
									        		  tableau.setValueAt(event, ligne-1,3 );
									        		  IDCal = (String) tableau.getValueAt(ligne-1,0 );
										        		
									        	  tableau.setDefaultRenderer(JButton.class, new ButtonRenderer());
									        	  tableau.getColumn("Voir l'évenement").setCellEditor(new ButtonEditor());
										
									        	  }
									          }
									          else {
									          }

								        	 
							        	  }
								      
								          fewEvent.getContentPane().add(new JScrollPane(tableau));

													}
												}
										   
								      }

								 
								 });

					 }
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
		   
				}// fin for cases jour	
		    repaint();
			return daysInMonth;
				
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
	  
	
	  public static String GetMoisLettreToDate(String str){
		    switch (str) {
		      case "1":
		        return "Janvier";
		      case "2":
		        return "Février";
		      case "3":
		        return "Mars";
		      case "4":
		        return "Avril";
		      case "5":
		        return "Mai";
		      case "6":
		        return "Juin";
		      case "7":
		        return "Juillet";
		      case "8":
		        return "Aout";
		      case "9":
		        return "Septembre";
		      case "10":
		        return "Octobre";
		      case "11":
		        return "Novembre";
		      case "12":
		        return "Décembre";
		      default: 
		        return str;
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

		  //barre ou il ya les deux listes d�roulantes
			BoxLayout box= new BoxLayout(tp, BoxLayout.Y_AXIS);
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
		     String moisSrt = String.valueOf(moisSelect);
			 JLabel mois = new JLabel(moisSrt);
			 tp.add(mois);


		     monthChoice.addActionListener(new ActionListener() {

		      public void actionPerformed(ActionEvent ae) {


		        int m = monthChoice.getSelectedIndex();
		        
		        Object connecteur = Connecteur.connecteurUL;
				List<List> eventA= AgendaC.consultationEvenement(User.id_utilisateur);
				
		     /*b.removeAll(); 
				b.updateUI();*/
		        

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
		        if (!num.equals("") ) {
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
