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
import java.util.List;

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

public class NotificacionesPanel extends JDialog {

	private final JLabel label = new JLabel("");
	private PotrerosPanel ventana;
	private JButton btnRegresar;
	private JButton btnDestete;
	private JButton btnPartos;
	private JButton btnPurgado;
	private JList<Res> list;
	private JScrollPane listScroller;
	DefaultListModel<Res> modelo;

	public NotificacionesPanel(PotrerosPanel ventana) {

		setTitle("Notificaciones");

		setLayout(new BorderLayout(0, 0));

		setSize(605, 452);
		setLocationRelativeTo(null);

		this.ventana = ventana;
		Components();
		listeners();
		setVisible(true);

	}

	public void Components() {
		JLabel lblNewLabel_2 = new JLabel("Eventos Proximos");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_2, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 3));
		panel_1.add(label);

		btnRegresar = new JButton("Regresar");
		panel_1.add(btnRegresar);

		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(10, 1));

		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);

		btnPartos = new JButton("Partos");
		panel.add(btnPartos);

		JLabel lblNewLabel_4 = new JLabel("");
		panel.add(lblNewLabel_4);

		btnDestete = new JButton("Destete");
		panel.add(btnDestete);

		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);

		btnPurgado = new JButton("Purgado");
		panel.add(btnPurgado);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		add(panel_2, BorderLayout.EAST);

		list = new JList<Res>();
		modelo = new DefaultListModel<Res>();
		list.setModel(modelo);

		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(450, 300));

		panel_2.add(listScroller, BorderLayout.CENTER);

		JLabel lblNewLabel_6 = new JLabel("      ");
		panel_2.add(lblNewLabel_6, BorderLayout.EAST);

		JLabel lblNewLabel_7 = new JLabel("      ");
		panel_2.add(lblNewLabel_7, BorderLayout.NORTH);

	}

	public void listeners() {

		btnDestete.addActionListener(e -> {

			destetar();

		});

		btnPartos.addActionListener(e -> {

			partos();

		});

		btnPurgado.addActionListener(e -> {

			purgado();

		});

		btnRegresar.addActionListener(e -> {

			ventana.setNotificaciones(null);
			dispose();

		});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {

				ventana.setNotificaciones(null);
				dispose();

			}

		});

	}

	public void purgado() {

		modelo.clear();

		ArrayList<Res> reses = ResCRUD.reportePurgado();
		System.out.println(reses.size());

		list.setCellRenderer(new RenderizadoPurgado());

		for (int i = 0; i < reses.size(); i++) {

			modelo.addElement(reses.get(i));

		}

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getButton() == MouseEvent.BUTTON3) {

					List<Res> selectedValuesList = list.getSelectedValuesList();

					System.out.println(selectedValuesList.size() + "jeje");

					int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

					if (valor == JOptionPane.YES_OPTION) {

						CalendarioDialog calendario = new CalendarioDialog(null);

						calendario.getBtnSeleccionarFecha().addActionListener(e -> {

							Res res = null;
							Purgante purgante = null;

							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

							String fecha = format.format(calendario.getCalendar().getDate());
							for (int i = 0; i < selectedValuesList.size(); i++) {

								res = selectedValuesList.get(i);
								purgante = ResCRUD.selectPurgantes(res.getResID()).pop();

								ResCRUD.insertPurgante(res.getResID(), purgante.getNombre(), fecha);
								modelo.removeElement(list.getSelectedValue());

							}

						});

						// ResCRUD.insertPurgante(res.getResID(),purgante.getNombre(),
						// ResCRUD.fecha_sistema().toString() );

						// System.out.println("actualizado");

						// ResCRUD.update(res.getResID(), res);
						// modelo.removeElement(list.getSelectedValue());

						ventana.refreshTable();
					}

				}

			}

		});

	}

	public void destetar() {

		modelo.clear();

		list.setCellRenderer(new RenderizadoDestete());

		ArrayList<Res> reses = ResCRUD.reporteDestete();
		System.out.println(reses.size());

		for (int i = 0; i < reses.size(); i++) {

			modelo.addElement(reses.get(i));

		}

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getButton() == MouseEvent.BUTTON3) {

					List<Res> selectedValuesList = list.getSelectedValuesList();

					int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

					if (valor == JOptionPane.YES_OPTION) {

						Res res = null;

						for (int i = 0; i < selectedValuesList.size(); i++) {
							res = selectedValuesList.get(i);

							if (res.getGenero().equals("H")) {

								res.setTipo("HL");
								ResCRUD.update(res.getResID(), res);
								modelo.removeElement(list.getSelectedValue());
								System.out.println("actualizado");

							}

							if (res.getGenero().equals("M")) {

								res.setTipo("ML");
								ResCRUD.update(res.getResID(), res);
								modelo.removeElement(list.getSelectedValue());
								System.out.println("actualizado");

							}
						}

						ventana.refreshTable();
					}

				}

			}

		});

	}

	public void partos() {

		// list.removeAll();
		// modelo.removeAllElements();
		modelo.clear();

		list.setCellRenderer(new RenderizadoPartos());

		ArrayList<Res> reses = ResCRUD.reportePartos();
		System.out.println(reses.size() + "aqui");

		for (int i = 0; i < reses.size(); i++) {

			modelo.addElement(reses.get(i));
		}

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getButton() == MouseEvent.BUTTON3) {

					List<Res> selectedValuesList = list.getSelectedValuesList();

					// Double-click detected
					System.out.println(list.getSelectedIndex() + "oprimio");

					int numero = list.getSelectedIndex();

					int valor = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta notificación?");

					if (valor == JOptionPane.OK_OPTION) {
						Res res = null;

						for (int i = 0; i < selectedValuesList.size(); i++) {

							res = selectedValuesList.get(i);

							if (res.getGenero().equals("H")) {

								res.setTipo("VP");
								ResCRUD.update(res.getResID(), res);
								modelo.removeElement(list.getSelectedValue());

							}
						}

						ventana.refreshTable();

					}

				}

			}

		});

	}

	public JButton btnDestete() {
		return btnDestete;
	}

	public JButton btnPartos() {
		return btnPartos;
	}

	public JButton BtnPurgado() {
		return btnPurgado;
	}

	public JList lista() {
		return list;
	}

}
