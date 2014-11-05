package ido.main;

import java.util.List;
//@author A0114813N
public class IsValidIndex {
	private String filename;
	private String index;

	IsValidIndex(String filename, String index) {
		if (filename.equals("-")) {
			this.filename = "general";
		}else if (filename.equals("archives")){
			this.filename = "archives";
		}else if (filename.equals("overdue")){
			this.filename = "overdue";
		}else{
		this.filename = filename;
		}
		this.index = index;
	}

	public String validateIndex() {
		List<String> data = (new FileAccessor(filename + ".txt")).readContents();
		
		int _index = -1; // will fail
		try {
			_index = Integer.parseInt(index);
		} catch (Exception e) {
			// do nothing
		}
		if (_index > 0 && _index <= data.size())
			return index;
		WarningPopUp.infoBox("Invalid Index!", "ERROR");
		return ""; // does not do shit
	}

	public boolean testEmptyFile() {
		List<String> data = (new FileAccessor(filename + ".txt")).readContents();
		
		if (data.size() == 0) {
			System.out.println("nothing to clear");
			return false;
		} else {
			return true;
		}
	}
}
