package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColorEstadisticas extends JPanel {

	public PanelColorEstadisticas() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("CAMPONO"), 0, 0, null);

		g.drawImage(FileManager.imagenes.get("ESTADISTICA"), 385, 140, null);

		repaint();
	}

}
