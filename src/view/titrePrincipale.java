package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class titrePrincipale extends JPanel {
	/*
	 * class pour créé un titre pricipale
	 */
	public titrePrincipale(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 60);
		JLabel texte = new JLabel();
		texte = new JLabel(txt, JLabel.CENTER);
		texte.setFont(font);
		texte.setForeground(new Color(18,62,108));
		this.setOpaque(false);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}
}
