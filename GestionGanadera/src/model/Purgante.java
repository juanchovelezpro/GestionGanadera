package model;

public class Purgante {

	private String nombre;
	private String fecha;

	public Purgante(String nombre) {

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
		return "Purgante [nombre=" + nombre + ", fecha=" + fecha + "]";
	}

	public boolean equals(Purgante purgante) {

		return nombre.equals(purgante.getNombre());
	}

}
