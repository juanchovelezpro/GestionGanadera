package model;

public class Usuario {

	private String nombre;
	private String ubicacion;
	private String nombreFinca;
	private String password;
	private String serialNumber;
	private String email;
	private String fechaLimite;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String ubicacion, String nombreFinca, String password, String serialNumber,
			String email, String fechaLimite) {

		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.nombreFinca = nombreFinca;
		this.password = password;
		this.serialNumber = serialNumber;
		this.email = email;
		this.fechaLimite = fechaLimite;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
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

	public String toJson() {

		String json = "{" + "\"email\": \"" + email + "\"," + "\"fechaLimite\": \"" + fechaLimite + "\","
				+ "\"nombre\": \"" + nombre + "\"," + "\"password\": \"" + password + "\"," + "\"serialNumber\": \""
				+ serialNumber + "\"" + "}";

		return json;

	}

}
