package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import view.agenda.Date;
import model.Connecteur;
import controller.AgendaC;

 
public class Agenda<Spanned> extends JPanel  {

	/**
         * 
         */
	private static final long serialVersionUID = 1L;
	public static String IDCal;
	public static String List_Event;
	public static String IdRole;



	public Agenda() {
		TitrePrincipale bienvenue = new TitrePrincipale("Agenda");
		
		
		
		
		this.add(bienvenue);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month= Calendar.getInstance().get(Calendar.MONTH);
        
        
        JPanel main=new JPanel();
        
		Dimension sizeEcran= Toolkit.getDefaultToolkit().getScreenSize();
		int height=sizeEcran.height-150;
        int width=sizeEcran.width;
        main.setPreferredSize(new Dimension(width, height));
        Button MoisPrec= new Button("Mois précedent");
        Button MoisSuiv= new Button ("Mois suivant");
        Button AjoutEventButton=new Button("Ajouter un évènement");
        AjoutEventButton.addActionListener(FormulairePopup());
        JPanel[] Temp= {new JPanel()};
        JPanel[] JourSemaine={new JPanel()};
        String[] ListeNomJour= {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche"};
        List<String> YearList=new ArrayList<>();
        for(int i=year-3;i<year+2;i++) {
        	YearList.add(Integer.toString(i));
        }
        String [] ListeAnnee = new String[YearList.size()];
        ListeAnnee = YearList.toArray(ListeAnnee);

        JComboBox<String> MonthSelect = new JComboBox<>(months);
        JComboBox<String> YearSelect= new JComboBox<>(ListeAnnee);
        MonthSelect.setSelectedIndex(month);
        YearSelect.setSelectedIndex(3);
        
        JPanel Selection=new JPanel();
        Selection.setPreferredSize(new Dimension(width-50, 100));
        Selection.add(MoisPrec);
        Selection.add(MonthSelect);
        Selection.add(YearSelect);
        Selection.add(MoisSuiv);
        Selection.add(AjoutEventButton);
        Selection.setBackground(Color.WHITE);
        main.add(Selection);
        
        
        for(int i = 1;i<7;i++) {
        	JourSemaine = ajoutemoi(JourSemaine, new JPanel());
        }
        for(int i=0;i<JourSemaine.length;i++) {
        	JLabel NomJour=new JLabel(ListeNomJour[i]);
        	NomJour.setForeground(Color.white);
        	JourSemaine[i].add(NomJour);
        	JourSemaine[i].setPreferredSize(new Dimension(width/8, 50));
        	JourSemaine[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	JourSemaine[i].setBackground(new Color(0,63,128));
        	
        	main.add(JourSemaine[i]);
        }
        for(int i = 1;i<50;i++) {
        	Temp = ajoutemoi(Temp, new JPanel());
        }
        
        final JPanel[] SeptPanel=Temp;
        
        for (int i=0; i<getFirstDateOfCurrentMonth(year,month).getDay()-1;i++) {
        	SeptPanel[i].setPreferredSize(new Dimension(width/8, 50));
        	SeptPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	main.add(SeptPanel[i]);
        	
        }
        JLabel[] TempLabel= {new JLabel()};
        
        for(int i = 1;i<SeptPanel.length+1;i++) {
        	TempLabel = ajoutemoiLabel(TempLabel, new JLabel());
        }
        final JLabel[] jourDansLeMois= TempLabel;
        
        for (int i = 0; i < SeptPanel.length; i++) {
        	SeptPanel[i].setPreferredSize(new Dimension(width/8, 50));
			SeptPanel[i].add(jourDansLeMois[i]);
			SeptPanel[i].setBackground(Color.WHITE);
			main.add(SeptPanel[i]);
        }
        
        
        ChangeMonth(SeptPanel,jourDansLeMois,month,year);
        YearSelect.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex();
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        	ChangeMonth(SeptPanel,jourDansLeMois,MoisChoisie,YearChoisie);
        	if(MoisChoisie==11 && YearChoisie==year+1) {
        		MoisSuiv.setEnabled(false);
        	}else {
        		MoisSuiv.setEnabled(true);
        	}
        	if(MoisChoisie==0 && YearChoisie==year-3) {
        		MoisPrec.setEnabled(false);
        	}else {
        		MoisPrec.setEnabled(true);
        	}
        	
		    });
        MonthSelect.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex();
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        	ChangeMonth(SeptPanel,jourDansLeMois,MoisChoisie,YearChoisie);
        	if(MoisChoisie==11 && YearChoisie==year+1) {
        		MoisSuiv.setEnabled(false);
        	}else {
        		MoisSuiv.setEnabled(true);
        	}
        	if(MoisChoisie==0 && YearChoisie==year-3) {
        		MoisPrec.setEnabled(false);
        	}else {
        		MoisPrec.setEnabled(true);
        	}
        	
		    });
        MoisPrec.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex()-1;
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        	MoisSuiv.setEnabled(true);
        	if(MoisChoisie==0 && YearChoisie==year-3) {
        		MoisPrec.setEnabled(false);
        	}else {
        		MoisPrec.setEnabled(true);
        	}
        	if(MoisChoisie==-1) {
        		MoisChoisie=11;
        		YearChoisie=YearChoisie-1;
        		YearSelect.setSelectedItem(Integer.toString(YearChoisie));
        	}
        	MonthSelect.setSelectedIndex(MoisChoisie);
        	
        	ChangeMonth(SeptPanel,jourDansLeMois,MoisChoisie,YearChoisie);
        });
        MoisSuiv.addActionListener(e -> {
        	int MoisChoisie=MonthSelect.getSelectedIndex()+1;
        	int YearChoisie=Integer.parseInt((String) YearSelect.getSelectedItem());
        	MoisPrec.setEnabled(true);
        	if(MoisChoisie==11 && YearChoisie==year+1) {
        		MoisSuiv.setEnabled(false);
        	}else {
        		MoisSuiv.setEnabled(true);
        	}
        	if(MoisChoisie==12) {
        		MoisChoisie=0;
        		YearChoisie=YearChoisie+1;
        		YearSelect.setSelectedItem(Integer.toString(YearChoisie));
        	}
        	MonthSelect.setSelectedIndex(MoisChoisie);
        	ChangeMonth(SeptPanel,jourDansLeMois,MoisChoisie,YearChoisie);
        });
        
        

        main.setBackground(Color.WHITE);
		this.add(main);
		
		
	}
	public void ChangeMonth(JPanel[] SeptPanel,JLabel[] jourDansLeMois,int month,int year) {
		
		List<List> EventDuMois=AgendaC.consultationEvenementMois(User.id_utilisateur,month+1,year);
		


		

		YearMonth yearMonthObject = YearMonth.of(year, month+1);
        int NumberDaysInMonth = yearMonthObject.lengthOfMonth();
        int numJour=getFirstDateOfCurrentMonth(year,month).getDay();
        if(numJour==0) {
        	numJour=7;
        }
        for (int i = 0; i < SeptPanel.length; i++) {
        	SeptPanel[i].setBackground(Color.WHITE);
        	SeptPanel[i].removeMouseListener(givePopup(Integer.toString(i-numJour+2),month,year));
        	
        	    for( MouseListener al : SeptPanel[i].getMouseListeners() ) {
        	    	SeptPanel[i].removeMouseListener( al );
        	    }
        	
        	if(i>numJour-2 && i<NumberDaysInMonth+numJour-1) {
        		jourDansLeMois[i].setText(Integer.toString(i-numJour+2));
        		
        	}else {
        		jourDansLeMois[i].setText("");
        	}

        	if(i<NumberDaysInMonth+numJour-1) {
        		SeptPanel[i].setBorder(BorderFactory.createLineBorder(Color.black));
        	}else {
        		SeptPanel[i].setBorder(null);
        	}
        	
		}
        if(EventDuMois.size()>0) {
        	List<String> ListJourAvecEvent=new ArrayList<>();
        	 for(int j=0;j<EventDuMois.size();j++) {
         		String DateDebut=(String) EventDuMois.get(j).get(1);
         		java.util.Date date=null;
         		try {
         			date=new SimpleDateFormat("yyyy-MM-dd").parse(DateDebut);
         		} catch (ParseException e) {
         			e.printStackTrace();
         		}
         		SimpleDateFormat formater = new SimpleDateFormat("dd");
         		String JourEvent=formater.format(date);
         		if (!ListJourAvecEvent.contains(JourEvent)) {
         			//On évite d'ajouter plusieurs MouseListener à une seule journée, évitant donc plusieurs popup pour le jour 
         			ListJourAvecEvent.add(JourEvent);
         		}	
         	}for(int j=0;j<ListJourAvecEvent.size();j++) {
         		String JourEvent=ListJourAvecEvent.get(j);
         		SeptPanel[Integer.parseInt(JourEvent)+numJour-2].setBackground(Color.GREEN);
         		SeptPanel[Integer.parseInt(JourEvent)+numJour-2].addMouseListener(givePopup(JourEvent,month,year));
         	}
        }
       
    		
	}
	String[] months = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Decembre"};
	String[] quartDheur= {"00","15","30","45"};
	
	
	
	
	public ActionListener FormulairePopup() {
		ActionListener Form=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int widthPopup=900;
		    	int HeightPopup=500;
				Popup popup=new Popup("Ajouts d'évènements",widthPopup,HeightPopup);
				List<String> HorlogeList=new ArrayList<>();
				for (int i=6;i<18;i++) {
					for(int j=0;j<quartDheur.length;j++) {
						if(Integer.toString(i).length()==1) {
							HorlogeList.add("0"+Integer.toString(i)+":"+quartDheur[j]);
						}else {
							HorlogeList.add(Integer.toString(i)+":"+quartDheur[j]);
						}
					}
				}
				String[] HorlogeArray=new String[HorlogeList.size()];
				HorlogeArray=HorlogeList.toArray(HorlogeArray);
				JComboBox<String> HorlogeSelectDebut = new JComboBox<>(HorlogeArray);
				JComboBox<String> HorlogeSelectFin = new JComboBox<>(HorlogeArray);
				
				JPanel panelPopup= new JPanel();
				panelPopup.setPreferredSize(new Dimension(widthPopup, HeightPopup));
		    	panelPopup.setBackground(Color.white);
		    	
				JPanel DateDebutPanel=new JPanel();
				DateDebutPanel.setPreferredSize(new Dimension(widthPopup, 50));
				DateDebutPanel.setBackground(Color.white);
				JPanel DateFinPanel=new JPanel();
				DateFinPanel.setPreferredSize(new Dimension(widthPopup, 50));
				DateFinPanel.setBackground(Color.white);
		    	
		    	JLabel DateDebut=new JLabel("Date du début de l'évènement");
		    	JLabel DateFin=new JLabel("Date de la fin de l'évènement");
		    	UtilDateModel model1 = new UtilDateModel();
		    	UtilDateModel model2 = new UtilDateModel();
				JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
				JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
				
				JDatePickerImpl datePickerDebut = new JDatePickerImpl(datePanel1);
				JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanel2);
				JPanel espacement[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};

				for(int i=0;i<espacement.length;i++) {
					espacement[i].setPreferredSize(new Dimension(widthPopup/8, 50));
					espacement[i].setBackground(Color.white);
				}
				
				DateDebutPanel.add(DateDebut);
				DateDebutPanel.add(datePickerDebut);
				DateDebutPanel.add(espacement[0]);
				DateDebutPanel.add(HorlogeSelectDebut);
				panelPopup.add(DateDebutPanel);
				DateDebutPanel.add(espacement[1]);
				
				
				DateFinPanel.add(DateFin);
				DateFinPanel.add(datePickerFin);
				
				
				DateFinPanel.add(espacement[2]);
				
				DateFinPanel.add(HorlogeSelectFin);
				DateFinPanel.add(espacement[3]);

				panelPopup.add(DateFinPanel);
				
				JLabel NomEventLabel=new JLabel("Nom de l'évènement : ");
				JFormattedTextField NomEventJTF=new JFormattedTextField();
				NomEventJTF.setColumns(15);
				JPanel NomEventPanel=new JPanel();
				NomEventPanel.add(NomEventLabel);
				NomEventPanel.add(NomEventJTF);
				NomEventPanel.setPreferredSize(new Dimension(widthPopup, 50));
				NomEventPanel.setBackground(Color.white);
				NomEventPanel.add(espacement[4]);
				espacement[4].setPreferredSize(new Dimension((int) (widthPopup/2.5), 50));
				panelPopup.add(NomEventPanel);
				
				JLabel Praticien=new JLabel("Visite de medecin ?");
				List<String> ListNomMed=new ArrayList<>();
				ListNomMed.add("Aucune Visite");
				List<List> ListMedTemp = compteRenduControleur.selectMedecin();
				List<Integer> ListIdMed=new ArrayList<>();
				for (int i = 0; i < ListMedTemp.size(); i++) {

					int idTemp = Integer.valueOf((String) ListMedTemp.get(i).get(0));
					ListIdMed.add(idTemp);
					ListNomMed.add((String) ListMedTemp.get(i).get(1));


				}
				String [] ArrayMedecin = new String[ListNomMed.size()];
				ArrayMedecin = ListNomMed.toArray(ArrayMedecin);
			
				JComboBox<String> SelectMedecin = new JComboBox<>(ArrayMedecin);
				
				JPanel Visite=new JPanel();
				Visite.setPreferredSize(new Dimension(widthPopup, 50));
				Visite.setBackground(Color.white);
				Visite.add(Praticien);
				Visite.add(SelectMedecin);
				Visite.add(espacement[5]);
				espacement[5].setPreferredSize(new Dimension(widthPopup/2, 50));
				panelPopup.add(Visite);
				
				JButton boutton=new JButton("Ajouter l'évènement");
				JPanel validation=new JPanel();
				validation.add(boutton);
				validation.setPreferredSize(new Dimension(widthPopup, 200));
				validation.setBackground(Color.white);
				
				panelPopup.add(validation);
				
				boutton.addActionListener(e -> {
					String DateDebutChoisie;
					String DateFinChoisie;
					String EventName=NomEventJTF.getText();
					String HeurDebut=(String) HorlogeSelectDebut.getSelectedItem();
					String HeurFin=(String) HorlogeSelectFin.getSelectedItem();
					if(EventName.length()<2) {
						EventName=null;
					}
					try {
						java.util.Date selectedDateDebut = (java.util.Date) datePickerDebut.getModel().getValue();
						DateFormat formatDateDebut = new SimpleDateFormat("yyyy/MM/dd");
						DateDebutChoisie = formatDateDebut.format(selectedDateDebut);

						java.util.Date selectedDateFin = (java.util.Date) datePickerFin.getModel().getValue();
						DateFormat formatDateFin = new SimpleDateFormat("yyyy/MM/dd");
						DateFinChoisie = formatDateFin.format(selectedDateFin);
						if(selectedDateDebut.compareTo(selectedDateFin)>0) {
							DateDebutChoisie=null;
							DateFinChoisie=null;
							System.out.println("Date de début supérieur à la date de fin de l'évènement");
						}

					} catch (Exception e2) {
						System.out.println(e2);
						System.out.println("une date n'a pas été sélectionnée");
						DateDebutChoisie = null;
						DateFinChoisie = null;
					}
					if(DateDebutChoisie!=null && DateFinChoisie!=null) {
						if(DateDebutChoisie.equals(DateFinChoisie)) {
							if(!verifHeur(HeurDebut,HeurFin)) {
								HeurDebut=null;
								HeurFin=null;
								System.out.println("problème de logique : heure de début après l'heure de fin pour la meme date.");
							}
						}
						if(EventName!=null && HeurDebut!=null && HeurFin!=null) {
							System.out.println("à prioris tout est bon");
							System.out.println(HeurDebut);
							System.out.println(HeurFin);
							System.out.println(DateDebutChoisie);
							System.out.println(DateFinChoisie);
							System.out.println(EventName);
							Integer idMedSelect;
							String nomMedecin;
							if(SelectMedecin.getSelectedIndex()==0) {
								idMedSelect=-1;
								nomMedecin=null;
							}else {
								idMedSelect=ListIdMed.get(SelectMedecin.getSelectedIndex()-1);
								nomMedecin=ListNomMed.get(idMedSelect);
							}
							System.out.println("id du medecin : "+idMedSelect);
							System.out.println(nomMedecin);
							AgendaC.ajoutEvenementVisiteur(EventName, DateDebutChoisie, DateFinChoisie, User.id_utilisateur, HeurDebut, HeurFin, idMedSelect);
							
							popup.dispatchEvent(new WindowEvent(popup, WindowEvent.WINDOW_CLOSING));
							
						}else {
							System.out.println("erreur lors du formulaire d'entré d'évènement");
						}
					}
					
		        });
				
				
				popup.add(panelPopup);
				

				
			}
			
		};
		
		return Form;
	}
	
	
	public MouseListener givePopup(final String Jour,final int unMois,final int uneAnnee) {
		MouseListener popupOnClick = new MouseAdapter() {
			@Override
	        public void mousePressed(MouseEvent e) {
				int widthPopup=900;
		    	int HeightPopup=500;
		    	
		    	Popup popup = new Popup("Évènements du "+Jour+" "+months[unMois]+" "+uneAnnee, widthPopup, HeightPopup);
		    	JPanel panelPopup= new JPanel();
		    	panelPopup.setPreferredSize(new Dimension(widthPopup, HeightPopup));
		    	List<List> EventsDuJour=AgendaC.consultationEvenementJour(User.id_utilisateur,Jour,unMois+1,uneAnnee);
		    	System.out.println(EventsDuJour);
		    	for(int i=0;i<EventsDuJour.size();i++) {
		    		JPanel ligne=new JPanel();
		    		ligne.setPreferredSize(new Dimension(widthPopup, 50));
		    		ligne.setBackground(Color.white);
		    		
		    		
		    		JLabel nomEvent=new JLabel((String) EventsDuJour.get(i).get(0));
		    		ligne.add(nomEvent);
		    		JLabel date;
		    		String dateDebut=USDateToFrDate((String) EventsDuJour.get(i).get(1));
		    		String dateFin=USDateToFrDate((String) EventsDuJour.get(i).get(2));

		    		String heureDebut=String.valueOf(((String) EventsDuJour.get(i).get(3)).charAt(0))+String.valueOf(((String) EventsDuJour.get(i).get(3)).charAt(1))+"h";
		    		String heureFin=String.valueOf(((String) EventsDuJour.get(i).get(4)).charAt(0))+String.valueOf(((String) EventsDuJour.get(i).get(4)).charAt(1))+"h";

		    		if(dateDebut.equals(dateFin)) {
		    			date=new JLabel("le "+USDateToFrDate((String) EventsDuJour.get(i).get(1)));
		    			ligne.add(date);
		    			JLabel heure;
		    			if(heureDebut.equals(heureFin)) {
		    				heure=new JLabel("à "+heureDebut);
		    			}else {
		    				heure=new JLabel("de "+heureDebut+" à "+heureFin);
		    			}
		    			ligne.add(heure);
		    			
		    			
		    		}else {		    			
		    			date=new JLabel("du "+USDateToFrDate((String) EventsDuJour.get(i).get(1))+" à "+heureDebut+" jusqu'au "+USDateToFrDate((String) EventsDuJour.get(i).get(2))+" à "+heureFin);
		    			ligne.add(date);
		    		}
		    	
		    		panelPopup.add(ligne);
		    	}
		    	popup.add(panelPopup);
	        }
		};
		return popupOnClick;
	}
		  

	  public java.util.Date getFirstDateOfCurrentMonth(int Year,int mois) {
		  //Calendar cal = Calendar.getInstance();
		  Calendar cal = new GregorianCalendar(Year,mois,1);
		  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		  
		  return cal.getTime();
		}
	  

	public String USDateToFrDate(String USDate) {
		SimpleDateFormat FrFormat=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat USFormat=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date TmpUsDate=null;
		try {
			TmpUsDate = USFormat.parse(USDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("erreur à la méthode USDateToFrDate -> Agenda.java");
		}
		
		return FrFormat.format(TmpUsDate);
	}
	
	public boolean verifHeur(String HeurDebut,String HeurFin) {
		String heureDebutWithoutPoint = Character.toString(HeurDebut.charAt(0))+Character.toString(HeurDebut.charAt(1))+Character.toString(HeurDebut.charAt(3))+Character.toString(HeurDebut.charAt(4));
		System.out.println(heureDebutWithoutPoint);
		String heureFinWithoutPoint = Character.toString(HeurFin.charAt(0))+Character.toString(HeurFin.charAt(1))+Character.toString(HeurFin.charAt(3))+Character.toString(HeurFin.charAt(4));
		System.out.println(heureFinWithoutPoint);
		int heurDebutInt=Integer.parseInt(heureDebutWithoutPoint);
		int heurFinInt=Integer.parseInt(heureFinWithoutPoint);
		if(heurDebutInt<heurFinInt || heurDebutInt==heurFinInt) {			
			return true;
		}else {
			return false;
		}
		
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
	

	private JLabel[] ajoutemoiLabel(JLabel[] MonArray, JLabel NouveauPanel) {
		/*
		 * 
		 * Fonction qui sert � rajouter des �l�ments dans un tableau. (ici nous ajoutons des JPanels) 
		 * 
		 */
		int newSize = MonArray.length + 1;
		JLabel[] tempArray = new JLabel[ newSize ];
		//Nous cr��ons un array temporaire avec la taille de l'array actuelle

		
		for (int i=0; i < MonArray.length; i++)
		{
			tempArray[i] = MonArray[i];
			//Pour chaque �l�ment dans mon array, on ajoute l'�l�ment dans la nouvelle array temporaire
		}
		tempArray[newSize- 1] = NouveauPanel; //Ici on ajoute le nouveau Jpanel dans l'array
		return tempArray;   // on retourne notre nouvelle array
	}
}