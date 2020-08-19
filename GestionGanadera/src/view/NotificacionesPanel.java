package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;

import db.ResCRUD;

import java.awt.Color;
import java.awt.Dimension;

public class NotificacionesPanel extends JPanel {
	
	
	private final JLabel label = new JLabel("");
	private PotrerosPanel ventana;
	private JButton btnRegresar;
	private JButton btnDestete;
	private JButton btnPartos;
	private JButton btnVacunas;
	private JButton btnPurgado;
	private JList list;
	
	public NotificacionesPanel(PotrerosPanel ventana) {
		setLayout(new BorderLayout(0, 0));
		
		setSize(605,452);
		
		this.ventana=ventana;
		Components();
		listeners();
	
	}
	
	public void Components() {
		JLabel lblNewLabel_2 = new JLabel("Eventos Proximos");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1,3));
		panel_1.add(label);
		
		btnRegresar = new JButton("Regresar");
		panel_1.add(btnRegresar);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(10,1));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		btnDestete = new JButton("Destete");
		panel.add(btnDestete);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		btnPartos = new JButton("Partos");
		panel.add(btnPartos);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel.add(lblNewLabel_4);
		
		btnVacunas = new JButton("Vacunas");
		panel.add(btnVacunas);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		btnPurgado = new JButton("Purgado");
		panel.add(btnPurgado);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		add(panel_2, BorderLayout.EAST);
		
		list = new JList();
		
	
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(450, 300));
			
		panel_2.add(listScroller, BorderLayout.CENTER);
		

		JLabel lblNewLabel_6 = new JLabel("      ");
		panel_2.add(lblNewLabel_6, BorderLayout.EAST);
		
		JLabel lblNewLabel_7 = new JLabel("      ");
		panel_2.add(lblNewLabel_7, BorderLayout.NORTH);
		
	}

	public void listeners() {
		
		btnDestete.addActionListener(e -> {
			
			ArrayList<String > mensaje =ResCRUD.desteteMensaje();
			System.out.println(mensaje.size() + "asdasdd");
			
			String[] values = new String[mensaje.size()];
			
			for (int i = 0; i < mensaje.size(); i++) {
				values[i] = mensaje.get(i);
			}
			
			list.setModel(new AbstractListModel() {
			
				
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});

		});
		
		btnPartos.addActionListener(e -> {
			

			list.removeAll();
			ArrayList<String > mensaje =ResCRUD.desteteMensaje();
			System.out.println(mensaje.size() + "asdasdd");
			
			String[] values = new String[mensaje.size()];
			
			for (int i = 0; i < mensaje.size(); i++) {
				values[i] = mensaje.get(i);
			}
			
			list.setModel(new AbstractListModel() {
			
				
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});


		});
		
		btnVacunas.addActionListener(e -> {

		});
		
		btnPurgado.addActionListener(e -> {

		});
		
		btnRegresar.addActionListener(e -> {

		});

	}
	public JButton btnDestete() {
		return btnDestete;
	}
	public JButton btnPartos() {
		return btnPartos;
	}
	public JButton btnVacunas() {
		return btnVacunas;
	}
	public JButton BtnPurgado() {
		return btnPurgado;
	}
	public JList lista() {
		return list;
	}
}
