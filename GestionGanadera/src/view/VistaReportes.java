package view;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VistaReportes extends JDialog{
	
	
	
	
	public VistaReportes() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		setSize(720, 400);
		
		JPanel panelArriba = new JPanel();
		getContentPane().add(panelArriba, BorderLayout.CENTER);
		
		JPanel Tabla = new JPanel();
		getContentPane().add(Tabla, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Reporte de la res #435");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Tabla.add(lblNewLabel);
		
		JPanel botones = new JPanel();
		getContentPane().add(botones, BorderLayout.SOUTH);
		botones.setLayout(new GridLayout(1,10));
		
		JLabel lblNewLabel_1 = new JLabel("   ");
		botones.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("   ");
		botones.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Regresar");
		botones.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("   ");
		botones.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Editar");
		botones.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("   ");
		botones.add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("Guardar");
		botones.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("   ");
		botones.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("   ");
		botones.add(lblNewLabel_6);
	}

}
