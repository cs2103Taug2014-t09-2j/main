package org.eclipse.wb.swt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Archives {

	// When commandDone is activated, store task in archives
	// undo will need to remove from archives
	// transfer archive storage to display in pop up, separate file

	// track commands for add/edit/done
	// for every new command > clear undo part till current b4 adding command
	// track activation sequence for done in parser
	// store info from commandDone?
	// To display in pop up > copy each date into an arraylist > sort by date >
	// > pick out index to copy into new file

	// Note task cannot be just a date
	
	private static final String ARCHIVESTXT = "Archives.txt";
	private static final String ARCHIVES = "Archives";
	static File file_object = new File(ARCHIVESTXT);

	public ArrayList<String> arcStorageDate;
	public <> arcStorageContent;
	public LinkedList<String> arcModDateSeq;

	public Archives() {
		arcStorageDate = new ArrayList<String>();
		arcStorageContent = new <>();
		arcModDateSeq = new LinkedList<String>();
	}

	private void loadArchives() {
		ArrayList<String> temp = new ArrayList();

		if (!file_object.exists()) {
			ArrayList<String> initialArc = new ArrayList<>();
			(new FileAccessor(ARCHIVESTXT, initialArc)).writeContents();

		} else if (file_object.exists()) {
			
			String date = "";
			
			// Read contents from file into storageContent
			ArrayList<String> currArc = new ArrayList<>();
			currArc = (new FileAccessor(ARCHIVESTXT)).readContents();
			
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
						arcStorageContent.add(new );
						
					}
					
				} else {
					temp.add(currArc.get(i));
				}
				
				// Add final date of archived task

			}
		}
	}
}
