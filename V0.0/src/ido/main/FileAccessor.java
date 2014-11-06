package ido.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class FileAccessor {
	private String fileName;
	private ArrayList<String> currDateTask;

	private static final String CONTENT_TO_DISPLAY = "%1$d. %2$s\n";
	private static final String FILE_HEADING = "%1$s %2$s\n----------------------------------\n";
	private static final String ERROR_LOCATION = "ERROR";
	private static final String READ_ERROR = "Failure to read file %1$s!";
	private static final String WRITE_ERROR = "Failure to write file %1$s!";
	private static final String TEXT_EXTENSION = ".txt";
	private static final String NO_EXTENSION = "";
	private static final String LINE_FORMAT = "%1$s\n";
	private static final int NUM_LINES_SKIPPED = 2;

	// Constructor
	public FileAccessor(String fileName, ArrayList<String> currDateTask) {
		this.fileName = fileName;
		this.currDateTask = currDateTask;
	}

	public FileAccessor(String fileName) {
		this.fileName = fileName;
		currDateTask = new ArrayList<>();
	}

	public FileAccessor() {
		this.fileName = null;
		this.currDateTask = null;
	}

	// Mutator
	public void setFileName(String newFileName) {
		this.fileName = newFileName;
	}

	public ArrayList<String> readContents() {
		BufferedReader br = null;
		try {
			String curr;
			br = new BufferedReader(new FileReader(fileName));
			int _ignore = NUM_LINES_SKIPPED;
			while ((curr = br.readLine()) != null) {
				if (_ignore > 0) { // ignore first 2 lines
					_ignore -= 1;
					continue;
				}
				String lineContent = curr.split(" ", 2)[1];
				currDateTask.add(lineContent);
			}
			br.close();
		} catch (IOException ee) {
			WarningPopUp.infoBox(String.format(READ_ERROR, fileName),
					ERROR_LOCATION);
			ee.printStackTrace();
		}
		return currDateTask;
	}

	//@author A0113768Y
	String readFileString() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(String.format(LINE_FORMAT, line));
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public void writeContents() {
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Collections.sort(currDateTask);
			
			String _date = fileName.replace(TEXT_EXTENSION, NO_EXTENSION);
			
			String formattedDate = reformatDate(_date);
			String _dayOfWeek = DayModifier.getDayOfWeek(_date);
			
			bw.write(String.format(FILE_HEADING, _dayOfWeek, formattedDate));
			for (int i = 0; i < currDateTask.size(); i++) {
				bw.write(String.format(CONTENT_TO_DISPLAY, i + 1,
						currDateTask.get(i)));
			}
			bw.close();
		} catch (IOException ee) {
			WarningPopUp.infoBox(String.format(WRITE_ERROR, fileName),
					ERROR_LOCATION);
			ee.printStackTrace();
		}

	}
	
	//@author A0113768Y
		/*
		 * Checks the required files to be displayed by task boxes in GUI exist, 
		 * starting from startDate. If the required files do not exist, it creates the file
		 * Pre-cond: -
		 * Post-cond: Required files exist/created
		 */
	public void checkFilesExistCustom(String startDate) {
		String temp = fileName;
		for (int i = 0; i < 7; i++) {
			temp = startDate + ".txt";
			File file = new File(temp);
			if (!file.exists()) {
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(temp, "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String _dayOfWeek = DayModifier.getDayOfWeek(startDate);
				String formattedDate = reformatDate(startDate);
				writer.print(String.format(FILE_HEADING, _dayOfWeek, formattedDate));
				writer.close();
			}
			startDate = DateModifier.getNextDate(startDate);
		}
		
	}
	
	//@author A0113768Y
	/*
	 * Checks the required files to be displayed by task boxes in GUI exist, 
	 * starting from today's date. If the required files do not exist, it creates the file
	 * Pre-cond: -
	 * Post-cond: Required files exist/created
	 */
	public void checkFilesExist() {
		String currDateString = DateModifier.getCurrDate();

		// check the archive file
		fileName = "archives.txt";
		File ArchivesFile = new File(fileName);

		if (!ArchivesFile.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}		
			writer.print(String.format(FILE_HEADING, "", "Archives"));
			writer.close();
		}

		// check the general task file
		fileName = "general.txt";
		File GeneralFile = new File(fileName);

		if (!GeneralFile.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}		
			writer.print(String.format(FILE_HEADING, "", "General"));
			writer.close();
		}

		/*
		 * check overdue tasks file
		 */
		fileName = "overdue.txt";
		File Overdue = new File(fileName);

		if (!Overdue.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			writer.print(String.format(FILE_HEADING, "", "Overdue"));
			writer.close();
		}

		// check the normal dates
		for (int i = 1; i < 8; i++) {
			fileName = currDateString + ".txt";
			File file = new File(fileName);

			if (!file.exists()) {
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(fileName, "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String _dayOfWeek = DayModifier.getDayOfWeek(currDateString);
				String formattedDate = reformatDate(currDateString);
				writer.print(String.format(FILE_HEADING, _dayOfWeek, formattedDate));
				writer.close();
			}
			currDateString = DateModifier.getNextDate(currDateString);
		}

	}

	public String reformatDate(String date) {
		DateFormat dateFormat1 = new SimpleDateFormat("ddMMyy");
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
		String newDate = new String();
		if ((date.toLowerCase()).equals("overdue")||(date.toLowerCase()).equals("general")||(date.toLowerCase()).equals("archives")) {
			// Must return blank string as now the day and date is displayed
			newDate = "";
		} else {
			try {
				newDate = dateFormat2.format(dateFormat1.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return newDate;
	}

	//@author A0113768Y
	/*
	 * Reads the tasks in the fileName 
	 * Pre-cond: the fileName.txt file exists
	 * Post-cond: arrayList of tasks for the date for every hour is created
	 */
	public ArrayList<String> getTasksInADay() {
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			result.add("");
		}
		int hour1 = 0, hour2, duration;
		temp = this.readContents();
		for (int i = 0; i < temp.size(); i++) {
			if (Character.isDigit(temp.get(i).charAt(4)))
				hour1 = Integer.valueOf(temp.get(i).substring(1, 3));

			if (Character.isDigit(temp.get(i).charAt(9)))
				hour2 = Integer.valueOf(temp.get(i).substring(9, 10));
			else
				hour2 = hour1 + 1;
			duration = hour2 - hour1;
			for (int j = 0; j < duration; j++) {
				result.add(hour1, temp.get(i));
			}

		}

		return result;
	}
	
	/*
	 * Transfer the tasks in the fileName to agendaContainer in GUI 
	 * Pre-cond: the fileName.txt file exists
	 * Post-cond: agenda for the date is created
	 */
	public void createAgendaForTheDate() {
		int arrayListSize = this.readContents().size();
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<Boolean> emptySlots = new ArrayList<Boolean>();
		int startDayAgenda=0;
		for(int i=0;i<25;i++){
			emptySlots.add(true);
		}
		temp = this.readContents();
		int hour1 = 0, hour2, duration;
		for (int i = 0; i < arrayListSize; i++) {
			if (Character.isDigit(temp.get(i).charAt(1)))
				hour1 = Integer.valueOf(temp.get(i).substring(1, 3));
			
			if(i==0){
				startDayAgenda = hour1;
			}

			if (Character.isDigit(temp.get(i).charAt(6)))
				hour2 = Integer.valueOf(temp.get(i).substring(6, 8));
			else
				hour2 = hour1 + 1;
			duration = hour2 - hour1;
			for(int j=hour1;j<duration+hour1;j++){
				emptySlots.set(j, false);
			}
			
			GUI.addTaskToAgenda(this.readContents().get(i), hour1, duration);
		}
		for(int i=startDayAgenda;i<25;i++){
			if(emptySlots.get(i) == true)
				GUI.addTaskToAgenda("", i, 1);
		}

	}

}
