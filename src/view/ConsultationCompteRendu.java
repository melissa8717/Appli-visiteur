package view;

import javax.swing.JPanel;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import javax.swing.JComboBox;


public class ConsultationCompteRendu extends JPanel {
    public ConsultationCompteRendu() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        TitrePrincipale mesSaisiesCompteRendu = new TitrePrincipale("Mes saisies compte rendu");
        mesSaisiesCompteRendu.setPreferredSize(new Dimension(1500, 100));

        JPanel pickDate = new JPanel();
        pickDate.setOpaque(false);
        pickDate.setPreferredSize(new Dimension(1300, 70));

        Date dateEntree = new Date();
        Date[] dates = {dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree, dateEntree};
        String[] medecins = {"Jean", "Michel", "Daniel", "Melissa", "Arthur", "Bernard", "Jeannot", "Nicolas", "Raoul", "Adrien", "Florian", "Lucas"};
        String[] medicaments = {"Lexomil", "Texomil", "Oxomil", "Otexomil", "Rexomil", "Yexomil", "Jexomil", "Mexomil", "Xexomil", "Ixomil", "Uxomil"};

        JComboBox<?> select = new JComboBox<Date>(dates);

        JPanel cartes = new JPanel();

        for (Integer i = 0; i < 4; i++) {
            this.add(new CarteCompteRendu(dates[i], medecins[i], medicaments[i]));
        }


        CarteCompteRendu carteCompteRendu = new CarteCompteRendu(date, "Michel Boujenah", "Lexomil");

        this.add(mesSaisiesCompteRendu);
        pickDate.add(select);
        this.add(pickDate);
        this.add(carteCompteRendu);
    }
}
