package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import db.ResCRUD;
import model.Peso;
import model.Res;
import tools.FileManager;

public class ReporteDialog extends JDialog {
	private JButton btnExportar;
	private JButton btnRegresar;
	private String reporte;
	private Calendar fechaSystem;
	private String fecha_Convertida;
	private JTable tablaDestete;
	private ModelTable modelDestete;
	private JTable tablaParto;
	private ModelTable modelParto;
	private JScrollPane scroller;
	private JPanel panelinfo;


	public ReporteDialog(String reporte, int valor) {

		this.reporte = reporte;
		setTitle("Reporte");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(647, 396);
		setIconImage(FileManager.imagenes.get("REPORTE"));
		setLocationRelativeTo(null);

		Components();
		elegirTabla(valor);

		listeners();

		setModalityType(ModalityType.APPLICATION_MODAL);

		setVisible(true);
		

	}

	public void Components() {

		JPanel panelTexto = new JPanel();
		getContentPane().add(panelTexto, BorderLayout.NORTH);
		panelTexto.setLayout(new GridLayout(3, 1));

		JLabel lblNewLabel = new JLabel("     ");
		panelTexto.add(lblNewLabel);

		fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		fecha_Convertida = dia + "/" + mes + "/" + anio;
		
		JLabel lblNewLabel_1 = new JLabel(reporte + " - " + fecha_Convertida);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelTexto.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panelTexto.add(lblNewLabel_2);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("     ");
		panelPrincipal.add(lblNewLabel_3, BorderLayout.WEST);

		JLabel lblNewLabel_4 = new JLabel("     ");
		panelPrincipal.add(lblNewLabel_4, BorderLayout.EAST);

		// REPORTE DE INFORMACION

	    panelinfo = new JPanel();
		panelinfo.setLayout(new GridLayout(1,1));

		scroller =new JScrollPane();


		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel, BorderLayout.SOUTH);
		
	     btnExportar = new JButton(" Exportar ");
				panel.add(btnExportar);
			

				btnRegresar = new JButton(" Regresar ");
				panel.add(btnRegresar);

				panelPrincipal.add(panelinfo, BorderLayout.CENTER);


				


	}
	
	public void elegirTabla(int valor) {
		
		if (valor ==1) {
			crearTablaDestete();
		}
		else if (valor ==2) {
			crearTablaPartos();
		}
		
	}
	
	public void crearTablaPartos() {
		



		String[] columns = { "NUMERO", "FECHA EMBARAZO", "TIEMPO DE EMBARAZO", "POTRERO"  };

		modelParto = new ModelTable();

			ArrayList<Res> reses =ResCRUD.reportePartos();
			Object[][] data = new Object[reses.size()][columns.length];
			Res temp = null;
			String mensaje = "";

			for (int i = 0; i < data.length; i++) {

				temp = reses.get(i);

				for (int j = 0; j < data[0].length; j++) {

					if (j == 0)
						data[i][j] = temp.getResID();

					if (j == 1)
						data[i][j] = temp.getFecha_embarazo();
					
					if (j == 2)
						data[i][j] = ResCRUD.calcDate(temp.getFecha_embarazo());
					
					if (j == 3)
						data[i][j] = temp.getPotreroNombre();
					
					
					
					

				}

			

		         
			modelParto.setData(data);
			modelParto.setColumns(columns);
			tablaParto = new JTable(modelParto);
			tablaParto.getColumnModel().getColumn(2).setPreferredWidth(140);
			tablaParto.getColumnModel().getColumn(1).setPreferredWidth(120);
			//tablaDestete.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

                
			tablaParto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tablaParto.setShowHorizontalLines(true);
			tablaParto.setShowVerticalLines(true);
			tablaParto.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
			scroller.setViewportView(tablaParto);
			tablaParto.setFillsViewportHeight(true);
			panelinfo.add(scroller);
			
			}
			


		}
	
	public void crearTablaDestete() {
		



			String[] columns = { "NUMERO", "NACIMIENTO", "TIEMPO DE CRÍA", "MADRE", "POTRERO"  };

			modelDestete = new ModelTable();

				ArrayList<Res> reses =ResCRUD.reporteDestete();
				Object[][] data = new Object[reses.size()][columns.length];
				Res temp = null;
				String mensaje = "";

				for (int i = 0; i < data.length; i++) {

					temp = reses.get(i);

					for (int j = 0; j < data[0].length; j++) {

						if (j == 0)
							data[i][j] = temp.getResID();

						if (j == 1)
							data[i][j] = temp.getFecha_nacimiento();
						
						if (j == 2)
							data[i][j] = ResCRUD.calcDate(temp.getFecha_nacimiento());
						
						if (j == 3)
							data[i][j] = temp.getMadreID();
						
						if (j == 4)
							data[i][j] = temp.getPotreroNombre();
						
						

					}

				

			
				modelDestete.setData(data);
				modelDestete.setColumns(columns);
				tablaDestete = new JTable(modelDestete);
				tablaDestete.getColumnModel().getColumn(2).setPreferredWidth(140);
				tablaDestete.getColumnModel().getColumn(1).setPreferredWidth(120);
				//tablaDestete.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);


				tablaDestete.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tablaDestete.setShowHorizontalLines(true);
				tablaDestete.setShowVerticalLines(true);
				tablaDestete.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
				scroller.setViewportView(tablaDestete);
				tablaDestete.setFillsViewportHeight(true);
				panelinfo.add(scroller);
				


			}

		}
	

	public void listeners() {

		btnExportar.addActionListener(e -> {

		});

		btnRegresar.addActionListener(e -> {

			setVisible(false);

		});

	}


	public JButton getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

}
