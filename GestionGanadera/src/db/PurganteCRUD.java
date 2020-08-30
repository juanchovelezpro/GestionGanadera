package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Purgante;

public class PurganteCRUD {

	public static void insert(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			ArrayList<Purgante> purgantes = select();

			boolean existe = false;

			for (int i = 0; i < purgantes.size() && !existe; i++) {

				if (purgantes.get(i).getNombre().equalsIgnoreCase(nombre)) {

					existe = true;
				}
			}

			if (!existe) {

				sql.getStatement().executeUpdate("INSERT INTO purgantes (nombre) VALUES ('" + nombre + "')");
			}

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

				purgantes.add(new Purgante(result.getString(1)));

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

	public static void deletePurganteRes(String idRes) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("DELETE FROM res_tiene_purgantes WHERE resID= '" + idRes + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updatePurganteRes(String resIdViejo, String resIdNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate(
					"UPDATE res_tiene_purgantes SET resID='" + resIdNuevo + "' WHERE resID='" + resIdViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
