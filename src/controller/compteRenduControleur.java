package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import CnxBDDLocalhost;

public class compteRenduControleur {

	
	/*public Boolean ajoutCompteRendu (String medecin, String Motif, String commentaire, String Date, int echantillon) {
		
		String requete = "INSERT INTO rapport(idRapport, date, bilan, motif, idUtilisateur) VALUES (1,'"+Date+"','"+commentaire+"','"+Motif+"',"+connectionControleur.id_utilisateur+")";
		Statement statement = ;
		return false;  
	}
	*/
	
	// CR consultation
	public static Boolean consultationCompteRendu(String medecin) throws SQLException {
		
		Connection conn = CnxBDDLocalhost.connecteur();
	    
	    /* Création de l'objet gérant les requêtes */
	    Statement statement = conn.createStatement();
	    ResultSet resultatCR = statement.executeQuery( "SELECT *  FROM rapport" );	
	    System.out.println(resultatCR);
	    return false; 
	}


}
