package model;

import java.util.ArrayList;
import java.util.Stack;

public class Res {

	private String resID;
	private String genero;
	private String tipo;
	private String color;
	private int vivo;
	private String fecha_nacimiento;
	private String observaciones;
	private int embarazada;
	private String fecha_embarazo;
	private String madreID;
	private ArrayList<Res> crias;
	private ArrayList<Vacuna> vacunas;
	private Stack<Purgante> purgantes;
	private ArrayList<Peso> pesos;
	private String fecha_ultimo_purgado;
	private String fecha_ultimo_vacunado;
	private String potreroNombre;

	public Res(String resID, String genero, String tipo, String color, int vivo, String fecha_nacimiento,
			String observaciones, int embarazada, String fecha_embarazo, String madreID, String fecha_ultimo_purgado,
			String fecha_ultimo_vacunado, String potreroNombre) {
		super();
		this.resID = resID;
		this.genero = genero;
		this.tipo = tipo;
		this.color = color;
		this.vivo = vivo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.observaciones = observaciones;
		this.embarazada = embarazada;
		this.fecha_embarazo = fecha_embarazo;
		this.madreID = madreID;
		this.fecha_ultimo_purgado = fecha_ultimo_purgado;
		this.fecha_ultimo_vacunado = fecha_ultimo_vacunado;
		this.potreroNombre = potreroNombre;

		crias = new ArrayList<>();
		vacunas = new ArrayList<>();
		purgantes = new Stack<>();
		pesos = new ArrayList<>();
	}

	public Res() {

		crias = new ArrayList<>();
		vacunas = new ArrayList<>();
		purgantes = new Stack<>();
		pesos = new ArrayList<>();

	}

	public String getPotreroNombre() {
		return potreroNombre;
	}

	public void setPotreroNombre(String potreroNombre) {
		this.potreroNombre = potreroNombre;
	}

	public String getResID() {
		return resID;
	}

	public void setResID(String resID) {
		this.resID = resID;
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

	public int getVivo() {
		return vivo;
	}

	public void setVivo(int vivo) {
		this.vivo = vivo;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getEmbarazada() {
		return embarazada;
	}

	public void setEmbarazada(int embarazada) {
		this.embarazada = embarazada;
	}

	public String getFecha_embarazo() {
		return fecha_embarazo;
	}

	public void setFecha_embarazo(String fecha_embarazo) {
		this.fecha_embarazo = fecha_embarazo;
	}

	public String getMadreID() {
		return madreID;
	}

	public void setMadreID(String madreID) {
		this.madreID = madreID;
	}

	public ArrayList<Res> getCrias() {
		return crias;
	}

	public void setCrias(ArrayList<Res> crias) {
		this.crias = crias;
	}

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Stack<Purgante> getPurgantes() {
		return purgantes;
	}

	public void setPurgantes(Stack<Purgante> resess) {
		this.purgantes = resess;
	}

	public ArrayList<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(ArrayList<Peso> pesos) {
		this.pesos = pesos;
	}

	public String getFecha_ultimo_purgado() {
		return fecha_ultimo_purgado;
	}

	public void setFecha_ultimo_purgado(String fecha_ultimo_purgado) {
		this.fecha_ultimo_purgado = fecha_ultimo_purgado;
	}

	public String getFecha_ultimo_vacunado() {
		return fecha_ultimo_vacunado;
	}

	public void setFecha_ultimo_vacunado(String fecha_ultimo_vacunado) {
		this.fecha_ultimo_vacunado = fecha_ultimo_vacunado;
	}

	@Override
	public String toString() {
		return "Res [resID=" + resID + ", genero=" + genero + ", tipo=" + tipo + ", color=" + color + ", vivo=" + vivo
				+ ", fecha_nacimiento=" + fecha_nacimiento + ", observaciones=" + observaciones + ", embarazada="
				+ embarazada + ", fecha_embarazo=" + fecha_embarazo + ", madreID=" + madreID + ", crias=" + crias
				+ ", vacunas=" + vacunas + ", purgantes=" + purgantes + ", pesos=" + pesos + ", fecha_ultimo_purgado="
				+ fecha_ultimo_purgado + ", fecha_ultimo_vacunado=" + fecha_ultimo_vacunado + ", potreroNombre="
				+ potreroNombre + "]";
	}

	public String toPartos() {
		return "Este atento al parto de la res: " + resID + " Del potrero: " + potreroNombre + " Con fecha de embarazo "
				+ fecha_embarazo;
	}

	public String toDestete() {

		return "Es momento de realizar el destete a la res: " + resID + " Del potrero: " + potreroNombre
				+ " Con fecha de nacimiento " + fecha_nacimiento;
	}

	public String toPurgado() {

		if (purgantes.size() > 0) {
			return " Es momento de aplicar la segunda dosis del purgante "
					+ purgantes.get(purgantes.size() - 1).getNombre() + " Que tuvo la primera dosis el día "
					+ purgantes.get(purgantes.size() - 1).getFecha() + " a la res " + resID;

		} else {
			return "hay un problemilla";
		}
	}

}
