package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class CalendarioDialog extends JDialog {

	private JDateChooser calendar;
	private JButton btnSeleccionarFecha;
	private String fechaSeleccionada;

	public CalendarioDialog() {

		setTitle("Seleccionar Fecha");
		setSize(250, 110);
		getContentPane().setLayout(new GridLayout(2, 1));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		fechaSeleccionada = "";

		setComponents();
		listeners();

		setVisible(true);

	}

	public void setComponents() {

		calendar = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		getContentPane().add(calendar, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1, 2));

		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		btnSeleccionarFecha = new JButton("Seleccionar");
		btnSeleccionarFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelBotones.add(btnSeleccionarFecha);

	}

	public void listeners() {

		btnSeleccionarFecha.addActionListener(e -> {

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			Date fechaSelected = calendar.getDate();
		
			if(fechaSelected!=null)
			fechaSeleccionada = format.format(fechaSelected);

			
			
			
		});

	}

	public JButton getBtnSeleccionarFecha() {
		return btnSeleccionarFecha;
	}

	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

}
