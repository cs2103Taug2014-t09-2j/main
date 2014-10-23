package org.eclipse.wb.swt;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandSearch {
	private String keyword;
	
	private final static Logger LOGGER = Logger.getLogger(CommandSearch.class .getName());
	
	private static final String FILENAME_GENERAL = "general";
	private static final String TEXT_EXTENSION = ".txt";
	private static final String SEARCH_RESULT_GENERAL = "General %1$d. %2$s";
	private static final String SEARCH_RESULT_DATE = "%1$s %2$d. %3$s";
	private static final String LINE_FORMAT = "%1$s\n";
	
	public CommandSearch() {
	}
	
	//Mutator
	public void setKeyword(String newKey) {
		keyword = newKey;
	}
	
	public String search(String key) {
		setKeyword(key);
		
		LOGGER.setLevel(Level.INFO);
		//The array to store the search result
		ArrayList<String> searchResult = new ArrayList<String>();
		
		try {
			searchResult.addAll(searchDate());
		} catch (FileNotFoundException e) {
			LOGGER.warning("FileNotFoundException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
			searchResult.addAll(searchGeneral());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			searchResult.addAll(searchPrevDate());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayListToString(searchResult);
	}
	
	public ArrayList<String> searchGeneral() throws FileNotFoundException{
		String fileName = FILENAME_GENERAL + TEXT_EXTENSION;
		ArrayList<String> generalTask = new ArrayList<String>(); //generalTask contains tasks in General box
		generalTask = (new FileAccessor(fileName)).readContents();
		
		ArrayList<String> searchResultGeneral = new ArrayList<String>();
		for (int i=0; i<generalTask.size(); i++) {
			if (generalTask.get(i).toLowerCase().contains(keyword)) {
				String result = String.format(SEARCH_RESULT_GENERAL, i+1, generalTask.get(i));
				searchResultGeneral.add(result);
			}
		}
		return searchResultGeneral;
	}
	
	public ArrayList<String> searchPrevDate() throws FileNotFoundException{
		String prevDateString = DateModifier.getPrevDate(DateModifier.getCurrDate()) + TEXT_EXTENSION;
		ArrayList<String> prevDateTask = new ArrayList<String>();
		prevDateTask = (new FileAccessor(prevDateString)).readContents();
		
		ArrayList<String> searchResultPrevDate = new ArrayList<String>();
		for (int i=0; i<prevDateTask.size(); i++) {
			if (prevDateTask.get(i).toLowerCase().contains(keyword)) {
				String result = String.format(SEARCH_RESULT_DATE, DateModifier.getPrevDate(DateModifier.getCurrDate()), i+1, prevDateTask.get(i));
				searchResultPrevDate.add(result);
			}
		}
		return searchResultPrevDate;
	}
	
	public ArrayList<String> searchDate() throws FileNotFoundException{
		ArrayList<String> searchResultDate = new ArrayList<String>();
		ArrayList<String> dateTask = new ArrayList<String>();
		String result = new String();
		
		String currDateString = DateModifier.getCurrDate();
		for (int i=0; i<7; i++) {
			if (!dateTask.isEmpty()) {
				dateTask.clear();
			}
			String fileName = currDateString + TEXT_EXTENSION;
			//read file content into an ArrayList
			dateTask = (new FileAccessor(fileName)).readContents();
			
			//Search the content of each file, add any match to searchResultDate arraylist
			for (int j=0; j<dateTask.size(); j++){
				if (dateTask.get(j).toLowerCase().contains(keyword)) {
					result = String.format(SEARCH_RESULT_DATE, currDateString, i+1, dateTask.get(j));
					searchResultDate.add(result);
				}
			}
			 currDateString = DateModifier.getNextDate(currDateString);
		 }
		 return searchResultDate;
	}
	
	private String arrayListToString(ArrayList<String> arr) {
		String str = new String();
		for (int i=0; i<arr.size(); i++) {
			str = str + String.format(LINE_FORMAT, arr.get(i));
		}
		return str;
	}
}
