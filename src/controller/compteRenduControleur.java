package controller;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import controller.*;
import model.*;

import com.mysql.jdbc.Connection;


public class compteRenduControleur {

	



	public static  String resultat = new String();


	public static boolean ajoutCompteRendu (int medecin, String Motif, String commentaire,String date, int echantillon,String Medicament) {
		try {
			Connection conn = (Connection) CnxBDD.connecteurUserLab();
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
	
	
	// CR consultation
	public static void consultationCompteRendu() throws SQLException {
		System.out.println("je suis dans la requeteeeeeeeee");
		try {

			Connection conn =(Connection) CnxBDD.connecteur();
			//Connection conn =(Connection) CnxBDD.connecteurUserLab();
			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    System.out.println("statement" + statement);
		    /* Exécution d'une requête de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici présent, j'ai mit 5min avant de comprendre xD
		    String requete = "SELECT idRapport, date, bilan, motif, idUtilisateur, echantillon, medecin, medicament from rapport";
			ResultSet resultat = statement.executeQuery(requete);
			System.out.println("resultat"+resultat);
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
	            int idRapport = resultat.getInt("idRapport");
	            Date date = resultat.getDate("date");
	            String bilan = resultat.getString("bilan");
	            String motif = resultat.getString("motif");
	            int echantillon = resultat.getInt("echantillon");
	            String medecin = resultat.getString("medecin");
	            String medicament = resultat.getString("medicament");
	            int idUtilisateur = resultat.getInt("idUtilisateur");


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
		
		
	
	}	
	
}
	

