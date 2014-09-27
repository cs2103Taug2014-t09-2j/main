package org.eclipse.wb.swt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IsValidDate {

	static String date;

	public IsValidDate(String test) {
		date = test;
		//System.out.println(date);
	}

	public static boolean testValidDate() {
		
		// Condition required as parse does not use the entire length of string
		if (date.length() != 6) {
			return false;
			
		} else {
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
}
