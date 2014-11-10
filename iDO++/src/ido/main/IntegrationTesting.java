package ido.main;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class IntegrationTesting {

	private static final String FILE = "211013";
	private static final String FILETXT = FILE + ".txt";
	
	@Test
	public void testing(){
		ArrayList<String> test = new ArrayList<>();
		(new FileAccessor(FILETXT, test)).writeContents();

		//Commands
		(new CommandAdd(FILE,"-","1st")).addTask();
		(new CommandAdd(FILE,"-","2nd")).addTask();
		(new CommandAdd(FILE,"-","3rd")).addTask();
		(new CommandAdd(FILE,"-","4th")).addTask();
		(new CommandDelete(FILE, "-1")).delete();
		test = (new FileAccessor(FILETXT).readContents());
		assertEquals(test.size(),0);
		(new CommandAdd(FILE,"-","1st")).addTask();
		(new CommandAdd(FILE,"9-10","2nd")).addTask();
		try {
			(new CommandEdit()).edit(FILE,"1","time","-");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test = (new FileAccessor(FILETXT).readContents());
		System.out.println(test.get(0));
		System.out.println(test.get(1));
		assertEquals(test.get(1),"[all-day] 2nd");
		
		System.out.println((new File(FILETXT)).delete());
	}
}
