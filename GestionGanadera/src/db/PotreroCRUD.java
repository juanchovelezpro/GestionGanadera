package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Potrero;
import model.Res;

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

	public static void insertRes(String potreroNombre, String numero, String genero, String color,
			String fecha_nacimiento, String observaciones, int vivo, int embarazada, String fecha_embarazo,
			String fecha_ultima_purgado, String fecha_ultima_vacunado, String madreID) {

		ResCRUD.insert(numero, genero, color, fecha_nacimiento, observaciones, vivo, embarazada, fecha_embarazo,
				fecha_ultima_purgado, fecha_ultima_vacunado, madreID);

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO potreros_tiene_res (potreroNombre,resID) VALUES ('"
					+ potreroNombre + "','" + numero + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<Res> selectRes(String potreroNombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeQuery("SELECT * FROM potreros_tiene_res WHERE potreroNombre='"+potreroNombre+"'");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
		
	}

	public static void deleteRes(String resID) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("DELETE FROM potreros_tiene_res WHERE resID='" + resID + "'");
			ResCRUD.delete(resID);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void updateRes(String resID, Res res) {

		SQLConnection sql = SQLConnection.getInstance();

	}

}
