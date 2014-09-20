package org.eclipse.wb.swt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
	String fileName;
	ArrayList<String> currDateTask;

	// Constructor
	public WriteFile(String fileName, ArrayList<String> currDateTask) {
		this.fileName = fileName;
		this.currDateTask = currDateTask;
	}

	// Mutator
	public void writeContents() {
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
}
