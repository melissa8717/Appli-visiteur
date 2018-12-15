package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import model.User;

public class CnxBDDLocalhost {
	public static Connection connecteur() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://localhost:3306/applivisiteur?useSSL=false";
		    String user = "Bobo";
		    String passwd = "8629";

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective !");
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
			Connection conn = connecteur();
		    
		    // Création de l'objet gérant les requêtes
		    Statement statement = conn.createStatement();
		    
		    // Exécution d'une requête de lecture
		    // N'oubliez pas de mettre des ' ' autour de vos variables comme ici présent
			ResultSet resultat = statement.executeQuery("SELECT idUtilisateur, nom, prenom, role  FROM utilisateur WHERE login = '" + login + "' AND password = '" + mdp + "';");
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) { 
	            /* Formatage des données pour affichage dans la JSP finale. */
	            User.id_utilisateur = resultat.getInt("idUtilisateur");
	            User.nom = resultat.getString("nom");
				User.prenom = resultat.getString( "prenom" );
				User.role = resultat.getInt("role");
				User.connected = true;
				return true;
	            // Pour faire ca, faut que les attributs de user soit en static, me demander par pourquoi
			}
			else {
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
