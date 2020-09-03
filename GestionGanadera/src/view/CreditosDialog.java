package view;

import java.awt.TextComponent;
import java.awt.Dialog.ModalityType;

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
import javax.swing.JSpinner.ListEditor;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class CreditosDialog extends JDialog{
	
	private JButton btnRegresar;
	
     public CreditosDialog() {
    	 
    	 
    	 setSize(450,300);
    
    		setTitle("Creditos");
    
    		setLocationRelativeTo(null);
    		
            setModalityType(ModalityType.APPLICATION_MODAL); 
   
     	components();
    	 
     	setVisible(true);
    	 
     }
     
     public void components() {
    	 
    	  	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
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
         	lblNewLabel_23.setFont(new Font("SansSerif", Font.PLAIN, 13));
         	lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_2.add(lblNewLabel_23);
         	
         	JLabel lblNewLabel_21 = new JLabel("fueron tomadas previamente de la pagina web");
         	lblNewLabel_21.setFont(new Font("SansSerif", Font.PLAIN, 13));
         	lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_2.add(lblNewLabel_21);
         	
         	JLabel lblNewLabel_20 = new JLabel("www.flaticon.es, desarrolladas por los autores:");
         	lblNewLabel_20.setFont(new Font("SansSerif", Font.PLAIN, 13));
         	lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_2.add(lblNewLabel_20);
         	
         	JLabel lblNewLabel_19 = new JLabel("Freepik, Pixelmeetup, Flat Icons, Pixel perfect,");
         	lblNewLabel_19.setFont(new Font("SansSerif", Font.PLAIN, 13));
         	lblNewLabel_19.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_2.add(lblNewLabel_19);
         	
         	JLabel lblNewLabel_18 = new JLabel("Those icons, Becris, y surang.   ");
         	lblNewLabel_18.setFont(new Font("SansSerif", Font.PLAIN, 13));
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
         	
         	JLabel lblNewLabel_2 = new JLabel("");
         	panel.add(lblNewLabel_2, BorderLayout.WEST);
         	
         	JLabel lblNewLabel_3 = new JLabel("");
         	panel.add(lblNewLabel_3, BorderLayout.EAST);
         	
         	JPanel panel_3 = new JPanel();
         	panel.add(panel_3, BorderLayout.CENTER);
         	panel_3.setLayout(new GridLayout(10,1));
         	
         	JLabel lblNewLabel_4 = new JLabel("");
         	panel_3.add(lblNewLabel_4);
         
         	
         	JLabel lblNewLabel_7 = new JLabel("Este software fue desarrollado por:");
         	lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_7);
         	
         	JLabel lblNewLabel_13 = new JLabel("Juan David Paz - Juan Camilo Velez");
         	lblNewLabel_13.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_13);
         	
         	JLabel lblNewLabel_12 = new JLabel("");
         	panel_3.add(lblNewLabel_12);
         	
         	JLabel lblNewLabel_8 = new JLabel("Soporte en:");
         	lblNewLabel_8.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_8);
         	
         	JLabel lblNewLabel_11 = new JLabel("juandoradou@hotmail.com ");
         	lblNewLabel_11.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_11);
         	
         	JLabel lblNewLabel_9 = new JLabel("velezolaya2012@hotmail.com\n");
         	lblNewLabel_9.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_9);
         	
        	JLabel lblNewLabel_6 = new JLabel("");
         	panel_3.add(lblNewLabel_6);
         	
         	JLabel lblNewLabel_10 = new JLabel("Cali, Colombia.");
         	lblNewLabel_10.setFont(new Font("SansSerif", Font.PLAIN, 14));
         	lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
         	panel_3.add(lblNewLabel_10);
        	 
        	JLabel lblNewLabel_5 = new JLabel("");
         	panel_3.add(lblNewLabel_5);
         	
         	JPanel panel_4 = new JPanel();
         	getContentPane().add(panel_4, BorderLayout.SOUTH);
         	panel_4.setLayout(new GridLayout(1, 3));
         	
         	JLabel lblNewLabel_24 = new JLabel("");
         	panel_4.add(lblNewLabel_24);
         	
         	
         	
         btnRegresar = new JButton("Regresar");
         	
       
         	JLabel label5 =new JLabel("");
         	panel_4.add(label5);
         	
         	
     }
     
     public void listeners() {
    	 
    	 btnRegresar.addActionListener(e -> {

 			dispose();

 		});
    	 
     }
	
}
