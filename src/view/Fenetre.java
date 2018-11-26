package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.Dimension;
//import java.awt.Rectangle;


public class Fenetre extends JFrame {
	
	private static final long serialVersionUID = 1L;

	
	public Fenetre() {


		this.setBounds(100,50,1000,1000/16*9);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("Application Visiteur");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel fenetre = new JPanel();
		this.setContentPane(fenetre);
		fenetre.setBackground(new java.awt.Color(102, 163, 211));
		
		this.setVisible(true);
	}
	
	public int[] ObtenirDimensionFenetre() {
		
		int[] dimension = new int[2];

		dimension[0] = this.getSize().width;
		dimension[1] = this.getSize().height;
		
		return dimension;
	}
}