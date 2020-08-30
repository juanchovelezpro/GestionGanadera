package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import db.PotreroCRUD;
import db.ResCRUD;
import model.Potrero;
import model.Res;
import tools.DocsImporter;
import tools.FileManager;

public class PotrerosPanel extends JPanel {

	private InicioPanel inicio;
	private JPopupMenu menu;
	private ArrayList<Res> ganado;
	private JComboBox comboHembraMacho;
	private JButton btnAgregar;
	private JButton btnReporteVacunas;
	private JButton btnReportePurgantes;
	private JButton btnNotificaciones;
	private JLabel lblNombrePotrero;
	private JPanel panelResTable;
	private JTable tablaRes;
	private TableRowSorter<ModelTable> sorter;
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
		comboHembraMacho.setModel(new DefaultComboBoxModel(new String[] { "VER TODO", "HEMBRAS", "MACHOS" }));
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
		ganado = PotreroCRUD.selectRes(potrero_elegido);

		modelRes = new ModelTable();

		crearTablaRes();
		add(panelResTable, BorderLayout.CENTER);
		panelResTable.setLayout(new GridLayout(1, 1));

		popUpMenu();

	}

	public void crearTablaRes() {

		fillData();

		tablaRes = new JTable(modelRes);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < modelRes.getColumnCount(); i++) {

			tablaRes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		}
		tablaRes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tablaRes.setRowHeight(25);
		tablaRes.setShowHorizontalLines(true);
		tablaRes.setShowVerticalLines(true);
		tablaRes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		scroller.setViewportView(tablaRes);
		tablaRes.setFillsViewportHeight(true);
		panelResTable.add(scroller);

	}

	public void fillData() {

		String[] columns = { "ID", "TIPO", "GENERO", "COLOR", "FECHA NACIMIENTO", "VIVO", "MADRE", "OBSERVACIONES" };
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

		modelRes.setColumns(columns);
		modelRes.setData(data);

	}

	public void refreshTable() {

		ganado = PotreroCRUD.selectRes(potrero_elegido);
		fillData();
		modelRes.fireTableDataChanged();
		listeners();

	}

	public void listeners() {

		// Abre el panel para agregar una vaca
		btnAgregar.addActionListener(e -> {

			AgregarEditarResDialog dialogAgregarEditar = new AgregarEditarResDialog(null, this);

		});

		// Regresar al panel inicio
		btnRegresar.addActionListener(e -> {

			int reses = ResCRUD.select().size();

			inicio.actualizarreses(reses);

			inicio.getVentana().remove(this);
			inicio.getVentana().setSize(800, 400);
			inicio.getVentana().setResizable(false);
			inicio.getVentana().setLocationRelativeTo(null);
			inicio.getVentana().add(inicio);
			inicio.getVentana().refresh();

		});

		// Abrir panel de notificaciones
		btnNotificaciones.addActionListener(e -> {

			NotificacionesDialog notificaciones = new NotificacionesDialog(this);

		});

		btnReportePurgantes.addActionListener(e -> {

		});

		btnReporteVacunas.addActionListener(e -> {

		});

		// Importar el documento de excel.
		btnImportar.addActionListener(e -> {

			importarDatos();
			refreshTable();

		});

		// Filtro
		comboHembraMacho.addActionListener(e -> {

			switch (comboHembraMacho.getSelectedIndex()) {

			case 0:
				// Mostrar todo - Quitar Filtro
				tablaRes.setRowSorter(null);
				break;

			case 1:
				// Filtrar por hembras
				sorter = new TableRowSorter<>(modelRes);
				sorter.setRowFilter(RowFilter.regexFilter("H", 2));
				tablaRes.setRowSorter(sorter);
				break;

			case 2:
				// Filtrar por machos
				sorter = new TableRowSorter<>(modelRes);
				sorter.setRowFilter(RowFilter.regexFilter("M", 2));
				tablaRes.setRowSorter(sorter);
				break;

			}

		});

		// Acciones y eventos con el mouse en la tabla.
		tablaRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Para editar la res seleccionada.
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {

					int row = tablaRes.getSelectedRow();

					// Por si se encuentra sortered or filtered la tabla
					if (tablaRes.getRowSorter() != null)
						row = tablaRes.getRowSorter().convertRowIndexToModel(row);

					Res res = ResCRUD.selectResByID(modelRes.getValueAt(row, 0).toString());

					// Abre el panel para editar la vaca seleccionada.

					AgregarEditarResDialog dialogAgregarEditar = new AgregarEditarResDialog(res, PotrerosPanel.this);

				}

				// Click derecho
				if (e.getButton() == MouseEvent.BUTTON3) {

					menu.show(e.getComponent(), e.getX(), e.getY());
				}

			}
		});

	}

	public void popUpMenu() {

		menu = new JPopupMenu();

		JMenuItem vacunar = new JMenuItem("Vacunar");
		vacunar.addActionListener(a -> {

		});

		JMenuItem purgar = new JMenuItem("Purgar");
		purgar.addActionListener(a -> {

		});

		JMenuItem trasladar = new JMenuItem("Trasladar");
		trasladar.addActionListener(a -> {

			trasladar();

		});

		JMenuItem eliminar = new JMenuItem("Eliminar");
		eliminar.addActionListener(a -> {

			eliminar();

		});

		menu.add(vacunar);
		menu.addSeparator();
		menu.add(purgar);
		menu.addSeparator();
		menu.add(trasladar);
		menu.addSeparator();
		menu.add(eliminar);

	}

	public JTable getTablaRes() {
		return tablaRes;
	}

	public void setTablaRes(JTable tablaRes) {
		this.tablaRes = tablaRes;
	}

	public ModelTable getModelRes() {
		return modelRes;
	}

	public void setModelRes(ModelTable modelRes) {
		this.modelRes = modelRes;
	}

	public void vacunar() {

	}

	public void purgar() {

	}

	public void trasladar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		// Por si se encuentra "sorted or filtered" la tabla
		if (tablaRes.getRowSorter() != null) {

			for (int i = 0; i < rowsSelected.length; i++) {

				rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

			}

		}

		ArrayList<Potrero> potreros = PotreroCRUD.select();
		String[] potreritos = new String[potreros.size()];

		for (int i = 0; i < potreritos.length; i++) {
			potreritos[i] = potreros.get(i).getNombre();
		}

		Icon icon = new ImageIcon("");

		String resp = (String) JOptionPane.showInputDialog(null,
				"Seleccione el potrero al que desea trasladar las reses", "Trasladar de potrero",
				JOptionPane.DEFAULT_OPTION, icon, potreritos, potreritos[0]);

		if (resp != null) {
			int option = JOptionPane.showConfirmDialog(this,
					"\u00BFEsta seguro que desea trasladar " + rowsSelected.length + " reses al potrero " + resp + "?",
					"Trasladar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (option == 0) {

			}
		}

	}

	public void eliminar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		// Por si se encuentra "sorted or filtered" la tabla
		if (tablaRes.getRowSorter() != null) {

			for (int i = 0; i < rowsSelected.length; i++) {

				rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

			}

		}

		int option = JOptionPane.showConfirmDialog(this,
				"\u00BFEsta seguro que desea eliminar " + rowsSelected.length + " reses?", "Eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		// Si presiona SI
		if (option == 0) {

			new Thread() {
				BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

				int value = 0;

				@Override
				public void run() {

					for (int i = 0; i < rowsSelected.length; i++) {

						String id = modelRes.getValueAt(rowsSelected[i], 0).toString();
						ResCRUD.delete(id);
						value++;
						progreso.getProgreso().setValue(value);

					}

					refreshTable();
					progreso.dispose();

				}

			}.start();

		}

	}

	public void importarDatos() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(inicio.getVentana());
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

				// Cargar definitivamente el excel al programa y base de datos.

				DocsImporter.importData(fs, potrero_elegido, this);
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
