package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IsValidDate {

	static String date;

	public IsValidDate(String test) {
		date = test;
		//System.out.println(date);
	}

	public static String validateDate(String date){
		if (date.toLowerCase().equals("today")){
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			Date currDate = new Date();
			return dateFormat.format(currDate);
		}
		else if (date.toLowerCase().equals("tomorrow")){
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			long currTime = new Date().getTime();
			Date currDate = new Date(currTime+(24*3600*1000));
			return dateFormat.format(currDate);
		}
		else if (date.equals("-")) {
			return date;
		} else {
			new IsValidDate(date);
			if ( IsValidDate.testValidDate()){
				return date;
			}
		}
		try{
			int box = Integer.valueOf(date);
			if (box>=1 && box<=7){
				DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
				Date currDate = new Date();
				return DateModifier.getParticularDate(dateFormat.format(currDate), box-1);
			}
		}
		catch (Exception e){
			//do nothing
		}
		WarningPopUp.infoBox("Invalid Date!",
				"ERROR");
		return "";
	}
	
	public static boolean testValidDate() {
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(date.trim());
			} catch (ParseException pe) {
				//System.out.println("F");
				return false;
			}
			return true;
	}
}
