import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// the file is created only when user types a filename else the program automatically exits.
// the file is saved to the disk only when the user exits the program.
// Antony Karukapally A0114813N
/*
 * TextBuddy is a command line program which can add, delete, display and clear command entered by the user
 * and is saved into a txt file as specified by the user.
 * Sample interaction
	c:>java  TextBuddy mytextfile.txt
	Welcome to TextBuddy. mytextfile.txt is ready for use
	command: add little brown fox
	added to mytextfile.txt: “little brown fox”
	command: display
	1. little brown fox
	command: add jumped over the moon
	added to mytextfile.txt: “jumped over the moon”
	command: display
	1. little brown fox
	2. jumped over the moon
	command: delete 2
	deleted from mytextfile.txt: “jumped over the moon”
	command: display
	1. little brown fox
	command: clear
	all content deleted from mytextfile.txt
	command: display
	mytextfile.txt is empty
	command: exit
	c:>
 */
public class TextBuddy {

	// User IO constants
	private static final String EXIT = "exit";
	private static final String DISPLAY = "display";
	private static final String CLEAR = "clear";
	private static final String ADD = "add";
	private static final String DELETE = "delete";
	private static final String COMMAND_PROMPT = "command: ";

	// Global content holders
	public ArrayList<String> content = new ArrayList<String>();
	public String filename;
	public File file;

	// String constants as specified by the sample interaction given above
	public static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	public static final String MESSAGE_CLEAR = "All content deleted from %s.";
	public static final String MESSAGE_ADD = "added to %s: \"%s\"";
	public static final String MESSAGE_EMPTY = "%s is empty.";
	public static final String MESSAGE_DELETE = "deleted from %s: \"%s\"";
	public static final String DISPLAY_FORMAT = "%d. %s";

	public TextBuddy(String inputFilename) {
		filename = inputFilename;
	}

	public static void main(String[] args) throws IOException {
		checkFileName(args);
		TextBuddy execute = new TextBuddy(args[0]);
		execute.run();
	}
//to check if a file name is provided. If not the program exits.
	public static void checkFileName(String[] args){
		if (args.length == 0) {
			System.out.println("No file name provided. Exiting.");
			System.exit(0);
		}
	}
	public void run() throws IOException {
		createFile();
		readFile();
		printWelcome();
		processUserCommands();
		exitProgram();
	}
//creates the file in the current directory, with the file name provided.
	public boolean createFile() throws IOException {
		boolean create = false;
		String currDir = new File("").getAbsolutePath(); // gets current
															// directory
		file = new File(currDir + "/" + filename);
		boolean fileExists = file.isFile();
		if (!fileExists) {
			try {
				create = file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return true;
		}
		return create;
	}

	public void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		content = new ArrayList<String>();
		while (reader.ready()) {
			content.add(reader.readLine());
		}
		reader.close();
	}

	public void printWelcome() {
		System.out.println(String.format(MESSAGE_WELCOME, filename));
		System.out.print(COMMAND_PROMPT);
	}
//processes commands until the user exits.
	public void processUserCommands() {
		Scanner sc = new Scanner(System.in);
		String command = "";
		while (sc.hasNext()) {
			command = sc.next().toLowerCase(); // to allow for any variation of command. eg. Add, add, AdD, etc
			switch (command) {
			case ADD:
				addContent(sc.nextLine().trim());
				break;
			case DISPLAY:
				displayContent();
				break;
			case DELETE:
				deleteContent(sc.nextInt() - 1);
				break;
			case CLEAR:
				clearContent();
				break;
			case EXIT:
				sc.close();
				return;
			}
			System.out.print(COMMAND_PROMPT);
		}
		sc.close();
	}

	public void exitProgram() {
		writetofile(file);
		System.exit(1);
	}
	//writes the content received from user into the file.
	public void writetofile(File file) {
		try {
			FileWriter rw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(rw);
			for (int i = 0; i < content.size(); i++) {
				bw.write(content.get(i) + "\r\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addContent(String input) {
		content.add(input);
		System.out.println(String.format(MESSAGE_ADD, filename, input));
	}

	public void displayContent() {
		if (content.size() == 0) {
			System.out.println(String.format(MESSAGE_EMPTY, filename));
			return;
		}
		int counter = 1;
		for (int i = 0; i < content.size(); i++) {
			System.out.println(String.format(DISPLAY_FORMAT, counter,
					content.get(i)));
			counter++;
		}
	}
//removes the line specified by the user.
	public void deleteContent(int index) {
		String deleted;
		deleted = content.get(index);
		content.remove(index);
		System.out.println(String.format(MESSAGE_DELETE, filename, deleted));
	}
//deletes the whole content of the file. The file becomes empty.
	public void clearContent() {
		content.clear();
		System.out.println(String.format(MESSAGE_CLEAR, filename));
	}
}
