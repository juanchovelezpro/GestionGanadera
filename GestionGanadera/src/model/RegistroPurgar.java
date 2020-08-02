package model;

import java.util.ArrayList;
import java.util.Date;

public class RegistroPurgar {

	private ArrayList<Res> vacas;
	private Date fecha;
	public RegistroPurgar(ArrayList<Res> vacas, Date fecha) {
		this.vacas = vacas;
		this.fecha = fecha;
	}
	public ArrayList<Res> getVacas() {
		return vacas;
	}
	public void setVacas(ArrayList<Res> vacas) {
		this.vacas = vacas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
