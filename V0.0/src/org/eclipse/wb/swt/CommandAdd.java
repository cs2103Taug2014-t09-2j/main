package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CommandAdd {
	String date;
	String task;
	String time;

	private String validateTime(String time) throws Exception{
		if (time.equals("-")){
			return "-";
		}
		int t = Integer.valueOf(time);
		if (t>=0 && t<=23){
			return time;
		}
		throw new Exception("Invalid time.");
	}
	
	public CommandAdd(String date, String time, String task){
		this.date = date;
		try{
			this.time = validateTime(time);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		this.task = task;
	}

	public ArrayList<String> isFileExist(String fileName)
			throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			File file = new File(fileName);
			if (file.exists()) {
				String line;
				BufferedReader br = new BufferedReader(new FileReader(
						fileName));
				while ((line = br.readLine()) != null) {
					list.add(line); // File already exists, add its
									// content to the list
				}
				br.close();
			}
		} catch (FileNotFoundException e) {
			list.clear(); // Clear all corrupted data
		}
		return list;
	}
	
	public void addTask() throws IOException {
		// Create the name of the text file
		String fileName = date + ".txt";
		if (date.equals("-")){
			fileName = "general.txt";
		}
		String content = "[" + time + "] " + task;
		if (time.equals("-")){
			content = task;
		}
		ArrayList<String> list = new ArrayList<String>();
		// Check if the text file exists, if it does, add its content to
		// a list and return the list
		list = isFileExist(fileName);
		list.add(content);
		(new WriteFile(fileName, list)).writeContents();
		//writeToFile(list, fileName);
	}
}
