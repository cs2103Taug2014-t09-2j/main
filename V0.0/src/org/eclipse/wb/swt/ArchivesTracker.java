package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArchivesTracker {
	// Similar to HistoryTrackerSingleFile but saves an
	// arrayList<arrayList<String>>
	// need to change the recordBaseFile and recordUpdatedFile

	public LinkedList<ArrayList<String>> arcAL;
	int counter = 0; // Position where the current file is
	int maxCounter = 0; // Max Counter when there is no new add/edit/done
						// command
	String date;

	// Set Base file Here
	public ArchivesTracker(String date, ArrayList<String> task) {
		arcAL = new LinkedList<ArrayList<String>>();
		if (date.equals("-")) {
			this.date = "general";
		} else {
			this.date = date;
		}
		recordBaseFile(task);
	}

	public void recordBaseFile(ArrayList<String> task) {
		arcAL.add(task);
		counter++;
		maxCounter = counter;
	}

	public void recordNewTask(ArrayList<String> task) {
		arcAL.add(task);
		System.out.println(task);
		counter++;
		maxCounter = counter;
		System.out.println("AT counter: "+counter);
	}

	public void clearArcALR() {
		// System.out.println("Clear= " + counter);
		while (arcAL.size() != counter) {
			arcAL.removeLast();
		}
	}

	public void runUndo() {
		if (counter > 1) {
			counter--;
		}
	}

	public void runRedo() {
		if (counter < maxCounter) {
			counter++;
		}
	}

	public ArrayList<String> getarcAL() {
		ArrayList<String> arcDateTask = new ArrayList<String>();

		// Until the latest entry
		for (int i = 0; i < arcAL.size() && i < counter; i++) {
			System.out.println(date + " size: "+arcAL.get(i).size());
			for (int j = 0; j < arcAL.get(i).size(); j++) {
				arcDateTask.add(arcAL.get(i).get(j));
			}
		}
		return arcDateTask;
	}
	
	public String getDate(){
		return date;
	}

}
