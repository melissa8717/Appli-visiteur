package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class AlertError extends JPanel{
	public AlertError(String texte) {
		JPanel messageError = new JPanel();
		messageError.setOpaque(true);
		messageError.setBackground(new Color(235, 77, 75));
		
		Paragraphe textError = new Paragraphe(texte);
		textError.setForeground(new Color(191, 48, 48));
		
		messageError.add(textError);
		
		this.add(messageError);
	}
}
