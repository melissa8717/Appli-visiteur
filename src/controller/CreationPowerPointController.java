package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.CnxBDD;

public class CreationPowerPointController {
	
	public static List<String> ListeMedicament(String LettreSelected){
	String nomMedoc;
	String effetMedoc;
	List<String> medicaments_list = new ArrayList<String>();
	
	try {
		Connection conn = CnxBDD.connecteurMedocLab();
	    
	    /* Création de l'objet gérant les requêtes */
	    Statement statement = conn.createStatement();
	    
		ResultSet resultat = statement.executeQuery("SELECT nom,effet FROM medicament WHERE nom LIKE '"+LettreSelected+"%';");
	
	    /* Récupération des données du résultat de la requête de lecture */
	    while(resultat.next()) {
            
            nomMedoc = resultat.getString( "nom" );
            effetMedoc= resultat.getString("effet");
            System.out.println(nomMedoc);
            System.out.println("__________________________");
            System.out.println(effetMedoc);
            medicaments_list.add(nomMedoc);
            
	    }
	    return medicaments_list;
	}catch (Exception ErreurCnxMedocLab) {
			// TODO: handle exception
			System.out.println("Problème de connexion à: 'bdmedocLab'");
		}
	return medicaments_list;

}
}
