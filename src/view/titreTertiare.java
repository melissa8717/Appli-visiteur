package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class titreTertiare extends JPanel {
	public titreTertiare(String txt) {
		Font font = new Font("Open Sans", Font.PLAIN, 24);
		JLabel texte = new JLabel();
		texte = new JLabel(txt);
		texte.setFont(font);
		texte.setForeground(new Color(102,163,211));
		this.setBounds(102,102,500,500);
		this.setVisible(true);
		this.setSize(getMaximumSize());
		this.add(texte);
	}

}
