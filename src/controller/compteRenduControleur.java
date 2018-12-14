package controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

import com.mysql.jdbc.PreparedStatement;

import controller.CnxBDDLocalhost;
import model.User;

public class compteRenduControleur {

	

	public static boolean ajoutCompteRendu (String medecin, String Motif, String commentaire,String date, int echantillon) {
		try {
			Connection conn = (Connection) CnxBDDLocalhost.connecteur();
			String requete = "INSERT INTO rapport(idRapport, date, bilan, motif, idUtilisateur) VALUES (1,'"+date+"','"+commentaire+"','"+Motif+"',"+connectionControleur.id_utilisateur+")";
			Statement statement =  conn.createStatement();
			int rep = statement.executeUpdate(requete);
			return (rep>0);
		}
		
		catch (Exception e){
			return false;
		}
	}
	
	
	// CR consultation
	public static Boolean consultationCompteRendu() throws SQLException {
		System.out.println("je suis dans la requeteeeeeeeee");
		try {

			Connection conn =(Connection) CnxBDDLocalhost.connecteur();
			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    System.out.println("statement" + statement);
		    /* Exécution d'une requête de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici présent, j'ai mit 5min avant de comprendre xD
			ResultSet resultat = statement.executeQuery("SELECT * from rapport;");
			System.out.println("resultat"+resultat);
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	            int idUtilisateur = resultat.getInt( "idUtilisateur" );
	            int idRapport = resultat.getInt("idRapport");
	            Date date = resultat.getDate("date");
	            String bilan = resultat.getString("bilan");
	            String motif = resultat.getString("motif");
	            int echantillon = resultat.getInt("echantillon");
	            System.out.println("id mec"+idUtilisateur + "id rapport"+idRapport+"date"+date+"bilan :"+bilan+"motif : " +motif +" echantillon : " +echantillon);
	          
	            /* Formatage des données pour affichage dans la JSP finale. */
				System.out.println( "Données retournées par la requête :  Id="+idUtilisateur+".");
	            
	            User.id_utilisateur = idUtilisateur;
	           
				return true;
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}

			else{
	        	System.out.println("requete incorrect");
	        }
		    return true;
		}
		
		    
		    catch (Exception e){
		    	System.out.println("je ne suis plus dans la requete visiblement....");
		  
				return false;
			}
		
		
	
	}	
	
}
	

