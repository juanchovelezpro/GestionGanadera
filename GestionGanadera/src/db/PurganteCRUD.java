package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Potrero;
import model.Purgante;

public class PurganteCRUD {

	
	
	public static void insert(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO purgantes (nombre) VALUES ('" + nombre + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static ArrayList<Purgante> select() {

	   SQLConnection sql = SQLConnection.getInstance();
	 ArrayList<Purgante> purgantes = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM purgantes");

			while (result.next()) {

				purgantes.add(new Purgante(result.getString(2)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return purgantes;
	}
	
	
	public static void update(String nombreViejo, String nombreNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO purgantes (nombre) VALUES ('" + nombreNuevo + "'");
			sql.getStatement().executeUpdate("UPDATE res_tiene_purgantes SET purganteNombre='" + nombreNuevo
					+ "' WHERE purganteNombre='" + nombreViejo + "'");
			sql.getStatement().executeUpdate("DELETE FROM purgantes WHERE nombre='" + nombreViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
	
}
