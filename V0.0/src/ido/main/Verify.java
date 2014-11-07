package ido.main;

//@author A0110679A 

/*
 * This class check if the command input has correct syntax before executing the command
 * This class is a compilation of errors from all the command classes
 */

public class Verify {

	public String input;
	public static boolean check;
	public static String currDate;
	public static String currIndex;
	public static String newTime;
	public static String newTask;
	public static String destDate;
	public static String editTTA;
	public static String editMod;

	// Constructor
	public Verify() {
		input = "";
		check = false;
	}

	// Mutator
	void setInput(String input) {
		this.input = input;
	}

	void setDate(String date) {
		currDate = date;
	}

	void setIndex(String index) {
		currIndex = index;
	}

	void setTime(String time) {
		newTime = time;
	}

	void setTask(String task) {
		newTask = task;
	}

	void setDestDate(String date) {
		destDate = date;
	}

	void setEditTTA(String TTA) {
		editTTA = TTA;
	}

	void setEditMod(String mod) {
		editMod = mod;
	}

	// Accessor
	String getCurrDate() {
		return currDate;
	}

	String getCurrIndex() {
		return currIndex;
	}

	String getNewTime() {
		return newTime;
	}

	String getNewTask() {
		return newTask;
	}

	boolean getCheck() {
		return check;
	}

	String getDestDate() {
		return destDate;
	}

	String getEditTTA() {
		return editTTA;
	}

	String getEditMod() {
		return editMod;
	}

	// Others
	public void processEditString() {
		String editStr[] = input.split(" ", 4);
		String date = IsValidDate.validateDate(editStr[0]);
		if (!date.equals("") && !date.equals("overdue")
				&& !date.equals("archives")) { // Check Date
			setDate(date);
			String index = (new IsValidIndex(date, editStr[1])).validateIndex();
			if (!index.equals("")) { // Check Index
				setIndex(index);
				if (editStr[2].equals("time")) { // Check Time
					setEditTTA("time");
					String time = IsValidTime.validateTime(editStr[3]); // Syntax
					if (!time.equals("")) {
						setEditMod(time);
						check = true;
					} else {
						WarningPopUp.infoBox("Invalid Time", "WARNING");
						check = false;
					}
				} else if (editStr[2].equals("task")) {
					setEditTTA("task");
					setEditMod(editStr[3]);
					check = true;
				} else if (editStr[2].equals("all")) { // Check Time
					setEditTTA("all");
					String[] change = editStr[3].split(" ", 2);
					String timeAll = IsValidTime.validateTime(change[0]);
					if (!timeAll.equals("")) {
						setEditMod(editStr[3]);
						check = true;
					} else {
						WarningPopUp.infoBox("Invalid Time", "WARNING");
						check = false;
					}
				} else {
					WarningPopUp
							.infoBox(
									"Please enter what you would like to modify\ntask/time/all",
									"ERROR!");
					check = false;
				}
			} else {
				// WarningPopUp.infoBox("Invalid Index", "WARNING");
				check = false;
			}
		} else {
			// WarningPopUp.infoBox("Invalid Date", "WARNING");
			check = false;
		}

	}

	public void processAddString() {
		String addString[] = input.split(" ", 3);
		String date = IsValidDate.validateDate(addString[0]);
		if (!date.equals("") && !date.equals("overdue")
				&& !date.equals("archives")) { // Check Date
			setDate(date);
			String time = IsValidTime.validateTime(addString[1]); // Syntax
			if (!time.equals("")) { // Check Time
				setTime(time);
				setTask(addString[2]);
				check = true;
			} else {
				WarningPopUp.infoBox("Invalid Time", "WARNING");
				check = false;
			}
		} else {
			// WarningPopUp.infoBox("Invalid Date", "WARNING");
			check = false;
		}

	}

	public void processCopyString() {
		String cpyString[] = input.split(" ", 3);
		String sourcedate = IsValidDate.validateDate(cpyString[0]);
		if (!sourcedate.equals("") && !sourcedate.equals("overdue")
				&& !sourcedate.equals("archives")) { // Check Date
			setDate(sourcedate);
			String index = (new IsValidIndex(sourcedate, cpyString[1]))
					.validateIndex();
			if (!index.equals("")) { // Check Index
				setIndex(index);
				String destdate = IsValidDate.validateDate(cpyString[2]);
				if (!destdate.equals("") && !destdate.equals("overdue")
						&& !destdate.equals("archives")) { // Check Date
					setDestDate(destdate);
					check = true;
				} else {
					// WarningPopUp.infoBox("Invalid Date", "WARNING");
					check = false;
				}
			} else {
				// WarningPopUp.infoBox("Invalid Index", "WARNING");
				check = false;
			}
		} else {
			// WarningPopUp.infoBox("Invalid Date", "WARNING");
			check = false;
		}

	}

	public void processDeleteString() {
		String delString[] = input.split(" ");
		String date = IsValidDate.validateDate(delString[0]);
		if (!date.equals("")) { // Check Date
			setDate(date);
			if (delString.length == 1) { // Check Empty File
				String index = "-1";
				check = (new IsValidIndex(date, index)).testEmptyFile();
				if (check) {
					setIndex(index);
				} else {
					WarningPopUp.infoBox("Cannot delete from empty file",
							"WARNING");
				}
			} else {
				String index = (new IsValidIndex(date, delString[1]))
						.validateIndex();
				if (!index.equals("")) {// Check Index
					setIndex(index);
					check = true;
				} else {
					// WarningPopUp.infoBox("Invalid Index", "WARNING");
					check = false;
				}
			}
		} else {
			// WarningPopUp.infoBox("Invalid Date", "WARNING");
			check = false;
		}
	}

	public void processDoneString() {
		String doneString[] = input.split(" ");
		String date = IsValidDate.validateDate(doneString[0]);
		if (!date.equals("") && !date.equals("overdue")
				&& !date.equals("archives")) { // Check Date
			setDate(date);
			if (doneString.length == 1) { // Check Empty File
				String index = "-1";
				check = (new IsValidIndex(date, index)).testEmptyFile();
				if (check) {
					setIndex(index);
				} else {
					WarningPopUp.infoBox("Cannot delete from empty file",
							"WARNING");
				}
			} else {
				String index = (new IsValidIndex(date, doneString[1]))
						.validateIndex();
				if (!index.equals("")) { // Check Index
					setIndex(index);
					check = true;
				} else {
					// WarningPopUp.infoBox("Invalid Index", "WARNING");
					check = false;
				}
			}
		} else {
			// WarningPopUp.infoBox("Invalid Date", "WARNING");
			check = false;
		}
	}

	public boolean isValidAgendaInput() {
		if (DateModifier.isValidDate(input)) {
			return true;
		} else {
			switch (input) {
			case "1":
				return true;
				
			case "2":
				return true;

			case "3":
				return true;

			case "4":
				return true;

			case "5":
				return true;

			case "6":
				return true;

			case "7":
				return true;

			case "today":
				return true;
	
			case "tomorrow":
				return true;
				
			case "off":
				return true;

			default:
				return false;
			}
		}
	}
	
	public boolean isValidViewInput() {
		if (DateModifier.isValidDate(input)) {
			return true;
		} else {
			switch (input) {

			case "today":
				return true;
	
			case "tomorrow":
				return true;
				
			case "next week":
				return true;
				
			case "next month":
				return true;

			default:
				return false;
			}
		}
	}
}
