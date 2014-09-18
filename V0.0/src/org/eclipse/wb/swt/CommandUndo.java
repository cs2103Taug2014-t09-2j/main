package org.eclipse.wb.swt;

import java.util.Stack;

public class CommandUndo {

	public Stack<String> commandHistory;

	public CommandUndo() {
		commandHistory = new Stack<String>();
	}

	public void copyAddReverseCommand(String date, int position) {
		commandHistory.push("done " + date + " " + position);
	}

	public void copyDoneReverseCommand(String date, String time, String task) {
		commandHistory.push("add " + date + " " + time + " " + task);
	}

	public void copyEditReverseCommand(String date, int position,
			String oldTime, String oldTask) {
		commandHistory.push("edit " + date + " " + position + " " + oldTime
				+ " " + oldTask);
	}

	// Print command to be executed
	public void printReverseCommand() {
		if (!commandHistory.isEmpty()) {
			String checkInt = commandHistory.pop();
			if (checkInt.length() == 1) {
				// For done all
				int popNo = Integer.parseInt(checkInt);
				for (int i = 0; i < popNo; i++) {
					System.out.println(commandHistory.pop());
				}
			} else {
				// For other commands
				System.out.println(commandHistory.pop());
			}
		} else {
			// Unable to undo
			System.out.println("Undo failed");
		}
	}
}
