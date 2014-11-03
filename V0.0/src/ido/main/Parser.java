package ido.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	public static HistoryTrackerAllFiles history = new HistoryTrackerAllFiles();
	public static Archives arc = new Archives();
	private FileAccessor fileAccessor = new FileAccessor();
	private static Logger logger = Logger.getLogger("Parser");
	public static boolean testCmd = false;
	public static Verify verify = new Verify();

	// needs to be launch b4 this class
	public static OverDueTask ODTLaunch = new OverDueTask();

	enum CommandTypes {
		START, AGENDA, ADD, EDIT, DONE, INVALID, UNDO, REDO, ZOOM, SEARCH, COPY, MIN, MAX, HELP, DELETE, VIEW
	};

	private static CommandTypes determineCmd(String command) {

		switch (command) {
		case "start":
			return CommandTypes.START;
		case "add":
			return CommandTypes.ADD;
		case "done":
			return CommandTypes.DONE;
		case "edit":
			return CommandTypes.EDIT;
		case "undo":
			return CommandTypes.UNDO;
		case "redo":
			return CommandTypes.REDO;
		case "zoom":
			return CommandTypes.ZOOM;
		case "search":
			return CommandTypes.SEARCH;
		case "copy":
			return CommandTypes.COPY;
		case "min":
			return CommandTypes.MIN;
		case "max":
			return CommandTypes.MAX;
		case "help":
			return CommandTypes.HELP;
		case "delete":
			return CommandTypes.DELETE;
		case "view":
			return CommandTypes.VIEW;
		case "agenda":
			return CommandTypes.AGENDA;
		default:
			return CommandTypes.INVALID;
		}

	}

	public String processInput(String input) throws IOException {
		// set the default start date to be displayed
		String startDateToBeDisplayed = DateModifier.getCurrDate();
		String fileName = DateModifier.getCurrDate();

		// assuming the fileName is successfully updated
		assert fileName.isEmpty() == false;

		// log a message at INFO level
		logger.log(Level.INFO, "start processing");

		String inputArr[] = input.split(" ", 2);
		// take care of the one word input
		if (inputArr.length == 1) {
			switch (inputArr[0]) {
			case "exit":
				arc.saveArchives();
				System.exit(0);

			case "undo":
				try {
					history.undo();
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e);
				}

				// if prev command is done
				arc.executeCmd(-1);
				// update file whenever undo is called
				arc.saveArchives();
				break;

			case "redo":
				history.redo();

				// if prev command is done
				arc.executeCmd(1);
				// update file whenever redo is called
				arc.saveArchives();
				break;

			case "min":
				GUI.minWindow();
				break;

			case "max":
				GUI.maxWindow();
				break;

			case "help":
				GUI.showHelp();
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
			case VIEW:
				Verify vView = new Verify();
				vView.setInput(theRest);
				if(vView.isValidViewInput()){
					FileAccessor faView = new FileAccessor();
					faView.checkFilesExistCustom(DateModifier.convertInputViewToDate(theRest));
					startDateToBeDisplayed = DateModifier.convertInputViewToDate(theRest);
				}
				else{
					WarningPopUp.infoBox("Invalid view input", "ERROR");
				}
				break;

			case EDIT:
				// String editString[] = theRest.split(" ", 4);
				// String date = IsValidDate.validateDate(editString[0]);
				// String number = (new IsValidIndex(date,
				// editString[1])).validateIndex();
				// String time = editString[2];
				// String modification = editString[3];
				//
				// // testCmd date and index before executing the command
				// testCmd = !date.equals("") && !date.equals("overdue")
				// && !date.equals("archives");
				// testCmd = !number.equals("");

				verify.setInput(theRest);
				verify.processEditString();
				testCmd = verify.getCheck();
				String date = verify.getCurrDate();
				String number = verify.getCurrIndex();
				String time = verify.getEditTTA();
				String modification = verify.getEditMod();

				if (testCmd) {
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(date);
					try {
						(new CommandEdit()).edit(date, number, time,
								modification);
						// editTask(date, number, time, modification);
					} catch (IOException e) {
						// log a message at warning level
						logger.log(Level.WARNING, "processing error", e);
						e.printStackTrace();
					}
					history.recordUpdatedFile(date);
					arc.saveArchives();
				}
				break;

			case ADD:
				// String addString[] = theRest.split(" ", 3);
				// String date1 = IsValidDate.validateDate(addString[0]);
				// String time1 = addString[1];
				// String task = addString[2];
				//
				// // testCmd date and index before executing the command
				// testCmd = !date1.equals("") && !date1.equals("overdue")
				// && !date1.equals("archives");

				verify.setInput(theRest);
				verify.processAddString();
				testCmd = verify.getCheck();
				String date1 = verify.getCurrDate();
				String time1 = verify.getNewTime();
				String task = verify.getNewTask();

				if (testCmd) {
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					
					File file = new File(date1+".txt");
					if(!file.exists()){
						file.createNewFile();
					}
					
					history.checkBaseFile(date1);
					(new CommandAdd(date1, time1, task)).addTask();
					history.recordUpdatedFile(date1);
					arc.saveArchives();
				}
				break;

			case COPY:
				// String cpyString[] = theRest.split(" ", 3);
				// String sourcedate = IsValidDate.validateDate(cpyString[0]);
				// String index = (new IsValidIndex(sourcedate,
				// cpyString[1])).validateIndex();
				// String destdate = IsValidDate.validateDate(cpyString[2]);
				//
				// // testCmd date and index before executing the command
				// testCmd = !sourcedate.equals("")
				// && !sourcedate.equals("overdue")
				// && !sourcedate.equals("archives");
				// testCmd = !destdate.equals("") && !destdate.equals("overdue")
				// && !destdate.equals("archives");
				// testCmd = !index.equals("");

				verify.setInput(theRest);
				verify.processCopyString();
				testCmd = verify.getCheck();
				String sourcedate = verify.getCurrDate();
				String index = verify.getCurrIndex();
				String destdate = verify.getDestDate();

				if (testCmd) {
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(destdate);
					CommandCopy.copyTask(sourcedate, index, destdate);
					history.recordUpdatedFile(destdate);
					arc.saveArchives();
				}
				break;
			case DELETE:
				// String delString[] = theRest.split(" ");
				// String delDate = IsValidDate.validateDate(delString[0]);
				// String delIndex;
				//
				// if (delString.length == 1) {
				// delIndex = "-1";
				// // testCmd date and index before executing the command
				// testCmd = !delDate.equals("");
				// testCmd = (new IsValidIndex(delDate,
				// delIndex)).testEmptyFile();
				// } else {
				// delIndex = (new IsValidIndex(delDate,
				// delString[1])).validateIndex();
				// // testCmd date and index before executing the command
				// testCmd = !delDate.equals("");
				// testCmd = !delIndex.equals("");
				// }

				verify.setInput(theRest);
				verify.processDeleteString();
				testCmd = verify.getCheck();
				String delDate = verify.getCurrDate();
				String delIndex = verify.getCurrIndex();

				if (testCmd) {
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(delDate);
					new CommandDelete(delDate, delIndex).delete();
					history.recordUpdatedFile(delDate);
					arc.saveArchives();
				}
				break;

			case DONE:
				// String doneString[] = theRest.split(" ");
				// String doneDate = IsValidDate.validateDate(doneString[0]);
				// String doneIndex;
				//
				// if (doneString.length == 1) {
				// doneIndex = "-1";
				// // testCmd date and index before executing the command
				// testCmd = !doneDate.equals("") && !doneDate.equals("overdue")
				// && !doneDate.equals("archives");
				// testCmd = (new IsValidIndex(doneDate,
				// doneIndex)).testEmptyFile();
				// } else {
				// doneIndex = (new IsValidIndex(doneDate,
				// doneString[1])).validateIndex();
				// // testCmd date and index before executing the command
				// testCmd = !doneDate.equals("") && !doneDate.equals("overdue")
				// && !doneDate.equals("archives");
				// testCmd = !doneIndex.equals("");
				// }

				verify.setInput(theRest);
				verify.processDeleteString();
				testCmd = verify.getCheck();
				String doneDate = verify.getCurrDate();
				String doneIndex = verify.getCurrIndex();

				if (testCmd) {
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(doneDate);
					new CommandDone(doneDate, doneIndex).delete();
					history.recordUpdatedFile(doneDate);
					arc.saveArchives();
				}
				break;

			case SEARCH:
				CommandSearch srch = new CommandSearch();
				String searchResult = srch.search(theRest);
				System.out.print(searchResult);
				if (!searchResult.isEmpty()) {
					WarningPopUp.infoBox(searchResult, "Search Result");
				} else {
					WarningPopUp.infoBox("Not Found!", "Search Result");
				}
				break;

			case AGENDA:

				Verify verify = new Verify();
				verify.setInput(theRest);
				if (verify.isValidAgendaInput()) {
					if (theRest.equals("off")) {
						GUI.agendaOff();
					} else {
						if (GUI.checkAgendaActive() != null)
							GUI.closeAgenda();
						IsValidDate validDate = new IsValidDate(theRest);
						FileAccessor faAgenda = new FileAccessor(
								validDate.validateDate(theRest) + ".txt");
						faAgenda.checkFilesExistCustom(validDate
								.validateDate(theRest));
						GUI.addDetailedAgenda(validDate.validateDate(theRest));
						faAgenda.createAgendaForTheDate();
					}
				} else {
					WarningPopUp.infoBox("Invalid agenda input", "ERROR");
				}
				break;

			case ZOOM:
				FileAccessor fileAccessor = new FileAccessor();
				if (theRest.length() > 1) {
					if ((theRest.length() == 6) && (!theRest.equals("general"))
							&& (!theRest.equals("undone"))) {
						fileName = theRest + ".txt";
						File zoomFile = new File(fileName);
						if (!zoomFile.exists()) {
							PrintWriter writer = null;
							try {
								writer = new PrintWriter(fileName, "UTF-8");
							} catch (FileNotFoundException
									| UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							writer.println(theRest);
							writer.close();
						}
						fileAccessor.setFileName(fileName);
						String dateContentString = fileAccessor
								.readFileString();
						WarningPopUp.infoBox(dateContentString, "Zoom Result");
						break;
					} else {
						switch (theRest) {

						case "general":
							fileName = "general.txt";
							fileAccessor.setFileName(fileName);
							String dateContentString = fileAccessor
									.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;
						case "undone":

							fileName = DateModifier.getPrevDate(DateModifier
									.getCurrDate()) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;
						case "archives":
							fileName = "archives.txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						default:
							WarningPopUp.infoBox("Invalid Input!", "WARNING");
							break;
						}
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
							fileName = DateModifier.getCurrDate() + ".txt";
							fileAccessor.setFileName(fileName);
							String dateContentString = fileAccessor
									.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 2:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 1) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 3:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 2) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;
						case 4:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 3) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 5:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 4) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 6:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 5) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									"Zoom Result");
							break;

						case 7:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 6) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
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
		System.out.println("after all " + startDateToBeDisplayed);
		return startDateToBeDisplayed;
	}
}
