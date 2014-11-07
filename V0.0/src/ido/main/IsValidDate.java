package ido.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@author A0114813N
public class IsValidDate {
	private static final String ERROR_HEADING = "ERROR";
	private static final String ERROR_DATE = "Invalid Date!";
	private static final String ARCHIVES_CHECK = "archives";
	private static final String OVERDUE_CHECK = "overdue";
	private static final String FLOATING_CHECK = "-";
	private static final String DATE_FORMAT = "ddMMyy";
	private static final String TODAY_CHECK = "today";
	private static final String TOMORROW_CHECK = "tomorrow";

	static String date;

	public IsValidDate(String test) {
		date = test;
	}

	public static String validateDate(String date) {
		if (date.toLowerCase().equals(TODAY_CHECK)) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			Date currDate = new Date();
			return dateFormat.format(currDate);
		} else if (date.toLowerCase().equals(TOMORROW_CHECK)) {
			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			long currTime = new Date().getTime();
			Date currDate = new Date(currTime + (24 * 3600 * 1000));
			return dateFormat.format(currDate);
		} else if ((date.equals(FLOATING_CHECK))
				|| (date.equals(ARCHIVES_CHECK))
				|| (date.equals(OVERDUE_CHECK))) {
			return date;
		} else {
			new IsValidDate(date);
			if (IsValidDate.testValidDate()) {
				return date;
			}
		}
		try {
			int box = Integer.valueOf(date);
			if (box >= 1 && box <= 7) {
				DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				Date currDate = new Date();
				return DateModifier.getParticularDate(
						dateFormat.format(currDate), box - 1);
			}
		} catch (Exception e) {
			// do nothing
		}
		WarningPopUp.infoBox(ERROR_DATE, ERROR_HEADING);
		return "";
	}

	public static boolean testValidDate() {

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date.trim());
		} catch (ParseException pe) {
			// System.out.println("F");
			return false;
		}
		return true;
	}
}
