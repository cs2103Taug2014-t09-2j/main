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

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {

	private static JFrame frame;
	private JTextField commandBox;
	private String fileName = null;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.getFrame().setVisible(true);
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

		setFrame(new JFrame());
		getFrame().addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent arg0) {
				checkFilesExist();
			}
		});
		getFrame().setBounds(100, 100, 677, 730);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setBounds(30, 2, 149, 59);
		lblIdo.setForeground(new Color(0, 0, 0));
		lblIdo.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
		getFrame().getContentPane().add(lblIdo);
		// create padding inside the text area
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane);

		final JTextArea DateBox1_1 = new JTextArea();
		scrollPane.setViewportView(DateBox1_1);
		DateBox1_1.setWrapStyleWord(true);
		DateBox1_1.setEditable(false);
		DateBox1_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		DateBox1_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		DateBox1_1.setBackground(new Color(255, 215, 0));
		/*
		 * DateBox1.addMouseListener(new MouseAdapter() { public void
		 * mouseClicked(MouseEvent arg0) { DateBox1.setSize(250, 250);
		 * DateBox1.setFont(new Font("Rockwell", Font.PLAIN, 20)); }
		 * 
		 * @Override public void mouseExited(MouseEvent e) {
		 * DateBox1.setSize(123, 113); DateBox1.setFont(new Font("Rockwell",
		 * Font.PLAIN, 12)); } });
		 */
		DateBox1_1.setLineWrap(true);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(233, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane_1);

		final JTextArea date2_1 = new JTextArea();
		scrollPane_1.setViewportView(date2_1);
		date2_1.setWrapStyleWord(true);
		date2_1.setLineWrap(true);
		date2_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date2_1.setEditable(false);
		date2_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date2_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(436, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane_2);

		final JTextArea date3_1 = new JTextArea();
		scrollPane_2.setViewportView(date3_1);
		date3_1.setWrapStyleWord(true);
		date3_1.setLineWrap(true);
		date3_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date3_1.setEditable(false);
		date3_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date3_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(30, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_3);

		final JTextArea date4_1 = new JTextArea();
		scrollPane_3.setViewportView(date4_1);
		date4_1.setWrapStyleWord(true);
		date4_1.setLineWrap(true);
		date4_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date4_1.setEditable(false);
		date4_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date4_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(233, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_4);
		// Border border = BorderFactory.createLineBorder(Color.WHITE);

		final JTextArea date5_1 = new JTextArea();
		scrollPane_4.setViewportView(date5_1);
		date5_1.setWrapStyleWord(true);
		date5_1.setLineWrap(true);
		date5_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date5_1.setEditable(false);
		date5_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date5_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(436, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_5);

		final JTextArea date6_1 = new JTextArea();
		scrollPane_5.setViewportView(date6_1);
		date6_1.setWrapStyleWord(true);
		date6_1.setLineWrap(true);
		date6_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date6_1.setEditable(false);
		date6_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date6_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(30, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_6);

		final JTextArea date7_1 = new JTextArea();
		scrollPane_6.setViewportView(date7_1);
		date7_1.setWrapStyleWord(true);
		date7_1.setLineWrap(true);
		date7_1.setFont(new Font("Rockwell", Font.PLAIN, 12));
		date7_1.setEditable(false);
		date7_1.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		date7_1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(233, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_7);

		final JTextArea missingBox = new JTextArea();
		scrollPane_7.setViewportView(missingBox);
		missingBox.setWrapStyleWord(true);
		missingBox.setLineWrap(true);
		missingBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
		missingBox.setEditable(false);
		missingBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		missingBox.setBackground(new Color(255, 99, 71));

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(436, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_8);
		// missingBox.read(reader, this);

		final JTextArea generalTaskBox = new JTextArea();
		scrollPane_8.setViewportView(generalTaskBox);
		generalTaskBox.setWrapStyleWord(true);
		generalTaskBox.setText("General Tasks");
		generalTaskBox.setLineWrap(true);
		generalTaskBox.setFont(new Font("Rockwell", Font.PLAIN, 12));
		generalTaskBox.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		generalTaskBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 5, 5, 5)));
		generalTaskBox.setBackground(new Color(135, 206, 235));

		commandBox = new JTextField();
		
		commandBox.setBounds(181, 19, 455, 20);
		// prompt the user to type start
		commandBox.setText("Please type the command here!");
		commandBox.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if(commandBox.getText().equals("Please type the command here!")){
					commandBox.setText("");
				}
			}
		});
		

		// when the mouse clicks the command box, empty the command box
		commandBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				commandBox.setText("");
			}
		});

		commandBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//Create a parser object
				Parser parser = new Parser();
				try {
					parser.processInput(commandBox.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				commandBox.setText("");
				displayAll();
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

				String prevDateString = DateModifier.getPrevDate(DateModifier
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
				String currDateString = DateModifier.getCurrDate();
				for (int i = 1; i < 8; i++) {
					// display 9 task starting from today's date
					fileName = currDateString + ".txt";
					try {
						reader = new BufferedReader(new FileReader(fileName));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					displayDateTask(i, currDateString);
					// update the current date for the next loop
					currDateString = DateModifier.getNextDate(currDateString);
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
						DateBox1_1.read(reader, this);
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
						date2_1.read(reader, this);
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
						date3_1.read(reader, this);
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
						date4_1.read(reader, this);
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
						date5_1.read(reader, this);
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
						date6_1.read(reader, this);
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
						date7_1.read(reader, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		});
		getFrame().getContentPane().add(commandBox);
		commandBox.setColumns(10);

		// generalTaskBox.read(reader, this);

		/*
		 * Populate the task boxes with the data in the source files Cannot use
		 * displayAll() as it is outside the commandBox actionPerformed
		 */

		displayAllTasks(DateBox1_1, date2_1, date3_1, date4_1, date5_1,
				date6_1, date7_1, missingBox, generalTaskBox);

	}
	
	public static void minWindow(){
		getFrame().setBounds(0,0,200, 200);
	}
	
	public static void maxWindow(){
		getFrame().setBounds(100, 100, 677, 730);
	}
	
	public void checkFilesExist() {
		String currDateString = DateModifier.getCurrDate();
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
					e.printStackTrace();
				}
				writer.println(currDateString);
				writer.close();
			}
			currDateString = DateModifier.getNextDate(currDateString);
		}
	}

	public final void displayAllTasks(final JTextArea DateBox1_1,
			final JTextArea date2_1, final JTextArea date3_1,
			final JTextArea date4_1, final JTextArea date5_1,
			final JTextArea date6_1, final JTextArea date7_1,
			final JTextArea missingBox, final JTextArea generalTaskBox) {
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

		String prevDateString = DateModifier
				.getPrevDate(DateModifier.getCurrDate());
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
		String currDateString = DateModifier.getCurrDate();
		for (int i = 1; i < 8; i++) {
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
					DateBox1_1.read(reader, this);
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
					date2_1.read(reader, this);
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
					date3_1.read(reader, this);
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
					date4_1.read(reader, this);
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
					date5_1.read(reader, this);
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
					date6_1.read(reader, this);
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
					date7_1.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			// update the current date for the next loop
			currDateString = DateModifier.getNextDate(currDateString);
		}
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		GUI.frame = frame;
	}
}
