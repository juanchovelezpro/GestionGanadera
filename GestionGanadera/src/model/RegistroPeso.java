package model;

import java.util.Date;

public class RegistroPeso {

	
	private double peso;
	private Date fecha;
	public RegistroPeso(double peso, Date fecha) {
		
		this.peso = peso;
		this.fecha = fecha;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
