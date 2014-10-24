package ido.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Ngoc Anh
 */
public class CommandEditTest {
	
	@Test
	public void testEditTask() {
		System.out.println("Test editTask:");
		CommandEdit cmdEdit = new CommandEdit();
		cmdEdit.setDate("251014");
		cmdEdit.setIndex("2");
		cmdEdit.setMod("have lunch with dad");
		cmdEdit.setSpec("task");
		String oldTask = "[1000-1200] have lunch with friends";
		String newTask = cmdEdit.editTask(oldTask);
		assertEquals("[1000-1200] have lunch with dad", newTask);
	}
	@Test
	public void testEditTime() throws Exception {
		System.out.println("Test editTime:");
		CommandEdit cmdEdit = new CommandEdit();
		cmdEdit.setDate("251014");
		cmdEdit.setIndex("2");
		cmdEdit.setMod("1300-1400");
		cmdEdit.setSpec("time");
		String oldTask = "[1000-1200] have lunch with friends";
		String newTask = cmdEdit.editTime(oldTask);
		assertEquals("[1300-1400] have lunch with friends", newTask);
	}
	@Test
	public void testEditAll() {
		System.out.println("Test editAll:");
		CommandEdit cmdEdit = new CommandEdit();
		cmdEdit.setDate("251014");
		cmdEdit.setIndex("2");
		cmdEdit.setMod("1300-1400 have lunch with dad");
		cmdEdit.setSpec("all");
		String oldTask = "[1000-1200] have lunch with friends";
		String newTask = cmdEdit.editAll(oldTask);
		System.out.println(oldTask);
		assertEquals("[1300-1400] have lunch with dad", newTask);
	}
	
}
