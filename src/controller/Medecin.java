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

}
