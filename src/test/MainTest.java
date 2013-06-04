package test;


import static org.junit.Assert.*;

import org.junit.Test;

import me.salyer.Monkey.*;


public class MainTest {

	@Test
	public void testMain() 
	{
		
		//using exit codes to decide application exit state
		assertEquals(0, Application.main(new String[] {}));
		
		//testing the application doesn't choke on args
		assertEquals(3, Application.main(new String[] {"a","b","c"}));
	}

}
