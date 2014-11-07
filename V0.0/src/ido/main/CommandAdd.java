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

//@author A0114813N

public class CommandAdd {
	private static final String ALL_DAY_TASK = "[by-today] ";
	private static final int DATE_LENGTH = 6;
	private static final int BOX_VALUE = 1;
	private static final int TOTAL_BOX_VALUE = 7;
	private static final String FLOATING_CHECK = "-";
	private static final String ERROR_HEADING = "ERROR";
	private static final String ERROR_TIME = "Invalid Time!";
	private static final String MESSAGE_TASK = "Task Added!";
	private static Logger logger = Logger.getLogger("CommandAdd");

	String date;
	String task;
	String time;

	public CommandAdd(String date, String time, String task) {
		this.date = date;
		try {
			this.time = IsValidTime.validateTime(time);
		} catch (Exception e) {
			WarningPopUp.infoBox(ERROR_TIME, ERROR_HEADING);
			e.printStackTrace();
		}
		this.task = task;
	}

	public void addTask() {
		logger.log(Level.INFO, "add processing");

		// Create the name of the text file
		String fileName = date + ".txt";
		if (date.equals(FLOATING_CHECK)) {
			fileName = "general.txt"; // adds to general file
		}
		String content = "[" + time + "] " + task;
		if (time.equals(FLOATING_CHECK)) {
			content = ALL_DAY_TASK + task;
		}

		ArrayList<String> list = new ArrayList<String>();
		// Check if the text file exists, if it does, add its content to
		// a list and return the list
		(new FileAccessor(fileName)).checkFilesExistCustom(date);
		list = (new FileAccessor(fileName)).readContents();
		list.add(content);
		(new FileAccessor(fileName, list)).writeContents();

		boolean dateOutOfBox = true;
		for (int i = 0; i < TOTAL_BOX_VALUE; i++) {
			if (date.equals(DateModifier.getParticularDate(
					DateModifier.getCurrDate(), i))) {
				dateOutOfBox = false;
				break;
			}
		}
		if (date.equals(FLOATING_CHECK)) {
			dateOutOfBox = false;
		}
		if (dateOutOfBox) {
			WarningPopUp.infoBox(content, MESSAGE_TASK);
		}
		logger.log(Level.INFO, "add complete");
	}
}
