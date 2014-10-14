package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileAccessor {
	private String fileName;
	private ArrayList<String> currDateTask;
	
	// Constructor
	public FileAccessor(String fileName, ArrayList<String> currDateTask) {
		this.fileName = fileName;
		this.currDateTask = currDateTask;
		}
	
	public FileAccessor(String fileName) {
		this.fileName = fileName;
		currDateTask = new ArrayList<>();
	}
	
	// Mutator
	public void setFileName(String newFileName){
		this.fileName=newFileName;
	}
	
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
	
	String readFileToString() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
	
	String readFileString() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
	
	public void writeContents() {
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Collections.sort(currDateTask);
			String _date = fileName.replace(".txt", "");
			bw.write(_date + "\n" + "\n");
			for (int i = 0; i < currDateTask.size(); i++) {
				bw.write(currDateTask.get(i) + "\n");
			}
			bw.close();
		} catch (IOException ee) {
			WarningPopUp.infoBox("Failure to Write File " + fileName + "!",
					"ERROR");
			ee.printStackTrace();
		}

	}
	
	

}
