package org.eclipse.wb.swt;

import java.io.IOException;
import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.Stack;

public class CommandUndo {

	public Stack<String> commandHistory;

	public CommandUndo() {
		commandHistory = new Stack<String>();
	}

	public void copyAddCommandToReverse(String date, String time, String task) {
		int arrLine = (new CommandSearchCurrDate(date, time, task)).searchString();
		System.out.println("done " + date + " " + arrLine);
		commandHistory.push("done " + date + " " + arrLine);
		
	}

	public void copyDoneCommandToReverseSpecific(String date, int position) {

	}
	
	public void copyDoneCommandToReverseAll(String date) {
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(date+".txt")).readContents();
		int originalSize = currDateTask.size();
		for(int i = 0; i < originalSize; i++){
			String[] str = (currDateTask.get(i)).split(" ", 2);
			//System.out.println(date + " " + str[0].substring(1,str[0].length()-1) + " " + str[1]);
			commandHistory.push(date + " " + str[0].substring(1,str[0].length()-1) + " " + str[1]);
		}
		commandHistory.push(Integer.toString(originalSize));
	}

	public void copyEditCommandToReverse(String date, int position,
			String update) {

	}

	// Print command to be executed
	public void runReverseCommand() throws IOException {
		if (!commandHistory.isEmpty()) {
			String checkStr = commandHistory.pop();
			if (checkStr.length() == 1) {
				// For done all only, adds back in to date.txt 
				int popNo = Integer.parseInt(checkStr);
				for (int i = 0; i < popNo; i++) {
					//String[] arrStr = commandHistory.pop().split(" ", 3);
					String str = commandHistory.pop();
					String[] arrStr = str.split(" ", 3);
					(new CommandAdd(arrStr[0],arrStr[1],arrStr[2])).addTask();
				}
			} else {
				// For other commands
				String[] arrStr = checkStr.split(" ", 2);
				//System.out.println(arrStr[0]);
				if(arrStr[0].equals("done")){
					String[] arrStrDS = arrStr[1].split(" ", 2);
					(new CommandDone(arrStrDS[0],arrStrDS[1])).clearDateTaskSpecific();
				}
			}
		} else {
			// Unable to undo
			System.out.println("Undo failed");
		}
	}
}
