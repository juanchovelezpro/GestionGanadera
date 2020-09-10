package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import db.PotreroCRUD;
import db.ResCRUD;
import db.UsuarioCRUD;
import model.Potrero;
import model.Res;
import model.Usuario;
import tools.FileManager;

public class InicioPanel extends JPanel {

	private PotrerosPanel potreros;
	private JTextField txtnombreDueno;
	private JTextField txtubicacion;
	private JLabel nombreFinca;
	private JLabel numeroDepotreros;
	private JLabel numeroDevacas;
	private VentanaPrincipal ventana;
	private JComboBox<String> comboBoxPotreros;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JButton btnreporteDestete;
	private JButton btnreportePartos;
	private Icon icono;
	private JButton btnreporteVacuna;
	private JButton btnreportePurgante;
	private JButton btnEstadistica;

	public InicioPanel(VentanaPrincipal ventana) {

		this.ventana = ventana;

		setLayout(new BorderLayout(0, 0));
		ventana.setSize(800, 400);
		ventana.setLocationRelativeTo(null);
		setComponents();
		listeners();

		cargarInfo();

		comprobarEliminar();


	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("CAMPO"), 0, 0, null);

		repaint();
	}

	private void setComponents() {


		JPanel panelderecho = new JPanel();
		panelderecho.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelderecho, BorderLayout.EAST);
		panelderecho.setLayout(new BorderLayout(0, 0));
		panelderecho.setBackground(new Color(0, 0, 0, 5));

		JLabel derechoespacio1 = new JLabel("    ");
		panelderecho.add(derechoespacio1, BorderLayout.WEST);

		JLabel derechoespacio2 = new JLabel("    ");
		panelderecho.add(derechoespacio2, BorderLayout.EAST);

		JPanel panelDerechoinfo = new JPanel();
		panelderecho.add(panelDerechoinfo, BorderLayout.CENTER);
		panelDerechoinfo.setLayout(new GridLayout(13, 1));
		panelDerechoinfo.setBackground(new Color(0, 0, 0, 5));

		JLabel lblNewLabel_5 = new JLabel("POTREROS");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBackground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_5);

		JLabel lblNewLabel_11 = new JLabel("");
		panelDerechoinfo.add(lblNewLabel_11);

		comboBoxPotreros = new JComboBox();
		comboBoxPotreros.setForeground(Color.WHITE);
		comboBoxPotreros.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(comboBoxPotreros);

		btnAgregar = new JButton(" Agregar potrero ");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnAgregar.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnAgregar);

		btnEliminar = new JButton(" Eliminar potrero");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnEliminar.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnEliminar);

		btnBuscar = new JButton(" Buscar res");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnBuscar.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnBuscar);

		btnEstadistica = new JButton("Estad\u00EDsticas");
		btnEstadistica.setForeground(Color.WHITE);
		btnEstadistica.setBackground(new Color(0, 0, 0, 130));
		btnEstadistica.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelDerechoinfo.add(btnEstadistica);

		JLabel lblNewLabel_8 = new JLabel("----------------------------");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panelDerechoinfo.add(lblNewLabel_8);

		btnreporteDestete = new JButton(" Reporte Destete ");
		btnreporteDestete.setForeground(Color.WHITE);
		btnreporteDestete.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnreporteDestete.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnreporteDestete);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);

		btnreportePartos = new JButton(" Reporte Partos ");
		btnreportePartos.setForeground(Color.WHITE);
		btnreportePartos.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnreportePartos.setBackground(new Color(0, 0, 0, 130));

		panelDerechoinfo.add(btnreportePartos);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);

		btnreporteVacuna = new JButton(" Reporte Vacunas ");
		btnreporteVacuna.setForeground(Color.WHITE);
		btnreporteVacuna.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnreporteVacuna.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnreporteVacuna);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);

		btnreportePurgante = new JButton(" Reporte Purgantes ");
		btnreportePurgante.setForeground(Color.WHITE);
		btnreportePurgante.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnreportePurgante.setBackground(new Color(0, 0, 0, 130));
		panelDerechoinfo.add(btnreportePurgante);

		JPanel panelprincipal = new JPanel();
		panelprincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelprincipal, BorderLayout.CENTER);
		panelprincipal.setLayout(new BorderLayout(0, 0));
		panelprincipal.setBackground(new Color(0, 0, 0, 5));

		JPanel panelBotones = new JPanel();
		panelprincipal.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(2, 1));
		panelBotones.setBackground(new Color(0, 0, 0, 5));

		JPanel panelBotones3 = new JPanel();
		panelBotones3.setBackground(new Color(0, 0, 0, 5));

		JPanel panelBotones2 = new JPanel();
		panelBotones.add(panelBotones2, BorderLayout.SOUTH);
		panelBotones2.setLayout(new GridLayout(1, 5));
		panelBotones2.setBackground(new Color(0, 0, 0, 5));

		panelBotones.add(panelBotones3);

		JLabel labelaux3 = new JLabel("");
		panelBotones2.add(labelaux3);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBackground(new Color(0, 0, 0, 130));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelBotones2.add(btnGuardar);

		JLabel labelaux2 = new JLabel("");
		panelBotones2.add(labelaux2);

		btnEditar = new JButton("EDITAR");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(new Color(0, 0, 0, 130));
		btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelBotones2.add(btnEditar);

		JLabel labelaux1 = new JLabel("");
		panelBotones2.add(labelaux1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 100));
		panelprincipal.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(1, 1));

		nombreFinca = new JLabel("Nombre de la finca");
		nombreFinca.setForeground(Color.WHITE);
		nombreFinca.setHorizontalAlignment(SwingConstants.CENTER);
		nombreFinca.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		panel.add(nombreFinca, BorderLayout.CENTER);


		JPanel panelInfo = new JPanel();
		panelprincipal.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new BorderLayout(0, 0));
		panelInfo.setBackground(new Color(0, 0, 0, 100));

		JLabel labelespacio1 = new JLabel("     ");
		panelInfo.add(labelespacio1, BorderLayout.WEST);

		JLabel labelespacio2 = new JLabel("                  ");
		panelInfo.add(labelespacio2, BorderLayout.EAST);

		JPanel panelinformacion = new JPanel();
		panelInfo.add(panelinformacion, BorderLayout.CENTER);
		panelinformacion.setLayout(new GridLayout(6, 2));
		panelinformacion.setBackground(new Color(0, 0, 0, 5));

		JLabel lblNewLabel_1 = new JLabel("    ");
		panelinformacion.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_2);

		JLabel nombreDueno = new JLabel("Propietario de la finca:");
		nombreDueno.setForeground(Color.WHITE);
		nombreDueno.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreDueno.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreDueno);

		txtnombreDueno = new JTextField();
		txtnombreDueno.setForeground(Color.WHITE);
		txtnombreDueno.setEditable(false);
		txtnombreDueno.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtnombreDueno.setHorizontalAlignment(SwingConstants.CENTER);
		txtnombreDueno.setText("");
		panelinformacion.add(txtnombreDueno);
		txtnombreDueno.setColumns(10);
		txtnombreDueno.setBackground(new Color(0, 0, 0, 130));

		JLabel nombreUbicacion = new JLabel("Ubicaci\u00F3n:");
		nombreUbicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		nombreUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(nombreUbicacion);
		nombreUbicacion.setForeground(Color.WHITE);

		txtubicacion = new JTextField();
		txtubicacion.setForeground(Color.white);
		txtubicacion.setEditable(false);
		txtubicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtubicacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtubicacion.setText("");
		panelinformacion.add(txtubicacion);
		txtubicacion.setColumns(10);
		txtubicacion.setBackground(new Color(0, 0, 0, 130));

		JLabel numeroPotreros = new JLabel(" Cantidad de potreros: ");
		numeroPotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroPotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroPotreros);
		numeroPotreros.setForeground(Color.WHITE);

		numeroDepotreros = new JLabel("10 potreros ");
		numeroDepotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDepotreros.setHorizontalAlignment(SwingConstants.CENTER);
		panelinformacion.add(numeroDepotreros);
		numeroDepotreros.setForeground(Color.WHITE);

		JLabel numeroVacas = new JLabel(" Cantidad de reses: ");
		numeroVacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroVacas.setHorizontalAlignment(SwingConstants.CENTER);
		numeroVacas.setForeground(Color.WHITE);
		panelinformacion.add(numeroVacas);

		numeroDevacas = new JLabel(" 1500 vacas ");
		numeroDevacas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		numeroDevacas.setHorizontalAlignment(SwingConstants.CENTER);
		numeroDevacas.setForeground(Color.WHITE);
		panelinformacion.add(numeroDevacas);

		JLabel lblNewLabel_3 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("     ");
		panelinformacion.add(lblNewLabel_4);

	}

	public void comprobarEliminar() {

		if (comboBoxPotreros.getItemCount() == 1) {

			btnEliminar.setEnabled(false);
		} else {
			btnEliminar.setEnabled(true);
		}
	}

	public void cargarInfo() {

		Usuario user = UsuarioCRUD.select().get(0);
		String potreros = PotreroCRUD.select().size() + " Potreros";
		String vacas = ResCRUD.select().size() + " Reses";

		nombreFinca.setText(user.getNombreFinca().toUpperCase());

		numeroDepotreros.setText(potreros);

		numeroDevacas.setText(vacas);

		txtnombreDueno.setText(user.getNombre());

		if (user.getUbicacion().equals(null)) {

			txtubicacion.setText("");

		} else {
			txtubicacion.setText(user.getUbicacion());
		}

		ArrayList<Potrero> potreros2 = PotreroCRUD.select();
		comboBoxPotreros.removeAll();
		comboBoxPotreros.addItem("");
		for (int i = 0; i < potreros2.size(); i++) {

			comboBoxPotreros.addItem(potreros2.get(i).getNombre());

		}

	}

	public void listeners() {

		

		comboBoxPotreros.addActionListener(e -> {

			String potreroelegido = (String) comboBoxPotreros.getSelectedItem();

			if (potreroelegido != null && !potreroelegido.equals("")) {

				ventana.remove(this);
				potreros = new PotrerosPanel(this, potreroelegido);
				ventana.add(potreros);
				ventana.setResizable(true);
				ventana.setSize(VentanaPrincipal.WIDTH, VentanaPrincipal.HEIGHT);
				ventana.setExtendedState(Frame.MAXIMIZED_BOTH);
				ventana.refresh();

			}

		});

		btnAgregar.addActionListener(e -> {

			icono = new ImageIcon(FileManager.imagenes.get("FINCA"));

			String nombre_potrero = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre del potrero",
					"Agregar Potrero", 0, icono, null, null);

			if (nombre_potrero != null && !nombre_potrero.equals(""))

			{

				if (PotreroCRUD.selectPotreroByID(nombre_potrero) == null) {

					PotreroCRUD.insert(nombre_potrero.toUpperCase());

					ArrayList<Potrero> potreros3 = PotreroCRUD.select();

					comboBoxPotreros.removeAllItems();
					comboBoxPotreros.addItem("");

					for (int i = 0; i < potreros3.size(); i++) {

						comboBoxPotreros.addItem(potreros3.get(i).getNombre());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Este potrero ya se encuentra en el programa");
				}

			}
			comprobarEliminar();
			String potreros = PotreroCRUD.select().size() + " Potreros";
			String vacas = ResCRUD.select().size() + " Reses";
			numeroDepotreros.setText(potreros);

			numeroDevacas.setText(vacas);

		});

		btnEliminar.addActionListener(e -> {

			ArrayList<Potrero> potreros2 = PotreroCRUD.select();

			String[] carreras = new String[potreros2.size()];

			for (int i = 0; i < carreras.length; i++) {

				carreras[i] = potreros2.get(i).getNombre();
			}

			Icon icon = new ImageIcon(FileManager.imagenes.get("BORRAR"));
			String resp = (String) JOptionPane.showInputDialog(null, "Seleccione el potrero que desea borrar",
					"Eliminar Potrero", JOptionPane.DEFAULT_OPTION, icon, carreras, carreras[0]);

			if (resp != null && !resp.equals("")) {

				PotreroCRUD.delete(resp);

				ArrayList<Potrero> potreros3 = PotreroCRUD.select();
				comboBoxPotreros.removeAllItems();
				comboBoxPotreros.addItem("");

				for (int i = 0; i < potreros3.size(); i++) {

					comboBoxPotreros.addItem(potreros3.get(i).getNombre());

				}

			}

			comprobarEliminar();
			String potreros = PotreroCRUD.select().size() + " Potreros";
			String vacas = ResCRUD.select().size() + " Reses";
			numeroDepotreros.setText(potreros);

			numeroDevacas.setText(vacas);

		});

		btnBuscar.addActionListener(e -> {

			icono = new ImageIcon(FileManager.imagenes.get("BUSCAR"));

			String nombreVaca = (String) JOptionPane.showInputDialog(null, "Ingrese el n\u00FAmero de la res", "Buscar res",
					0, icono, null, null);

			if (nombreVaca != null && !nombreVaca.equals(null)) {

				Res resita = ResCRUD.selectResByID(nombreVaca);

				if (resita != null) {

					System.out.println(resita);

					AgregarEditarResDialog dialog = new AgregarEditarResDialog(resita, this);

				} else {
					JOptionPane.showMessageDialog(null, "N\u00FAmero de res no encontrado");
				}
			}

		});

		btnGuardar.addActionListener(e -> {

			Usuario useractual = UsuarioCRUD.select().get(0);

			String nombre = useractual.getNombre();

			useractual.setNombre(txtnombreDueno.getText());
			useractual.setUbicacion(txtubicacion.getText());

			txtnombreDueno.setEditable(false);
			txtubicacion.setEditable(false);

			UsuarioCRUD.update(nombre, useractual);

		});

		btnEditar.addActionListener(e -> {

			txtnombreDueno.setEditable(true);
			txtubicacion.setEditable(true);

		});

		btnreporteDestete.addActionListener(e -> {

			if (ResCRUD.reporteDestete().size() == 0) {

				JOptionPane.showMessageDialog(null, "No hay reses que cumplan con este reporte");
			} else {
				
				ReporteDialog dialog = new ReporteDialog("Reporte de Destete", 1);
			}

		
		});

		btnreportePartos.addActionListener(e -> {

			if (ResCRUD.reportePartos().size() == 0) {

				JOptionPane.showMessageDialog(null, "No hay reses que cumplan con este reporte");
			} else {
				ReporteDialog dialog = new ReporteDialog("Reporte de Partos", 2);
			}
		});

		btnreporteVacuna.addActionListener(e -> {

			if (ResCRUD.reporteVacunaNotificaciones().size() == 0) {

				JOptionPane.showMessageDialog(null, "No hay reses que cumplan con este reporte");
			} else {
				ReporteDialog dialog = new ReporteDialog("Reporte de Vacuna", 3);

			}
		});

		btnreportePurgante.addActionListener(e -> {

			if (ResCRUD.reportePurgado().size() == 0) {

				JOptionPane.showMessageDialog(null, "No hay reses que cumplan con este reporte");
			} else {
				ReporteDialog dialog = new ReporteDialog("Reporte de Purgado", 4);
			}
		});

		btnEstadistica.addActionListener(e -> {

			EstadisticaDialog dialog = new EstadisticaDialog(1, "");

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

	public void actualizarreses(int cantidad) {

		numeroDevacas.setText(cantidad + "");

		ArrayList<Potrero> potreros3 = PotreroCRUD.select();
		comboBoxPotreros.removeAllItems();
		comboBoxPotreros.addItem("");

		for (int i = 0; i < potreros3.size(); i++) {

			comboBoxPotreros.addItem(potreros3.get(i).getNombre());

		}

		numeroDepotreros.setText(potreros3.size() + " Potreros");

	}

	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
