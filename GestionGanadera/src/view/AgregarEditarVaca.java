package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class AgregarEditarVaca extends JDialog {
	private JComboBox comboMesNac;
	private JTextField txtColor;
	private JComboBox comboPotreros;
	private JComboBox comboMesEmbarazo;
	private JComboBox comboEmbarazada;
	private JComboBox comboGenero;
	private JComboBox comboAnioNac;
	private JButton btnCargarFoto;
	private JComboBox comboDiaEmbarazo;
	private JButton btnGuardarCerrar;
	private JButton btnEditar;
	private JComboBox comboAnioEmbarazo;
	private JComboBox comboTipo;
	private JTextField txtNumero;
	private JComboBox comboDiaNac;
	private JLabel lblFoto;
	private JButton btnRegistroPeso;
	private JTextArea txtObservaciones;
	private JTextField txtMadre;
	private JTextField txtUltimoPeso;
	private JButton btnCrias;
	private JButton btnRegistroVacuna;
	private JComboBox comboPurgado;
	private JComboBox comboAnioPurgado;
	private JComboBox comboMesPurgado;
	private JComboBox comboDiaPurgado;

	public AgregarEditarVaca() {
		getContentPane().setLayout(new GridLayout(1, 2));
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setComponents();
		listeners();
		setVisible(true);

	}

	public void setComponents() {

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Informaci\u00F3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		btnEditar = new JButton("Editar");
		panel_2.add(btnEditar);

		btnGuardarCerrar = new JButton("Guardar y cerrar");
		panel_2.add(btnGuardarCerrar);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 1));

		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(10, 2));

		JLabel lblNewLabel_22_7 = new JLabel("Numero");
		lblNewLabel_22_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_7.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_7);

		txtNumero = new JTextField("");
		panelAux.add(txtNumero);

		JLabel lblNewLabel_22_9 = new JLabel("G\u00E9nero");
		lblNewLabel_22_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_9.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_9);

		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el g\u00E9nero", "HEMBRA", "MACHO" }));
		panelAux.add(comboGenero);

		JLabel lblNewLabel_22_10 = new JLabel("Tipo");
		lblNewLabel_22_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_22_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(lblNewLabel_22_10);

		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el tipo" }));
		panelAux.add(comboTipo);

		JLabel lblNewLabel_22_12 = new JLabel("Color");
		lblNewLabel_22_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_12.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_12);

		txtColor = new JTextField("");
		panelAux.add(txtColor);

		JLabel lblNewLabel_22_14 = new JLabel("Fecha nacimiento");
		lblNewLabel_22_14.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_14.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_14);

		JPanel lblNewLabel_22_15 = new JPanel();
		panelAux.add(lblNewLabel_22_15);
		lblNewLabel_22_15.setLayout(new GridLayout(1, 3));

		comboDiaNac = new JComboBox();
		lblNewLabel_22_15.add(comboDiaNac);

		comboMesNac = new JComboBox();
		lblNewLabel_22_15.add(comboMesNac);

		comboAnioNac = new JComboBox();
		lblNewLabel_22_15.add(comboAnioNac);

		JLabel lblNewLabel_22_16 = new JLabel("\u00DAltimo Peso");
		lblNewLabel_22_16.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_22_16.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_16);

		txtUltimoPeso = new JTextField("");
		panelAux.add(txtUltimoPeso);

		JLabel lblNewLabel_22_18 = new JLabel("\u00BFEmbarazada?");
		lblNewLabel_22_18.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_18.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_18);

		comboEmbarazada = new JComboBox();
		comboEmbarazada.setModel(new DefaultComboBoxModel(new String[] { "NO APLICA", "NO", "SI" }));
		panelAux.add(comboEmbarazada);
		comboEmbarazada.setEnabled(false);

		JLabel lblNewLabel_22_20 = new JLabel("Fecha embarazo");
		lblNewLabel_22_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22_20.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_22_20);

		panel_3.add(panelAux);

		JPanel lblNewLabel_22_21 = new JPanel();
		panelAux.add(lblNewLabel_22_21);
		lblNewLabel_22_21.setLayout(new GridLayout(1, 3));

		comboDiaEmbarazo = new JComboBox();
		comboDiaEmbarazo.setEnabled(false);
		lblNewLabel_22_21.add(comboDiaEmbarazo);

		comboMesEmbarazo = new JComboBox();
		comboMesEmbarazo.setEnabled(false);
		lblNewLabel_22_21.add(comboMesEmbarazo);

		comboAnioEmbarazo = new JComboBox();
		comboAnioEmbarazo.setEnabled(false);
		lblNewLabel_22_21.add(comboAnioEmbarazo);
		
		JLabel lblNewLabel_4 = new JLabel("Madre");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_4);
		
		txtMadre = new JTextField("");
		txtMadre.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(txtMadre);
		
		JLabel lblNewLabel_18 = new JLabel("Crias");
		lblNewLabel_18.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNewLabel_18);
		
		btnCrias = new JButton("Ver Crias");
		panelAux.add(btnCrias);

		JPanel panelObservaciones = new JPanel();
		panel_3.add(panelObservaciones);
		panelObservaciones.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panelObservaciones.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(2, 1));
		
		JPanel lblNewLabel_15 = new JPanel();
		panel_7.add(lblNewLabel_15);
		lblNewLabel_15.setLayout(new GridLayout(4,2));
		
		JLabel lblNewLabel_23_5 = new JLabel("\u00BFPurgado?");
		lblNewLabel_23_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_23_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.add(lblNewLabel_23_5);
		
		comboPurgado = new JComboBox();
		comboPurgado.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "NO", "EN PROCESO", "SI"}));
		lblNewLabel_15.add(comboPurgado);
		
		JLabel lblNewLabel_16 = new JLabel("Fecha Purgado");
		lblNewLabel_16.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.add(lblNewLabel_16);
		
		JPanel lblNewLabel_22 = new JPanel();
		lblNewLabel_15.add(lblNewLabel_22);
		lblNewLabel_22.setLayout(new GridLayout(1,3));
		
		comboDiaPurgado = new JComboBox();
		lblNewLabel_22.add(comboDiaPurgado);
		
		comboMesPurgado = new JComboBox();
		lblNewLabel_22.add(comboMesPurgado);
		
		comboAnioPurgado = new JComboBox();
		lblNewLabel_22.add(comboAnioPurgado);
		
		JLabel lblNewLabel_22_1 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_22_1);
		
		JLabel lblNewLabel_22_2 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_22_2);
		
		JLabel lblNewLabel_22_3 = new JLabel("Observaciones");
		lblNewLabel_22_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_22_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_15.add(lblNewLabel_22_3);
		
		JLabel lblNewLabel_22_4 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_22_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);

		txtObservaciones = new JTextArea();
		txtObservaciones.setLineWrap(true);
		scrollPane.setViewportView(txtObservaciones);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1));

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(1, 3));

		JLabel lblNewLabel_1 = new JLabel("");
		panel_6.add(lblNewLabel_1);

		btnCargarFoto = new JButton("Cargar Foto");
		panel_6.add(btnCargarFoto);

		JLabel lblNewLabel_2 = new JLabel("");
		panel_6.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Foto");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_3, BorderLayout.NORTH);

		lblFoto = new JLabel("No hay foto");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblFoto, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(12, 2));

		JLabel lblNewLabel_5 = new JLabel("");
		panel_5.add(lblNewLabel_5);

		JLabel lblNewLabel_9 = new JLabel("");
		panel_5.add(lblNewLabel_9);

		JLabel lblNewLabel_6 = new JLabel("");
		panel_5.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("");
		panel_5.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("");
		panel_5.add(lblNewLabel_8);

		JLabel lblNewLabel_10 = new JLabel("");
		panel_5.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("");
		panel_5.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("");
		panel_5.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Registro Vacuna");
		lblNewLabel_13.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_13);

		btnRegistroVacuna = new JButton("Ver registro vacuna");
		btnRegistroVacuna.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_5.add(btnRegistroVacuna);

		JLabel lblRegistro = new JLabel("Registro Peso");
		lblRegistro.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblRegistro);

		btnRegistroPeso = new JButton("Ver registro peso");
		btnRegistroPeso.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnRegistroPeso.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(btnRegistroPeso);

		JLabel lblNewLabel_17 = new JLabel("Potrero");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_17);

		comboPotreros = new JComboBox();
		panel_5.add(comboPotreros);

		JLabel lblNewLabel_21 = new JLabel("");
		panel_5.add(lblNewLabel_21);

		JLabel lblNewLabel_19 = new JLabel("");
		panel_5.add(lblNewLabel_19);

		JLabel lblNewLabel_20 = new JLabel("");
		panel_5.add(lblNewLabel_20);

		JLabel lblNewLabel_20_1 = new JLabel("");
		panel_5.add(lblNewLabel_20_1);

		JLabel lblNewLabel_20_2 = new JLabel("");
		panel_5.add(lblNewLabel_20_2);

		JLabel lblNewLabel_20_3 = new JLabel("");
		panel_5.add(lblNewLabel_20_3);

		JLabel lblNewLabel_20_4 = new JLabel("");
		panel_5.add(lblNewLabel_20_4);

		JLabel lblNewLabel_20_5 = new JLabel("");
		panel_5.add(lblNewLabel_20_5);

		JPanel panel_2_2 = new JPanel();
		panel_5.add(panel_2_2);

		JPanel panel_2_1 = new JPanel();
		panel_5.add(panel_2_1);

	}

	public void listeners() {

	}

	public JComboBox getComboMesNac() {
		return comboMesNac;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public JComboBox getComboPotreros() {
		return comboPotreros;
	}

	public JComboBox getComboMesEmbarazo() {
		return comboMesEmbarazo;
	}

	public JComboBox getComboEmbarazada() {
		return comboEmbarazada;
	}

	public JComboBox getComboGenero() {
		return comboGenero;
	}

	public JComboBox getComboAnioNac() {
		return comboAnioNac;
	}

	public JButton getBtnCargarFoto() {
		return btnCargarFoto;
	}

	public JComboBox getComboDiaEmbarazo() {
		return comboDiaEmbarazo;
	}

	public JButton getBtnGuardarCerrar() {
		return btnGuardarCerrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JComboBox getComboAnioEmbarazo() {
		return comboAnioEmbarazo;
	}

	public JComboBox getComboTipo() {
		return comboTipo;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public JComboBox getComboDiaNac() {
		return comboDiaNac;
	}

	public JLabel getLblFoto() {
		return lblFoto;
	}

	public JButton getBtnRegistroPeso() {
		return btnRegistroPeso;
	}

	public JTextArea getTxtObservaciones() {
		return txtObservaciones;
	}
	public JTextField getTxtMadre() {
		return txtMadre;
	}
	public JTextField getTxtUltimoPeso() {
		return txtUltimoPeso;
	}
	public JButton getBtnCrias() {
		return btnCrias;
	}
	public JButton getBtnRegistroVacuna() {
		return btnRegistroVacuna;
	}
	public JComboBox getComboPurgado() {
		return comboPurgado;
	}
	public JComboBox getComboAnioPurgado() {
		return comboAnioPurgado;
	}
	public JComboBox getComboMesPurgado() {
		return comboMesPurgado;
	}
	public JComboBox getComboDiaPurgado() {
		return comboDiaPurgado;
	}
}
