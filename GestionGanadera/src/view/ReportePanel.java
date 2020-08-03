package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;

public class ReportePanel extends JPanel{
	
	
	private JLabel labelFecha;
	private JButton btnExportar;
	private JButton btnRegresar;
	private VentanaPrincipal ventana;
	
	public ReportePanel(VentanaPrincipal ventana) {
		
		this.ventana= ventana;
		
		setLayout(new BorderLayout(0, 0));
		
		Components();
		listeners();
		
	}
	
	
		public void Components() {
			
			JPanel panelTexto = new JPanel();
			add(panelTexto, BorderLayout.NORTH);
			panelTexto.setLayout(new GridLayout(3,1));
			
			JLabel lblNewLabel = new JLabel("     ");
			panelTexto.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("   Reporte de destete  ");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			panelTexto.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("     ");
			panelTexto.add(lblNewLabel_2);
			
			JPanel panelPrincipal = new JPanel();
			add(panelPrincipal, BorderLayout.CENTER);
			panelPrincipal.setLayout(new BorderLayout(0, 0));
			
			JLabel lblNewLabel_3 = new JLabel("     ");
			panelPrincipal.add(lblNewLabel_3, BorderLayout.WEST);
			
			JLabel lblNewLabel_4 = new JLabel("     ");
			panelPrincipal.add(lblNewLabel_4, BorderLayout.EAST);
			
			//REPORTE DE INFORMACION
			
			JPanel panelinformacion = new JPanel();
			panelPrincipal.add(panelinformacion, BorderLayout.CENTER);
			panelinformacion.setLayout(new GridLayout(6,3));
			
			JLabel lblNewLabel_5 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_5);
			
			labelFecha = new JLabel(" FECHA DEL REPORTE");
			panelinformacion.add(labelFecha);
			
			JLabel lblNewLabel_20 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_20);
			
			JLabel lblNewLabel_12 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_12);
		
			JLabel lblNewLabel_11 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_11);
			
		
			
			JLabel lblNewLabel_17 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_17);
			
			JLabel lblNewLabel_18 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_18);
			
			btnExportar = new JButton(" Exportar ");
			panelinformacion.add(btnExportar);
			
			
			
			JLabel lblNewLabel_16 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_16);
			
			JLabel lblNewLabel_15 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_15);
			
			JLabel lblNewLabel_10 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_10);

			
			
			JLabel lblNewLabel_9 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_9);
			
			JLabel lblNewLabel_13 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_13);
			
		
			btnRegresar = new JButton(" Regresar ");
			panelinformacion.add(btnRegresar);
			
			
			JLabel lblNewLabel_6 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_8);
			
			JLabel lblNewLabel_14 = new JLabel("     ");
			panelinformacion.add(lblNewLabel_14);
			
			
		}
		
		
		public void listeners() {

			btnExportar.addActionListener(e -> {


			});

			btnRegresar.addActionListener(e -> {

				

			});

		}
		
	

	public JLabel getLabelFecha() {
		return labelFecha;
	}

	public void setLabelFecha(JLabel labelFecha) {
		this.labelFecha = labelFecha;
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


	public VentanaPrincipal getVentana() {
		return ventana;
	}


	public void setVentana(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}
	
	

	
	
}
