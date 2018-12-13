package view;

import javax.swing.JFrame;

public class launchView extends JFrame{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fenetre();
	}

	public static void fenetre() {
		JFrame vue= new JFrame();
		vue.add(new Connexion());
		vue.setBounds(50,50,500,500);
		vue.setVisible(true);
		vue.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

