package org.eclipse.wb.swt;

//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//// import java.util.ArrayList;
//import java.util.Stack;

/* UNUSED
 * CONFLICTING CONDITIONS
 * COMMENTED ALL TO PREVENT ERROR MSG WHEN RUNNING GUI
 */

public class ZCommandUndoRedo {

//	public Stack<String> undoHistory;
//	public Stack<String> redoHistory;
//	public Stack<String> executedUndoHistory;
//
//	public CommandUndoRedo() {
//		undoHistory = new Stack<String>();
//		redoHistory = new Stack<String>();
//		executedUndoHistory = new Stack<String>();
//	}
//
//	public void storeOriginalCommand(String command, String remain) {
//		redoHistory.push((command + " " + remain).trim());
//
//	}
//
//	public void runRedo() {
//		if (redoHistory.size() > undoHistory.size()) {
//			String input = redoHistory.pop();
//			String[] arrInput = input.split(" ", 2);
//			if ((new IsValidDate(arrInput[1]).testValidDate())) {
//				try {
//					(new CommandDone(arrInput[1])).clearDateTaskAll();
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				int counter = Integer.parseInt(executedUndoHistory.pop());
//				for (int i = 0; i < counter; i++) {
//					undoHistory.push(executedUndoHistory.pop());
//				}
//				undoHistory.push(Integer.toString(counter));
//
//			} else if (arrInput[0].equals("done")) {
//				String[] arrStrDS = arrInput[1].split(" ", 2);
//				(new CommandDone(arrStrDS[0], arrStrDS[1]))
//						.clearDateTaskSpecific();
//				undoHistory.push(executedUndoHistory.pop());
//
//			} else if (arrInput[0].equals("edit")) {
//				String[] arrStrE = arrInput[1].split(" ", 4);
//				try {
//					(new CommandEdit(arrStrE[0], arrStrE[1], arrStrE[2],
//							arrStrE[3])).edit();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				undoHistory.push(executedUndoHistory.pop());
//
//			} else if (arrInput[0].equals("add")) {
//				String[] arrStrAdd = arrInput[1].split(" ", 3);
//				try {
//					(new CommandAdd(arrStrAdd[0], arrStrAdd[1], arrStrAdd[2]))
//							.addTask();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				undoHistory.push(executedUndoHistory.pop());
//
//			}
//
//		} else {
//			System.out.println("Failed Redo");
//		}
//
//	}
//
//	public void copyAddCommandToReverse(String date, String time, String task) {
//		int arrLine = (new CommandSearchCurrDate(date, time, task))
//				.searchStringPosition();
//		// System.out.println("done " + date + " " + arrLine);
//		undoHistory.push("done " + date + " " + arrLine);
//
//	}
//
//	public void copyDoneSpecificCommandToReverse(String date, String position) {
//		ArrayList<String> currDateTask = new ArrayList<>();
//		currDateTask = (new ReadFile(date + ".txt")).readContents();
//		int location = Integer.parseInt(position);
//		String str = currDateTask.get(location - 1);
//		String[] arrStr = str.split(" ", 2);
//		// System.out.println("add " + date + " " +
//		// arrStr[0].substring(1,arrStr[0].length()-1) + " " + arrStr[1]);
//		undoHistory.push("add " + date + " "
//				+ arrStr[0].substring(1, arrStr[0].length() - 1) + " "
//				+ arrStr[1]);
//	}
//
//	public void copyDoneAllCommandToReverse(String date) {
//		ArrayList<String> currDateTask = new ArrayList<>();
//		currDateTask = (new ReadFile(date + ".txt")).readContents();
//		int originalSize = currDateTask.size();
//		if (currDateTask.size() != 0) {
//			for (int i = 0; i < originalSize; i++) {
//				String[] arrStr = (currDateTask.get(i)).split(" ", 2);
//				// System.out.println(date + " " +
//				// arrStr[0].substring(1,arrStr[0].length()-1) + " " +
//				// arrStr[1]);
//				undoHistory.push(date + " "
//						+ arrStr[0].substring(1, arrStr[0].length() - 1) + " "
//						+ arrStr[1]);
//			}
//			undoHistory.push(Integer.toString(originalSize));
//		}
//	}
//
//	public void copyEditCommandToReverse(String date, String position,
//			String choice, String update, String oldInfo) {
//		// Store old Info
//		// Store new position
//		int newindex = 0;
//		String[] arrStr = oldInfo.split(" ", 2); // contains old time and task
//		// System.out.println("1 " + arrStr[0] + " 2 " + arrStr[1]);
//		if (choice.equals("time")) {
//			newindex = (new CommandSearchCurrDate(date, update, arrStr[1]))
//					.searchStringPosition();
//			// System.out.println("edit " + date + " " + newindex + " " + choice
//			// + " " + arrStr[0].substring(1,arrStr[0].length()-1));
//			undoHistory.push("edit " + date + " " + newindex + " " + choice
//					+ " " + arrStr[0].substring(1, arrStr[0].length() - 1));
//		} else if (choice.equals("task")) {
//			newindex = (new CommandSearchCurrDate(date, arrStr[0].substring(1,
//					arrStr[0].length() - 1), update)).searchStringPosition();
//			undoHistory.push("edit " + date + " " + newindex + " " + choice
//					+ " " + arrStr[1]);
//		} else if (choice.equals("all")) {
//			String[] arrStr2 = update.split(" ", 2);
//			newindex = (new CommandSearchCurrDate(date, arrStr2[0], arrStr2[1]))
//					.searchStringPosition();
//			// System.out.println("edit " + date + " " + newindex + " " + choice
//			// + " " + arrStr[0].substring(1,arrStr[0].length()-1) + " " +
//			// arrStr[1]);
//			undoHistory.push("edit " + date + " " + newindex + " " + choice
//					+ " " + arrStr[0].substring(1, arrStr[0].length() - 1)
//					+ " " + arrStr[1]);
//		}
//	}
//
//	// Print command to be executed
//	public void runUndo() throws IOException {
//		if (!undoHistory.isEmpty()) {
//			String checkStr = undoHistory.pop();
//			if (checkStr.length() == 1) {
//				// For done all only, adds back in to date.txt
//				int popNo = Integer.parseInt(checkStr);
//				for (int i = 0; i < popNo; i++) {
//					// String[] arrStr = undoHistory.pop().split(" ", 3);
//					String str = undoHistory.pop();
//					executedUndoHistory.push(str);
//					String[] arrStr = str.split(" ", 3);
//					(new CommandAdd(arrStr[0], arrStr[1], arrStr[2])).addTask();
//				}
//				executedUndoHistory.push(checkStr);
//			} else {
//				executedUndoHistory.push(checkStr);
//				// For other commands
//				String[] arrStr = checkStr.split(" ", 2);
//				// System.out.println(arrStr[0]);
//				if (arrStr[0].equals("done")) {
//					String[] arrStrDS = arrStr[1].split(" ", 2);
//					(new CommandDone(arrStrDS[0], arrStrDS[1]))
//							.clearDateTaskSpecific();
//				} else if (arrStr[0].equals("add")) {
//					String[] arrStrAdd = arrStr[1].split(" ", 3);
//					(new CommandAdd(arrStrAdd[0], arrStrAdd[1], arrStrAdd[2]))
//							.addTask();
//				} else if (arrStr[0].equals("edit")) {
//					String[] arrStrE = arrStr[1].split(" ", 4);
//					// System.out.println(arrStrE[0] + arrStrE[1] + arrStrE[2] +
//					// arrStrE[3]);
//					(new CommandEdit(arrStrE[0], arrStrE[1], arrStrE[2],
//							arrStrE[3])).edit();
//				}
//			}
//		} else {
//			// Unable to undo
//			System.out.println("Undo failed");
//		}
//	}
}
