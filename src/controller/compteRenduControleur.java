package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import controller.*;
import model.*;

import com.mysql.jdbc.Connection;


public class compteRenduControleur {


	/* Fonction d'ajout du compte rendu saisi */
	public static boolean ajoutCompteRendu (int medecin, String Motif, String commentaire, String date, int echantillon, String Medicament) {
		try { 
			Connection conn = (Connection) CnxBDD.connecteurUserLab();
			
			System.out.println("connection ok");
			commentaire = commentaire.replaceAll("(\')", "\\\\'");
			Medicament = Medicament.replaceAll("(\')", "\\\\'");
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`rapport`( `date`, `bilan`, `motif`, `idUtilisateur`, `idPraticien`, `nomMedicament`, `echantillon`)" + 
					"VALUES ('"+date+"','"+commentaire+"','"+Motif+"','"+connectionControleur.id_utilisateur+"',4,'"+Medicament+"','"+echantillon+"')";
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
	
	/* Fonction de sélection du médecin */
	public static List<List> selectMedecin() {
		try {
			List<List> List_Medecins = new ArrayList<List>();
			
			Connection conn = (Connection) CnxBDD.connecteurUserLab();

			/* Requête de récupération des ids des médecins */
			String requete = "SELECT idPraticien,nom FROM praticien;";
			Statement statement =  conn.createStatement();
			ResultSet resultat = statement.executeQuery(requete);

			/* Récupère tous les id des médecins */
			while(resultat.next()) {
				List<String> unMedecin = new ArrayList<String>();
				int idMed= resultat.getInt( "idPraticien" );
				String nomMed= resultat.getString( "nom" );
				unMedecin.add(Integer.toString(idMed));
				unMedecin.add(nomMed);
				List_Medecins.add(unMedecin);
			}
			
			return List_Medecins;
			
			
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return null;
		}
		
		
	}
	
	/* Fonction de récupération des comptes rendus du user connecté */
	public static List<List> consultationCompteRendu(int unMois,int debut,int IdUser)  {

	
		try {
			List<List> List_CR = new ArrayList<List>();
			Connection conn =(Connection) CnxBDD.connecteurUserLab();
			
			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
			Statement statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT rapport.idRapport, rapport.date, rapport.bilan, rapport.motif, rapport.idUtilisateur,"
		    		+ " rapport.echantillon, praticien.nom, rapport.nomMedicament from rapport,praticien"
		    		+ " where rapport.idPraticien=praticien.idPraticien AND rapport.idUtilisateur="+IdUser+""
		    				+ " AND rapport.date LIKE '%-"+unMois+"-%' LIMIT 6 OFFSET "+debut+";";
		    
			ResultSet resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> cr = new ArrayList<String>();

	            int idRapport = resultat.getInt("rapport.idRapport");
	            String date = resultat.getString("rapport.date");
	            String bilan = resultat.getString("rapport.bilan");
	            String motif = resultat.getString("rapport.motif");
	            int echantillon = resultat.getInt("rapport.echantillon");
	            String medecin = resultat.getString("praticien.nom");
	            String medicament = resultat.getString("rapport.nomMedicament");
	            
	            int idUtilisateur = resultat.getInt("rapport.idUtilisateur");
	            cr.add(Integer.toString(idRapport));
	            cr.add(date);
	            cr.add(bilan);
	            cr.add(motif);
	            cr.add(Integer.toString(echantillon));
	            cr.add(medecin);
	            cr.add(medicament);
	            cr.add(Integer.toString(idUtilisateur));
	            List_CR.add(cr);
	            


	           
	            /* Formatage des données pour affichage dans la JSP finale. */
				//System.out.println( "Données retournées par la requête :  Id="+idUtilisateur+".");
	            
	            User.id_utilisateur = idUtilisateur;
	           
				
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}
		    

			return List_CR;

		   
		}
		
		    
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return null;
		}
		
		
		
		
	
	}	
	
}
	

