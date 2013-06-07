package me.salyer.Monkey.Models;

import java.util.Map;
import java.util.HashMap;

public enum Digit 
{
	//by the numbers
	ZERO  	(" _ "+
			 "| |"+
			 "|_|"),
	ONE		("   "+
			 "  |"+
			 "  |"),
	TWO		(" _ "+
			 " _|"+
			 "|_ "),
	THREE 	(" _ "+
			 " _|"+
			 " _|"),
	FOUR  	("   "+
			 "|_|"+
			 "  |"),
	FIVE  	(" _ "+
			 "|_ "+
			 " _|"),
	SIX   	(" _ "+
			 "|_ "+
			 "|_|"),
	SEVEN 	(" _ "+
			 "  |"+
			 "  |"),
	EIGHT 	(" _ "+
			 "|_|"+
			 "|_|"),
	NINE  	(" _ "+
			 "|_|"+
			 " _|")
;


	private String string;
	
	private static Map<String, Digit> stringToDigitMap;
	
	
	private Digit(String aString) 
	{
		this.string = aString;
	}
	
	private static void initMapping() 
	{
		stringToDigitMap = new HashMap<String, Digit>();
		
		for (Digit d : values())
		{
			stringToDigitMap.put(d.string, d);
		}
		
	}
	
	public int getNumber() 
	{
		return this.ordinal();
	}

	public String getString() 
	{
		return string;
	}
	
	public String getNumberAsString()
	{
		return Integer.toString(this.getNumber());
	}
	
	
	public static Digit getDigit(String s) 
	{
		Digit d = null;

		if (stringToDigitMap == null) 
		{
			initMapping();
		}
		
		if(s instanceof String && s.length() == 9)
		{
			d = stringToDigitMap.get(s);
		}

		
		return d;
		
	}
	
}
