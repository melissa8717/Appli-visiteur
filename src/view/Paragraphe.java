package view;



import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

public class Paragraphe extends JPanel {
	public Paragraphe(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 18);
		JLabel texte = new JLabel();
		texte = new JLabel(txt);
		texte.setForeground(new Color(0,0,0));
		texte.setFont(font);
		this.setBounds(102,102,500,500);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}
	
}
