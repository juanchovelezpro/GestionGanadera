package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import tools.FileManager;

public class BarraProgreso extends JDialog {

	private JProgressBar progreso;

	public BarraProgreso(int carga) {

		setTitle("Proceso");
		setIconImage(FileManager.imagenes.get("VACA"));
		getContentPane().setLayout(new GridLayout(1, 1));
		setSize(600, 70);
		setLocationRelativeTo(null);

		UIManager.put("nimbusOrange", Color.GREEN.brighter());

		progreso = new JProgressBar(0, carga);
		progreso.setSize(600, 70);
		progreso.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progreso.setString("Cargando...");
		progreso.setStringPainted(true);
		progreso.setIndeterminate(false);
		progreso.setBackground(Color.GREEN);

		getContentPane().add(progreso);
		setAlwaysOnTop(true);
		setVisible(true);

	}

	public JProgressBar getProgreso() {
		return progreso;
	}

	public void setProgreso(JProgressBar progreso) {
		this.progreso = progreso;
	}

}
