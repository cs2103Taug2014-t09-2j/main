package ido.main;

import java.io.File;
import java.util.ArrayList;

//@author A0110679A 

/* This class reads 10 text files from previous date 
 * and stores the overdue task into overdue text file 
 */

public class OverDueTask {

	ArrayList<String> prevDateTask = new ArrayList<>();
	ArrayList<String> ODTask = new ArrayList<>();
	File file_object = null;
	private static final String OVERDUETXT = "overdue.txt";

	public OverDueTask() {
		
		updateOverDueTask();
		
	}

	public void updateOverDueTask() {
		
		// Get initial previous date
		String prevDate = DateModifier.getPrevDate(DateModifier.getCurrDate());

		for (int j = 0; j < 10; j++) { // Deletes files from 10 days ago

			String fileName = prevDate + ".txt";
			file_object = new File(fileName);
			
			if (file_object.exists()) {
				
				// read the content of the previous date file, put in the list
				prevDateTask = (new FileAccessor(fileName)).readContents();

				if (prevDateTask.size() != 0) {
					ODTask = (new FileAccessor(OVERDUETXT)).readContents();

					// Transfer contents over
					for (int i = 0; i < prevDateTask.size(); i++) {
						ODTask.add(prevDate + " " + prevDateTask.get(i));
					}

					// write in file
					(new FileAccessor(OVERDUETXT, ODTask)).writeContents();
				}

				// Delete the previous date text file to save space
				file_object.delete();
			}

			// Get previous date from the previous date for the next loop
			prevDate = DateModifier.getPrevDate(prevDate);
			
		}
	}
}
