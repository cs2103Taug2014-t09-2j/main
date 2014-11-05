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

	// needs to be launch b4 this class if possible
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

				// Determine if previous command is done
				arc.executeCmd(-1);
				// update file whenever undo is called
				arc.saveArchives();
				break;

			case "redo":
				history.redo();

				// Determine if earlier command is done
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
				if (vView.isValidViewInput()) {
					FileAccessor faView = new FileAccessor();
					faView.checkFilesExistCustom(DateModifier
							.convertInputViewToDate(theRest));
					startDateToBeDisplayed = DateModifier
							.convertInputViewToDate(theRest);
				} else {
					WarningPopUp.infoBox("Invalid view input", "ERROR");
				}
				break;

			case EDIT:

				verify.setInput(theRest);
				verify.processEditString();
				testCmd = verify.getCheck();

				if (testCmd) {

					// Get required information
					String date = verify.getCurrDate();
					String number = verify.getCurrIndex();
					String time = verify.getEditTTA();
					String modification = verify.getEditMod();

					// Start recording of file and command
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(date);

					// Execute Command
					try {
						(new CommandEdit()).edit(date, number, time,
								modification);
						// editTask(date, number, time, modification);
					} catch (IOException e) {
						// log a message at warning level
						logger.log(Level.WARNING, "processing error", e);
						e.printStackTrace();
					}

					// Save current state
					history.recordUpdatedFile(date);
					arc.saveArchives();
				}
				break;

			case ADD:

				verify.setInput(theRest);
				verify.processAddString();
				testCmd = verify.getCheck();

				if (testCmd) {

					// Get required information
					String date1 = verify.getCurrDate();
					String time1 = verify.getNewTime();
					String task = verify.getNewTask();

					// Start recording of file and command
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					// Create new file before saving base file
					File file = new File(date1 + ".txt");
					if (!file.exists()) {
						file.createNewFile();
					}
					history.checkBaseFile(date1);

					// Execute Command
					(new CommandAdd(date1, time1, task)).addTask();

					// Save current state
					history.recordUpdatedFile(date1);
					arc.saveArchives();
				}
				break;

			case COPY:

				verify.setInput(theRest);
				verify.processCopyString();
				testCmd = verify.getCheck();

				if (testCmd) {

					// Get required information
					String sourcedate = verify.getCurrDate();
					String index = verify.getCurrIndex();
					String destdate = verify.getDestDate();

					// Start recording of file and command
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(destdate);

					// Execute Command
					CommandCopy.copyTask(sourcedate, index, destdate);

					// Save current state
					history.recordUpdatedFile(destdate);
					arc.saveArchives();
				}
				break;

			case DELETE:

				// Get required information
				verify.setInput(theRest);
				verify.processDeleteString();
				testCmd = verify.getCheck();

				if (testCmd) {

					// Get required information
					String delDate = verify.getCurrDate();
					String delIndex = verify.getCurrIndex();

					// Start recording of file and command
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(delDate);

					// Execute Command
					new CommandDelete(delDate, delIndex).delete();

					// Save current state
					history.recordUpdatedFile(delDate);
					arc.saveArchives();
				}
				break;

			case DONE:

				verify.setInput(theRest);
				verify.processDeleteString();
				testCmd = verify.getCheck();

				if (testCmd) {

					// Get required information
					String doneDate = verify.getCurrDate();
					String doneIndex = verify.getCurrIndex();
					
					// Start recording of file and command
					arc.clear();
					arc.cmdTAECD(inputArr[0]);
					history.clear();
					history.checkBaseFile(doneDate);
					
					// Execute Command
					new CommandDone(doneDate, doneIndex).delete();
					
					// Save current state
					history.recordUpdatedFile(doneDate);
					arc.saveArchives();
				}
				break;

			case SEARCH:
				CommandSearch srch = new CommandSearch();
				String searchResult = srch.search(theRest);
				System.out.print(searchResult);

				WarningPopUp.infoBox(searchResult, "Search Result");
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
