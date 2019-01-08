package view;
import java.awt.*;
import java.awt.List;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalComboBoxButton;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.util.*;
import view.agenda.Date;

 
public class Agenda extends JPanel  {

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
		
		   
		    for ( int i = 1; i <= daysInMonth; i++) {
		  
		      JButton b = labs[(date.firstDayOfTheMonth() + i - 2) / 7][(date.firstDayOfTheMonth() + i - 2) % 7];


		      b.setText(Integer.toString(i));
		      b.getText();
			  Font p = new Font("open-sans", Font.PLAIN, 18);
			  b.setFont(p);
			  b.setBackground(Color.white);
			  b.setForeground(new Color (0,63,128));

			  b.setSize(getMaximumSize());

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
						

						  
						  JPanel heureDebut = new JPanel();
						  JPanel heureFin = new JPanel();
						  JPanel evenement = new JPanel();
						  evenement.setPreferredSize(new Dimension(600, 100));

						  JPanel valider = new JPanel();
						

					      JLabel heureDebutLabel = new JLabel("Heure début de l'évènement"); 
					      String[]  heureItems = {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
					      JComboBox<?> heureDebutSelect = new JComboBox<Object>(heureItems);

					      String[]  minItems = {"00", "15", "30", "45"};
					      JComboBox<?> minDebutSelect = new JComboBox<Object>(minItems);

					      JLabel heureFinLabel = new JLabel("Heure fin de l'évènement"); 
					      JComboBox<?> heureFinSelect = new JComboBox<Object>(heureItems);

					      JComboBox<?> minFinSelect = new JComboBox<Object>(minItems);


					      JLabel evenementLabel = new JLabel("Evènement");     
					      JTextArea inputArea = new JTextArea(5,25);
					      
					      JButton validerButton = new JButton("Valider");
					      valider.setPreferredSize(new Dimension(800, 100));

						  heureDebut.add(heureDebutLabel);
						  heureDebut.add(heureDebutSelect);
						  heureDebut.add(minDebutSelect);


						  heureFin.add(heureFinLabel);
						  heureFin.add(heureFinSelect);
						  heureFin.add(minFinSelect);


						  evenement.add(evenementLabel);
						  evenement.add(inputArea);

						  valider.add(validerButton);
						  

						  validerButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
								   
								String evenement;
								Calendar cal=Calendar.getInstance();
								SimpleDateFormat moisSelect = new SimpleDateFormat("MM");
								String month_number = moisSelect.format(cal.getTime());
								String dateDebut = anneeSelect+"-"+month_number+"-"+jour;
								String dateFin = dateDebut;
							    String heureDeb = (String)heureDebutSelect.getSelectedItem();
							    String minDeb = (String)minDebutSelect.getSelectedItem();

							    String heureEnd = (String)heureFinSelect.getSelectedItem();
							    String minFin = (String)minFinSelect.getSelectedItem();


								


									
								
									try {
										
									
										evenement = inputArea.getText();
										String heureDebut = heureDeb+":"+minDeb;
										String heureFinC = heureEnd+":"+minFin;


										controller.AgendaC.ajoutEvenement(evenement,dateDebut, dateFin, heureDebut, heureFinC );
										System.out.println("evenement ajouté date deb"+dateDebut+ dateFin+ heureDebut+ "date fin" +heureFinC+ "evenement"+ evenement);
											
		
										 JPanel messageSucces = new AlertSuccess("Evenement ajouté correctement !");
										 ajoutEvenement.add(messageSucces);


										
									} catch (Exception e2) {
										

									}
									
								}
				  			      
						  });
						

						  ajoutEvenement.add(titrePopup);
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
		          recompute();
		        }
		      }
		    });
		     Object moisSelected = monthChoice.getSelectedItem();
		     System.out.println(moisSelected);

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
		    
		    //évenement sur plusieurs jours
		    JButton ajouter = new JButton("Ajouter un évènement");
		    ajouter.setFont(new Font("Arial", Font.PLAIN, 22));
		    ajouter.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent ae) {
			    	  Popup ajoutEvenement = new Popup("Ajouter un évènement", 500, 500);
					  ajoutEvenement.setAlwaysOnTop(true);
					  
					  TitreSecondaire titrePopup = new TitreSecondaire("Ajouter un évènement");
					  
					  UtilDateModel model = new UtilDateModel();
<<<<<<< HEAD
					  JDatePanelImpl datePanelDeb = new JDatePanelImpl(model);
					  JDatePickerImpl datePickerDeb = new JDatePickerImpl(datePanelDeb);
					  JDatePanelImpl datePanelFin = new JDatePanelImpl(model);
					  JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin);
=======
					  JDatePanelImpl datePanel = new JDatePanelImpl(model);
					  UtilDateModel model2 = new UtilDateModel();
					  JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
					  JDatePickerImpl datePickerDeb = new JDatePickerImpl(datePanel);
					  JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanel2);
>>>>>>> 29d6aa0cbeb5268ce6474d1e5215220ad5ac3eeb

					  
					  JPanel dateDebut = new JPanel();
					  JPanel dateFin = new JPanel();
					  JPanel evenement = new JPanel();
					  evenement.setPreferredSize(new Dimension(600, 100));

					  JPanel valider = new JPanel();
					

				      JLabel dateDebutLabel = new JLabel("Date début de l'évènement");   
				      JLabel dateFinLabel = new JLabel("Date fin de l'évènement"); 
				      JLabel evenementLabel = new JLabel("Evènement");     
				      JTextArea inputArea = new JTextArea(5,25);
				      
				      JButton validerButton = new JButton("Valider");

					  dateDebut.add(dateDebutLabel);
					  dateDebut.add(datePickerDeb);
					  dateFin.add(dateFinLabel);
					  dateFin.add(datePickerFin);
					  evenement.add(evenementLabel);
					  evenement.add(inputArea);

					  valider.add(validerButton);
<<<<<<< HEAD
					/*  validerButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String dateDebut;
								String dateFin;
								String evenement;
								if(controller.Agenda.ajoutEvenement(evenement, dateDebut, dateFin)) {
									ajoutEvenement.dispose();

								}
							}
						});*/
=======
					  
					  validerButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String evenement;

								String dateDebut;
								String dateFin;
								String heureDebut = null;
								String heureFin = null;

								
							
								try {
									DateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");

									java.util.Date selectedDateDeb = (java.util.Date) datePickerDeb.getModel().getValue();
									dateDebut = formatDate.format(selectedDateDeb);
									java.util.Date selectedDateFin = (java.util.Date) datePickerFin.getModel().getValue();
									dateFin = formatDate.format(selectedDateFin);
									evenement = inputArea.getText();

									controller.AgendaC.ajoutEvenement(evenement,dateDebut, dateFin, heureDebut, heureFin );
									 System.out.println("evenement ajouté date deb"+ dateDebut+ "date fin" +dateFin+ "evenement"+ evenement);
										
	
									 JPanel messageSucces = new AlertSuccess("Evenement ajouté correctement !");
									 ajoutEvenement.add(messageSucces);


									
								} catch (Exception e2) {
									

								}
								
							}
			  			      
					  });
>>>>>>> 29d6aa0cbeb5268ce6474d1e5215220ad5ac3eeb
					

					  ajoutEvenement.add(titrePopup);
					  ajoutEvenement.add(dateDebut);
					  ajoutEvenement.add(dateFin);
					  ajoutEvenement.add(evenement);
					  ajoutEvenement.add(valider);
					  


			      }
			      
		    });
		   

		    tp.add(ajouter);
		    

		   


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