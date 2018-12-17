package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitrePrincipale extends JPanel {
	/*
	 * 	Classe pour créé un titre pricipale
	 */
	private static final long serialVersionUID = 1L;
	
	public TitrePrincipale(String txt) {
		// Création des éléments
		Font font = new Font("Open Sans", Font.PLAIN, 60);
		JLabel texte = new JLabel(txt, JLabel.CENTER);
		// Définition du style
		texte.setFont(font);
		texte.setForeground(new Color(2, 62, 108));

		setOpaque(false);
		setVisible(true);
		setSize(new Dimension(5000, 500));
		add(texte);
	}
}