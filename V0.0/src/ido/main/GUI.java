package ido.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI {

	private static JFrame frame;
	private JTextField commandBox;
	private static JTextArea dateBox1;
	private static JTextArea dateBox2;
	private static JTextArea dateBox3;
	private static JTextArea dateBox4;
	private static JTextArea dateBox5;
	private static JTextArea dateBox6;
	private static JTextArea dateBox7;
	private static JTextArea overDueTasksBox;
	private static JTextArea generalTasksBox;
	private static JPanel agendaContainer;
	private static JPanel panel1;

	
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

		setFrame(new JFrame("iDO++"));
		getFrame().addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent arg0) {
				FileAccessor fa = new FileAccessor();
				
				fa.checkFilesExist();
			}
		});
		getFrame().setBounds(0, 0, 677, 730);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setForeground(new Color(255,255,255));
		getFrame().getContentPane().setLayout(null);
		getFrame().getContentPane().setBackground(new Color(255, 255, 255));

		

		JLabel lblIdo = new JLabel("iDO++");
		lblIdo.setBounds(30, 2, 149, 59);
		lblIdo.setForeground(new Color(0, 0, 0));
		lblIdo.setFont(new Font("Segoe UI", Font.PLAIN, 45));
		getFrame().getContentPane().add(lblIdo);
		// create padding inside the text area
		Border border = BorderFactory.createLineBorder(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(30, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane);

		dateBox1 = new JTextArea();
		scrollPane.setViewportView(dateBox1);
		dateBox1.setWrapStyleWord(true);
		dateBox1.setEditable(false);
		dateBox1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox1.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		dateBox1.setBackground(new Color(255, 215, 0));
		dateBox1.setLineWrap(true);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_1.setBounds(233, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane_1);

		dateBox2 = new JTextArea();
		scrollPane_1.setViewportView(dateBox2);
		dateBox2.setWrapStyleWord(true);
		dateBox2.setLineWrap(true);
		dateBox2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox2.setEditable(false);
		dateBox2.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox2.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_2.setBounds(436, 64, 200, 200);
		getFrame().getContentPane().add(scrollPane_2);

		dateBox3 = new JTextArea();
		scrollPane_2.setViewportView(dateBox3);
		dateBox3.setWrapStyleWord(true);
		dateBox3.setLineWrap(true);
		dateBox3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox3.setEditable(false);
		dateBox3.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox3.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_3.setBounds(30, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_3);

		dateBox4 = new JTextArea();
		scrollPane_3.setViewportView(dateBox4);
		dateBox4.setWrapStyleWord(true);
		dateBox4.setLineWrap(true);
		dateBox4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox4.setEditable(false);
		dateBox4.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox4.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_4.setBounds(233, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_4);
		// Border border = BorderFactory.createLineBorder(Color.WHITE);

		dateBox5 = new JTextArea();
		scrollPane_4.setViewportView(dateBox5);
		dateBox5.setWrapStyleWord(true);
		dateBox5.setLineWrap(true);
		dateBox5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox5.setEditable(false);
		dateBox5.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox5.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_5.setBounds(436, 266, 200, 200);
		getFrame().getContentPane().add(scrollPane_5);

		dateBox6 = new JTextArea();
		scrollPane_5.setViewportView(dateBox6);
		dateBox6.setWrapStyleWord(true);
		dateBox6.setLineWrap(true);
		dateBox6.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox6.setEditable(false);
		dateBox6.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox6.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_6.setBounds(30, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_6);

		dateBox7 = new JTextArea();
		scrollPane_6.setViewportView(dateBox7);
		dateBox7.setWrapStyleWord(true);
		dateBox7.setLineWrap(true);
		dateBox7.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		dateBox7.setEditable(false);
		dateBox7.setBackground(new Color(255, 215, 0));
		// create padding inside the text area
		dateBox7.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_7.setBounds(233, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_7);

		overDueTasksBox = new JTextArea();
		scrollPane_7.setViewportView(overDueTasksBox);
		overDueTasksBox.setWrapStyleWord(true);
		overDueTasksBox.setLineWrap(true);
		overDueTasksBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		overDueTasksBox.setEditable(false);
		overDueTasksBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		overDueTasksBox.setBackground(new Color(255, 99, 71));

		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBorder(BorderFactory.createEmptyBorder());
		scrollPane_8.setBounds(436, 469, 200, 200);
		getFrame().getContentPane().add(scrollPane_8);
		// missingBox.read(reader, this);

		generalTasksBox = new JTextArea();
		scrollPane_8.setViewportView(generalTasksBox);
		generalTasksBox.setWrapStyleWord(true);
		generalTasksBox.setText("General Tasks");
		generalTasksBox.setLineWrap(true);
		generalTasksBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		generalTasksBox.setEditable(false);
		// Border border = BorderFactory.createLineBorder(Color.BLACK);
		generalTasksBox.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		generalTasksBox.setBackground(new Color(135, 206, 235));

		commandBox = new JTextField();
		commandBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
					e.printStackTrace();
				}
				commandBox.setText("");
				displayAllTasks();
			}
		});
		getFrame().getContentPane().add(commandBox);
		commandBox.setColumns(10);

		/*
		 * Populate the task boxes with the data in the source files Cannot use
		 * displayAll() as it is outside the commandBox actionPerformed
		 */
		
		FileAccessor fa = new FileAccessor();
		
		fa.checkFilesExist();

		displayAllTasks();

	}
	
	public static void minWindow(){
		getFrame().setBounds(0,0,450, 306);
	}
	
	public static void maxWindow(){
		getFrame().setBounds(0, 0, 677, 730);
	}	

	public final void displayAllTasks() {
		BufferedReader reader = null;
		fileName = "general.txt";
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			try {
				Files.createFile(Paths.get(fileName));
				reader = new BufferedReader(new FileReader(fileName));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			generalTasksBox.read(reader, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read the overdue tasks file
		fileName = "overdue.txt";
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			overDueTasksBox.read(reader, this);
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
				try {
					Files.createFile(Paths.get(fileName));
					reader = new BufferedReader(new FileReader(fileName));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			// BufferedReader reader = null;
			switch (i) {
			case 1:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox1.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox2.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox3.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 4:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox4.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 5:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox5.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 6:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox6.read(reader, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 7:
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					try {
						Files.createFile(Paths.get(fileName));
						reader = new BufferedReader(new FileReader(fileName));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				try {
					dateBox7.read(reader, this);
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
	
	public static void setFrameSize(int width, int height){
		getFrame().setBounds(0, 0, width, height);
	}
	
	public static void addDetailedAgenda(String dateToBeDisplayed){
		getFrame().setBounds(0, 0, 1080, 730);
		agendaContainer = new JPanel();
		agendaContainer.setBounds(645, 20, 400, 650);
		agendaContainer.setBackground(new Color(255, 215, 0));
		agendaContainer.setLayout(new BoxLayout(agendaContainer,BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		agendaContainer.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		JLabel lblIdo = new JLabel(dateToBeDisplayed);
		//lblIdo.setBounds(5, 2, 10, 60);
		lblIdo.setForeground(new Color(0, 0, 0));
		lblIdo.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		agendaContainer.add(lblIdo);
		
		panel1 = new JPanel(new GridBagLayout());
		panel1.setBackground((new Color(255, 215, 0)));
		panel1.setBounds(5, 2, 600, 60);
		//panel1.setLayout(new GridLayout(24,1));
		agendaContainer.add(panel1);
		getFrame().getContentPane().add(agendaContainer);
	}
	
	public static void addTaskToAgenda(String task,int startTime,int duration){
		JButton agendaContent = new JButton(task);
		//agendaContent.setAlignmentX(11);
		//int agendaStart = 20+startTime*50;
		//agendaContent.setBounds(10, agendaStart, 160, duration*25);
		if(task.isEmpty()){
		agendaContent.setBackground(new Color(250, 164, 98));
		agendaContent.setOpaque(false);
		agendaContent.setBorderPainted(false);
		}
		else{
			agendaContent.setBackground(new Color(255, 99, 71));
			agendaContent.setBorderPainted(false);

		}
		if(!agendaContent.getText().isEmpty())
			agendaContent.setOpaque(true);
		agendaContent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints c = new GridBagConstraints();
		System.out.println("grid y "+startTime);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridy = startTime;
		c.ipady = duration*10;      //make this component tall
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 100;
		c.insets = new Insets(2,0,0,0);  
		c.gridx = 0;
		
		panel1.add(agendaContent,c);
	}
	
	public static void showHelp() throws IOException {
		FileAccessor fa = new FileAccessor();
		fa.setFileName("help.txt");
		String helpString = fa.readFileString();
		WarningPopUp.infoBox(helpString, "COMMANDS");
	}
}
