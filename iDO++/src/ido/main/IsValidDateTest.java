package ido.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//@author A0114813N
public class IsValidDateTest {

	@Test
	public void testExecutedCommands() {

		String test1today1 = IsValidDate.validateDate("today");
		String test1today2 = DateModifier.getCurrDate();
		assertEquals(test1today1, test1today2);

		String test2tomorrow1 = IsValidDate.validateDate("tomorrow");
		String test2tomorrow2 = DateModifier.getNextDate(DateModifier
				.getCurrDate());
		assertEquals(test2tomorrow1, test2tomorrow2);

		//Known issue, but code does not receive this input
		 boolean test3 = new
		 IsValidDate("1. today is 221014").testValidDate();
		 //Should return false
		 assertFalse(test3);

	}
}
