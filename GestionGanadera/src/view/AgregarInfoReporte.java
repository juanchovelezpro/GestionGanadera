package view;

import java.awt.GridLayout;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import db.ResCRUD;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AgregarInfoReporte extends JDialog {

	private JDateChooser calendar;
	int mensajeMostrar;
	private JTextField textField;
	private JLabel mensaje;
	private JButton btnGuardar;
	private String res_ID;

	public AgregarInfoReporte(int mensaje2, String resID) {

		this.mensajeMostrar = mensaje2;
		res_ID =resID;

		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setSize(450, 300);
		Components();
		listeners();
		elegirMensaje(mensaje2);
		setVisible(true);

	}

	public void Components() {

		JLabel lblNewLabel = new JLabel("                        ");
		getContentPane().add(lblNewLabel, BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("                      ");
		getContentPane().add(lblNewLabel_1, BorderLayout.EAST);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 1));

		mensaje = new JLabel("Ingrese el registro");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(mensaje);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("Ingrese la fecha del registro");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);

		calendar = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		panel.add(calendar);

		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);

		btnGuardar = new JButton("Guardar");
		panel.add(btnGuardar);

	}

	public void elegirMensaje(int tipo) {

		switch (tipo) {

		case 1:

			mensaje.setText("Ingrese el peso");
			break;

		case 2:

			mensaje.setText("Ingrese el nombre de la vacuna");

			break;
		case 3:

			mensaje.setText("Ingrese el nombre del purgante");

			break;

		case 4:


			break;

		default:
			break;
		}
	}

	public void listeners() {

		btnGuardar.addActionListener(e -> {
			
			String fechaSeleccionada ="";
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaSelected = calendar.getDate();

            if (fechaSelected != null) {
                fechaSeleccionada = format.format(fechaSelected);

            }
			

			switch (mensajeMostrar) {
			
			
			case 1:


				try {
				double pesoNuevo =  Double.parseDouble(textField.getText());
				
				ResCRUD.insertPeso(res_ID, pesoNuevo, fechaSeleccionada);
				}catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "Ingrese un valor númerico separado de punto (.)");
				}

				break;

			case 2:
				
				ResCRUD.insertVacuna(res_ID, textField.getText(), fechaSeleccionada);
				break;

			case 3:

				ResCRUD.insertPurgante(res_ID, textField.getText(), fechaSeleccionada);

				break;

	

			default:
				break;
			}

			dispose();
		});
	}

	public JLabel mensaje() {
		return mensaje;
	}

	public JButton btnGuardar() {
		return btnGuardar;
	}

	public JTextField getTextNombre() {
		return textField;
	}
}
