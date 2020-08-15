package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import db.PotreroCRUD;
import db.ResCRUD;
import model.Res;

public class PotrerosPanel extends JPanel {

	private InicioPanel inicio;
	private JComboBox comboHembraMacho;
	private JButton btnAgregar;
	private JButton btnReporteVitamina;
	private JButton btnReportePartos;
	private JButton btnNotificaciones;
	private JLabel lblNombrePotrero;
	private JPanel panelResTable;
	private JTable tablaRes;
	private ModelTable modelRes;
	private JScrollPane scroller;
	private String potrero_elegido;
	private JButton btnRegresar;

	public PotrerosPanel(InicioPanel inicio, String potreroelegido) {

		this.inicio = inicio;
		potrero_elegido = potreroelegido;

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
		panel_2.setLayout(new GridLayout(1, 10));

		btnRegresar = new JButton("Regresar");
		panel_2.add(btnRegresar);

		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);

		lblNombrePotrero = new JLabel(potrero_elegido);
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

		panelResTable = new JPanel();
		crearTablaRes(potrero_elegido);
		add(panelResTable, BorderLayout.CENTER);
		panelResTable.setLayout(new GridLayout(1, 1));

	}

	public void crearTablaRes(String nombrep) {

		String[] columns = { "ID", "TIPO", "GENERO", "COLOR", "FECHA NACIMIENTO", "VIVO", "MADRE", "OBSERVACIONES" };
		ArrayList<Res> ganado = PotreroCRUD.selectRes(nombrep);
		Object[][] data = new Object[ganado.size()][columns.length];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {

				if (j == 0)
					data[i][j] = ganado.get(i).getResID();

				if (j == 1)
					data[i][j] = ganado.get(i).getTipo();

				if (j == 2)
					data[i][j] = ganado.get(i).getGenero();

				if (j == 3)
					data[i][j] = ganado.get(i).getColor();

				if (j == 4)
					data[i][j] = ganado.get(i).getFecha_nacimiento();

				if (j == 5) {

					int vivo = ganado.get(i).getVivo();

					String is = vivo == 0 ? "MUERTO" : "VIVO";

					data[i][j] = is;

				}

				if (j == 6)
					data[i][j] = ganado.get(i).getMadreID();

				if (j == 7)
					data[i][j] = ganado.get(i).getObservaciones();

			}

		}

		modelRes = new ModelTable();
		modelRes.setColumns(columns);
		modelRes.setData(data);
		tablaRes = new JTable(modelRes);
		tablaRes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaRes.setShowHorizontalLines(true);
		tablaRes.setShowVerticalLines(true);
		tablaRes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

		scroller = new JScrollPane(tablaRes);
		tablaRes.setFillsViewportHeight(true);
		panelResTable.add(scroller);

	}

	public void listeners() {

		btnAgregar.addActionListener(e -> {

			AgregarEditarVaca dialog = new AgregarEditarVaca(null);

		});

		btnRegresar.addActionListener(e -> {

			inicio.getVentana().remove(this);
			inicio.getVentana().setSize(800, 400);
			inicio.getVentana().setResizable(false);
			inicio.getVentana().setLocationRelativeTo(null);
			inicio.getVentana().add(inicio);
			inicio.getVentana().refresh();

		});

		btnNotificaciones.addActionListener(e -> {

		});

		btnReportePartos.addActionListener(e -> {

		});

		btnReporteVitamina.addActionListener(e -> {

		});

		tablaRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int row = tablaRes.getSelectedRow();
					Res res = ResCRUD.selectResByID(modelRes.getData()[row][0].toString());

					AgregarEditarVaca agregar = new AgregarEditarVaca(res);

				}

			}
		});

	}

	public InicioPanel getInicio() {
		return inicio;
	}

	public void setInicio(InicioPanel inicio) {
		this.inicio = inicio;
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

	public JPanel getPanelResTable() {
		return panelResTable;
	}

}
