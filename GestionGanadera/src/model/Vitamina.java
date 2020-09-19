package model;

public class Vitamina {

	private String nombre;
	private String fecha;

	public Vitamina(String nombre) {

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
		return "Vitamina [nombre=" + nombre + ", fecha=" + fecha + "]";
	}

}
