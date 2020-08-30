package db;

import java.util.ArrayList;

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

}
