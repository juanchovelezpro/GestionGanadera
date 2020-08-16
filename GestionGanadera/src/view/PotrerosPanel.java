package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import db.PotreroCRUD;
import db.ResCRUD;
import javafx.scene.input.ScrollEvent;
import model.Res;
import tools.DocsImporter;

public class PotrerosPanel extends JPanel {

	private InicioPanel inicio;
	private JComboBox comboHembraMacho;
	private JButton btnAgregar;
	private JButton btnReporteVacunas;
	private JButton btnReportePurgantes;
	private JButton btnNotificaciones;
	private JLabel lblNombrePotrero;
	private JPanel panelResTable;
	private JTable tablaRes;
	private ModelTable modelRes;
	private JScrollPane scroller;
	private String potrero_elegido;
	private JButton btnRegresar;
	private JButton btnImportar;

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

		btnImportar = new JButton("Importar Datos");
		panelSuperior.add(btnImportar);

		btnNotificaciones = new JButton("Notificaciones");
		panelSuperior.add(btnNotificaciones);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new GridLayout(1, 6));

		btnAgregar = new JButton("Agregar vaca");
		panelInferior.add(btnAgregar);

		JLabel lblNewLabel = new JLabel("");
		panelInferior.add(lblNewLabel);

		btnReporteVacunas = new JButton("Reporte Vacunaci\u00F3n");
		panelInferior.add(btnReporteVacunas);

		JLabel lblNewLabel_3 = new JLabel("");
		panelInferior.add(lblNewLabel_3);

		btnReportePurgantes = new JButton("Reporte Desparasitar");
		panelInferior.add(btnReportePurgantes);

		panelResTable = new JPanel();
		scroller = new JScrollPane();
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
		scroller.setViewportView(tablaRes);
		tablaRes.setFillsViewportHeight(true);
		panelResTable.add(scroller);

	}

	public void refreshTable() {

		scroller.getViewport().removeAll();
		crearTablaRes(potrero_elegido);
		listeners();

	}

	public void listeners() {

		btnAgregar.addActionListener(e -> {

			AgregarEditarVaca dialog = new AgregarEditarVaca(null, this);

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

		btnReportePurgantes.addActionListener(e -> {

		});

		btnReporteVacunas.addActionListener(e -> {

		});
		
		btnImportar.addActionListener(e ->{
			
			importarDatos();
			refreshTable();
			
		});

		tablaRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int row = tablaRes.getSelectedRow();
					Res res = ResCRUD.selectResByID(modelRes.getData()[row][0].toString());

					AgregarEditarVaca agregar = new AgregarEditarVaca(res, PotrerosPanel.this);

				}

			}
		});

	}
	
	public void importarDatos() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		fileChooser.isVisible();

		FileInputStream fs = null;

		try {
			if (fileChooser.getSelectedFile() != null) {
				fs = new FileInputStream(fileChooser.getSelectedFile());

			} else {

				JOptionPane.showMessageDialog(null, "No se ha seleccionado un archivo", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

			if (fs != null) {

				// Cargar definitivamente el excel al programa y realizar todos los calculos y
				// procesos.

				DocsImporter.importData(fs, potrero_elegido);
				JOptionPane.showMessageDialog(null, "El archivo se ha cargado correctamente!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				fs.close();

			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		
	}

	public String getPotrero_elegido() {
		return potrero_elegido;
	}

	public void setPotrero_elegido(String potrero_elegido) {
		this.potrero_elegido = potrero_elegido;
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
		return btnReporteVacunas;
	}

	public JButton getBtnReportePartos() {
		return btnReportePurgantes;
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

	public JButton getBtnImportar() {
		return btnImportar;
	}
}
