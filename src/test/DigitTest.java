/**
 * 
 */
package test;

import static org.junit.Assert.*;

import me.salyer.Monkey.Models.Digit;

import org.junit.Test;

/**
 * @author jsalyer
 *
 */
public class DigitTest {

	@Test
	public void testForZero() 
	{
		String str = " _ "+
				 	 "| |"+
				 	 "|_|";
		
		//LEARN: Why JAVA primitive typing is vague
		int expectedInt = 0;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
	
		
	}
	
	
	@Test
	public void testForOne() 
	{
		String str = "   "+
					 "  |"+
					 "  |";
		
		int expectedInt = 1;
		Digit digit = Digit.getDigit(str);
		
	
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	
	@Test
	public void testForTwo() 
	{
		String str = " _ "+
				 	 " _|"+
				 	 "|_ ";
		
		int expectedInt = 2;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
		
		
	}
	
	
	@Test
	public void testForThree() 
	{
		String str = " _ "+
				 	 " _|"+
				 	 " _|";
		
		int expectedInt = 3;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForFour() 
	{
		String str = "   "+
				 	 "|_|"+
				 	 "  |";
		
		int expectedInt = 4;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForFive() 
	{
		String str = " _ "+
				 	 "|_ "+
				 	 " _|";
		
		int expectedInt = 5;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForSix() 
	{
		String str = " _ "+
				 	 "|_ "+
				 	 "|_|";
		
		int expectedInt = 6;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForSeven() 
	{
		String str = " _ "+
				 	 "  |"+
				 	 "  |";
		
		int expectedInt = 7;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForEight() 
	{
		String str = " _ "+
				 	 "|_|"+
				 	 "|_|";
		
		int expectedInt = 8;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
	}
	
	@Test
	public void testForNine() 
	{
		String str = " _ "+
				 	 "|_|"+
				 	 " _|";
		
		int expectedInt = 9;
		Digit digit = Digit.getDigit(str);
		
		assertEquals( digit.ordinal(), expectedInt);
		assertEquals( digit.getNumber(), digit.ordinal());
		assertTrue( digit.getString().equals(str) );
		assertTrue( digit.getNumberAsString().equals(Integer.toString(expectedInt)));
		
		
	}
	
	
}
