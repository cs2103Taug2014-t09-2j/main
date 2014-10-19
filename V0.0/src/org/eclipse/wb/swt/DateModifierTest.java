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
public class DateModifierTest {

	@Test
	public void test() {
		String curdate = DateModifier.getCurrDate();
		assertEquals(DateModifier.getNextDate("010114"), "020114");
		assertEquals(DateModifier.getNextDate("310114"), "010214");
		assertEquals(DateModifier.getNextDate("280214"), "010314");
		assertEquals(DateModifier.getNextDate("311214"), "010115");
	}
}
