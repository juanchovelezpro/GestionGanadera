package model;

public class Usuario {

	private String nombre;
	private String ubicacion;
	private String nombreFinca;
	private String password;

	public Usuario(String nombre, String ubicacion, String nombreFinca, String password) {

		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.nombreFinca = nombreFinca;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNombreFinca() {
		return nombreFinca;
	}

	public void setNombreFinca(String nombreFinca) {
		this.nombreFinca = nombreFinca;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", ubicacion=" + ubicacion + ", nombreFinca=" + nombreFinca + ", password="
				+ password + "]";
	}

}
