package ido.main;

import java.util.List;

public class IsValidIndex {
	private String filename;
	private String index;

	IsValidIndex(String filename, String index) {
		this.filename = filename;
		this.index = index;
	}

	public String validateIndex() {
		List<String> data;
		if (filename.equals("-")) {
			data = (new FileAccessor("general.txt")).readContents();
		} else if (filename.equals("archives.txt")) {
			data = (new FileAccessor("archives.txt")).readContents();
		} else if (filename.equals("overdue.txt")) {
			data = (new FileAccessor("overdue.txt")).readContents();
		} else {
			data = (new FileAccessor(filename + ".txt")).readContents();
		}
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
		List<String> data;
		if (filename.equals("-")) {
			data = (new FileAccessor("general.txt")).readContents();
		} else if (filename.equals("archives.txt")) {
			data = (new FileAccessor("archives.txt")).readContents();
		} else if (filename.equals("overdue.txt")) {
			data = (new FileAccessor("overdue.txt")).readContents();
		} else {
			data = (new FileAccessor(filename + ".txt")).readContents();
		}
		if (data.size() == 0) {
			System.out.println("nothing to clear");
			return false;
		} else {
			return true;
		}
	}
}
