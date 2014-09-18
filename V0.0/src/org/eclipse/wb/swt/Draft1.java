package org.eclipse.wb.swt;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Draft1 {

	private JFrame frame;
	private JTextField commandBox;
	private String fileName = null;
	private JTextComponent date1;
	
	enum CommandTypes {
		START, EDIT, DONE, INVALID
	};

	private static CommandTypes determineCmd(String str) {
		if (str.equals("start")) {
			return CommandTypes.START;
		} else if (str.equals("done")) {
			return CommandTypes.DONE;
		} else if (str.equals("edit")) {
			return CommandTypes.EDIT;
		} else {
			return CommandTypes.INVALID;
		}
	}
	
	/**
	 * Launch the application.
	 */

	// Trial command to push
	// trial merge update
	// Trial For Made
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draft1 window = new Draft1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Draft1() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setForeground(Color.ORANGE);
		lblIdo.setFont(new Font("Bauhaus 93", Font.PLAIN, 24));
		lblIdo.setBounds(10, 11, 90, 36);
		frame.getContentPane().add(lblIdo);

		JLabel lblMissingThis = new JLabel("Missing This?");
		lblMissingThis.setBounds(416, 17, 97, 14);
		frame.getContentPane().add(lblMissingThis);

		JLabel lblGeneralTask = new JLabel("General Task");
		lblGeneralTask.setBounds(416, 205, 97, 14);
		frame.getContentPane().add(lblGeneralTask);

		JTextArea missingBox = new JTextArea();
		missingBox.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		missingBox.setEditable(false);
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		missingBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(20, 5, 5, 5)));
		missingBox.setBackground(new Color(255, 99, 71));
		missingBox.setBounds(406, 11, 168, 182);
		frame.getContentPane().add(missingBox);

		final JTextArea generalTaskBox = new JTextArea();
		generalTaskBox.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		generalTaskBox.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		generalTaskBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		generalTaskBox.setBackground(new Color(30, 144, 255));
		generalTaskBox.setBounds(406, 198, 168, 208);
		frame.getContentPane().add(generalTaskBox);

		final JTextArea date1 = new JTextArea();
		date1.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		// create padding inside the text area
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		date1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		date1.setEditable(false);
		date1.setBackground(new Color(255, 215, 0));
		date1.setLineWrap(true);
		date1.setBounds(20, 59, 123, 114);
		frame.getContentPane().add(date1);

		final JTextArea date2 = new JTextArea();
		date2.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date2.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		date2.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		date2.setBounds(147, 59, 123, 114);
		frame.getContentPane().add(date2);

		final JTextArea date3 = new JTextArea();
		date3.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date3.setEditable(false);
		date3.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		date3.setBackground(new Color(255, 215, 0));
		date3.setBounds(273, 59, 123, 114);
		frame.getContentPane().add(date3);

		final JTextArea date4 = new JTextArea();
		date4.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date4.setEditable(false);
		date4.setLineWrap(true);
		date4.setBounds(20, 176, 123, 114);
		frame.getContentPane().add(date4);
		date4.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date6 = new JTextArea();
		date6.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date6.setEditable(false);
		date6.setBounds(273, 176, 123, 114);
		frame.getContentPane().add(date6);
		date6.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date5 = new JTextArea();
		date5.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date5.setEditable(false);
		date5.setBackground(new Color(255, 215, 0));
		date5.setBounds(147, 176, 123, 114);
		frame.getContentPane().add(date5);
		date5.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date7 = new JTextArea();
		date7.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date7.setEditable(false);
		date7.setBackground(new Color(255, 215, 0));
		date7.setLineWrap(true);
		date7.setBounds(20, 292, 123, 114);
		frame.getContentPane().add(date7);
		date7.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date9 = new JTextArea();
		date9.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date9.setEditable(false);
		date9.setBackground(new Color(255, 215, 0));
		date9.setBounds(273, 292, 123, 114);
		frame.getContentPane().add(date9);
		date9.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date8 = new JTextArea();
		date8.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		date8.setEditable(false);
		date8.setBounds(147, 292, 123, 114);
		frame.getContentPane().add(date8);
		date8.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		commandBox = new JTextField();
		//prompt the user to type start
		commandBox.setText("Please type \"start\" to start!");

		// when the mouse clicks the command box, empty the command box
		commandBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				commandBox.setText("");
			}
		});

		commandBox.addActionListener(new ActionListener() {
			private BufferedWriter bw;

			/*
			 * Read in input and split input into 2parts: command and the rest
			 * 
			 * Return an array containing 2 String elements
			 */
			private String[] readInput() {
				String input = commandBox.getText();
				return input.split(" ", 2);
			}

			public void actionPerformed(ActionEvent arg0) {
				String arrString[] = readInput();
				
				//if the command is "start"
				//initialize the content of all the task boxes
				if(arrString[0].equals("start")){
					displayAll();
					commandBox.setText("");
				}
				CommandTypes command = determineCmd(arrString[0]);
				String theRest = arrString[1];

				switch (command) {
				/*case START:
					displayAll();
					// empty the command box
					commandBox.setText("");
					break;
				*/
				//format input: edit date number time modification 
				case EDIT:
					String arrString2[] = theRest.split(" ", 4);
					String date = arrString2[0];
					String number = arrString2[1];
					String time = arrString2[2];
					String modification = arrString2[3];
					try {
						editTask(date, number, time, modification);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case DONE:
					System.out.println(theRest);
					if (theRest.length() == 6) {
						try {
							doneTaskClear(theRest);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						String arrString3[] = theRest.split(" ", 2);
						String doneDate = arrString3[0];
						String doneNumber = arrString3[1];
						try {
							doneTaskSpecified(doneDate, doneNumber);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}

			private void doneTaskClear(String date) throws IOException {
				fileName = date + ".txt";
				clearFile(fileName);
				// then display in the UI
				displayAll();

				// set the command box to be empty
				commandBox.setText("");

			}

			private void doneTaskSpecified(String date, String number)
					throws IOException {
				fileName = date + ".txt";
				ArrayList<String> currDateTask;
				currDateTask = new ArrayList<>();

				// read the content of the file, put in the list
				BufferedReader br = null;
				try {
					String curr;
					br = new BufferedReader(new FileReader(fileName));
					while ((curr = br.readLine()) != null) {
						currDateTask.add(curr);
					}
				} catch (IOException ee) {
					ee.printStackTrace();
				}

				// delete the specific task in the date
				int position = Integer.parseInt(number);
				String toBeRemoved = currDateTask.get(position - 1);
				currDateTask.remove(toBeRemoved);

				// write to the file
				writeToFile(currDateTask, fileName);

				// then display in the UI
				displayAll();

				// set the command box to be empty
				commandBox.setText("");

			}

			// THIS IS STUB
			private void displayAll() {
				// TODO Auto-generated method stub

				BufferedReader reader = null;

				// obtain the current date
				DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
				Date currDate = new Date();
				String currDateString = dateFormat.format(currDate);
				// read the general task file
				fileName = "general.txt";
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					generalTaskBox.read(reader, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// loop to display all the date tasks
				// NOTE: i starts from 1
				for (int i = 1; i < 10; i++) {
					// display 9 task starting from today's date
					fileName = currDateString + ".txt";
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					displayDateTask(i, currDateString);
					// date1.read(reader, this);
					String dt = currDateString; // Start date
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
					Calendar c = Calendar.getInstance();
					try {
						c.setTime(sdf.parse(dt));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					c.add(Calendar.DATE, 1); // number of days to add
					dt = sdf.format(c.getTime()); // dt is now the new date
					currDateString = dt; // update the current date for the next
											// loop
				}
			}

			private void displayDateTask(int i, String myDate) {
				BufferedReader reader = null;
				// TODO Auto-generated method stub
				switch (i) {
				case 1:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date1.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 2:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date2.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 3:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date3.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 4:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date4.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 5:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date5.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 6:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date6.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 7:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date7.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 8:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date8.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case 9:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date9.read(reader, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				}
			}

			public void editTask(String date, String number, String time,
					String modification) throws IOException {

				fileName = date + ".txt";
				ArrayList<String> currDateTask;
				currDateTask = new ArrayList<>();

				// read the content of the file, put in the list
				BufferedReader br = null;
				try {
					String curr;
					br = new BufferedReader(new FileReader(fileName));
					while ((curr = br.readLine()) != null) {
						currDateTask.add(curr);
					}
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				// delete the specific task in the date
				int position = Integer.parseInt(number);
				String toBeRemoved = currDateTask.get(position - 1);
				currDateTask.remove(toBeRemoved);
				
				String modificationFinal = "(" + time + ") " + modification;

				// edit 121212 1 modification
				// insert the modification into the arrayList
				currDateTask.add(position - 1, modificationFinal);

				// clear the file
				clearFile(fileName);

				// write to the file
				writeToFile(currDateTask, fileName);

				// then display in the UI
				displayAll();

				// set the command box to be empty
				commandBox.setText("");

			}

			private void writeToFile(ArrayList<String> currDateTask,
					String fileName) throws IOException {
				// TODO Auto-generated method stub
				try {
					File file = new File(fileName);
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					for (int i = 0; i < currDateTask.size(); i++) {

						bw.write(currDateTask.get(i) + "\n");
					}

				} catch (IOException ee) {
					ee.printStackTrace();
				} finally {
					// close the writer so that it can write to the file
					bw.close();
				}
				System.out.println("DONE!");

			}

			private void clearFile(String fileName) {
				// TODO Auto-generated method stub
				try {
					File file = new File(fileName);
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("");
					bw.close();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		commandBox.setBounds(94, 21, 302, 25);
		frame.getContentPane().add(commandBox);
		commandBox.setColumns(10);
	}
}
