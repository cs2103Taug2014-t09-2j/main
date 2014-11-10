//@author:A0113768Y

package ido.main;
import javax.swing.JOptionPane;

/*
 * A multipurpose class to display various message via pop up window
 * Pre-cond: -
 * Post-condition: displays a pop up window to display message
 */	
public class WarningPopUp {
	
	public static void infoBox(String infoMessage, String location){
		JOptionPane.showMessageDialog(null, infoMessage,location, JOptionPane.INFORMATION_MESSAGE);
	}

}
