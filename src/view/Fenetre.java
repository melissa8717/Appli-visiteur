package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import model.User;
import view.Accueil;
import view.ConsultationPowerPoint;
import view.CreationMessagerie;


public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelActif = new JPanel();
	public User u = new User();
	
	public Fenetre() {
        Color bleu_clair = new java.awt.Color(102, 163, 211);
        // Declaration du style pour les JMenu (elements menu) et les JMenuItem (elements sous-menu)
    	Font p = new Font("open-sans", Font.PLAIN, 24);
		UIManager.put("Menu.font", p);
		UIManager.put("MenuItem.font", p);
        UIManager.put("Menu.foreground", Color.white);
        UIManager.put("Menu.background", bleu_clair);
	    UIManager.put("MenuItem.background", new Color (0, 63, 128));
	    UIManager.put("MenuItem.foreground", Color.white);
	    UIManager.put("Menu.selectionBackground", new Color (0, 63, 128));
	    UIManager.put("Menu.selectionForeground", Color.white);
	    UIManager.put("MenuItem.selectionBackground", new Color (0, 63, 128));
	    UIManager.put("MenuItem.selectionForeground", Color.white);
	    UIManager.put("Panel.background", bleu_clair);
    	
	    // Definition du style de la fenetre
	    setBounds(50, 50, 1000, 500);
	    setMinimumSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Generation de la vue connexion
        JPanel panel_connexion = new Connexion(this.u, this);

        // Definition du panel par défaut
        panelActif = panel_connexion;

        // Ajout + affichage de la fenetre
        add(panelActif, BorderLayout.CENTER);
        
        setVisible(true);  
    }
	
	public int[] ObtenirDimensionFenetre() {
		int[] dimension = new int[2];

		dimension[0] = this.getSize().width;
		dimension[1] = this.getSize().height;
		
		return dimension;
    }
    
    public void refreshConnexion(boolean connected){
    	// Generation de la fentre final si utilisateur connecté
        if(connected){
	    	// Generation des vues
	            // Menu Accueil
	        JPanel panel_accueil = new Accueil();
	            // Menu Compte Rendu
	        final JPanel panel_compte_rendu = new JPanel();
	        final JPanel panel_compte_rendu1 = new SaisiCompteRendu();
	        final JPanel panel_compte_rendu2 = new ConsultationCompteRendu();
	        	// Menu Power Point
	        final JPanel panel_power_point = new JPanel();
	        final JPanel panel_power_point1 = new CreationPowerPoint();
	        final JPanel panel_power_point2 = new ConsultationPowerPoint();
	        	// Menu Messagerie
	        final JPanel panel_messagerie = new JPanel();
	        final JPanel panel_messagerie1 = new CreationMessagerie();
	        final JPanel panel_messagerie2 = new ConsultationMessagerie();
	        	// Menu Agenda
	        final JPanel panel_agenda = new Agenda();
	        	// Menu Medecin
	        final JPanel panel_medecin = new JPanel();
	        final JPanel panel_medecin1 = new CreationMedecin();
	        final JPanel panel_medecin2 = new ConsultationMedecin();
	        	// Menu Utilisateur
	        final JPanel panel_utilisateur = new JPanel();
	        final JPanel panel_utilisateur1 = new CreationUtilisateur();
	        final JPanel panel_utilisateur2 = new ConsultationUtilisateur();
	        	// Menu Deconnexion
	        final JPanel panel_deconnexion = new Deconnexion();
	        	
            // Creation de la barre menu
            JMenuBar menuBar = new JMenuBar();

            // Creation des differents elements du menu + declaration de l'event pour changer l'interface
            JMenu menu1 = new JMenu("Accueil");
        	JMenu menu2 = new JMenu("Compte Rendu");       
        	JMenu menu3 = new JMenu("Power Point");
            JMenu menu4 = new JMenu("Messagerie"); 
            JMenu menu5 = new JMenu("Agenda");
            JMenu menu6 = new JMenu("Informations Médecins");
            JMenu menu7 = new JMenu("Utilisateurs");
            JMenu menu8 = new JMenu("Deconnexion");
            AddMenuAction(menu1, panel_accueil);
            AddMenuAction(menu5, panel_agenda);
            AddMenuAction(menu8, panel_deconnexion);
            
            // Creation des elements du sous-menu + declaration de l'event pour changer l'interface
            JMenuItem item_compte_rendu1 = new JMenuItem("Saisie");     
            JMenuItem item_compte_rendu2 = new JMenuItem("Consultation");
            JMenuItem item_power_point1 = new JMenuItem("Création");
            JMenuItem item_power_point2 = new JMenuItem("Consultation");
            JMenuItem item_messagerie1 = new JMenuItem("Ecrire un message");
            JMenuItem item_messagerie2 = new JMenuItem("Consultation");
            JMenuItem item_medecin1 = new JMenuItem("Ajouter un medecin");
            JMenuItem item_medecin2 = new JMenuItem("Consulter la liste des medecins");
            JMenuItem item_utilisateur1 = new JMenuItem("Ajouter un utilisateur");
            JMenuItem item_utilisateur2 = new JMenuItem("Consultation de la liste des utilisateurs");
            AddMenuItemAction(item_compte_rendu1, panel_compte_rendu1);
            AddMenuItemAction(item_compte_rendu2, panel_compte_rendu2);
            AddMenuItemAction(item_power_point1, panel_power_point1);
            AddMenuItemAction(item_power_point2, panel_power_point2);
            AddMenuItemAction(item_messagerie1, panel_messagerie1);
            AddMenuItemAction(item_messagerie2, panel_messagerie2);
            AddMenuItemAction(item_medecin1, panel_medecin1);
            AddMenuItemAction(item_medecin2, panel_medecin2);
            AddMenuItemAction(item_utilisateur1, panel_utilisateur1);
            AddMenuItemAction(item_utilisateur2, panel_utilisateur2);
            
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
            System.out.println(u.role);
            if(u.role == 1 || u.role==2) {
            	System.out.println("je suis un visiteur ou un délégué, j'ai donc des menu en plus");
            	menuBar.add(menu2);
            	menuBar.add(menu3);
            }
          
            
            
            
            menuBar.add(menu4);
            menuBar.add(menu5);
            menuBar.add(menu6);
            menuBar.add(menu7);
            menuBar.add(menu8);
            
            remove(panelActif);
            panelActif = panel_accueil;
            setJMenuBar(menuBar);
            add(panelActif, BorderLayout.CENTER);
            
            revalidate();
            repaint();
        }
    }

    public void AddMenuAction(JMenu bouton, JPanel panel) {
        bouton.addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent e) {
                remove(panelActif);
                panelActif = panel;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
            @Override public void menuCanceled(MenuEvent e) {/* TODO Auto-generated method stub */}
            @Override public void menuDeselected(MenuEvent e) {/* TODO Auto-generated method stub */}   
        }); 
    }
	
	public void AddMenuItemAction(JMenuItem bouton, JPanel panel) {
		bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                remove(panelActif);
                panelActif = panel;
                add(panelActif, BorderLayout.CENTER);
                
                revalidate();
                repaint();
            }
        });
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