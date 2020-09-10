package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import db.ResCRUD;
import model.Res;
import tools.FileManager;

public class NotificacionesDialog extends JDialog {
	private PotrerosPanel ventana;
	private JList<Res> listPartos;
	private JList<Res> listDestete;
	private JList<Res> listPurgado;
	private JList<Res> listVacuna;
	private JScrollPane listScrollerPartos;
	DefaultListModel<Res> modeloPartos;
	private JScrollPane listScrollerDestete;
	DefaultListModel<Res> modeloDestete;
	private JScrollPane listScrollerPurgado;
	DefaultListModel<Res> modeloPurgado;
	private JScrollPane listScrollerVacuna;
	DefaultListModel<Res> modeloVacuna;
	JTabbedPane tabbedPane;
	JTable tablaPartos;
	JTable tablaDestete;
	JTable tablaPurgado;
	JTable tablaVacuna;
	ModelTable modeloPartosP;
	ModelTable modeloDesteteP;
	ModelTable modeloPurgadoP;
	ModelTable modeloVacunaP;
	ModeloTableVacuna modelotablevacuna;
	ModeloTablePurgado modelotablepurgado;
	ModeloTableParto modelotablepartos;
	ModeloTableDestete modelotabledestete;
	private JPanel panel;

	public NotificacionesDialog(PotrerosPanel ventana) {

		this.ventana = ventana;

		setTitle("Notificaciones");

		setSize(730, 450);
		setLocationRelativeTo(null);
		setResizable(false);


		setIconImage(FileManager.imagenes.get("ICONO"));
		Components();
		listeners();


		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public void Components() {

		getContentPane().setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setForeground(Color.WHITE);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		listPartos = new JList<Res>();
		modeloPartos = new DefaultListModel<Res>();
		listPartos.setModel(modeloPartos);

		listDestete = new JList<Res>();
		modeloDestete = new DefaultListModel<Res>();
		listDestete.setModel(modeloDestete);

		listPurgado = new JList<Res>();
		modeloPurgado = new DefaultListModel<Res>();
		listPurgado.setModel(modeloPurgado);

		listVacuna = new JList<Res>();
		modeloVacuna = new DefaultListModel<Res>();
		listVacuna.setModel(modeloVacuna);

		listScrollerPartos = new JScrollPane(listPartos);
		listScrollerPartos.setPreferredSize(new Dimension(450, 300));

		listScrollerDestete = new JScrollPane(listDestete);
		listScrollerDestete.setPreferredSize(new Dimension(450, 300));

		listScrollerPurgado = new JScrollPane(listPurgado);
		listScrollerPurgado.setPreferredSize(new Dimension(450, 300));

		listScrollerVacuna = new JScrollPane(listVacuna);
		listScrollerVacuna.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScrollerVacuna.setPreferredSize(new Dimension(450, 300));

		tabbedPane.addTab("Partos", null, listScrollerPartos, null);
		tabbedPane.addTab("Destete", null, listScrollerDestete, null);
		tabbedPane.addTab("Purgado", null, listScrollerPurgado, null);
		tabbedPane.addTab("Vacunas", null, listScrollerVacuna, null);

		PanelColorNotificaciones panel = new PanelColorNotificaciones();
		getContentPane().add(panel, BorderLayout.NORTH);
		JLabel lblNewLabel_2 = new JLabel("Eventos Proximos");
		lblNewLabel_2.setForeground(Color.WHITE);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		crearTablaVacuna();
		crearTablaDestete();
		crearTablaParto();
		crearTablaPurgado();

	}

	public void crearTablaVacuna() {

		String[] columns = { "NOTIFICACION" };

		ArrayList<Res> resesVacuna = ResCRUD.reporteVacunaNotificaciones();

		modelotablevacuna = new ModeloTableVacuna(resesVacuna);

		modelotablevacuna.setColumna(columns);
		tablaVacuna = new JTable(modelotablevacuna);
		tablaVacuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaVacuna.setShowHorizontalLines(true);
		tablaVacuna.setShowVerticalLines(true);
		tablaVacuna.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		listScrollerVacuna.setViewportView(tablaVacuna);
		tablaVacuna.setFillsViewportHeight(true);

	}

	public void crearTablaPurgado() {

		String[] columns = { "NOTIFICACION" };

		ArrayList<Res> resesVacuna = ResCRUD.reportePurgado();

		modelotablepurgado = new ModeloTablePurgado(resesVacuna);

		modelotablepurgado.setColumna(columns);
		tablaPurgado = new JTable(modelotablepurgado);
		tablaPurgado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaPurgado.setShowHorizontalLines(true);
		tablaPurgado.setShowVerticalLines(true);
		tablaPurgado.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		listScrollerPurgado.setViewportView(tablaPurgado);
		tablaPurgado.setFillsViewportHeight(true);

	}

	public void crearTablaParto() {

		String[] columns = { "NOTIFICACION" };

		ArrayList<Res> resesVacuna = ResCRUD.reportePartos();

		modelotablepartos = new ModeloTableParto(resesVacuna);

		modelotablepartos.setColumna(columns);
		tablaPartos = new JTable(modelotablepartos);
		tablaPartos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaPartos.setShowHorizontalLines(true);
		tablaPartos.setShowVerticalLines(true);
		tablaPartos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		listScrollerPartos.setViewportView(tablaPartos);
		tablaPartos.setFillsViewportHeight(true);

	}

	public void crearTablaDestete() {

		String[] columns = { "NOTIFICACION" };

		ArrayList<Res> resesVacuna = ResCRUD.reporteDestete();

		modelotabledestete = new ModeloTableDestete(resesVacuna);

		modelotabledestete.setColumna(columns);
		tablaDestete = new JTable(modelotabledestete);
		tablaDestete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaDestete.setShowHorizontalLines(true);
		tablaDestete.setShowVerticalLines(true);
		tablaDestete.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		listScrollerDestete.setViewportView(tablaDestete);
		tablaDestete.setFillsViewportHeight(true);

	}

	

	public void listeners() {

		tablaVacuna.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int[] rowsSelected = tablaVacuna.getSelectedRows();

				ArrayList<Res> resesSeleccionadas = new ArrayList<>();
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {

					int valor = JOptionPane.showConfirmDialog(null, "\u00BFEst\u00E1 seguro de eliminar esta notificaci\u00F3n?");

					if (valor == JOptionPane.YES_OPTION) {

						for (int i = 0; i < rowsSelected.length; i++) {

							resesSeleccionadas.add(modelotablevacuna.getReses().get(rowsSelected[i]));

						}

						CalendarioDialog calendario = new CalendarioDialog(null);

						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

						String fecha = format.format(calendario.getCalendar().getDate());

						ResCRUD.insertVacunaMultipleSegunda(resesSeleccionadas, fecha);

						refreshTableVacuna();
					}

				}
			}

		});

		tablaPurgado.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int[] rowsSelected = tablaPurgado.getSelectedRows();

				ArrayList<Res> resesSeleccionadas = new ArrayList<>();
				if (e.getButton() == MouseEvent.BUTTON3) {

					int valor = JOptionPane.showConfirmDialog(null, "\u00BFEst\u00E1 seguro de eliminar esta notificaci\u00F3n?");

					if (valor == JOptionPane.YES_OPTION) {

						for (int i = 0; i < rowsSelected.length; i++) {

							resesSeleccionadas.add(modelotablepurgado.getReses().get(rowsSelected[i]));

						}

						CalendarioDialog calendario = new CalendarioDialog(null);

						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

						String fecha = format.format(calendario.getCalendar().getDate());

						ResCRUD.insertPurganteMultipleSegunda(resesSeleccionadas, fecha);

						refreshTablePurgado();
					}

				}
			}

		});

		tablaPartos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int[] rowsSelected = tablaPartos.getSelectedRows();

				ArrayList<Res> resesSeleccionadas = new ArrayList<>();
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {

					int valor = JOptionPane.showConfirmDialog(null, "\u00BFEst\u00E1 seguro de eliminar esta notificaci\u00F3n?");

					if (valor == JOptionPane.YES_OPTION) {

						for (int i = 0; i < rowsSelected.length; i++) {

							resesSeleccionadas.add(modelotablepartos.getReses().get(rowsSelected[i]));

							if (resesSeleccionadas.get(i).getGenero().equals("H")) {

								resesSeleccionadas.get(i).setTipo("VP");
								resesSeleccionadas.get(i).setEmbarazada(0);
								resesSeleccionadas.get(i).setFecha_embarazo("");
								ResCRUD.update(resesSeleccionadas.get(i).getResID(), resesSeleccionadas.get(i));

							}
						}

					}
					refreshTablePartos();
					ventana.refreshTable();

				}

			}

		});

		tablaDestete.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int[] rowsSelected = tablaDestete.getSelectedRows();

				ArrayList<Res> resesSeleccionadas = new ArrayList<>();
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {

					int valor = JOptionPane.showConfirmDialog(null, "¿Est\u00E1 seguro de eliminar esta notificaci\u00F3n?");

					if (valor == JOptionPane.YES_OPTION) {

						for (int i = 0; i < rowsSelected.length; i++) {

							resesSeleccionadas.add(modelotabledestete.getReses().get(rowsSelected[i]));

							if (resesSeleccionadas.get(i).getGenero().equals("H")) {

								resesSeleccionadas.get(i).setTipo("HL");
								ResCRUD.update(resesSeleccionadas.get(i).getResID(), resesSeleccionadas.get(i));
								System.out.println("actualizado");

								Res madre = ResCRUD.selectResByID(resesSeleccionadas.get(i).getMadreID());

								madre.setTipo("VH");
								ResCRUD.update(madre.getResID(), madre);

							}

							if (resesSeleccionadas.get(i).getGenero().equals("M")) {

								resesSeleccionadas.get(i).setTipo("ML");
								ResCRUD.update(resesSeleccionadas.get(i).getResID(), resesSeleccionadas.get(i));
								System.out.println("actualizado");

								Res madre = ResCRUD.selectResByID(resesSeleccionadas.get(i).getMadreID());

								madre.setTipo("VH");
								ResCRUD.update(madre.getResID(), madre);

							}

						}

						refreshTableDestete();
						ventana.refreshTable();

					}
				}

			}

		});

	}

	public void refreshTablePurgado() {

		ArrayList<Res> resesVacuna = ResCRUD.reportePurgado();

		modelotablepurgado.setReses(resesVacuna);
		modelotablepurgado.fireTableDataChanged();

	}

	public void refreshTablePartos() {

		ArrayList<Res> resesVacuna = ResCRUD.reportePartos();

		modelotablepartos.setReses(resesVacuna);
		modelotablepartos.fireTableDataChanged();

	}

	public void refreshTableDestete() {

		ArrayList<Res> resesVacuna = ResCRUD.reporteDestete();

		modelotabledestete.setReses(resesVacuna);
		modelotabledestete.fireTableDataChanged();

	}

	public void refreshTableVacuna() {

		ArrayList<Res> resesVacuna = ResCRUD.reporteVacunaNotificaciones();

		modelotablevacuna.setReses(resesVacuna);
		modelotablevacuna.fireTableDataChanged();

	}

	

}