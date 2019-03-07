package controller;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.Connecteur;

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

}
