package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import model.Peso;
import model.Purgante;
import model.Res;
import model.Vacuna;
import model.Vitamina;

public class ResCRUD {

	public static void insert(Res res) {

		SQLConnection sql = SQLConnection.getInstance();

		String values = "'" + res.getResID() + "','" + res.getTipo() + "','" + res.getGenero() + "','" + res.getColor()
				+ "','" + res.getFecha_nacimiento() + "','" + res.getObservaciones() + "'," + res.getVivo() + ","
				+ res.getEmbarazada() + ",'" + res.getFecha_embarazo() + "','" + res.getFecha_UltimoEmbarazo() + "','"
				+ res.getFecha_ultimo_vacunado() + "','" + res.getMadreID() + "','" + res.getPotreroNombre() + "'";

		try {
			sql.getStatement().executeUpdate(
					"INSERT INTO res (numero,tipo,genero,color,fecha_nacimiento,observaciones,vivo,embarazada,fecha_embarazo,fecha_UltimoEmbarazo,fecha_ultima_vacunado,madreID,potreroNombre) "
							+ "VALUES (" + values + ")");

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public static void insertMultiple(ArrayList<Res> reses) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate(
					"INSERT INTO res (numero,tipo,genero,color,fecha_nacimiento,observaciones,vivo,embarazada,fecha_embarazo,fecha_UltimoEmbarazo,fecha_ultima_vacunado,madreID,potreroNombre) "
							+ "VALUES " + SQLUtils.concatenarReses(reses));
		} catch (SQLException e) {

		}
	}

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
				String fecha_UltimoEmbarazo = result.getString(10);
				String fecha_ultimo_vacunado = result.getString(11);
				String madreID = result.getString(12);
				String potreroNombre = result.getString(13);

				Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madreID, fecha_UltimoEmbarazo, fecha_ultimo_vacunado, potreroNombre);
				vacas.add(res);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return vacas;

	}

	public static Res selectResByID(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		Res res = null;

		try {
			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res WHERE numero='" + resID + "'");

			while (result.next()) {

				String res_ID = result.getString(1);
				String tipo = result.getString(2);
				String genero = result.getString(3);
				String color = result.getString(4);
				String fecha_nacimiento = result.getString(5);
				String observaciones = result.getString(6);
				int vivo = result.getInt(7);
				int embarazada = result.getInt(8);
				String fecha_embarazo = result.getString(9);
				String fecha_UltimoEmbarazo = result.getString(10);
				String fecha_ultimo_vacunado = result.getString(11);
				String madreID = result.getString(12);
				String potreroNombre = result.getString(13);

				res = new Res(res_ID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madreID, fecha_UltimoEmbarazo, fecha_ultimo_vacunado, potreroNombre);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
	}

	public static void update(String id, Res res) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("UPDATE res SET numero ='" + res.getResID() + "', genero='"
					+ res.getGenero() + "', tipo='" + res.getTipo() + "', color='" + res.getColor() + "',vivo="
					+ res.getVivo() + ", fecha_nacimiento='" + res.getFecha_nacimiento() + "', observaciones='"
					+ res.getObservaciones() + "', embarazada =" + res.getEmbarazada() + ", fecha_embarazo='"
					+ res.getFecha_embarazo() + "',madreID='" + res.getMadreID() + "',fecha_UltimoEmbarazo='"
					+ res.getFecha_UltimoEmbarazo() + "', fecha_ultima_vacunado='" + res.getFecha_ultimo_vacunado()
					+ "', potreroNombre='" + res.getPotreroNombre() + "' WHERE numero='" + id + "'");

			updatePesosRes(id, res.getResID());
			PurganteCRUD.updatePurganteRes(id, res.getResID());
			VacunaCRUD.updateVacunaRes(id, res.getResID());
			updateMadreaCria(id, res.getResID());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	public static void updateMadreaCria(String idVieja, String idNueva) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res SET madreID='" + idNueva + "' WHERE madreID='" + idVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void delete(ArrayList<String> ids) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("DELETE FROM res WHERE numero IN (" + SQLUtils.concatenarValores(ids) + ")");
			deletePesosRes(ids);
			PurganteCRUD.deletePurganteRes(ids);
			VacunaCRUD.deleteVacunaRes(ids);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void trasladar(ArrayList<String> idsRes, String potreroOrigen, String potreroDestino) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("UPDATE res SET potreroNombre='" + potreroDestino + "' WHERE numero IN ("
					+ SQLUtils.concatenarValores(idsRes) + ")");

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

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
				String fecha_UltimoEmbarazo = result.getString(10);
				String fecha_ultimo_vacunado = result.getString(11);
				String madre_ID = result.getString(12);
				String potreroNombre = result.getString(13);

				Res res = new Res(resID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madre_ID, fecha_UltimoEmbarazo, fecha_ultimo_vacunado, potreroNombre);
				crias.add(res);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return crias;
	}

	// insertar peso

	public static void insertPeso(String resID, double peso, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_pesos (resID,peso,fecha) VALUES ('" + resID + "',"
					+ peso + ",'" + fecha + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// seleccionar peso
	public static Stack<Peso> selectPesos(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Peso> pesos = new ArrayList<Peso>();
		Stack<Peso> pesos2 = new Stack();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res_tiene_pesos WHERE resID='" + resID + "'");

			while (result.next()) {

				double peso = result.getDouble(3);
				String fecha = result.getString(4);

				pesos.add(new Peso(peso, fecha));
				pesos2.push(new Peso(peso, fecha));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println(pesos2.size());
		return pesos2;

	}

	public static ArrayList<Peso> selectPesosLista(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Peso> pesos = new ArrayList<Peso>();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res_tiene_pesos WHERE resID='" + resID + "'");

			while (result.next()) {

				double peso = result.getDouble(3);
				String fecha = result.getString(4);

				pesos.add(new Peso(peso, fecha));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return pesos;

	}

	public static void deletePesosRes(ArrayList<String> idsRes) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate(
					"DELETE FROM res_tiene_pesos WHERE resID IN (" + SQLUtils.concatenarValores(idsRes) + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deletePesoFromRes(String resID, double peso, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("DELETE FROM res_tiene_pesos WHERE resID=\"" + resID + "\" AND peso="
					+ peso + " AND fecha=\"" + fecha + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updatePesosRes(String resIdViejo, String resIdNuevo) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate(
					"UPDATE res_tiene_pesos SET resID='" + resIdNuevo + "' WHERE resID='" + resIdViejo + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// actualizar peso
	public static void updatePeso(String resID, String fechaVieja, Peso peso) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement().executeUpdate("UPDATE res_tiene_pesos SET peso=" + peso.getPeso() + ", fecha='"
					+ peso.getFecha() + "' WHERE resID='" + resID + "' AND fecha='" + fechaVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Insertar vacuna a una sola res
	public static void insertVacuna(String resID, String vacunaNombre, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			VacunaCRUD.insert(vacunaNombre);
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vacunas (resID,vacunaNombre,fecha) VALUES ('"
					+ resID + "','" + vacunaNombre + "','" + fecha + "')");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Insertar vacuna a multiples vacas (Primera Aplicacion)
	public static void insertVacunaMultiple(ArrayList<String> ids, String vacuna, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vacunas (resID,vacunaNombre,fecha) VALUES "
					+ SQLUtils.concatenarAplicar(ids, vacuna, fecha));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Insertar vacuna a multiples reses (Segunda Aplicacion)
	public static void insertVacunaMultipleSegunda(ArrayList<Res> reses, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vacunas (resID,vacunaNombre,fecha) VALUES "
					+ SQLUtils.concatenarSegundoAplicarVacunas(reses, fecha));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// seleccionar Vacuna
	public static Stack<Vacuna> selectVacunas(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
		Stack<Vacuna> vacunas2 = new Stack<Vacuna>();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res_tiene_vacunas WHERE resID='" + resID + "'");

			while (result.next()) {

				String vacuna_Nombre = result.getString(3);
				String fecha = result.getString(4);

				Vacuna vacuna_Actual = new Vacuna(vacuna_Nombre);
				vacuna_Actual.setFecha(fecha);
				vacunas.add(vacuna_Actual);
				vacunas2.push(vacuna_Actual);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vacunas2;

	}

	// actualizar peso
	public static void updateVacuna(String resID, String fechaVieja, Vacuna vacuna) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res_tiene_vacunas SET vacunaNombre='" + vacuna.getNombre() + "', fecha='"
							+ vacuna.getFecha() + "' WHERE resID='" + resID + "' AND fecha='" + fechaVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// insertpurgante
	public static void insertPurgante(String resID, String purganteNombre, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			PurganteCRUD.insert(purganteNombre);
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_purgantes (resID,purganteNombre,fecha) VALUES ('"
					+ resID + "','" + purganteNombre + "','" + fecha + "')");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void insertPurganteMultiple(ArrayList<String> ids, String purgante, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("INSERT INTO res_tiene_purgantes (resID,purganteNombre,fecha) VALUES "
					+ SQLUtils.concatenarAplicar(ids, purgante, fecha));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void insertPurganteMultipleSegunda(ArrayList<Res> reses, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("INSERT INTO res_tiene_purgantes (resID,purganteNombre,fecha) VALUES "
					+ SQLUtils.concatenarSegundoAplicarPurgantes(reses, fecha));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// seleccionar purgante
	public static Stack<Purgante> selectPurgantes(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Purgante> purgantes = new ArrayList<Purgante>();
		Stack<Purgante> purgantespila = new Stack<>();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res_tiene_purgantes WHERE resID='" + resID + "'");

			while (result.next()) {

				String purgante_Nombre = result.getString(3);
				String fecha = result.getString(4);

				Purgante purgante_Actual = new Purgante(purgante_Nombre);
				purgante_Actual.setFecha(fecha);
				purgantes.add(purgante_Actual);
				purgantespila.push(purgante_Actual);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return purgantespila;

	}

	// actualizar purgante
	public static void updatePurgante(String resID, String fechaVieja, Purgante purgante) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res_tiene_purgantes SET purganteNombre='" + purgante.getNombre()
							+ "', fecha='" + purgante.getFecha() + "' WHERE resID='" + resID + "' AND fecha='"
							+ fechaVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// insert vitamina
	public static void insertVitamina(String resID, String vitaminaNombre, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			VitaminaCRUD.insert(vitaminaNombre);
			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vitaminas (resID,vitaminaNombre,fecha) VALUES ('"
					+ resID + "','" + vitaminaNombre + "','" + fecha + "')");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void insertVitaminaMultiple(ArrayList<String> ids, String vitamina, String fecha) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("INSERT INTO res_tiene_vitaminas (resID,vitaminaNombre,fecha) VALUES "
					+ SQLUtils.concatenarAplicar(ids, vitamina, fecha));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// seleccionar
	public static ArrayList<Vitamina> selectVitaminas(String resID) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Vitamina> vitaminas = new ArrayList<Vitamina>();

		try {
			ResultSet result = sql.getStatement()
					.executeQuery("SELECT * FROM res_tiene_vitaminas WHERE resID='" + resID + "'");

			while (result.next()) {

				String vitamina_Nombre = result.getString(3);
				String fecha = result.getString(4);

				Vitamina vitamina_Actual = new Vitamina(vitamina_Nombre);
				vitamina_Actual.setFecha(fecha);
				vitaminas.add(vitamina_Actual);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return vitaminas;

	}

	// actualizar purgante
	public static void updateVitamina(String resID, String fechaVieja, Vitamina vitamina) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res_tiene_vitaminas SET vitaminaNombre='" + vitamina.getNombre()
							+ "', fecha='" + vitamina.getFecha() + "' WHERE resID='" + resID + "' AND fecha='"
							+ fechaVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static ArrayList<Res> reporteDestete(String potrero) {

		ArrayList<Res> vacas_destete = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = PotreroCRUD.selectRes(potrero);

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);
			if (res.getFecha_nacimiento() != null && !res.getFecha_nacimiento().equals("")
					&& !res.getFecha_nacimiento().equalsIgnoreCase("SIN REGISTRO")) {

				long dias = diasEntreFechas(res.getFecha_nacimiento());
				long meses = mesesEntreFechas(res.getFecha_nacimiento());

				if (res.getTipo().equals("CH") || res.getTipo().equals("CM")) {

					if (dias <= 250 && dias >= 220) {

						vacas_destete.add(res);

					}

				}

			}
		}

		return vacas_destete;

	}
	
	public static ArrayList<Res> reporteAlertas(String potrero){
		
		ArrayList<Res> vacas_alertas =new ArrayList<>();
		
		Res res = null;
		
		ArrayList<Res> reses = PotreroCRUD.selectRes(potrero);
		
		
		for (int i = 0; i < reses.size(); i++) {
			
			
			res = reses.get(i);
			
			

				
			if (res.getFecha_UltimoEmbarazo()!=null) {

			if(!res.getFecha_UltimoEmbarazo().equalsIgnoreCase("") && !res.getFecha_UltimoEmbarazo().equalsIgnoreCase("null")) {
			
				long dias = diasEntreFechas(res.getFecha_UltimoEmbarazo());
				long meses = mesesEntreFechas(res.getFecha_UltimoEmbarazo());
			
				
			 if(dias>810) {
				 vacas_alertas.add(res);
			 }	
			
			}
		
			
			}
		}
		
		return vacas_alertas;
		
	}

	public static ArrayList<Res> reportePartos(String potrero) {

		ArrayList<Res> vacas_partos = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = PotreroCRUD.selectRes(potrero);

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			if (res.getTipo().equals("NV") || res.getTipo().equals("VH")) {

				if (res.getEmbarazada() == 1) {

					if (!res.getFecha_embarazo().equals("")
							&& !res.getFecha_embarazo().equalsIgnoreCase("SIN REGISTRO")) {

						long dias = diasEntreFechas(res.getFecha_embarazo());
						long meses = mesesEntreFechas(res.getFecha_embarazo());

						if (dias <= 280 && dias >= 230) {

							vacas_partos.add(res);

						}
					}
				}
			}
		}

		return vacas_partos;

	}

	public static ArrayList<Res> reportePurgado(String potrero) {

		ArrayList<Res> reses_purgado = new ArrayList<>();

		ArrayList<Res> reses = PotreroCRUD.selectRes(potrero);

		Res res = null;

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			Stack<Purgante> resess = selectPurgantes(res.getResID());
			res.setPurgantes(resess);

			if (resess != null) {

				int tamanio = resess.size();

				if (tamanio > 0) {
					Purgante ultimo = resess.peek();

					if (ultimo != null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());

							if (dias > 10 && resess.size() % 2 != 0) {

								reses_purgado.add(res);

							}
						}

					}

				}
			}

		}

		return reses_purgado;

	}

	public static ArrayList<Res> reporteVacunaNotificaciones(String potrero) {

		ArrayList<Res> reses_vacuna = new ArrayList<>();

		ArrayList<Res> reses = PotreroCRUD.selectRes(potrero);
		Res res = null;

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			Stack<Vacuna> resess = selectVacunas(res.getResID());
			res.setVacunas(resess);

			if (resess != null) {

				int tamanio = resess.size();

				if (tamanio > 0) {
					Vacuna ultimo = resess.peek();

					if (ultimo != null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());
							long meses = mesesEntreFechas(ultimo.getFecha());

							if (dias < 195 && dias > 160) {

								reses_vacuna.add(res);

							}
						}

					}

				}
			}

		}

		return reses_vacuna;

	}

	public static ArrayList<Res> reporteDestete1() {

		ArrayList<Res> vacas_destete = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = select();

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);
			if (res.getFecha_nacimiento() != null && !res.getFecha_nacimiento().equals("")
					&& !res.getFecha_nacimiento().equalsIgnoreCase("SIN REGISTRO")) {

				long dias = diasEntreFechas(res.getFecha_nacimiento());
				long meses = mesesEntreFechas(res.getFecha_nacimiento());

				if (res.getTipo().equals("CH") || res.getTipo().equals("CM")) {

					if (dias <= 250 && dias >= 220) {

						vacas_destete.add(res);

					}

				}

			}
		}

		return vacas_destete;

	}

	public static ArrayList<Res> reportePartos1() {

		ArrayList<Res> vacas_partos = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = select();

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			if (res.getTipo().equals("NV") || res.getTipo().equals("VH")) {

				if (res.getEmbarazada() == 1) {

					if (!res.getFecha_embarazo().equals("")
							&& !res.getFecha_embarazo().equalsIgnoreCase("SIN REGISTRO")) {

						long dias = diasEntreFechas(res.getFecha_embarazo());
						long meses = mesesEntreFechas(res.getFecha_embarazo());

						if (dias <= 280 && dias >= 230) {

							vacas_partos.add(res);

						}
					}
				}
			}
		}

		return vacas_partos;

	}

	public static ArrayList<Res> reportePurgado1() {

		ArrayList<Res> reses_purgado = new ArrayList<>();

		ArrayList<Res> reses = select();

		Res res = null;

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			Stack<Purgante> resess = selectPurgantes(res.getResID());
			res.setPurgantes(resess);

			if (resess != null) {

				int tamanio = resess.size();

				if (tamanio > 0) {
					Purgante ultimo = resess.peek();

					if (ultimo != null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());

							if (dias > 10 && resess.size() % 2 != 0) {

								reses_purgado.add(res);

							}
						}

					}

				}
			}

		}

		return reses_purgado;

	}

	public static ArrayList<Res> reporteVacunaNotificaciones1() {

		ArrayList<Res> reses_vacuna = new ArrayList<>();

		ArrayList<Res> reses = select();

		Res res = null;

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			Stack<Vacuna> resess = selectVacunas(res.getResID());
			res.setVacunas(resess);

			if (resess != null) {

				int tamanio = resess.size();

				if (tamanio > 0) {
					Vacuna ultimo = resess.peek();

					if (ultimo != null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());
							long meses = mesesEntreFechas(ultimo.getFecha());

							if (dias < 195 && dias > 160) {

								reses_vacuna.add(res);

							}
						}

					}

				}
			}

		}

		return reses_vacuna;

	}

	public static void actualizarTipo() {

		ArrayList<Res> reses = select();

		Res res = null;
		Res cria = null;

		for (int i = 0; i < reses.size(); i++) {

			ArrayList<Res> crias = selectCria(reses.get(i).getResID());
			res = reses.get(i);

			if (res.getTipo().equalsIgnoreCase("HL")) {

				String fecha_nacimiento = res.getFecha_nacimiento();

				if (fecha_nacimiento != null && !fecha_nacimiento.equalsIgnoreCase("")) {

					long meses = mesesEntreFechas(fecha_nacimiento);
					long dias = diasEntreFechas(fecha_nacimiento);

					if (meses >= 24) {

						res.setTipo("NV");
						update(res.getResID(), res);
					}
				}
			}

			if (res.getGenero().equalsIgnoreCase("M")) {

				String fecha_nacimiento = res.getFecha_nacimiento();

				if (res.getTipo().equalsIgnoreCase("ML")) {

					if (fecha_nacimiento != null && !fecha_nacimiento.equalsIgnoreCase("")) {

						long meses = mesesEntreFechas(fecha_nacimiento);
						long dias = diasEntreFechas(fecha_nacimiento);

						if (meses == 24 && dias < 735) {

							res.setTipo("MC");
							update(res.getResID(), res);
						}
					}

				}
			}

		}

	}

	public static long mesesEntreFechas(String fechaIn) {

		int difM = 0;

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "/" + mes + "/" + anio;
		try {
			Calendar inicio = new GregorianCalendar();
			Calendar fin = new GregorianCalendar();
			inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fechaIn));
			fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(fecha_Convertida));
			int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
			difM = difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
		} catch (ParseException ex) {

		}

		return Math.abs(difM);

	}

	public static long diasEntreFechas(String fechaIn) {

		long dias_1 = 0;
		long dias = 0;

		Date fecha_In = ParseFecha(fechaIn);

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "/" + mes + "/" + anio;

		Date fecha_system = ParseFecha(fecha_Convertida);

		long diff = fecha_In.getTime() - fecha_system.getTime();

		dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		dias_1 = Math.abs(dias);

		return dias_1;

	}

	public static Date fecha_sistema() {

		Calendar fechaSystem = new GregorianCalendar();

		int dia = fechaSystem.get(Calendar.DAY_OF_MONTH);
		int mes = fechaSystem.get(Calendar.MONTH) + 1;
		int anio = fechaSystem.get(Calendar.YEAR);

		String fecha_Convertida = dia + "/" + mes + "/" + anio;

		Date fecha_system = ParseFecha(fecha_Convertida);

		return fecha_system;
	}

	public static String calcDate(String date1) {

		String message = "";
		Date fechauno = ParseFecha(date1);
		Date fechaactual = fecha_sistema();

		double diff = Math.floor(fechauno.getTime() - fechaactual.getTime());
		double day = 1000 * 60 * 60 * 24;

		int days = (int) Math.abs(Math.floor(diff / day) + 1);
		int months = (int) Math.abs(Math.floor(days / 30));
		int years = (int) Math.abs(Math.floor(months / 12));

		if (months == 0) {
			message = "";
			// message += " Faltan ";
			message += days + " dias ";

		} else if (months != 0 && years == 0) {
			message = "";
			// message += " Faltan ";
			message += months + " meses y ";
			int diasres = days - (months * 30);
			message += diasres + " dias ";

		} else if (years != 0) {
			message = "";
			// message += " Faltan ";
			message += years + " años y ";
			int mesesres = months - (years * 12);
			message += mesesres + " meses ";
			int dias = days - (months * 30);
			message += dias + " dias ";
		}

		return message;
	}

	public static Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

}
