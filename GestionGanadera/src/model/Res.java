package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;

public class Res {

	private String numero;
	private String genero;
	private String tipo;
	private String color;
	private boolean vivo;
	private Date fechaNacimiento;
	private String observaciones;
	private boolean embarazada;
	private Date fechaEmbarazo;
	private Image foto;
	private Res madre;
	private ArrayList<Res> crias;
	private ArrayList<Vacuna> vacunas;
	private ArrayList<Purgante> purgantes;
	private ArrayList<Peso> pesos;
	private Date fechaUltimaPurgada;
	private Date fechaUltimaVacunada;

	public Res(String numero, String genero, String tipo, String color, boolean vivo, Date fechaNacimiento,
			String observaciones, boolean embarazada, Date fechaEmbarazo, Image foto, Res madre,
			Date fechaUltimaPurgada, Date fechaUltimaVacunada) {
		this.numero = numero;
		this.genero = genero;
		this.tipo = tipo;
		this.color = color;
		this.vivo = vivo;
		this.fechaNacimiento = fechaNacimiento;
		this.observaciones = observaciones;
		this.embarazada = embarazada;
		this.fechaEmbarazo = fechaEmbarazo;
		this.foto = foto;
		this.madre = madre;
		this.fechaUltimaPurgada = fechaUltimaPurgada;
		this.fechaUltimaVacunada = fechaUltimaVacunada;
		
		crias = new ArrayList<>();
		vacunas = new ArrayList<>();
		purgantes = new ArrayList<>();
		pesos = new ArrayList<>();
		
	}

	public ArrayList<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(ArrayList<Peso> pesos) {
		this.pesos = pesos;
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

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
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

	public boolean isEmbarazada() {
		return embarazada;
	}

	public void setEmbarazada(boolean embarazada) {
		this.embarazada = embarazada;
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

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public ArrayList<Purgante> getPurgantes() {
		return purgantes;
	}

	public void setPurgantes(ArrayList<Purgante> purgantes) {
		this.purgantes = purgantes;
	}

	public Date getFechaUltimaPurgada() {
		return fechaUltimaPurgada;
	}

	public void setFechaUltimaPurgada(Date fechaUltimaPurgada) {
		this.fechaUltimaPurgada = fechaUltimaPurgada;
	}

	public Date getFechaUltimaVacunada() {
		return fechaUltimaVacunada;
	}

	public void setFechaUltimaVacunada(Date fechaUltimaVacunada) {
		this.fechaUltimaVacunada = fechaUltimaVacunada;
	}

}
