package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistory {

	public Stack<String> historyDate;
	public Stack<ArrayList<String>> historyAL;
	public Stack<String> historyCmd;

	public CommandHistory() {
		historyDate = new Stack<String>();
		historyAL = new Stack<ArrayList<String>>();
		historyCmd = new Stack<String>();
	}

	// Before the done,add,edit method starts
	public void recordHistory(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyDate.push(fileName);
		historyAL.push(currDateTask);

	}

	public void recordInputHistory(String input) {
		historyCmd.push(input);
	}

	public void runHistoryUndo() {

		if (!historyDate.empty()) {
			String fileName = historyDate.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			currDateTask = historyAL.pop();
			(new WriteFile(fileName, currDateTask)).writeContents();

		} else {
			System.out.println("Undo Limit");

		}

	}

	public String runHistoryRedo() {
		return historyCmd.pop();
	}
}
