package view.agenda;
import java.awt.BorderLayout;
<<<<<<< HEAD
=======
import java.awt.Color;
>>>>>>> af554d59887cab90c2c3306accd38565f246c045
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
 
import javax.swing.BorderFactory;
=======
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
>>>>>>> af554d59887cab90c2c3306accd38565f246c045
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
 
public class Calendar extends JPanel {

=======
import javax.swing.RootPaneContainer;

 
public class Calendar extends JPanel {
 
>>>>>>> af554d59887cab90c2c3306accd38565f246c045
	/**
         * 
         */
	private static final long serialVersionUID = 6411499808530678723L;
 
	  /** The buttons to be displayed */
	  protected JButton labs[][];
 
	  private Date date=new Date();
	  String[] months = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
		      "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" };
	  public int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
			  31, 30, 31, 31, /* may jun jul aug */
			  30, 31, 30, 31 /* sep oct nov dec */
			  };
	  /** The month choice */
	  private JComboBox<String> monthChoice;
 
	  /** The year choice */
	  private JComboBox<String> yearChoice;
 
	  Calendar(){
		    super();
		    buildGUI();
		    recompute();
		  }
 
	protected void recompute() {
		// TODO Auto-generated method stub
		// Blank out all
		for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) 
		         labs[i][j].setText("");
 
		 int daysInMonth = dom[date.getMonth()];
		 if (isLeap(date.getYear()) && date.getMonth() == 1)
		      daysInMonth++;
		// Blank out the labels before 1st day of month
		    for (int i = 0; i < date.firstDayOfTheMonth(); i++) {
		      labs[0][i].setText("");
		    }
 
		 // Fill in numbers for the day of month.
		    for (int i = 1; i <= daysInMonth; i++) {
		      JButton b = labs[(date.firstDayOfTheMonth() + i - 2) / 7][(date.firstDayOfTheMonth() + i - 2) % 7];
		      b.setText(Integer.toString(i));
		    }
 
 
		    repaint();
	}
 
 
	private boolean isLeap(int year) {
		// TODO Auto-generated method stub
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
		      return true;
		return false;
	}
 
	private void buildGUI() {
		getAccessibleContext().setAccessibleDescription(
		        "Calendar not accessible yet. Sorry!");
		    setBorder(BorderFactory.createEtchedBorder());
 
		    setLayout(new BorderLayout());
 
		    JPanel tp = new JPanel();
		    tp.add(monthChoice = new JComboBox<String>());
		    for (int i = 0; i < months.length; i++)
		      monthChoice.addItem(months[i]);
		    monthChoice.setSelectedItem(months[date.getMonth()]);
		    monthChoice.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        int i = monthChoice.getSelectedIndex();
		        if (i >= 0) {
		          date.setMonth(i);
		          recompute();
		        }
		      }
		    });
		    monthChoice.getAccessibleContext().setAccessibleName("Months");
		    monthChoice.getAccessibleContext().setAccessibleDescription(
		        "Choose a month of the year");
		    tp.add(yearChoice = new JComboBox<String>());
		    yearChoice.setEditable(true);
		    for (int i = date.getYear() - 5; i < date.getYear() + 5; i++)
		      yearChoice.addItem(Integer.toString(i));
		    yearChoice.setSelectedItem(Integer.toString(date.getYear()));
		    yearChoice.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        int i = yearChoice.getSelectedIndex();
		        if (i >= 0) {
		          date.setYear(Integer.parseInt(yearChoice.getSelectedItem().toString()));
		          recompute();
		        }
		      }
		    });
		    add(BorderLayout.CENTER, tp);
 
		    JPanel bp = new JPanel();
<<<<<<< HEAD
		    bp.setLayout(new GridLayout(7, 7));
			bp.setSize(getMaximumSize());
=======
			/*bp.setBounds(500,50,500,500/16*9);
			
		
			BoxLayout b= new BoxLayout(bp, BoxLayout.Y_AXIS);
			
			((RootPaneContainer) bp).setContentPane(bp);
			bp.setBackground(Color.green);
			bp.setSize(getMaximumSize());
			bp.setVisible(true);
			bp.setOpaque(true);

		    bp.setLayout(new GridLayout(7, 7));
			BoxLayout bx= new BoxLayout(bp, BoxLayout.Y_AXIS);
			bp.setSize(getMaximumSize());*/
>>>>>>> af554d59887cab90c2c3306accd38565f246c045

		    labs = new JButton[6][7]; // first row is days
 
		    bp.add(new JButton("Lundi"));
		    bp.add(new JButton("Mardi"));
		    bp.add(new JButton("Mercredi"));
		    bp.add(new JButton("Jeudi"));
		    bp.add(new JButton("Vendredi"));
		    bp.add(new JButton("Samedi"));
		    bp.add(new JButton("Dimanche"));

 
		    ActionListener dateSetter = new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        String num = e.getActionCommand();
		        if (!num.equals("")) {
		          // set the current day highlighted
		          setDayActive(Integer.parseInt(num));
 
		          // When this becomes a Bean, you can
		          // fire some kind of DateChanged event here.
		          // Also, build a similar daySetter for day-of-week btns.
		        }
		      }
 
		    };
 
 
 
		    // Construct all the buttons, and add them.
		    for (int i = 0; i < 6; i++)
		      for (int j = 0; j < 7; j++) {
		        bp.add(labs[i][j] = new JButton(""));
		        labs[i][j].addActionListener(dateSetter);
		      }
 
		    add(BorderLayout.SOUTH, bp);
 
	}
 
	protected void setDayActive(int parseInt) {
		// TODO Auto-generated method stub
 
	}
 
	public static void main(String[] argv)
	  {
	    JFrame f = new JFrame("GSB - Calendrier");
	    Container c = f.getContentPane();
		c.setBounds(500,50,500,500/16*9);
<<<<<<< HEAD

	    c.setLayout(new FlowLayout());
	    c.add(new Calendar());
=======
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);

	    c.setLayout(new FlowLayout());
	    c.add(new Calendar());
	    
>>>>>>> af554d59887cab90c2c3306accd38565f246c045
 
	    f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
	  }
 
<<<<<<< HEAD
}
=======
}
>>>>>>> af554d59887cab90c2c3306accd38565f246c045
