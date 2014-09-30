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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {

	private JFrame frame;
	private JTextField commandBox;
	private String fileName = null;

	// static CommandUndoRedo commandHistory = new CommandUndoRedo();
	static CommandHistory history = new CommandHistory();

	enum CommandTypes {
		START, ADD, EDIT, DONE, INVALID, UNDO, REDO, ZOOM
	};

	private static CommandTypes determineCmd(String str) {
		if (str.equals("start")) {
			return CommandTypes.START;
		} else if (str.equals("add")) {
			return CommandTypes.ADD;
		} else if (str.equals("done")) {
			return CommandTypes.DONE;
		} else if (str.equals("edit")) {
			return CommandTypes.EDIT;
		} else if (str.equals("undo")) {
			return CommandTypes.UNDO;
		} else if (str.equals("redo")) {
			return CommandTypes.REDO;
		} else if (str.equals("zoom")) {
			return CommandTypes.ZOOM;
		} else {
			return CommandTypes.INVALID;
		}
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent arg0) {

				String currDateString = DateUpdate.getCurrDate();
				for (int i = 1; i < 10; i++) {
					// check for each date whether the files exists or not
					fileName = currDateString + ".txt";
					File file = new File(fileName);

					// check if the today's file does not exist
					if (!file.exists()) {
						PrintWriter writer = null;
						try {
							writer = new PrintWriter(fileName, "UTF-8");
						} catch (FileNotFoundException
								| UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						writer.println(currDateString);
						writer.close();
					}
					currDateString = DateUpdate.getNextDate(currDateString);
				}
			}

		});
		frame.setBounds(100, 100, 600, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setForeground(Color.ORANGE);
		lblIdo.setFont(new Font("Helvetica", Font.BOLD, 24));
		lblIdo.setBounds(10, 11, 90, 36);
		frame.getContentPane().add(lblIdo);
		Border border = BorderFactory.createLineBorder(Color.WHITE);

		final JTextArea DateBox1 = new JTextArea();
		DateBox1.setWrapStyleWord(true);
		DateBox1.setEditable(false);
		DateBox1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		// create padding inside the text area
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		DateBox1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		DateBox1.setBackground(new Color(255, 215, 0));
		DateBox1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				DateBox1.setSize(250, 250);
				DateBox1.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				DateBox1.setSize(123, 113);
				DateBox1.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		DateBox1.setLineWrap(true);
		DateBox1.setBounds(20, 59, 123, 114);
		frame.getContentPane().add(DateBox1);

		final JTextArea date2 = new JTextArea();
		date2.setWrapStyleWord(true);
		date2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date2.setSize(250, 250);
				date2.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date2.setSize(123, 113);
				date2.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date2.setLineWrap(true);
		date2.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date2.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		date2.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		date2.setBounds(147, 59, 123, 114);
		frame.getContentPane().add(date2);

		final JTextArea date3 = new JTextArea();
		date3.setWrapStyleWord(true);
		date3.setLineWrap(true);
		date3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date3.setSize(250, 250);
				date3.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date3.setSize(123, 113);
				date3.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date3.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date3.setEditable(false);
		date3.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		date3.setBackground(new Color(255, 215, 0));
		date3.setBounds(273, 59, 123, 114);
		frame.getContentPane().add(date3);

		final JTextArea date4 = new JTextArea();
		date4.setWrapStyleWord(true);
		date4.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date4.setSize(250, 250);
				date4.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date4.setSize(123, 113);
				date4.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date4.setEditable(false);
		date4.setLineWrap(true);
		date4.setBounds(20, 176, 123, 114);
		frame.getContentPane().add(date4);
		date4.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date5 = new JTextArea();
		date5.setWrapStyleWord(true);
		date5.setLineWrap(true);
		date5.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date5.setEditable(false);
		date5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date5.setSize(250, 250);
				date5.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date5.setSize(123, 113);
				date5.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date5.setBackground(new Color(255, 215, 0));
		date5.setBounds(147, 176, 123, 114);
		frame.getContentPane().add(date5);
		date5.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date6 = new JTextArea();
		date6.setWrapStyleWord(true);
		date6.setLineWrap(true);
		date6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date6.setSize(250, 250);
				date6.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date6.setSize(123, 113);
				date6.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date6.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date6.setEditable(false);
		date6.setBounds(273, 176, 123, 114);
		frame.getContentPane().add(date6);
		date6.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date7 = new JTextArea();
		date7.setWrapStyleWord(true);
		date7.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date7.setEditable(false);
		date7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date7.setSize(250, 250);
				date7.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date7.setSize(123, 113);
				date7.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date7.setBackground(new Color(255, 215, 0));
		date7.setLineWrap(true);
		date7.setBounds(20, 292, 123, 114);
		frame.getContentPane().add(date7);
		date7.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date8 = new JTextArea();
		date8.setWrapStyleWord(true);
		date8.setLineWrap(true);
		date8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date8.setSize(250, 250);
				date8.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date8.setSize(123, 113);
				date8.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date8.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date8.setEditable(false);
		date8.setBounds(147, 292, 123, 114);
		frame.getContentPane().add(date8);
		date8.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea date9 = new JTextArea();
		date9.setWrapStyleWord(true);
		date9.setLineWrap(true);
		date9.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				date9.setSize(250, 250);
				date9.setFont(new Font("Rockwell", Font.PLAIN, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				date9.setSize(123, 113);
				date9.setFont(new Font("Rockwell", Font.PLAIN, 12));
			}
		});
		date9.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date9.setEditable(false);
		date9.setBackground(new Color(255, 215, 0));
		date9.setBounds(273, 292, 123, 114);
		frame.getContentPane().add(date9);
		date9.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		final JTextArea missingBox = new JTextArea();
		missingBox.setWrapStyleWord(true);
		missingBox.setLineWrap(true);
		missingBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
		missingBox.setEditable(false);
		missingBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		missingBox.setBackground(new Color(255, 99, 71));
		missingBox.setBounds(406, 21, 168, 172);
		frame.getContentPane().add(missingBox);

		final JTextArea generalTaskBox = new JTextArea();
		generalTaskBox.setWrapStyleWord(true);
		generalTaskBox.setText("General Tasks");
		generalTaskBox.setLineWrap(true);
		generalTaskBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
		generalTaskBox.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		generalTaskBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		generalTaskBox.setBackground(new Color(30, 144, 255));
		generalTaskBox.setBounds(406, 194, 168, 212);
		frame.getContentPane().add(generalTaskBox);

		commandBox = new JTextField();
		// prompt the user to type start
		commandBox.setText("Please type the command here!");

		// when the mouse clicks the command box, empty the command box
		commandBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				commandBox.setText("");
			}
		});

		commandBox.addActionListener(new ActionListener() {
			/*
			 * Read in input and split input into 2parts: command and the rest
			 */

			public void actionPerformed(ActionEvent arg0) {
				processCommand(DateBox1, date2, date3, date4, date5, date6,
						date7, date8, date9,commandBox.getText());
			}

			public void processCommand(final JTextArea DateBox1,
					final JTextArea date2, final JTextArea date3,
					final JTextArea date4, final JTextArea date5,
					final JTextArea date6, final JTextArea date7,
					final JTextArea date8, final JTextArea date9, String input) {
				String inputArr[] = input.split(" ", 2);
				// take care of the one word input
				if (inputArr.length == 1) {
					switch (inputArr[0]) {
					case "exit":
						System.exit(0);
					case "undo":
						// try {
						// commandHistory.runUndo();
						// } catch (IOException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						history.runHistoryUndo();
						commandBox.setText("");
						displayAll();
						break;
					case "redo":
						// commandHistory.runRedo();
						history.runHistoryRedo();
//						String repeatCmd = history.runHistoryRedo();
//						System.out.println(repeatCmd);
						commandBox.setText("");
//						if(repeatCmd.length()!=0){
//						processCommand(DateBox1, date2, date3, date4, date5, date6,
//								date7, date8, date9,repeatCmd);
//						}
						displayAll();
						break;

					default:
						WarningPopUp.infoBox("Invalid Input", "WARNING");
						commandBox.setText("");
					}

				} else {
					CommandTypes command = determineCmd(inputArr[0]);

					/*
					 * take all the words in the input except the first word to
					 * be added to the file, depending on the command
					 */
					String theRest = inputArr[1].trim();

					switch (command) {
					case EDIT:
						String editString[] = theRest.split(" ", 4);
						String date = editString[0];
						String number = editString[1];
						String time = editString[2];
						String modification = editString[3];
						history.recordHistory(date);
						history.recordInputHistory("edit " + theRest);
						// String oldInfo = (new CommandSearchCurrDate(date,
						// number)).searchString();
						try {
							(new CommandEdit(date, number, time, modification))
									.edit();
							// editTask(date, number, time, modification);
						} catch (IOException e) {
							e.printStackTrace();
						}
						// commandHistory.copyEditCommandToReverse(date,
						// number,time, modification, oldInfo);
						// commandHistory.storeOriginalCommand("edit", theRest);
						commandBox.setText("");
						displayAll();
						break;

					case ADD:
						String addString[] = theRest.split(" ", 3);
						String date1 = addString[0];
						String time1 = addString[1];
						String task = addString[2];
						history.recordHistory(date1);
						history.recordInputHistory("add " + theRest);
						try {
							(new CommandAdd(date1, time1, task)).addTask();
							displayAll();
							// addTask(date1,time1,task);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						// commandHistory.copyAddCommandToReverse(date1,
						// time1,task);
						// commandHistory.storeOriginalCommand("add", theRest);
						commandBox.setText("");
						break;

					case DONE:
						if ((new IsValidDate(theRest).testValidDate())) {
							history.recordHistory(theRest);
							history.recordInputHistory("done " + theRest);
							// commandHistory.copyDoneAllCommandToReverse(theRest);
							try {
								(new CommandDone(theRest)).clearDateTaskAll();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							// commandHistory.storeOriginalCommand("done",
							// theRest);
							displayAll();
							commandBox.setText("");
						} else {
							String doneString[] = theRest.split(" ", 2);
							history.recordHistory(doneString[0]);
							history.recordInputHistory("done " + theRest);
							// commandHistory.copyDoneSpecificCommandToReverse(
							// doneString[0], doneString[1]);
							(new CommandDone(doneString[0], doneString[1]))
									.clearDateTaskSpecific();
							// commandHistory .storeOriginalCommand("done",
							// theRest);
							displayAll();
							commandBox.setText("");
						}
						break;

					case ZOOM:
						// obtain the date to be zoomed in
						int dateToBeZoomed = Integer.valueOf(theRest);
						// check the date validity
						if (!((dateToBeZoomed > 0) && (dateToBeZoomed < 10))) {
							WarningPopUp.infoBox("Invalid Input!", "WARNING");
							commandBox.setText("");
						} else {
							switch (dateToBeZoomed) {
							case 1:
								DateBox1.setSize(250, 250);
								DateBox1.setFont(new Font("Rockwell",
										Font.PLAIN, 20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										DateBox1.setSize(123, 113);
										DateBox1.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 2:
								date2.setSize(250, 250);
								date2.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date2.setSize(123, 113);
										date2.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 3:
								date3.setSize(250, 250);
								date3.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date3.setSize(123, 113);
										date3.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 4:
								date4.setSize(250, 250);
								date4.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date4.setSize(123, 113);
										date4.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 5:
								date5.setSize(250, 250);
								date5.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date5.setSize(123, 113);
										date5.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 6:
								date6.setSize(250, 250);
								date6.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date6.setSize(123, 113);
										date6.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 7:
								date7.setSize(250, 250);
								date7.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date7.setSize(123, 113);
										date7.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 8:
								date8.setSize(250, 250);
								date8.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date8.setSize(123, 113);
										date8.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							case 9:
								date9.setSize(250, 250);
								date9.setFont(new Font("Rockwell", Font.PLAIN,
										20));
								commandBox.setText("");
								commandBox.addKeyListener(new KeyAdapter() {
									public void keyPressed(KeyEvent e) {
										date9.setSize(123, 113);
										date9.setFont(new Font("Rockwell",
												Font.PLAIN, 12));
									}
								});
								break;
							default:

								break;
							}

						}
						break;

					// other input will be displayed as invalid input
					default:
						WarningPopUp.infoBox("Invalid Input", "WARNING");
						commandBox.setText("");
						break;

					}
				}
			}

			/*
			 * This method will display the contents for each box in the UI
			 */
			public final void displayAll() {

				BufferedReader reader = null;
				fileName = "general.txt";
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					generalTaskBox.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}

				String prevDateString = DateUpdate.getPrevDate(DateUpdate
						.getCurrDate());
				// read the missing task file
				fileName = prevDateString + ".txt";
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					missingBox.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// loop to display all the date tasks
				// NOTE: i starts from 1
				String currDateString = DateUpdate.getCurrDate();
				for (int i = 1; i < 10; i++) {
					// display 9 task starting from today's date
					fileName = currDateString + ".txt";
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					displayDateTask(i, currDateString);
					// update the current date for the next loop
					currDateString = DateUpdate.getNextDate(currDateString);
				}

			}

			public void displayDateTask(int i, String myDate) {
				BufferedReader reader = null;
				switch (i) {
				case 1:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						DateBox1.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 2:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date2.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 3:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date3.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 4:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date4.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 5:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date5.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 6:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date6.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 7:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date7.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 8:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date8.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case 9:
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						date9.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				}
			}
		});
		commandBox.setBounds(102, 21, 294, 25);
		frame.getContentPane().add(commandBox);
		commandBox.setColumns(10);

		/*
		 * Populate the task boxes with the data in the source files
		 */

		BufferedReader reader = null;
		fileName = "general.txt";
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			generalTaskBox.read(reader, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String prevDateString = DateUpdate
				.getPrevDate(DateUpdate.getCurrDate());
		// read the missing task file
		fileName = prevDateString + ".txt";
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			missingBox.read(reader, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// loop to display all the date tasks
		// NOTE: i starts from 1
		String currDateString = DateUpdate.getCurrDate();
		for (int i = 1; i < 10; i++) {
			// display 9 task starting from today's date
			fileName = currDateString + ".txt";
			try {
				reader = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// BufferedReader reader = null;
			switch (i) {
			case 1:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					DateBox1.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date2.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date3.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 4:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date4.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 5:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date5.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 6:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date6.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 7:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date7.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 8:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date8.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 9:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					date9.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			}
			// update the current date for the next loop
			currDateString = DateUpdate.getNextDate(currDateString);
		}

	}
}
