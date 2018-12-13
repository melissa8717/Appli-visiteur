package controller;

import java.sql.Statement;

public class compteRenduControleur {

	
	public Boolean ajoutCompteRendu (String medecin, String Motif, String commentaire, String Date, int echantillon) {
		
		String requete = "INSERT INTO rapport(idRapport, date, bilan, motif, idUtilisateur) VALUES (1,'"+Date+"','"+commentaire+"','"+Motif+"',"+connectionControleur.id_utilisateur+")";
		Statement statement = ;
		return false;  
	}


}
