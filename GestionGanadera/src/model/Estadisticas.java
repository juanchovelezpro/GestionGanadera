package model;

import java.util.ArrayList;

import db.PotreroCRUD;
import db.ResCRUD;

public  class Estadisticas {

	
	/**Estadisticas generales
	Cantidad de potreros
	Cantidad de reses totales
	Cantidad de hembras totales
	Cantidad de CH
	Cantidad de HL
	Cantidad de NV
	Cantidad de VH
	Cantidad de VP
	Cantidad de machos totales
	Cantidad de CM
	Cantidad de ML
	Cantidad de MC
	Cantidad de TP
	**/
	
	public static ArrayList<Integer> estadisticageneral (){
		
		ArrayList<Integer> mensaje =new ArrayList<>();
		
		ArrayList<Res> resess =ResCRUD.select();
		
		ArrayList<Potrero> potrero =PotreroCRUD.select();
		
		Res res =null;


		int potreros = potrero.size();
		int reses = resess.size();
		int hembras = 0;
		int machos =0;
		int ch =0;
		int hl =0;
		int nv =0;
		int vh=0;
		int vp=0;
		int cm= 0;
		int ml=0;
		int mc=0;
		int tp=0;
		
		for (int i = 0; i < resess.size(); i++) {
			
			res = resess.get(i);
			
			if (res.getGenero().equalsIgnoreCase("H")) {
				hembras++;
				
				if (res.getTipo().equalsIgnoreCase("CH")) {
					ch++;
				}else if (res.getTipo().equalsIgnoreCase("HL")) {
					hl++;
				}else if (res.getTipo().equalsIgnoreCase("NV")) {
					nv++;
				}else if (res.getTipo().equalsIgnoreCase("VH")) {
					vh++;
				}else if (res.getTipo().equalsIgnoreCase("VP")) {
					vp++;
				}
				
			}else if (res.getGenero().equalsIgnoreCase("M")) {
				machos++;
				
				if (res.getTipo().equalsIgnoreCase("CM")) {
					cm++;
				}else if (res.getTipo().equalsIgnoreCase("ML")) {
					ml++;
				}else if (res.getTipo().equalsIgnoreCase("MC")) {
					mc++;
				}else if (res.getTipo().equalsIgnoreCase("TP")) {
					tp++;
				
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
	
	
	
	/**Estadisticas por potrero
	Cantidad de reses totales
	Cantidad de hembras totales
	Cantidad de CH
	Cantidad de HL
	Cantidad de NV
	Cantidad de VH
	Cantidad de VP
	Cantidad de machos totales
	Cantidad de CM
	Cantidad de ML
	Cantidad de MC
	Cantidad de TP
	**/
	
	public static ArrayList<Integer> estadisticasPorPotrero(String nombrePotrero){
		
        ArrayList<Integer> mensaje =new ArrayList<>();
		
		ArrayList<Res> resess =PotreroCRUD.selectRes(nombrePotrero);
		
		
		Res res =null;

		int reses = resess.size();
		int hembras = 0;
		int machos =0;
		int ch =0;
		int hl =0;
		int nv =0;
		int vh=0;
		int vp=0;
		int cm= 0;
		int ml=0;
		int mc=0;
		int tp=0;
		
	for (int i = 0; i < resess.size(); i++) {
			
			res = resess.get(i);
			
			if (res.getGenero().equalsIgnoreCase("H")) {
				hembras++;
				
				if (res.getTipo().equalsIgnoreCase("CH")) {
					ch++;
				}else if (res.getTipo().equalsIgnoreCase("HL")) {
					hl++;
				}else if (res.getTipo().equalsIgnoreCase("NV")) {
					nv++;
				}else if (res.getTipo().equalsIgnoreCase("VH")) {
					vh++;
				}else if (res.getTipo().equalsIgnoreCase("VP")) {
					vp++;
				}
				
			}else if (res.getGenero().equalsIgnoreCase("M")) {
				machos++;
				
				if (res.getTipo().equalsIgnoreCase("CM")) {
					cm++;
				}else if (res.getTipo().equalsIgnoreCase("ML")) {
					ml++;
				}else if (res.getTipo().equalsIgnoreCase("MC")) {
					mc++;
				}else if (res.getTipo().equalsIgnoreCase("TP")) {
					tp++;
				
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
