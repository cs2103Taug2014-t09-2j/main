package ido.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/* When commandDone is activated, store task in archives
 * undo will need to remove from archives
 * transfer archive storage to display in pop up, separate file
 * track commands for add/edit/done
 * for every new command > clear undo part till current b4 adding command
 * track activation sequence for done in parser
 * store info from commandDone
 * To display in pop up > copy each date into an arraylist > sort by date >
 * > pick out index to copy into new file
 * Note task cannot be just a date
 * 
 */

public class Archives {

	private static final String ARCHIVESTXT = "Archives.txt";
	private static final String ARCHIVES = "Archives";
	static File file_object = new File(ARCHIVESTXT);

	public static ArrayList<String> arcStorageContent;
	public static LinkedList<Integer> counterSize;
	static int counter = 0;
	static int maxCounter = 0;
	static int initialArcSize;
	static int counterSizeIndex = 0;

	public static LinkedList<Integer> ZorO;
	static int cmdCounter = 0; // for track
	static int cmdMaxCounter = 0; // for track

	public Archives() {
		arcStorageContent = new ArrayList<String>();
		counterSize = new LinkedList<Integer>();
		loadArchives();
		ZorO = new LinkedList<Integer>();
	}

	public void loadArchives() {

		// Read contents from file into storageContent
		arcStorageContent = (new FileAccessor(ARCHIVESTXT)).readContents();
		System.out.println("Archive size" + arcStorageContent.size());
		counter += arcStorageContent.size();
		maxCounter = counter;
		initialArcSize = arcStorageContent.size();

	}

	public void clear() {

		// System.out.println("Clear= " + counter);
		while (arcStorageContent.size() != counter) {
			arcStorageContent.remove(arcStorageContent.size() - 1);
		}
		maxCounter = counter;
		cmdMaxCounter = cmdCounter;
		// System.out.println("cleared history");
	}

	public static void addOneDoneTask(String date, String task) {
		System.out.println(date + " " + task);
		arcStorageContent.add(date + " " + task);
		counter++;
		maxCounter = counter;
		counterSize.add(1);
		counterSizeIndex = counterSize.size();

	}

	public static void addAllDoneTask(String date, ArrayList<String> task) {
		for (int i = 0; i < task.size(); i++) {
			System.out.println(date + " " + task.get(i));
			arcStorageContent.add(date + " " + task.get(i));
		}
		counter += task.size();
		maxCounter = counter;
		counterSize.add(task.size());
		counterSizeIndex = counterSize.size();
	}

	public static void undo() {

		if (counter - 1 > initialArcSize) {
			counter -= counterSize.get(counterSizeIndex - 1);
			counterSizeIndex--;

		} else {
			// System.out.println("max undo");
		}

	}

	public static void redo() {

		if (counter < maxCounter) {
			counter += counterSize.get(counterSizeIndex);
			counterSizeIndex++;

		} else {
			// System.out.println("max redo");
		}

	}

	// write all data from arcStorageContent into text file
	public void saveArchives() {
		ArrayList<String> arcTemp = new ArrayList<String>();
		for (int i = 0; i < arcStorageContent.size() && i < counter; i++) {
			System.out.println(arcStorageContent.get(i));
			arcTemp.add(arcStorageContent.get(i));
		}
		new FileAccessor(ARCHIVESTXT, arcTemp).writeContents();
	}

	public void cmdTAECD(String cmd) {
		if (cmd.equals("add") || cmd.equals("edit") || cmd.equals("copy")) {
			ZorO.add(0);
			cmdCounter++;
			cmdMaxCounter = cmdCounter;
		} else if (cmd.equals("done")) {
			ZorO.add(1);
			cmdCounter++;
			cmdMaxCounter = cmdCounter;
		}
	}

	public void executeCmd(int num) {
		if (num == -1 && cmdCounter > 0) {
			cmdCounter += num;
			if (ZorO.get(cmdCounter - 1) == 1) {
				undo();
			}

		} else if (num == 1 && cmdCounter < cmdMaxCounter) {
			cmdCounter += num;
			if (ZorO.get(cmdCounter - 1) == 1) {
				redo();
			}

		}
	}

}
