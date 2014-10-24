package ido.main;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Add general task and missing task edit
 * 
 */

public class CommandDone {

	private static Logger logger = Logger.getLogger("CommandDone");

	String date; // fileName
	int position = -1;
	File file_object = null;

	// Constructor
	// General start up of done
	public CommandDone(String date, String position) {
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
				doneSpecificTask(fileName);
			} else {
				String fileName = date + ".txt";
				doneSpecificTask(fileName);
			}
			// } else if (date.equals("miss")) {
			// String fileName = "missing.txt"; // Naming in progress
			// doneSpecificTask(fileName);
			
		} else { // All Task
			if (date.equals("-")) {
				String fileName = "general.txt";
				doneAllTask(fileName);

			} else {
				String fileName = date + ".txt";
				doneAllTask(fileName);
			}
			// } else if (date.equals("miss")) {
			// String fileName = "missing.txt"; // Naming in progress
			// doneAllTask(fileName);
			
		}

	}

	private void doneAllTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();
		file_object = new File(fileName);

		logger.log(Level.INFO, "delete processing");

		// Test file
		if (file_object.exists()) {

			// read the content of the file, put in the list
			currDateTask = (new FileAccessor(fileName)).readContents();

			if (currDateTask.size() == 0) {
				// System.out.println("Nothing to clear");
				WarningPopUp.infoBox("Nothing to clear", "WARNING");

			} else {
				// add to archives
				//System.out.println("doneallsize: "+currDateTask.size());
				Archives.addAllDoneTask(fileName.replace(".txt",""),currDateTask);
				
				currDateTask.clear();
				// System.out.println("Success Clear");

			}

			// write in file
			(new FileAccessor(fileName, currDateTask)).writeContents();

		} else {
			// System.out.println("Failed Clear");
			WarningPopUp.infoBox("Unable to delete", "WARNING");

			logger.log(Level.WARNING, "processing error");

		}

		logger.log(Level.INFO, "delete complete");

	}

	private void doneSpecificTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();

		logger.log(Level.INFO, "delete processing");

		// read the content of the file, put in the list
		currDateTask = (new FileAccessor(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {
			// add to archives
			Archives.addOneDoneTask(fileName.replace(".txt",""),currDateTask.get(position - 1));
			
			currDateTask.remove(position - 1);
			// System.out.println("Success delete");

		} else {
			// System.out.println("Failed delete");
			WarningPopUp.infoBox("Unable to delete", "WARNING");

			logger.log(Level.WARNING, "processing error");

		}

		// write in file
		(new FileAccessor(fileName, currDateTask)).writeContents();

		logger.log(Level.INFO, "delete complete");

	}

}
