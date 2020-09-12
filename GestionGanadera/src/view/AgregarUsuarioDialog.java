package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.RemoteCRUD;
import db.UsuarioCRUD;
import model.Usuario;
import tools.FileManager;
import tools.InternetAvailabilityChecker;
import tools.SystemMotherBoardNumber;

public class AgregarUsuarioDialog extends JDialog {

	private RemoteCRUD remote;
	private JTextField txtusuario;
	private JPasswordField txtpassword;
	private JButton guardar;
	private JTextField nombrefinca;
	private JTextField correo;

	public AgregarUsuarioDialog() {

		setTitle("Crear Usuario");

		getContentPane().setLayout(new BorderLayout(0, 0));

		setSize(530, 330);
		setIconImage(FileManager.imagenes.get("USUARIO"));
		setResizable(false);

		setLocationRelativeTo(null);

		remote = new RemoteCRUD();
		Components();
		listeners();

		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public void Components() {
		PanelColor panel = new PanelColor();
		panel.setBackground(new Color(210, 180, 140, 100));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel10 = new JPanel();
		panel10.setBackground(new Color(210, 180, 140, 140));
		JPanel panel11 = new JPanel();
		panel11.setBackground(new Color(210, 180, 140, 140));

		JLabel espacio2 = new JLabel("                             ");
		espacio2.setBackground(new Color(210, 180, 140, 50));
		panel10.add(espacio2);
		panel.add(panel10, BorderLayout.WEST);

		JLabel espacio1 = new JLabel("                             ");
		espacio1.setBackground(new Color(210, 180, 140, 50));
		panel11.add(espacio1);
		panel.add(panel11, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(210, 180, 140, 140));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(13, 1));

		JLabel txtUsuario = new JLabel("Digite su nombre de usuario");
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtUsuario);

		txtusuario = new JTextField();
		txtusuario.setBackground(new Color(230, 230, 250, 100));
		txtusuario.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtusuario.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(txtusuario);
		txtusuario.setColumns(10);

		JLabel password = new JLabel("");
		panel_1.add(password);

		JLabel lblNewLabel_3 = new JLabel("Digite su correo electr\u00f3nico");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblNewLabel_3);

		correo = new JTextField();
		correo.setBackground(new Color(230, 230, 250, 100));
		panel_1.add(correo);
		correo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");
		panel_1.add(lblNewLabel_4);

		JLabel espacio3 = new JLabel("Digite su contrase\u00F1a");
		espacio3.setFont(new Font("Tahoma", Font.BOLD, 16));
		espacio3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(espacio3);

		txtpassword = new JPasswordField();
		txtpassword.setBackground(new Color(230, 230, 250, 100));
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
		nombrefinca.setBackground(new Color(230, 230, 250, 100));
		panel_1.add(nombrefinca);
		nombrefinca.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		nombrefinca.setHorizontalAlignment(SwingConstants.CENTER);
		nombrefinca.setText("");
		nombrefinca.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		panel_1.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 180, 140, 0));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 3));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);

		guardar = new JButton("Guardar");
		guardar.setBackground(new Color(230, 230, 250, 100));
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

			Usuario user = new Usuario(txtusuario.getText(), "", "", pass,
					SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber(), correo.getText(), fechalimite());

			try {
				if (InternetAvailabilityChecker.isInternetAvailable()) {
					remote.registrarUsuario(user);
					UsuarioCRUD.insert(user);
				} else {

					JOptionPane.showMessageDialog(null, "No tienes una conexion a internet", "Error conexion",
							JOptionPane.ERROR_MESSAGE);
					
					System.exit(0);

				}
			} catch (HeadlessException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			dispose();

			RegistroPanel.compruebaUser();

		});

	}

	public static String fechalimite() {

		String fechaLimite = "";

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		int nuevoanio = anio + 1;

		String fecha_Convertida = dia + "/" + mes + "/" + nuevoanio;

		fechaLimite = fecha_Convertida;

		return fechaLimite;
	}

	public JTextField getNombrefinca() {
		return nombrefinca;
	}

	public void setNombrefinca(JTextField nombrefinca) {
		this.nombrefinca = nombrefinca;
	}

	public JTextField getCorreo() {
		return correo;
	}

	public void setCorreo(JTextField correo) {
		this.correo = correo;
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
