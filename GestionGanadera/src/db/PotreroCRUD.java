package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Potrero;

public class PotreroCRUD {

	public static void insert(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO potreros (nombre) VALUES (" + nombre + ")");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Potrero> select() {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Potrero> potreros = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM potreros");

			while (result.next()) {

				potreros.add(new Potrero(result.getString(1)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return potreros;
	}

	public static void update(String nombreViejo, String nombreNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO potreros (nombre) VALUES ('" + nombreNuevo + "'");
			sql.getStatement().executeUpdate("UPDATE potreros_tiene_res SET potreroNombre='" + nombreNuevo
					+ "' WHERE potreroNombre='" + nombreViejo + "'");
			sql.getStatement().executeUpdate("DELETE FROM potreros WHERE nombre='" + nombreViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void delete(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("DELETE FROM potreros_tiene_res WHERE potreroNombre='" + nombre + "'");
			sql.getStatement().executeUpdate("DELETE FROM potreros WHERE nombre='" + nombre + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
