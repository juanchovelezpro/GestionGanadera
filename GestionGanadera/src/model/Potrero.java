package model;

import java.util.ArrayList;

public class Potrero {

	
	private String nombre;
	private ArrayList<RegistroVitamina> registrodeVitamina;
	private ArrayList<RegistroPurgar> registrodePurgar;
	private ArrayList<Res> vacas;
	
	
	public Potrero(String nombre, ArrayList<RegistroVitamina> registrodeVitamina,
			ArrayList<RegistroPurgar> registrodePurgar, ArrayList<Res> vacas) {
		super();
		this.nombre = nombre;
		this.registrodeVitamina = registrodeVitamina;
		this.registrodePurgar = registrodePurgar;
		this.vacas = vacas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<RegistroVitamina> getRegistrodeVitamina() {
		return registrodeVitamina;
	}
	public void setRegistrodeVitamina(ArrayList<RegistroVitamina> registrodeVitamina) {
		this.registrodeVitamina = registrodeVitamina;
	}
	public ArrayList<RegistroPurgar> getRegistrodePurgar() {
		return registrodePurgar;
	}
	public void setRegistrodePurgar(ArrayList<RegistroPurgar> registrodePurgar) {
		this.registrodePurgar = registrodePurgar;
	}
	public ArrayList<Res> getVacas() {
		return vacas;
	}
	public void setVacas(ArrayList<Res> vacas) {
		this.vacas = vacas;
	}
	
	public void agregarVaca() {
		
		
	}
	
	public void eliminarVaca() {
		
		
	}
	
	public void mostrarRegistroVitamina() {
		
		
	}
	
	public void mostrarRegistroPurgar() {
		
		
	}
  
	
	
	
	
	
}
