import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class JPanelTest extends JPanel {
	private JTextField commandBox;

	/**
	 * Create the panel.
	 */
	
	public static void main(String[] args){
	    Display display = new Display();
	    Shell shell = new Shell(display);
	    draft1 ido = new draft1(shell, SWT.NONE);
	    ido.pack();
	    shell.pack();
	    shell.open();
	    while(!shell.isDisposed()){
	        if(!display.readAndDispatch()) display.sleep();
	    }
	}
	
	public JPanelTest() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblIdo.setBounds(10, 0, 91, 42);
		add(lblIdo);
		
		commandBox = new JTextField();
		commandBox.addInputMethodListener(new InputMethodListener() {
			private JLabel post1;
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				post1.setText(post1.getText()+"\n"+ commandBox.getText());
				commandBox.setText("");
			}
		});
		commandBox.setBounds(90, 15, 174, 20);
		add(commandBox);
		commandBox.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(277, 13, 162, 151);
		add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(277, 176, 162, 173);
		add(textArea_1);
		
		JTextArea post1 = new JTextArea();
		post1.setEditable(false);
		post1.setWrapStyleWord(true);
		post1.setLineWrap(true);
		post1.setBackground(new Color(255, 102, 51));
		post1.setBounds(15, 46, 76, 97);
		add(post1);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBackground(Color.ORANGE);
		textArea_3.setForeground(Color.RED);
		textArea_3.setBounds(100, 46, 76, 97);
		add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(186, 46, 76, 97);
		add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(15, 152, 76, 97);
		add(textArea_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setBounds(100, 152, 76, 97);
		add(textArea_6);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setBounds(186, 152, 76, 97);
		add(textArea_7);
		
		JTextArea textArea_8 = new JTextArea();
		textArea_8.setBounds(15, 260, 247, 89);
		add(textArea_8);
			
	}
}
