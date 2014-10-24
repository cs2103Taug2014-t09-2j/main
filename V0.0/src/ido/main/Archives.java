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

	public static ArrayList<String> arcStorageDate;
	public static ArrayList<ArchivesTracker> arcStorageContent;
	public static LinkedList<String> arcModDateSeq;
	static int counter = 0; // for arcModDateSeq
	static int maxCounter = 0; // for arcModDateSeq

	public Archives() {
		arcStorageDate = new ArrayList<String>();
		arcStorageContent = new ArrayList<ArchivesTracker>();
		arcModDateSeq = new LinkedList<String>();
		loadArchives();
	}

	public void loadArchives() {
		ArrayList<String> temp = new ArrayList<String>();

		if (!file_object.exists()) {
			ArrayList<String> initialArc = new ArrayList<>();
			(new FileAccessor(ARCHIVESTXT, initialArc)).writeContents();
			System.out.println("executed");
		} else if (file_object.exists()) {

			String date = "";

			// Read contents from file into storageContent
			ArrayList<String> currArc = new ArrayList<>();
			currArc = (new FileAccessor(ARCHIVESTXT)).readContents();
			System.out.println("Archive size"+currArc.size());

			for (int i = 0; i < currArc.size(); i++) {

				// issues with readfile and write file numbering
				String chkdate = currArc.get(i).split(" ", 2)[1];
				boolean check;

				// check if if task or date
				if (chkdate.length() != 6) {
					check = false;
				} else {
					check = new IsValidDate(chkdate).testValidDate();
				}

				// sort into arcStorageDate or arcStorageContent
				if (check) {
					arcStorageDate.add(currArc.get(i));
					date = currArc.get(i);
					temp.clear();
					if (temp.size() != 0) {
						// records previously archived task
						arcStorageContent.add(new ArchivesTracker(date, temp));
					}

				} else {
					temp.add(currArc.get(i));
				}

				// Add final date of archived task
				arcStorageContent.add(new ArchivesTracker(date, temp));

			}
		}
	}

	public void clear() {
		for (int i = 0; i < arcStorageContent.size(); i++) {
			arcStorageContent.get(i).clearArcALR();
		}
		while (arcModDateSeq.size() != counter) {
			arcModDateSeq.removeLast();
		}
		maxCounter = counter;
		// System.out.println("cleared history");
	}

	public static void addDoneTask(String date, ArrayList<String> task) {
		int index = -1;
		for (int i = 0; i < arcStorageDate.size(); i++) {
			if (arcStorageDate.get(i).equals(date)) {
				index = i;
				break;
			}
		}

		// -1 > creates a new position to store the archived task
		if (index == -1) {
			System.out.println("from done to archive: "+task.size());
			arcStorageDate.add(date);
			arcStorageContent.add(new ArchivesTracker(date, new ArrayList<String>()));
			arcStorageContent.get(arcStorageContent.size()-1).recordNewTask(task);
			System.out.println("generating new date");

		} else {
			arcStorageContent.get(index).recordNewTask(task);
			arcModDateSeq.add(date);
			counter++;
			maxCounter = counter;
			System.out.println("date found");
		}
	}

	public static void undo() {

		int index = -1;
		if (counter - 1 < 0) {
			// System.out.println("max undo");

		} else {
			String date = arcModDateSeq.get(counter - 1);

			for (int i = 0; i < arcStorageDate.size(); i++) {
				if (arcStorageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}

			if (index != -1) {
				arcStorageContent.get(index).runUndo();
				counter--;
			}

			// System.out.println(index);

		}

	}

	public static void redo() {
		int index = -1;
		if (counter + 1 > maxCounter) {
			// System.out.println("max redo");
			WarningPopUp.infoBox("Nothing to Redo", "WARNING");

		} else {
			String date = arcModDateSeq.get(counter);

			for (int i = 0; i < arcStorageDate.size(); i++) {
				if (arcStorageDate.get(i).equals(date)) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				arcStorageContent.get(index).runRedo();
				counter++;
			}
			// System.out.println(index);

		}

	}

	// write all data from arcStorageContent into text file
	public void saveArchives() {
		ArrayList<String> arcAll = new ArrayList<String>();
		ArrayList<String> arcTemp = new ArrayList<String>();
		for (int i = 0; i < arcStorageContent.size(); i++) {
			arcTemp = arcStorageContent.get(i).getarcAL();
			arcAll.add(arcStorageContent.get(i).getDate()); 
			for (int j = 0; j < arcTemp.size(); j++) {
				arcAll.add(arcTemp.get(j));
			}
		}
		new FileAccessor(ARCHIVESTXT,arcAll).writeContents();
	}

}
