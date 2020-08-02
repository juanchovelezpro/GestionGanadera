package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class VentanaPrincipal extends JFrame{

	
	public static final int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	public static final int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getHeight();
		
	
	private RegistroPanel registro;
	
	public VentanaPrincipal() {
	
	setTitle("Gestion Ganadera");
	setSize(1280,768);
	setPreferredSize(new Dimension(1280,768));
	setLayout(new BorderLayout());
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setExtendedState(MAXIMIZED_BOTH);
	setLocationRelativeTo(null);

	try {

		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

	} catch (Exception ex) {

		ex.printStackTrace();

	}
	
	
	registro = new RegistroPanel();
	
	
	add(registro, BorderLayout.CENTER);
	
	
	
	}
	
}
