package org.eclipse.wb.swt;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CommandSearch {
	private String keyword;
	
	public CommandSearch(String key) {
		keyword = key;
	}
	
	public String search() {
		//The array to store the search result
		ArrayList<String> searchResult = new ArrayList<String>();
		
		try {
			searchResult.addAll(searchDate());
		} catch (FileNotFoundException e) {
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
	
	private ArrayList<String> searchGeneral() throws FileNotFoundException{
		String fileName = "general.txt";
		ArrayList<String> generalTask = new ArrayList<String>(); //generalTask contains tasks in General box
		generalTask = (new FileAccessor(fileName)).readContents();
		
		ArrayList<String> searchResultGeneral = new ArrayList<String>();
		for (int i=0; i<generalTask.size(); i++) {
			if (generalTask.get(i).toLowerCase().contains(keyword)) {
				String result = "General " + Integer.toString(i+1) + ". " + generalTask.get(i);
				searchResultGeneral.add(result);
			}
		}
		return searchResultGeneral;
	}
	
	private ArrayList<String> searchPrevDate() throws FileNotFoundException{
		String prevDateString = DateModifier.getPrevDate(DateModifier.getCurrDate());
		ArrayList<String> prevDateTask = new ArrayList<String>(); //missingTask contains tasks in Missing box
		prevDateTask = (new FileAccessor(prevDateString)).readContents();
		
		ArrayList<String> searchResultPrevDate = new ArrayList<String>();
		for (int i=0; i<prevDateTask.size(); i++) {
			if (prevDateTask.get(i).toLowerCase().contains(keyword)) {
				String result = prevDateString + " " + Integer.toString(i+1) + ". " + prevDateTask.get(i);
				searchResultPrevDate.add(result);
			}
		}
		return searchResultPrevDate;
	}
	
	private ArrayList<String> searchDate() throws FileNotFoundException{
		ArrayList<String> searchResultDate = new ArrayList<String>();
		ArrayList<String> dateTask = new ArrayList<String>();
		String result = new String();
		
		String currDateString = DateModifier.getCurrDate();
		for (int i=0; i<7; i++) {
			if (!dateTask.isEmpty()) {
				dateTask.clear();
			}
			String fileName = currDateString + ".txt";
			//read file content into an ArrayList
			dateTask = (new FileAccessor(fileName)).readContents();
			
			//Search the content of each file, add any match to searchResultDate arraylist
			for (int j=0; j<dateTask.size(); j++){
				if (dateTask.get(j).toLowerCase().contains(keyword)) {
					result = currDateString + " " + Integer.toString(j+1) + ". " + dateTask.get(j);
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
			str = str + arr.get(i) + "\n";
		}
		return str;
	}
}
