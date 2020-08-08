package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

import db.UsuarioCRUD;
import model.Gestion;
import tools.FileManager;

public class VentanaPrincipal extends JFrame {

	public static final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	public static final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getHeight();

	private Gestion gestion;
	private RegistroPanel registro;
	private InicioPanel inicio;

	public VentanaPrincipal() {

		setTitle("Gestion Ganadera");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

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
				add(inicio, BorderLayout.CENTER);

			}

		} else {
			// CREAR BASE DE DATOS
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
