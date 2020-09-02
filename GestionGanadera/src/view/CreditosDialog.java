package view;

import java.awt.TextComponent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.sun.corba.se.spi.ior.MakeImmutable;
import com.sun.java.accessibility.util.java.awt.TextComponentTranslator;

import tools.FileManager;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class CreditosDialog extends JDialog{
	
	
     public CreditosDialog() {
    	 
    	 
    	 
     	
     	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
     	getContentPane().add(tabbedPane, BorderLayout.CENTER);
     	
     	JPanel panel_1 = new JPanel();
     	tabbedPane.addTab("Recursos", null, panel_1, null);
     	panel_1.setLayout(new BorderLayout(0, 0));
     	
     	JLabel lblNewLabel = new JLabel("");
     	panel_1.add(lblNewLabel, BorderLayout.WEST);
     	
     	JLabel lblNewLabel_1 = new JLabel("");
     	panel_1.add(lblNewLabel_1, BorderLayout.EAST);
     	
     	JPanel panel_2 = new JPanel();
     	panel_1.add(panel_2, BorderLayout.CENTER);
     	panel_2.setLayout(new GridLayout(10,1));
     	
     	JLabel lblNewLabel_14 = new JLabel("");
     	panel_2.add(lblNewLabel_14);
     	
     	JLabel lblNewLabel_22 = new JLabel("");
     	panel_2.add(lblNewLabel_22);
     	
     	JLabel lblNewLabel_23 = new JLabel("Todas las imagenes utlizadas en este software ");
     	lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
     	panel_2.add(lblNewLabel_23);
     	
     	JLabel lblNewLabel_21 = new JLabel("fueron tomadas previamente de la pagina web");
     	lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
     	panel_2.add(lblNewLabel_21);
     	
     	JLabel lblNewLabel_20 = new JLabel("www.flaticon.es, desarrolladas por los autores:");
     	lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
     	panel_2.add(lblNewLabel_20);
     	
     	JLabel lblNewLabel_19 = new JLabel("Freepik, Pixelmeetup, Flat Icons, Pixel perfect,");
     	lblNewLabel_19.setHorizontalAlignment(SwingConstants.CENTER);
     	panel_2.add(lblNewLabel_19);
     	
     	JLabel lblNewLabel_18 = new JLabel("Those icons, Becris, y surang.   ");
     	lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
     	panel_2.add(lblNewLabel_18);
     	
     	JLabel lblNewLabel_17 = new JLabel("");
     	panel_2.add(lblNewLabel_17);
     	
     	JLabel lblNewLabel_16 = new JLabel("");
     	panel_2.add(lblNewLabel_16);
     	
     	JLabel lblNewLabel_15 = new JLabel("");
     	panel_2.add(lblNewLabel_15);
     	
     	JPanel panel = new JPanel();
     	tabbedPane.addTab("Developers", null, panel, null);
     	panel.setLayout(new BorderLayout(0, 0));
     	
     	JLabel lblNewLabel_2 = new JLabel("New label");
     	panel.add(lblNewLabel_2, BorderLayout.WEST);
     	
     	JLabel lblNewLabel_3 = new JLabel("New label");
     	panel.add(lblNewLabel_3, BorderLayout.EAST);
     	
     	JPanel panel_3 = new JPanel();
     	panel.add(panel_3, BorderLayout.CENTER);
     	panel_3.setLayout(new GridLayout(10,1));
     	
     	JLabel lblNewLabel_4 = new JLabel("New label");
     	panel_3.add(lblNewLabel_4);
     	
     	JLabel lblNewLabel_5 = new JLabel("New label");
     	panel_3.add(lblNewLabel_5);
     	
     	JLabel lblNewLabel_6 = new JLabel("New label");
     	panel_3.add(lblNewLabel_6);
     	
     	JLabel lblNewLabel_7 = new JLabel("New label");
     	panel_3.add(lblNewLabel_7);
     	
     	JLabel lblNewLabel_13 = new JLabel("New label");
     	panel_3.add(lblNewLabel_13);
     	
     	JLabel lblNewLabel_12 = new JLabel("New label");
     	panel_3.add(lblNewLabel_12);
     	
     	JLabel lblNewLabel_8 = new JLabel("New label");
     	panel_3.add(lblNewLabel_8);
     	
     	JLabel lblNewLabel_11 = new JLabel("New label");
     	panel_3.add(lblNewLabel_11);
     	
     	JLabel lblNewLabel_9 = new JLabel("New label");
     	panel_3.add(lblNewLabel_9);
     	
     	JLabel lblNewLabel_10 = new JLabel("New label");
     	panel_3.add(lblNewLabel_10);
    	 
    	 
     	setVisible(true);
    	 
     }
	
}
