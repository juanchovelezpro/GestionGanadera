package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class VentanaPrincipal extends JFrame {

	public static final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	public static final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getHeight();

	private RegistroPanel registro;
	private InicioPanel inicio;
	private PotrerosPanel potreros;
	private ReportePanel reportes;

	public VentanaPrincipal() {

		setTitle("Gestion Ganadera");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setLocationRelativeTo(null);

		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		registro = new RegistroPanel(this);
		inicio = new InicioPanel(this);
		potreros = new PotrerosPanel(this);
		reportes = new ReportePanel(this);

		add(registro, BorderLayout.CENTER);

	}
	
	

	public ReportePanel getReportes() {
		return reportes;
	}



	public void setReportes(ReportePanel reportes) {
		this.reportes = reportes;
	}



	public RegistroPanel getRegistro() {
		return registro;
	}

	public InicioPanel getInicio() {
		return inicio;
	}

	public PotrerosPanel getPotreros() {
		return potreros;
	}
	
	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
