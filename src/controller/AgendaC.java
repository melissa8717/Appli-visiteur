package controller;

import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class AgendaC {
	/* Fonction d'ajout du compte rendu saisi */
	public static boolean ajoutEvenement (String rapport, String dateDebutEvent, String dateFinEvent) {
		System.out.println("appel du controller");
		try { 
			Connection conn = (Connection) CnxBDD.connecteurUserLab();
			System.out.println(conn);
			
			System.out.println("connection ok");
			
			
			/* Requête d'insertion en base du compte rendu */
			String requete = 
					"INSERT INTO" + 
					"`agenda`( `evenement`, `dateDebut`, `dateFin`, `idUtilisateur`)" + 
					"VALUES ('"+rapport+"','"+dateDebutEvent+"','"+dateFinEvent+"','"+connectionControleur.id_utilisateur+"')";
			System.out.println(requete);
			Statement statement =  conn.createStatement();	

			/* Exécution de la reqête */
			int rep = statement.executeUpdate(requete);
			
			return (rep > 0);
		}
		
		catch (Exception e){
			System.out.println(e);
			System.out.println("marche pas chef");
			return false;
		}
	}
}
