package view.agenda;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.User;


public class test extends JFrame {

			
			public test() {
				
				
				this.setBounds(500,50,500,500/16*9);
				this.setTitle("Accueil");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setBackground(Color.pink);
				
				JLabel bienvenue = new JLabel("Bienvenu Copain Suisse");
				
				JPanel panel[]= {new JPanel()};
				
				panel[1].add(bienvenue);
			
				
				for (int i = 1; i < 8; i++) {
					panel[i].setOpaque(false);panel[0].add(panel[i]);
				}
				
				BoxLayout b= new BoxLayout(panel[0], BoxLayout.Y_AXIS);
				
				this.setContentPane(panel[0]);
				panel[0].setBackground(Color.green);
				
				this.setVisible(true);
				
			
			}
	}
		



