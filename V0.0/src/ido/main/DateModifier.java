//@author A0113768Y

package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateModifier {

	/*
	 * Obtain today's date in a String with ddmmyy format
	 */
	public static String getCurrDate() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date currDate = new Date();
		String currDateString = dateFormat.format(currDate);
		return currDateString;
	}

	/*
	 * Obtain one day after the currDateString date in a String with ddmmyy format
	 */
	public static String getNextDate(String currDateString) {
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

	/*
	 * Obtain one day before the currDateString date in a String with ddmmyy format
	 */
	public static String getPrevDate(String currDateString) {
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

	/*
	 * Returns a date that is i away from currDateString's date
	 * Pre-cond: currDateString is in ddmmyy format
	 * Post-condition: returns a String date with ddmmyy format
	 */
	public static String getParticularDate(String currDateString, int i) {
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

	/*
	 * Returns a custom date format for agenda heading in GUI 
	 * Pre-cond: myDate is in ddmmyy format
	 * Post-condition: returns a date with format e.g. 01 January 2014
	 */	
	public static String getAgendaHeading(String myDate) {
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		String headingDate = myDate.substring(0, 2);
		String headingMonth = months[Integer.valueOf(myDate.substring(2, 4)) - 1];
		String headingYear = "20" + myDate.substring(4, 6);
		return headingDate + " " + headingMonth + " " + headingYear;
	}

	/*
	 * Validate the input as a valid date with ddmmyy format
	 */
	public static boolean isValidDate(String input) {
		if (input.length() == 6) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(input.trim());
			} catch (ParseException pe) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Translate the input for view command in parser into acceptable date format
	 * Pre-cond: input is a valid for view command
	 * Post-condition: returns a date with format e.g. 01 January 2014
	 */	
	public static String convertInputViewToDate(String input) {
		String result;
		switch (input) {
		case "today":
			result = DateModifier.getCurrDate();
			break;
		case "tomorrow":
			;
		case "tomo":
			result = DateModifier.getParticularDate(DateModifier.getCurrDate(),
					1);break;
		case "next week":
			result = DateModifier.getParticularDate(DateModifier.getCurrDate(),
					7); break;
		case "next month":
			result = DateModifier.getParticularDate(DateModifier.getCurrDate(),
					30); break;
		default:
			result = input;

		}
		return result;
	}
}
