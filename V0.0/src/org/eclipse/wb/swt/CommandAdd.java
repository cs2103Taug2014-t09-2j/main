package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommandAdd {
	String date;
	String task;
	String time;

	public CommandAdd(String date, String time, String task){
		this.date = date;
		try{
			this.time = IsValidTime.validateTime(time);
		}
		catch (Exception e){
			WarningPopUp.infoBox("Invalid Time!",
					"ERROR");
			e.printStackTrace();
		}
		this.task = task;
	}
	
	public void addTask() {
		// Create the name of the text file
		String fileName = date + ".txt";
		if (date.equals("-")){
			fileName = "general.txt";
		}
		String content = "[" + time + "] " + task;
		if (time.equals("-")){
			content = "[all-day] "+ task;
		}
		ArrayList<String> list = new ArrayList<String>();
		// Check if the text file exists, if it does, add its content to
		// a list and return the list
		list = (new FileAccessor(fileName)).readContents();
		list.add(content);
		(new FileAccessor(fileName, list)).writeContents();
	}
}
