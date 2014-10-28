package ido.main;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Benedict
 */

/* 
 * This class deletes the task without archiving
 * Code from CommandDone without archive
 * 
 */

public class CommandDelete {

	String date; // fileName
	int position = -1;
	File file_object = null;

	// Constructor
	// General start up of delete
	public CommandDelete(String date, String position) {
		this.date = IsValidDate.validateDate(date);
		this.position = Integer.parseInt(position);
	}

	// } else if (arrStr[0].equals("miss")) { // Input Missing Task
	// setVariables(arrStr);
	// }
	// System.out.println("values stored");

	// Mutator
	public void delete() {
		if (position != -1) {
			// Specific task
			if (date.equals("-")) {
				String fileName = "general.txt";
				deleteSpecificTask(fileName);
			} else {
				String fileName = date + ".txt";
				deleteSpecificTask(fileName);
			}
			// } else if (date.equals("miss")) {
			// String fileName = "missing.txt"; // Naming in progress
			// deleteSpecificTask(fileName);
			
		} else { // All Task
			if (date.equals("-")) {
				String fileName = "general.txt";
				deleteAllTask(fileName);

			} else {
				String fileName = date + ".txt";
				deleteAllTask(fileName);
			}
			// } else if (date.equals("miss")) {
			// String fileName = "missing.txt"; // Naming in progress
			// deleteAllTask(fileName);
			
		}

	}

	private void deleteAllTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();
		file_object = new File(fileName);

		// Test file
		if (file_object.exists()) {

			// read the content of the file, put in the list
			currDateTask = (new FileAccessor(fileName)).readContents();

			if (currDateTask.size() == 0) {
				// System.out.println("Nothing to clear");
				WarningPopUp.infoBox("Nothing to clear", "WARNING");

			} else {			
				currDateTask.clear();
				// System.out.println("Success Clear");

			}

			// write in file
			(new FileAccessor(fileName, currDateTask)).writeContents();

		} else {
			// System.out.println("Failed Clear");
			WarningPopUp.infoBox("Unable to delete", "WARNING");

		}

	}

	private void deleteSpecificTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();

		// read the content of the file, put in the list
		currDateTask = (new FileAccessor(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {			
			currDateTask.remove(position - 1);
			// System.out.println("Success delete");

		} else {
			// System.out.println("Failed delete");
			WarningPopUp.infoBox("Unable to delete", "WARNING");

		}

		// write in file
		(new FileAccessor(fileName, currDateTask)).writeContents();

	}
}
