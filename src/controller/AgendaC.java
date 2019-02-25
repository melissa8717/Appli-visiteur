package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import view.Popup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.util.SystemOutLogger;

import com.mysql.jdbc.Connection;

import model.Connecteur;

public class AgendaC {
	public static boolean ajoutEvenement(String rapport, String dateDebutEvent, String dateFinEvent, String heureDebut,
			String heureFinC) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Requête d'insertion en base du compte rendu */
			String requete = "INSERT INTO"
					+ "`agenda`( `evenement`, `dateDebut`, `dateFin`, `idUtilisateur`, `heureDebut`, `heureFin`)"
					+ "VALUES ('" + rapport + "','" + dateDebutEvent + "','" + dateFinEvent + "','"
					+ connectionControleur.id_utilisateur + "', '" + heureDebut + "' ,'" + heureFinC + "')";
			statement = conn.createStatement();

			/* Exécution de la reqête */
			int rep = statement.executeUpdate(requete);
			Popup Succes = new Popup("Ajout", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("L'évenement a été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));
			return (rep > 0);

		}

		catch (Exception e) {
			Popup NotSucces = new Popup("Ajout : erreur", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été ajouté correctement !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			labelNotSucces.setFont(font);
			NotSucces.add(panelNotSucces);
			panelNotSucces.add(labelNotSucces);

			panelNotSucces.setBackground(new Color(235, 77, 75));
			panelNotSucces.setForeground(new Color(191, 48, 48));
			return false;
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}

	public static List<List> consultationEvenement(int IdUser) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_CE = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();

			/* Requête récupérat les comptes rendus du user connecté */
			String requete = "SELECT evenement, dateDebut, dateFin, idUtilisateur, heureDebut, heureFin, idAgenda from agenda where idUtilisateur="
					+ IdUser + ";";
			resultat = statement.executeQuery(requete);

			/* Exécution d'une requête de lecture */

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				List<String> event = new ArrayList<String>();

				String evenement = resultat.getString("evenement");
				Date dateD = resultat.getDate("dateDebut");
				Date dateFin = resultat.getDate("dateFin");
				int idUtilisateur = resultat.getInt("idUtilisateur");
				String heureDebut = resultat.getString("heureDebut");
				String heureFin = resultat.getString("heureFin");
				int idAgenda = resultat.getInt("idAgenda");

				// convertir une date en string
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String strDateD = dateFormat.format(dateD);
				String strDateF = dateFormat.format(dateFin);

				event.add(evenement);
				event.add(strDateD);
				event.add(strDateF);
				event.add(Integer.toString(idUtilisateur));
				event.add(heureDebut);
				event.add(heureFin);
				event.add(Integer.toString(idAgenda));
				List_CE.add(event);

			}

			return List_CE;

		}

		catch (Exception e) {

			return null;
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}

	public static List<List> consultationLastEvenement(int IdUser) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_CLE = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			System.out.println("connection" + conn);
			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();

			/* Requête récupérat les comptes rendus du user connecté */
			String requete = "SELECT evenement, dateDebut, dateFin, idUtilisateur, heureDebut, heureFin, idAgenda from agenda where idUtilisateur="
					+ IdUser + " order by idAgenda DESC limit 1;";
			resultat = statement.executeQuery(requete);

			/* Exécution d'une requête de lecture */

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				List<String> event = new ArrayList<String>();

				String evenement = resultat.getString("evenement");
				Date dateD = resultat.getDate("dateDebut");
				Date dateFin = resultat.getDate("dateFin");
				int idUtilisateur = resultat.getInt("idUtilisateur");
				String heureDebut = resultat.getString("heureDebut");
				String heureFin = resultat.getString("heureFin");
				int idAgenda = resultat.getInt("idAgenda");

				// convertir une date en string
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String strDateD = dateFormat.format(dateD);
				String strDateF = dateFormat.format(dateFin);

				event.add(evenement);
				event.add(strDateD);
				event.add(strDateF);
				event.add(Integer.toString(idUtilisateur));
				event.add(heureDebut);
				event.add(heureFin);
				event.add(Integer.toString(idAgenda));
				List_CLE.add(event);

			}

			return List_CLE;

		}

		catch (Exception e) {

			return null;
		}

	}

	public static boolean countEvenement(int IdUser, String debut) {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List<List> List_Count = new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();

			/* Requête récupérat les comptes rendus du user connecté */
			String requete = "SELECT COUNT(idAgenda) as event FROM agenda where dateDebut = " + debut
					+ " and idUtilisateur =" + IdUser + ";";
			resultat = statement.executeQuery(requete);

			/* Exécution d'une requête de lecture */

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				// return resultat.getInt(1);

			}
			return true;

		}

		catch (Exception e) {

			return true;

		} finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}

	public static boolean updateEvent(int idAgendaInt, String textEvent, String dateDebut, String dateFin,
			int idUtilisateur, String heureDebut, String heureFin) {
		Statement statement = null;
		int resultat = 0;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			String requete = "UPDATE agenda SET idAgenda=" + idAgendaInt + ",evenement='" + textEvent + "',dateDebut='"
					+ dateDebut + "',dateFin='" + dateFin + "',idUtilisateur=" + idUtilisateur + ",heureDebut='"
					+ heureDebut + "',heureFin='" + heureFin + "' WHERE idAgenda=" + idAgendaInt + ";";
			resultat = statement.executeUpdate(requete);

			Popup Succes = new Popup("Mis à jour", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("L'évenement a été correctement mise à jour !");
			Font font = new Font("Open Sans", Font.PLAIN, 30);
			// Définition du style
			labelSucces.setFont(font);
			Succes.add(panelSucces);
			panelSucces.add(labelSucces);

			panelSucces.setBackground(new Color(85, 239, 196));
			panelSucces.setForeground(new Color(96, 191, 96));

			return true;
		} catch (Exception e) {

			Popup NotSucces = new Popup("L'évenement n'a pas été correctement mise à jour !", 800, 100);

			JPanel panelNotSucces = new JPanel();
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été correctement mise à jour !");
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

	public static boolean suppressionEvent(int idAgendaInt) {
		Statement statement = null;
		try {
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();
			String requete = "DELETE FROM agenda WHERE idAgenda = " + idAgendaInt + ";";

			int resultat = statement.executeUpdate(requete);
			Popup Succes = new Popup("Suppression :", 800, 200);

			JPanel panelSucces = new JPanel();
			JLabel labelSucces = new JLabel("L'évenement a été correctement supprimé !");
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
			JLabel labelNotSucces = new JLabel("L'évenement n'a pas été correctement supprimé !");
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

	public static List selectVisiteur() {
		Statement statement = null;
		ResultSet resultat = null;
		try {
			List List_SV = (List) new ArrayList<List>();
			Connection conn = (Connection) Connecteur.connecteurUL;

			/* Création de l'objet gérant les requêtes */
			statement = conn.createStatement();

			/* Requête récupérat les comptes rendus du user connecté */
			String requete = "SELECT CONCAT( nom,\" \", prenom) as nomVisiteur, idUtilisateur, role from utilisateur where role = 1";
			resultat = statement.executeQuery(requete);
			/* Exécution d'une requête de lecture */

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				ArrayList<String> visiteurs = (ArrayList<String>) new ArrayList<String>();

				String nomVisiteur = resultat.getString("nomVisiteur");
				int role = resultat.getInt("role");

				visiteurs.add(nomVisiteur);
				visiteurs.add(Integer.toString(role));

				((ArrayList<List>) List_SV).add((List) visiteurs);

			}
			if (((ArrayList<List>) List_SV).isEmpty()) {
			}

			return List_SV;

		}

		catch (Exception e) {
			return null;
		}

		finally {
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

	}
}