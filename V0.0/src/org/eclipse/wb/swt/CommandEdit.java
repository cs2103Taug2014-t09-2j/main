package org.eclipse.wb.swt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandEdit {
	private String date;
	private int index;
	private String specification;//specify whether to edit time or task
	private String modification;
	
	private final static Logger LOGGER = Logger.getLogger(CommandEdit.class .getName());
	
	private static final String TXT_EXTENSION = ".txt";
	private static final String CONTENT_TO_DISPLAY = "[%1$s] %2$s";
	
	public CommandEdit() {
	}
	
	//Mutators
	public void setDate(String str){
		date = str;
	}
	public void setIndex(String str) {
		index = Integer.parseInt(str);
	}
	public void setSpec(String str) {
		specification = str;
	}
	public void setMod(String str) {
		modification = str;
	}
	
	public void edit(String str1, String str2, String str3, String str4) throws IOException{
		LOGGER.setLevel(Level.INFO);
		
		setDate(str1); setIndex(str2); setSpec(str3); setMod(str4);
		
		String fileName = getDateStr(date);
		
		ArrayList<String> dateTask = new ArrayList<String>();
		dateTask = getFileContent(fileName);
		
		String oldTask = getTask(dateTask);//get the task to be edited given the index
		dateTask.remove(oldTask);
		String editedTask = new String();
		
		switch (specification) {
		case "time":
			try {
				editedTask = editTime(oldTask);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "task":
			editedTask = editTask(oldTask);
			break;
		case "all":
			editedTask = editAll(oldTask);
			break;
		default:
			WarningPopUp.infoBox("Please enter what you would like to modify\ntask/time/all", "ERROR!");
		}
		dateTask.add(index-1, editedTask);
		(new FileAccessor(fileName, dateTask)).writeContents();
	}
	
	public String editTime(String oldTask) throws Exception {
		LOGGER.info("Edits the time of a specified task");
		
		//Split toBeRemoved string to 2parts and get the task (the unchanged part)
		String keep = oldTask.split(" ", 2)[1];
		
		setMod(getNewTimeStr(modification));
		return String.format(CONTENT_TO_DISPLAY, modification, keep);
	}
	
	public String editTask(String oldTask) {
		LOGGER.info("Edits the task content of a specified task");

		//Split toBeRemoved string to 2parts and get the time (the unchanged part)
		String keep = oldTask.split(" ", 2)[0];
		keep = keep.substring(1, keep.length()-1);
		return String.format(CONTENT_TO_DISPLAY, keep, modification);
	}
	
	public String editAll(String fileName) {
		LOGGER.info("Edits the whole line of a specified task");
		
		String[] change = modification.split(" ", 2);
		return String.format(CONTENT_TO_DISPLAY, change[0], change[1]);
	}
	
	private String getDateStr(String date){
		return IsValidDate.validateDate(date) + TXT_EXTENSION;//get the name of the file given the date input
													//date input can be 1-7
	}
	private String getNewTimeStr(String time) throws Exception {
		return IsValidTime.validateTime(time);
	}
	
	private ArrayList<String> getFileContent(String fileName) {
		return (new FileAccessor(fileName)).readContents();
	}
	
	//Gets the task string given the index of the task
	private String getTask(ArrayList<String> dateTask) { 
		String task = dateTask.get(index -1);
		return task;
	}
}

