package view;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.SQLConnection;
import db.UsuarioCRUD;
import model.Usuario;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Label;

public class AgregarUsuario extends JDialog {
	private JTextField txtusuario;
	private JPasswordField txtpassword;
	private JButton guardar;
	private RegistroPanel ventana;
	private JTextField nombrefinca;

	public AgregarUsuario() {
		
		setTitle("Crear Usuario");

		getContentPane().setLayout(new BorderLayout(0, 0));

		setSize(530, 330);

		setLocationRelativeTo(null);

		setVisible(true);

		// this.ventana = new RegistroPanel(ventana);

		Components();
		listeners();

	}

	public void Components() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel espacio2 = new JLabel("                             ");
		panel.add(espacio2, BorderLayout.WEST);

		JLabel espacio1 = new JLabel("                             ");
		panel.add(espacio1, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(10, 1));

		JLabel txtUsuario = new JLabel("Digite su nombre de usuario");
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtUsuario);

		txtusuario = new JTextField();
		txtusuario.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtusuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtusuario);
		txtusuario.setColumns(10);

		JLabel password = new JLabel("");
		panel_1.add(password);

		JLabel espacio3 = new JLabel("Digite su contrase\u00F1a");
		espacio3.setFont(new Font("Tahoma", Font.BOLD, 16));
		espacio3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(espacio3);

		txtpassword = new JPasswordField();
		txtpassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setText("");
		panel_1.add(txtpassword);
		txtpassword.setColumns(10);

		JLabel espacio4 = new JLabel("");
		panel_1.add(espacio4);

		JLabel espacio5 = new JLabel("Digite el nombre de la finca");
		espacio5.setFont(new Font("Tahoma", Font.BOLD, 16));
		espacio5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(espacio5);

		nombrefinca = new JTextField();
		panel_1.add(nombrefinca);
		nombrefinca.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		nombrefinca.setHorizontalAlignment(SwingConstants.CENTER);
		nombrefinca.setText("");
		nombrefinca.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		panel_1.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 3));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);

		guardar = new JButton("Guardar");
		panel_2.add(guardar);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);

	}

	public void listeners() {

		
		guardar.addActionListener(e -> {

			char[] letras = txtpassword.getPassword();
			String pass = "";

			for (int i = 0; i < letras.length; i++) {

				pass += letras[i];

			}

			Usuario user = new Usuario(txtusuario.getText(), "", nombrefinca.getText(), pass);
			UsuarioCRUD.insert(user);

			dispose();

			RegistroPanel.compruebaUser();

		});

	}

	public JTextField getTxtusuario() {
		return txtusuario;
	}

	public void setTxtusuario(JTextField txtusuario) {
		this.txtusuario = txtusuario;
	}

	public JTextField getTxtpassword() {
		return txtpassword;
	}

	public void setTxtpassword(JPasswordField txtpassword) {
		this.txtpassword = txtpassword;
	}

	public JButton getGuardar() {
		return guardar;
	}

	public void setGuardar(JButton guardar) {
		this.guardar = guardar;
	}

}
