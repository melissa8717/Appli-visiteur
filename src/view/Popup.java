package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Popup extends JFrame {
    public Popup(String title, int width, int height){

		this.setBounds(100,50,1000,1000/16*9);
		
		this.setSize(width, height);

		this.setTitle(title);
		
		JPanel fenetre = new JPanel();
		this.setContentPane(fenetre);
		fenetre.setBackground(new java.awt.Color(102, 163, 211));
		
		this.setVisible(true);
    }
}
