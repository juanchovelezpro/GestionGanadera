package model;

import java.util.ArrayList;

import db.PotreroCRUD;
import db.ResCRUD;

public class Estadisticas {

	/**
	 * Estadisticas generales Cantidad de potreros Cantidad de reses totales
	 * Cantidad de hembras totales Cantidad de CH Cantidad de HL Cantidad de NV
	 * Cantidad de VH Cantidad de VP Cantidad de machos totales Cantidad de CM
	 * Cantidad de ML Cantidad de MC Cantidad de TP
	 **/

	public static ArrayList<Integer> estadisticageneral() {

		ArrayList<Integer> mensaje = new ArrayList<>();

		ArrayList<Res> resesAntes = ResCRUD.select();
		ArrayList<Res> resess = new ArrayList<>();

		
		for (int i = 0; i < resesAntes.size(); i++) {
			
			if (resesAntes.get(i).getVivo()==1) {
				
				resess.add(resesAntes.get(i));
			}
		}		

		ArrayList<Potrero> potrero = PotreroCRUD.select();

		Res res = null;

		int potreros = potrero.size();
		int reses = resess.size();
		int hembras = 0;
		int machos = 0;
		int ch = 0;
		int hl = 0;
		int nv = 0;
		int vh = 0;
		int vp = 0;
		int cm = 0;
		int ml = 0;
		int mc = 0;
		int tp = 0;

		for (int i = 0; i < resess.size(); i++) {

			res = resess.get(i);

			if (res.getGenero().equalsIgnoreCase("H")) {
				hembras++;

				switch (res.getTipo()) {

				case "CH":

					ch++;

					break;

				case "HL":

					hl++;

					break;

				case "NV":

					nv++;

					break;

				case "VH":

					vh++;

					break;

				case "VP":

					vp++;
					break;

				}

			}

			if (res.getGenero().equalsIgnoreCase("M")) {
				machos++;

				switch (res.getTipo()) {

				case "CM":

					cm++;

					break;

				case "ML":

					ml++;

					break;

				case "MC":

					mc++;

					break;

				case "TP":

					tp++;

					break;

				}

			}

		}

		mensaje.add(potreros);
		mensaje.add(reses);
		mensaje.add(hembras);
		mensaje.add(machos);
		mensaje.add(ch);
		mensaje.add(hl);
		mensaje.add(nv);
		mensaje.add(vh);
		mensaje.add(vp);
		mensaje.add(cm);
		mensaje.add(ml);
		mensaje.add(mc);
		mensaje.add(tp);

		return mensaje;
	}

	/**
	 * Estadisticas por potrero Cantidad de reses totales Cantidad de hembras
	 * totales Cantidad de CH Cantidad de HL Cantidad de NV Cantidad de VH Cantidad
	 * de VP Cantidad de machos totales Cantidad de CM Cantidad de ML Cantidad de MC
	 * Cantidad de TP
	 **/

	public static ArrayList<Integer> estadisticasPorPotrero(String nombrePotrero) {

		ArrayList<Integer> mensaje = new ArrayList<>();

		ArrayList<Res> resess = PotreroCRUD.selectResesNormales(nombrePotrero);

		Res res = null;

		int reses = resess.size();
		int hembras = 0;
		int machos = 0;
		int ch = 0;
		int hl = 0;
		int nv = 0;
		int vh = 0;
		int vp = 0;
		int cm = 0;
		int ml = 0;
		int mc = 0;
		int tp = 0;

		for (int i = 0; i < resess.size(); i++) {

			res = resess.get(i);

			if (res.getGenero().equalsIgnoreCase("H")) {
				hembras++;

				switch (res.getTipo()) {

				case "CH":

					ch++;

					break;

				case "HL":

					hl++;

					break;

				case "NV":

					nv++;

					break;

				case "VH":

					vh++;

					break;

				case "VP":

					vp++;
					break;

				}

			}

			if (res.getGenero().equalsIgnoreCase("M")) {
				machos++;

				switch (res.getTipo()) {

				case "CM":

					cm++;

					break;

				case "ML":

					ml++;

					break;

				case "MC":

					mc++;

					break;

				case "TP":

					tp++;

					break;

				}

			}

		}

		mensaje.add(reses);
		mensaje.add(hembras);
		mensaje.add(machos);
		mensaje.add(ch);
		mensaje.add(hl);
		mensaje.add(nv);
		mensaje.add(vh);
		mensaje.add(vp);
		mensaje.add(cm);
		mensaje.add(ml);
		mensaje.add(mc);
		mensaje.add(tp);

		return mensaje;
	}

}
