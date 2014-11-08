package ido.main;

import java.util.ArrayList;
import java.util.LinkedList;

//@author A0110679A 

/* This class stores the individual states of the text files in sequence * 
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

	}

	public void undo() throws IndexOutOfBoundsException {

		int index = -1;

		if (counter - 1 < 0) {
			WarningPopUp.infoBox("Nothing to Undo", "WARNING");

		} else {
			String date = modDateSeq.get(counter - 1);

			for (int i = 0; i < storageDate.size(); i++) {
				if (storageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}

			if (index != -1) {
				storageContent.get(index).runUndo();
				counter--;
			}

			if (index == -1) {
				throw new IndexOutOfBoundsException("Index not found!");
			}

		}

	}

	public void redo() throws IndexOutOfBoundsException {

		int index = -1;

		if (counter + 1 > maxCounter) {
			WarningPopUp.infoBox("Nothing to Redo", "WARNING");

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

			if (index == -1) {
				throw new IndexOutOfBoundsException("Index not found!");
			}

		}

	}
	
	public String getCurrFileDate(){
		return modDateSeq.get(counter);
	}
}
