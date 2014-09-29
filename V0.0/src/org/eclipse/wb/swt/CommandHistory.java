package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistory {

	public Stack<String> historyDate;
	public Stack<String> historyDateSave;
	public Stack<ArrayList<String>> historyAL;
	public Stack<ArrayList<String>> historyALSave;

	public CommandHistory() {
		historyDate = new Stack<String>();
		historyDateSave = new Stack<String>();
		historyAL = new Stack<ArrayList<String>>();
		historyALSave = new Stack<ArrayList<String>>();
	}

	public void clearHistorySave() {
		while (!historyALSave.empty()) {
			historyALSave.pop();
		}
		while (!historyDateSave.empty()) {
			historyDateSave.pop();
		}

	}

	// Before the done,add,edit method starts
	public void recordHistory(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		// Check if duplicate list
		if (!historyAL.empty()) {
			ArrayList<String> copy = new ArrayList<>();
			copy = historyAL.pop();
			historyAL.push(copy);
			if (copy.size() == currDateTask.size()) {
				for (int i = 0; i < copy.size(); i++) {
					if (!copy.contains(currDateTask.get(i))) {
						historyDate.push(fileName);
						historyAL.push(currDateTask);
						break;
					}
				}
			} else {
				historyDate.push(fileName);
				historyAL.push(currDateTask);
			}
		} else {
			historyDate.push(fileName);
			historyAL.push(currDateTask);
		}

		// System.out.println("HD=" + historyDate.size());
	}

	public void recordHistorySave(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyDateSave.push(fileName);
		historyALSave.push(currDateTask);
		// System.out.println("HDS=" + historyDateSave.size());

	}

	public void runHistory(String cmd) {
		if (cmd.equals("undo")) {
			if (historyDate.size() != 1) {
				String fileName = historyDate.pop();
				ArrayList<String> currDateTask = new ArrayList<>();
				currDateTask = historyAL.pop();
				historyDateSave.push(fileName);
				historyALSave.push(currDateTask);
				(new WriteFile(fileName, currDateTask)).writeContents();

			} else {
				System.out.println("Undo Limit");
				String fileName = historyDate.pop();
				ArrayList<String> currDateTask = new ArrayList<>();
				currDateTask = historyAL.pop();
				(new WriteFile(fileName, currDateTask)).writeContents();
				historyDate.push(fileName);
				historyAL.push(currDateTask);
			}
		} else if (cmd.equals("redo")) {
			if (historyDateSave.size() != 1) {
				String fileName = historyDateSave.pop();
				ArrayList<String> currDateTask = new ArrayList<>();
				currDateTask = historyALSave.pop();
				historyDate.push(fileName);
				historyAL.push(currDateTask);
				(new WriteFile(fileName, currDateTask)).writeContents();

			} else {
				System.out.println("Redo Limit");
				String fileName = historyDateSave.pop();
				ArrayList<String> currDateTask = new ArrayList<>();
				currDateTask = historyALSave.pop();
				(new WriteFile(fileName, currDateTask)).writeContents();
				historyDateSave.push(fileName);
				historyALSave.push(currDateTask);
			}
		}
	}

}
