package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import view.Fenetre;
import model.User;

public class CnxBDD {
	public static Connection connecteurUserLab() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://192.168.1.118/bduserlab?useSSL=false";
		    String user = "rootuser";
		    String passwd = "Aristee.2018..//";
		    /*String url ="jdbc:mysql://localhost/applivisiteur?useSSL=false";
		    String user = "root";
		    String passwd = "root";*/

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective à la base BDUserLab!");
		    return conn; 
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un problème");
			return null;
		} 
	}
	
	public static Connection connecteurMedocLab() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://192.168.1.118/bdmedocLab?useSSL=false";
		    String user = "rootuser";
		    String passwd = "Aristee.2018..//";
		    /*String url ="jdbc:mysql://localhost/bdmedoclab?useSSL=false";
		    String user = "root";
		    String passwd = "root";*/

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective à la base BDUserLab!");
		    return conn; 
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un problème");
			return null;
		} 
	}

	public static Boolean connect(String login, String mdp, User User) {
		try {
			Connection conn = connecteurUserLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
		    /* Exécution d'une requête de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici présent, j'ai mit 5min avant de comprendre xD
			ResultSet resultat = statement.executeQuery("SELECT idUtilisateur, login, password, nom, prenom  FROM utilisateur WHERE login='" + login + "' AND password='" + mdp + "';");
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	            int idUtilisateur = resultat.getInt( "idUtilisateur" );
	            String loginUtilisateur = resultat.getString( "login" );
	            String prenomUtilisateur= resultat.getString("prenom");
	            String motDePasseUtilisateur = resultat.getString( "password" );
	            String nomUtilisateur = resultat.getString( "nom" );
	            /* Formatage des données pour affichage dans la JSP finale. */
				System.out.println( "Données retournées par la requête : login = " + loginUtilisateur + ", motdepasse = " + motDePasseUtilisateur + ", nom = " + nomUtilisateur + ", Id="+idUtilisateur+".");
	            // new Fenetre();
	            
	            User.id_utilisateur = idUtilisateur;
	            User.nom = nomUtilisateur;
				User.prenom = prenomUtilisateur;
				User.connected = true;
				return true;
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}
			else{
	        	System.out.println("login ou mot de passe incorrect");
	        }
		    return true;
		} 
		catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("La connexion a eu un problème");
		    return false;
		}
	}
}
