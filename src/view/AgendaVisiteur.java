package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.xmlbeans.impl.soap.Node;

import com.mysql.fabric.xmlrpc.base.Array;

import controller.AgendaC;
import view.agenda.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.Arrays;

public class AgendaVisiteur extends JPanel implements ItemListener{
	
	JPanel p1, p2;
    JComboBox month;
    JComboBox year;
    int days[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String weekdays[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

	public AgendaVisiteur() {
		TitrePrincipale titre = new TitrePrincipale("Agenda des visiteurs");
		List<List> ListVisiteur = (List<List>) AgendaC.selectVisiteur();
        List<String> ListNomVisiteur=new ArrayList<String>();


		
		for(int i=0; i<ListVisiteur.size(); i++) {
			ListNomVisiteur.add((String) ListVisiteur.get(i).get(0));

		}
		
        String nomVisiteur[]=ListNomVisiteur.toArray(new String[0]);

        
		JComboBox<?> visiteurs = new JComboBox<Object>(nomVisiteur);
		visiteurs.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
        });   

		
		JPanel top = new JPanel();
	    top.setPreferredSize(new Dimension(1800, 100));

		
		



	
		


		this.add(titre);
		this.add(top);
		top.add(visiteurs);
		
	
        p1 = new JPanel(); // select mois + an
        //p1.setSize(350, 30);
        //p1.setLayout(new FlowLayout());
        month = new JComboBox();
        for(int i=0;i< months.length;i++)
        {
            month.addItem(months[i]);
        }
        month.addItemListener(this);
        year = new JComboBox();
        for(int i=1980;i<=2099;i++)
        {
            year.addItem(i);
        }
        year.addItemListener(this);
        p1.add(month);
        p1.add(year);
        p2 = new JPanel();
        p2.setLayout(new GridLayout(0,7,5,5));
        Date date = new Date();
        drawCalendar(months[date.getMonth()], (1900+date.getYear()));
        year.setSelectedItem((1900+date.getYear()));
        month.setSelectedItem(months[date.getMonth()]);
    
        setVisible(true);
        //setBounds(200, 200, 350, 300);
        setSize(230,220);
    }
  

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            drawCalendar((String)month.getSelectedItem(), (Integer)year.getSelectedItem());
        }
    }
    
    public void drawCalendar(String inputMonth, int inputYear)
    {
        p2.removeAll();
        for(int i=0;i< weekdays.length;i++)
        {
            JLabel label = new JLabel(weekdays[i]);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            p2.setBackground(Color.yellow);
            p2.add(label);
    
        }
		JPanel tableau = new JPanel();
	    tableau.setPreferredSize(new Dimension(300, 650));
 	    JPanel global = new JPanel();
    		global.setBackground(new Color(102, 163, 211));
    		global.setForeground(Color.blue);
    		this.add(global);
    		global.add(tableau);
    		tableau.setBackground(Color.white);
            tableau.add(p2);
        Date date = new Date();
        int noOfDaysInMonth = days[date.getMonth()];
        if(date.getYear()%4==0 && date.getMonth()==1)
        {
            noOfDaysInMonth = 29;
        }

        for(int i=1, day=1;day<=noOfDaysInMonth;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(day==1)
                {
                    if(j==date.getDay())
                    {
                        JLabel label = new JLabel(String.valueOf(day));
                        label.setHorizontalAlignment(SwingConstants.RIGHT);
                        p2.add(label);
                        day++;
                    }
                    else
                    {
                        JLabel label = new JLabel("");
                        p2.add(label);
                    }
                }
                else
                {
                    JLabel label = new JLabel(String.valueOf(day));
                    label.setHorizontalAlignment(SwingConstants.RIGHT);
                    p2.add(label);
                    day++;
                }
                if(day>noOfDaysInMonth)
                {
                    break;
                }
            }
        }
        p2.validate();
		
	}

}
