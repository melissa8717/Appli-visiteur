package controller;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;

import com.mysql.jdbc.Connection;
import controller.CnxBDD;

public class compteRenduControleur {

	
	public static boolean ajoutCompteRendu (int medecin, String Motif, String commentaire,String date, int echantillon,String Medicament) {
		try {
			Connection conn = (Connection) CnxBDD.connecteur();
			String requete = "INSERT INTO rapport(date, bilan, motif, idUtilisateur,idPraticien,nomMedicament) VALUES ('"+date+"','"+commentaire+"','"+Motif+"',"
			+connectionControleur.id_utilisateur+",'"+medecin+"','"+Medicament+"');";
			Statement statement =  conn.createStatement();
			int rep = statement.executeUpdate(requete);
			System.out.println("compte rendu inséré");
			return (rep>0);
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return false;
		}
	}


}
