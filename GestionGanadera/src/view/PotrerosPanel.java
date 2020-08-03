package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PotrerosPanel extends JPanel {
	private JComboBox comboHembraMacho;
	private JButton btnAgregar;
	private JButton btnReporteVitamina;
	private JButton btnReportePartos;
	private JButton btnNotificaciones;
	private JLabel lblNombrePotrero;
	private VentanaPrincipal ventana;

	public PotrerosPanel(VentanaPrincipal ventana) {
		
		this.ventana = ventana;
		
		setLayout(new BorderLayout(0, 0));

		setComponents();
		listeners();

	}

	public void setComponents() {

		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(1, 5));
		
		JPanel panel_2 = new JPanel();
		panelSuperior.add(panel_2);
		panel_2.setLayout(new GridLayout(1,10));
		
		JButton btnRegresar = new JButton("");
		panel_2.add(btnRegresar);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);

		lblNombrePotrero = new JLabel("Nombre");
		lblNombrePotrero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombrePotrero.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblNombrePotrero);

		comboHembraMacho = new JComboBox();
		comboHembraMacho.setModel(new DefaultComboBoxModel(new String[] { "VER", "HEMBRAS", "MACHOS" }));
		panelSuperior.add(comboHembraMacho);

		JLabel lblCantVacas = new JLabel("# Vacas");
		lblCantVacas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantVacas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSuperior.add(lblCantVacas);

		JLabel lblNewLabel_1 = new JLabel("");
		panelSuperior.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		panelSuperior.add(lblNewLabel_2);

		btnNotificaciones = new JButton("Notificaciones");
		panelSuperior.add(btnNotificaciones);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new GridLayout(1, 6));

		btnAgregar = new JButton("Agregar vaca");
		panelInferior.add(btnAgregar);

		JLabel lblNewLabel = new JLabel("");
		panelInferior.add(lblNewLabel);

		btnReporteVitamina = new JButton("Reporte Vitaminas");
		panelInferior.add(btnReporteVitamina);

		JLabel lblNewLabel_3 = new JLabel("");
		panelInferior.add(lblNewLabel_3);

		btnReportePartos = new JButton("Reporte Desparasitar");
		panelInferior.add(btnReportePartos);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(1, 12));

	}

	public void listeners() {

		btnAgregar.addActionListener(e -> {

		});

		btnNotificaciones.addActionListener(e -> {

		});

		btnReportePartos.addActionListener(e -> {

		});

		btnReporteVitamina.addActionListener(e -> {

		});

	}

	public JComboBox getComboHembraMacho() {
		return comboHembraMacho;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnReporteDestete() {
		return btnReporteVitamina;
	}

	public JButton getBtnReportePartos() {
		return btnReportePartos;
	}

	public JButton getBtnNotificaciones() {
		return btnNotificaciones;
	}
	public JLabel getLblNombrePotrero() {
		return lblNombrePotrero;
	}
}
