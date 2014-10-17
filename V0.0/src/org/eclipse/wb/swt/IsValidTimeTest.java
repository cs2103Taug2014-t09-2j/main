/**
 * 
 */
package org.eclipse.wb.swt;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Antony
 *
 */
public class IsValidTimeTest {

	@Test
	public void test() {
		// String curdate = DateModifier.getCurrDate();
		try {
			assertEquals(IsValidTime.getFormattedTime(0), "0000");
			assertEquals(IsValidTime.getFormattedTime(1), "0100");
			assertEquals(IsValidTime.getFormattedTime(130), "0130");
			assertEquals(IsValidTime.getFormattedTime(14), "1400");
			assertEquals(IsValidTime.getFormattedTime(1245), "1245");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// passes test when exception is thrown
		boolean fail = false;
		try {
			IsValidTime.getFormattedTime(299);
			fail = true;
		} catch (Exception e) {}
		try {
			IsValidTime.getFormattedTime(180);
			fail = true;
		} catch (Exception e) {}
		try {
			IsValidTime.getFormattedTime(11111);
			fail = true;
		} catch (Exception e) {}
		try {
			IsValidTime.getFormattedTime(2500);
			fail = true;
		} catch (Exception e) {}
		try {
			IsValidTime.getFormattedTime(-1);
			fail = true;
		} catch (Exception e) {}
		if (fail) {
			fail("fails to fail");
		}
	}

}
