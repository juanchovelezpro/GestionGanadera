package model;

import java.util.ArrayList;

public class Potrero {

	private String nombre;
	private ArrayList<Res> vacas;

	public Potrero(String nombre) {

		this.nombre = nombre;
		vacas = new ArrayList<>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Res> getVacas() {
		return vacas;
	}

	public void setVacas(ArrayList<Res> vacas) {
		this.vacas = vacas;
	}

	@Override
	public String toString() {
		return "Potrero [nombre=" + nombre + ", vacas=" + vacas + "]";
	}

}
