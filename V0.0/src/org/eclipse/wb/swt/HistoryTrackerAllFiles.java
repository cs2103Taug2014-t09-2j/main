package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.LinkedList;

/* This class stores individual files save state
 * 
 */

public class HistoryTrackerAllFiles {

	public ArrayList<String> storageDate;
	public ArrayList<HistoryTrackerSingleFile> storageContent;
	public LinkedList<String> modDateSeq;
	int counter = 0; // for modDateSeq
	int maxCounter = 0; // for modDateSeq

	public HistoryTrackerAllFiles() {
		storageDate = new ArrayList<String>();
		storageContent = new ArrayList<HistoryTrackerSingleFile>();
		modDateSeq = new LinkedList<String>();
	}

	public void checkBaseFile(String date) {
		if (storageDate.size() == 0) { // Initial check
			storageDate.add(date);
			storageContent.add((new HistoryTrackerSingleFile(date)));

		} else { // Check if date exist
			int index = -1;
			for (int i = 0; i < storageDate.size(); i++) {
				if (storageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				storageDate.add(date);
				storageContent.add(new HistoryTrackerSingleFile(date));
			}
			// System.out.println(index);
		}

	}

	public void clear() {
		for (int i = 0; i < storageContent.size(); i++) {
			storageContent.get(i).clearALR();
		}
		while (modDateSeq.size() != counter) {
			modDateSeq.removeLast();
		}
		maxCounter = counter;
		// System.out.println("cleared history");
	}

	public void recordUpdatedFile(String date) {
		int index = -1;
		for (int i = 0; i < storageDate.size(); i++) {
			if (storageDate.get(i).equals(date)) {
				index = i;
				break;
			}
		}
		storageContent.get(index).recordUpdatedFile(date);
		modDateSeq.add(date);
		counter++;
		maxCounter = counter;
		// System.out.println("record updated" + index);
	}

	public void undo() throws IndexOutOfBoundsException {

		int index = -1;
		if (counter - 1 < 0) {
			// System.out.println("max undo");
			WarningPopUp.infoBox("Undo Limit", "WARNING");

		} else {
			String date = modDateSeq.get(counter - 1);

			for (int i = 0; i < storageDate.size(); i++) {
				if (storageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}
			
			try {
				if (index != -1) {
					storageContent.get(index).runUndo();
					counter--;
				}
			} catch (IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Index not found!");
			}
			
			// System.out.println(index);

		}

	}

	public void redo() {
		int index = -1;
		if (counter + 1 > maxCounter) {
			// System.out.println("max redo");
			WarningPopUp.infoBox("Redo Limit", "WARNING");

		} else {
			String date = modDateSeq.get(counter);

			for (int i = 0; i < storageDate.size(); i++) {
				if (storageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				storageContent.get(index).runRedo();
				counter++;
			}
			// System.out.println(index);

		}

	}
}
