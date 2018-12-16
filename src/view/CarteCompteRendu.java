package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.Date;
import java.util.List;
import java.awt.Component;
import java.awt.Dimension;


public class CarteCompteRendu extends JPanel {
    public CarteCompteRendu(String someFrame, String medicament) {

        
        
        Conteneur conteneur = new Conteneur(); 

      //  JLabel JLabelDate = new JLabel(String.valueOf(date));
      //  JLabelDate.setPreferredSize(new Dimension(1000, 150));
       // JLabel JLabelMedecin = new JLabel(medecin);
        JLabel JLabelNomMedicament = new JLabel(someFrame);

        
        
       // conteneur.add(JLabelDate);
       // conteneur.add(JLabelMedecin);
       // conteneur.add(JLabelNomMedicament);
        conteneur.add(medicament);

        this.add(conteneur,medicament);
    }

	
}