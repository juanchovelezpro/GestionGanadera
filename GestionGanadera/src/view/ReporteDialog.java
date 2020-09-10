package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import db.ResCRUD;
import model.Res;
import tools.DocsImporterExporter;
import tools.FileManager;

public class ReporteDialog extends JDialog {
	private JButton btnExportar;
	private JButton btnRegresar;
	private String reporte;
	private Calendar fechaSystem;
	private String fecha_Convertida;
	private JTable tablaDestete;
	private ModelTable modelDestete;
	private JTable tablaParto;
	private ModelTable modelParto;
	private JTable tablaPurgante;
	private ModelTable modelPurgante;
	private JTable tablaVacuna;
	private ModelTable modelVacuna;
	private JScrollPane scroller;
	private JPanel panelinfo;
	private int valor;

	
	public ReporteDialog(String reporte, int valor) {

		this.reporte = reporte;
		this.valor = valor;
		setTitle("Reporte");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(650, 400);
		setResizable(false);

		setIconImage(FileManager.imagenes.get("REPORTE"));
		setLocationRelativeTo(null);

		Components();
		elegirTabla(valor);

		listeners();

		setModalityType(ModalityType.APPLICATION_MODAL);

		setVisible(true);

	}

	public void Components() {

		fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		fecha_Convertida = dia + "/" + mes + "/" + anio;

		scroller = new JScrollPane();

		PanelColorReporte panel_1 = new PanelColorReporte();
		panel_1.setBackground(new Color(0, 0, 0, 120));
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1, BorderLayout.CENTER);

		JPanel panelTexto = new JPanel();
		panelTexto.setBackground(new Color(0, 0, 0, 120));
		panel_1.add(panelTexto, BorderLayout.NORTH);
		panelTexto.setLayout(new GridLayout(3, 1));

		JLabel lblNewLabel = new JLabel("     ");
		panelTexto.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(reporte + " - " + fecha_Convertida);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelTexto.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panelTexto.add(lblNewLabel_2);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 0, 0, 120));
		panel_1.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("     ");
		panelPrincipal.add(lblNewLabel_3, BorderLayout.WEST);

		JLabel lblNewLabel_4 = new JLabel("     ");
		panelPrincipal.add(lblNewLabel_4, BorderLayout.EAST);

		// REPORTE DE INFORMACION

		panelinfo = new JPanel();
		panelinfo.setBackground(new Color(0, 0, 0, 120));
		panelinfo.setLayout(new GridLayout(1, 1));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		panelPrincipal.add(panel, BorderLayout.SOUTH);

		btnExportar = new JButton(" Exportar ");
		btnExportar.setBackground(Color.WHITE);
		panel.add(btnExportar);

		btnRegresar = new JButton(" Regresar ");
		btnRegresar.setBackground(Color.WHITE);
		panel.add(btnRegresar);

		panelPrincipal.add(panelinfo, BorderLayout.CENTER);

		

	}

	public void elegirTabla(int valor) {

		if (valor == 1) {
			crearTablaDestete();

		} else if (valor == 2) {
			crearTablaPartos();
		} else if (valor == 4) {
			crearTablaPurgante();
		} else if (valor == 3) {
			crearTablaVacuna();
		}

	}

	public void crearTablaPartos() {

		String[] columns = { "N\u00DAMERO", "FECHA EMBARAZO", "TIEMPO DE EMBARAZO", "POTRERO" };

		modelParto = new ModelTable();

		ArrayList<Res> reses = ResCRUD.reportePartos();
		Object[][] data = new Object[reses.size()][columns.length];
		Res temp = null;
		String mensaje = "";

		for (int i = 0; i < data.length; i++) {

			temp = reses.get(i);

			for (int j = 0; j < data[0].length; j++) {

				if (j == 0)
					data[i][j] = temp.getResID();

				if (j == 1)
					data[i][j] = temp.getFecha_embarazo();

				if (j == 2)
					data[i][j] = ResCRUD.calcDate(temp.getFecha_embarazo());

				if (j == 3)
					data[i][j] = temp.getPotreroNombre();

			}

			modelParto.setData(data);
			modelParto.setColumns(columns);
			tablaParto = new JTable(modelParto);
			tablaParto.setBackground(new Color(225, 235, 239));

			tablaParto.getColumnModel().getColumn(2).setPreferredWidth(140);
			tablaParto.getColumnModel().getColumn(1).setPreferredWidth(120);

			tablaParto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaParto.setShowHorizontalLines(true);
			tablaParto.setShowVerticalLines(true);
			tablaParto.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaParto);
			tablaParto.setFillsViewportHeight(true);
			panelinfo.add(scroller);

		}

	}

	public void crearTablaPurgante() {

		String[] columns = { "N\u00DAMERO", "PURGANTE", "ULTIMA FECHA", "POTRERO" };

		modelPurgante = new ModelTable();

		ArrayList<Res> reses = ResCRUD.reportePurgado();
		Object[][] data = new Object[reses.size()][columns.length];
		Res temp = null;
		String mensaje = "";

		for (int i = 0; i < data.length; i++) {

			temp = reses.get(i);

			for (int j = 0; j < data[0].length; j++) {

				if (j == 0)
					data[i][j] = temp.getResID();

				if (j == 1)
					data[i][j] = temp.getPurgantes().peek().getNombre();

				if (j == 2)
					data[i][j] = temp.getPurgantes().peek().getFecha();

				if (j == 3)
					data[i][j] = temp.getPotreroNombre();

			}

			modelPurgante.setData(data);
			modelPurgante.setColumns(columns);
			tablaPurgante = new JTable(modelPurgante);
			tablaPurgante.setBackground(new Color(225, 235, 239));

			tablaPurgante.getColumnModel().getColumn(2).setPreferredWidth(140);
			tablaPurgante.getColumnModel().getColumn(1).setPreferredWidth(120);

			tablaPurgante.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaPurgante.setShowHorizontalLines(true);
			tablaPurgante.setShowVerticalLines(true);
			tablaPurgante.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaPurgante);
			tablaPurgante.setFillsViewportHeight(true);
			panelinfo.add(scroller);

		}

	}

	public void crearTablaVacuna() {

		String[] columns = { "N\u00FDAMERO", "VACUNA", "ULTIMA FECHA", "POTRERO" };

		modelVacuna = new ModelTable();

		ArrayList<Res> reses = ResCRUD.reporteVacunaNotificaciones();

		Object[][] data = new Object[reses.size()][columns.length];
		Res temp = null;
		String mensaje = "";

		for (int i = 0; i < data.length; i++) {

			temp = reses.get(i);

			for (int j = 0; j < data[0].length; j++) {

				if (j == 0)
					data[i][j] = temp.getResID();

				if (j == 1)
					data[i][j] = temp.getVacunas().peek().getNombre();

				if (j == 2)
					data[i][j] = temp.getVacunas().peek().getFecha();

				if (j == 3)
					data[i][j] = temp.getPotreroNombre();

			}

			modelVacuna.setData(data);
			modelVacuna.setColumns(columns);
			tablaVacuna = new JTable(modelVacuna);
			tablaVacuna.setBackground(new Color(225, 235, 239));
			tablaVacuna.getColumnModel().getColumn(2).setPreferredWidth(140);
			tablaVacuna.getColumnModel().getColumn(1).setPreferredWidth(120);

			tablaVacuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaVacuna.setShowHorizontalLines(true);
			tablaVacuna.setShowVerticalLines(true);
			tablaVacuna.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaVacuna);
			tablaVacuna.setFillsViewportHeight(true);
			panelinfo.add(scroller);

		}

	}

	public void crearTablaDestete() {

		String[] columns = { "N\u00DAMERO", "NACIMIENTO", "TIEMPO DE CRÍA", "MADRE", "POTRERO" };

		modelDestete = new ModelTable();

		ArrayList<Res> reses = ResCRUD.reporteDestete();
		Object[][] data = new Object[reses.size()][columns.length];
		Res temp = null;
		String mensaje = "";

		for (int i = 0; i < data.length; i++) {

			temp = reses.get(i);

			for (int j = 0; j < data[0].length; j++) {

				if (j == 0)
					data[i][j] = temp.getResID();

				if (j == 1)
					data[i][j] = temp.getFecha_nacimiento();

				if (j == 2)
					data[i][j] = ResCRUD.calcDate(temp.getFecha_nacimiento());

				if (j == 3)
					data[i][j] = temp.getMadreID();

				if (j == 4)
					data[i][j] = temp.getPotreroNombre();

			}

			modelDestete.setData(data);
			modelDestete.setColumns(columns);
			tablaDestete = new JTable(modelDestete);
			tablaDestete.setBackground(new Color(225, 235, 239));
			tablaDestete.getColumnModel().getColumn(2).setPreferredWidth(140);
			tablaDestete.getColumnModel().getColumn(1).setPreferredWidth(120);

			tablaDestete.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaDestete.setShowHorizontalLines(true);
			tablaDestete.setShowVerticalLines(true);
			tablaDestete.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaDestete);
			tablaDestete.setFillsViewportHeight(true);
			panelinfo.add(scroller);

		}

	}

	public void listeners() {

		btnExportar.addActionListener(e -> {

			try {

				JFileChooser fileSaver = new JFileChooser();
				int op = fileSaver.showSaveDialog(null);

				if (op == JFileChooser.APPROVE_OPTION) {

					DocsImporterExporter.exportarReporte(valor, fileSaver.getSelectedFile().getPath() + ".xlsx");

					JOptionPane.showMessageDialog(null, "Guardado en " + fileSaver.getSelectedFile().getPath(), "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception xd) {
				xd.printStackTrace();
			}

		});

		btnRegresar.addActionListener(e -> {

			setVisible(false);

		});

	}

	public JButton getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

}
