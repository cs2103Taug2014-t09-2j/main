package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.LinkedList;

/* This class applies to only 1 file
 * 
 */
public class CommandHistoryLinkedList {

	public LinkedList<ArrayList<String>> historyAL;
	int counter = 0; // Position where the current file is
	int maxCounter = 0; // Max Counter when there is no new add/edit/done
						// command
	String date;

	// Set Base file Here
	public CommandHistoryLinkedList(String date) {
		historyAL = new LinkedList<ArrayList<String>>();
		this.date = date;
		recordBaseFile(date);
	}

	public void recordBaseFile(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyAL.add(currDateTask);
		counter++;
		maxCounter = counter;
	}

	public void recordUpdatedFile(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyAL.add(currDateTask);
		counter++;
		maxCounter = counter;
		// System.out.println("total= " + counter);
	}

	// Clear DateR & ALR after new command add/edit/done
	public void clearALR() {
		// System.out.println("Clear= " + counter);
		while (historyAL.size() != counter) {
			historyAL.removeLast();
		}
	}

	public void runUndo() {
		if (counter > 1) {
			counter--;
			// System.out.println("U= " + counter);
			(new WriteFile(date, historyAL.get(counter - 1))).writeContents();
			System.out.println("Success");
		} else {
			System.out.println("Undo Limit");
		}
	}

	public void runRedo() {
		if (counter < maxCounter) {
			counter++;
			// System.out.println("R= " + counter);
			(new WriteFile(date, historyAL.get(counter - 1))).writeContents();
			System.out.println("Success");
		} else {
			System.out.println("Redo Limit");
		}
	}
}
