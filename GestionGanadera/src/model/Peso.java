package model;

import java.util.Date;

public class Peso {

	private double peso;
	private Date fecha;

	public Peso(double peso, Date fecha) {

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
