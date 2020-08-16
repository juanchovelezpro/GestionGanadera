package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.sqlite.SQLiteException;

import db.ResCRUD;
import model.Peso;
import model.Purgante;
import model.Res;
import model.Vacuna;
import tools.FileManager;

public class AgregarEditarVaca extends JDialog {

	private Res res;
	private PotrerosPanel potrero;
	private JTextField txtColor;
	private JComboBox comboEmbarazada;
	private JComboBox comboGenero;
	private JButton btnGuardarCerrar;
	private JComboBox comboTipo;
	private JTextField txtNumero;
	private JTextArea txtObservaciones;
	private JTextField txtMadre;
	private JButton btnCrias;
	private JLabel lblTipo;
	private JButton btnFechaNacimiento;
	private JButton btnFechaEmbarazo;
	private JButton btnVerRegistroPeso;
	private JButton btnAgregar;
	private JTable tablaPesos;
	private ModelTable modelPesos;
	private JTable tablaVacunas;
	private ModelTable modelVacunas;
	private JTable tablaCrias;
	private ModelTable modelCrias;
	private JTable tablaPurgantes;
	private ModelTable modelPurgantes;
	private JScrollPane scroller;
	// 1= peso , 2= vacuna, 3= purgante 4=crias
	private int tiporeporte;
	private JButton btnRegistroVacunas;
	private JButton btnRegistroPurgantes;
	private JPanel panelTablaGraficas;
	private JPanel panelGrafica;
	private JPanel panelTabla;

	public AgregarEditarVaca(Res res, PotrerosPanel potrero) {

		this.res = res;
		this.potrero = potrero;

		if (res != null)
			setTitle("Editar Vaca/ ID: " + res.getResID());
		else
			setTitle("Agregar vaca");

		setIconImage(FileManager.imagenes.get("ICONO"));
		getContentPane().setLayout(new GridLayout(1, 2));
		setSize(500, 700);
		setLocationRelativeTo(null);
		setComponents();
		listeners();

		cargarInfoRes();

		if (res == null) {

			btnVerRegistroPeso.setEnabled(false);
			btnCrias.setEnabled(false);
			btnRegistroPurgantes.setEnabled(false);
			btnRegistroVacunas.setEnabled(false);

		}

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
		comboTipo.setModel(new DefaultComboBoxModel(
				new String[] { "Seleccione el tipo", "VP", "VH", "CH", "HL", "NV", "CM", "ML", "MC", "TP" }));
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

		btnFechaEmbarazo = new JButton("dd/mm/AAAA");
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

		btnVerRegistroPeso = new JButton("Ver Registro Peso");
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

		btnRegistroVacunas = new JButton("Ver Registro Vacunas");
		btnRegistroVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistroVacunas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(btnRegistroVacunas);

		JLabel lblRegistroPurgantes = new JLabel("Registro Purgantes");
		lblRegistroPurgantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroPurgantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_15.add(lblRegistroPurgantes);

		btnRegistroPurgantes = new JButton("Ver Registro Purgantes");
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

		btnAgregar = new JButton("Agregar");

		scroller = new JScrollPane();

		panelTablaGraficas = new JPanel();
		panelTablaGraficas.setLayout(new GridLayout(2, 1));

		panelGrafica = new JPanel();

		panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout());
		panelTabla.add(scroller, BorderLayout.CENTER);

		panelTabla.add(btnAgregar, BorderLayout.SOUTH);

		panelTablaGraficas.add(panelGrafica);
		panelTablaGraficas.add(panelTabla);

	}

	public void cargarInfoRes() {

		if (res != null) {

			txtNumero.setText(res.getResID());
			String genero = res.getGenero().equals("H") ? "HEMBRA" : "MACHO";
			comboGenero.setSelectedItem(genero);
			comboTipo.setSelectedItem(res.getTipo());
			txtColor.setText(res.getColor());

			if (res.getFecha_nacimiento() != null && !res.getFecha_nacimiento().equals(""))
				btnFechaNacimiento.setText(res.getFecha_nacimiento());
			else
				btnFechaNacimiento.setText("SIN REGISTRO");

			if (res.getGenero().equals("H")) {

				comboEmbarazada.setEnabled(true);
				btnFechaEmbarazo.setEnabled(true);

				String emb = res.getEmbarazada() == 0 ? "NO" : "SI";

				comboEmbarazada.setSelectedItem(emb);

				if (emb.equals("SI"))
					btnFechaEmbarazo.setText(res.getFecha_embarazo());

			} else {

				comboEmbarazada.setEnabled(false);
				btnFechaEmbarazo.setEnabled(false);

				comboEmbarazada.setSelectedItem("NO APLICA");

			}

			txtMadre.setText(res.getMadreID());
			txtObservaciones.setText(res.getObservaciones());

		}

	}

	public Res obtenerInfoRes() {

		Res res = new Res();

		res.setPotreroNombre(potrero.getPotrero_elegido());

		res.setResID(txtNumero.getText());
		res.setTipo(comboTipo.getSelectedItem().toString());

		String genero = comboGenero.getSelectedItem().toString();

		if (genero.equals("HEMBRA")) {

			res.setGenero("H");
			int embarazo = comboEmbarazada.getSelectedItem().toString().equals("SI") ? 1 : 0;
			res.setEmbarazada(embarazo);

		} else {

			res.setGenero("M");
			res.setEmbarazada(-1);

		}

		res.setColor(txtColor.getText());

		String fechaNac = btnFechaNacimiento.getText();

		if (fechaNac != null && !fechaNac.equals("") && !fechaNac.equals("dd/mm/AAAA")) {
			res.setFecha_nacimiento(fechaNac);

		}

		res.setMadreID(txtMadre.getText());

		System.out.println(txtObservaciones.getText());
		res.setObservaciones(txtObservaciones.getText());

		return res;

	}

	public void transformarPanel() {

		getContentPane().add(panelTablaGraficas);
		setSize(925, 700);
		setLocationRelativeTo(null);

	}

	public void crearTablaPesos() {

		String[] columns = { "PESO", "FECHA" };

		if (res != null) {

			Stack<Peso> pesos = ResCRUD.selectPesos(res.getResID());
			Object[][] data = new Object[pesos.size()][columns.length];
			Peso temp = null;

			for (int i = 0; i < data.length; i++) {

				temp = pesos.pop();

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = temp.getPeso();

					if (j == 1)
						data[i][j] = temp.getFecha();

				}

			}

			modelPesos = new ModelTable();
			modelPesos.setColumns(columns);
			modelPesos.setData(data);
			tablaPesos = new JTable(modelPesos);
			tablaPesos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPesos.setShowHorizontalLines(true);
			tablaPesos.setShowVerticalLines(true);
			tablaPesos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaPesos);
			tablaPesos.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelPesos = new ModelTable();
			modelPesos.setColumns(columns);
			modelPesos.setData(data);
			tablaPesos = new JTable(modelPesos);
			tablaPesos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPesos.setShowHorizontalLines(true);
			tablaPesos.setShowVerticalLines(true);
			tablaPesos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaPesos);
			tablaPesos.setFillsViewportHeight(true);

			transformarPanel();

		}

	}

	public void crearTablaVacunas() {

		String[] columns = { "VACUNA", "FECHA" };

		if (res != null) {

			ArrayList<Vacuna> vacunas = ResCRUD.selectVacunas(res.getResID());
			Object[][] data = new Object[vacunas.size()][columns.length];

			for (int i = 0; i < data.length; i++) {

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = vacunas.get(i).getNombre();

					if (j == 1)
						data[i][j] = vacunas.get(i).getFecha();

				}

			}

			modelVacunas = new ModelTable();
			modelVacunas.setColumns(columns);
			modelVacunas.setData(data);
			tablaVacunas = new JTable(modelVacunas);
			tablaVacunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaVacunas.setShowHorizontalLines(true);
			tablaVacunas.setShowVerticalLines(true);
			tablaVacunas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaVacunas);
			tablaVacunas.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelVacunas = new ModelTable();
			modelVacunas.setColumns(columns);
			modelVacunas.setData(data);
			tablaVacunas = new JTable(modelVacunas);
			tablaVacunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaVacunas.setShowHorizontalLines(true);
			tablaVacunas.setShowVerticalLines(true);
			tablaVacunas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaVacunas);
			tablaVacunas.setFillsViewportHeight(true);

			transformarPanel();

		}

	}

	public void crearTablaPurgantes() {

		String[] columns = { "PURGANTE", "FECHA" };

		if (res != null) {

			ArrayList<Purgante> purgantes = ResCRUD.selectPurgantes(res.getResID());
			Object[][] data = new Object[purgantes.size()][columns.length];

			for (int i = 0; i < data.length; i++) {

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = purgantes.get(i).getNombre();

					if (j == 1)
						data[i][j] = purgantes.get(i).getFecha();

				}

			}

			modelPurgantes = new ModelTable();
			modelPurgantes.setColumns(columns);
			modelPurgantes.setData(data);
			tablaPurgantes = new JTable(modelPurgantes);
			tablaPurgantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPurgantes.setShowHorizontalLines(true);
			tablaPurgantes.setShowVerticalLines(true);
			tablaPurgantes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaPurgantes);
			tablaPurgantes.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelPurgantes = new ModelTable();
			modelPurgantes.setColumns(columns);
			modelPurgantes.setData(data);
			tablaPurgantes = new JTable(modelPurgantes);
			tablaPurgantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPurgantes.setShowHorizontalLines(true);
			tablaPurgantes.setShowVerticalLines(true);
			tablaPurgantes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaPurgantes);
			tablaPurgantes.setFillsViewportHeight(true);

			transformarPanel();

		}
	}

	public void crearTablaCrias() {

		String[] columns = { "# Cria", "FECHA NACIMIENTO" };

		if (res != null) {

			ArrayList<Res> crias = ResCRUD.selectCria(res.getResID());
			Object[][] data = new Object[crias.size()][columns.length];

			for (int i = 0; i < data.length; i++) {

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = crias.get(i).getResID();

					if (j == 1)
						data[i][j] = crias.get(i).getFecha_nacimiento();

				}

			}

			modelCrias = new ModelTable();
			modelCrias.setColumns(columns);
			modelCrias.setData(data);
			tablaCrias = new JTable(modelCrias);
			tablaCrias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaCrias.setShowHorizontalLines(true);
			tablaCrias.setShowVerticalLines(true);
			tablaCrias.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaCrias);
			tablaCrias.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelCrias = new ModelTable();
			modelCrias.setColumns(columns);
			modelCrias.setData(data);
			tablaCrias = new JTable(modelCrias);
			tablaCrias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaCrias.setShowHorizontalLines(true);
			tablaCrias.setShowVerticalLines(true);
			tablaCrias.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));

			scroller.getViewport().add(tablaCrias);
			tablaCrias.setFillsViewportHeight(true);

			transformarPanel();

		}
	}

	public void refreshTable(int tipo) {

		switch (tipo) {

		case 1:
			scroller.getViewport().removeAll();
			crearTablaPesos();
			break;

		case 2:
			scroller.getViewport().removeAll();
			crearTablaVacunas();
			break;

		case 3:
			scroller.getViewport().removeAll();
			crearTablaPurgantes();
			break;

		}

	}

	public void listeners() {

		btnFechaNacimiento.addActionListener(e -> {

			CalendarioDialog calendar = new CalendarioDialog(btnFechaNacimiento);

		});

		btnFechaEmbarazo.addActionListener(e -> {

			CalendarioDialog calendar = new CalendarioDialog(btnFechaEmbarazo);

		});

		btnGuardarCerrar.addActionListener(e -> {

			// Guardar en la base de datos.

			if (res != null) {

				if (res.getResID().equals(txtNumero.getText().trim()) || !existeRes(txtNumero.getText())) {

					ResCRUD.update(res.getResID(), obtenerInfoRes());
					potrero.refreshTable();
					System.out.println("UPDATED");
					dispose();

				} else {

					JOptionPane.showMessageDialog(null, "Ya existe una res con ese numero", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} else {

				if (!existeRes(txtNumero.getText().trim())) {
					ResCRUD.insert(obtenerInfoRes());
					potrero.refreshTable();
					System.out.println("INSERTED");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Ya existe una res con ese numero", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		comboGenero.addActionListener(e ->

		{

			String selected = comboGenero.getSelectedItem().toString();

			if (selected.equals("MACHO")) {

				comboEmbarazada.setSelectedItem("NO APLICA");
				comboEmbarazada.setEnabled(false);
				btnFechaEmbarazo.setText("NO APLICA");
				btnFechaEmbarazo.setEnabled(false);

			} else {

				comboEmbarazada.setEnabled(true);
				btnFechaEmbarazo.setEnabled(true);

			}

		});

		btnVerRegistroPeso.addActionListener(e -> {

			tiporeporte = 1;
			// scroller.removeAll();
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			crearTablaPesos();

			btnAgregar.setEnabled(true);

		});

		btnRegistroVacunas.addActionListener(e -> {

			tiporeporte = 2;
			// scroller.removeAll();
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			crearTablaVacunas();

			btnAgregar.setEnabled(true);

		});

		btnRegistroPurgantes.addActionListener(e -> {

			tiporeporte = 3;
			// scroller.removeAll();
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			crearTablaPurgantes();

			btnAgregar.setEnabled(true);

		});

		btnCrias.addActionListener(e -> {

			tiporeporte = 4;
			// scroller.removeAll();
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			crearTablaCrias();

			btnAgregar.setEnabled(false);

		});

		btnAgregar.addActionListener(e -> {

			AgregarInfoReporte inforeporte = new AgregarInfoReporte(tiporeporte, res.getResID(), this);

		});

	}

	public boolean existeRes(String resID) {

		boolean existe = false;

		Res resita = ResCRUD.selectResByID(resID);

		if (resita != null)
			existe = true;

		return existe;

	}

	public JTable getTablaPesos() {
		return tablaPesos;
	}

	public JTable getTablaVacunas() {
		return tablaVacunas;
	}

	public JTable getTablaCrias() {
		return tablaCrias;
	}

	public JTable getTablaPurgantes() {
		return tablaPurgantes;
	}

	public ModelTable getModelPesos() {
		return modelPesos;
	}

	public ModelTable getModelVacunas() {
		return modelVacunas;
	}

	public ModelTable getModelCrias() {
		return modelCrias;
	}

	public ModelTable getModelPurgantes() {
		return modelPurgantes;
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

	public JButton getBtnFechaEmbarazo() {
		return btnFechaEmbarazo;
	}

	public JButton getBtnVerRegistroPeso() {
		return btnVerRegistroPeso;
	}

	public JButton getBtnRegistroVacunas() {
		return btnRegistroVacunas;
	}

	public JButton getBtnRegistroPurgantes() {
		return btnRegistroPurgantes;
	}
}
