package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;

public class Res {

	   private  String numero;
	   private String genero;
	   private String tipo;
	   private String color;
	   private boolean estaVivo;
	   private Date fechaNacimiento;
	   private String observaciones;
	   private boolean estaEmbarazada;
	   private Date fechaEmbarazo;
	   private Image foto;
	   private ArrayList<RegistroPeso> registrodePeso;
	   private ArrayList<Res> crias;
	   private Res madre;
	   
	   
	public Res(String numero, String genero, String tipo, String color, boolean estaVivo, Date fechaNacimiento,
			String observaciones, boolean estaEmbarazada, Date fechaEmbarazo, Image foto,
			ArrayList<RegistroPeso> registrodePeso, ArrayList<Res> crias, Res madre) {
		this.numero = numero;
		this.genero = genero;
		this.tipo = tipo;
		this.color = color;
		this.estaVivo = estaVivo;
		this.fechaNacimiento = fechaNacimiento;
		this.observaciones = observaciones;
		this.estaEmbarazada = estaEmbarazada;
		this.fechaEmbarazo = fechaEmbarazo;
		this.foto = foto;
		this.registrodePeso = registrodePeso;
		this.crias = crias;
		this.madre = madre;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public boolean isEstaVivo() {
		return estaVivo;
	}


	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public boolean isEstaEmbarazada() {
		return estaEmbarazada;
	}


	public void setEstaEmbarazada(boolean estaEmbarazada) {
		this.estaEmbarazada = estaEmbarazada;
	}


	public Date getFechaEmbarazo() {
		return fechaEmbarazo;
	}


	public void setFechaEmbarazo(Date fechaEmbarazo) {
		this.fechaEmbarazo = fechaEmbarazo;
	}


	public Image getFoto() {
		return foto;
	}


	public void setFoto(Image foto) {
		this.foto = foto;
	}


	public ArrayList<RegistroPeso> getRegistrodePeso() {
		return registrodePeso;
	}


	public void setRegistrodePeso(ArrayList<RegistroPeso> registrodePeso) {
		this.registrodePeso = registrodePeso;
	}


	public ArrayList<Res> getCrias() {
		return crias;
	}


	public void setCrias(ArrayList<Res> crias) {
		this.crias = crias;
	}


	public Res getMadre() {
		return madre;
	}


	public void setMadre(Res madre) {
		this.madre = madre;
	}
	
	public void destetar() {
		
	}
	
	public void mostrarRegistroPeso() {
		
	}
	
	
	public void cambiarTipo() {
		
		
	}
	
	public void verCrias() {
		
		
	}
	
	   
	   
	   
	 
	   
}
