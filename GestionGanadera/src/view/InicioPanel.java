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
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import db.PotreroCRUD;
import db.ResCRUD;
import db.UsuarioCRUD;
import javafx.scene.control.ComboBox;
import model.Potrero;
import model.Usuario;

public class InicioPanel extends JPanel {

	private PotrerosPanel potreros;
	private ReportePanel reportes;
	private JTextField txtnombreDueno;
	private JTextField txtubicacion;
	private JLabel nombreFinca;
	private JLabel numeroDepotreros;
	private JLabel numeroDevacas;
	private VentanaPrincipal ventana;
	private JComboBox comboBoxPotreros;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JButton btnreporteDestete;
	private JButton btnreportePartos;

	public InicioPanel(VentanaPrincipal ventana) {

		this.ventana = ventana;

		ventana.setSize(800,400);
		
		setLayout(new BorderLayout(0, 0));
       
		ventana.setLocationRelativeTo(null);
		setComponents();
		listeners();

		cargarInfo();
	}

	private void setComponents() {

		// panel derecho

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
		panelDerechoinfo.setLayout(new GridLayout(13, 1));

		JLabel lblNewLabel_5 = new JLabel("                                                          ");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_5);

		comboBoxPotreros = new JComboBox();
		panelDerechoinfo.add(comboBoxPotreros);

		JLabel lblNewLabel = new JLabel("     ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel);

		btnAgregar = new JButton(" Agregar ");
		panelDerechoinfo.add(btnAgregar);

		JLabel lblNewLabel_6 = new JLabel("     ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_6);

		btnEliminar = new JButton(" Eliminar ");
		panelDerechoinfo.add(btnEliminar);

		JLabel lblNewLabel_7 = new JLabel("     ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_7);

		btnBuscar = new JButton(" Buscar ");
		panelDerechoinfo.add(btnBuscar);

		JLabel lblNewLabel_8 = new JLabel("     ");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_8);

		btnreporteDestete = new JButton(" Reporte Destete ");

		panelDerechoinfo.add(btnreporteDestete);

		JLabel lblNewLabel_9 = new JLabel("     ");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_9);

		btnreportePartos = new JButton(" Reporte Partos ");
		panelDerechoinfo.add(btnreportePartos);

		JLabel lblNewLabel_10 = new JLabel("     ");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_10);

		JPanel panelprincipal = new JPanel();
		panelprincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelprincipal, BorderLayout.CENTER);
		panelprincipal.setLayout(new BorderLayout(0, 0));

		// PANEL BAJO, BOTONES
		JPanel panelBotones = new JPanel();
		panelBotones.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelprincipal.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(2, 1));

		JPanel panelBotones3 = new JPanel();

		JPanel panelBotones2 = new JPanel();
		panelBotones.add(panelBotones2, BorderLayout.SOUTH);
		panelBotones2.setLayout(new GridLayout(1, 5));

		panelBotones.add(panelBotones3);

		JLabel labelaux3 = new JLabel("");
		panelBotones2.add(labelaux3);

		btnGuardar = new JButton("GUARDAR");
		panelBotones2.add(btnGuardar);

		JLabel labelaux2 = new JLabel("");
		panelBotones2.add(labelaux2);

		btnEditar = new JButton("EDITAR");
		panelBotones2.add(btnEditar);

		JLabel labelaux1 = new JLabel("");
		panelBotones2.add(labelaux1);

		// BANNER
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelprincipal.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(1, 1));

		

		nombreFinca = new JLabel("Nombre de la finca");
		nombreFinca.setHorizontalAlignment(SwingConstants.CENTER);
		nombreFinca.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel.add(nombreFinca, BorderLayout.CENTER);


		// PANEL PRINCIPAL - INFORMACION

		JPanel panelInfo = new JPanel();
		panelprincipal.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));

		JLabel labelespacio1 = new JLabel("     ");
		panelInfo.add(labelespacio1, BorderLayout.WEST);

		JLabel labelespacio2 = new JLabel("                  ");
		panelInfo.add(labelespacio2, BorderLayout.EAST);

		JPanel panelinformacion = new JPanel();
		panelInfo.add(panelinformacion, BorderLayout.CENTER);
		panelinformacion.setLayout(new GridLayout(6, 2));

		// grid de informacion
		JLabel lblNewLabel_1 = new JLabel("    ");
		panelinformacion.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_2);

		JLabel nombreDueno = new JLabel("Due\u00F1o de la finca:");
		nombreDueno.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreDueno.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreDueno);

		txtnombreDueno = new JTextField();
		txtnombreDueno.setEditable(false);
		txtnombreDueno.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtnombreDueno.setHorizontalAlignment(SwingConstants.CENTER);
		txtnombreDueno.setText("");
		panelinformacion.add(txtnombreDueno);
		txtnombreDueno.setColumns(10);

		JLabel nombreUbicacion = new JLabel("Ubicaci\u00F3n:");
		nombreUbicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreUbicacion);

		txtubicacion = new JTextField();
		txtubicacion.setEditable(false);
		txtubicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtubicacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtubicacion.setText("");
		panelinformacion.add(txtubicacion);
		txtubicacion.setColumns(10);

		JLabel numeroPotreros = new JLabel(" Cantidad de potreros: ");
		numeroPotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroPotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroPotreros);

		numeroDepotreros = new JLabel("10 potreros ");
		numeroDepotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDepotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroDepotreros);

		JLabel numeroVacas = new JLabel(" Cantidad de Vacas ");
		numeroVacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroVacas.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroVacas);

		numeroDevacas = new JLabel(" 1500 vacas ");
		numeroDevacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDevacas.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroDevacas);

		JLabel lblNewLabel_3 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_4);

	}
	
	public void cargarInfo() {
		
		
		Usuario user = UsuarioCRUD.select().get(0);
		String potreros =PotreroCRUD.select().size()+ " Potreros";
		String vacas = ResCRUD.select().size()+ " Reses";
		
		
		nombreFinca.setText(user.getNombreFinca().toUpperCase());
		
		numeroDepotreros.setText(potreros);
		
		numeroDevacas.setText(vacas);
		
		txtnombreDueno.setText(user.getNombre());
		
		
		
		if (user.getUbicacion().equals(null)) {
			
			txtubicacion.setText("");
			
		}else {
			txtubicacion.setText(user.getUbicacion());
		}
		
		
	}

	public void listeners() {

		btnAgregar.addActionListener(e -> {

			ventana.remove(this);
			potreros = new PotrerosPanel(this);
			ventana.add(potreros);
			ventana.setResizable(true);
			ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
			ventana.refresh();
			
			
		});

		comboBoxPotreros.addActionListener(e -> {

		});

		btnAgregar.addActionListener(e -> {

		});

		btnEliminar.addActionListener(e -> {

		});

		btnBuscar.addActionListener(e -> {

		});

		btnGuardar.addActionListener(e -> {
			
			
			Usuario useractual =UsuarioCRUD.select().get(0);
			
			String nombre = useractual.getNombre();

			useractual.setNombre(txtnombreDueno.getText());
			useractual.setUbicacion(txtubicacion.getText());
			
			UsuarioCRUD.update(nombre, useractual);

		});

		btnEditar.addActionListener(e -> {
			
			txtnombreDueno.setEditable(true);
			txtubicacion.setEditable(true);
			
			

		});

		btnreporteDestete.addActionListener(e -> {

			ventana.remove(this);
			reportes = new ReportePanel(this);
			ventana.add(reportes);
			ventana.setSize(450, 300);
			ventana.setResizable(false);
			ventana.setLocationRelativeTo(null);
			ventana.refresh();

		});

		btnreportePartos.addActionListener(e -> {

		});

	}

	public JTextField getTxtnombreDueno() {
		return txtnombreDueno;
	}

	public void setTxtnombreDueno(JTextField txtnombreDueno) {
		this.txtnombreDueno = txtnombreDueno;
	}

	public JTextField getTxtubicacion() {
		return txtubicacion;
	}

	public void setTxtubicacion(JTextField txtubicacion) {
		this.txtubicacion = txtubicacion;
	}

	public JLabel getNombreFinca() {
		return nombreFinca;
	}

	public void setNombreFinca(JLabel nombreFinca) {
		this.nombreFinca = nombreFinca;
	}

	public JLabel getNumeroDepotreros() {
		return numeroDepotreros;
	}

	public void setNumeroDepotreros(JLabel numeroDepotreros) {
		this.numeroDepotreros = numeroDepotreros;
	}

	public JLabel getNumeroDevacas() {
		return numeroDevacas;
	}

	public void setNumeroDevacas(JLabel numeroDevacas) {
		this.numeroDevacas = numeroDevacas;
	}

	public VentanaPrincipal getVentana() {
		return ventana;
	}

	public void setVentana(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	public JComboBox getComboBoxPotreros() {
		return comboBoxPotreros;
	}

	public void setComboBoxPotreros(JComboBox comboBoxPotreros) {
		this.comboBoxPotreros = comboBoxPotreros;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnreporteDestete() {
		return btnreporteDestete;
	}

	public void setBtnreporteDestete(JButton btnreporteDestete) {
		this.btnreporteDestete = btnreporteDestete;
	}

	public JButton getBtnreportePartos() {
		return btnreportePartos;
	}

	public void setBtnreportePartos(JButton btnreportePartos) {
		this.btnreportePartos = btnreportePartos;
	}

}
