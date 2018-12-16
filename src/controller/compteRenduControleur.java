package controller;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import controller.*;
import model.*;

import com.mysql.jdbc.Connection;


public class compteRenduControleur {


	//CR Saisie Compte Rendu
	public static boolean ajoutCompteRendu (int medecin, String Motif, String commentaire,String date, int echantillon,String Medicament) {
		try {
			//Connection conn = (Connection) CnxBDD.connecteurUserLab();

			Connection conn = (Connection) CnxBDD.connecteur();
			String requete = "INSERT INTO rapport(date, bilan, motif, idUtilisateur,idPraticien,nomMedicament) VALUES ('"+date+"','"+commentaire+"','"+Motif+"',"
			+connectionControleur.id_utilisateur+",'"+medecin+"','"+Medicament+"');";
			Statement statement =  conn.createStatement();
			int rep = statement.executeUpdate(requete);
			System.out.println("compte rendu inséré");
			return (rep>0);
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return false;
		}
	}
	
	public static List<List> selectMedecin() {
		try {
			List<List> List_Medecins = new ArrayList<List>();
			
			
			//Connection conn = (Connection) CnxBDD.connecteurUserLab();
			Connection conn = (Connection) CnxBDD.connecteur();

			String requete = "SELECT idPraticien,nom FROM praticien;";
			Statement statement =  conn.createStatement();
			ResultSet resultat = statement.executeQuery(requete);
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
	
	
	// CR consultation
	public static List<List> consultationCompteRendu()  {
		System.out.println("je suis dans la requeteeeeeeeee");

		try {
			List<List> List_CR = new ArrayList<List>();


			Connection conn =(Connection) CnxBDD.connecteur();
			//Connection conn =(Connection) CnxBDD.connecteurUserLab();
			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    String requete = "SELECT idRapport, date, bilan, motif, idUtilisateur, echantillon, medecin, medicament from rapport";

			ResultSet resultat = statement.executeQuery(requete);

		    System.out.println("statement" + statement);
		    /* Exécution d'une requête de lecture */
		    
			System.out.println("resultat"+resultat);
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> cr = new ArrayList<String>();

	            int idRapport = resultat.getInt("idRapport");
	            String date = resultat.getString("date");
	            String bilan = resultat.getString("bilan");
	            String motif = resultat.getString("motif");
	            int echantillon = resultat.getInt("echantillon");
	            String medecin = resultat.getString("medecin");
	            String medicament = resultat.getString("medicament");
	            int idUtilisateur = resultat.getInt("idUtilisateur");
	            cr.add(Integer.toString(idRapport));
	            cr.add(date);
	            cr.add(bilan);
	            cr.add(motif);
	            cr.add(Integer.toString(echantillon));
	            cr.add(medecin);
	            cr.add(medicament);
	            cr.add(Integer.toString(idUtilisateur));
	            List_CR.add(cr);
	            


	            System.out.println("id mec"+idUtilisateur + "id rapport"+idRapport+"date"+date+"bilan :"+bilan+"motif : " +motif +" echantillon : " +echantillon);
	          
	            /* Formatage des données pour affichage dans la JSP finale. */
				System.out.println( "Données retournées par la requête :  Id="+idUtilisateur+".");
	            
	            User.id_utilisateur = idUtilisateur;
	           
				
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}

			
		}
		
		    
		    catch (Exception e){
		    	System.out.println("je ne suis plus dans la requete visiblement....");
		  
			}
		
		return List_CR;
		
		
	
	}	
	
}
	

