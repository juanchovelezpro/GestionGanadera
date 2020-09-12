package model;

public class Licencia {

	private String email;
	private String serialNumber;
	private String fechaLimite;

	public Licencia() {
		super();
	}

	public Licencia(String email, String serialNumber, String fechaLimite) {
		super();
		this.email = email;
		this.serialNumber = serialNumber;
		this.fechaLimite = fechaLimite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String toJson() {

		String json = "{" + "\"email\": \"" + email + "\"," + "\"serialNumber\": \"" + serialNumber + "\","
				+ "\"fechaLimite\": \"" + fechaLimite + "\"" + "}";

		return json;

	}

}
