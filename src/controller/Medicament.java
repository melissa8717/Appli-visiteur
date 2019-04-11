package controller;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Connection;

import model.Connecteur;
import view.Popup;

public class Medicament {

		public static  List<List> consultationMedoc() {
			
			try {
				List<List> List_Medoc = new ArrayList<List>();
				Connection conn = (Connection) Connecteur.connecteurML;

			    /* Création de l'objet gérant les requêtes */
				Statement statement = conn.createStatement();
				
				/* Requête récupérat les comptes rendus du user connecté */
			    String requete = "SELECT  idMedicament ,  depotLegal ,  nom ,  effet ,  contreIndication ,  composition ,  quantite ,  unite ,  typeIndividu ,  urlImage ,  famille ,  prixEchantillon ,  notice  FROM  medicament  ;";
				ResultSet resultat = statement.executeQuery(requete);
				
			
			    /* Récupération des données du résultat de la requête de lecture */
			    while(resultat.next()) {
					List<String> medoc = new ArrayList<String>();
					
					int idMedicament = resultat.getInt("idMedicament");
					String depotLegal = resultat.getString("depotLegal");
					String nom = resultat.getString("nom");
					String contreInd = resultat.getString("contreIndication");
					String compo = resultat.getString("composition");
					int qte = resultat.getInt("quantite");
					String unite = resultat.getString("unite");
					String type = resultat.getString("typeIndividu");
					String famille = resultat.getString("famille");							
					int prix = resultat.getInt("prixEchantillon");
					String notice = resultat.getString("notice");
					String effet = resultat.getString("effet");


		            medoc.add(Integer.toString(idMedicament));
		            medoc.add(depotLegal);
		            medoc.add(nom);
		            medoc.add(contreInd);
		            medoc.add(compo);
		            medoc.add(Integer.toString(qte));
		            medoc.add(unite);
		            medoc.add(type);
		            medoc.add(famille);
		            medoc.add(Integer.toString(prix));
		            medoc.add(notice);
		            medoc.add(effet);
		          
					List_Medoc.add(medoc);
		           
				}

				return List_Medoc;

			}

			catch (Exception e) {

				return null;
			}

			
		
		}
		
		public static  List<List> consultationThisMedoc(String medocSelectStr) {
			
			try {
				List<List> List_Medoc = new ArrayList<List>();
				Connection conn = (Connection) Connecteur.connecteurML;

			    /* Création de l'objet gérant les requêtes */
				Statement statement = conn.createStatement();
				
				/* Requête récupérat les comptes rendus du user connecté */
			    String requete = "SELECT  idMedicament ,  depotLegal ,  nom ,  effet ,  contreIndication ,  composition ,  quantite ,  unite ,  typeIndividu ,  urlImage ,  famille ,  prixEchantillon ,  notice  FROM  medicament  where idMedicament =" + medocSelectStr +" ;";
			    ResultSet resultat = statement.executeQuery(requete);

			
			    /* Récupération des données du résultat de la requête de lecture */
			    while(resultat.next()) {
					List<String> medoc = new ArrayList<String>();
					
					int idMedicament = resultat.getInt("idMedicament");
					String depotLegal = resultat.getString("depotLegal");
					String nom = resultat.getString("nom");
					String contreInd = resultat.getString("contreIndication");
					String compo = resultat.getString("composition");
					int qte = resultat.getInt("quantite");
					String unite = resultat.getString("unite");
					String type = resultat.getString("typeIndividu");
					String famille = resultat.getString("famille");							
					int prix = resultat.getInt("prixEchantillon");
					String notice = resultat.getString("notice");
					String effet = resultat.getString("effet");

		            medoc.add(Integer.toString(idMedicament));
		            medoc.add(depotLegal);
		            medoc.add(nom);
		            medoc.add(contreInd);
		            medoc.add(compo);
		            medoc.add(Integer.toString(qte));
		            medoc.add(unite);
		            medoc.add(type);
		            medoc.add(famille);
		            medoc.add(Integer.toString(prix));
		            medoc.add(notice);
		            medoc.add(effet);
		          
					List_Medoc.add(medoc);

		           
				}

				return List_Medoc;

			}

			catch (Exception e) {

				return null;
			}

			
		
		}
		
		public static  List<List> consultationLastMedoc() {
			
			try {
				List<List> List_Medoc = new ArrayList<List>();
				Connection conn = (Connection) Connecteur.connecteurML;


			    /* Création de l'objet gérant les requêtes */
				Statement statement = conn.createStatement();
				
				/* Requête récupérat les comptes rendus du user connecté */
			    String requete = "SELECT `idMedicament`, `depotLegal`, `nom`, `effet`, `contreIndication`, `composition`, `quantite`, `unite`, `typeIndividu`, `urlImage`, `famille`, `prixEchantillon`, `notice` FROM `medicament` order by idMedicament desc limit 1 ;";
				ResultSet resultat = statement.executeQuery(requete);
				
				
			
			    /* Récupération des données du résultat de la requête de lecture */
			    while(resultat.next()) {
					List<String> medoc = new ArrayList<String>();
					
					int idMedicament = resultat.getInt("idMedicament");
					String depotLegal = resultat.getString("depotLegal");
					String nom = resultat.getString("nom");
					String contreInd = resultat.getString("contreIndication");
					String compo = resultat.getString("composition");
					int qte = resultat.getInt("quantite");
					String unite = resultat.getString("unite");
					String type = resultat.getString("typeIndividu");
					String famille = resultat.getString("famille");							
					int prix = resultat.getInt("prixEchantillon");
					String notice = resultat.getString("notice");
					String effet = resultat.getString("effet");


		            medoc.add(Integer.toString(idMedicament));
		            medoc.add(depotLegal);
		            medoc.add(nom);
		            medoc.add(contreInd);
		            medoc.add(compo);
		            medoc.add(Integer.toString(qte));
		            medoc.add(unite);
		            medoc.add(type);
		            medoc.add(famille);
		            medoc.add(Integer.toString(prix));
		            medoc.add(notice);
		            medoc.add(effet);
		          
					List_Medoc.add(medoc);

		           
				}

				return List_Medoc;

			}

			catch (Exception e) {

				return null;
			}

			
		
		}
		
public static  List<List> consultationNotice(String idMedoc) {
			
			try {
				List<List> List_Notice = new ArrayList<List>();
				Connection conn = (Connection) Connecteur.connecteurML;


			    /* Création de l'objet gérant les requêtes */
				Statement statement = conn.createStatement();
				
				/* Requête récupérat les comptes rendus du user connecté */
			    String requete = "SELECT  idMedicament ,   notice  FROM  medicament  where idMedicament = " + idMedoc + ";";
				ResultSet resultat = statement.executeQuery(requete);
						
			    /* Récupération des données du résultat de la requête de lecture */
			    while(resultat.next()) {
					List<String> note = new ArrayList<String>();
					
					int idMedicament = resultat.getInt("idMedicament");
					String notice = resultat.getString("notice");

		            note.add(Integer.toString(idMedicament));
		            note.add(notice);
		          
					List_Notice.add(note);

		           
				}

				return List_Notice;

			}

			catch (Exception e) {

				return null;
			}

			
		
		}


	public static boolean updateMedoc(String idMedoc, String depot, String nom,  String  cind, String compo, String qte, String unite, String type, String famille, String prix) {
		int resultat = 0;
		try {
			Connection conn = (Connection) Connecteur.connecteurML;
			/* Création de l'objet gérant les requêtes */
			Statement statement =  conn.createStatement();	

			String requete = "UPDATE medicament SET idMedicament='" + idMedoc + "',    depotLegal='" + depot + "' ,  nom '" + nom + "' ,   contreIndication='" + cind + "' ,  composition='" + compo + "' ,  quantite='" + qte + "' ,  unite='" + unite + "' ,  typeIndividu ='" + type + "',    famille='" + famille + "' ,  prixEchantillon = '" + prix + "'  where idMedicament = '" + idMedoc + "';";
			resultat = statement.executeUpdate(requete);

			System.out.println("i am here");

			Popup Succes = new Popup("Médecin mis à jour", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("Le medicament a été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			return true;
		} catch (Exception e) {
			Popup NotSucces = new Popup("Le medicament n'a pas été correctement mise à jour !", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("Le medicament n'a pas été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;

			} 
		}



public static boolean suppressionMedoc(String idMedoc) {
	Statement statement = null;
	try {
		Connection conn = (Connection) Connecteur.connecteurML;

		/* Création de l'objet gérant les requêtes */
		statement = conn.createStatement();
		String requete = "DELETE FROM medicament WHERE idMedicament = '" + idMedoc + "';";

		int resultat = statement.executeUpdate(requete);
		Popup Succes = new Popup("Suppression :", 800, 200);

		JPanel panelSucces = new JPanel();
		JLabel labelSucces = new JLabel("Le medicament a été correctement supprimé !");
		Font font = new Font("Open Sans", Font.PLAIN, 30);
		// Définition du style
		labelSucces.setFont(font);
		Succes.add(panelSucces);
		panelSucces.add(labelSucces);

		panelSucces.setBackground(new Color(85, 239, 196));
		panelSucces.setForeground(new Color(96, 191, 96));
		return true;
	} catch (Exception e) {

		Popup NotSucces = new Popup("Suppression : erreur ", 800, 100);

		JPanel panelNotSucces = new JPanel();
		JLabel labelNotSucces = new JLabel("Le medicament n'a pas été correctement supprimé !");
		Font font = new Font("Open Sans", Font.PLAIN, 30);
		// Définition du style
		labelNotSucces.setFont(font);
		NotSucces.add(panelNotSucces);
		panelNotSucces.add(labelNotSucces);

		panelNotSucces.setBackground(new Color(235, 77, 75));
		panelNotSucces.setForeground(new Color(191, 48, 48));

		return false;

	} finally {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				/* ignored */}
			}
		}

	}


public static  List<List> consultationEffet(String idMedoc) {
			
			try {
				List<List> List_Effet = new ArrayList<List>();
				Connection conn = (Connection) Connecteur.connecteurML;


			    /* Création de l'objet gérant les requêtes */
				Statement statement = conn.createStatement();
				
				/* Requête récupérat les comptes rendus du user connecté */
			    String requete = "SELECT  idMedicament ,   effet  FROM  medicament  where idMedicament = " + idMedoc + ";";
				ResultSet resultat = statement.executeQuery(requete);
						
			    /* Récupération des données du résultat de la requête de lecture */
			    while(resultat.next()) {
					List<String> effetList = new ArrayList<String>();
					
					int idMedicament = resultat.getInt("idMedicament");
					String effet = resultat.getString("effet");

					effetList.add(Integer.toString(idMedicament));
					effetList.add(effet);
		          
					List_Effet.add(effetList);

		           
				}

				return List_Effet;

			}

			catch (Exception e) {

				return null;
			}

			
		
		}


}		
	

