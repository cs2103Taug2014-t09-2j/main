/**
 * 
 */
package ido.main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Antony
 *
 */
public class IsValidTimeTest {

	@Test
	public void getFormattedTime_test() {
		
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
			IsValidTime.getFormattedTime(2500); //boundary
			fail = true;
		} catch (Exception e) {}
		try {
			IsValidTime.getFormattedTime(-1); // boundary
			fail = true;
		} catch (Exception e) {}
		if (fail) {
			fail("fails to fail");
		}
	}

	@Test
	public void validateTime_test() {
		try {
			assertEquals(IsValidTime.validateTime("0"), "0000");
			assertEquals(IsValidTime.validateTime("-"), "-");
			assertEquals(IsValidTime.validateTime("8-10"), "0800-1000");
			assertEquals(IsValidTime.validateTime("830-945"), "0830-0945");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean fail = false;
		try {
			IsValidTime.validateTime("");
			fail = true;
		}
		catch (Exception e) {}
		try {
			IsValidTime.validateTime("000000000001"); //value testing
			fail = true;
		}
		catch (Exception e) {}
		try {
			IsValidTime.validateTime("299");
			fail = true;
		}
		catch (Exception e) {}
		if (fail) {
			fail("fails to fail");
		}
	}
}
