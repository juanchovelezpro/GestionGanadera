package model;

public class Peso {

	private double peso;
	private String fecha;

	public Peso(double peso, String fecha) {

		this.peso = peso;
		this.fecha = fecha;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
