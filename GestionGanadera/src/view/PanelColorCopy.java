package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColorCopy extends JPanel {

	public PanelColorCopy() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("COPY"), 8, -24, null);

		repaint();
	}

}
