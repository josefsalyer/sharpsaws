package me.salyer.Monkey.Models;

import java.util.HashMap;
import java.util.Map;

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
			 " _|"),
 UNKNOWN("")
;


    public static final String        UNK = "?";
    private String                    string;
	
	private static Map<String, Digit> stringToDigitMap;
	
	
    public static Digit getDigit(String s)
    {
        Digit d = null;

        if ( stringToDigitMap == null )
        {
            initMapping();
        }

        if(stringToDigitMap.containsKey(s))
        {
            d = stringToDigitMap.get(s);
        }
        else
        {
            d = Digit.UNKNOWN;
            d.string = s;
        }
        

        return d;

    }

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
        return (isUnknown()) ? -1 : this.ordinal();
	}

	public String getString() 
	{
		return string;
	}
	
	public String getNumberAsString()
	{
        return (isUnknown()) ? Digit.UNK : Integer.toString(this.getNumber());
	}
	
    private Boolean isUnknown()
	{
        return (this.ordinal() + 1 == Digit.values().length);
	}

	
	

}
