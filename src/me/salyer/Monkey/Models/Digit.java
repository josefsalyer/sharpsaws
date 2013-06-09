package me.salyer.Monkey.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final int          THRESHHOLD = 1;

    public static final String        UNK = "?";
    public static final Integer       LINE_WIDTH = 27;

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


    // shamelessly stolen from wikipedia:
    // http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
    // optimized for some performance gains

    public static int computeLevenshteinDistance(CharSequence str1, CharSequence str2)
    {
        int str1len = str1.length();
        int str2len = str2.length();

        int[][] distance = new int[str1len + 1][str2len + 1];

        for (int i = 0; i <= str1len; i++)
            distance[i][0] = i;
        for (int j = 1; j <= str2len; j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1len; i++)
            for (int j = 1; j <= str2len; j++)
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]
                                + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                                        : 1));

        return distance[str1.length()][str2.length()];
    }

    private static int minimum(int a, int b, int c)
    {
        return Math.min(Math.min(a, b), c);
    }

    public List<Digit> guesses()
    {
        List<Digit> guesses = null;

        for (int i = Account.ACCT_WIDTH; i > -1; i--)
        {
            Digit digit = Digit.values()[i];
            String s = digit.getString();
            int distance = Digit.computeLevenshteinDistance(this.string, s);

            if ( distance <= Digit.THRESHHOLD )
            {
                if(guesses == null)
                {
                    guesses = new ArrayList<Digit>();
                }
                guesses.add(digit);
            }

                
        }

        return guesses;
    }
	

}
