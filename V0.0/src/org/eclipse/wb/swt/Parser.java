package org.eclipse.wb.swt;

public class Parser {
	private String date;
	private int index;
	private String time;
	private String task;
	
	public void processInput(String input){
		String inputArr[] = input.split(" ", 2);
		// take care of the one word input
		if (inputArr.length == 1) {
			switch (inputArr[0]) {
			case "exit":
				System.exit(0);
			case "undo":
				HistoryTrackerAllFiles history = new HistoryTrackerAllFiles();
				history.undo();
				break;
			case "redo":
				history.redo();
				break;

			default:
				WarningPopUp.infoBox("Invalid Input", "WARNING");
				commandBox.setText("");
			}

		} else {
			CommandTypes command = determineCmd(inputArr[0]);

			/*
			 * take all the words in the input except the first word to
			 * be added to the file, depending on the command
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
					(new CommandEdit(date, number, time, modification))
							.edit();
					// editTask(date, number, time, modification);
				} catch (IOException e) {
					e.printStackTrace();
				}
				history.recordUpdatedFile(date);
				commandBox.setText("");
				displayAll();
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
					displayAll();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				history.recordUpdatedFile(date1);
				commandBox.setText("");
				break;

			case COPY:
				String cpyString[] = theRest.split(" ", 3);
				String sourcedate = IsValidDate.validateDate(cpyString[0]);
				String index = cpyString[1];
				String destdate = IsValidDate.validateDate(cpyString[2]);
				history.clear();
				history.checkBaseFile(sourcedate);
				CommandCopy.copyTask(sourcedate,index,destdate);
				displayAll();
				history.recordUpdatedFile(sourcedate);
				commandBox.setText("");
				break;
				
			case DONE:
				history.clear();
				String[] doneString = theRest.split(" ");
				history.checkBaseFile(doneString[0]);
				(new CommandDone(theRest)).delete();
				displayAll();
				history.recordUpdatedFile(doneString[0]);
				commandBox.setText("");

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
				commandBox.setText("");
				break;

			case ZOOM:
				if (theRest.length() > 1) {
					switch (theRest) {
					case "general":
						fileName = "general.txt";
						String dateContent = readFile(fileName);
						commandBox.setText("");
						WarningPopUp
								.infoBox(dateContent, "Zoom Result");
						break;
					case "missing":
						fileName = DateUpdate.getPrevDate(DateUpdate
								.getCurrDate()) + ".txt";
						dateContent = readFile(fileName);
						commandBox.setText("");
						WarningPopUp
								.infoBox(dateContent, "Zoom Result");
						break;
					default:
						WarningPopUp.infoBox("Invalid Input!",
								"WARNING");
						commandBox.setText("");
						break;
					}
				} else {
					// obtain the date to be zoomed in
					int dateToBeZoomed = Integer.valueOf(theRest);
					// check the date validity
					if (!((dateToBeZoomed > 0) && (dateToBeZoomed < 10))) {
						WarningPopUp.infoBox("Invalid Input!",
								"WARNING");
						commandBox.setText("");
					} else {
						switch (dateToBeZoomed) {
						case 1:
							fileName = DateUpdate.getCurrDate()
									+ ".txt";
							String dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 2:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 1)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 3:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 2)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 4:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 3)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 5:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 4)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 6:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 5)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						case 7:
							fileName = DateUpdate.getParticularDate(
									DateUpdate.getCurrDate(), 6)
									+ ".txt";
							dateContent = readFile(fileName);
							commandBox.setText("");
							WarningPopUp.infoBox(dateContent,
									"Zoom Result");
							break;
						default:
							WarningPopUp.infoBox("Invalid Input!",
									"WARNING");
							commandBox.setText("");
							break;
						}
					}
				}
				break;

			// other input will be displayed as invalid input
			default:
				WarningPopUp.infoBox("Invalid Input", "WARNING");
				commandBox.setText("");
				break;

			}
		
	}
}
