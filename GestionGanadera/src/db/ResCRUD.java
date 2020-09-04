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

public class ResCRUD {

	// Agrega una res a la tabla res de la base de datos.
	public static void insert(Res res) {

		SQLConnection sql = SQLConnection.getInstance();

		String values = "'" + res.getResID() + "','" + res.getTipo() + "','" + res.getGenero() + "','" + res.getColor()
				+ "','" + res.getFecha_nacimiento() + "','" + res.getObservaciones() + "'," + res.getVivo() + ","
				+ res.getEmbarazada() + ",'" + res.getFecha_embarazo() + "','" + res.getFecha_ultimo_purgado() + "','"
				+ res.getFecha_ultimo_vacunado() + "','" + res.getMadreID() + "','" + res.getPotreroNombre() + "'";

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
				String fecha_ultimo_purgado = result.getString(10);
				String fecha_ultimo_vacunado = result.getString(11);
				String madreID = result.getString(12);
				String potreroNombre = result.getString(13);

				res = new Res(res_ID, genero, tipo, color, vivo, fecha_nacimiento, observaciones, embarazada,
						fecha_embarazo, madreID, fecha_ultimo_purgado, fecha_ultimo_vacunado, potreroNombre);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return res;
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
					+ "', potreroNombre='" + res.getPotreroNombre() + "' WHERE numero='" + id + "'");

			updatePesosRes(id, res.getResID());
			PurganteCRUD.updatePurganteRes(id, res.getResID());
			VacunaCRUD.updateVacunaRes(id, res.getResID());
			updateMadreaCria(id, res.getResID());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<Res> selectCustom(String criteria) {

		SQLConnection sql = SQLConnection.getInstance();
		ArrayList<Res> vacas = new ArrayList<>();

		try {

			ResultSet result = sql.getStatement().executeQuery("SELECT * FROM res WHERE " + criteria);

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

	// update madreIDencrias

	public static void updateMadreaCria(String idVieja, String idNueva) {

		SQLConnection sql = SQLConnection.getInstance();

		try {
			sql.getStatement()
					.executeUpdate("UPDATE res SET madreID='" + idNueva + "' WHERE madreID='" + idVieja + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Elimina una res de la base de datos.
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

	// Trasladar reses de un potrero a otro
	public static void trasladar(ArrayList<String> idsRes, String potreroOrigen, String potreroDestino) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate("UPDATE res SET potreroNombre='" + potreroDestino + "' WHERE numero IN ("
					+ SQLUtils.concatenarValores(idsRes) + ")");

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

	}

	// seleccion una cria
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
					+ SQLUtils.concatenarAplicarVacunas(ids, vacuna, fecha));
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

	public static void deletePesosRes(ArrayList<String> idsRes) {

		SQLConnection sql = SQLConnection.getInstance();

		try {

			sql.getStatement().executeUpdate(
					"DELETE FROM res_tiene_pesos WHERE resID IN (" + SQLUtils.concatenarValores(idsRes) + ")");
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

	public static ArrayList<Res> reporteDestete() {

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

					if (dias <= 250 && (meses == 8 || meses == 9)) {

						// vacas_destete.add("Es momento de realizar el destete a la res: " +
						// res.getResID() + " Del potrero: "
						// + res.getPotreroNombre() + " Con fecha de nacimiento " +
						// res.getFecha_nacimiento());
						vacas_destete.add(res);

					}

				}

			}
		}

		return vacas_destete;

	}

	public static ArrayList<Res> reportePartos() {

		ArrayList<Res> vacas_partos = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = select();

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			System.out.println(res + "primera prueba");

			if (res.getTipo().equals("NV")) {

				if (res.getEmbarazada() == 1) {

					if (!res.getFecha_embarazo().equals("")
							&& !res.getFecha_embarazo().equalsIgnoreCase("SIN REGISTRO")) {

						System.out.println(res.getFecha_embarazo() + "fecha");
						long dias = diasEntreFechas(res.getFecha_embarazo());
						long meses = mesesEntreFechas(res.getFecha_embarazo());

						if (dias <= 280 && (meses == 9 || meses == 10)) {

							vacas_partos.add(res);

						}
						System.out.println(vacas_partos.size());
					}
				}
			}
		}

		return vacas_partos;

	}

	public static ArrayList<Res> reportePeso() {

		ArrayList<Res> vacas_pesos = new ArrayList<>();

		Res res = null;

		ArrayList<Res> reses = select();

		for (int i = 0; i < reses.size(); i++) {

			res = reses.get(i);

			Peso peso_actual = selectPesos(res.getResID()).pop();

			long dias = diasEntreFechas(peso_actual.getFecha());
			long meses = mesesEntreFechas(peso_actual.getFecha());

			if (dias <= 182 && meses == 6) {

				vacas_pesos.add(res);
			}

		}

		return vacas_pesos;

	}

	public static ArrayList<Res> reportePurgado() {

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

						// if (selectPurgantes(res.getResID()).peek()!=null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());

							if (dias > 10 && resess.size() % 2 != 0) {

								reses_purgado.add(res);

							}
						}
						// }

					}

				}
			}

		}

		return reses_purgado;

	}

	public static ArrayList<Res> reporteVacunaNotificaciones() {

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

						// if (selectPurgantes(res.getResID()).peek()!=null) {

						if (ultimo.getFecha() != null && ultimo.getNombre() != null) {

							long dias = diasEntreFechas(ultimo.getFecha());
							long meses = mesesEntreFechas(ultimo.getFecha());

							if (dias < 195 && meses == 6) {

								reses_vacuna.add(res);

							}
						}
						// }

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
			for (int j = 0; j < reses.size(); j++) {

				res = reses.get(i);
				cria = reses.get(j);

				if (res.getGenero().equalsIgnoreCase("H")) {

					if (res.getTipo().equalsIgnoreCase("VP")) {

						if (cria.getMadreID().equals(res.getResID())) {

							if (cria.getTipo() == "HL") {

								res.setTipo("VH");
								update(res.getResID(), res);
							}

						}

					}

					if (res.getTipo().equalsIgnoreCase("HL")) {

						String fecha_nacimiento = cria.getFecha_nacimiento();
						long meses = mesesEntreFechas(fecha_nacimiento);
						long dias = diasEntreFechas(fecha_nacimiento);

						if (meses == 24 && dias < 735) {

							res.setTipo("NV");
							update(res.getResID(), res);
						}
					}

					if (res.getTipo().equalsIgnoreCase("VH")) {

						if (res.getEmbarazada() == 1) {
							String fecha_nacimiento = cria.getFecha_embarazo();
							long meses = mesesEntreFechas(fecha_nacimiento);
							long dias = diasEntreFechas(fecha_nacimiento);

							if (meses <= 9 && dias < 275) {

								res.setTipo("VP");
								update(res.getResID(), res);
							}
						}
					}
				}

				if (res.getGenero().equalsIgnoreCase("M")) {

					if (res.getTipo().equalsIgnoreCase("ML")) {

						String fecha_nacimiento = cria.getFecha_nacimiento();
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
			// System.out.println(difM);
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

		// float days = (diff / (1000*60*60*24));

		dias_1 = Math.abs(dias);

		// org.joda.time.format.DateTimeFormatter dateStringFormat = DateTimeFormat
		/// .forPattern("dd/MM/yyyy");

		// DateTime firstTime = dateStringFormat.parseDateTime(fechaIn);
		/// DateTime secondTime = dateStringFormat.parseDateTime(fecha_Convertida);
		// long days = Days.daysBetween(new LocalDate(firstTime),
		// new LocalDate(secondTime)).getDays();

		// long days1 =Days.daysBetween(firstTime, secondTime).getDays();

		// System.out.println(days1 + "esta es la segunda opcion");

		// dias_1 =days;

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

	public static void main(String[] args) {

//	insert("101","VH", "H", "negro", "12/12/2000", "bonito", 1, 0, null, "12/12/2018", "12/12/2018", null, "povelez");
//		insert("102","VH", "M", "negro", "12/12/2000", "bonito", 1, 0, null, "12/12/2018", "12/12/2018", null, "povelez");
//		insert("103","VH", "M", "negro", "12/12/2000", "bonito", 1, 0, null, "12/12/2018", "12/12/2018", "101", "povelez");
		// insert("104","VH", "H", "negro", "12/12/2000", "bonito", 1, 0, null,
		// "12/12/2018", "12/12/2018", null, "popaz");
		// insert("105","VH", "M", "negro", "12/12/2000", "bonito", 1, 0, null,
		// "12/12/2018", "12/12/2018", null, "popaz");
		// insert("106","VH", "M", "negro", "12/12/2000", "bonito", 1, 0, null,
		// "12/12/2018", "12/12/2018", "104", "popaz");

		/// System.out.println(select().size());

		// Res resita = select().get(1);
		// resita.setColor("BLANCO");

		// Res resita2 = select().get(4);
		// resita2.setColor("BLANCO");

		// update("102", resita);
		// update("105", resita2);

		// delete("105");

		// System.out.println(selectCria("101"));

		// insertPeso("104", 150.6, "12/12/2012");
		// insertPeso("105", 200.6, "12/06/2013");
		// insertPeso("106", 200.7, "12/12/2013");

		// insertPeso("106", 250.6, "12/12/2013");

		// System.out.println(selectPesos("102").size());

		// updatePeso("102", "12/12/2013", new Peso(300, "13/12/2015"));

		// PurganteCRUD.insert("purgantebueno");
		// VacunaCRUD.insert("vacunabuena");

		// insertPurgante("101", "purgantebueno", "15/01/2010");
		// insertPurgante("102", "purgantebueno", "15/07/2010");

		// insertVacuna("101", "vacunabuena", "01/01/2000");
		// insertVacuna("102", "vacunabuena", "01/07/2000");

		// insertPurgante("104", "purgantebueno", "15/01/2010");
		// insertPurgante("105", "purgantebueno", "15/07/2010");

		// insertVacuna("104", "vacunabuena", "01/01/2000");
		// insertVacuna("105", "vacunabuena", "01/07/2000");

		// System.out.println(selectPurgantes("104").size());
		// System.out.println(selectPurgantes("103").size());
		// System.out.println(selectVacunas("104").size());

		// Purgante purgan =new Purgante("purgantemalo");
		// purgan.setFecha("21/12/2020");

		// Vacuna vacun =new Vacuna("vacunamalo");
		// vacun.setFecha("22/12/2020");

		// PurganteCRUD.insert("purgantemalo");
		// VacunaCRUD.insert("vacunamalo");

		// updatePurgante("104", "15/01/2010", purgan);
		// updateVacuna("104", "01/01/2000", vacun);

		// System.out.println(select().size());

		// delete("104");
		// delete("102");

	}

}
