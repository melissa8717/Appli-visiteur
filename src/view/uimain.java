package view;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.User;

public class uimain extends JFrame {
	

		
		public uimain() {
			
			//private JLabel bienvenu, important, txtImportant, equipe, txtEquipe, activite, txtActivite;
			
			this.setBounds(500,50,500,500/16*9);
			this.setTitle("Accueil");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBackground(Color.pink);
			
			titrePrincipale bienvenue = new titrePrincipale("Bienvenu Copain Suisse");
			titreSecondaire important = new titreSecondaire("Important");
			titreTertiare txtImportant = new titreTertiare("vous puez trop vous ete vire, perche' stai cercando il sole nell'abisso");
			JLabel equipe = new JLabel("Equipe");
			JLabel txtEquipe = new JLabel("Jaque Boulons, Richard Pastel, Liserac");
			JLabel activite = new JLabel("Activite");
			Paragraphe txtActivite = new Paragraphe("manger des petit suisse, voir Martine, Vendre mes enfant, pendre un NAZI, vir un lama.");
			
			JPanel panel[]= {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
			
			panel[1].add(bienvenue);
			panel[2].add(important);
			panel[3].add(txtImportant);
			panel[4].add(equipe);
			panel[5].add(txtEquipe);
			panel[6].add(activite);
			panel[7].add(txtActivite);
			
			for (int i = 1; i < 8; i++) {
				panel[i].setOpaque(false);panel[0].add(panel[i]);
			}
			
			BoxLayout b= new BoxLayout(panel[0], BoxLayout.Y_AXIS);
			
			this.setContentPane(panel[0]);
			panel[0].setBackground(Color.green);
			panel[7].setSize(getMaximumSize());
			this.setVisible(true);
			panel[7].setOpaque(true);
			
			/*User u = new User(59);
			
			//u.Read(1);
			
			System.out.println("userfirstname : " + u.prenom);
			System.out.println("username : " + u.nom);
			System.out.println("userpassword : " + u.password);
			System.out.println("userlogin : " + u.login);
			System.out.println("userid : " + u.id_utilisateur);*/
			
		
		}
}
	
