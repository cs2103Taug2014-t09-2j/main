package ido.main;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandAdd {

	private static Logger logger = Logger.getLogger("CommandAdd");

	String date;
	String task;
	String time;

	public CommandAdd(String date, String time, String task) {
		this.date = date;
		try {
			this.time = IsValidTime.validateTime(time);
		} catch (Exception e) {
			WarningPopUp.infoBox("Invalid Time!", "ERROR");
			e.printStackTrace();
		}
		this.task = task;
	}

	public void addTask() {
		logger.log(Level.INFO, "add processing");

		// Create the name of the text file
		String fileName = date + ".txt";
		if (date.equals("-")) {
			fileName = "general.txt"; // adds to general file
		}
		String content = "[" + time + "] " + task;
		if (time.equals("-")) {
			content = "[all-day] " + task;
		}
		ArrayList<String> list = new ArrayList<String>();
		// Check if the text file exists, if it does, add its content to
		// a list and return the list
		(new FileAccessor(fileName)).checkFilesExistCustom(date);
		list = (new FileAccessor(fileName)).readContents();
		list.add(content);
		(new FileAccessor(fileName, list)).writeContents();
		
		boolean dateOutOfBox = true;
		for (int i = 0; i < 7; i++) {
			if (date.equals(DateModifier.getParticularDate(DateModifier.getCurrDate(), i))) {
				dateOutOfBox = false;
				break;
			}
		}
		if (dateOutOfBox) {
			WarningPopUp.infoBox("Task added!", "WARNING");
		}
		logger.log(Level.INFO, "add complete");
	}
}
