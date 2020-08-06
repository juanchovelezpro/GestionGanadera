package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Peso;
import model.Purgante;
import model.Res;
import model.Vacuna;

public class ResCRUD {

	// Agrega una res a la tabla res de la base de datos.
	public static void insert(String numero, String tipo, String genero, String color, String fecha_nacimiento, String observaciones,
			int vivo, int embarazada, String fecha_embarazo, String fecha_ultima_purgado, String fecha_ultima_vacunado,
			String madreID, String potreroNombre) {

		SQLConnection sql = SQLConnection.getInstance();

		String values = "'" + numero + "','" + tipo + "','" + genero + "','" + color + "','" + fecha_nacimiento + "','" + observaciones
				+ "'," + vivo + "," + embarazada + ",'" + fecha_embarazo + "','" + fecha_ultima_purgado + "','"
				+ fecha_ultima_vacunado + "','" + madreID + "','" + potreroNombre + "'";

		try {
			sql.getStatement().executeUpdate(
					"INSERT INTO res (numero,tipo,genero,color,fecha_nacimiento,observaciones,vivo,embarazada,fecha_embarazo,fecha_ultima_purgado,fecha_ultima_vacunado,madreID,potreroNombre) "
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
				String potreroNombre = result.getString(13);

				Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madreID, fecha_ultimo_purgado, fecha_ultimo_vacunado, potreroNombre);
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
			sql.getStatement().executeUpdate("UPDATE res SET numero ='" + res.getResID() + "', genero='"
					+ res.getGenero() + "', tipo='" + res.getTipo() + "', color='" + res.getColor() + "',vivo="
					+ res.getVivo() + ", fecha_nacimiento='" + res.getFecha_nacimiento() + "', observaciones='"
					+ res.getObservaciones() + "', embarazada =" + res.getEmbarazada() + ", fecha_embarazo='"
					+ res.getFecha_embarazo() + "',madreID='" + res.getMadreID() + "',fecha_ultima_purgado='"
					+ res.getFecha_ultimo_purgado() + "', fecha_ultima_vacunado='" + res.getFecha_ultimo_vacunado()
					+ "', potreroNombre='" + res.getPotreroNombre() + "' WHERE numero=" + id);
			
			updateMadreaCria(id, res.getResID());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//update madreIDencrias
	
	
	public static void updateMadreaCria(String idVieja, String idNueva) {
		
		SQLConnection sql =SQLConnection.getInstance();
		
		try {
			sql.getStatement().executeUpdate("UPDATE res SET madreID='" + idNueva + "' WHERE madreID='" + idVieja + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	// Elimina una res de la base de datos.
	public static void delete(String id) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("DELETE FROM res WHERE numero=" + id);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	//seleccion una cria
	public static ArrayList<Res> selectCria(String madreID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Res> crias = new ArrayList<>();

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res WHERE madreID='" + madreID + "'");

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
				String madre_ID = result.getString(12);
				String potreroNombre = result.getString(13);

				Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madre_ID, fecha_ultimo_purgado, fecha_ultimo_vacunado, potreroNombre);
				crias.add(res);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return crias;
	}
	
	//insertar peso

	public static void insertPeso(String resID, double peso, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_pesos (resID,peso,fecha) VALUES ('" + resID + "',"
					+ peso + ",'" + fecha + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	
	//seleccionar peso
	public static ArrayList<Peso> selectPesos(String resID){
		
		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Peso> pesos = new ArrayList<Peso>();
		
		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res_tiene_pesos WHERE resID='"+resID+"'");
			
			while(result.next()) {
				
				double peso = result.getDouble(3);
				String fecha = result.getString(4);
				
				pesos.add(new Peso(peso, fecha));
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return pesos;
		
	}
	
	
	//actualizar peso
	public static void updatePeso(String resID,String fechaVieja, Peso peso) {
		
		SQLConnection sql = SQLConnection.getInstance();
		
		try {
			sql.getStatement().executeUpdate("UPDATE res_tiene_pesos SET peso="+peso.getPeso()+", fecha='"+peso.getFecha()+"' WHERE resID='"+resID+"' AND fecha='"+fechaVieja+"'");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	//insertvacuna
	public static void insertVacuna(String resID, String vacunaNombre, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vacunas (resID,vacunaNombre,fecha) VALUES ('" + resID + "','"
					+ vacunaNombre + "','" + fecha + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	//seleccionar Vacuna
	public static ArrayList<Vacuna> selectVacunas(String resID){
		
		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
		
		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res_tiene_vacunas WHERE resID='"+resID+"'");
			
			while(result.next()) {
				
				String vacuna_Nombre = result.getString(3);
				String fecha = result.getString(4);
				
				Vacuna vacuna_Actual =new Vacuna(vacuna_Nombre);
				vacuna_Actual.setFecha(fecha);
				vacunas.add(vacuna_Actual);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return vacunas;
		
	}
	
	//actualizar peso
	public static void updateVacuna(String resID,String fechaVieja, Vacuna vacuna) {
		
		SQLConnection sql = SQLConnection.getInstance();
		
		try {
			sql.getStatement().executeUpdate("UPDATE res_tiene_vacunas SET vacunaNombre='"+vacuna.getNombre()+"', fecha='"+vacuna.getFecha()+"' WHERE resID='"+resID+"' AND fecha='"+fechaVieja+"'");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	//insertpurgante
		public static void insertPurgante(String resID, String purganteNombre, String fecha) {

			SQLConnection sql = SQLConnection.getInstance();

			try {
				sql.getStatement().executeUpdate("INSERT INTO res_tiene_purgantes (resID,purganteNombre,fecha) VALUES ('" + resID + "','"
						+ purganteNombre + "','" + fecha + "')");
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
		
		
		//seleccionar purgante
		public static ArrayList<Purgante> selectPurgantes(String resID){
			
			SQLConnection sql = SQLConnection.getInstance();
			ArrayList<Purgante> purgantes = new ArrayList<Purgante>();
			
			try {
				ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res_tiene_purgantes WHERE resID='"+resID+"'");
				
				while(result.next()) {
					
					String purgante_Nombre = result.getString(3);
					String fecha = result.getString(4);
					
					Purgante purgante_Actual =new Purgante(purgante_Nombre);
					purgante_Actual.setFecha(fecha);
					purgantes.add(purgante_Actual);
					
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			return purgantes;
			
		}
		

		//actualizar purgante
		public static void updatePurgante(String resID,String fechaVieja, Purgante purgante) {
			
			SQLConnection sql = SQLConnection.getInstance();
			
			try {
				sql.getStatement().executeUpdate("UPDATE res_tiene_purgantes SET purganteNombre='"+purgante.getNombre()+"', fecha='"+purgante.getFecha()+"' WHERE resID='"+resID+"' AND fecha='"+fechaVieja+"'");
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}
		
		
	
	public static void main(String[] args) {
		
		//insert("103", "M", "negro", "12/12/2000", "bonito", 1, 0, null, "12/12/2018", "12/12/2018", "100", "otropotreritopaz");
		//insert("104", "M", "negro", "12/12/2000", "bonito", 1, 0, null, "12/12/2018", "12/12/2018", "100", "otropotreritopaz");

		//System.out.println(select().size());
		
		//Res resita = select().get(1);
		//resita.setTipo("VH");
		//update("105", resita);
		
		//delete("105");
		
	//	System.out.println(selectCria("110").size());
		
	//	insertPeso("102", 150.6, "12/12/2012");
	//	insertPeso("102", 200.6, "12/06/2013");
	//	insertPeso("102", 250.6, "12/12/2013");

	 //System.out.println(selectPesos("102").size());	
		
	//	updatePeso("102", "12/12/2012", new Peso(160, "13/12/2012"));
		
     //  PurganteCRUD.insert("purgantebueno2");	
    //   VacunaCRUD.insert("vacunabuena");	

       
	//	insertPurgante("103", "purgantebueno", "15/01/2010");
	//	insertPurgante("104", "purgantebueno", "15/07/2010");
		
	//	insertVacuna("103", "vacunabuena", "01/01/2000");
	//	insertVacuna("104", "vacunabuena", "01/07/2000");


	//	System.out.println(selectPurgantes("102").size());
	//	System.out.println(selectPurgantes("103").size());
	//	System.out.println(selectVacunas("103").size());
		
		//Purgante purgan =new Purgante("purgantemalo");
		//		purgan.setFecha("21/12/2020");
				
		//		Vacuna vacun =new Vacuna("vacunamalo");
		//¡		vacun.setFecha("22/12/2020");
				
				//  PurganteCRUD.insert("purgantemalo");	
			      //VacunaCRUD.insert("vacunamalo");	
				
	//	updatePurgante("103", "15/01/2010", purgan);
	//	updateVacuna("103", "01/01/2000", vacun);


		
	//	System.out.println(select().size());
		
	}
	

}
