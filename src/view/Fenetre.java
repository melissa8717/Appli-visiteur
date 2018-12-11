package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.Accueil;
import view.ConsultationPowerPoint;
import view.CreationMessagerie;


public class Fenetre extends JFrame {
	private JPanel panelActif = new JPanel();

	public Fenetre() {
        // Declaration du style pour les JMenu (elements menu) et les JMenuItem (elements sous-menu)
    	Font p = new Font("open-sans", Font.PLAIN, 24);
		UIManager.put("Menu.font", p);
		UIManager.put("MenuItem.font", p);
	    UIManager.put("Menu.foreground", Color.white);
	    UIManager.put("MenuItem.background", new Color (0,63,128));
	    UIManager.put("MenuItem.foreground", Color.white);
	    UIManager.put("Menu.selectionBackground", new Color (0,63,128));
	    UIManager.put("Menu.selectionForeground", Color.white);
	    UIManager.put("MenuItem.selectionBackground", new Color (0,63,128));
	    UIManager.put("MenuItem.selectionForeground", Color.white);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Creation des differentes interfaces (JPanel)
            // Menu Accueil
        final JPanel panel_accueil = new Accueil();
        panel_accueil.setBackground(Color.red);
        	// Connexion
        final JPanel panel_connexion = new Connexion();
        panel_connexion.setBackground(Color.red);
            // Menu Compte Rendu
        final JPanel panel_compte_rendu = new JPanel();
        panel_compte_rendu.setBackground(Color.red);
        final JPanel panel_compte_rendu1 = new SaisiCompteRendu();
        panel_compte_rendu1.setBackground(Color.red);
        final JPanel panel_compte_rendu2 = new ConsultationCompteRendu();
        panel_compte_rendu2.setBackground(Color.red);

        final JPanel panel_power_point = new JPanel();
        panel_power_point.setBackground(Color.red);
        final JPanel panel_power_point1 = new CreationPowerPoint();
        panel_power_point1.setBackground(Color.red);
        final JPanel panel_power_point2 = new ConsultationPowerPoint();
        panel_power_point2.setBackground(Color.red);
        
        final JPanel panel_messagerie = new JPanel();
        final JPanel panel_messagerie1 = new CreationMessagerie();
        final JPanel panel_messagerie2 = new ConsultationMessagerie();
        
        final JPanel panel_agenda = new Agenda();

        final JPanel panel_medecin = new JPanel();
        final JPanel panel_medecin1 = new CreationMedecin();
        final JPanel panel_medecin2 = new ConsultationMedecin();

        final JPanel panel_utilisateur = new JPanel();
        final JPanel panel_utilisateur1 = new CreationUtilisateur();
        final JPanel panel_utilisateur2 = new ConsultationUtilisateur();

        final JPanel panel_deconnexion = new Deconnexion();

        // Definition du panel par défaut
        panelActif = panel_accueil;
        
        // Creation de la barre menu
        JMenuBar menuBar = new JMenuBar();

        // Creation des differents elements du menu + declaration de l'event pour changer l'interface
        JMenu menu1 = new JMenu("Accueil");
        JMenu menu2 = new JMenu("Compte Rendu");
        JMenu menu3 = new JMenu("Power Point");
        JMenu menu4 = new JMenu("Messagerie"); 
        JMenu menu5 = new JMenu("Agenda");
        JMenu menu6 = new JMenu("Informations M�decins");
        JMenu menu7 = new JMenu("Utilisateurs");
        JMenu menu8 = new JMenu("Deconnexion");
        menu1.addMenuListener(new MenuListener(){
        	@Override
        	public void menuSelected(MenuEvent e) {
        		remove(panelActif);
        		panelActif = panel_accueil;
        		add(panelActif, BorderLayout.CENTER);
        		
        		revalidate();
        		repaint();
        		
        	}
        	@Override public void menuCanceled(MenuEvent e) {/* TODO Auto-generated method stub */}
        	@Override public void menuDeselected(MenuEvent e) {/* TODO Auto-generated method stub */}        	
        });
        menu5.addMenuListener(new MenuListener(){
        	@Override
        	public void menuSelected(MenuEvent e) {
        		remove(panelActif);
        		panelActif = panel_agenda;
        		add(panelActif, BorderLayout.CENTER);
        		
        		revalidate();
        		repaint();
        		
        	}
        	@Override public void menuCanceled(MenuEvent e) {/* TODO Auto-generated method stub */}
        	@Override public void menuDeselected(MenuEvent e) {/* TODO Auto-generated method stub */}   
        });
        menu8.addMenuListener(new MenuListener(){
        	@Override
        	public void menuSelected(MenuEvent e) {
        		remove(panelActif);
        		panelActif = panel_deconnexion;
        		add(panelActif, BorderLayout.CENTER);
        		
        		revalidate();
        		repaint();
        		
        	}
        	@Override public void menuCanceled(MenuEvent e) {/* TODO Auto-generated method stub */}
        	@Override public void menuDeselected(MenuEvent e) {/* TODO Auto-generated method stub */}   
        });
        
        
        // Creation des elements du sous-menu + declaration de l'event pour changer l'interface
        JMenuItem item_compte_rendu1 = new JMenuItem("Saisie");     
        JMenuItem item_compte_rendu2 = new JMenuItem("Consultation");
        JMenuItem item_power_point1 = new JMenuItem("Cr�ation");
        JMenuItem item_power_point2 = new JMenuItem("Consultation");
        JMenuItem item_messagerie1 = new JMenuItem("Ecrire un message");
        JMenuItem item_messagerie2 = new JMenuItem("Consultation");
        JMenuItem item_medecin1 = new JMenuItem("Ajouter un medecin");
        JMenuItem item_medecin2 = new JMenuItem("Consulter la liste des medecins");
        JMenuItem item_utilisateur1 = new JMenuItem("Ajouter un utilisateur");
        JMenuItem item_utilisateur2 = new JMenuItem("Consultation de la liste des utilisateurs");
        item_compte_rendu1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_compte_rendu1;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_compte_rendu2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_compte_rendu2;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_power_point1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_power_point1;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_power_point2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_power_point2;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_messagerie1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_messagerie1;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_messagerie2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_messagerie2;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_medecin1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_medecin1;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_medecin2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_medecin2;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_utilisateur1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_utilisateur1;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        item_utilisateur2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel_utilisateur2;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
        
        menu2.add(item_compte_rendu1);
        menu2.add(item_compte_rendu2);
        menu3.add(item_power_point1);
        menu3.add(item_power_point2);
        menu4.add(item_messagerie1);
        menu4.add(item_messagerie2);
        menu6.add(item_medecin1);
        menu6.add(item_medecin2);
        menu7.add(item_utilisateur1);
        menu7.add(item_utilisateur2);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
        menuBar.add(menu5);
        menuBar.add(menu6);
        menuBar.add(menu7);
        menuBar.add(menu8);

        add(panel_accueil, BorderLayout.CENTER);
        setJMenuBar(menuBar);
        setSize(1100, 500);
        setVisible(true);
    }
	
	public int[] ObtenirDimensionFenetre() {
		
		int[] dimension = new int[2];

		dimension[0] = this.getSize().width;
		dimension[1] = this.getSize().height;
		
		return dimension;
	}

	public static void main(String... args){
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Fenetre();
            }
        });
    }
}