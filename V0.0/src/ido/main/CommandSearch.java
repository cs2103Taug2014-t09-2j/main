package ido.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandSearch {
	private String keyword;
	
	private final static Logger LOGGER = Logger.getLogger(CommandSearch.class .getName());
	
	
	private static final String TEXT_EXTENSION = ".txt";

	private static final String SEARCH_RESULT = "%1$s %2$d. %3$s";
	private static final String NUM_RESULT = "Number of result(s): %1$d\n";
	private static final String LINE_FORMAT = "%1$s\n";
	private static final String NO_EXTENSION = "";
	public CommandSearch() {
	}
	
	//Mutator
	public void setKeyword(String newKey) {
		keyword = newKey.toLowerCase();
	}
	
	public String search(String key) {
		setKeyword(key);
		
		LOGGER.setLevel(Level.INFO);
		//The array to store the search result
		ArrayList<String> searchResult = new ArrayList<String>();
		String current = "";
		String fileName = "";
		String displayedFileName = "";
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File dir = new File(current);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
		    	fileName = child.getName();
		    	
		    	displayedFileName = fileName.replace(TEXT_EXTENSION, NO_EXTENSION);
		    	if (isDateFile(fileName)||fileName.equals("general.txt")) {
		    		ArrayList<String> taskList = new ArrayList<String>();
		    		taskList = (new FileAccessor(fileName)).readContents();
			    	for (int i=0; i<taskList.size(); i++) {
						if (taskList.get(i).toLowerCase().contains(keyword)) {
							String result = String.format(SEARCH_RESULT, displayedFileName, i+1, taskList.get(i));
							searchResult.add(result);
						}
					}
		    	}
		    }
		}
		// System.out.print(arrayListToString(searchResult));
		if (!searchResult.isEmpty()) {
			return arrayListToString(searchResult) + String.format(NUM_RESULT, searchResult.size());
		} else {
			return "Nothing found!";
		}
		
		
	}
	private String arrayListToString(ArrayList<String> arr) {
		String str = new String();
		for (int i=0; i<arr.size(); i++) {
			str = str + String.format(LINE_FORMAT, arr.get(i));
		}
		return str;
	}
	
	private boolean isDateFile(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		try {
			sdf.parse(fileName);
			return true;
		} catch (ParseException e) {
			return false;
			
		}
	}
}
