package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import tools.FileManager;

public class PanelColorAgregar extends JPanel {

	public PanelColorAgregar() {

		setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		
		g.drawImage(FileManager.imagenes.get("LLANO"), 0, 0, null);

		repaint();
	}

}
