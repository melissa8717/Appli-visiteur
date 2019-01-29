package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import view.Fenetre;
import model.User;

public class CnxBDD {


	public static Connection connecteurUserLab() {
    	System.out.println("i am connected");

		/* Connection à la base de donnée BDUserLab */
	    final int INITIAL_POOL_SIZE = 10;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");
		    

<<<<<<< HEAD

=======
>>>>>>> f79ced0ee77b053f1f47baa742daa77a496fac7a
		    String url = "jdbc:mysql://192.168.1.118/bduserlab?useSSL=false";
		    String user = "rootuser";
		    String passwd = "Aristee.2018..//";
		    
<<<<<<< HEAD
		    /*String url = "jdbc:mysql://localhost/test-appli-visiteur?useSSL=false";
=======
		   /* String url = "jdbc:mysql://localhost/test-appli-visiteur?useSSL=false";
>>>>>>> f79ced0ee77b053f1f47baa742daa77a496fac7a
		    String user = "root";
		    String passwd = "";*/

		    Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective à la base BDUserLab!");
		    return conn; 
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un problème");
			return null;
		} 
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}
	
	/* Connection à la base de donnée BDMedocLab */
	public static Connection connecteurMedocLab() {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    System.out.println("Driver O.K.");

		    String url = "jdbc:mysql://192.168.1.118/bdmedocLab?useSSL=false";
		    String user = "rootuser";

		    String passwd = "Aristee.2018..//";

		    /*String url ="jdbc:mysql://localhost/test-appli-visiteur?useSSL=false";

		    String user = "root";
		    String passwd = "";*/


		   Connection conn = DriverManager.getConnection(url, user, passwd);
		    System.out.println("Connexion effective à la base BDUserLab!");
		    return conn; 
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("La connexion a eu un problème");
			return null;
		}
		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) { /* ignored */}
			}
		}
	}

	/* Connexion et récupération des informations du user connecté */
	public static Boolean connect(String login, String mdp, User User) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			conn = connecteurUserLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    statement = conn.createStatement();
		    
		    /* Exécution d'une requête de lecture */
		    
		    //N'oubliez pas de mettre des ' ' sur vos variables comme ici présent, j'ai mit 5min avant de comprendre xD
			resultat = statement.executeQuery("SELECT idUtilisateur, login, password, nom, prenom,role  FROM utilisateur WHERE login='" + login + "' AND password='" + mdp + "';");
			statement.setFetchSize(100);
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	            User.id_utilisateur = resultat.getInt( "idUtilisateur" );
	            User.nom = resultat.getString( "nom" );
				User.prenom = resultat.getString("prenom");
				User.role = resultat.getInt("role");
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
		finally {
			if (resultat != null) {
				try {
					resultat.close();
					conn.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
					conn.close();

				} catch (SQLException e) { /* ignored */}
			}
		}
	}



}
