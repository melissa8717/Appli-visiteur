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
	    
		ResultSet resultat = statement.executeQuery("SELECT nom FROM medicament WHERE nom LIKE '"+LettreSelected+"%';");
	
	    /* Récupération des données du résultat de la requête de lecture */
	    while(resultat.next()) {
            
            nomMedoc = resultat.getString( "nom" );
            medicaments_list.add(nomMedoc);
            
	    }
	    return medicaments_list;
	}catch (Exception ErreurCnxMedocLab) {
			// TODO: handle exception
			System.out.println("Problème de connexion à: 'bdmedocLab'");
		}
	return medicaments_list;

	}
	public static String EffetMedoc(String nomMedicament){
		String effet = null;
		try {
			Connection conn = CnxBDD.connecteurMedocLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
			ResultSet resultat = statement.executeQuery("SELECT REPLACE(REPLACE(effet, CHAR(13), ' '), CHAR(10), ' ') AS effetPropre FROM medicament WHERE nom ='"+nomMedicament+"';");
			
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	            
	            effet = resultat.getString( "effetPropre" );
	            return effet;
		    }
		    else {
		    	System.out.println("Effet non récupérer, nom inexistant ou effet non répertorié");
		    	return effet;
		    }
		    
		}catch (Exception ErreurCnxMedocLab) {
				// TODO: handle exception
				System.out.println("Problème de connexion à: 'bdmedocLab'");
			}
		
		return effet;
	}
	public static String contreIndication(String nomMedicament){
		String contreIndication = null;
		try {
			Connection conn = CnxBDD.connecteurMedocLab();
		    
		    /* Création de l'objet gérant les requêtes */
		    Statement statement = conn.createStatement();
		    
			ResultSet resultat = statement.executeQuery("SELECT  REPLACE(REPLACE(contreIndication, CHAR(13), ' '), CHAR(10), ' ') AS contreIndicationPropre FROM medicament WHERE nom ='"+nomMedicament+"';");
		
		    /* Récupération des données du résultat de la requête de lecture */
		    if(resultat.next()) {
	            
		    	contreIndication = resultat.getString( "contreIndicationPropre" );
	            return contreIndication;
		    }
		    else {
		    	System.out.println("contreIndication non récupérer, nom inexistant ou contreIndication non répertorié");
		    	return contreIndication;
		    }
		    
		}catch (Exception ErreurCnxMedocLab) {
				// TODO: handle exception
				System.out.println("Problème de connexion à: 'bdmedocLab'");
			}
		
		return contreIndication;
	}
}
