package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

public class AgendaC {
	/* Fonction d'ajout du compte rendu saisi */
	public static boolean ajoutEvenement (String rapport, String dateDebutEvent, String dateFinEvent, String heureDebut, String heureFinC) {
		try { 
			Connection conn = (Connection) CnxBDD.connecteurUserLab();
			
			System.out.println("connection ok");
			
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`agenda`( `evenement`, `dateDebut`, `dateFin`, `idUtilisateur`, `heureDebut`, `heureFin`)" + 
					"VALUES ('"+rapport+"','"+dateDebutEvent+"','"+dateFinEvent+"','"+connectionControleur.id_utilisateur+"', '"+heureDebut+"' ,'"+heureFinC+"')";
			System.out.println(requete);
			Statement statement =  conn.createStatement();	

			/* Exécution de la reqête */
			int rep = statement.executeUpdate(requete);
			
			return (rep > 0);
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return false;
		}
	}


	
	public static  List<List> consultationEvenement(int IdUser) {
		try {
			List<List> List_CE = new ArrayList<List>();
			Connection conn =(Connection) CnxBDD.connecteurUserLab();

			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
			Statement statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT evenement, dateDebut, dateFin, idUtilisateur, heureDebut, heureFin from agenda where idUtilisateur="+IdUser+";";
			ResultSet resultat = statement.executeQuery(requete);
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
				List_CE.add(event);
	            

	           
				
			}
		   


			return List_CE;


		   
		}
		
		    
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return null;
		}
		
	
	}
	
	public static  List selectVisiteur( ) {
		try {
			List List_SV = (List) new ArrayList<List>();
			Connection conn =(Connection) CnxBDD.connecteurUserLab();

			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
			Statement statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT CONCAT( nom,\" \", prenom) as nomVisiteur , role from utilisateur where role = 1";
			ResultSet resultat = statement.executeQuery(requete);
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
				System.out.println("empty");
			}
		    

			return List_SV;

		   
		}
		
		    
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return null;
		}
		
		
	
	}
	public static  List selectDateDebut(int IdUser) {
		try {
			List List_SDD = (List) new ArrayList<List>();
			Connection conn =(Connection) CnxBDD.connecteurUserLab();

			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
			String requete ="SELECT DATE_FORMAT(dateDebut, '%Y-%m') as dateDeb from agenda where idUtilisateur="+IdUser+";";
			Statement statement =  conn.createStatement();
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String>  dateDebutEvent =  new ArrayList<String>();
				String dateDebut = resultat.getString("dateDeb");
				dateDebutEvent.add(dateDebut);
				
	            List_SDD.add(dateDebutEvent);

			}
			return List_SDD;
			
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return null;
		}
		
	}
	
}

