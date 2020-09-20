package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import db.PurganteCRUD;
import db.ResCRUD;
import db.VacunaCRUD;
import db.VitaminaCRUD;
import model.Purgante;
import model.Vacuna;
import model.Vitamina;
import tools.FileManager;

public class AgregarInfoReporte extends JDialog {

	private JDateChooser calendar;
	int mensajeMostrar;
	private JTextField textField;
	private JLabel mensaje;
	private JButton btnGuardar;
	private String res_ID;
	private AgregarEditarResDialog agregarEditarPanel;
	private JComboBox<String> comboOpciones;
	JPanel panel;

	public AgregarInfoReporte(int mensaje2, String resID, AgregarEditarResDialog agregarEditarPanel) {

		this.agregarEditarPanel = agregarEditarPanel;
		this.mensajeMostrar = mensaje2;
		res_ID = resID;

		setTitle("Agregar");

		getContentPane().setLayout(new BorderLayout(0, 0));

		setResizable(false);

		setIconImage(FileManager.imagenes.get("ICONO"));

		setSize(450, 300);
		Components();
		listeners();
		elegirMensaje(mensaje2);
		setLocationRelativeTo(null);

		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	public void Components() {

		JLabel lblNewLabel = new JLabel("                        ");
		getContentPane().add(lblNewLabel, BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("                      ");
		getContentPane().add(lblNewLabel_1, BorderLayout.EAST);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 1));

		mensaje = new JLabel("Ingrese el registro");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(mensaje);

		textField = new JTextField();
		textField.setColumns(10);

		comboOpciones = new JComboBox<String>();

		pintarAutomatico(mensajeMostrar);

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

	public void pintarAutomatico(int valor) {

		if (valor == 1) {

			panel.add(textField);

		}
		if (valor == 2) {

			ArrayList<Vacuna> vacunas = VacunaCRUD.select();

			for (int i = 0; i < vacunas.size(); i++) {

				comboOpciones.addItem(vacunas.get(i).getNombre());

			}
			panel.add(comboOpciones);

		}
		if (valor == 3) {

			ArrayList<Purgante> purgantes = PurganteCRUD.select();

			for (int i = 0; i < purgantes.size(); i++) {

				comboOpciones.addItem(purgantes.get(i).getNombre());

			}
			panel.add(comboOpciones);
		}
		
		if (valor == 4) {

			ArrayList<Vitamina> vitaminas = VitaminaCRUD.select();

			for (int i = 0; i < vitaminas.size(); i++) {

				comboOpciones.addItem(vitaminas.get(i).getNombre());

			}
			panel.add(comboOpciones);
		}

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

			mensaje.setText("Ingrese el nombre de la vitamina");
			break;
		
		}
	}

	public void partos(String fechaSeleccionada) {

		double pesoNuevo = Double.parseDouble(textField.getText());

		if (((textField.getText() != null && !textField.getText().equalsIgnoreCase(""))
				&& !fechaSeleccionada.equalsIgnoreCase(""))) {

			ResCRUD.insertPeso(res_ID, pesoNuevo, fechaSeleccionada);
			agregarEditarPanel.refreshTable(1);
			agregarEditarPanel.cerrarPanel();

		}
	}

	public void vacuna(String fechaSeleccionada) {

		if (comboOpciones.getSelectedItem() != null && !comboOpciones.getSelectedItem().toString().equalsIgnoreCase("")
				&& !fechaSeleccionada.equalsIgnoreCase("")) {

			ResCRUD.insertVacuna(res_ID, comboOpciones.getSelectedItem().toString(), fechaSeleccionada);
			agregarEditarPanel.refreshTable(2);

		}

	}

	public void purgante(String fechaSeleccionada) {

		if (comboOpciones.getSelectedItem() != null && !comboOpciones.getSelectedItem().toString().equalsIgnoreCase("")
				&& !fechaSeleccionada.equalsIgnoreCase("")) {

			ResCRUD.insertPurgante(res_ID, comboOpciones.getSelectedItem().toString(), fechaSeleccionada);
			agregarEditarPanel.refreshTable(3);

		}

	}
	
	public void vitamina(String fechaSeleccionada) {

		if (comboOpciones.getSelectedItem() != null && !comboOpciones.getSelectedItem().toString().equalsIgnoreCase("")
				&& !fechaSeleccionada.equalsIgnoreCase("")) {

			ResCRUD.insertVitamina(res_ID, comboOpciones.getSelectedItem().toString(), fechaSeleccionada);
			agregarEditarPanel.refreshTable(4);

		}

	}

	public void listeners() {

		btnGuardar.addActionListener(e -> {

			String fechaSeleccionada = "";
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			Date fechaSelected = calendar.getDate();

			if (fechaSelected != null) {
				fechaSeleccionada = format.format(fechaSelected);

			}

			switch (mensajeMostrar) {

			case 1:

				try {
					partos(fechaSeleccionada);

				} catch (Exception e2) {

					JOptionPane.showMessageDialog(null, "Ingrese un valor númerico separado de punto (.)");
				}

				break;

			case 2:

				vacuna(fechaSeleccionada);
				break;

			case 3:

				purgante(fechaSeleccionada);
				break;

			case 4:
				vitamina(fechaSeleccionada);
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
