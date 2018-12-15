package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.Date;
import java.awt.Dimension;


public class CarteCompteRendu extends JPanel {
    public CarteCompteRendu(String medecin, String nomMedicament) {

	public CarteCompteRendu(Date date, String medecin, String nomMedicament) {

        
        
        Conteneur conteneur = new Conteneur(); 

        JLabel JLabelDate = new JLabel(String.valueOf(date));
        JLabelDate.setPreferredSize(new Dimension(1000, 150));
        JLabel JLabelMedecin = new JLabel(medecin);
        JLabel JLabelNomMedicament = new JLabel(nomMedicament);

        
        
        conteneur.add(JLabelDate);
        conteneur.add(JLabelMedecin);
        conteneur.add(JLabelNomMedicament);

        this.add(conteneur);
    }
}