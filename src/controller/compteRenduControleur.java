package controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
=======
import java.sql.ResultSet;
>>>>>>> 2dc8d5078a4b61e40d04f65e7661132140f88799
import java.sql.Statement;
import CnxBDDLocalhost;

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
	
	
	// CR consultation
	public static Boolean consultationCompteRendu(String Mois) throws SQLException {
		
		Connection conn = CnxBDDLocalhost.connecteur();
	    
	    /* Création de l'objet gérant les requêtes */
	    Statement statement = conn.createStatement();
	    ResultSet resultatCR = statement.executeQuery( "SELECT *  FROM rapport" );	
	    System.out.println(resultatCR);
	    return false; 
	}


}
