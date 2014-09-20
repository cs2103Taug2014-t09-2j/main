package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class commandDone {

	String date; // fileName
	int position;
	File file_object = null;

	// Constructor
	// Specific task
	public commandDone(String date, String position) {
		this.date = date;
		this.position = Integer.parseInt(position);
		//System.out.println("executed 1");
	}

	public commandDone(String date) {
		// All task in a date
		this.date = date;
		//System.out.println("executed 2");
	}

	// Mutator
	// Specific task
	public void clearDateTaskSpecific() {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		
		// read the content of the file, put in the list
		BufferedReader br = null;
		try {
			String curr;
			br = new BufferedReader(new FileReader(fileName));
			while ((curr = br.readLine()) != null) {
				currDateTask.add(curr);
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		
		// check if valid
		if(position-1 < currDateTask.size()){
		currDateTask.remove(position-1);
			System.out.println("Success delete");
		} else{
			System.out.println("Failed delete");
		}
		
		// write in file
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < currDateTask.size(); i++) {
				bw.write(currDateTask.get(i) + "\n");
			}
			bw.close();
		} catch (IOException ee) {
			ee.printStackTrace();
		}

	}

	// All task in a date
	public void clearDateTaskAll() throws FileNotFoundException {
		String fileName = date + ".txt";
		file_object = new File(fileName);
		if (file_object.exists()) {
			file_object = new File(fileName);
			PrintWriter pw = new PrintWriter(file_object);
			pw.write("");
			pw.close();
			System.out.println("Success Clear");
		} else {
			System.out.println("Failed Clear");
		}

	}

}
