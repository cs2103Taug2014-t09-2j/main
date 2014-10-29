package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayModifier {
	
	public static String getDayOfWeek(String currDateStr) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String dayOfWeekStr = "";
		try {
			dayOfWeekStr = sdf2.format(sdf1.parse(currDateStr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dayOfWeekStr;
	}

}
