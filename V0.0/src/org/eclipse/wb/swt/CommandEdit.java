package org.eclipse.wb.swt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandEdit {
	private String date;
	private String index;
	private String specification;//specify whether to edit time or task
	private String modification;
	
	private final static Logger LOGGER = Logger.getLogger(CommandEdit.class .getName());
	
	private static final String TEXT_EXTENSION = ".txt";
	private static final String CONTENT_TO_DISPLAY = "[%1$s] %2$s";
	public CommandEdit(String str1, String str2, String str3, String str4) {
		date = str1;
		index = str2;
		specification = str3;
		modification = str4;
	}
	
	public void edit() throws IOException {
		LOGGER.setLevel(Level.INFO);
		String fileName = getDateStr(date);
		if (specification.equals("time")){
			editTime(fileName);
			LOGGER.warning("Possible IOException or FileNotFoundException");
		} else if (specification.equals("task")){
			editTask(fileName);
		} else if (specification.equals("all")) {
			editAll(fileName);
		}
	}
	
	private void editTime(String fileName) throws IOException {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Edits the time of a specified task");
		
		ArrayList<String> dateTask = new ArrayList<String>();
		dateTask = (new FileAccessor(fileName)).readContents();
		
		// delete the task with the given index number in the date
		int position = Integer.parseInt(index);
		String toBeRemoved = dateTask.get(position-1);
		//Split toBeRemoved string to 2parts and get the task
		String keep = toBeRemoved.split(" ", 2)[1];
		dateTask.remove(toBeRemoved);

		String modificationFinal = String.format(CONTENT_TO_DISPLAY, modification, keep);

		// insert the modification into the arrayList
		dateTask.add(position - 1, modificationFinal);

		(new FileAccessor(fileName, dateTask)).writeContents();
	}
	
	private void editTask(String fileName) throws IOException {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Edits the task content of a specified task");
		
		ArrayList<String> dateTask = new ArrayList<String>();
		dateTask = (new FileAccessor(fileName)).readContents();
		
		// delete the task with the given index number in the date
		int position = Integer.parseInt(index);
		String toBeRemoved = dateTask.get(position - 1);
		dateTask.remove(toBeRemoved);

		//Split toBeRemoved string to 2parts and get the time
		String keep = toBeRemoved.split(" ", 2)[0];
		keep = keep.substring(1, keep.length()-1);
		String modificationFinal = String.format(CONTENT_TO_DISPLAY, keep, modification);

		// insert the modification into the arrayList
		dateTask.add(position - 1, modificationFinal);

		(new FileAccessor(fileName, dateTask)).writeContents();
	}
	
	private void editAll(String fileName) throws IOException {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Edits the whole line of a specified task");
		
		ArrayList<String> dateTask = new ArrayList<String>();
		dateTask = (new FileAccessor(fileName)).readContents();
		
		// delete the task with the given index number in the date
		int position = Integer.parseInt(index);
		String toBeRemoved = dateTask.get(position - 1);
		dateTask.remove(toBeRemoved);
		
		String[] change = modification.split(" ", 2);
		String modificationFinal = String.format(CONTENT_TO_DISPLAY, change[0], change[1]);
		
		dateTask.add(position - 1, modificationFinal);

		(new FileAccessor(fileName, dateTask)).writeContents();
	}
	
	private String getDateStr(String date){
		return IsValidDate.validateDate(date) + TEXT_EXTENSION;//get the name of the file given the date input
													//date input can be 1-7
	}
}

