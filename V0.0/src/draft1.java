import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class draft1 extends Composite {

	protected static final String fileName = "test.txt";
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text txtIdo;
	private Text commandBox;
	private Text post1;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Button btnNewButton;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public draft1(Composite parent, int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(null);
		
		
		txtIdo = new Text(this, SWT.READ_ONLY);
		txtIdo.setBounds(0, 0, 112, 36);
		txtIdo.setFont(SWTResourceManager.getFont("Bauhaus 93", 24, SWT.NORMAL));
		txtIdo.setText("iDO++");
		toolkit.adapt(txtIdo, true, true);
		
		btnNewButton = new Button(this, SWT.FLAT | SWT.TOGGLE);
		btnNewButton.setForeground(SWTResourceManager.getColor(0, 0, 0));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					post1.setText(post1.getText()+"\n"+ commandBox.getText());
					commandBox.setText("");
				}
			
		});
		btnNewButton.setBounds(278, 8, 75, 25);
		toolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("Do!");
		
		commandBox = new Text(this, SWT.BORDER);
		commandBox.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		commandBox.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		commandBox.setBounds(118, 7, 235, 26);
		toolkit.adapt(commandBox, true, true);
		
		post1 = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		post1.setBounds(10, 55, 177, 119);
		post1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		post1.setForeground(SWTResourceManager.getColor(255, 127, 80));
		toolkit.adapt(post1, true, true);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(193, 55, 177, 119);
		toolkit.adapt(text_1, true, true);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(10, 180, 177, 119);
		toolkit.adapt(text_2, true, true);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(193, 180, 177, 119);
		toolkit.adapt(text_3, true, true);
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(10, 305, 177, 119);
		toolkit.adapt(text_4, true, true);
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(193, 305, 177, 119);
		toolkit.adapt(text_5, true, true);
		
		text_6 = new Text(this, SWT.BORDER);
		text_6.setBounds(376, 10, 177, 196);
		toolkit.adapt(text_6, true, true);
		
		text_7 = new Text(this, SWT.BORDER);
		text_7.setBounds(376, 212, 177, 212);
		toolkit.adapt(text_7, true, true);
		

	}
	
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
}
