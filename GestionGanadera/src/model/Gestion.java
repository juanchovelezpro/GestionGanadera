package model;

import java.util.ArrayList;

public class Gestion {

	private ArrayList<Potrero> potreros;
	private ArrayList<Notificacion> notificaciones;
	
	

	public Gestion(ArrayList<Potrero> potreros, ArrayList<Notificacion> notificaciones) {
		this.potreros = potreros;
		this.notificaciones =notificaciones;
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

	public void mostrarEmbarazada() {
		
		
	}
	
	public void moverVaca() {
		
	}
	
	
}
