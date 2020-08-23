package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;

import com.sun.javafx.collections.SetListenerHelper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import db.ResCRUD;
import model.Res;

import java.awt.Color;
import java.awt.Dimension;

public class NotificacionesPanel extends JDialog {

	private final JLabel label = new JLabel("");
	private PotrerosPanel ventana;
	private JButton btnRegresar;
	private JButton btnDestete;
	private JButton btnPartos;
	private JButton btnPurgado;
	private JList<String> list;
	private JScrollPane listScroller;
	DefaultListModel<String> modelo;

	public NotificacionesPanel(PotrerosPanel ventana) {

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

		list = new JList();
		modelo = new DefaultListModel<String>();
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
			modelo.clear();


			ArrayList<Res> reses = ResCRUD.reporteDestete();
			System.out.println(reses.size());
			int posicion = 0;

			String[] values = new String[reses.size()];

			for (int i = 0; i < reses.size(); i++) {
				values[i] = reses.get(i).toString();
				posicion = i;
				modelo.add(i, values[i]);
			}

			list.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					JList list = (JList<String>) evt.getSource();
					if (evt.getClickCount() == 2) {

						// Double-click detected
						System.out.println(list.getSelectedIndex() + "oprimio");

						int numero = list.getSelectedIndex();
						if (numero != -1) {
							Res res = reses.get(list.getSelectedIndex());

							int valor = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de eliminar esta notificación?");

							if (valor == JOptionPane.OK_OPTION) {

								if (res.getGenero().equals("H")) {

									res.setTipo("HL");
									ResCRUD.update(res.getResID(), res);

								}

								if (res.getGenero().equals("M")) {

									res.setTipo("ML");
									ResCRUD.update(res.getResID(), res);

								}

								if (numero != -1) {

									modelo.removeElement(list.getSelectedValue());
								}

								ventana.refreshTable();
							}

						}

					}
				}

			});

		});

		btnPartos.addActionListener(e -> {

			// list.removeAll();
		//	modelo.removeAllElements();
			modelo.clear();

			ArrayList<Res> reses = ResCRUD.reportePartos();
			System.out.println(reses.size());
			int posicion = 0;

			String[] values = new String[reses.size()];

			for (int i = 0; i < reses.size(); i++) {
				values[i] = reses.get(i).toPartos();
				posicion = i;
				modelo.add(i, values[i]);
			}

			list.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					JList list = (JList<String>) evt.getSource();
					if (evt.getClickCount() == 2) {

						// Double-click detected
						System.out.println(list.getSelectedIndex() + "oprimio");

						int numero = list.getSelectedIndex();
						if (numero != -1) {
							Res res = reses.get(list.getSelectedIndex());

							int valor = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de eliminar esta notificación?");

							if (valor == JOptionPane.OK_OPTION) {

								if (res.getGenero().equals("H")) {

									res.setTipo("VP");
									ResCRUD.update(res.getResID(), res);

								}

								if (numero != -1) {

									modelo.removeElement(list.getSelectedValue());
								}

								ventana.refreshTable();

							}

						}

					}
				}

			});

			System.out.println(posicion + "posicion");

		});

		btnPurgado.addActionListener(e -> {
			modelo.clear();


		});

		btnRegresar.addActionListener(e -> {

			dispose();

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
