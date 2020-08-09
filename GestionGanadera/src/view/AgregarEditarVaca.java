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

import com.toedter.calendar.JCalendar;

import model.Res;

public class AgregarEditarVaca extends JDialog {

	private Res res;
	private JTextField txtColor;
	private JComboBox comboEmbarazada;
	private JComboBox comboGenero;
	private JButton btnGuardarCerrar;
	private JButton btnEditar;
	private JComboBox comboTipo;
	private JTextField txtNumero;
	private JTextArea txtObservaciones;
	private JTextField txtMadre;
	private JButton btnCrias;
	private JLabel lblTipo;
	private JButton btnFechaNacimiento;

	public AgregarEditarVaca(Res res) {

		this.res = res;
		
		if (res !=null)
			setTitle("Editar Vaca/ ID: " + res.getResID());
		else
			setTitle("Agregar vaca");
		
		
		getContentPane().setLayout(new GridLayout(1, 2));
		setSize(500, 700);
		setLocationRelativeTo(null);
		setComponents();
		listeners();
		setVisible(true);

	}

	public void setComponents() {
		
		JPanel infoPanel = new JPanel();
		getContentPane().add(infoPanel);
		infoPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Informaci\u00F3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelBotones = new JPanel();
		infoPanel.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(1, 0, 0, 0));

		btnEditar = new JButton("Editar");
		panelBotones.add(btnEditar);

		btnGuardarCerrar = new JButton("Guardar y cerrar");
		panelBotones.add(btnGuardarCerrar);

		JPanel panelGeneralInfo = new JPanel();
		infoPanel.add(panelGeneralInfo, BorderLayout.CENTER);
		panelGeneralInfo.setLayout(new GridLayout(2, 1));

		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(10, 2));

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNumero);

		txtNumero = new JTextField("");
		txtNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(txtNumero);

		JLabel lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblGenero);

		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el g\u00E9nero", "HEMBRA", "MACHO" }));
		panelAux.add(comboGenero);

		lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(lblTipo);

		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el tipo" }));
		panelAux.add(comboTipo);

		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblColor);

		txtColor = new JTextField("");
		txtColor.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(txtColor);

		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblFechaNacimiento);

		btnFechaNacimiento = new JButton("dd/mm/AAAA");
		btnFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(btnFechaNacimiento);

		JLabel lblEmbarazada = new JLabel("\u00BFEmbarazada?");
		lblEmbarazada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmbarazada.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblEmbarazada);

		comboEmbarazada = new JComboBox();
		comboEmbarazada.setModel(new DefaultComboBoxModel(new String[] { "NO APLICA", "NO", "SI" }));
		panelAux.add(comboEmbarazada);
		comboEmbarazada.setEnabled(false);

		JLabel lblFechaEmbarazo = new JLabel("Fecha embarazo");
		lblFechaEmbarazo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaEmbarazo.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblFechaEmbarazo);

		panelGeneralInfo.add(panelAux);

		JButton btnFechaEmbarazo = new JButton("dd/mm/AAAA");
		btnFechaEmbarazo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFechaEmbarazo.setEnabled(false);
		panelAux.add(btnFechaEmbarazo);

		JLabel lblMadre = new JLabel("Madre");
		lblMadre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMadre.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblMadre);

		txtMadre = new JTextField("");
		txtMadre.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(txtMadre);

		JLabel lblCrias = new JLabel("Crias");
		lblCrias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCrias.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblCrias);

		btnCrias = new JButton("Ver Crias");
		btnCrias.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(btnCrias);

		JLabel lblRegistroPeso = new JLabel("Registro Peso");
		lblRegistroPeso.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroPeso.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(lblRegistroPeso);

		JButton btnVerRegistroPeso = new JButton("Ver Registro Peso");
		btnVerRegistroPeso.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(btnVerRegistroPeso);

		JPanel panelObservaciones = new JPanel();
		panelGeneralInfo.add(panelObservaciones);
		panelObservaciones.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panelObservaciones.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(2, 1));

		JPanel lblNewLabel_15 = new JPanel();
		panel_7.add(lblNewLabel_15);
		lblNewLabel_15.setLayout(new GridLayout(5, 2));

		JLabel lblRegistroVacunas = new JLabel("Registro Vacunas");
		lblRegistroVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroVacunas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(lblRegistroVacunas);

		JButton btnRegistroVacunas = new JButton("Ver Registro Vacunas");
		btnRegistroVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistroVacunas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(btnRegistroVacunas);

		JLabel lblRegistroPurgantes = new JLabel("Registro Purgantes");
		lblRegistroPurgantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroPurgantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(lblRegistroPurgantes);

		JButton btnRegistroPurgantes = new JButton("Ver Registro Purgantes");
		btnRegistroPurgantes.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistroPurgantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(btnRegistroPurgantes);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_4);

		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_4_3);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservaciones.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_15.add(lblObservaciones);

		JLabel lblNewLabel_22_4 = new JLabel("");
		lblNewLabel_15.add(lblNewLabel_22_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);

		txtObservaciones = new JTextArea();
		txtObservaciones.setLineWrap(true);
		scrollPane.setViewportView(txtObservaciones);

	}

	public void cargarInfoRes() {

		if (res != null) {

			txtNumero.setText(res.getResID());
			String genero = res.getGenero().equals("H") ? "HEMBRA" : "MACHO";
			comboGenero.setSelectedItem(genero);
			comboTipo.setSelectedItem(res.getTipo());
			txtColor.setText(res.getColor());

		}

	}

	public void listeners() {

		btnFechaNacimiento.addActionListener(e -> {

			CalendarioDialog calendar = new CalendarioDialog(btnFechaNacimiento);

		});

	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public JComboBox getComboEmbarazada() {
		return comboEmbarazada;
	}

	public JComboBox getComboGenero() {
		return comboGenero;
	}

	public JButton getBtnGuardarCerrar() {
		return btnGuardarCerrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JComboBox getComboTipo() {
		return comboTipo;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public JTextArea getTxtObservaciones() {
		return txtObservaciones;
	}

	public JTextField getTxtMadre() {
		return txtMadre;
	}

	public JButton getBtnCrias() {
		return btnCrias;
	}

	public JButton getBtnFechaNacimiento() {
		return btnFechaNacimiento;
	}
}
