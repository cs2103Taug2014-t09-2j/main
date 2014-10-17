package org.eclipse.wb.swt;

public class IsValidTime {
	
	public static String getFormattedTime(int time) throws Exception{
		if (time<10 && time>=0){
			return String.format("0%d00", time);
		}
		else if (time<=24 && time >=0){
			return String.format("%d00", time);
		}
		else if (time>=100&&time<=2400){
			if (time%100<60 && time%100>=0){
				if (time>=1000){
					return String.format("%d", time);
				}
				else{
					return String.format("0%d", time);
				}
			}
		}
		throw new Exception("Invalid time.");
	}

	public static String validateTime(String time) throws Exception{
		// TODO Throw meaningful exception messages
		if (time.equals("-")){
			return "-";
		}
		String splitted_time[] = time.split("-");
		boolean valid = true;
		if (splitted_time.length < 1 || splitted_time.length >2){
			valid = false;
		}
		if (time.length() > 0){
			if (time.charAt(time.length()-1) == '-'){
				valid = false;
			}
		}
		String reformatted_time = "";
		int old_t = -1; //1 character shorter will allow it to be always be on top
		for (int i=0;i<splitted_time.length;i++){
			splitted_time[i] = getFormattedTime(Integer.valueOf(splitted_time[i]));
			int t = Integer.valueOf(splitted_time[i]);
			if (old_t>=t){
				valid = false;
			}
			old_t = t;
			reformatted_time+=splitted_time[i];
			if (i<splitted_time.length-1){
				reformatted_time+="-";
			}
		}
		if (valid){
			return reformatted_time;
		}
		throw new Exception("Invalid time.");
	}
	
}
