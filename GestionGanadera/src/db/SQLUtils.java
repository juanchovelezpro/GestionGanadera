package db;

import java.util.ArrayList;

import model.Purgante;
import model.Res;
import model.Vacuna;

public class SQLUtils {

	public static String concatenarValores(ArrayList<String> valores) {

		String resultado = "";

		for (int i = 0; i < valores.size(); i++) {

			if (i < valores.size() - 1)
				resultado += "\"" + valores.get(i) + "\",";
			else
				resultado += "\"" + valores.get(i) + "\"";

		}

		return resultado;

	}

	public static String concatenarReses(ArrayList<Res> reses) {

		String resultado = "";

		for (int i = 0; i < reses.size(); i++) {

			Res resita = reses.get(i);
			String numero = resita.getResID();
			String tipo = resita.getTipo();
			String genero = resita.getGenero();
			String color = resita.getColor();
			String fecha_nac = resita.getFecha_nacimiento();
			String observaciones = resita.getObservaciones();
			int vivo = resita.getVivo();
			int embarazada = resita.getEmbarazada();
			String fechaEmbarazo = resita.getFecha_embarazo();
//			String fecha

			if (i < reses.size() - 1) {

				resultado += "(\"";

			} else {

			}

		}

		return resultado;

	}

	public static String concatenarAplicarVacunas(ArrayList<String> ids, String vacuna, String fecha) {

		String resultado = "";

		for (int i = 0; i < ids.size(); i++) {

			if (i < ids.size() - 1)
				resultado += "(\"" + ids.get(i) + "\",\"" + vacuna + "\",\"" + fecha + "\"),";
			else
				resultado += "(\"" + ids.get(i) + "\",\"" + vacuna + "\",\"" + fecha + "\")";

		}

		return resultado;

	}

	public static String concatenarSegundoAplicarVacunas(ArrayList<Res> reses, String fecha) {

		String resultado = "";

		for (int i = 0; i < reses.size(); i++) {

			Res resita = reses.get(i);

			if (i < reses.size() - 1)
				resultado += "(\"" + resita.getResID() + "\",\""
						+ resita.getVacunas().get(resita.getVacunas().size() - 1).getNombre() + "\",\"" + fecha
						+ "\"),";
			else
				resultado += "(\"" + resita.getResID() + "\",\""
						+ resita.getVacunas().get(resita.getVacunas().size() - 1).getNombre() + "\",\"" + fecha + "\")";

		}

		return resultado;

	}

	public static String concatenarAplicarPurgantes(ArrayList<String> ids, String purgante, String fecha) {

		String resultado = "";

		for (int i = 0; i < ids.size(); i++) {

			if (i < ids.size() - 1)
				resultado += "(\"" + ids.get(i) + "\",\"" + purgante + "\",\"" + fecha + "\"),";
			else
				resultado += "(\"" + ids.get(i) + "\",\"" + purgante + "\",\"" + fecha + "\")";

		}

		return resultado;

	}

	public static String concatenarSegundoAplicarPurgantes(ArrayList<Res> reses, String fecha) {

		String resultado = "";

		for (int i = 0; i < reses.size(); i++) {

			Res resita = reses.get(i);

			if (i < reses.size() - 1)
				resultado += "(\"" + resita.getResID() + "\",\""
						+ resita.getPurgantes().get(resita.getPurgantes().size() - 1).getNombre() + "\",\"" + fecha
						+ "\"),";
			else
				resultado += "(\"" + resita.getResID() + "\",\""
						+ resita.getPurgantes().get(resita.getPurgantes().size() - 1).getNombre() + "\",\"" + fecha
						+ "\")";

		}

		return resultado;

	}

}
