package org.eclipse.wb.swt;

import java.util.ArrayList;

public class CommandCopy {
	public static void copyTask(String source_date, String index,
			String destination_date) {
		ArrayList<String> files = new ReadFile(source_date+".txt").readContents();
		try{
			String line = files.get(Integer.valueOf(index)-1);
			String[] splitter = line.split("]");
			String time = splitter[0].substring(1);
			String task = line.substring(splitter[0].length()+2); //']' and ' '
			new CommandAdd(destination_date, time, task).addTask();
		}
		catch (Exception e){
			WarningPopUp.infoBox("Invalid Index!",
					"ERROR");
		}
	}
}
