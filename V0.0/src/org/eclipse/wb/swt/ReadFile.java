package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	String fileName;
	ArrayList<String> currDateTask;

	// Constructor
	public ReadFile(String fileName) {
		this.fileName = fileName;
		currDateTask = new ArrayList<>();
	}

	// Mutator
	public ArrayList<String> readContents() {
		BufferedReader br = null;
		try {
			String curr;
			br = new BufferedReader(new FileReader(fileName));
			while ((curr = br.readLine()) != null) {
				currDateTask.add(curr);
			}
			br.close();
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		return currDateTask;
	}

}
