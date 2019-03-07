package controller;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Connecteur;
import view.Popup;

public class Medecin {
	public static  List<List> listSpeMedecin(){
		try {
			List<List> List_SM = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM specialitepraticien;";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> speMed = new ArrayList<String>();
				String nomSpe = resultat.getString("nom");
				int idSpe = resultat.getInt("idSpe");
				
				speMed.add(nomSpe);
				speMed.add(Integer.toString(idSpe));
				
				List_SM.add(speMed);
			}
			return List_SM;
		}catch(Exception e){
			return null;
		}
	}
	
	public static  List<List> listMedecin(){
		try {
			List<List> List_Med = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT * FROM praticien;";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> Med = new ArrayList<String>();
				
				int idMed = resultat.getInt("idPraticien");
				int idSpeMed = resultat.getInt("idTypePraticien");
				String nomMed = resultat.getString("nom");
				String prenomMed = resultat.getString("prenom");
				String adresseMed = resultat.getString("adresse");
				String villeMed = resultat.getString("ville");
				String cpMed = resultat.getString("codePostal");
				String telMed = resultat.getString("telephone");

				Med.add(Integer.toString(idMed));
				Med.add(Integer.toString(idSpeMed));
				Med.add(nomMed);
				Med.add(prenomMed);
				Med.add(adresseMed);
				Med.add(villeMed);
				Med.add(cpMed);
				Med.add(telMed);

				List_Med.add(Med);
			}
			return List_Med;
		}catch(Exception e){
			return null;
		}
	}
	
	public static  List<List> listTypeMedecin(String idSpe){
		try {
			List<List> List_TM = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;
			Statement statement = conn.createStatement();
		    String requete = "SELECT idSpe, specialitepraticien.nom FROM `specialitepraticien` left join praticien on specialitepraticien.idSpe = praticien.idTypePraticien where specialitepraticien.idSpe = '"+idSpe+"';";
			ResultSet resultat = statement.executeQuery(requete);
			
			while(resultat.next()) {
				List<String> Spe = new ArrayList<String>();
				
				int idSpeMed = resultat.getInt("idSpe");
				String nomSpe = resultat.getString("nom");
			
				Spe.add(Integer.toString(idSpeMed));
				Spe.add(nomSpe);
			
				List_TM.add(Spe);
			}
			return List_TM;
		}catch(Exception e){
			return null;
		}
	}
	
	public static boolean updateMedecin(String idMed, String nomMedecin, String prenomMedecin, String adresse, String ville, String cp, String tel) {
		java.sql.PreparedStatement statement = null;
		int resultat = 0;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			String requete = "UPDATE praticien SET idPraticien='" + idMed + "', '" +nomMedecin + "', '" + prenomMedecin+ "', '" + adresse +"', '" + ville+"', '" + cp+ "', '" + tel + "' where idPraticien = '" + idMed + "';";
			statement = conn.prepareStatement(requete);

			resultat = statement.executeUpdate(requete);
			System.out.println("result"+ resultat);
			Popup Succes = new Popup("Médecin mis à jour", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("Le médecin a été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			return true;
		} catch (Exception e) {

			Popup NotSucces = new Popup("Le medecin n'a pas été correctement mise à jour !", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("Le médecin n'a pas été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}


}
