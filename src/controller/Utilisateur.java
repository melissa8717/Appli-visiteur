package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;

import model.Connecteur;
import model.User;
import view.Popup;

public class Utilisateur {
	public static boolean ajoutVisiteurEquipe (int idVisiteurInt, String equipe) {
		
		try {
			Connection conn =(Connection) Connecteur.connecteurUL;
		
			Statement statement = conn.createStatement();
			String requete = "UPDATE utilisateur SET equipe='"+equipe+"',WHERE idUtilisateur="+idVisiteurInt+";";
			ResultSet resultat = statement.executeQuery(requete);
			System.out.println(" ia m here !!!");

			System.out.println("requete"+resultat);
			
			Popup Succes = new Popup("Mis à jour", 800,200);
			
			JPanel panelSucces = new JPanel(); 
			JLabel labelSucces = new JLabel("L'utilisateur a été correctement rajouté à l'équipe !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			
			return true;
		}
		catch (Exception e){
			
			Popup NotSucces = new Popup("L'utilisateur n'a pas été correctement rajouté à l'équipe !", 800,100);
			
			JPanel panelNotSucces = new JPanel(); 
			JLabel labelNotSucces = new JLabel("L'utilisateur n'a pas été correctement rajouté à l'équipe !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;

		}
	}
	
	public static  List<List> consultationEquipe(String equipe) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_E = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

		    /* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			
			/* Requête récupérat les comptes rendus du user connecté */
		    String requete = "SELECT `idUtilisateur`, `nom`, `prenom`, `equipe` FROM `utilisateur` WHERE equipe="+equipe+" ;";
			resultat = statement.executeQuery(requete);

		    /* Exécution d'une requête de lecture */
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    while(resultat.next()) {
				List<String> equipeList = new ArrayList<String>();

	            int idUtilisateur = resultat.getInt("idUtilisateur");
	            String nom = resultat.getString("nom");
	            String prenom = resultat.getString("prenom");
	            String equipeV = resultat.getString("equipe");

	            //convertir une date en string
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	           
	        
	            equipeList.add(Integer.toString(idUtilisateur));
	            equipeList.add(nom);
	            equipeList.add(prenom);
	            equipeList.add(equipeV);
				List_E.add(equipeList);
	 			
			}
			return List_E;
		   
		}
		
		    
		catch (Exception e){
			
			
			return null;
		}
		
	
	}
	
	
	
	
	
	
	
	/* Get / Create Utilisateur */
	
	public static List<List> getUtilisateur() {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			String requete = "SELECT * from `utilisateur`";
			statement = conn.createStatement();
			
			resultat = statement.executeQuery(requete);
			
			List<List> Utilisateur = new ArrayList<List>();
			
			List<String> Utilisateur_list = new ArrayList<String>();
			
			while(resultat.next()) {

	            int idUtilisateur = resultat.getInt("idUtilisateur");
	            String nom = resultat.getString("nom");
	            String prenom = resultat.getString("prenom");
	            String adresse = resultat.getString("adresse");
	            String ville = resultat.getString("ville");
	            String codePostal = resultat.getString("codePostal");
	            String role = resultat.getString("role");
	            String login = resultat.getString("login");
	           
	            Utilisateur_list.add(Integer.toString(idUtilisateur));
	            Utilisateur_list.add(nom);
	            Utilisateur_list.add(prenom);
	            Utilisateur_list.add(adresse);
	            Utilisateur_list.add(ville);
	            Utilisateur_list.add(codePostal);
	            Utilisateur_list.add(role);
	            Utilisateur_list.add(login);
	            
			}
			Utilisateur.add(Utilisateur_list);
			return Utilisateur;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
			
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}
	/*public User Create(String Name, String Email, String Password, String RememberToken, int UserProfile, int SupplierId) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			String requete = "SELECT * from `utilisateur`";
			statement = conn.createStatement();
			
		}
		
	}*/
}
