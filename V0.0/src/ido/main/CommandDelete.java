package ido.main;

import java.io.File;
import java.util.ArrayList;

//@author A0110679A 

/*
 * This class deletes the task without archiving Code from CommandDone without
 * archive
 */

public class CommandDelete {

	String date; // fileName w/o .txt
	String fileName; // fileName w .txt
	int position = -1;
	File file_object = null;
	private static final String GENERALTXT = "general.txt";

	// Constructor
	public CommandDelete(String date, String position) {
		
		this.date = IsValidDate.validateDate(date);
		this.position = Integer.parseInt(position);
		
	}

	// Accessor
	
	
	// Mutator
	
	// Others
	public void delete() {
		
		if (position != -1) {
			
			// Specific task
			if (date.equals("-")) {
				fileName = GENERALTXT;
				deleteSpecificTask();
							
			} else {
				fileName = date + ".txt";
				deleteSpecificTask();
				
			}

		} else { // All Task
			
			if (date.equals("-")) {
				fileName = GENERALTXT;
				deleteAllTask();
				
			} else {
				fileName = date + ".txt";
				deleteAllTask();
				
			}
		
		}

	}

	private void deleteAllTask() {
		ArrayList<String> currDateTask = new ArrayList<>();
		file_object = new File(fileName);

		// Test file
		if (file_object.exists()) {

			// read the content of the file, put in the list
			currDateTask = (new FileAccessor(fileName)).readContents();

			if (currDateTask.size() == 0) {				
				WarningPopUp.infoBox("Nothing to clear", "WARNING");

			} else {
				currDateTask.clear();
				
			}

			// write in file
			(new FileAccessor(fileName, currDateTask)).writeContents();

		} else {
			WarningPopUp.infoBox("Unable to delete", "WARNING");

		}

	}

	private void deleteSpecificTask() {
		ArrayList<String> currDateTask = new ArrayList<>();

		// read the content of the file, put in the list
		currDateTask = (new FileAccessor(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {
			currDateTask.remove(position - 1);			

		} else {
			WarningPopUp.infoBox("Unable to delete", "WARNING");

		}

		// write in file
		(new FileAccessor(fileName, currDateTask)).writeContents();

	}
}
