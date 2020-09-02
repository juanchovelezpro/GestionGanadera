package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
import db.PurganteCRUD;
import db.ResCRUD;
import db.VacunaCRUD;
import model.Potrero;
import model.Purgante;
import model.Res;
import model.Vacuna;
import tools.DocsImporterExporter;
import tools.FileManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PotrerosPanel extends JPanel {

	private InicioPanel inicio;
	private JPopupMenu menu;
	private ArrayList<Res> ganado;
	private JComboBox comboHembraMacho;
	private JButton btnAgregar;
	private JButton btnNotificaciones;
	private JLabel lblNombrePotrero;
	private JPanel panelResTable;
	private JTable tablaRes;
	private TableRowSorter<ModelTable> sorter;
	private ModelTable modelRes;
	private JScrollPane scroller;
	private String potrero_elegido;
	private JButton btnRegresar;
	private JButton btnAgregarPurgante;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JButton btnAgregarVacuna;
	private JMenuBar menuBar;
	private JLabel lblCantVacas;

	public PotrerosPanel(InicioPanel inicio, String potreroelegido) {

		this.inicio = inicio;
		potrero_elegido = potreroelegido;

		setLayout(new BorderLayout(0, 0));

		setComponents();
		listeners();

	}

	public void setComponents() {

		menuBar = new JMenuBar();

		menuBar();

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new GridLayout(2, 1));
		panelTop.add(menuBar);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new GridLayout(1, 5));

		JPanel panel_2 = new JPanel();
		panelSuperior.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 10));

		panelTop.add(panelSuperior);

		add(panelTop, BorderLayout.NORTH);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(btnRegresar);

		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);

		lblNombrePotrero = new JLabel(potrero_elegido);
		lblNombrePotrero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombrePotrero.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblNombrePotrero);

		panelResTable = new JPanel();
		scroller = new JScrollPane();
		ganado = PotreroCRUD.selectRes(potrero_elegido);

		modelRes = new ModelTable();

		crearTablaRes();
		add(panelResTable, BorderLayout.CENTER);
		panelResTable.setLayout(new GridLayout(1, 1));

		popUpMenu();
		
		lblCantVacas = new JLabel("Cantidad Reses: " + modelRes.getData().length);
		lblCantVacas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantVacas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSuperior.add(lblCantVacas);

		JLabel lblNewLabel_1 = new JLabel("Filtrar por:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSuperior.add(lblNewLabel_1);

		comboHembraMacho = new JComboBox();
		comboHembraMacho.setModel(new DefaultComboBoxModel(new String[] { "VER TODO", "HEMBRAS", "MACHOS" }));
		panelSuperior.add(comboHembraMacho);

		JLabel lblNewLabel_3 = new JLabel("");
		panelSuperior.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("");
		panelSuperior.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		panelSuperior.add(lblNewLabel_6);

		btnNotificaciones = new JButton("Notificaciones");
		btnNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSuperior.add(btnNotificaciones);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new GridLayout(1, 6));

		lblNewLabel = new JLabel("");
		panelInferior.add(lblNewLabel);

		btnAgregarPurgante = new JButton("Agregar Purgante", new ImageIcon(FileManager.imagenes.get("PURGANTICO")));
		btnAgregarPurgante.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregarPurgante.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregarPurgante.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnAgregarPurgante);

		btnAgregar = new JButton("Agregar Res", new ImageIcon(FileManager.imagenes.get("RES")));
		btnAgregar.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregar.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnAgregar);

		btnAgregarVacuna = new JButton("Agregar Vacuna", new ImageIcon(FileManager.imagenes.get("VACUNITA")));
		btnAgregarVacuna.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregarVacuna.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregarVacuna.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInferior.add(btnAgregarVacuna);

		lblNewLabel_2 = new JLabel("");
		panelInferior.add(lblNewLabel_2);

		

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
		lblCantVacas.setText("Cantidad Reses: "+ modelRes.getData().length);

	}

	public void listeners() {

		// Abre el panel para agregar una vaca
		btnAgregar.addActionListener(e -> {

			AgregarEditarResDialog agregarEditarDialog = new AgregarEditarResDialog(null, this);
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

		btnAgregarVacuna.addActionListener(e -> {
			agregarVacuna();
		});

		btnAgregarPurgante.addActionListener(e -> {
			agregarPurgante();
		});

		// Filtro
		comboHembraMacho.addActionListener(e -> {
			filtrar();
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

					AgregarEditarResDialog agregarEditarDialog = new AgregarEditarResDialog(res, PotrerosPanel.this);

				}

				// Click derecho
				if (e.getButton() == MouseEvent.BUTTON3) {

					menu.show(e.getComponent(), e.getX(), e.getY());
				}

			}
		});

		tablaRes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					eliminar();
				}
				
			}
		});
		
	}

	public void filtrar() {

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

	}

	public void agregarPurgante() {

		String purgante = JOptionPane.showInputDialog(null, "Ingrese el nombre del purgante", "Agregar Purgante",
				JOptionPane.INFORMATION_MESSAGE);

		if (purgante != null && !purgante.equals("")) {
			if (PurganteCRUD.selectPurganteByNombre(purgante) == null) {
				PurganteCRUD.insert(purgante);
			} else {
				JOptionPane.showMessageDialog(null, "El purgante " + purgante + " ya se encuentra agregado",
						"Purgante ya agregado", JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "Debe ingresar un nombre al purgante", "No agregado",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void agregarVacuna() {

		String vacuna = JOptionPane.showInputDialog(null, "Ingrese el nombre de la vacuna", "Agregar Vacuna",
				JOptionPane.INFORMATION_MESSAGE);

		if (vacuna != null && !vacuna.equals("")) {
			if (VacunaCRUD.selectVacunaByNombre(vacuna) == null) {
				VacunaCRUD.insert(vacuna);
			} else {
				JOptionPane.showMessageDialog(null, "La vacuna " + vacuna + " ya se encuentra agregada",
						"Vacuna ya agregada", JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "Debe ingresar un nombre a la vacuna", "No agregada",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public void menuBar() {

		JMenu archivo = new JMenu("Archivo");
		JMenuItem importar = new JMenuItem("Importar", new ImageIcon(FileManager.imagenes.get("IMPORTAR")));
		importar.addActionListener(e -> {
			importar();
		});
		JMenuItem exportar = new JMenuItem("Exportar", new ImageIcon(FileManager.imagenes.get("EXPORTAR")));
		exportar.addActionListener(e -> {

		});
		JMenuItem copiaSeguridad = new JMenuItem("Copia de seguridad", new ImageIcon(FileManager.imagenes.get("BACKUP")));
		copiaSeguridad.addActionListener(e -> {

		});
		JMenuItem plantilla = new JMenuItem("Guardar Plantilla Excel", new ImageIcon(FileManager.imagenes.get("EXCEL")));
		plantilla.addActionListener(e -> {

		});
		JMenuItem cerrar = new JMenuItem("Cerrar");
		cerrar.addActionListener(e -> {
			inicio.getVentana().setVisible(false);
			System.exit(0);
		});
		JMenu nuevo = new JMenu("Agregar");
		JMenuItem newRes = new JMenuItem("Res", new ImageIcon(FileManager.imagenes.get("RES")));
		newRes.addActionListener(e -> {
			AgregarEditarResDialog agregarRes = new AgregarEditarResDialog(null, this);
		});
		JMenuItem newVacuna = new JMenuItem("Vacuna", new ImageIcon(FileManager.imagenes.get("VACUNITA")));
		newVacuna.addActionListener(e -> {
			agregarVacuna();
		});
		JMenuItem newPurgante = new JMenuItem("Purgante", new ImageIcon(FileManager.imagenes.get("PURGANTICO")));
		newPurgante.addActionListener(e -> {
			agregarPurgante();
		});
		nuevo.add(newRes);
		nuevo.add(newVacuna);
		nuevo.add(newPurgante);
		archivo.add(nuevo);
		archivo.addSeparator();
		archivo.add(plantilla);
		archivo.add(importar);
		archivo.add(exportar);
		archivo.add(copiaSeguridad);
		archivo.addSeparator();
		archivo.add(cerrar);

		JMenu acciones = new JMenu("Acciones");
		JMenuItem trasladar = new JMenuItem("Trasladar", new ImageIcon(FileManager.imagenes.get("TRASLADADA")));
		trasladar.addActionListener(e -> {
			trasladar();
		});
		JMenuItem vacunar = new JMenuItem("Vacunar", new ImageIcon(FileManager.imagenes.get("VACUNITA")));
		vacunar.addActionListener(e -> {
			vacunar();
		});
		JMenuItem purgar = new JMenuItem("Purgar", new ImageIcon(FileManager.imagenes.get("PURGANTICO")));
		purgar.addActionListener(e -> {
			purgar();
		});
		acciones.add(trasladar);
		acciones.add(vacunar);
		acciones.add(purgar);

		JMenu edicion = new JMenu("Edicion");
		JMenuItem limpiar = new JMenuItem("Limpiar Seleccion");
		limpiar.addActionListener(e -> {

			tablaRes.clearSelection();

		});
		JMenuItem seleccionarTodo = new JMenuItem("Seleccionar Todo");
		seleccionarTodo.addActionListener(e -> {

			tablaRes.selectAll();

		});
		JMenuItem eliminar = new JMenuItem("Eliminar", new ImageIcon(FileManager.imagenes.get("ELIMINAR")));
		eliminar.addActionListener(e -> {

			eliminar();

		});
		edicion.add(limpiar);
		edicion.add(seleccionarTodo);
		edicion.add(eliminar);

		JMenu ver = new JMenu("Ver");
		JMenuItem estadisticas = new JMenuItem("Estadisticas", new ImageIcon(FileManager.imagenes.get("STATS")));
		estadisticas.addActionListener(e -> {

		});
		JMenuItem creditos = new JMenuItem("Creditos", new ImageIcon(FileManager.imagenes.get("CREDITOS")));
		creditos.addActionListener(e -> {

		});
		ver.add(estadisticas);
		ver.add(creditos);

		menuBar.add(archivo);
		menuBar.add(edicion);
		menuBar.add(acciones);
		menuBar.add(ver);
	}

	public void popUpMenu() {

		menu = new JPopupMenu();

		JMenuItem vacunar = new JMenuItem("Vacunar");
		vacunar.addActionListener(a -> {
			vacunar();
		});

		JMenuItem purgar = new JMenuItem("Purgar");
		purgar.addActionListener(a -> {
			purgar();
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

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
			// Por si se encuentra "sorted or filtered" la tabla
			if (tablaRes.getRowSorter() != null) {

				for (int i = 0; i < rowsSelected.length; i++) {

					rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

				}

			}

			ArrayList<Vacuna> vacunas = VacunaCRUD.select();
			String[] vacunitas = new String[vacunas.size()];

			for (int i = 0; i < vacunitas.length; i++) {
				vacunitas[i] = vacunas.get(i).getNombre();
			}

			Icon icon = new ImageIcon(FileManager.imagenes.get("VACUNA"));

			String resp = (String) JOptionPane.showInputDialog(null,
					"Seleccione la vacuna que desea aplicar a las reses", "Vacunar ganado", JOptionPane.DEFAULT_OPTION,
					icon, vacunitas, null);

			if (resp != null) {
				CalendarioDialog cal = new CalendarioDialog(null);
				String fecha = cal.getFechaSeleccionada();

				if (fecha != null && !fecha.equals("")) {

					int option = JOptionPane.showConfirmDialog(this,
							"\u00BFESTA SEGURO QUE DESEA VACUNAR " + rowsSelected.length + " RESES CON LA VACUNA "
									+ resp + " CON FECHA " + fecha + "" + "?",
							"ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						new Thread() {
							BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

							int value = 0;

							@Override
							public void run() {

								for (int i = 0; i < rowsSelected.length; i++) {

									String id = modelRes.getValueAt(rowsSelected[i], 0).toString();

									ResCRUD.insertVacuna(id, resp, fecha);
									value++;
									progreso.getProgreso().setValue(value);

								}

								progreso.dispose();
								JOptionPane.showMessageDialog(null, "Se ha realizado con exito la vacunacion",
										"Vacunacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
							}
						}.start();

					}
				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la accion.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public void purgar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
			// Por si se encuentra "sorted or filtered" la tabla
			if (tablaRes.getRowSorter() != null) {

				for (int i = 0; i < rowsSelected.length; i++) {

					rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

				}

			}

			ArrayList<Purgante> purgantes = PurganteCRUD.select();
			String[] purganticos = new String[purgantes.size()];

			for (int i = 0; i < purganticos.length; i++) {
				purganticos[i] = purgantes.get(i).getNombre();
			}

			Icon icon = new ImageIcon(FileManager.imagenes.get("PURGANTE"));

			String resp = (String) JOptionPane.showInputDialog(null,
					"Seleccione el purgante que desea aplicar a las reses", "Purgar ganado", JOptionPane.DEFAULT_OPTION,
					icon, purganticos, null);

			if (resp != null) {
				CalendarioDialog cal = new CalendarioDialog(null);
				String fecha = cal.getFechaSeleccionada();

				if (fecha != null && !fecha.equals("")) {

					int option = JOptionPane.showConfirmDialog(this,
							"\u00BFESTA SEGURO QUE DESEA PURGAR " + rowsSelected.length + " RESES CON EL PURGANTE "
									+ resp + " CON FECHA " + fecha + "" + "?",
							"ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						new Thread() {
							BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

							int value = 0;

							@Override
							public void run() {

								for (int i = 0; i < rowsSelected.length; i++) {

									String id = modelRes.getValueAt(rowsSelected[i], 0).toString();

									ResCRUD.insertPurgante(id, resp, fecha);
									value++;
									progreso.getProgreso().setValue(value);

								}

								progreso.dispose();
								JOptionPane.showMessageDialog(null, "Se ha realizado con exito el purgado",
										"Purgado Exitoso", JOptionPane.INFORMATION_MESSAGE);
							}
						}.start();

					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la accion.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void trasladar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
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

			Icon icon = new ImageIcon(FileManager.imagenes.get("TRASLADAR"));

			String resp = (String) JOptionPane.showInputDialog(null,
					"Seleccione el potrero al que desea trasladar las reses", "Trasladar de potrero",
					JOptionPane.DEFAULT_OPTION, icon, potreritos, null);

			if (resp != null) {
				int option = JOptionPane
						.showConfirmDialog(this,
								"\u00BFEsta seguro que desea trasladar " + rowsSelected.length + " reses al potrero "
										+ resp + "?",
								"Trasladar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (option == 0) {

					new Thread() {
						BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

						int value = 0;

						@Override
						public void run() {

							ArrayList<String> ids = new ArrayList<String>();

							for (int i = 0; i < rowsSelected.length; i++) {

								String id = modelRes.getValueAt(rowsSelected[i], 0).toString();
								ids.add(id);
								value++;
								progreso.getProgreso().setValue(value);

							}

							ResCRUD.trasladar(ids, potrero_elegido, resp);
							refreshTable();
							progreso.dispose();

						}
					}.start();

				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la accion.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void eliminar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
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

						ArrayList<String> ids = new ArrayList<String>();

						for (int i = 0; i < rowsSelected.length; i++) {

							String id = modelRes.getValueAt(rowsSelected[i], 0).toString();
							ids.add(id);
							value++;
							progreso.getProgreso().setValue(value);

						}

						ResCRUD.delete(ids);
						refreshTable();
						progreso.dispose();

					}

				}.start();

			}
		} else {

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la accion.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void importar() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(inicio.getVentana());

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

				DocsImporterExporter.importData(fs, potrero_elegido, this);
				refreshTable();
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

	public JButton getBtnNotificaciones() {
		return btnNotificaciones;
	}

	public JLabel getLblNombrePotrero() {
		return lblNombrePotrero;
	}

	public JPanel getPanelResTable() {
		return panelResTable;
	}

	public JButton getBtnAgregarPurgante() {
		return btnAgregarPurgante;
	}

	public JButton getBtnAgregarVacuna() {
		return btnAgregarVacuna;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public JLabel getLblCantVacas() {
		return lblCantVacas;
	}
}
