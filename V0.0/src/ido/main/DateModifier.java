package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateModifier {
	
	public static String getCurrDate() {
		// obtain the current date
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date currDate = new Date();
		String currDateString = dateFormat.format(currDate);
		return currDateString;
	}
	
	public static String getNextDate(String currDateString){
		String dt = currDateString; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 1); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		currDateString = dt; 
		return currDateString;
	}
	
	public static String getPrevDate(String currDateString){
		String dt = currDateString; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, -1); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		currDateString = dt; 
		return currDateString;
	}
	
	public static String getParticularDate(String currDateString, int i){
		String dt = currDateString; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, i); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		currDateString = dt; 
		return currDateString;
	}
}
