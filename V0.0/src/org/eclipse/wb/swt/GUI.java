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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		frame.setBounds(100, 100, 677, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{23, 198, 210, 191, 0};
		gridBagLayout.rowHeights = new int[]{53, 202, 203, 207, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
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
												date7, date8, date9, commandBox.getText());
									}
						
									public void processCommand(final JTextArea DateBox1,
											final JTextArea date2, final JTextArea date3,
											final JTextArea date4, final JTextArea date5,
											final JTextArea date6, final JTextArea date7,
											final JTextArea date8, final JTextArea date9, String input) {
										
										history.trackCmd(input);
										String inputArr[] = input.split(" ", 2);
										// take care of the one word input
										if (inputArr.length == 1) {
											switch (inputArr[0]) {
											case "exit":
												System.exit(0);
											case "undo":
												history.runHistoryUndo();
												commandBox.setText("");
												displayAll();
												break;
											case "redo":
												history.runHistoryRedo();
												commandBox.setText("");
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
											String recordDate[] = theRest.split(" ", 2);
											history.recordHistory(recordDate[0]);
											history.clearDateRALR();
											history.checkPrevPrevNcheckPrev();
																
											switch (command) {
											case EDIT:
												String editString[] = theRest.split(" ", 4);
												String date = editString[0];
												String number = editString[1];
												String time = editString[2];
												String modification = editString[3];
												try {
													(new CommandEdit(date, number, time, modification))
															.edit();
													// editTask(date, number, time, modification);
												} catch (IOException e) {
													e.printStackTrace();
												}
												commandBox.setText("");
												displayAll();
												break;
						
											case ADD:
												String addString[] = theRest.split(" ", 3);
												String date1 = addString[0];
												String time1 = addString[1];
												String task = addString[2];
												
												try {
													(new CommandAdd(date1, time1, task)).addTask();
													displayAll();
													// addTask(date1,time1,task);
												} catch (IOException e1) {
													e1.printStackTrace();
												}						
												commandBox.setText("");
												break;
						
											case DONE:
												if ((new IsValidDate(theRest).testValidDate())) {							
													try {
														(new CommandDone(theRest)).clearDateTaskAll();
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													}
													displayAll();
													commandBox.setText("");
												} else {
													String doneString[] = theRest.split(" ", 2);							
													(new CommandDone(doneString[0], doneString[1]))
															.clearDateTaskSpecific();							
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
								
										JLabel lblIdo = new JLabel("iDO++");
										lblIdo.setForeground(new Color(255, 215, 0));
										lblIdo.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
										GridBagConstraints gbc_lblIdo = new GridBagConstraints();
										gbc_lblIdo.fill = GridBagConstraints.VERTICAL;
										gbc_lblIdo.insets = new Insets(0, 0, 5, 5);
										gbc_lblIdo.gridx = 1;
										gbc_lblIdo.gridy = 0;
										frame.getContentPane().add(lblIdo, gbc_lblIdo);
								GridBagConstraints gbc_commandBox = new GridBagConstraints();
								gbc_commandBox.fill = GridBagConstraints.HORIZONTAL;
								gbc_commandBox.insets = new Insets(0, 0, 5, 0);
								gbc_commandBox.gridwidth = 2;
								gbc_commandBox.gridx = 2;
								gbc_commandBox.gridy = 0;
								frame.getContentPane().add(commandBox, gbc_commandBox);
								commandBox.setColumns(10);
		
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
				GridBagConstraints gbc_DateBox1 = new GridBagConstraints();
				gbc_DateBox1.fill = GridBagConstraints.BOTH;
				gbc_DateBox1.insets = new Insets(0, 0, 5, 5);
				gbc_DateBox1.gridx = 1;
				gbc_DateBox1.gridy = 1;
				frame.getContentPane().add(DateBox1, gbc_DateBox1);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Rockwell", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setWrapStyleWord(true);
		textArea_2.setLineWrap(true);
		textArea_2.setFont(new Font("Rockwell", Font.PLAIN, 12));
		textArea_2.setEditable(false);
		textArea_2.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_2.gridx = 3;
		gbc_textArea_2.gridy = 1;
		frame.getContentPane().add(textArea_2, gbc_textArea_2);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setWrapStyleWord(true);
		textArea_4.setLineWrap(true);
		textArea_4.setFont(new Font("Rockwell", Font.PLAIN, 12));
		textArea_4.setEditable(false);
		textArea_4.setBackground(new Color(255, 215, 0));
		GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_4.gridx = 1;
		gbc_textArea_4.gridy = 2;
		frame.getContentPane().add(textArea_4, gbc_textArea_4);
		Border border = BorderFactory.createLineBorder(Color.WHITE);
				
				JTextArea textArea_5 = new JTextArea();
				textArea_5.setWrapStyleWord(true);
				textArea_5.setLineWrap(true);
				textArea_5.setFont(new Font("Rockwell", Font.PLAIN, 12));
				textArea_5.setEditable(false);
				textArea_5.setBackground(new Color(255, 215, 0));
				GridBagConstraints gbc_textArea_5 = new GridBagConstraints();
				gbc_textArea_5.fill = GridBagConstraints.BOTH;
				gbc_textArea_5.insets = new Insets(0, 0, 5, 5);
				gbc_textArea_5.gridx = 2;
				gbc_textArea_5.gridy = 2;
				frame.getContentPane().add(textArea_5, gbc_textArea_5);
				
				JTextArea textArea_3 = new JTextArea();
				textArea_3.setWrapStyleWord(true);
				textArea_3.setLineWrap(true);
				textArea_3.setFont(new Font("Rockwell", Font.PLAIN, 12));
				textArea_3.setEditable(false);
				textArea_3.setBackground(new Color(255, 215, 0));
				GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
				gbc_textArea_3.fill = GridBagConstraints.BOTH;
				gbc_textArea_3.insets = new Insets(0, 0, 5, 0);
				gbc_textArea_3.gridx = 3;
				gbc_textArea_3.gridy = 2;
				frame.getContentPane().add(textArea_3, gbc_textArea_3);
						
						JTextArea textArea_1 = new JTextArea();
						textArea_1.setWrapStyleWord(true);
						textArea_1.setLineWrap(true);
						textArea_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
						textArea_1.setEditable(false);
						textArea_1.setBackground(new Color(255, 215, 0));
						GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
						gbc_textArea_1.fill = GridBagConstraints.BOTH;
						gbc_textArea_1.insets = new Insets(0, 0, 0, 5);
						gbc_textArea_1.gridx = 1;
						gbc_textArea_1.gridy = 3;
						frame.getContentPane().add(textArea_1, gbc_textArea_1);
				
						final JTextArea missingBox = new JTextArea();
						missingBox.setWrapStyleWord(true);
						missingBox.setLineWrap(true);
						missingBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
						missingBox.setEditable(false);
						missingBox.setBorder(BorderFactory.createCompoundBorder(border,
								BorderFactory.createEmptyBorder(5, 5, 5, 5)));
						missingBox.setBackground(new Color(255, 99, 71));
						GridBagConstraints gbc_missingBox = new GridBagConstraints();
						gbc_missingBox.fill = GridBagConstraints.BOTH;
						gbc_missingBox.insets = new Insets(0, 0, 0, 5);
						gbc_missingBox.gridx = 2;
						gbc_missingBox.gridy = 3;
						frame.getContentPane().add(missingBox, gbc_missingBox);
						missingBox.read(reader, this);
		
				final JTextArea generalTaskBox = new JTextArea();
				generalTaskBox.setWrapStyleWord(true);
				generalTaskBox.setText("General Tasks");
				generalTaskBox.setLineWrap(true);
				generalTaskBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
				generalTaskBox.setEditable(false);
				// Border border = BorderFactory.createLineBorder(Color.BLACK);
				generalTaskBox.setBorder(BorderFactory.createCompoundBorder(border,
						BorderFactory.createEmptyBorder(10, 5, 5, 5)));
				generalTaskBox.setBackground(new Color(135, 206, 235));
				GridBagConstraints gbc_generalTaskBox = new GridBagConstraints();
				gbc_generalTaskBox.fill = GridBagConstraints.BOTH;
				gbc_generalTaskBox.gridx = 3;
				gbc_generalTaskBox.gridy = 3;
				frame.getContentPane().add(generalTaskBox, gbc_generalTaskBox);
				generalTaskBox.read(reader, this);

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
