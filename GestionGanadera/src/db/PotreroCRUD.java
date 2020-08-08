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
			sql.getStatement().executeUpdate("INSERT INTO potreros (nombre) VALUES ('" + nombre + "')");
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
			sql.getStatement().executeUpdate("INSERT INTO potreros (nombre) VALUES ('" + nombreNuevo + "')");
			sql.getStatement().executeUpdate(
					"UPDATE res SET potreroNombre='" + nombreNuevo + "' WHERE potreroNombre='" + nombreViejo + "'");
			sql.getStatement().executeUpdate("DELETE FROM potreros WHERE nombre='" + nombreViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void delete(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("DELETE FROM res_tiene_pesos WHERE resID IN(SELECT numero FROM res WHERE potreroNombre= '"+nombre+"')");
			sql.getStatement().executeUpdate("DELETE FROM res_tiene_purgantes WHERE resID IN(SELECT numero FROM res WHERE potreroNombre= '"+nombre+"')");
			sql.getStatement().executeUpdate("DELETE FROM res_tiene_vacunas WHERE resID IN(SELECT numero FROM res WHERE potreroNombre= '"+nombre+"')");
			sql.getStatement().executeUpdate("DELETE FROM res WHERE potreroNombre='" + nombre + "'");
			sql.getStatement().executeUpdate("DELETE FROM potreros WHERE nombre='" + nombre + "'");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Res> selectRes(String potreroNombre) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Res> vacas = new ArrayList<Res>();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res WHERE potreroNombre='" + potreroNombre + "'");


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
					String potrero_Nombre = result.getString(13);

					Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
							fecha_embarazo, madreID, fecha_ultimo_purgado, fecha_ultimo_vacunado, potrero_Nombre);
					
					vacas.add(res);

				}

			

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vacas;

	}
	
		public static void main(String[] args) {
		
		
	//insert("popaz");
	//	insert("povelez");
		
		

	//	System.out.println(select());
		
	//	update("povelez", "povelez2");
			
			
		
			
		//	select
		
			//seleccionar vacas de un potrero
			
	//	System.out.println(selectRes("popaz").size());
		
	//	System.out.println(selectRes("potreritovelez").size());

		//	delete("povelez");
			
			
		
	}

}
