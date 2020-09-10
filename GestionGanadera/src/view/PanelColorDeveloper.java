package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColorDeveloper extends JPanel {

	public PanelColorDeveloper() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

		super.paintComponent(g);

		g.drawImage(FileManager.imagenes.get("DEVELOPER"), 0, 0, null);

		repaint();
	}

}
