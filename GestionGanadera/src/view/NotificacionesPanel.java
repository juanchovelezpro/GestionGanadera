package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;

public class NotificacionesPanel extends JPanel {
	private final JLabel label = new JLabel("");
	public NotificacionesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		setSize(600,450);
		
		JLabel lblNewLabel_2 = new JLabel("Eventos Proximos");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1,3));
		panel_1.add(label);
		
		JButton btnNewButton = new JButton("Regresar");
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(10,1));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Destete");
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Partos");
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("Vacunas");
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Purgado");
		panel.add(btnNewButton_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		add(panel_2, BorderLayout.EAST);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"la vaca 345-07 del potrero la ceiba se debe destetar en estos dias", "", "das", "dasds", "ad", "d", "das", "ad", "ad", "a", "dad"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(450, 300));
			
		panel_2.add(listScroller, BorderLayout.CENTER);
		

		JLabel lblNewLabel_6 = new JLabel("      ");
		panel_2.add(lblNewLabel_6, BorderLayout.EAST);
		
		JLabel lblNewLabel_7 = new JLabel("      ");
		panel_2.add(lblNewLabel_7, BorderLayout.NORTH);
	}

}
