package org.eclipse.wb.swt;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	public static HistoryTrackerAllFiles history = new HistoryTrackerAllFiles();
	
	enum CommandTypes {
		START, ADD, EDIT, DONE, INVALID, UNDO, REDO, ZOOM, SEARCH, COPY
	};

	private static CommandTypes determineCmd(String str) {
		if (str.equals("start")) {
			return CommandTypes.START;
		} else if (str.equals("add")) {
			return CommandTypes.ADD;
		} else if (str.equals("done")) {
			return CommandTypes.DONE;
		} else if (str.equals("edit")) {
			return CommandTypes.EDIT;
		} else if (str.equals("undo")) {
			return CommandTypes.UNDO;
		} else if (str.equals("redo")) {
			return CommandTypes.REDO;
		} else if (str.equals("zoom")) {
			return CommandTypes.ZOOM;
		} else if (str.equals("search")) {
			return CommandTypes.SEARCH;
		} else if (str.equals("copy")) {
			return CommandTypes.COPY;
		} else {
			return CommandTypes.INVALID;
		}
	}

	public void processInput(String input) {
		
		String fileName=DateUpdate.getCurrDate();
		
		ReadFile readFile = new ReadFile(fileName);

		String inputArr[] = input.split(" ", 2);
		// take care of the one word input
		if (inputArr.length == 1) {
			switch (inputArr[0]) {
			case "exit":
				System.exit(0);
			case "undo":
				history.undo();
				break;
			case "redo":
				history.redo();
				break;

			default:
				WarningPopUp.infoBox("Invalid Input", "WARNING");
			}

		} else {
			CommandTypes command = determineCmd(inputArr[0]);
			/*
			 * take all the words in the input except the first word to be added
			 * to the file, depending on the command
			 */
			String theRest = inputArr[1].trim();

			switch (command) {
			case EDIT:
				String editString[] = theRest.split(" ", 4);
				String date = editString[0];
				String number = editString[1];
				String time = editString[2];
				String modification = editString[3];
				history.clear();
				history.checkBaseFile(date);
				try {
					(new CommandEdit(date, number, time, modification)).edit();
					// editTask(date, number, time, modification);
				} catch (IOException e) {
					e.printStackTrace();
				}
				history.recordUpdatedFile(date);
				break;

			case ADD:
				String addString[] = theRest.split(" ", 3);
				String date1 = IsValidDate.validateDate(addString[0]);
				String time1 = addString[1];
				String task = addString[2];
				history.clear();
				history.checkBaseFile(date1);
				try {
					(new CommandAdd(date1, time1, task)).addTask();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				history.recordUpdatedFile(date1);
				break;

			case COPY:
				String cpyString[] = theRest.split(" ", 3);
				String sourcedate = IsValidDate.validateDate(cpyString[0]);
				String index = cpyString[1];
				String destdate = IsValidDate.validateDate(cpyString[2]);
				history.clear();
				history.checkBaseFile(sourcedate);
				CommandCopy.copyTask(sourcedate, index, destdate);
				history.recordUpdatedFile(sourcedate);
				break;

			case DONE:
				history.clear();
				String[] doneString = theRest.split(" ");
				history.checkBaseFile(doneString[0]);
				(new CommandDone(theRest)).delete();
				history.recordUpdatedFile(doneString[0]);
				break;

			case SEARCH:
				CommandSearch srch = new CommandSearch(theRest);
				String searchResult = srch.search();
				System.out.print(searchResult);
				if (!searchResult.isEmpty()) {
					WarningPopUp.infoBox(searchResult, "Search Result");
				} else {
					WarningPopUp.infoBox("Not Found!", "Search Result");
				}
				break;

			case ZOOM:
				if (theRest.length() > 1) {

					switch (theRest) {

					case "general":
						fileName = "general.txt";
						readFile.setFileName(fileName);
						ArrayList<String> dateContent = readFile.readContents();
						String dateContentString = dateContent.toString();
						WarningPopUp.infoBox(dateContentString, "Zoom Result");
						break;
					case "missing":

						fileName = DateUpdate.getPrevDate(DateUpdate
								.getCurrDate()) + ".txt";
						readFile.setFileName(fileName);
						dateContent = readFile.readContents();
						dateContentString = dateContent.toString();
						WarningPopUp.infoBox(dateContentString, "Zoom Result");
						break;
					default:
						WarningPopUp.infoBox("Invalid Input!", "WARNING");
						break;
					}
				} else {
					// obtain the date to be zoomed in
					int dateToBeZoomed = Integer.valueOf(theRest);
					// check the date validity
					if (!((dateToBeZoomed > 0) && (dateToBeZoomed < 10))) {
						WarningPopUp.infoBox("Invalid Input!", "WARNING");
					} else {
						switch (dateToBeZoomed) {
						case 1:
							fileName = DateUpdate.getCurrDate() + ".txt";
							readFile.setFileName(fileName);
							ArrayList<String> dateContent = readFile
									.readContents();
							String dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 2:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 1)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 3:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 2)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;
						case 4:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 3)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 5:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 4)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 6:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 5)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 7:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 6)
									+ ".txt";
							readFile.setFileName(fileName);
							dateContent = readFile.readContents();
							dateContentString = dateContent.toString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						default:
							WarningPopUp.infoBox("Invalid Input!", "WARNING");
							break;
						}
					}
				}
				break;

			// other input will be displayed as invalid input
			default:
				WarningPopUp.infoBox("Invalid Input", "WARNING");
				break;

			}

		}
	}
}
