package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.util.SystemOutLogger;

import com.mysql.jdbc.Connection;

import model.Connecteur;
import model.User;
import view.Popup;

public class AgendaC {
	public static boolean ajoutEvenement (String rapport, String dateDebutEvent, String dateFinEvent, String heureDebut, String heureFinC) {
		Statement statement = null;
		ResultSet resultat = null;
		try { 
			Connection conn = (Connection) Connecteur.connecteurUL;
			
			
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`agenda`( `evenement`, `dateDebut`, `dateFin`, `idUtilisateur`, `heureDebut`, `heureFin`)" + 
					"VALUES ('"+rapport+"','"+dateDebutEvent+"','"+dateFinEvent+"','"+connectionControleur.id_utilisateur+"', '"+heureDebut+"' ,'"+heureFinC+"')";
			statement =  conn.createStatement();	

			Popup Succes = new Popup("Ajout", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("L'évenement a été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));
			return true;
			
			
			
		}
		
		catch (Exception e){
			Popup NotSucces = new Popup("Ajout : erreur", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;
		}
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}


	public static boolean ajoutEvenementVisiteur (String rapport, String dateDebutEvent, String dateFinEvent, int idVisiteur, String heureDebut, String heureFinC, int idMedecin, int idRole) {
		Statement statement = null;
		ResultSet resultat = null;
		try { 
			Connection conn = (Connection) Connecteur.connecteurUL;
			
			
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`agenda`( `evenement`, `dateDebut`, `dateFin`, `idUtilisateur`, `heureDebut`, `heureFin`,`idPraticien`,`idRole`)" + 
					"VALUES ('"+rapport+"','"+dateDebutEvent+"','"+dateFinEvent+"',"+idVisiteur+", '"+heureDebut+"' ,'"+heureFinC+"', "+idMedecin+", "+User.role+")";
			statement =  conn.createStatement();	

			/* Exécution de la reqête */
			statement =  conn.createStatement();	
			Popup Succes = new Popup("Ajout", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("L'évenement a été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));
			return true;
			
			
			
		}
		
		catch (Exception e){
			Popup NotSucces = new Popup("Ajout : erreur", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;
		}
		
	}

	
	public static  List<List> consultationEvenement(int IdUser) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_CE = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT evenement, dateDebut, dateFin, idUtilisateur, heureDebut, heureFin, idAgenda, idPraticien, idRole from agenda where idUtilisateur="+IdUser+";";
			resultat = statement.executeQuery(requete);
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> event = new ArrayList<String>();

	            String evenement = resultat.getString("evenement");
	            Date dateD = resultat.getDate("dateDebut");
	            Date dateFin = resultat.getDate("dateFin");
	            int idUtilisateur = resultat.getInt("idUtilisateur");
	            String heureDebut = resultat.getString("heureDebut");
	            String heureFin = resultat.getString("heureFin");
	            int idAgenda = resultat.getInt("idAgenda");
	            int idMedecin= resultat.getInt("idPraticien");
	            int idRole = resultat.getInt("idRole");


	            //convertir une date en string
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            
	            String strDateD = dateFormat.format(dateD);
	            String strDateF = dateFormat.format(dateFin);
	            
	            
	            event.add(evenement);
	            event.add(strDateD);
	            event.add(strDateF);
	            event.add(Integer.toString(idUtilisateur));
	            event.add(heureDebut);
	            event.add(heureFin);
	            event.add(Integer.toString(idAgenda));
	            event.add(Integer.toString(idMedecin));
	            event.add(Integer.toString(idRole));

				List_CE.add(event);
				   System.out.println("event bdd:"+List_CE);

	           
				
			}

			return List_CE;

		   
		}
		
		    
		catch (Exception e){
			
			
			return null;
		}
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
		
	
	}
	
	public static  List<List> consultationLastEvenement(int IdUser) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_CLE = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT evenement, dateDebut, dateFin, idUtilisateur, heureDebut, heureFin, idAgenda from agenda where idUtilisateur="+IdUser+" order by idAgenda DESC limit 1;";
			resultat = statement.executeQuery(requete);

		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> event = new ArrayList<String>();

	            String evenement = resultat.getString("evenement");
	            Date dateD = resultat.getDate("dateDebut");
	            Date dateFin = resultat.getDate("dateFin");
	            int idUtilisateur = resultat.getInt("idUtilisateur");
	            String heureDebut = resultat.getString("heureDebut");
	            String heureFin = resultat.getString("heureFin");
	            int idAgenda = resultat.getInt("idAgenda");

	            //convertir une date en string
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            
	            String strDateD = dateFormat.format(dateD);
	            String strDateF = dateFormat.format(dateFin);
	            
	            
	            event.add(evenement);
	            event.add(strDateD);
	            event.add(strDateF);
	            event.add(Integer.toString(idUtilisateur));
	            event.add(heureDebut);
	            event.add(heureFin);
	            event.add(Integer.toString(idAgenda));
				List_CLE.add(event);
	            
	           
				
			}
		   

			return List_CLE;


		   
		}
		
		    
		catch (Exception e){
			
			
			return null;
		}
		
	
	}
	public static  int countEvenement(int IdUser, String debut) {
		
	
	
		try {
			List<List> List_Count = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			Statement statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT COUNT(dateDebut) FROM agenda where dateDebut = "+debut+" and idUtilisateur ="+IdUser+";";
			ResultSet resultat = statement.executeQuery(requete);

		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
			
			 while (resultat.next()) {
				 int count = resultat.getInt(1);

				 return count;

				 }
		    
		}
		
		    
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			
		}
		return IdUser;
		
	
	}

	
	public static boolean updateEvent(int idAgendaInt, String textEvent, String dateDebut, String dateFin, int idUtilisateur, String heureDebut, String heureFin) {
		Statement statement = null;
		int resultat = 0;
		try {
			Connection conn =(Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			String requete = "UPDATE agenda SET idAgenda="+idAgendaInt+",evenement='"+textEvent+"',dateDebut='"+dateDebut+"',dateFin='"+dateFin+"',idUtilisateur="+idUtilisateur+",heureDebut='"+heureDebut+"',heureFin='"+heureFin+"' WHERE idAgenda="+idAgendaInt+";";
			resultat = statement.executeUpdate(requete);
			
			
			Popup Succes = new Popup("Mis à jour", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("L'évenement a été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			
			return true;
		}
		catch (Exception e){
			
			Popup NotSucces = new Popup("L'évenement n'a pas été correctement mise à jour !", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;

		}
		finally {
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}
	
	public static boolean suppressionEvent(int idAgendaInt) {
		Statement statement = null;
		try {
			Connection conn =(Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			String requete = "DELETE FROM agenda WHERE idAgenda = "+idAgendaInt+";";

			int resultat = statement.executeUpdate(requete);
			Popup Succes = new Popup("Suppression :", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("L'évenement a été correctement supprimé !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));
			return true;
		}
		catch (Exception e){
			
			Popup NotSucces = new Popup("Suppression : erreur ", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été correctement supprimé !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			
			
			return false;

		}
		finally {
			
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}
	
	
	
	public static  List selectVisiteur( ) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List List_SV = (List) new ArrayList<List>();
			Connection conn =(Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT CONCAT( nom,'_', prenom) as nomVisiteur, idUtilisateur, role from utilisateur where role = 1";
			resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
		    	ArrayList<String> visiteurs = (ArrayList<String>) new ArrayList<String>();

	            String nomVisiteur = resultat.getString("nomVisiteur");
	            int role = resultat.getInt("role");
	            
	            visiteurs.add(nomVisiteur);
	            visiteurs.add(Integer.toString(role));
	            
	            ((ArrayList<List>) List_SV).add((List) visiteurs);
	            

			}
		    if(((ArrayList<List>) List_SV).isEmpty()) {
			}
		    

			return List_SV;

		   
		}
		
		    
		catch (Exception e){
			return null;
		}
		
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	
	}
	
	public static  List selectDelegue( ) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List List_SV = (List) new ArrayList<List>();
			Connection conn =(Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT CONCAT( nom,'_', prenom) as nomVisiteur, idUtilisateur, role from utilisateur where role = 2";
			resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
		    	ArrayList<String> visiteurs = (ArrayList<String>) new ArrayList<String>();

	            String nomVisiteur = resultat.getString("nomVisiteur");
	            int role = resultat.getInt("role");
	            
	            visiteurs.add(nomVisiteur);
	            visiteurs.add(Integer.toString(role));
	            
	            ((ArrayList<List>) List_SV).add((List) visiteurs);
	            

			}
		    if(((ArrayList<List>) List_SV).isEmpty()) {
			}
		    

			return List_SV;

		   
		}
		
		    
		catch (Exception e){
			return null;
		}
		
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	
	}
	
	
	public static  List idVisiteur(String nom, String prenom ) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List List_SV = (List) new ArrayList<List>();
			Connection conn =(Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT idUtilisateur FROM `utilisateur` WHERE nom ='"+nom+"' and prenom= '"+prenom+"'";
			resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
		    	ArrayList<String> visiteurs = (ArrayList<String>) new ArrayList<String>();

	            int idVisiteur = resultat.getInt("idUtilisateur");
	            
	            visiteurs.add(Integer.toString(idVisiteur));
	            
	            ((ArrayList<List>) List_SV).add((List) visiteurs);
	            

			}
		    if(((ArrayList<List>) List_SV).isEmpty()) {
			}
		    

			return List_SV;

		   
		}
		
		    
		catch (Exception e){
			return null;
		}
		
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	
	}
	
	
	/* Fonction de sélection du médecin */
	public static List<List> choixMedecin(Object medecinSelected) {
		try {
			List<List> List_Medecins = new ArrayList<List>();

			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Requête de récupération des ids des médecins */
			String requete = "SELECT idPraticien,nom, prenom, adresse, ville, codePostal, telephone FROM praticien where idPraticien = '"+medecinSelected+"';";
			Statement statement =  conn.createStatement();
			ResultSet resultat = statement.executeQuery(requete);

			/* Récupère tous les id des médecins */
			while(resultat.next()) {
				List<String> unMedecin = new ArrayList<String>();
				int idMed= resultat.getInt( "idPraticien" );
				String nomMed= resultat.getString( "nom" );
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");


				unMedecin.add(Integer.toString(idMed));
				unMedecin.add(nomMed);
				unMedecin.add(prenomMed);
				unMedecin.add(adresseMed);
				unMedecin.add(villeMed);
				unMedecin.add(cpMed);
				unMedecin.add(telMed);
				List_Medecins.add(unMedecin);
			}
			
			return List_Medecins;
			
			
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef un medecin ");
			return null;
		}
		
		
	}
}

