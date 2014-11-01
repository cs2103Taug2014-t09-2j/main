package ido.main;

import java.util.List;

public class IsValidIndex {
	private String filename;
	private String index;
	
	IsValidIndex(String filename, String index){
		this.filename = filename;
		this.index = index;
	}
	
	String validateIndex(){
		List<String> data = (new FileAccessor(filename+".txt")).readContents();
		int _index = -1; //will fail
		try{
			_index = Integer.parseInt(index);
		} catch (Exception e){
			//do nothing
		}
		if (_index>0 && _index<=data.size())
			return index;
		WarningPopUp.infoBox("Invalid Index!", "ERROR");
		return ""; //does not do shit
	}
}
