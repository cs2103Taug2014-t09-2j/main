package org.eclipse.wb.swt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * 
 */

public class CommandDone {

	String date; // fileName
	int position;
	File file_object = null;

	// Constructor
	// Specific task
	public CommandDone(String date, String position) {
		this.date = date;
		this.position = Integer.parseInt(position);
		// System.out.println("executed 1");
	}

	// All task in a date
	public CommandDone(String date) {
		this.date = date;
		// System.out.println("executed 2");
	}

	// Mutator
	// Specific task
	public void clearDateTaskSpecific() {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();

		// read the content of the file, put in the list
		currDateTask = (new ReadFile(fileName)).readContents();

		// check if valid
		if (position - 1 < currDateTask.size()) {
			currDateTask.remove(position - 1);
			// System.out.println("Success delete");
		} else {
			// System.out.println("Failed delete");
		}

		// write in file
		(new WriteFile(fileName, currDateTask)).writeContents();

	}

	// All task in a date
	public void clearDateTaskAll() throws FileNotFoundException {
		String fileName = date + ".txt";
		file_object = new File(fileName);
		if (file_object.exists()) {
			file_object = new File(fileName);
			PrintWriter pw = new PrintWriter(file_object);
			pw.write(date);
			pw.write("");
			pw.close();
			System.out.println("Success Clear");
		} else {
			System.out.println("Failed Clear");
		}

	}

}
