package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.Stack;

public class CommandHistory {

	public Stack<String> undoHistoryDate;
	public Stack<String> redoHistoryDate;
	// Switch over to Stack of Stack of Strings Stack<Stack<String>>
	// public Stack<ArrayList<String>> undoHistoryAL;
	// public Stack<ArrayList<String>> redoHistoryAL;

	public Stack<String> undoHistoryStr;
	public Stack<String> redoHistoryStr;

	public Stack<Integer> undoHistoryInt;
	public Stack<Integer> redoHistoryInt;

	public CommandHistory() {
		undoHistoryDate = new Stack<String>();
		redoHistoryDate = new Stack<String>();
		// undoHistoryAL = new Stack<ArrayList<String>>();
		// redoHistoryAL = new Stack<ArrayList<String>>();
		undoHistoryStr = new Stack<String>();
		redoHistoryStr = new Stack<String>();
		undoHistoryInt = new Stack<Integer>();
		redoHistoryInt = new Stack<Integer>();
	}

	// When done,add,edit called
	public void clearRedoHistory() {
		while (!redoHistoryDate.empty()) {
			redoHistoryDate.pop();
		}
		while (!redoHistoryStr.empty()) {
			redoHistoryStr.pop();
		}
		while (!redoHistoryInt.empty()) {
			redoHistoryInt.pop();
		}
	}

	// Before the done,add,edit method starts
	public void recordUndoHistory(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		undoHistoryDate.push(fileName);
		for (int i = 0; i < currDateTask.size(); i++) {
			undoHistoryStr.push(currDateTask.get(i));
		}
		undoHistoryInt.push(currDateTask.size());
	}
	
	// After the done,add,edit method end
	public void recordRedoHistory(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		undoHistoryDate.push(fileName);
		for (int i = 0; i < currDateTask.size(); i++) {
			undoHistoryStr.push(currDateTask.get(i));
		}
		undoHistoryInt.push(currDateTask.size());
	}

	public void runUndo() {
		if (!undoHistoryDate.empty()) {
			String fileName = undoHistoryDate.pop();
			int counter = undoHistoryInt.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			for (int i = 0; i < counter; i++) {
				String task = undoHistoryStr.pop();
				System.out.println(i + " " + task);
				currDateTask.add(task);
				redoHistoryStr.push(task);
			}
			redoHistoryInt.push(counter);
			redoHistoryDate.push(fileName);

			(new WriteFile(fileName, currDateTask)).writeContents();

		} else {
			System.out.println("Failed Undo");
		}
	}

	// ArrayList does not copy actual contents
	// Somehow copies exact contents making redo fail
	public void runRedo() {
		if (!redoHistoryDate.empty() && redoHistoryDate.size()-undoHistoryDate.size()==2) {
			String fileName = redoHistoryDate.pop();
			int counter = redoHistoryInt.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			for (int i = 0; i < counter; i++) {
				String task = redoHistoryStr.pop();
				System.out.println(i + " " + task);
				currDateTask.add(task);
				undoHistoryStr.push(task);
			}
			undoHistoryInt.push(counter);
			undoHistoryDate.push(fileName);

			(new WriteFile(fileName, currDateTask)).writeContents();
			System.out.println("Works");
		} else {
			System.out.println("Failed Redo");
		}
	}

}
