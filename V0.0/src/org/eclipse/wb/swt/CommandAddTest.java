/**
 * 
 */
package org.eclipse.wb.swt;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Antony
 *
 */
public class CommandAddTest {
	
	@Test
	public void test() {
		try {
			String filename = "a1";
			BufferedWriter bw = (new BufferedWriter(new FileWriter((new File(filename+".txt")).getAbsoluteFile())));
			bw.write("010101\n\n1. [0100-0900] Play dota\n");
			bw.close();
			(new CommandAdd(filename,"2","sometask")).addTask();
			ArrayList<String> l = (new FileAccessor(filename+".txt").readContents());
			for (int i=0;i<l.size();i++){
				System.out.println(l.get(i));
			}
			assertEquals(l.get(0),"[0100-0900] Play dota");
			assertEquals(l.get(1),"[0200] sometask");
			(new File(filename+".txt")).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
