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
			int _ignore = 2;
			while ((curr = br.readLine()) != null) {
				if (_ignore > 0){ //ignore first 2 lines
					_ignore -= 1;
					continue;
				}
				currDateTask.add(curr);
			}
			br.close();
		} catch (IOException ee) {
			WarningPopUp.infoBox("Failure to Read File " + fileName + "!",
					"ERROR");
			ee.printStackTrace();
		}
		return currDateTask;
	}

}
