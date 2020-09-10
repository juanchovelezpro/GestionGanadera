package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColor extends JPanel {

	public PanelColor() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("CAMPO3"), 0, 0, null);

		repaint();
	}

}
