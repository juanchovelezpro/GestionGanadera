package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import db.ResCRUD;
import model.Peso;
import model.Purgante;
import model.Res;
import model.Vacuna;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import tools.DocsImporterExporter;
import tools.FileManager;
import tools.GeneradorGrafica;

public class AgregarEditarResDialog extends JDialog {

	private Res res;
	private PotrerosPanel potrero;
	private InicioPanel inicio;
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
	private JCheckBox checkMuerto;
	private JButton btnExportar;
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";



	/**
	 * @wbp.parser.constructor
	 */
	public AgregarEditarResDialog(Res res, PotrerosPanel potrero) {

		this.res = res;
		this.potrero = potrero;

		if (res != null)
			setTitle("Editar Vaca/ ID: " + res.getResID() + " / Potrero: " + res.getPotreroNombre());
		else
			setTitle("Agregar vaca");

		setIconImage(FileManager.imagenes.get("ICONO"));
		getContentPane().setLayout(new GridLayout(1, 2));
		setSize(500, 700);
		setResizable(false);

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

		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public AgregarEditarResDialog(Res res, InicioPanel inicio) {

		this.res = res;
		this.inicio = inicio;

		if (res != null)
			setTitle("Editar Vaca/ ID: " + res.getResID() + " / Potrero: " + res.getPotreroNombre());
		else
			setTitle("Agregar vaca");

		setIconImage(FileManager.imagenes.get("ICONO"));
		getContentPane().setLayout(new GridLayout(1, 2));
		setSize(500, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setComponents();
		listeners();

		cargarInfoRes();

		if (res == null) {

			btnVerRegistroPeso.setEnabled(false);
			btnCrias.setEnabled(false);
			btnRegistroPurgantes.setEnabled(false);
			btnRegistroVacunas.setEnabled(false);

		}

		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public void setComponents() {

		PanelColorAgregar infoPanel = new PanelColorAgregar();
		infoPanel.setBackground(new Color(0, 0, 0, 0));
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
		panelGeneralInfo.setBackground(new Color(0, 0, 0, 0));
		infoPanel.add(panelGeneralInfo, BorderLayout.CENTER);
		panelGeneralInfo.setLayout(new GridLayout(2, 1));

		JPanel panelAux = new JPanel();
		panelAux.setBackground(new Color(0, 0, 0, 0));
		panelAux.setLayout(new GridLayout(10, 2));

		JLabel lblNumero = new JLabel("N\u00FAmero (*)");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblNumero);

		txtNumero = new JTextField("");
		txtNumero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(txtNumero);

		JLabel lblGenero = new JLabel("G\u00E9nero (*)");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		panelAux.add(lblGenero);

		comboGenero = new JComboBox();
		comboGenero.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el g\u00E9nero", "HEMBRA", "MACHO" }));
		panelAux.add(comboGenero);

		lblTipo = new JLabel("Tipo (*)");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelAux.add(lblTipo);

		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] { "Seleccione el tipo" }));
		panelAux.add(comboTipo);

		JLabel lblColor = new JLabel("Color (*)");
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
		comboEmbarazada.setModel(new DefaultComboBoxModel(new String[] { "NO", "SI", "NO APLICA" }));
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
		panelObservaciones.setBackground(new Color(0, 0, 0, 0));
		panelGeneralInfo.add(panelObservaciones);
		panelObservaciones.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 0, 0, 0));
		panelObservaciones.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(2, 1));

		JPanel panelOtro = new JPanel();
		panelOtro.setBackground(new Color(0, 0, 0, 0));
		panel_7.add(panelOtro);
		panelOtro.setLayout(new GridLayout(5, 2));

		JLabel lblRegistroVacunas = new JLabel("Registro Vacunas");
		lblRegistroVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroVacunas.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelOtro.add(lblRegistroVacunas);

		btnRegistroVacunas = new JButton("Ver Registro Vacunas");
		btnRegistroVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistroVacunas.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelOtro.add(btnRegistroVacunas);

		JLabel lblRegistroPurgantes = new JLabel("Registro Purgantes");
		lblRegistroPurgantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroPurgantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelOtro.add(lblRegistroPurgantes);

		btnRegistroPurgantes = new JButton("Ver Registro Purgantes");
		btnRegistroPurgantes.setHorizontalAlignment(SwingConstants.CENTER);
		btnRegistroPurgantes.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelOtro.add(btnRegistroPurgantes);

		JLabel lblNewLabel_4 = new JLabel("");
		panelOtro.add(lblNewLabel_4);

		JLabel lblNewLabel_4_2 = new JLabel("");
		panelOtro.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panelOtro.add(lblNewLabel_4_1);

		checkMuerto = new JCheckBox("\u00BFMUERTO?");
		checkMuerto.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkMuerto.setHorizontalAlignment(SwingConstants.CENTER);
		panelOtro.add(checkMuerto);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservaciones.setFont(new Font("SansSerif", Font.BOLD, 18));
		panelOtro.add(lblObservaciones);

		JLabel lblNewLabel_22_4 = new JLabel("");
		panelOtro.add(lblNewLabel_22_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0.6f));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);

		txtObservaciones = new JTextArea();
		txtObservaciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtObservaciones.setForeground(Color.WHITE);
		txtObservaciones.setBackground(new Color(0, 0, 0, 0.6f));
		txtObservaciones.setLineWrap(true);
		scrollPane.setViewportView(txtObservaciones);

		btnAgregar = new JButton("Agregar");
		btnExportar =new JButton("Exportar");

		scroller = new JScrollPane();

		panelTablaGraficas = new JPanel();
		panelTablaGraficas.setBackground(new Color(0, 0, 0));
		panelTablaGraficas.setLayout(new GridLayout(2, 1));

		panelGrafica = new PanelColorGrafica();
		panelGrafica.setBackground(new Color(236, 235, 233));
		panelGrafica.setLayout(new GridLayout(1, 1));

		panelTabla = new JPanel();
		panelTabla.setLayout(new BorderLayout());
		panelTabla.add(scroller, BorderLayout.CENTER);

		JPanel panelBotonesPeso =new JPanel();
		panelBotonesPeso.setLayout(new GridLayout(1,2));
		
		panelBotonesPeso.add(btnAgregar);
		panelBotonesPeso.add(btnExportar);
		
		panelTabla.add(panelBotonesPeso, BorderLayout.SOUTH);
		
		

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
				btnFechaNacimiento.setText("");

			if (res.getGenero().equals("H")) {

				String emb = res.getEmbarazada() == 0 ? "NO" : "SI";

				comboEmbarazada.setSelectedItem(emb);

				if (emb.equals("SI"))
					btnFechaEmbarazo.setText(res.getFecha_embarazo());

			} else {

				comboEmbarazada.setEnabled(false);
				btnFechaEmbarazo.setEnabled(false);

				comboEmbarazada.setSelectedItem("NO APLICA");

			}

			if (res.getVivo() == 0) {
				checkMuerto.setSelected(true);
			}

			txtMadre.setText(res.getMadreID());
			txtObservaciones.setText(res.getObservaciones());

		}

	}

	public Res obtenerInfoRes(String potrero) throws Exception {

		Res res = new Res();

		res.setPotreroNombre(potrero);

		String id = txtNumero.getText();
		int tipo = comboTipo.getSelectedIndex();
		int gender = comboGenero.getSelectedIndex();
		String color = txtColor.getText();

		if (id != null && !id.equals("") && tipo > 0 && gender > 0 && color != null && !color.equals("")) {
			res.setResID(txtNumero.getText());
			res.setTipo(comboTipo.getSelectedItem().toString());
			String genero = comboGenero.getSelectedItem().toString();

			if (genero.equals("HEMBRA")) {

				res.setGenero("H");
				int embarazo = comboEmbarazada.getSelectedItem().toString().equals("SI") ? 1 : 0;
				res.setEmbarazada(embarazo);

				String fechaEmba = btnFechaEmbarazo.getText();

				if (embarazo == 1 && fechaEmba != null && !fechaEmba.equals("") && !fechaEmba.equals("dd/mm/AAAA")) {
										
					res.setFecha_UltimoEmbarazo(fechaEmba);
					res.setFecha_embarazo(fechaEmba);
				}else {
					
					if(this.res!=null && this.res.getFecha_UltimoEmbarazo()!=null)
						res.setFecha_UltimoEmbarazo(this.res.getFecha_UltimoEmbarazo());
					
				}
				

				System.out.println("Fecha emba: "+fechaEmba);
				if(embarazo == 1 && (fechaEmba==null || fechaEmba.equals("") || fechaEmba.equals("dd/mm/AAAA"))) {
					
					throw new Exception("Introduzca la fecha de embarazo si indica que se encuentra embarazada");
					
				}
				

			} else {

				res.setGenero("M");
				res.setEmbarazada(-1);

			}

			res.setColor(txtColor.getText());

			String fechaNac = btnFechaNacimiento.getText();

			if (fechaNac != null && !fechaNac.equals("") && !fechaNac.equals("dd/mm/AAAA")) {
				res.setFecha_nacimiento(fechaNac);
			} else {
				res.setFecha_nacimiento("");
			}

			if (checkMuerto.isSelected()) {
				res.setVivo(0);
			} else {
				res.setVivo(1);
			}

			res.setMadreID(txtMadre.getText());
			res.setObservaciones(txtObservaciones.getText());
		} else {

			throw new Exception("No se han completado algunos campos obligatorios. (*)");

		}

		return res;

	}

	public void transformarPanel() {

		getContentPane().add(panelTablaGraficas);
		setSize(1100, 700);
		setLocationRelativeTo(null);

	}
	
	public double calcularBalance(ArrayList<Peso> pesos, int posicion) {
		
		double balance = 0;

		
		if (posicion==pesos.size()-1) {
			
			balance= 0;
		}else {
			
			balance= pesos.get(posicion).getPeso()-pesos.get(posicion+1).getPeso();
			
		}
		

		return balance;
		
		
		
	}

	public void crearTablaPesos() {

		String[] columns = { "PESO", "FECHA", "BALANCE" };

		if (res != null) {

			ArrayList<Peso> pesos = ResCRUD.selectPesosLista(res.getResID());
			Collections.sort(pesos);
			Collections.reverse(pesos);
		
			Object[][] data = new Object[pesos.size()][columns.length];
			Peso temp = null;

			for (int i = 0; i < data.length; i++) {

				temp = pesos.get(i);

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = temp.getPeso();

					if (j == 1)
						data[i][j] = temp.getFecha();
					
					if (j == 2)
						data[i][j] = calcularBalance(pesos, i);
					    

				}

			}
			
			
           
			modelPesos = new ModelTable();
			modelPesos.setData(data);
			modelPesos.setColumns(columns);
			tablaPesos = new JTable(modelPesos);
			
		
			/**
			for (int i = 0; i < pesos.size(); i++) {
				
				double valor = Double.parseDouble(tablaPesos.getValueAt(i, 2).toString());
				
				if (valor<0) {
                    tablaPesos.getCellRenderer(i, 2).getTableCellRendererComponent(tablaPesos, valor, true, true, i, 2).setForeground(Color.RED);
				}else if (valor>0) {

                    tablaPesos.getCellRenderer(i, 2).getTableCellRendererComponent(tablaPesos, valor, true, true, i, 2).setForeground(Color.GREEN);

				}

			}
			**/
			
			
			tablaPesos.setFont(new Font("Tahoma", Font.BOLD, 14));
			tablaPesos.setShowHorizontalLines(true);
			tablaPesos.setShowVerticalLines(true);
			tablaPesos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaPesos);
			tablaPesos.setFillsViewportHeight(true);
			
			

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelPesos = new ModelTable();
			modelPesos.setData(data);
			modelPesos.setColumns(columns);
			tablaPesos = new JTable(modelPesos);
			tablaPesos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPesos.setShowHorizontalLines(true);
			tablaPesos.setShowVerticalLines(true);
			tablaPesos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaPesos);
			tablaPesos.setFillsViewportHeight(true);

			transformarPanel();

		}

		tablaPesos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == MouseEvent.BUTTON3) {

					int valor = JOptionPane.showConfirmDialog(null, "\u00BFEst\u00E1 seguro de eliminar este peso?");

					if (valor == JOptionPane.OK_OPTION) {

						int fila = tablaPesos.getSelectedRow();
						Peso peso = new Peso(Double.parseDouble(modelPesos.getValueAt(fila, 0).toString()),
								modelPesos.getValueAt(fila, 1).toString());
						ResCRUD.deletePesoFromRes(res.getResID(), peso.getPeso(), peso.getFecha());
						refreshTable(1);
						cerrarPanel();

					}

				}
			}
		});

	}

	public void crearTablaVacunas() {

		String[] columns = { "VACUNA", "FECHA" };

		if (res != null) {

			Stack<Vacuna> vacunas = ResCRUD.selectVacunas(res.getResID());
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
			modelVacunas.setData(data);
			modelVacunas.setColumns(columns);
			tablaVacunas = new JTable(modelVacunas);
			tablaVacunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaVacunas.setShowHorizontalLines(true);
			tablaVacunas.setShowVerticalLines(true);
			tablaVacunas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaVacunas);
			tablaVacunas.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelVacunas = new ModelTable();
			modelVacunas.setData(data);
			modelVacunas.setColumns(columns);
			tablaVacunas = new JTable(modelVacunas);
			tablaVacunas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaVacunas.setShowHorizontalLines(true);
			tablaVacunas.setShowVerticalLines(true);
			tablaVacunas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaVacunas);
			tablaVacunas.setFillsViewportHeight(true);

			transformarPanel();

		}

	}

	public void crearTablaPurgantes() {

		String[] columns = { "PURGANTE", "FECHA" };

		if (res != null) {

			Stack<Purgante> purgantes = ResCRUD.selectPurgantes(res.getResID());
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
			modelPurgantes.setData(data);
			modelPurgantes.setColumns(columns);
			tablaPurgantes = new JTable(modelPurgantes);
			tablaPurgantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPurgantes.setShowHorizontalLines(true);
			tablaPurgantes.setShowVerticalLines(true);
			tablaPurgantes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaPurgantes);
			tablaPurgantes.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelPurgantes = new ModelTable();
			modelPurgantes.setData(data);
			modelPurgantes.setColumns(columns);
			tablaPurgantes = new JTable(modelPurgantes);
			tablaPurgantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPurgantes.setShowHorizontalLines(true);
			tablaPurgantes.setShowVerticalLines(true);
			tablaPurgantes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaPurgantes);
			tablaPurgantes.setFillsViewportHeight(true);

			transformarPanel();

		}
	}

	public void crearTablaCrias() {

		String[] columns = { "# CR\u00CDA", "FECHA NACIMIENTO" };

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
			modelCrias.setData(data);
			modelCrias.setColumns(columns);
			tablaCrias = new JTable(modelCrias);
			tablaCrias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaCrias.setShowHorizontalLines(true);
			tablaCrias.setShowVerticalLines(true);
			tablaCrias.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaCrias);
			tablaCrias.setFillsViewportHeight(true);

			transformarPanel();

		} else {

			Object[][] data = new Object[0][columns.length];
			modelCrias = new ModelTable();
			modelCrias.setData(data);
			modelCrias.setColumns(columns);
			tablaCrias = new JTable(modelCrias);
			tablaCrias.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaCrias.setShowHorizontalLines(true);
			tablaCrias.setShowVerticalLines(true);
			tablaCrias.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaCrias);
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

	public void cerrarPanel() {

		remove(panelTablaGraficas);
		setLocationRelativeTo(null);
		setSize(500, 700);
		invalidate();
		revalidate();
		repaint();
	}

	public void listeners() {

		btnFechaNacimiento.addActionListener(e -> {

			CalendarioDialog calendar = new CalendarioDialog(btnFechaNacimiento);

		});

		btnFechaEmbarazo.addActionListener(e -> {

			CalendarioDialog calendar = new CalendarioDialog(btnFechaEmbarazo);

			if (btnFechaEmbarazo.getText() != null && !btnFechaEmbarazo.getText().equalsIgnoreCase("")
					&& comboTipo.getSelectedItem().toString().equalsIgnoreCase("VH")) {

				comboTipo.setSelectedItem("NV");
			}

		});

		btnGuardarCerrar.addActionListener(e -> {

			// Guardar en la base de datos.

			if (res != null) {

				if (res.getResID().equals(txtNumero.getText().trim()) || !existeRes(txtNumero.getText().trim())) {

					if (potrero != null) {

						try {
							ResCRUD.update(res.getResID(), obtenerInfoRes(potrero.getPotrero_elegido()));
							potrero.refreshTable();
							dispose();
						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex.getMessage(), "Info incompleta",
									JOptionPane.ERROR_MESSAGE);

						}

					} else {

						try {
							ResCRUD.update(res.getResID(), obtenerInfoRes(res.getPotreroNombre()));
							dispose();
						} catch (Exception ex) {

							JOptionPane.showMessageDialog(null, ex.getMessage(), "Info incompleta",
									JOptionPane.ERROR_MESSAGE);

						}
					}
				} else {

					JOptionPane.showMessageDialog(null, "Ya existe una res con ese numero", "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			} else {

				if (!existeRes(txtNumero.getText().trim())) {
					try {
						ResCRUD.insert(obtenerInfoRes(potrero.getPotrero_elegido()));
						potrero.refreshTable();
						dispose();
					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, ex.getMessage(), "Info incompleta",
								JOptionPane.ERROR_MESSAGE);

					}
				} else {
					JOptionPane.showMessageDialog(null, "Ya existe una res con ese n\u00FAmero", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		comboGenero.addActionListener(e ->

		{

			String selected = comboGenero.getSelectedItem().toString();

			if (selected.equals("MACHO")) {

				comboEmbarazada.setSelectedItem("NO APLICA");
				btnFechaEmbarazo.setText("NO APLICA");
				btnCrias.setEnabled(false);

			} else if (selected.equals("HEMBRA")) {

				btnCrias.setEnabled(true);
				btnFechaEmbarazo.setEnabled(true);
				comboEmbarazada.setEnabled(true);

			} else {

				btnCrias.setEnabled(false);

			}

		});

		btnVerRegistroPeso.addActionListener(e -> {

			tiporeporte = 1;
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			panelGrafica.removeAll();
			panelGrafica.add(GeneradorGrafica.graficaPesos(ResCRUD.selectPesosLista(res.getResID())));
			panelGrafica.invalidate();
			panelGrafica.revalidate();
			panelGrafica.repaint();
			pack();
			crearTablaPesos();

			btnAgregar.setEnabled(true);
			btnExportar.setEnabled(true);


		});

		btnRegistroVacunas.addActionListener(e -> {

			tiporeporte = 2;
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}
			panelGrafica.removeAll();
			panelGrafica.invalidate();
			panelGrafica.revalidate();
			panelGrafica.repaint();
			pack();
			crearTablaVacunas();

			btnAgregar.setEnabled(true);
			btnExportar.setEnabled(false);


		});

		btnRegistroPurgantes.addActionListener(e -> {

			tiporeporte = 3;
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			panelGrafica.removeAll();
			panelGrafica.invalidate();
			panelGrafica.revalidate();
			panelGrafica.repaint();
			pack();
			crearTablaPurgantes();

			btnAgregar.setEnabled(true);
			btnExportar.setEnabled(false);


		});

		btnCrias.addActionListener(e -> {

			tiporeporte = 4;
			if (panelTablaGraficas.getParent() != null) {

				getContentPane().remove(panelTablaGraficas);
			}

			panelGrafica.removeAll();
			panelGrafica.invalidate();
			panelGrafica.revalidate();
			panelGrafica.repaint();
			pack();
			crearTablaCrias();

			btnAgregar.setEnabled(false);
			btnExportar.setEnabled(false);

		});

		btnAgregar.addActionListener(e -> {

			AgregarInfoReporte inforeporte = new AgregarInfoReporte(tiporeporte, res.getResID(), this);

		});
		
		btnExportar.addActionListener(e ->{
			
			try {

				JFileChooser fileSaver = new JFileChooser();
				int op = fileSaver.showSaveDialog(null);

				if (op == JFileChooser.APPROVE_OPTION) {

					DocsImporterExporter.exportarPesos(fileSaver.getSelectedFile().getPath() + ".xlsx", res.getResID());

					JOptionPane.showMessageDialog(null, "Guardado en " + fileSaver.getSelectedFile().getPath(), "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception xd) {
				xd.printStackTrace();
			}
		});

		comboGenero.addActionListener(e -> {

			if (comboGenero.getSelectedIndex() == 1) {
				comboTipo.setModel(
						new DefaultComboBoxModel(new String[] { "Seleccione el tipo", "VP", "VH", "CH", "HL", "NV" }));
			}

			if (comboGenero.getSelectedIndex() == 2) {
				comboTipo.setModel(
						new DefaultComboBoxModel(new String[] { "Seleccione el tipo", "CM", "ML", "MC", "TP" }));
			}

		});

		comboEmbarazada.addActionListener(e -> {

			String selected = comboEmbarazada.getSelectedItem().toString();

			if (selected.equalsIgnoreCase("NO") || selected.equalsIgnoreCase("NO APLICA")) {

				btnFechaEmbarazo.setText("");
				btnFechaEmbarazo.setEnabled(false);

			} else {

				btnFechaEmbarazo.setText("dd/mm/AAAA");
				btnFechaEmbarazo.setEnabled(true);

			}

		});

		comboTipo.addActionListener(e -> {

		});

	}

	public boolean existeRes(String resID) {

		boolean existe = false;

		Res resita = ResCRUD.selectResByID(resID);

		if (resita != null)
			existe = true;

		return existe;

	}

	public void refreshGraficaPesos() {

		panelGrafica.removeAll();
		panelGrafica.add(GeneradorGrafica.graficaPesos(ResCRUD.selectPesosLista(res.getResID())));
		pack();
		setSize(1100, 700);

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

	public JCheckBox getCheckMuerto() {
		return checkMuerto;
	}
}
