package org.eclipse.wb.swt;

import java.util.ArrayList;
import java.util.Stack;

/* UNUSED
 * TOO MANY CONDITIONS TO SATISFY
 */

public class CommandHistory {

	// For Undo
	public Stack<String> historyDate;
	public Stack<ArrayList<String>> historyAL;

	// For Redo
	public Stack<String> historyDateR;
	public Stack<ArrayList<String>> historyALR;

	public Stack<String> historyCmd;

	// public int bugCounter = 0;

	public CommandHistory() {
		historyDate = new Stack<String>();
		historyAL = new Stack<ArrayList<String>>();
		historyDateR = new Stack<String>();
		historyALR = new Stack<ArrayList<String>>();
		historyCmd = new Stack<String>();

	}

	// Record Base file
	public void recordBaseFile(String date) {
		if (historyDate.empty()) {
			String fileName = date + ".txt";
			ArrayList<String> currDateTask = new ArrayList<>();
			currDateTask = (new ReadFile(fileName)).readContents();
			historyDate.push(fileName);
			historyAL.push(currDateTask);
		}
	}

	// Record updated file after the done,add,edit method
	public void recordUpdatedFile(String date) {
		String fileName = date + ".txt";
		ArrayList<String> currDateTask = new ArrayList<>();
		currDateTask = (new ReadFile(fileName)).readContents();
		historyDate.push(fileName);
		historyAL.push(currDateTask);
		// System.out.println(currDateTask.size());

	}

	// Track commands: ignore zoom, search
	public void trackCmd(String cmd) {
		String[] arrStr = cmd.split(" ", 2);
		//System.out.println(arrStr[0]);
		if (!arrStr[0].equals("zoom") && !arrStr[0].equals("search")) {
			historyCmd.push(cmd);
			//System.out.println("added!");
		}
	}

	// Clear DateR & ALR after new command add/edit/done
	public void clearDateRALR() {
		while (!historyDateR.empty()) {
			historyDateR.pop();
		}
		while (!historyALR.empty()) {
			historyALR.pop();
		}

	}

	// every new command requires saving of the current state of the file
//	public void getOriginalState() {
//		if (historyCmd.size() > 1) {
//			String checkNewCmd = historyCmd.pop();
//			String checkUndoRedo = historyCmd.pop();
//			historyCmd.push(checkUndoRedo);
//			historyCmd.push(checkNewCmd);
//			if ((checkUndoRedo.equals("undo") || checkUndoRedo.equals("redo"))
//					&& (!checkNewCmd.equals("undo") || !checkNewCmd
//							.equals("redo"))) {
//				historyDate.push(historyDateR.pop());
//				historyAL.push(historyALR.pop());
//			}
//		}
//	}

	// every new command after undo results in a duplicate old file
	// so have to pop once from Date & AL
	// public void checkPrevPrevNcheckPrev() {
	// if (historyCmd.size() > 1) {
	// String original = historyCmd.pop();
	// String checker = historyCmd.pop();
	// historyCmd.push(checker);
	// historyCmd.push(original);
	// if ((original.equals("undo") || original.equals("redo"))
	// && (!checker.equals("undo") || !checker.equals("redo"))) {
	// historyDate.pop();
	// historyAL.pop();
	// }
	//
	// }
	// }

	// Check prev prev command if undo or redo
	// public boolean checkPrevPrev(Stack<String> historyCmd) {
	// if (historyCmd.size() != 0) {
	// String original = historyCmd.pop();
	// String checker = historyCmd.pop();
	// historyCmd.push(checker);
	// historyCmd.push(original);
	// if (checker.equals("undo") || checker.equals("redo")) {
	// return false;
	// }
	// return true;
	// } else {
	// return false;
	// }
	// }

	public void runHistoryUndo() {

		// For start of every non-consecutive undo
		// If prev prev command is not undo/redo
		// if (checkPrevPrev(historyCmd)) {
		// if (historyDateR.empty()) {
		// String fileName = historyDate.pop();
		// historyDate.push(fileName);
		// ArrayList<String> currDateTask = new ArrayList<>();
		// currDateTask = (new ReadFile(fileName)).readContents();
		// historyDateR.push(fileName);
		// historyALR.push(currDateTask);
		//
		// }
		//
		// }

		if (historyDateR.empty()) {
			historyDateR.push(historyDate.pop());
			historyALR.push(historyAL.pop());
		}

		if (!historyDate.empty()) {
			String fileName = historyDate.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			// System.out.println(currDateTask.size());
			currDateTask = historyAL.pop();
			(new WriteFile(fileName, currDateTask)).writeContents();
			historyDateR.push(fileName);
			historyALR.push(currDateTask);

		} else {
			System.out.println("Undo Limit");

		}
	}

	public void runHistoryRedo() {

		if (historyDate.empty()) {
			historyDate.push(historyDateR.pop());
			historyAL.push(historyALR.pop());
		}

		if (!historyDateR.empty()) {
			String fileName = historyDateR.pop();
			ArrayList<String> currDateTask = new ArrayList<>();
			// System.out.println(currDateTask.size());
			currDateTask = historyALR.pop();
			(new WriteFile(fileName, currDateTask)).writeContents();
			historyDate.push(fileName);
			historyAL.push(currDateTask);

		} else {
			System.out.println("Redo Limit");

		}

	}

}
