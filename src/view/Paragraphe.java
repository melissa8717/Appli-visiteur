package view;



import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
//import java.awt.GraphicsEnvironment;
//import java.awt.Font;
//import java.awt.GraphicsEnvironment;

public class Paragraphe extends JPanel {

	private static final long serialVersionUID = 1L;

	/*
	 * class pour créé un paragraphe
	 */
	public Paragraphe(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		JLabel texte = new JLabel();
		texte = new JLabel(txt);
		texte.setForeground(new Color(0,0,0));
		texte.setFont(font);
		//this.setBounds(102,102,500,500);
		this.setOpaque(false);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}
	
}
