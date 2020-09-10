package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColorGrafica extends JPanel {

	public PanelColorGrafica() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("VAQUITA"), 0, 0, null);

		repaint();
	}

}
