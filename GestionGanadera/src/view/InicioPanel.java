package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

public class InicioPanel extends JPanel {
	private JTextField txtnombreDueño;
	private JTextField txtubicacion;
	
	
	public InicioPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelderecho = new JPanel();
		panelderecho.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelderecho, BorderLayout.EAST);
		panelderecho.setLayout(new BorderLayout(0, 0));
		
		JLabel derechoespacio1 = new JLabel("    ");
		panelderecho.add(derechoespacio1, BorderLayout.WEST);
		
		JLabel derechoespacio2 = new JLabel("    ");
		panelderecho.add(derechoespacio2, BorderLayout.EAST);
		
		JPanel panelDerechoinfo = new JPanel();
		panelderecho.add(panelDerechoinfo, BorderLayout.CENTER);
		panelDerechoinfo.setLayout(new GridLayout(9,1));
		
		
		JLabel lblNewLabel_5 = new JLabel("          ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_5);
		
		JComboBox comboBoxPotreros = new JComboBox();
		panelDerechoinfo.add(comboBoxPotreros);
		
		JLabel lblNewLabel = new JLabel("     ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel);
		
		JButton btnAgregar = new JButton(" Agregar ");
		panelDerechoinfo.add(btnAgregar);
		
		JLabel lblNewLabel_6 = new JLabel("     ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_6);
		
		JButton btnEliminar = new JButton(" Eliminar ");
		panelDerechoinfo.add(btnEliminar);
		
		JLabel lblNewLabel_7 = new JLabel("     ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_7);
		
		JButton btnBuscar = new JButton(" buscar ");
		panelDerechoinfo.add(btnBuscar);
		
		JLabel lblNewLabel_8 = new JLabel("     ");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_8);
		
		
		JPanel panelprincipal = new JPanel();
		panelprincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelprincipal, BorderLayout.CENTER);
		panelprincipal.setLayout(new BorderLayout(0, 0));
		
	
        //PANEL BAJO, BOTONES
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelprincipal.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(2,1));
		
		
		JPanel panelBotones3= new JPanel();
		
		
		JPanel panelBotones2 = new JPanel();
		panelBotones.add(panelBotones2, BorderLayout.SOUTH);
		panelBotones2.setLayout(new GridLayout(1,5));
		
		panelBotones.add(panelBotones3);
		
		JLabel labelaux3 = new JLabel("");
		panelBotones2.add(labelaux3);
		
		JButton botonGuardar = new JButton("GUARDAR");
		panelBotones2.add(botonGuardar);
		
		JLabel labelaux2 = new JLabel("");
		panelBotones2.add(labelaux2);
		
		JButton botonEditar = new JButton("EDITAR");
		panelBotones2.add(botonEditar);
		
		JLabel labelaux1 = new JLabel("");
		panelBotones2.add(labelaux1);
		

		//BANNER
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelprincipal.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3,1));
		
		JLabel label1 = new JLabel("");
		panel.add(label1);
		
		JLabel labelnombreFinca = new JLabel("                Nombre de la finca");
		labelnombreFinca.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(labelnombreFinca);
		
		JLabel label2 = new JLabel("");
		panel.add(label2);
		
		//PANEL PRINCIPAL - INFORMACION
		
		JPanel panelInfo = new JPanel();
		panelprincipal.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		JLabel labelespacio1 = new JLabel("     ");
		panelInfo.add(labelespacio1, BorderLayout.WEST);
		
		JLabel labelespacio2 = new JLabel("                  ");
		panelInfo.add(labelespacio2, BorderLayout.EAST);
		
		JPanel panelinformacion = new JPanel();
		panelInfo.add(panelinformacion, BorderLayout.CENTER);
		panelinformacion.setLayout(new GridLayout(6,2));
		
		
		// grid de informacion
		JLabel lblNewLabel_1 = new JLabel("    ");
		panelinformacion.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_2);
		
		JLabel nombreDueño = new JLabel(" Dueño de la Finca: ");
		nombreDueño.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreDueño.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreDueño);
		
		
		txtnombreDueño = new JTextField();
		txtnombreDueño.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtnombreDueño.setHorizontalAlignment(SwingConstants.CENTER);
		txtnombreDueño.setText(" Juan Camilo Velez ");
		panelinformacion.add(txtnombreDueño);
		txtnombreDueño.setColumns(10);
		
		JLabel nombreUbicacion = new JLabel(" Ubicación: ");
		nombreUbicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreUbicacion);
		

		txtubicacion = new JTextField();
		txtubicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtubicacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtubicacion.setText(" Vereda valle de lili");
		panelinformacion.add(txtubicacion);
		txtubicacion.setColumns(10);
		
		JLabel numeroPotreros = new JLabel(" Cantidad de potreros: ");
		numeroPotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroPotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroPotreros);
		
		JLabel numeroDepotreros = new JLabel("10 potreros ");
		numeroDepotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDepotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroDepotreros);
		
		JLabel numeroVacas = new JLabel(" Cantidad de Vacas ");
		numeroVacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroVacas.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroVacas);
		
		JLabel numeroDeVacas = new JLabel(" 1500 vacas ");
		numeroDeVacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDeVacas.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroDeVacas);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_4);
	
	
		
		
	}
	
	

}
