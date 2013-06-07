package me.salyer.Monkey.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Account 
{

    private String              accountNumber = null;

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    private String join(List<Digit> digits)
    {
        String joined = "";

        for (Digit digit : digits)
        {
            joined += digit.getNumberAsString();
        }

        return joined;
    }

	public static final Integer CHAR_WIDTH = 3;
	public static final Integer ACCT_WIDTH = 9;
	public static final Integer LINE_WIDTH = 27;
	
    public List<String>         lines;
    public List<Digit>          digits;
	
    public Account(String top, String middle, String bottom)
	{


        lines = new ArrayList<String>(Arrays.asList(top, middle, bottom));
        digits = new ArrayList<Digit>();

        iterateForDigits();

	}
	
    private Boolean validateLine(String line)
	{
		Boolean isValid = true;

        if ( line == null || line.length() != Account.LINE_WIDTH )
		{
            isValid = false;
		}

		return isValid;
	}



	private void iterateForDigits() 
	{
        for (int i = 0; i < ACCT_WIDTH; i++)
        {
            this.digits.add(Digit.getDigit(stringForIndex(i)));
        }
        
        this.accountNumber = (Account.checksum(digits)) ? this.join(digits) : "INVALID";

    }

    public static Boolean checksum(List<Digit> hopefulAccountNumber)
    {
        int sum = 0;
        for (int i = 1; i <= ACCT_WIDTH; i++)
        {
            sum += (hopefulAccountNumber.get(ACCT_WIDTH - i).ordinal() * i);
        }
        return (sum % 11 == 0);
    }




    private String stringForIndex(int i)
    {
        String digitString = "";
        for (String line : this.lines)
        {
            if ( validateLine(line) )
            {
                int start = i * CHAR_WIDTH;
                int end = start + CHAR_WIDTH;
                digitString += line.substring(start, end);
            }
        }

        return digitString;
    }

}
