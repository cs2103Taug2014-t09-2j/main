package ido.main;

import java.util.ArrayList;
import java.util.LinkedList;

//@author A0110679A 

/* This class saves the states of a text file
 */

public class HistoryTrackerSingleFile {

	public LinkedList<ArrayList<String>> historyAL;
	int counter = 0; // Position where the current file is
	int maxCounter = 0; // Max Counter when there is no new add/edit/done
						// command
	String date;
	private static final String GENERALTXT = "general.txt";

	// Set Base file Here
	public HistoryTrackerSingleFile(String date) {
		
		historyAL = new LinkedList<ArrayList<String>>();
		
		if (date.equals("-")) {
			this.date = GENERALTXT;
		} else {
			this.date = date + ".txt";
		}
		
		recordBaseFile(date);
	}

	public void recordBaseFile(String date) {
		
		String fileName = "";
		
		if (date.equals("-")) {
			fileName = GENERALTXT;
		} else {
			fileName = date + ".txt";
		}
		
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new FileAccessor(fileName)).readContents();
		historyAL.add(currDateTask);
		counter++;
		maxCounter = counter;
	}

	public void recordUpdatedFile(String date) {
		
		String fileName = "";
		
		if (date.equals("-")) {
			fileName = "general.txt";
		} else {
			fileName = date + ".txt";
		}
		
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new FileAccessor(fileName)).readContents();
		historyAL.add(currDateTask);
		counter++;
		maxCounter = counter;
		
	}

	// Clear ALR after new command add/edit/done
	public void clearALR() {
		
		while (historyAL.size() != counter) {
			historyAL.removeLast();
		}
		
	}

	public void runUndo() {
		
		if (counter > 1) {
			counter--;			
			(new FileAccessor(date, historyAL.get(counter - 1)))
					.writeContents();
		}
		
	}

	public void runRedo() {
		
		if (counter < maxCounter) {
			counter++;			
			(new FileAccessor(date, historyAL.get(counter - 1)))
					.writeContents();			
		}
		
	}
}
