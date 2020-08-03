package view;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class AgregarEditarVaca extends JDialog {
	public AgregarEditarVaca() {
		setTitle("Agregar/Editar Vaca");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1,2));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
