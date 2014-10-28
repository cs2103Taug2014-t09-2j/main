package ido.main;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Benedict
 */

/*
 * Might need to integrate with delete and archives and undo/redo if required to modify 
 */

public class OverDueTask {

	ArrayList<String> prevDateTask = new ArrayList<>();
	ArrayList<String> ODTask = new ArrayList<>();
	File file_object = null;

	public OverDueTask() {
		updateOverDueTask();
	}

	public void updateOverDueTask() {
		String prevDate = DateModifier.getPrevDate(DateModifier.getCurrDate());
		String fileName = prevDate + ".txt";
		file_object = new File(fileName);
		if (file_object.exists()) {
			// read the content of the previous date file, put in the list
			prevDateTask = (new FileAccessor(fileName)).readContents();
			
			if (prevDateTask.size() != 0) {
				ODTask = (new FileAccessor("overdue.txt")).readContents();
			
				// Transfer contents over
				for (int i = 0; i < prevDateTask.size(); i++) {
					ODTask.add(prevDate + " " + prevDateTask.get(i));
				}
				
				// write in file
				(new FileAccessor("overdue.txt", ODTask)).writeContents();
			}
			
			// Not sure if working
			// Delete the prev date txt to save space
			file_object.delete();
			//(new File(fileName)).delete();
		}
	}
}
