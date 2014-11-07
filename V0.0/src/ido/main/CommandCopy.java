package ido.main;

import java.util.ArrayList;
//@author A0114813N
public class CommandCopy {
	private static final String FLOATING_CHECK = "-";
	private static final String GENERAL_FILE = "general.txt";
	private static final String TXT_EXTENSION = ".txt";
	private static final String ALL_DAY_TASK = "by-today";
	
	public static void copyTask(String source_date, String index,
			String destination_date) {
		
		ArrayList<String> files;
		if(source_date.equals(FLOATING_CHECK)){
			files = new FileAccessor(GENERAL_FILE).readContents();
		}else{
			files = new FileAccessor(source_date + TXT_EXTENSION).readContents();
		}
		try{
			String line = files.get(Integer.valueOf(index)-1);
			String[] splitter = line.split("]");
			String time = splitter[0].substring(1);
			if(time.equals(ALL_DAY_TASK)){
				time = FLOATING_CHECK;
			}
			String task = line.substring(splitter[0].length()+2); //']' and ' '
			new CommandAdd(destination_date, time, task).addTask();
		}
		catch (Exception e){
		}
	}
}
