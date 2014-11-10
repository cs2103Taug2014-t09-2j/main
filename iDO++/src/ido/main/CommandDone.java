package ido.main;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@author A0110679A 

/* This class deletes the completed task and archives the task * 
 */

public class CommandDone {

	private static Logger logger = Logger.getLogger("CommandDone");

	String date; // fileName w/o .txt
	String fileName; // fileName w .txt
	int position = -1;
	File file_object = null;
	private static final String GENERALTXT = "general.txt";

	// Constructor
	public CommandDone(String date, String position) {
		
		this.date = IsValidDate.validateDate(date);
		this.position = Integer.parseInt(position);
		
	}
	
	// Mutator
	public void done() {
		
		if (position != -1) {
			
			// Specific task
			if (date.equals("-")) {
				fileName = GENERALTXT;
				doneSpecificTask();
				
			} else {
				fileName = date + ".txt";
				doneSpecificTask();
				
			}

		} else { // All Task
			if (date.equals("-")) {
				fileName = GENERALTXT;
				doneAllTask();

			} else {
				fileName = date + ".txt";
				doneAllTask();
				
			}

		}

	}

	private void doneAllTask() {
		
		ArrayList<String> currDateTask = new ArrayList<>();
		file_object = new File(fileName);

		logger.log(Level.INFO, "delete processing");

		// Test file
		if (file_object.exists()) {

			// read the content of the file, put in the list
			currDateTask = (new FileAccessor(fileName)).readContents();

			if (currDateTask.size() == 0) {
				WarningPopUp.infoBox("Nothing to clear", "WARNING");

			} else {
				// add to archives
				Archives.addAllDoneTask(fileName.replace(".txt", ""),
						currDateTask);
				currDateTask.clear();
				
			}

			// write in file
			(new FileAccessor(fileName, currDateTask)).writeContents();

		} else {
			WarningPopUp.infoBox("Unable to delete", "WARNING");

			logger.log(Level.WARNING, "processing error");

		}

		logger.log(Level.INFO, "delete complete");

	}

	private void doneSpecificTask() {
		ArrayList<String> currDateTask = new ArrayList<>();

		logger.log(Level.INFO, "delete processing");

		// read the content of the file, put in the list
		currDateTask = (new FileAccessor(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {
			// add to archives
			Archives.addOneDoneTask(fileName.replace(".txt", ""),
					currDateTask.get(position - 1));
			currDateTask.remove(position - 1);

		} else {
			WarningPopUp.infoBox("Unable to delete", "WARNING");

			logger.log(Level.WARNING, "processing error");

		}

		// write in file
		(new FileAccessor(fileName, currDateTask)).writeContents();

		logger.log(Level.INFO, "delete complete");

	}

}
