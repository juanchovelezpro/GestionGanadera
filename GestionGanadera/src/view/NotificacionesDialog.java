package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import db.ResCRUD;
import model.Purgante;
import model.Res;
import model.Vacuna;
import tools.FileManager;
import javax.swing.JTabbedPane;

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

	public NotificacionesDialog(PotrerosPanel ventana) {

		this.ventana = ventana;

		setTitle("Notificaciones");

		setSize(605, 452);
		setLocationRelativeTo(null);

		setIconImage(FileManager.imagenes.get("ICONO"));
		Components();
		listenersPrincipales();
		listenerTabbed();

		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public void Components() {

		getContentPane().setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel_2 = new JLabel("Eventos Proximos");
		getContentPane().add(lblNewLabel_2, BorderLayout.NORTH);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
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
		listScrollerVacuna.setPreferredSize(new Dimension(450, 300));

		tabbedPane.addTab("Partos", null, listScrollerPartos, null);
		tabbedPane.addTab("Destete", null, listScrollerDestete, null);
		tabbedPane.addTab("Purgado", null, listScrollerPurgado, null);
		tabbedPane.addTab("Vacunas", null, listScrollerVacuna, null);

	}

	public void listenerTabbed() {

		tabbedPane.addMouseListener(new MouseAdapter() {

			int valor = 10;

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == MouseEvent.BUTTON1) {

					valor = tabbedPane.getSelectedIndex();

					if (valor == 0) {

						modeloPartos.clear();

						listPartos.setCellRenderer(new RenderizadoPartos());

						ArrayList<Res> resesPa = ResCRUD.reportePartos();
						System.out.println(resesPa.size() + "aqui");

						for (int i = 0; i < resesPa.size(); i++) {

							modeloPartos.addElement(resesPa.get(i));
						}

					}
					if (valor == 1) {

						modeloDestete.clear();

						listDestete.setCellRenderer(new RenderizadoDestete());

						ArrayList<Res> resesD = ResCRUD.reporteDestete();
						System.out.println(resesD.size());

						for (int i = 0; i < resesD.size(); i++) {

							modeloDestete.addElement(resesD.get(i));

						}

					}
					if (valor == 2) {

						modeloPurgado.clear();

						ArrayList<Res> resesP = ResCRUD.reportePurgado();
						System.out.println(resesP.size());

						listPurgado.setCellRenderer(new RenderizadoPurgado());

						for (int i = 0; i < resesP.size(); i++) {

							modeloPurgado.addElement(resesP.get(i));

						}

					}
					if (valor == 3) {

						modeloVacuna.clear();

						ArrayList<Res> resesV = ResCRUD.reporteVacunaNotificaciones();
						System.out.println(resesV.size());

						listVacuna.setCellRenderer(new RenderizadoVacuna());

						for (int i = 0; i < resesV.size(); i++) {

							modeloVacuna.addElement(resesV.get(i));

						}

					}
				}
			}
		});

	}

	public void partoLis() {

		List<Res> selectedValuesList = listPartos.getSelectedValuesList();

		// Double-click detected
		System.out.println(listPartos.getSelectedIndex() + "oprimio");

		int numero = listPartos.getSelectedIndex();

		int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

		if (valor == JOptionPane.OK_OPTION) {
			Res res = null;

			for (int i = 0; i < selectedValuesList.size(); i++) {

				res = selectedValuesList.get(i);

				if (res.getGenero().equals("H")) {

					res.setTipo("VP");
					ResCRUD.update(res.getResID(), res);
					modeloPartos.removeElement(listPartos.getSelectedValue());

				}
			}

		}

	}

	public void desteteLis() {

		List<Res> selectedValuesList = listDestete.getSelectedValuesList();

		int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

		if (valor == JOptionPane.YES_OPTION) {

			Res res = null;

			for (int i = 0; i < selectedValuesList.size(); i++) {
				res = selectedValuesList.get(i);

				if (res.getGenero().equals("H")) {

					res.setTipo("HL");
					ResCRUD.update(res.getResID(), res);
					modeloDestete.removeElement(listDestete.getSelectedValue());
					System.out.println("actualizado");

				}

				if (res.getGenero().equals("M")) {

					res.setTipo("ML");
					ResCRUD.update(res.getResID(), res);
					modeloDestete.removeElement(listDestete.getSelectedValue());
					System.out.println("actualizado");

				}
			}

		}

	}

	public void purgadoLis() {

		List<Res> selectedValuesList = listPurgado.getSelectedValuesList();

		System.out.println(selectedValuesList.size() + "jeje");

		int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

		if (valor == JOptionPane.YES_OPTION) {

			CalendarioDialog calendario = new CalendarioDialog(null);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			String fecha = format.format(calendario.getCalendar().getDate());
			System.out.println("dsdasadd");
			Res res = null;
			Purgante purgante = null;
			HashMap<Res, Purgante> mapa = new HashMap<>();
			BarraProgresoDialog progreso = new BarraProgresoDialog(selectedValuesList.size());

			for (int i = 0; i < selectedValuesList.size(); i++) {

				System.out.println("dsadada");
				System.out.println(selectedValuesList.get(i).getResID());
				res = selectedValuesList.get(i);

				purgante = ResCRUD.selectPurgantes(res.getResID()).peek();
				mapa.put(res, purgante);
			}

			new Thread() {

				int value = 0;

				@Override
				public void run() {

					for (Map.Entry<Res, Purgante> entry : mapa.entrySet()) {

						ResCRUD.insertPurgante(entry.getKey().getResID(), entry.getValue().getNombre(), fecha);
						modeloPurgado.removeElement(listPurgado.getSelectedValue());
						value++;
						progreso.getProgreso().setValue(value);

					}

					progreso.dispose();

				}

			}.start();

		}

	}

	public void vacunasLis() {

		List<Res> selectedValuesList = listVacuna.getSelectedValuesList();

		System.out.println(selectedValuesList.size() + "jeje");

		int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

		if (valor == JOptionPane.YES_OPTION) {

			CalendarioDialog calendario = new CalendarioDialog(null);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			String fecha = format.format(calendario.getCalendar().getDate());
			System.out.println("dsdasadd");

			HashMap<Res, Vacuna> mapa = new HashMap<>();
			BarraProgresoDialog progreso = new BarraProgresoDialog(selectedValuesList.size());

			new Thread() {

				int value = 0;
				Res res = null;
				Vacuna vacuna = null;

				@Override
				public void run() {

//					for (Map.Entry<Res, Vacuna> entry : mapa.entrySet()) {
					for (int i = 0; i < selectedValuesList.size(); i++) {

						System.out.println("dsadada");
						System.out.println(selectedValuesList.get(i).getResID());
						res = selectedValuesList.get(i);

						vacuna = ResCRUD.selectVacunas(res.getResID()).peek();
//						mapa.put(res, vacuna);

						ResCRUD.insertVacuna(res.getResID(), vacuna.getNombre(), fecha);
						modeloVacuna.removeElement(listVacuna.getSelectedValue());
						value++;
						progreso.getProgreso().setValue(value);
					}
//					}

					progreso.dispose();

				}

			}.start();

		}

	}

	public void listenersPrincipales() {

		listVacuna.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) {

					vacunasLis();
				}

			}
		});

		listPurgado.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == MouseEvent.BUTTON3) {

					purgadoLis();
				}

			}

		});

		listDestete.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == MouseEvent.BUTTON3) {

					desteteLis();
				}
			}
		});

		listPartos.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == MouseEvent.BUTTON3) {

					partoLis();
				}

			}

		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				ventana.refreshTable();

			}

		});

	}

}
