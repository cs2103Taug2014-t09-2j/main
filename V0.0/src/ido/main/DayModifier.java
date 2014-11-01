package ido.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DayModifier {
	private static final String OVERDUE_TASK = "overdue";
	private static final String GENERAL_TASK = "general";
	private static final String ARCHIVES_TASK = "archives";
	
	public static String getDayOfWeek(String currDateStr) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
		String dayOfWeekStr = "";
		
		if (currDateStr.equals(OVERDUE_TASK)||currDateStr.equals(GENERAL_TASK)||currDateStr.equals(ARCHIVES_TASK)) {
			dayOfWeekStr = currDateStr;
		} else {
			try {
				dayOfWeekStr = sdf2.format(sdf1.parse(currDateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dayOfWeekStr;
	}

}
