package org.eclipse.wb.swt;

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

public class CommandDoneTest {

	private static final String FILE = "211013";
	private static final String FILETXT = FILE + ".txt";

	@Test
	public void testDeleteAll() {
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

	@Test
	public void testDeleteSingle() {
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
}
