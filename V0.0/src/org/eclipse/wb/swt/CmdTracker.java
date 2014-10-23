package org.eclipse.wb.swt;

import java.util.LinkedList;

public class CmdTracker {

	public LinkedList<Integer> ZorO;
	static int counter = 0; //
	static int maxCounter = 0; //

	public CmdTracker(){
		ZorO = new LinkedList<Integer>();
	}
	
	public void cmdTADE(String cmd) {
		String check = cmd;
		if (check.equals("add") || check.equals("edit") || check.equals("copy")) {
			ZorO.add(0);
			counter++;
			maxCounter = counter;
		} else if (check.equals("done")) {
			ZorO.add(1);
			counter++;
			maxCounter = counter;
		}
	}

	public void executeCmd(int num) {
		if (num == -1 && counter > 0) {
			Archives.undo();
			counter--;
		} else if (num == 1 && counter < maxCounter) {
			Archives.redo();
			counter++;
		}
	}
	
	public void clear(){
		while (ZorO.size() != counter) {
			ZorO.removeLast();
		}
		maxCounter = counter;
	}

}
