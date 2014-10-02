package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandHistoryLinkedList {
	
	public LinkedList<String> historyDate;
	public LinkedList<ArrayList<String>> historyAL;
	static int counter = 0; // Position where the current file is
	static int maxCounter = 0; // Max Counter when there is no new add/edit/done command

	public CommandHistoryLinkedList() {
		historyDate = new LinkedList<String>();
		historyAL = new LinkedList<ArrayList<String>>();
	}

	public void recordBaseFile(String date) {
		if (historyDate.size() == 0) {
			String fileName = date + ".txt";
			ArrayList<String> currDateTask = new ArrayList<>();
			currDateTask = (new ReadFile(fileName)).readContents();
			historyDate.add(fileName);
			historyAL.add(currDateTask);
			counter++;
			maxCounter = counter;
		}
	}

	public void recordUpdatedFile(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyDate.add(fileName);
		historyAL.add(currDateTask);
		counter++;
		maxCounter = counter;
		//System.out.println("total= " + counter);
	}

	// Clear DateR & ALR after new command add/edit/done
	public void clearDateALR() {
		//System.out.println("Clear= " + counter);
		while (historyDate.size() != counter) {
			historyDate.removeLast();
			historyAL.removeLast();
		}
	}

	public void runUndo() {
		if (counter > 1) {
			counter--;
			//System.out.println("U= " + counter);
			(new WriteFile(historyDate.get(counter - 1),
					historyAL.get(counter - 1))).writeContents();
		} else {
			System.out.println("Undo Limit");
		}
	}

	public void runRedo() {
		if (counter < maxCounter) {
			counter++;
			//System.out.println("R= " + counter);
			(new WriteFile(historyDate.get(counter - 1),
					historyAL.get(counter - 1))).writeContents();
		} else {
			System.out.println("Undo Limit");
		}
	}
}
