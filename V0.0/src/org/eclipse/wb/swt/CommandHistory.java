package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistory {

	public Stack<String> undoHistoryDate;
	public Stack<String> redoHistoryDate;
	// Switch over to Stack of Stack of Strings Stack<Stack<String>>
	public Stack<ArrayList<String>> undoHistoryAL;
	public Stack<ArrayList<String>> redoHistoryAL;

	public CommandHistory() {
		undoHistoryDate = new Stack<String>();
		redoHistoryDate = new Stack<String>();
		undoHistoryAL = new Stack<ArrayList<String>>();
		redoHistoryAL = new Stack<ArrayList<String>>();
	}

	public void clearRedoHistory() {
		while (!redoHistoryDate.empty()) {
			redoHistoryDate.pop();
		}
		while (!redoHistoryAL.empty()) {
			redoHistoryAL.pop();
		}
	}

	// Before the done,add,edit method starts
	public void recordHistory(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		undoHistoryDate.push(fileName);
		undoHistoryAL.push(currDateTask);
	}

	public void runUndo() {
		if (!undoHistoryDate.empty()) {
			String fileName = undoHistoryDate.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			currDateTask = undoHistoryAL.pop();
			redoHistoryDate.push(fileName);			
			redoHistoryAL.push(currDateTask);
			(new WriteFile(fileName, currDateTask)).writeContents();
			

		} else {
			System.out.println("Failed Undo");
		}
	}

	// ArrayList does not copy actual contents
	public void runRedo() {
		if (!redoHistoryDate.empty()) {
			String fileName = redoHistoryDate.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			currDateTask = redoHistoryAL.pop();
			undoHistoryDate.push(fileName);			
			(new WriteFile(fileName, currDateTask)).writeContents();			
			System.out.println("Works");
		} else {
			System.out.println("Failed Redo");
		}
	}

}
