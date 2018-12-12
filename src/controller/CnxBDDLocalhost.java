package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import view.Fenetre;
import model.User;

public class CnxBDDLocalhost {

	public static boolean connect(String login,String mdp) {
		/***
		 * 
		 * 		ATTENTION CECI SERT A SE CONNECTER A UNE BASE DE DONNEE EN LOCAL / SURTOUT CELLE DE FEFE
		 * 
		 * 		MERCI POUR VOTRE COMPREHENTION
		 * 
		 * 
		 * 		Autre chose vous pouvez lancer Connexion.java via launchView, ceci n'est que temporaire, le temps qu'on code tout comme il faut
		 * 
		 * 
		 */
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost/applivisiteur?useSSL=false";
		    String user = "root";
		    String passwd = "";

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
		    /* Exécution d'une requête de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici présent, j'ai mit 5min avant de comprendre xD
		    ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur, login, password, nom,prenom  FROM utilisateur where login='"+login+"' AND password='"+mdp+"';" );
		    
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	        
	            int idUtilisateur = resultat.getInt( "idUtilisateur" );
	            String loginUtilisateur = resultat.getString( "login" );
	            String prenomUtilisateur= resultat.getString("prenom");
	            String motDePasseUtilisateur = resultat.getString( "password" );
	            String nomUtilisateur = resultat.getString( "nom" );
	            /* Formatage des données pour affichage dans la JSP finale. */
	           
	            	System.out.println( "Données retournées par la requête : login = " + loginUtilisateur
		                    + ", motdepasse = "
		                    + motDePasseUtilisateur + ", nom = " + nomUtilisateur + ", Id="+idUtilisateur+".");
	            	
	            new Fenetre();
	            
	            User.id_utilisateur=idUtilisateur;
	            User.nom=nomUtilisateur;
	            User.prenom=prenomUtilisateur;
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
	        
	            
	        }else{
	        	System.out.println("login ou mot de passe incorrect");
	        }
		    
		    
		    return true;
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("La connexion a eu un problème");
		    return false;
		}
		

	}

}
