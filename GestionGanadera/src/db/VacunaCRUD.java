package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Purgante;
import model.Vacuna;

public class VacunaCRUD {

	
	

	public static void insert(String nombre) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO vacunas (nombre) VALUES ('" + nombre + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public static ArrayList<Vacuna> select() {

	   SQLConnection sql = SQLConnection.getInstance();
	 ArrayList<Vacuna> vacunas = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM vacunas");

			while (result.next()) {

				vacunas.add(new Vacuna(result.getString(2)));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vacunas;
	}
	
	
	public static void update(String nombreViejo, String nombreNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO vacunas (nombre) VALUES ('" + nombreNuevo + "'");
			sql.getStatement().executeUpdate("UPDATE res_tiene_vacunas SET vacunaNombre='" + nombreNuevo
					+ "' WHERE vacunaNombre='" + nombreViejo + "'");
			sql.getStatement().executeUpdate("DELETE FROM vacunas WHERE nombre='" + nombreViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	

  public static void deleteVacunaRes (String idRes) {
		
		
		SQLConnection sql =SQLConnection.getInstance();
		
		try {
			
			sql.getStatement().executeUpdate("DELETE FROM res_tiene_vacunas WHERE resID= '"+idRes+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
  
  
	
	public static void updateVacunaRes(String resIdViejo, String resIdNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("UPDATE res_tiene_vacunas SET resID='" + resIdNuevo
					+ "' WHERE resID='" + resIdViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}


	
}
