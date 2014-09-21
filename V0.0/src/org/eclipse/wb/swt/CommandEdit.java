package org.eclipse.wb.swt;

import java.io.IOException;
import java.util.ArrayList;

public class CommandEdit {
	private String date;
	private String index;
	private String specification;//specify whether to edit time or task
	private String modification;
	
	private static final String CONTENT_TO_DISPLAY = "[%1$s] %2$s";
	public CommandEdit(String str1, String str2, String str3, String str4) {
		date = str1;
		index = str2;
		specification = str3;
		modification = str4;
	}
	
	public void edit() throws IOException {
		if (specification.equals("time")){
			editTime();
		} else if (specification.equals("task")){
			editTask();
		}
	}
	
	public void editTime() throws IOException {

		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<String>();
		currDateTask = (new ReadFile(fileName)).readContents();
		
		// delete the task with the given index number in the date
		int position = Integer.parseInt(index);
		String toBeRemoved = currDateTask.get(position+1);
		//Split toBeRemoved string to 2parts and get the task
		String removed = toBeRemoved.split(" ", 2)[1];
		currDateTask.remove(toBeRemoved);

		String modificationFinal = String.format(CONTENT_TO_DISPLAY, modification, removed);

		// insert the modification into the arrayList
		currDateTask.add(position + 1, modificationFinal);

		(new WriteFile(fileName, currDateTask)).writeContents();
	}
	
	public void editTask() throws IOException {

		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<String>();
		currDateTask = (new ReadFile(fileName)).readContents();
		
		// delete the task with the given index number in the date
		int position = Integer.parseInt(index);
		String toBeRemoved = currDateTask.get(position - 1);
		currDateTask.remove(toBeRemoved);

		//Split toBeRemoved string to 2parts and get the time
		String removed = toBeRemoved.split(" ", 2)[0];
		removed = removed.substring(1, removed.length()-1);
		String modificationFinal = String.format(CONTENT_TO_DISPLAY, removed, modification);

		// insert the modification into the arrayList
		currDateTask.add(position - 1, modificationFinal);

		(new WriteFile(fileName, currDateTask)).writeContents();
	}
}
