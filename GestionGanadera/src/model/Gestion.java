package model;

import java.util.ArrayList;

public class Gestion {

	private ArrayList<Potrero> potreros;
	private ArrayList<Notificacion> notificaciones;
	

	public Gestion() {

		potreros = new ArrayList<>();
		notificaciones = new ArrayList<>();

	}

	public ArrayList<Potrero> getPotreros() {
		return potreros;
	}

	public void setPotreros(ArrayList<Potrero> potreros) {
		this.potreros = potreros;
	}

	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;

	}
	
}
