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

			Connection conn = (Connection) CnxBDD.connecteurUserLab();

			
			
			
			//Connection conn = (Connection) CnxBDD.connecteur();
			System.out.println("connection ok ");
			commentaire = commentaire.replaceAll("'", "\'");
			Medicament = Medicament.replace("'", "\'");
			String requete = 
					"INSERT INTO" + 
					"`rapport`( `date`, `bilan`, `motif`, `idUtilisateur`, `idPraticien`, `nomMedicament`, `echantillon`)" + 
					"VALUES ('"+date+"','"+commentaire+"','"+Motif+"','"+connectionControleur.id_utilisateur+"',4,'"+Medicament+"','"+echantillon+"')";
			System.out.println(requete);
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
			Connection conn = (Connection) CnxBDD.connecteurUserLab();

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


		try {
			List<List> List_CR = new ArrayList<List>();
			Connection conn =(Connection) CnxBDD.connecteurUserLab();
			
			System.out.println("connection"+conn);
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    String requete = "SELECT idRapport, date, bilan, motif, idUtilisateur, echantillon, idPraticien, nomMedicament from rapport";
			ResultSet resultat = statement.executeQuery(requete);
		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> cr = new ArrayList<String>();

	            int idRapport = resultat.getInt("idRapport");
	            String date = resultat.getString("date");
	            String bilan = resultat.getString("bilan");
	            String motif = resultat.getString("motif");
	            int echantillon = resultat.getInt("echantillon");
	            String medecin = resultat.getString("idPraticien");
	            String medicament = resultat.getString("nomMedicament");
	            
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
	

