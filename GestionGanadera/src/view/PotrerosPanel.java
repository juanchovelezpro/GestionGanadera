package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db.PotreroCRUD;
import db.PurganteCRUD;
import db.ResCRUD;
import db.SQLConnection;
import db.VacunaCRUD;
import db.VitaminaCRUD;
import model.Potrero;
import model.Purgante;
import model.Res;
import model.Vacuna;
import model.Vitamina;
import tools.DocsImporterExporter;
import tools.FileManager;
import javax.swing.JToggleButton;

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
	private JButton btnAgregarVitamina;
	private JToggleButton btnToggleResesRetiradas;

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
		panelTop.setBackground(new Color(0, 0, 0, 0));
		panelTop.add(menuBar);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(0, 0, 0, 0));
		panelSuperior.setLayout(new GridLayout(1, 5));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0, 0));
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
		lblNombrePotrero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombrePotrero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePotrero.setToolTipText(potrero_elegido);
		panelSuperior.add(lblNombrePotrero);

		panelResTable = new JPanel();
		panelResTable.setBackground(new Color(0, 0, 0, 0));
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
		
		btnToggleResesRetiradas = new JToggleButton("Reses Retiradas");
		btnToggleResesRetiradas.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSuperior.add(btnToggleResesRetiradas);

		btnNotificaciones = new JButton("Notificaciones");
		btnNotificaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelSuperior.add(btnNotificaciones);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setBackground(new Color(0, 0, 0, 0));
		panelInferior.setLayout(new GridLayout(1, 6));

		lblNewLabel = new JLabel("");
		panelInferior.add(lblNewLabel);

		btnAgregar = new JButton("Agregar Res", new ImageIcon(FileManager.imagenes.get("RES2")));
		btnAgregar.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregar.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelInferior.add(btnAgregar);

		btnAgregarPurgante = new JButton("Agregar Purgante", new ImageIcon(FileManager.imagenes.get("PURGANTICO2")));
		btnAgregarPurgante.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregarPurgante.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregarPurgante.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelInferior.add(btnAgregarPurgante);

		btnAgregarVacuna = new JButton("Agregar Vacuna", new ImageIcon(FileManager.imagenes.get("VACUNITA2")));
		btnAgregarVacuna.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregarVacuna.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregarVacuna.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelInferior.add(btnAgregarVacuna);

		btnAgregarVitamina = new JButton("Agregar Vitamina", new ImageIcon(FileManager.imagenes.get("VITAMINITA2")));
		btnAgregarVitamina.setVerticalTextPosition(AbstractButton.CENTER);
		btnAgregarVitamina.setHorizontalTextPosition(AbstractButton.RIGHT);
		btnAgregarVitamina.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelInferior.add(btnAgregarVitamina);

		lblNewLabel_2 = new JLabel("");
		panelInferior.add(lblNewLabel_2);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("POTREROS"), 0, 90, null);

		repaint();

	}

	public void crearTablaRes() {

		fillData();

		tablaRes = new JTable(modelRes);
		tablaRes.setBackground(new Color(0, 0, 0, 0));
		tablaRes.setOpaque(false);
		((DefaultTableCellRenderer) tablaRes.getDefaultRenderer(Object.class)).setOpaque(false);
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
		tablaRes.getTableHeader().setOpaque(false);
		scroller.setViewportView(tablaRes);
		scroller.setOpaque(false);
		scroller.setBackground(new Color(0, 0, 0, 0));
		scroller.getViewport().setOpaque(false);
		tablaRes.setFillsViewportHeight(true);
		panelResTable.add(scroller);

	}

	public void fillData() {

		String[] columns = { "ID", "TIPO", "G\u00C9NERO", "COLOR", "FECHA NACIMIENTO", "ESTADO", "MADRE",
				"OBSERVACIONES" };
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
		lblCantVacas.setText("Cantidad Reses: " + modelRes.getData().length);

	}

	public void listeners() {

		// Abre el panel para agregar una vaca
		btnAgregar.addActionListener(e -> {

			AgregarEditarResDialog agregarEditarDialog = new AgregarEditarResDialog(null, this);
		});

		// Regresar al panel inicio
		btnRegresar.addActionListener(e -> {
			irAInicio();
		});
		
		
		btnToggleResesRetiradas.addActionListener(e ->{
			
			if(btnToggleResesRetiradas.isSelected()) {
				
				sorter = new TableRowSorter<>(modelRes); 
				sorter.setRowFilter(RowFilter.regexFilter("MUERTO", 5));
				tablaRes.setRowSorter(sorter);
				comboHembraMacho.setEnabled(false);
				
			}else {
				

				sorter = new TableRowSorter<>(modelRes); 
				sorter.setRowFilter(RowFilter.regexFilter("VIVO", 5));
				tablaRes.setRowSorter(sorter);
				comboHembraMacho.setEnabled(true);
				
			}
			
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
		
		btnAgregarVitamina.addActionListener(e ->{
			agregarVitamina();
		});

		comboHembraMacho.addActionListener(e -> {
			filtrar();
		});

		tablaRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {

					int row = tablaRes.getSelectedRow();

					if (tablaRes.getRowSorter() != null)
						row = tablaRes.getRowSorter().convertRowIndexToModel(row);

					Res res = ResCRUD.selectResByID(modelRes.getValueAt(row, 0).toString());

					AgregarEditarResDialog agregarEditarDialog = new AgregarEditarResDialog(res, PotrerosPanel.this);

				}

				if (e.getButton() == MouseEvent.BUTTON3) {

					menu.show(e.getComponent(), e.getX(), e.getY());
				}

			}
		});

		tablaRes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					eliminar();
				}

			}
		});

	}

	public void filtrar() {

		switch (comboHembraMacho.getSelectedIndex()) {

		case 0:
			sorter = new TableRowSorter<>(modelRes); 
			sorter.setRowFilter(RowFilter.regexFilter("VIVO", 5));
			tablaRes.setRowSorter(sorter);
			break;

		case 1:
			sorter = new TableRowSorter<>(modelRes);
			sorter.setRowFilter(RowFilter.regexFilter("H", 2));
			tablaRes.setRowSorter(sorter);
			break;

		case 2:
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

	public void agregarVitamina() {

		String vitamina = JOptionPane.showInputDialog(null, "Ingrese el nombre de la vitamina", "Agregar Vitamina",
				JOptionPane.INFORMATION_MESSAGE);

		if (vitamina != null && !vitamina.equals("")) {
			if (VitaminaCRUD.selectVitaminaByNombre(vitamina) == null) {
				VitaminaCRUD.insert(vitamina);
			} else {
				JOptionPane.showMessageDialog(null, "La vitamina " + vitamina + " ya se encuentra agregada",
						"Vitamina ya agregada", JOptionPane.ERROR_MESSAGE);
			}
		} else {

			JOptionPane.showMessageDialog(null, "Debe ingresar un nombre a la vitamina", "No agregada",
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
			exportar();
		});
		JMenuItem copiaSeguridad = new JMenuItem("Copia de seguridad",
				new ImageIcon(FileManager.imagenes.get("BACKUP")));
		copiaSeguridad.addActionListener(e -> {
			backUp();
		});
		JMenuItem plantilla = new JMenuItem("Guardar Plantilla Excel",
				new ImageIcon(FileManager.imagenes.get("EXCEL")));
		plantilla.addActionListener(e -> {
			guardarPlantilla();
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
		JMenuItem newVitamina = new JMenuItem("Vitamina", new ImageIcon(FileManager.imagenes.get("VITAMINITA")));
		newVitamina.addActionListener(e -> {
			agregarVitamina();
		});
		nuevo.add(newRes);
		nuevo.add(newVacuna);
		nuevo.add(newPurgante);
		nuevo.add(newVitamina);
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
		JMenuItem vitaminizar = new JMenuItem("Vitaminizar", new ImageIcon(FileManager.imagenes.get("VITAMINITA")));
		vitaminizar.addActionListener(e -> {
			vitaminizar();
		});
		acciones.add(trasladar);
		acciones.add(vacunar);
		acciones.add(purgar);
		acciones.add(vitaminizar);

		JMenu edicion = new JMenu("Edici\u00F3n");
		JMenuItem limpiar = new JMenuItem("Limpiar Selecci\u00F3n");
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

		JMenuItem actualizar = new JMenuItem("Actualizar", null);
		actualizar.addActionListener(e -> {

			ResCRUD.actualizarTipo();
			refreshTable();
		});
		edicion.add(limpiar);
		edicion.add(seleccionarTodo);
		edicion.add(eliminar);
		edicion.add(actualizar);

		JMenu ver = new JMenu("Ver");
		JMenuItem estadisticas = new JMenuItem("Estad\u00EDsticas", new ImageIcon(FileManager.imagenes.get("STATS")));
		estadisticas.addActionListener(e -> {

			EstadisticaDialog estadisticass = new EstadisticaDialog(2, potrero_elegido);

		});
		JMenuItem creditos = new JMenuItem("Cr\u00E9ditos", new ImageIcon(FileManager.imagenes.get("CREDITOS")));
		creditos.addActionListener(e -> {

			CreditosDialog cre = new CreditosDialog();
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

		JMenuItem vitaminizar = new JMenuItem("Vitaminizar");
		vitaminizar.addActionListener(a -> {
			vitaminizar();
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
		menu.add(vitaminizar);
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
							"\u00BFEST\u00C1 SEGURO QUE DESEA VACUNAR " + rowsSelected.length + " RESES CON LA VACUNA "
									+ resp + " CON FECHA " + fecha + "" + "?",
							"ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						new Thread() {
							BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

							int value = 0;

							@Override
							public void run() {

								ArrayList<String> ids = new ArrayList<>();

								for (int i = 0; i < rowsSelected.length; i++) {

									String id = modelRes.getValueAt(rowsSelected[i], 0).toString();
									ids.add(id);
									value++;
									progreso.getProgreso().setValue(value);

								}

								ResCRUD.insertVacunaMultiple(ids, resp, fecha);
								progreso.dispose();
								JOptionPane.showMessageDialog(null, "Se ha realizado con \u00E9xito la vacunaci\u00F3n",
										"Vacunaci\u00F3n Exitosa", JOptionPane.INFORMATION_MESSAGE);
							}
						}.start();

					}
				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la acci\u00F3n.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public void purgar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
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
							"\u00BFEST\u00C1 SEGURO QUE DESEA PURGAR " + rowsSelected.length + " RESES CON EL PURGANTE "
									+ resp + " CON FECHA " + fecha + "" + "?",
							"ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						new Thread() {
							BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

							int value = 0;

							@Override
							public void run() {

								ArrayList<String> ids = new ArrayList<>();

								for (int i = 0; i < rowsSelected.length; i++) {

									String id = modelRes.getValueAt(rowsSelected[i], 0).toString();

									ids.add(id);
									value++;
									progreso.getProgreso().setValue(value);

								}

								ResCRUD.insertPurganteMultiple(ids, resp, fecha);
								progreso.dispose();
								JOptionPane.showMessageDialog(null, "Se ha realizado con \u00E9xito el purgado",
										"Purgado Exitoso", JOptionPane.INFORMATION_MESSAGE);
							}
						}.start();

					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la acci\u00F3n.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void vitaminizar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
			if (tablaRes.getRowSorter() != null) {

				for (int i = 0; i < rowsSelected.length; i++) {

					rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

				}

			}

			ArrayList<Vitamina> vitaminas = VitaminaCRUD.select();
			String[] vitaminitas = new String[vitaminas.size()];

			for (int i = 0; i < vitaminitas.length; i++) {
				vitaminitas[i] = vitaminas.get(i).getNombre();
			}

			Icon icon = new ImageIcon(FileManager.imagenes.get("VITAMINA"));

			String resp = (String) JOptionPane.showInputDialog(null,
					"Seleccione la vitamina que desea aplicar a las reses", "Vitaminizar ganado",
					JOptionPane.DEFAULT_OPTION, icon, vitaminitas, null);

			if (resp != null) {
				CalendarioDialog cal = new CalendarioDialog(null);
				String fecha = cal.getFechaSeleccionada();

				if (fecha != null && !fecha.equals("")) {

					int option = JOptionPane.showConfirmDialog(this,
							"\u00BFEST\u00C1 SEGURO QUE DESEA VITAMINIZAR " + rowsSelected.length
									+ " RESES CON LA VITAMINA " + resp + " CON FECHA " + fecha + "" + "?",
							"ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if (option == 0) {

						new Thread() {
							BarraProgresoDialog progreso = new BarraProgresoDialog(rowsSelected.length);

							int value = 0;

							@Override
							public void run() {

								ArrayList<String> ids = new ArrayList<>();

								for (int i = 0; i < rowsSelected.length; i++) {

									String id = modelRes.getValueAt(rowsSelected[i], 0).toString();
									ids.add(id);
									value++;
									progreso.getProgreso().setValue(value);

								}

								ResCRUD.insertVitaminaMultiple(ids, resp, fecha);
								progreso.dispose();
								JOptionPane.showMessageDialog(null, "Se ha aplicado con \u00E9xito la vitamina",
										"Vitamina Aplicada", JOptionPane.INFORMATION_MESSAGE);
							}
						}.start();

					}
				}
			}
		} else {

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la acci\u00F3n.",
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
								"\u00BFEst\u00E1 seguro que desea trasladar " + rowsSelected.length
										+ " reses al potrero " + resp + "?",
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

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la acci\u00F3n.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void eliminar() {

		int[] rowsSelected = tablaRes.getSelectedRows();

		if (rowsSelected.length > 0) {
			if (tablaRes.getRowSorter() != null) {

				for (int i = 0; i < rowsSelected.length; i++) {

					rowsSelected[i] = tablaRes.getRowSorter().convertRowIndexToModel(rowsSelected[i]);

				}

			}

			int option = JOptionPane.showConfirmDialog(this,
					"\u00BFEst\u00E1 seguro que desea eliminar " + rowsSelected.length + " reses?", "Eliminar",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

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

			JOptionPane.showMessageDialog(null, "Selecciona al menos una res para la acci\u00F3n.",
					"No hay reses seleccionadas", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void importar() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Database DB", "db"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Libro de Excel 97-2003", "xls"));
		FileFilter filter = new FileNameExtensionFilter("Libro de Excel", "xlsx");
		fileChooser.setFileFilter(filter);
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

				String ext = fileChooser.getSelectedFile().getName().split("\\.")[1];

				if (ext.equals("xlsx")) {
					DocsImporterExporter.importData(fs, potrero_elegido, this);
					refreshTable();
					fs.close();
				} else if (ext.equals("db")) {

					SQLConnection.getInstance().getConnection().close();
					FileManager.saveFile(fileChooser.getSelectedFile(), FileManager.PATH + "database.db");
					Connection connection = DriverManager
							.getConnection("jdbc:sqlite:" + FileManager.PATH + "database.db");
					SQLConnection.getInstance().setConnection(connection);
					SQLConnection.getInstance().setStatement(connection.createStatement());

					irAInicio();

					fs.close();

				}

			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	public void guardarPlantilla() {

		try {

			JFileChooser fileSaver = new JFileChooser();
			int op = fileSaver.showSaveDialog(inicio.getVentana());

			if (op == JFileChooser.APPROVE_OPTION) {

				InputStream file = getClass().getResourceAsStream("/archivos/Plantilla.xlsx");

				XSSFWorkbook wb = new XSSFWorkbook(file);
				FileOutputStream out = new FileOutputStream(fileSaver.getSelectedFile().getPath() + ".xlsx");
				wb.write(out);
				out.close();
				JOptionPane.showMessageDialog(null, "Guardado en " + fileSaver.getSelectedFile().getPath(), "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void backUp() {

		try {

			JFileChooser fileSaver = new JFileChooser();
			int op = fileSaver.showSaveDialog(inicio.getVentana());

			if (op == JFileChooser.APPROVE_OPTION) {

				File database = new File(FileManager.PATH + "database.db");

				FileManager.saveFile(database, fileSaver.getSelectedFile().getPath() + ".db");

				JOptionPane.showMessageDialog(null, "Guardado en " + fileSaver.getSelectedFile().getPath(), "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void exportar() {

		try {

			JFileChooser fileSaver = new JFileChooser();
			int op = fileSaver.showSaveDialog(inicio.getVentana());

			if (op == JFileChooser.APPROVE_OPTION) {

				DocsImporterExporter.exportPotrero(potrero_elegido, fileSaver.getSelectedFile().getPath() + ".xlsx");

				JOptionPane.showMessageDialog(null, "Guardado en " + fileSaver.getSelectedFile().getPath(), "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void irAInicio() {

		int reses = ResCRUD.select().size();

		inicio.actualizarreses(reses);

		inicio.getVentana().remove(this);
		inicio.getVentana().setSize(800, 400);
		inicio.getVentana().setResizable(false);
		inicio.getVentana().setLocationRelativeTo(null);
		inicio.getVentana().getContentPane().add(inicio);
		inicio.getVentana().refresh();

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

	public JButton getBtnAgregarVitamina() {
		return btnAgregarVitamina;
	}

	public JToggleButton getBtnToggleResesRetiradas() {
		return btnToggleResesRetiradas;
	}
}
