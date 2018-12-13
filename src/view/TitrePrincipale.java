package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitrePrincipale extends JPanel {
	/*
	 * class pour créé un titre pricipale
	 */
	private static final long serialVersionUID = 1L;
	
	public TitrePrincipale(String txt) {
		System.out.println("1");
		Font font = new Font("Open Sans", Font.PLAIN, 60);
		System.out.println("2");
		JLabel texte = new JLabel(txt, JLabel.CENTER);
		System.out.println("3");
		texte.setFont(font);
		System.out.println("4");
		texte.setForeground(new Color(2, 62, 108));
		System.out.println("5");

		this.setOpaque(false);
		System.out.println("6");

		this.setVisible(true);
		System.out.println("7");
		this.setSize(new Dimension(5000, 500));
		System.out.println("8");
		this.add(texte);
		System.out.println("9");
	}
}