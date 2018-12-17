package view;

import java.awt.Color;
import javax.swing.JPanel;


public class AlertError extends JPanel{
	/**
	 * 	Cette classe permet de générer un JPanel avec un fond rouge contenant un message
	 * 	Inspiration : alert-danger de Bootstrap
	 */
	private static final long serialVersionUID = 1L;

	public AlertError(String texte) {
		JPanel messageError = new JPanel();
		messageError.setOpaque(true);
		messageError.setBackground(new Color(235, 77, 75));
		
		Paragraphe textError = new Paragraphe(texte);
		textError.setForeground(new Color(235, 47, 6));
		
		messageError.add(textError);
		
		add(messageError);
	}
}
