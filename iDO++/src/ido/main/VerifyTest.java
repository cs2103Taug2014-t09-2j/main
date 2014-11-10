package ido.main;

import static org.junit.Assert.*;

import org.junit.Test;

//@author A0110679A

public class VerifyTest {

	public static Verify verify = new Verify();
	public static boolean valid;

	@Test
	public void testAdd() {
		verify.setInput("230114 - test");
		verify.processAddString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("230114 1-2 test");
		verify.processAddString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("230114 223-23 test");
		verify.processAddString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("230114 123456 test");
		verify.processAddString();
		valid = verify.getCheck();
		assertFalse(valid);

		verify.setInput("123456 - test");
		verify.processAddString();
		valid = verify.getCheck();
		assertFalse(valid);
	}

	@Test
	public void testEdit() {
		verify.setInput("1 1 time -");
		verify.processEditString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("1 1 task test");
		verify.processEditString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("1 1 all 2-3 test");
		verify.processEditString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("1 1 hello -");
		verify.processEditString();
		valid = verify.getCheck();
		assertFalse(valid);
	}

	@Test
	public void testDoneNDelete() {
		verify.setInput("1");
		verify.processDeleteString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("- 1");
		verify.processDoneString();
		valid = verify.getCheck();
		assertTrue(valid);
	}

	@Test
	public void testCopy() {
		verify.setInput("1 1 2");
		verify.processCopyString();
		valid = verify.getCheck();
		assertTrue(valid);

		verify.setInput("- 1 -");
		verify.processCopyString();
		valid = verify.getCheck();
		assertTrue(valid);

	}
}
