package ido.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@author A0114076N
/*
* This class edits an existing task. 
* Based on user specification on what to modify, the method chooses which part of the 
* to-be-modified task string to keep and modifies the rest.
*/


public class CommandEdit {
	private String date;
	private int index;
	private String specification;
	private String modification;
	
	private final static Logger LOGGER = Logger.getLogger(CommandEdit.class .getName());
	
	private static final String TXT_EXTENSION = ".txt";
	private static final String CONTENT_TO_DISPLAY = "[%1$s] %2$s";
	private static final String UNKNOWN_TIME = "-";
	private static final String ALL_DAY_TIME = "by-today";
	private static final String FEEDBACK_INVALID_TIME  = "Invalid Time!";
	private static final String ERROR = "ERROR";
	private static final String SPEC_TIME = "time";
	private static final String SPEC_TASK = "task";
	private static final String SPEC_ALL = "all";
	private static final String ASK_FOR_SPEC = "Please enter what you would like to modify\ntask/time/all";
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
		
		//get the task to be edited given the index of that task
		String oldTask = getTask(dateTask);
		dateTask.remove(oldTask);
		String editedTask = "";
		
		switch (specification) {
		case SPEC_TIME:
			try {
				editedTask = editTime(oldTask);
			} catch (Exception e) {
				editedTask = oldTask;
				WarningPopUp.infoBox(FEEDBACK_INVALID_TIME, ERROR);
				e.printStackTrace();
			}
			break;
		case SPEC_TASK:
			editedTask = editTask(oldTask);
			break;
		case SPEC_ALL:
			try {
				editedTask = editAll(oldTask);
			} catch (Exception e) {
				editedTask = oldTask;
				WarningPopUp.infoBox(FEEDBACK_INVALID_TIME, ERROR);
				e.printStackTrace();
			}
			break;
		default:
			WarningPopUp.infoBox(ASK_FOR_SPEC, ERROR);
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
	
	public String editAll(String fileName) throws Exception{
		LOGGER.info("Edits the whole line of a specified task");
		
		String[] change = modification.split(" ", 2);
		change[0] = getNewTimeStr(change[0]);
		return String.format(CONTENT_TO_DISPLAY, change[0], change[1]);
	}
	
	//get the name of the file given the date input
			//date input can be 1-7, which is the box number is the date is being displayed
	private String getDateStr(String date){
		return IsValidDate.validateDate(date) + TXT_EXTENSION;
	}
	
	// Validates the time input. If input time is not specified, the time is assumed to be all-day
	private String getNewTimeStr(String time) throws Exception {
		String validatedTime = IsValidTime.validateTime(time);
		if (validatedTime.equals(UNKNOWN_TIME))
			validatedTime = ALL_DAY_TIME;
			return validatedTime;
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

