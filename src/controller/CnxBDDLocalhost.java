package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CnxBDDLocalhost {

	public static void main(String[] args) {
		/***
		 * 
		 * 		ATTENTION CECI SERT A SE CONNECTER A UNE BASE DE DONNEE EN LOCAL / SURTOUT CELLE DE FEFE
		 * 
		 * 		MERCI POUR VOTRE COMPREHENTION
		 * 
		 * 
		 * 
		 */
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost/applivisiteur?useSSL=false";
		    String user = "root";
		    String passwd = "root";

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
		    /* Exécution d'une requête de lecture */
		    ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur, login, password, nom  FROM utilisateur;" );
		    
		
		    /* Récupération des données du résultat de la requête de lecture */
	        while ( resultat.next() ) {
	            int idUtilisateur = resultat.getInt( "idUtilisateur" );
	            String login = resultat.getString( "login" );
	            String motDePasseUtilisateur = resultat.getString( "password" );
	            String nomUtilisateur = resultat.getString( "nom" );
	            /* Formatage des données pour affichage dans la JSP finale. */
	            System.out.println( "Données retournées par la requête : login = " + login
	                    + ", motdepasse = "
	                    + motDePasseUtilisateur + ", nom = " + nomUtilisateur + "." );
	        }
		    
		    
		    
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

}
