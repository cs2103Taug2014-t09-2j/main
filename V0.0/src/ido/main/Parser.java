package ido.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

	private static final String INVALID_COMMAND = "Invalid command!";
	private static final String WARNING_HEADING = "WARNING";
	private static final String ERROR_HEADING = "ERROR";
	private static final String INVALID_VIEW_COMMAND = "Invalid view command!";
	private static final String SEARCH_HEADING = "Search Result";
	private static final String ZOOM_HEADING = "Zoom Result";
	private static final String AGENDA_ERR_HEADING = "Invalid agenda input";
	private static final String ZOOM_ERR_HEADING = "Invalid zoom input";

	public static HistoryTrackerAllFiles history = new HistoryTrackerAllFiles();
	public static Archives arc = new Archives();
	private static Logger logger = Logger.getLogger("Parser");
	public static boolean testCmd = false;
	public static Verify verify = new Verify();

	enum CommandTypes {
		START, AGENDA, ADD, EDIT, DONE, INVALID, UNDO, REDO, ZOOM, SEARCH, COPY, MIN, MAX, HELP, DELETE, VIEW, EXIT
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
		case "exit":
			return CommandTypes.EXIT;
		default:
			return CommandTypes.INVALID;
		}

	}

	/*
	 * Process the user input obtained from commandBox in GUI 
	 * Pre-cond: input is a string 
	 * Post-cond: calls appropriate command depending on input
	 */

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
			CommandTypes command = determineCmd(inputArr[0]);
			switch (command) {
			case EXIT:
				arc.saveArchives();
				arc.formatArcTxtFile();
				System.exit(0);

			case UNDO:
				try {
					history.undo();
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e);
				}

				// Determine if previous command is done
				arc.executeCmd(-1);
				// update file whenever undo is called
				arc.saveArchives();
				if((GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())
						&&(!history.getUndoFileDate().equals("-"))&&(!history.getUndoFileDate().equals("overdue"))){					GUI.closeAgenda();
					processInput("agenda " + history.getUndoFileDate());
				}
				break;

			case REDO:
				try {
					history.redo();
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e);
				}

				// Determine if earlier command is done
				arc.executeCmd(1);
				// update file whenever redo is called
				arc.saveArchives();
				if((GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())
						&&(!history.getRedoFileDate().equals("-"))&&(!history.getRedoFileDate().equals("overdue"))){
					GUI.closeAgenda();
					processInput("agenda " + history.getRedoFileDate());
				}
				break;

			case MIN:
				GUI.minWindow();
				break;

			case MAX:
				GUI.maxWindow();
				break;

			case HELP:
				GUI.showHelp();
				break;

			default:
				WarningPopUp.infoBox(INVALID_COMMAND, WARNING_HEADING);
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
					WarningPopUp.infoBox(INVALID_VIEW_COMMAND, ERROR_HEADING);
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
					if((GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())){
						GUI.closeAgenda();
						processInput("agenda " + date);
					}
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
					System.out.println(date1);
					if((!theRest.equals("overdue"))&&(GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())
							&&(!date1.equals("-"))){
						processInput("agenda " + date1);
					}
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
					if((GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())){
						GUI.closeAgenda();
						processInput("agenda " + destdate);
					}
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
					System.out.println(delDate);
					if((!delDate.equals("overdue"))&&(GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())
							&&(!delDate.equals("-"))){
						GUI.closeAgenda();
						processInput("agenda " + delDate);
					}
				}
				break;

			case DONE:

				verify.setInput(theRest);
				verify.processDoneString();
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
					new CommandDone(doneDate, doneIndex).done();

					// Save current state
					history.recordUpdatedFile(doneDate);
					arc.saveArchives();
					if((!doneDate.equals("overdue"))&&(GUI.checkAgendaActive()!=null) && (GUI.checkAgendaActive().isVisible())
							&&(!doneDate.equals("-"))){
						processInput("agenda " + doneDate);
					}
				}
				break;

			case SEARCH:
				CommandSearch srch = new CommandSearch();
				String searchResult = srch.search(theRest);
				System.out.print(searchResult);

				WarningPopUp.infoBox(searchResult, SEARCH_HEADING);
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
					WarningPopUp.infoBox(AGENDA_ERR_HEADING, ERROR_HEADING);
				}
				break;

			case ZOOM:
				FileAccessor fileAccessor = new FileAccessor();
				if (theRest.length() > 1) {
					if ((theRest.length() == 6) && (!theRest.equals("general"))
							&& (!theRest.equals("overdue") && (!theRest.equals("archives")))) {
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
						WarningPopUp.infoBox(dateContentString, ZOOM_HEADING);
						break;
					} else {
						switch (theRest) {

						case "general":
							fileName = "general.txt";
							fileAccessor.setFileName(fileName);
							String dateContentString = fileAccessor
									.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;
							
						case "overdue":
							fileName = "overdue.txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						default:
							WarningPopUp.infoBox(ZOOM_ERR_HEADING,
									WARNING_HEADING);
							break;
						}
					}
				} else {
					// obtain the date to be zoomed in
					int dateToBeZoomed = Integer.valueOf(theRest);
					// check the date validity
					if (!((dateToBeZoomed > 0) && (dateToBeZoomed < 10))) {
						WarningPopUp.infoBox(ZOOM_ERR_HEADING, WARNING_HEADING);
					} else {
						switch (dateToBeZoomed) {
						case 1:
							fileName = DateModifier.getCurrDate() + ".txt";
							fileAccessor.setFileName(fileName);
							String dateContentString = fileAccessor
									.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						case 2:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 1) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						case 3:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 2) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;
						case 4:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 3) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						case 5:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 4) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						case 6:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 5) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						case 7:
							fileName = DateModifier.getParticularDate(
									DateModifier.getCurrDate(), 6) + ".txt";
							fileAccessor.setFileName(fileName);
							dateContentString = fileAccessor.readFileString();
							WarningPopUp.infoBox(dateContentString,
									ZOOM_HEADING);
							break;

						default:
							WarningPopUp.infoBox(ZOOM_ERR_HEADING,
									ERROR_HEADING);
							break;
						}
					}
				}
				break;

			// other input will be displayed as invalid input
			default:
				WarningPopUp.infoBox(ZOOM_ERR_HEADING, ERROR_HEADING);
				break;

			}

		}
		return startDateToBeDisplayed;
	}
}
