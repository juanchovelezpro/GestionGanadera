package tools;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date convertDateToLong(String fecha) {

		String[] myFecha = fecha.split("/");
		int day = Integer.parseInt(myFecha[0]);
		int month = Integer.parseInt(myFecha[1]);
		int year = Integer.parseInt(myFecha[2]);

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		return cal.getTime();

	}

}
