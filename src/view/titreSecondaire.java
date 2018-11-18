package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class titreSecondaire extends JPanel {
	/*
	 * class pour cr�� un titre secondaire 
	 */
	public titreSecondaire(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 36);
		JLabel texte = new JLabel();
		texte = new JLabel(txt , JLabel.CENTER);
		texte.setFont(font);
		texte.setForeground(new Color(18,62,108));
		this.setOpaque(false);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}

}
