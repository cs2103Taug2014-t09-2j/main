package ido.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Benedict
 */

/*
 * Test delete commands for single task and all tasks
 */

public class CommandDoneTest {

	private static final String FILE = "211013";
	private static final String FILETXT = FILE + ".txt";

	// Valid date
	@Test
	public void testDeleteAll() {
		System.out.println("test 1");
		ArrayList<String> test = new ArrayList<>();
		test.add("1. 1");
		test.add("2. 1");
		test.add("3. 1");
		(new FileAccessor(FILETXT, test)).writeContents();
		(new CommandDone(FILE, "-1")).delete();
		ArrayList<String> l = (new FileAccessor(FILETXT).readContents());
		assertEquals(l.size(), 0);
		(new File(FILETXT)).delete();
	}
	
	// Invalid date
	@Test
	public void testDeleteAll2() {
		System.out.println("test 2");
		ArrayList<String> test = new ArrayList<>();
		test.add("1. 1");
		test.add("2. 1");
		test.add("3. 1");
		(new FileAccessor(FILETXT, test)).writeContents();
		(new CommandDone("123456", "-1")).delete();
		ArrayList<String> l = (new FileAccessor(FILETXT).readContents());
		assertEquals(l.size(), 3);
		(new File(FILETXT)).delete();
	}

	// Valid date
	@Test
	public void testDeleteSingle() {
		System.out.println("test 3");
		ArrayList<String> test = new ArrayList<>();
		test.add("1. 1");
		test.add("2. 1");
		test.add("3. 1");
		(new FileAccessor(FILETXT, test)).writeContents();
		(new CommandDone(FILE, "2")).delete();
		ArrayList<String> l = (new FileAccessor(FILETXT).readContents());
		assertEquals(l.size(), 2);
		(new File(FILETXT)).delete();
	}
	
	// Invalid position
	@Test
	public void testDeleteSingle2() {
		System.out.println("test 4");
		ArrayList<String> test = new ArrayList<>();
		test.add("1. 1");
		test.add("2. 1");
		test.add("3. 1");
		(new FileAccessor(FILETXT, test)).writeContents();
		(new CommandDone(FILE, "4")).delete();
		ArrayList<String> l = (new FileAccessor(FILETXT).readContents());
		assertEquals(l.size(), 3);
		(new File(FILETXT)).delete();
	}
}
