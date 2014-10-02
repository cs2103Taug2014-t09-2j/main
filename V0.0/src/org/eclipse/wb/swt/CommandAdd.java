package org.eclipse.wb.swt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommandAdd {
	String date;
	String task;
	String time;

	private String validateDate(String date){
		if (date.toLowerCase().equals("today")){
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			Date currDate = new Date();
			return dateFormat.format(currDate);
		}
		else if (date.toLowerCase().equals("tomorrow")){
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			long currTime = new Date().getTime();
			Date currDate = new Date(currTime+(24*3600*1000));
			return dateFormat.format(currDate);
		}
		return date;
	}
	
	private String validateTime(String time) throws Exception{
		// TODO Throw meaningful exception messages
		if (time.equals("-")){
			return "-";
		}
		String splitted_time[] = time.split("-");
		boolean valid = true;
		if (splitted_time.length < 1 || splitted_time.length >2){
			valid = false;
		}
		if (time.length() > 0){
			if (time.charAt(time.length()-1) == '-'){
				valid = false;
			}
		}
		int old_t = -1;
		for (int i=0;i<splitted_time.length;i++){
			int t = Integer.valueOf(splitted_time[i]);
			if (t<0 || t>23 || old_t>=t){
				valid = false;
			}
			old_t = t;
		}
		if (valid){
			return time;
		}
		throw new Exception("Invalid time.");
	}
	
	public CommandAdd(String date, String time, String task){
		this.date = validateDate(date);
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
				int _ignore = 2;
				while ((line = br.readLine()) != null) {
					if (_ignore > 0){ //ignore first 2 lines
						_ignore -= 1;
						continue;
					}
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
			content = "[all-day] "+ task;
		}
		ArrayList<String> list = new ArrayList<String>();
		// Check if the text file exists, if it does, add its content to
		// a list and return the list
		list = isFileExist(fileName);
		list.add(content);
		(new WriteFile(fileName, list)).writeContents();
	}
}
