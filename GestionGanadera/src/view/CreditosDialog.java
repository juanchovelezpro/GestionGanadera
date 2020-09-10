package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class CreditosDialog extends JDialog {

	private JButton btnRegresar;

	public CreditosDialog() {

		setSize(450, 300);

		setTitle("Cr\u00E9ditos");

		setLocationRelativeTo(null);
		setResizable(false);

		setModalityType(ModalityType.APPLICATION_MODAL);

		components();

		setVisible(true);

	}

	public void components() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(0, 0, 51));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		PanelColorCopy panel_1 = new PanelColorCopy();
		panel_1.setBackground(new Color(0, 0, 51, 0));
		tabbedPane.addTab("Recursos", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel, BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1, BorderLayout.EAST);

		PanelColorCopy panel_2 = new PanelColorCopy();
		panel_2.setBackground(new Color(0, 0, 51, 0));
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(10, 1));

		JLabel lblNewLabel_14 = new JLabel("");
		panel_2.add(lblNewLabel_14);

		JLabel lblNewLabel_23 = new JLabel("Todas las imagenes utilizadas en este software ");
		lblNewLabel_23.setForeground(Color.WHITE);
		lblNewLabel_23.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_23);

		JLabel lblNewLabel_21 = new JLabel("fueron tomadas previamente de la pagina web");
		lblNewLabel_21.setForeground(Color.WHITE);
		lblNewLabel_21.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_21);

		JLabel lblNewLabel_20 = new JLabel("www.flaticon.es, desarrolladas por los autores:");
		lblNewLabel_20.setForeground(Color.WHITE);
		lblNewLabel_20.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_20);

		JLabel lblNewLabel_19 = new JLabel("Freepik, Pixelmeetup, Flat Icons, Pixel perfect,");
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_19);

		JLabel lblNewLabel_18 = new JLabel("Those icons, Becris, y surang.   ");
		lblNewLabel_18.setForeground(Color.WHITE);
		lblNewLabel_18.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_18);

		JLabel lblNewLabel_22 = new JLabel("");
		panel_2.add(lblNewLabel_22);
		JLabel lblNewLabel_17 = new JLabel("");
		panel_2.add(lblNewLabel_17);

		JLabel lblNewLabel_16 = new JLabel("");
		panel_2.add(lblNewLabel_16);

		JLabel lblNewLabel_15 = new JLabel("");
		panel_2.add(lblNewLabel_15);

		PanelColorDeveloper panel = new PanelColorDeveloper();
		panel.setBackground(new Color(0, 0, 51, 0));
		tabbedPane.addTab("Developers", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2, BorderLayout.WEST);

		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3, BorderLayout.EAST);

		PanelColorDeveloper panel_3 = new PanelColorDeveloper();
		panel_3.setBackground(new Color(0, 0, 51, 0));
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(10, 1));

		JLabel lblNewLabel_4 = new JLabel("");
		panel_3.add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("Este software fue desarrollado por:");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_7);

		JLabel lblNewLabel_13 = new JLabel("Juan David Paz - Juan Camilo Velez");
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_13);

		JLabel lblNewLabel_12 = new JLabel("");
		panel_3.add(lblNewLabel_12);

		JLabel lblNewLabel_8 = new JLabel("Soporte en:");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_8);

		JLabel lblNewLabel_11 = new JLabel("juandoradou@hotmail.com ");
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_11);

		JLabel lblNewLabel_9 = new JLabel("velezolaya2012@hotmail.com\n");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_9);

		JLabel lblNewLabel_6 = new JLabel("");
		panel_3.add(lblNewLabel_6);

		JLabel lblNewLabel_10 = new JLabel("Cali, Colombia.");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_10);

		JLabel lblNewLabel_5 = new JLabel("");
		panel_3.add(lblNewLabel_5);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 0, 51, 0));
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(1, 3));

		btnRegresar = new JButton("Regresar");

		JLabel label5 = new JLabel("");
		panel_4.add(label5);

		JLabel lblNewLabel_24 = new JLabel("");
		panel_4.add(lblNewLabel_24);

	}

	public void listeners() {

		btnRegresar.addActionListener(e -> {

			dispose();

		});

	}

}
