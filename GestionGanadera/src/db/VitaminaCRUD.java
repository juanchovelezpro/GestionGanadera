package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Vitamina;

public class VitaminaCRUD {

	public static void insert(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			ArrayList<Vitamina> vitaminas = select();

			boolean existe = false;

			for (int i = 0; i < vitaminas.size(); i++) {
				if (vitaminas.get(i).getNombre().equalsIgnoreCase(nombre)) {

					existe = true;
				}
			}
			if (!existe) {
				sql.getStatement().executeUpdate("INSERT INTO vitaminas (nombre) VALUES ('" + nombre + "')");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Vitamina> select() {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Vitamina> vitaminas = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM vitaminas");

			while (result.next()) {

				vitaminas.add(new Vitamina(result.getString(1)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vitaminas;
	}

	public static void update(String nombreViejo, String nombreNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO vitaminas (nombre) VALUES ('" + nombreNuevo + "'");
			sql.getStatement().executeUpdate("UPDATE res_tiene_vitaminas SET vitaminaNombre='" + nombreNuevo
					+ "' WHERE vitaminaNombre='" + nombreViejo + "'");
			sql.getStatement().executeUpdate("DELETE FROM vitaminas WHERE nombre='" + nombreViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static Vitamina selectVitaminaByNombre(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();
		Vitamina vitamina = null;
		try {

			ResultSet result = sql.getStatement()
					.executeQuery("SELECT nombre FROM vitaminas WHERE nombre = '" + nombre + "'");

			while (result.next()) {

				String vNombre = result.getString(1);
				vitamina = new Vitamina(vNombre);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return vitamina;

	}

	public static void deleteVitaminaRes(ArrayList<String> idsRes) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate(
					"DELETE FROM res_tiene_vitaminas WHERE resID IN (" + SQLUtils.concatenarValores(idsRes) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateVitaminaRes(String resIdViejo, String resIdNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate(
					"UPDATE res_tiene_vitaminas SET resID='" + resIdNuevo + "' WHERE resID='" + resIdViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
