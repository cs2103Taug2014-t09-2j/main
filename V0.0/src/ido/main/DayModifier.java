package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DayModifier {
	
	public static String getCurrDay() {
		// obtain the current date
		DateFormat dayFormat = new SimpleDateFormat("EEEE");
		Date currDay = new Date();
		String currDayStr = dayFormat.format(currDay);
		return currDayStr;
	}
	
	public static String getNextDay(String currDateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(currDateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 1); // number of days to add
		String nextDayStr = sdf.format(c.getTime());
		return nextDayStr;
	}
	
	public static String getPrevDay(String currDateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(currDateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, -1); // number of days to add
		String prevDayStr = sdf.format(c.getTime()); 
		return prevDayStr;
	}
	
	public static String getParticularDay(String currDateStr, int i){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(currDateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, i); // number of days to add
		String dayStr = sdf.format(c.getTime()); 
		return dayStr;
	}
}
