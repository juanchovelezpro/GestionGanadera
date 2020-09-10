package view;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

import db.SQLConnection;
import db.UsuarioCRUD;
import tools.FileManager;

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
				inicio = new InicioPanel(this);
				// ResCRUD.actualizarTipo();
				add(inicio, BorderLayout.CENTER);

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
