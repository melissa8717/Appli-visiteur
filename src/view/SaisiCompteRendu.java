package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import controller.*;

import view.TitrePrincipale;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import java.util.Date;

public class SaisiCompteRendu extends JPanel implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	public SaisiCompteRendu() {
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		TitrePrincipale titre_page = new TitrePrincipale("Saisie Compte Rendu");

		JLabel Titre = new JLabel("Compte Rendu");
		JLabel Medecin = new JLabel("Choix du médecin");
		JLabel Date = new JLabel("Date de la visite");
		JLabel Motif = new JLabel("Motif de la visite");
		JLabel Comment = new JLabel("Met un pouce bleu et laisse un commentaire !");
		JLabel Echantillons = new JLabel("Nombre d'échantillons laissés au practitien");
		JTextField input[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField(), new JTextField() };
		JFormattedTextField nbrEchantillonsField = new JFormattedTextField();
		String[] items = { "Médecin1", "Médecin2", "Médecin3", "Médecin4" };
		String[] MotifItems = { "Motif1", "Motif2", "Motif3", "Motif4" };
		JComboBox<?> BoxMedChoice = new JComboBox<Object>(items);
		JComboBox<?> BoxMotifChoice = new JComboBox<Object>(MotifItems);
		JButton Valider = new JButton("Valider");

		Font font = new Font("Arial", Font.BOLD, 20);
		Titre.setFont(font);

		JPanel espacement[] = { new JPanel(new FlowLayout(FlowLayout.LEFT)) };

		JPanel[] panel = { new JPanel(new FlowLayout(FlowLayout.LEFT)) };

		for (int i = 1; i < 10; i++) {
			panel = ajoutemoi(panel, new JPanel(new FlowLayout(FlowLayout.LEFT)));
			espacement = ajoutemoi(espacement, new JPanel(new FlowLayout(FlowLayout.LEFT)));
		}

		for (int i = 0; i < 5; i++) {
			input[i].setColumns(20);
		}

		String oui = " Insérez vos commentaires ici... \n\n (10 caractères minimum)";
		JTextArea inputArea = new JTextArea(oui, 5, 25);
		inputArea.setBorder(BorderFactory.createLineBorder(Color.black));
		inputArea.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (inputArea.getText() == oui) {
					inputArea.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (inputArea.getText() != oui && inputArea.getText().length() >= 10) {
					inputArea.setText("je marche <3");
				} else {
					inputArea.setText(oui);
				}
			}
		});

		this.setVisible(true); // Ceci après l'initialisation des input pour éviter des bugs d'affichage à cause de setColumns

		for (int i = 1; i < 9; i++) {
			espacement[i].setPreferredSize(new Dimension(50, 50));
			espacement[i].setOpaque(false);
			panel[i].add(espacement[i - 1]);
		}

		// TODO METTRE LE MENU DE NAVIGATION ICI
		espacement[9].setPreferredSize(new Dimension(100, 0));
		espacement[9].setOpaque(false);
		panel[0].add(espacement[9]);

		panel[1].add(Titre);
		panel[2].add(Medecin);
		panel[3].add(Date);
		panel[3].add(datePicker);
		panel[4].add(Motif);
		panel[6].add(Comment);
		// Le nombre d'ï¿½chantillons est paticulier car on ne doit qu'accepter que les
		// int
		panel[5].add(Echantillons);
		panel[5].add(nbrEchantillonsField);
		nbrEchantillonsField.setColumns(3);

		panel[2].add(BoxMedChoice);
		panel[4].add(BoxMotifChoice);

		for (int i = 1; i < 9; i++) {
			// panel[i].setPreferredSize(new Dimension(getWidth() / 2,50));
			if (i == 5) {
				// panel[i].setPreferredSize(new Dimension(getWidth() / 2,40));
			}
			panel[i].setOpaque(false);
			panel[9].add(panel[i]);
		}

		// panel[7].setPreferredSize(new Dimension(getWidth(), 100));
		panel[7].add(espacement[6]);
		panel[7].add(inputArea);

		espacement[7].setPreferredSize(new Dimension(200, 0));
		panel[8].add(espacement[7]);
		panel[8].add(Valider);

		panel[9].setPreferredSize(new Dimension(1000, 500));
		panel[9].setOpaque(true);
		panel[9].setBackground(Color.white);
		panel[0].add(panel[9]);
		panel[0].setBackground(new java.awt.Color(102, 163, 211));
		this.add(titre_page);
		this.add(panel[9]);

		// TODO fonction qui permet de rï¿½cupï¿½rer que des int pour un input
		nbrEchantillonsField.addKeyListener(new KeyAdapter() { // quand la personne ï¿½crit
			public void keyTyped(KeyEvent e) {
				char lettre = e.getKeyChar(); // rï¿½cupï¿½re le caractï¿½re
				if ((lettre < '0' || lettre > '9') && lettre != '\b') { // Si le caractï¿½re est autre chose qu'un
																		// nombre
					e.consume(); // le supprime
					System.out.println("lettre invalide");
				}
			}
		});
		nbrEchantillonsField.setDocument(new JTextFieldLimit(2));
	}

	private JPanel[] ajoutemoi(JPanel[] MonArray, JPanel NouveauPanel) {
		/*
		 * Fonction qui sert ï¿½ rajouter des ï¿½lï¿½ments dans un tableau. (ici nous
		 * ajoutons des JPanels)
		 */
		int newSize = MonArray.length + 1;
		JPanel[] tempArray = new JPanel[newSize];
		// Nous crï¿½ï¿½ons un array temporaire avec la taille de l'array actuelle

		for (int i = 0; i < MonArray.length; i++) {
			tempArray[i] = MonArray[i];
			// Pour chaque ï¿½lï¿½ment dans mon array, on ajoute l'ï¿½lï¿½ment dans la
			// nouvelle array temporaire
		}
		tempArray[newSize - 1] = NouveauPanel; // Ici on ajoute le nouveau Jpanel dans l'array
		return tempArray; // on retourne notre nouvelle array
	}

	class JTextFieldLimit extends PlainDocument {
		/*
		 * Classe permettant de limiter le nombre de caractï¿½re dans un JtextField
		 *
		 */
		private static final long serialVersionUID = 1L;
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null) {
				return;
			}
			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

	

}