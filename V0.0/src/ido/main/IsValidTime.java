package ido.main;
//@author A0114813N
public class IsValidTime {
	private static final String FLOATING_CHECK = "-";
	public static String getFormattedTime(int time) { // throws Exception{
		if (time < 10 && time >= 0) {
			return String.format("0%d00", time);
		} else if (time <= 24 && time >= 0) {
			return String.format("%d00", time);
		} else if (time >= 100 && time <= 2400) {
			if (time % 100 < 60 && time % 100 >= 0) {
				if (time >= 1000) {
					return String.format("%d", time);
				} else {
					return String.format("0%d", time);
				}
			}
		}
		return ""; // Added to return false result
		// throw new Exception("Invalid time.");
	}

	public static String validateTime(String time) { // throws Exception{
		// TODO Throw meaningful exception messages
		if (time.equals(FLOATING_CHECK)) {
			return FLOATING_CHECK;
		}
		String splitted_time[] = time.split(FLOATING_CHECK);
		boolean valid = true;
		if (splitted_time.length < 1 || splitted_time.length > 2) {
			valid = false;
		}
		if (time.length() > 0) {
			if (time.charAt(time.length() - 1) == '-') {
				valid = false;
			}
		}
		String reformatted_time = "";
		int old_t = -1; // 1 character shorter will allow it to be always be on
						// top
		for (int i = 0; i < splitted_time.length; i++) {
			if (splitted_time[i].length() > 4) {
				valid = false;
			}
			splitted_time[i] = getFormattedTime(Integer
					.valueOf(splitted_time[i]));

			// Added to break once an empty string is received
			if (splitted_time[i].equals("")) {
				break;
			}

			int t = Integer.valueOf(splitted_time[i]);
			if (old_t >= t) {
				valid = false;
			}
			old_t = t;
			reformatted_time += splitted_time[i];
			if (i < splitted_time.length - 1) {
				reformatted_time += FLOATING_CHECK;
			}
		}
		if (valid) {
			return reformatted_time;
		} else { // Added to return false result
			return "";
		}
		// throw new Exception("Invalid time.");
	}

}
