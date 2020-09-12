package view;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import db.SQLConnection;
import db.UsuarioCRUD;
import model.Usuario;
import tools.FileManager;
import tools.SystemMotherBoardNumber;

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
				
				if(comprobarSerial()) {
				inicio = new InicioPanel(this);
				add(inicio, BorderLayout.CENTER);
				}else {
					
					JOptionPane.showMessageDialog(null, "Esta licencia no esta habilitada para esta equipo","Error", JOptionPane.ERROR_MESSAGE);
					
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
		
		if(user.getSerialNumber().equalsIgnoreCase(serie)) {
			
			comprobar = true;
			
		}
		
		return comprobar;
		
	}
	
	public void comprobarLicencia() {
		
		
		
		
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
