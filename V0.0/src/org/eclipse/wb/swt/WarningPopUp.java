package org.eclipse.wb.swt;
import javax.swing.JOptionPane;

public class WarningPopUp {
	
	public static void infoBox(String infoMessage, String location){
		JOptionPane.showMessageDialog(null, infoMessage,location, JOptionPane.INFORMATION_MESSAGE);
	}

}
