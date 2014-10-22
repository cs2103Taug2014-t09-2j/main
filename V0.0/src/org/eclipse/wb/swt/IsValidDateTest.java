package org.eclipse.wb.swt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IsValidDateTest {

	@Test
	public void testExecutedCommands() {
				
		String test1today1 = IsValidDate.validateDate("today");
		String test1today2 = DateModifier.getCurrDate();
		assertEquals(test1today1,test1today2);
		
		String test2tomorrow1 = IsValidDate.validateDate("tomorrow");
		String test2tomorrow2 = DateModifier.getNextDate(DateModifier.getCurrDate());
		assertEquals(test2tomorrow1,test2tomorrow2);
		
		boolean test3 = new IsValidDate("1. today is 221014").testValidDate();
		//Should return false
		assertTrue(test3);
		
		boolean test4 = new IsValidDate("221014").testValidDate();
		assertFalse(test4);
	}
}
