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
import java.util.ArrayList;
import java.util.Collections;

public class FileAccessor {
	private String fileName;
	private ArrayList<String> currDateTask;
	
	private static final String CONTENT_TO_DISPLAY = "%1$d. %2$s\n";
	private static final String FILE_HEADING = "%1$s\n\n";
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
	public void setFileName(String newFileName){
		this.fileName=newFileName;
	}
	
	public ArrayList<String> readContents() {
		BufferedReader br = null;
		try {
			String curr;
			br = new BufferedReader(new FileReader(fileName));
			int _ignore = NUM_LINES_SKIPPED;
			while ((curr = br.readLine()) != null) {
				if (_ignore > 0){ //ignore first 2 lines
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
			bw.write(String.format(FILE_HEADING, _date));
			for (int i = 0; i < currDateTask.size(); i++) {
				bw.write(String.format(CONTENT_TO_DISPLAY,i+1,currDateTask.get(i)));
			}
			bw.close();
		} catch (IOException ee) {
			WarningPopUp.infoBox(String.format(WRITE_ERROR, fileName),
					ERROR_LOCATION);
			ee.printStackTrace();
		}

	}
	
	public void checkFilesExist() {
		String currDateString = DateModifier.getCurrDate();
		
		//check the archive file
		fileName = "archives.txt";
		File ArchivesFile = new File(fileName);

		if (!ArchivesFile.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException
					| UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			writer.println("Archives");
			writer.close();
		}
		
		
		//check the general task file
		fileName = "general.txt";
		File GeneralFile = new File(fileName);

		if (!GeneralFile.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException
					| UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			writer.println("General Tasks");
			writer.close();
		}
		
		/*
		 * check yesterday's date for undone task
		 */
		fileName = DateModifier.getPrevDate(DateModifier.getCurrDate())+ ".txt";
		File YesterdayFile = new File(fileName);

		if (!YesterdayFile.exists()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(fileName, "UTF-8");
			} catch (FileNotFoundException
					| UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			writer.println(DateModifier.getPrevDate(DateModifier.getCurrDate()));
			writer.close();
		}
		
		//check the normal dates 
		for (int i = 1; i < 8; i++) {
			fileName = currDateString + ".txt";
			File file = new File(fileName);

			if (!file.exists()) {
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(fileName, "UTF-8");
				} catch (FileNotFoundException
						| UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				writer.println(currDateString);
				writer.close();
			}
			currDateString = DateModifier.getNextDate(currDateString);
		}
		
	}
	
	

}
