package org.eclipse.wb.swt;

import java.util.ArrayList;

public class CommandSearchCurrDate {

	String date;
	String time;
	String task;
	ArrayList<String> currDateTask;

	// For CommandAdd
	// Assume 
	public CommandSearchCurrDate(String date, String time, String task) {
		currDateTask = new ArrayList<>();
		this.date = date;
		this.time = time;
		this.task = task;
	}

	public int searchString() {
		int arrLine = 0;
		currDateTask = (new ReadFile(date + ".txt")).readContents();
		for (int i = 0; i < currDateTask.size(); i++) {
			if(currDateTask.get(i).contains(time) && currDateTask.get(i).contains(task)){
				arrLine = i+1;
				break;
			}
		}
		return arrLine;
	}
}
