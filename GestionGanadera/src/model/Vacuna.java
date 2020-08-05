package model;

public class Vacuna {

	private String nombre;
	private String fecha;

	public Vacuna(String nombre) {
		this.nombre = nombre;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Vacuna [nombre=" + nombre + "]";
	}

	
	
}
