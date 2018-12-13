package controller;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;

import com.mysql.jdbc.Connection;
import controller.CnxBDDLocalhost;

public class compteRenduControleur {

	
	public static boolean ajoutCompteRendu (String medecin, String Motif, String commentaire,String date, int echantillon) {
		try {
			Connection conn = (Connection) CnxBDDLocalhost.connecteur();
			String requete = "INSERT INTO rapport(idRapport, date, bilan, motif, idUtilisateur) VALUES (1,'"+date+"','"+commentaire+"','"+Motif+"',"+connectionControleur.id_utilisateur+")";
			Statement statement =  conn.createStatement();
			int rep = statement.executeUpdate(requete);
			return (rep>0);
		}
		
		catch (Exception e){
			return false;
		}
	}


}
