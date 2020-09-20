package view;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import db.RemoteCRUD;
import db.SQLConnection;
import db.UsuarioCRUD;
import model.Usuario;
import tools.FileManager;
import tools.InternetAvailabilityChecker;
import tools.SystemMotherBoardNumber;
import tools.Utils;

public class VentanaPrincipal extends JFrame {

	public static final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	public static final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getHeight();

	private RegistroPanel registro;
	private InicioPanel inicio;

	public VentanaPrincipal() {

		FileManager.cargarRecursos();
		setTitle("Gesti\u00F3n Ganadera");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		setIconImage(FileManager.imagenes.get("ICONO"));

		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		comprobarpanel();

	}

	public void comprobarpanel() {

		if (FileManager.directoryProjectExists()) {

			if (UsuarioCRUD.select().size() == 1) {

				if (comprobarLicencia()) {
					System.out.println("Aqui comprueba Licencia");
					if (comprobarSerial()) {
						System.out.println("Aqui comprueba Serial");
						inicio = new InicioPanel(this);
						add(inicio, BorderLayout.CENTER);
					} else {

						JOptionPane.showMessageDialog(null, "Esta licencia no esta habilitada para esta equipo",
								"Error", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
				}
			} else {

				registro = new RegistroPanel(this);
				add(registro, BorderLayout.CENTER);

			}

		} else {

			SQLConnection.getInstance().createDatabase();
			registro = new RegistroPanel(this);
			add(registro, BorderLayout.CENTER);

		}
	}

	public boolean comprobarSerial() {

		boolean comprobar = false;

		Usuario user = UsuarioCRUD.select().get(0);

		String serie = SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber();

		System.out.println(serie);
		System.out.println(user.toString());

		if (user.getSerialNumber().equalsIgnoreCase(serie)) {

			comprobar = true;

		}

		return comprobar;

	}

	public boolean comprobarLicencia() {

		boolean vigente = false;

		Usuario user = UsuarioCRUD.select().get(0);
        RemoteCRUD remote= new RemoteCRUD(user);

		Date fechaLimite = Utils.convertDateToLong(user.getFechaLimite());
		Date fechaActual = Utils.convertDateToLong(fecha_sistema());

		if (fechaActual.compareTo(fechaLimite) >= 0) {

            String licencia= JOptionPane.showInputDialog(null, "Tu licencia se ha vencido\nComuniquese con el administrador", "Licencia vencida", JOptionPane.WARNING_MESSAGE);
			
            try {
				if (InternetAvailabilityChecker.isInternetAvailable()) {

					if (licencia != null && !licencia.equals("")) {
						remote.renovarLicenciaFinal(licencia);
						
						
					} else {

						JOptionPane.showMessageDialog(null, "Error con la licencia");

					}
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
		}else {
			vigente=true;
		}

		return vigente;
		
		

	}


	public static String fecha_sistema() {

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "/" + mes + "/" + anio;

		return fecha_Convertida;
	}

	public RegistroPanel getRegistro() {
		return registro;
	}

	public InicioPanel getInicio() {
		return inicio;
	}

	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
