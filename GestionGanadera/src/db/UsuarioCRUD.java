package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

public class UsuarioCRUD {

	public static void insert(Usuario user) {

		SQLConnection sql = SQLConnection.getInstance();

		String values = "'" + user.getNombre() + "','" + user.getPassword() + "','" + user.getUbicacion() + "','"
				+ user.getNombreFinca() + "','" + user.getEmail() + "','" + user.getSerialNumber() + "','"
				+ user.getFechaLimite() + "'";

		try {
			sql.getStatement().executeUpdate(
					"INSERT INTO usuarios (nombre,password,ubicacion,nombreFinca,email,serialNumber,fechaLimite) "
							+ "VALUES (" + values + ")");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Usuario> select() {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM usuarios");

			while (result.next()) {

				String nombre = result.getString(1);
				String password = result.getString(2);
				String ubicacion = result.getString(3);
				String nombreFinca = result.getString(4);
				String email = result.getString(5);
				String serialNumber = result.getString(6);
				String fechaLimite = result.getString(7);

				Usuario user = new Usuario(nombre, ubicacion, nombreFinca, password, email, serialNumber, fechaLimite);

				usuarios.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return usuarios;
	}

	// modificar y la res debe ser la modificada.
	public static void update(String nombreUser, Usuario user) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE usuarios SET nombre ='" + user.getNombre() + "', password='"
							+ user.getPassword() + "', ubicacion='" + user.getUbicacion() + "', nombreFinca='"
							+ user.getNombreFinca() + "', email='" + user.getEmail() + "', serialNumber='"
							+ user.getSerialNumber() + "', fechaLimite='" + user.getFechaLimite() + "' WHERE nombre='"
							+ nombreUser + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
