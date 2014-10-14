package org.eclipse.wb.swt;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Add general task and missing task edit
 * 
 */

public class CommandDone {

	String date; // fileName
	int position = -1;
	File file_object = null;

	// Constructor
	// General start up of done
	public CommandDone(String input) {
		String[] arrStr = input.split(" ");
		String chkDate = IsValidDate.validateDate(arrStr[0]); // check 1st input
		
		if(chkDate.equals("-")){ // Input General Task
			setVariables(arrStr);
		}else{ // Input Date Task
			setVariables(arrStr);
		}
		
//		} else if (arrStr[0].equals("miss")) { // Input Missing Task
//			setVariables(arrStr);
//		}
		// System.out.println("values stored");
	}

	private void setVariables(String[] arrStr) {
		if (arrStr.length == 1) {
			date = IsValidDate.validateDate(arrStr[0]);// return String date/-
		} else {
			date = IsValidDate.validateDate(arrStr[0]);// return String date/-
			position = Integer.parseInt(arrStr[1]);
		}
	}

	// Mutator
	public void delete() {
		// Specific task
		if (position != -1) {
			if (date.equals("-")) {
				String fileName = "general.txt";
				doneSpecificTask(fileName);
//			} else if (date.equals("miss")) {
//				String fileName = "missing.txt"; // Naming in progress
//				doneSpecificTask(fileName);
			} else {
				String fileName = date + ".txt";
				doneSpecificTask(fileName);
			}
		} else { // All Task
			if (date.equals("-")) {
				String fileName = "general.txt";
				doneAllTask(fileName);
//			} else if (date.equals("miss")) {
//				String fileName = "missing.txt"; // Naming in progress
//				doneAllTask(fileName);
			} else {
				String fileName = date + ".txt";
				doneAllTask(fileName);
			}
		}

	}

	private void doneAllTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();
		file_object = new File(fileName);

		// Test file
		if (file_object.exists()) {

			// read the content of the file, put in the list
			currDateTask = (new FileAccessor(fileName)).readContents();

			if (currDateTask.size() == 0) {
				System.out.println("Nothing to clear");

			} else {
				currDateTask.clear();
				System.out.println("Success Clear");

			}

			// write in file
			(new FileAccessor(fileName, currDateTask)).writeContents();

		} else {
			System.out.println("Failed Clear");

		}
	}

	private void doneSpecificTask(String fileName) {
		ArrayList<String> currDateTask = new ArrayList<>();

		// read the content of the file, put in the list
		currDateTask = (new FileAccessor(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {
			currDateTask.remove(position - 1);
			System.out.println("Success delete");

		} else {
			System.out.println("Failed delete");

		}

		// write in file
		(new FileAccessor(fileName, currDateTask)).writeContents();
	}
		
}
