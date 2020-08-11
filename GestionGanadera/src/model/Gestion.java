package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

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
