package view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Res;

public class RenderizadoVacuna extends JLabel implements ListCellRenderer<Res> {

	// This is the only method defined by ListCellRenderer.
	// We just reconfigure the JLabel each time we're called.

	@Override
	public Component getListCellRendererComponent(JList<? extends Res> list, Res value, int index, boolean isSelected,
			boolean cellHasFocus) {

		DefaultListCellRenderer default2 = new DefaultListCellRenderer();
		JLabel label = (JLabel) default2.getListCellRendererComponent(list, value.toVacunas(), index, isSelected,
				cellHasFocus);

		return label;
	}

}
