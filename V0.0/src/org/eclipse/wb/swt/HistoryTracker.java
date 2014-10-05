package org.eclipse.wb.swt;

import java.util.ArrayList;

public class HistoryTracker {
	public ArrayList<String> storageDate;
	public ArrayList<CommandHistoryLinkedList> storageContent;
	public ArrayList<String> modDateSeq;
	int counter = 0; // for seq

	public HistoryTracker() {
		storageDate = new ArrayList<String>();
		storageContent = new ArrayList<CommandHistoryLinkedList>();
		modDateSeq = new ArrayList<String>();

	}

	public void checkBaseFile(String date) {
		if (storageDate.size() == 0) { // Initial check
			storageDate.add(date);
			storageContent.add((new CommandHistoryLinkedList(date)));
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
				storageContent.add(new CommandHistoryLinkedList(date));
			}
			System.out.println(index);
		}

	}

	public void clear() {
		for (int i = 0; i < storageContent.size(); i++) {
			storageContent.get(i).clearALR();
		}
		System.out.println("cleared history");
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
		System.out.println("record updated");
	}

	public void undo() {
		int index = -1;
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
		System.out.println(index);

	}

	public void redo() {
		int index = -1;
		String date = modDateSeq.get(counter - 1);

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
		System.out.println(index);

	}
}
