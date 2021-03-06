package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.UsuarioCRUD;
import model.Usuario;
import tools.FileManager;

public class RegistroPanel extends JPanel {

	private JTextField txtNombreUsuario;
	private JPasswordField txtPassword;
	private JButton btnEntrar;
	private static JButton btnCrearUsuario;
	private VentanaPrincipal ventana;

	public RegistroPanel(VentanaPrincipal ventana) {

		this.ventana = ventana;

		ventana.setSize(800, 600);
		ventana.setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		setComponents();
		listeners();

		compruebaUser();

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("CAMPO2"), 0, 0, null);

		repaint();
	}

	public VentanaPrincipal getVentana() {

		return ventana;

	}

	public void setComponents() {

		JPanel panel = new JPanel();
		panel.setBackground(new Color(222, 184, 135, 100));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 3));

		JPanel panelprueba = new JPanel();
		panel.setBackground(new Color(222, 184, 135, 100));
		add(panelprueba, BorderLayout.PAGE_END);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(222, 184, 135, 100));
		panel.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(222, 184, 135, 100));
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(16, 1));

		JLabel lblNewLabel_2 = new JLabel("");
		panel_2.add(lblNewLabel_2);

		JLabel lblApp = new JLabel("Gesti\u00F3n Ganadera");
		lblApp.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblApp.setForeground(Color.BLACK);
		panel_2.add(lblApp);

		JLabel lblNewLabel_3 = new JLabel("");
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("");
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBackground(new Color(176, 224, 230, 100));
		txtNombreUsuario.setForeground(new Color(0, 0, 0));
		txtNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Contrase\u00F1a");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_7);

		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(176, 224, 230, 100));
		txtPassword.setForeground(new Color(0, 0, 0));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(txtPassword);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(176, 196, 222, 100));
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(new Color(176, 196, 222));
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_4.add(btnEntrar);

		JLabel lblNewLabel_8 = new JLabel("");
		panel_2.add(lblNewLabel_8);

		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.setBackground(new Color(176, 196, 222));
		btnCrearUsuario.setForeground(new Color(0, 0, 0));
		btnCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(btnCrearUsuario);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(222, 184, 135, 100));
		panel.add(panel_3);

	}

	public static void compruebaUser() {

		if (UsuarioCRUD.select().size() == 1) {

			btnCrearUsuario.setEnabled(false);
		}
	}

	public void listeners() {

		btnEntrar.addActionListener(e -> {

			Usuario usuarioActual = null;

			char[] letrass = txtPassword.getPassword();
			String passActual = "";

			for (int i = 0; i < letrass.length; i++) {

				passActual += letrass[i];

			}

			if (UsuarioCRUD.select().size() == 0) {

				JOptionPane.showMessageDialog(null, "Cree un usuario por favor");

			}

			if (UsuarioCRUD.select().size() != 0) {

				usuarioActual = UsuarioCRUD.select().get(0);

				if (txtNombreUsuario.getText().equals("") || passActual.equals("")) {

					JOptionPane.showMessageDialog(null, "Ingrese un usuario y contrase\u00F1a");

				}

				if (txtNombreUsuario.getText().equals(usuarioActual.getNombre())
						&& passActual.equals(usuarioActual.getPassword())) {

					ventana.remove(this);
					InicioPanel inicio = new InicioPanel(ventana);
					ventana.add(inicio);
					ventana.setSize(800, 400);
					ventana.setResizable(false);
					ventana.setLocationRelativeTo(null);
					ventana.refresh();

				} else if ((!txtNombreUsuario.getText().equals("") || !passActual.equals(""))
						&& (!txtNombreUsuario.getText().equals(usuarioActual.getNombre())
								|| !passActual.equals(usuarioActual.getPassword()))) {

					JOptionPane.showMessageDialog(null, "Usuario o contrase\u00F1a incorrectas");
				}

			}

		});

		btnCrearUsuario.addActionListener(e -> {

			AgregarUsuarioDialog agregar = new AgregarUsuarioDialog();

		});

	}

	public JButton getBtnEntrar() {
		return btnEntrar;
	}

	public JButton getBtnCrearUsuario() {
		return btnCrearUsuario;
	}
}
