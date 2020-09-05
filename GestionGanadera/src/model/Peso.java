package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import tools.Utils;

public class Peso implements Comparable<Peso> {

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

	@Override
	public String toString() {
		return "Peso [peso=" + peso + ", fecha=" + fecha + "]";
	}

	@Override
	public int compareTo(Peso o) {

		Date myFecha = Utils.convertDateToLong(fecha);
		Date otroFecha = Utils.convertDateToLong(o.getFecha());

		if (myFecha.compareTo(otroFecha) >= 0) {

			return 1;

		} else {

			return -1;

		}

	}

}
