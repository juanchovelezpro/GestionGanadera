package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Estadisticas;
import tools.FileManager;

public class EstadisticaDialog extends JDialog {
	private JButton btnRegresar;
	private JLabel lblNewLabel_32;
	private JLabel lblNewLabel_17;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_29;
	private JLabel lblNewLabel_31;
	private JLabel lblNewLabel_23;
	private JLabel lblNewLabel_24;
	private JLabel lblNewLabel_26;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_13;
	private JLabel numeroPotreros;
	private JLabel numeroReses;
	private JLabel numeroHembras;
	private JLabel numeroCH;
	private JLabel numeroHL;
	private JLabel numeroNV;
	private JLabel numeroVH;
	private JLabel numeroVP;
	private JLabel numeroMachos;
	private JLabel numeroCM;
	private JLabel numeroML;
	private JLabel numeroMC;
	private JLabel numeroTP;
	private JLabel labelPotrero;
	private String nombrePotrero;
	private JLabel titulo;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JPanel panel_4;

	public EstadisticaDialog(int valor, String nombrePotrero) {

		this.nombrePotrero = nombrePotrero;
		setTitle("Estad\u00EDsticas");
		ImageIcon icono = new ImageIcon(FileManager.imagenes.get("ESTADISTICA"));
		setIconImage(icono.getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(500, 420);
		setLocationRelativeTo(null);
		setResizable(false);

		Components();
		listeners();
		setModalityType(ModalityType.APPLICATION_MODAL);
		elegir(valor);

		setVisible(true);

	}

	public void Components() {

		PanelColorEstadisticas panel = new PanelColorEstadisticas();
		panel.setBackground(new Color(60, 179, 113, 100));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		panel_4.setBackground(new Color(245, 245, 220, 100));
		panel.add(panel_4, BorderLayout.NORTH);

		titulo = new JLabel("");
		titulo.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel_4.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 220, 100));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(15, 2));

		JLabel lblNewLabel_19 = new JLabel("");
		panel_1.add(lblNewLabel_19);

		JLabel lblNewLabel_18 = new JLabel("");
		panel_1.add(lblNewLabel_18);

		labelPotrero = new JLabel("Cantidad de potreros...........................");
		labelPotrero.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(labelPotrero);

		numeroPotreros = new JLabel("");
		numeroPotreros.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroPotreros);

		lblNewLabel_17 = new JLabel("Cantidad de reses..............................");
		lblNewLabel_17.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_17);

		numeroReses = new JLabel("");
		numeroReses.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroReses);

		JLabel lblNewLabel_3 = new JLabel("Cantidad de hembras........................");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_3);

		numeroHembras = new JLabel("");
		numeroHembras.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroHembras);

		lblNewLabel_5 = new JLabel("    Cantidad de CH...............................");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_5);

		numeroCH = new JLabel("");
		numeroCH.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroCH);

		lblNewLabel_29 = new JLabel("    Cantidad de HL..............................");
		lblNewLabel_29.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_29);

		numeroHL = new JLabel("");
		numeroHL.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroHL);

		lblNewLabel_31 = new JLabel("    Cantidad de NV...............................");
		lblNewLabel_31.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_31);

		numeroNV = new JLabel("");
		numeroNV.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroNV);

		lblNewLabel_23 = new JLabel("    Cantidad de VH.................................");
		lblNewLabel_23.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_23);

		numeroVH = new JLabel("");
		numeroVH.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroVH);

		lblNewLabel_24 = new JLabel("    Cantidad de VP...............................");
		lblNewLabel_24.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_24);

		numeroVP = new JLabel("");
		numeroVP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroVP);

		lblNewLabel_26 = new JLabel("Cantidad de machos..............................");
		lblNewLabel_26.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_26);

		numeroMachos = new JLabel("");
		numeroMachos.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroMachos);

		lblNewLabel_7 = new JLabel("    Cantidad de CM...............................");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_7);

		numeroCM = new JLabel("");
		numeroCM.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroCM);

		lblNewLabel_9 = new JLabel("    Cantidad de ML.............................");
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_9);

		numeroML = new JLabel("");
		numeroML.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroML);

		lblNewLabel_11 = new JLabel("    Cantidad de MC............................");
		lblNewLabel_11.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_11);

		numeroMC = new JLabel("");
		numeroMC.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroMC);

		lblNewLabel_13 = new JLabel("    Cantidad de TP...........................");
		lblNewLabel_13.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_13);

		numeroTP = new JLabel("");
		numeroTP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(numeroTP);

		JLabel lblNewLabel_15 = new JLabel("");
		panel_1.add(lblNewLabel_15);

		JLabel lblNewLabel_14 = new JLabel("");
		panel_1.add(lblNewLabel_14);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 245, 220, 100));
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 3));

		JLabel lblNewLabel_40 = new JLabel("");
		panel_2.add(lblNewLabel_40);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnRegresar.setBackground(new Color(255, 255, 255, 30));
		panel_2.add(btnRegresar);

		JLabel lblNewLabel_33 = new JLabel("");
		panel_2.add(lblNewLabel_33);

		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("        ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_1);

		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(245, 245, 220, 100));
		panel.add(panel_20, BorderLayout.WEST);

		JLabel lblNewLabel_2 = new JLabel("                ");
		panel_20.add(lblNewLabel_2, BorderLayout.EAST);

	};

	public void elegir(int valor) {

		refrescar();

		if (valor == 1) {
			actualizarGeneral();
		} else if (valor == 2) {

			actualizarPorPotrero(nombrePotrero);
		}
	}

	public void actualizarGeneral() {

		ArrayList<Integer> valores = Estadisticas.estadisticageneral();
		String espacio = "......";

		titulo.setText("Estad\u00EDsticas generales");
		numeroPotreros.setText(espacio + valores.get(0));
		numeroReses.setText(espacio + valores.get(1));
		numeroHembras.setText(espacio + valores.get(2));
		numeroMachos.setText(espacio + valores.get(3));
		numeroCH.setText(espacio + valores.get(4));
		numeroHL.setText(espacio + valores.get(5));
		numeroNV.setText(espacio + valores.get(6));
		numeroVH.setText(espacio + valores.get(7));
		numeroVP.setText(espacio + valores.get(8));
		numeroCM.setText(espacio + valores.get(9));
		numeroML.setText(espacio + valores.get(10));
		numeroMC.setText(espacio + valores.get(11));
		numeroTP.setText(espacio + valores.get(12));

	}

	public void actualizarPorPotrero(String nombrePotrero) {

		ArrayList<Integer> valores = Estadisticas.estadisticasPorPotrero(nombrePotrero);
		String espacio = "......";
		titulo.setText(nombrePotrero);
		numeroPotreros.setText("");
		labelPotrero.setText("");
		numeroReses.setText(espacio + valores.get(0));
		numeroHembras.setText(espacio + valores.get(1));
		numeroMachos.setText(espacio + valores.get(2));
		numeroCH.setText(espacio + valores.get(3));
		numeroHL.setText(espacio + valores.get(4));
		numeroNV.setText(espacio + valores.get(5));
		numeroVH.setText(espacio + valores.get(6));
		numeroVP.setText(espacio + valores.get(7));
		numeroCM.setText(espacio + valores.get(8));
		numeroML.setText(espacio + valores.get(9));
		numeroMC.setText(espacio + valores.get(10));
		numeroTP.setText(espacio + valores.get(11));

	}

	public void refrescar() {
		numeroPotreros.setText("");
		numeroReses.setText("");
		numeroHembras.setText("");
		numeroCH.setText("");
		numeroHL.setText("");
		numeroNV.setText("");
		numeroVH.setText("");
		numeroVP.setText("");
		numeroMachos.setText("");
		numeroCM.setText("");
		numeroML.setText("");
		numeroMC.setText("");
		numeroTP.setText("");
	}

	public void listeners() {

		btnRegresar.addActionListener(e -> {

			dispose();

		});
	}

	public JLabel labelTitulo() {
		return titulo;
	}

	public JButton btnRegresar() {
		return btnRegresar;
	}

	public JLabel labelCantidadPotreros() {
		return lblNewLabel_32;
	}

	public JLabel labelResesTotales() {
		return lblNewLabel_17;
	}

	public JLabel labelCantidadCH() {
		return lblNewLabel_5;
	}

	public JLabel labelCantidadHL() {
		return lblNewLabel_29;
	}

	public JLabel labelCantidadNV() {
		return lblNewLabel_31;
	}

	public JLabel labelCantidadVH() {
		return lblNewLabel_23;
	}

	public JLabel labelCantidadVP() {
		return lblNewLabel_24;
	}

	public JLabel labelCantidadMachos() {
		return lblNewLabel_26;
	}

	public JLabel labelCantidadCM() {
		return lblNewLabel_7;
	}

	public JLabel labelCantidadML() {
		return lblNewLabel_9;
	}

	public JLabel labelCantidadMC() {
		return lblNewLabel_11;
	}

	public JLabel labelCantidadTP() {
		return lblNewLabel_13;
	}

	public JLabel numeroPotrero() {
		return numeroPotreros;
	}

	public JLabel numeroReses() {
		return numeroReses;
	}

	public JLabel numeroHembras() {
		return numeroHembras;
	}

	public JLabel numeroCH() {
		return numeroCH;
	}

	public JLabel numeroHL() {
		return numeroHL;
	}

	public JLabel numeroNV() {
		return numeroNV;
	}

	public JLabel numeroVH() {
		return numeroVH;
	}

	public JLabel numeroVP() {
		return numeroVP;
	}

	public JLabel numeroMachos() {
		return numeroMachos;
	}

	public JLabel numeroCM() {
		return numeroCM;
	}

	public JLabel numeroML() {
		return numeroML;
	}

	public JLabel numeroMC() {
		return numeroMC;
	}

	public JLabel numeroTP() {
		return numeroTP;
	}
}
