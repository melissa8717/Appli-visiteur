package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AlertSuccess extends JPanel{
	public AlertSuccess(String texte) {
		JPanel messageSuccess = new JPanel();
		messageSuccess.setOpaque(true);
		messageSuccess.setBackground(new Color(85, 239, 196));
		
		Paragraphe textSuccess = new Paragraphe(texte);
		textSuccess.setForeground(new Color(235, 47, 6));
		
		messageSuccess.add(textSuccess);
		
		this.add(messageSuccess);
	}
}
