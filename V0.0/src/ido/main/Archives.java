package ido.main;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

//@author A0110679A 

/*
 * When commandDone is activated, store task in archives 
 * Undo/redo will need to remove from archives
 * Transfer archive storage to display in pop up
 * Track commands for add/edit/done for every new command
 * Clear undo part when activation of new command that modifies file
 * Store info from commandDone
 */

public class Archives {

	private static final String ARCHIVESTXT = "archives.txt";
	static File file_object = new File(ARCHIVESTXT);

	public static ArrayList<String> arcStorageContent;
	public static LinkedList<Integer> counterSize;
	static int counter = 0;
	static int maxCounter = 0;
	static int initialArcSize;
	static int counterSizeIndex = 0;

	public static LinkedList<Integer> ZorO;
	static int cmdCounter = 0; // for tracking
	static int cmdMaxCounter = 0; // for tracking

	// Constructor on start up
	public Archives() {
		arcStorageContent = new ArrayList<String>();
		counterSize = new LinkedList<Integer>();
		loadArchives();
		ZorO = new LinkedList<Integer>();
	}

	public void loadArchives() {
		// Read contents from file into storageContent
		arcStorageContent = (new FileAccessor(ARCHIVESTXT)).readContents();
		counter += arcStorageContent.size();
		maxCounter = counter;
		initialArcSize = arcStorageContent.size();

	}

	// Resets counter & contents to current state when new command modify file
	public void clear() {

		while (arcStorageContent.size() != counter) {
			arcStorageContent.remove(arcStorageContent.size() - 1);
		}
		maxCounter = counter;
		cmdMaxCounter = cmdCounter;
	}

	// Add one done task
	public static void addOneDoneTask(String date, String task) {

		arcStorageContent.add(date + " " + task);
		counter++;
		maxCounter = counter;
		counterSize.add(1);
		counterSizeIndex = counterSize.size();

	}

	// Add multiple done task
	public static void addAllDoneTask(String date, ArrayList<String> task) {

		for (int i = 0; i < task.size(); i++) {
			arcStorageContent.add(date + " " + task.get(i));
		}
		counter += task.size();
		maxCounter = counter;
		counterSize.add(task.size());
		counterSizeIndex = counterSize.size();

	}

	// Run undo of archives
	public static void undo() {

		if (counter > initialArcSize) {
			counter -= counterSize.get(counterSizeIndex - 1);
			counterSizeIndex--;
		}

	}

	// Run redo of archives
	public static void redo() {
		if (counter < maxCounter) {
			counter += counterSize.get(counterSizeIndex);
			counterSizeIndex++;
		}

	}

	// write all data from arcStorageContent into text file
	public void saveArchives() {

		ArrayList<String> arcTemp = new ArrayList<String>();
		for (int i = 0; i < arcStorageContent.size(); i++) {
			if (i < counter) {
				arcTemp.add(arcStorageContent.get(i));
			}
		}
		new FileAccessor(ARCHIVESTXT, arcTemp).writeContents();

	}

	// Tracks the cmd for Add Edit Copy Done
	public void cmdTAECD(String cmd) {

		if (cmd.equals("add") || cmd.equals("edit") || cmd.equals("copy")
				|| cmd.equals("delete")) {
			ZorO.add(0);
			cmdCounter++;
			cmdMaxCounter = cmdCounter;
		} else if (cmd.equals("done")) {
			ZorO.add(1);
			cmdCounter++;
			cmdMaxCounter = cmdCounter;
		}

	}

	// Determine if need to undo/redo for previous/after cmd
	public void executeCmd(int num) {
		if (num == -1 && cmdCounter > 0) {
			if (ZorO.get(cmdCounter - 1) == 1) {
				undo();
			}
			cmdCounter += num;

		} else if (num == 1 && cmdCounter < cmdMaxCounter) {
			if (ZorO.get(cmdCounter) == 1) {
				redo();
			}
			cmdCounter += num;

		}
	}

}
