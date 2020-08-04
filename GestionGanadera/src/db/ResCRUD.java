package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Res;

public class ResCRUD {

	// Agrega una res a la tabla res de la base de datos.
	public static void insert(String numero, String genero, String color, String fecha_nacimiento, String observaciones,
			int vivo, int embarazada, String fecha_embarazo, String fecha_ultima_purgado, String fecha_ultima_vacunado,
			String madreID) {

		SQLConnection sql = SQLConnection.getInstance();

		String values = "'" + numero + "','" + genero + "','" + color + "','" + fecha_nacimiento + "','" + observaciones
				+ "'," + vivo + "," + embarazada + "'" + fecha_embarazo + "','" + fecha_ultima_purgado + "','"
				+ fecha_ultima_vacunado + "','" + madreID + "'";

		try {
			sql.getStatement().executeUpdate(
					"INSERT INTO res (numero,genero,color,fecha_nacimiento,observaciones,vivo,embarazada,fecha_embarazo,fecha_ultima_purgado,fecha_ultima_vacunado,madreID) "
							+ "VALUES (" + values + ")");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Selecciona todas las res de la tabla res de la base de datos.
	public static ArrayList<Res> select() {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Res> vacas = new ArrayList<>();

		try {

			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res");

			while (result.next()) {

				String resID = result.getString(1);
				String tipo = result.getString(2);
				String genero = result.getString(3);
				String color = result.getString(4);
				String fecha_nacimiento = result.getString(5);
				String observaciones = result.getString(6);
				int vivo = result.getInt(7);
				int embarazada = result.getInt(8);
				String fecha_embarazo = result.getString(9);
				String fecha_ultimo_purgado = result.getString(10);
				String fecha_ultimo_vacunado = result.getString(11);
				String madreID = result.getString(12);

				Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madreID, fecha_ultimo_purgado, fecha_ultimo_vacunado);
				vacas.add(res);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return vacas;

	}

	// Actualiza los datos de una res especificada con su id, el id es de la res sin
	// modificar y la res debe ser la modificada.
	public static void update(String id, Res res) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res SET numero ='" + res.getResID() + "', genero='" + res.getGenero()
							+ "', tipo='" + res.getTipo() + "', color='" + res.getColor() + "',vivo=" + res.getVivo()
							+ ", fecha_nacimiento='" + res.getFecha_nacimiento() + "', observaciones='"
							+ res.getObservaciones() + "', embarazada =" + res.getEmbarazada() + ", fecha_embarazo='"
							+ res.getFecha_embarazo() + "',madreID='" + res.getMadreID() + "',fecha_ultima_purgado='"
							+ res.getFecha_ultimo_purgado() + "', fecha_ultima_vacunado='"
							+ res.getFecha_ultimo_vacunado() + "' WHERE numero=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void delete(String id) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		ArrayList<Res> vacas = ResCRUD.select();

		Res res = vacas.get(0);
		System.out.println(res);
		String id = res.getResID();
		System.out.println(id);

		res.setResID("5");
		res.setColor("NEGRO");
		res.setObservaciones(null);

		ResCRUD.update(id, res);

		ArrayList<Res> vacas2 = ResCRUD.select();

		System.out.println(vacas2.get(0));

	}

}
