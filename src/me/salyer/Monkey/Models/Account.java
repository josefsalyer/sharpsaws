package me.salyer.Monkey.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum AccountType
{
    OK("OK"), ERR("ERR"), ILL("ILL"), AMB("AMB");

    private final String name;

    AccountType(String s)
    {
        this.name = s;
    }
    
    @Override
    public String toString()
    {
        return this.name;
    }

}

enum MatchType
{
    NONE, ONE;
}


public class Account 
{
    // constants
    public static final Integer CHAR_WIDTH    = 3;
    public static final Integer ACCT_WIDTH    = 9;
    public static String        DELIMITER     = "    "; // FIXME: I'd probably
                                                       // want to use a comma or
                                                       // \t in a real world
                                                       // situation

    // properties
    public AccountType          accountType;
    public List<String>         lines;
    public List<Digit>          digits;
    private String              accountNumber = null;


    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    // constructor
    public Account(String top, String middle, String bottom)
    {

        lines = new ArrayList<String>(Arrays.asList(top, middle, bottom));
        digits = new ArrayList<Digit>();

        iterateForDigits();

    }

    // utilities
    private String join(List<Digit> digits)
    {
        String joined = "";

        for (Digit digit : digits)
        {
            joined += digit.getNumberAsString();
        }

        return joined;
    }


	
    private Boolean validateLine(String line)
	{
		Boolean isValid = true;

        if ( line == null || line.length() != Digit.LINE_WIDTH )
		{
            isValid = false;
		}

		return isValid;
	}

    // account determination logic
	private void iterateForDigits() 
	{
        List<Digit> candidates = new ArrayList<Digit>();

        for (int i = 0; i < Account.ACCT_WIDTH; i++)
        {
            Digit d = Digit.getDigit(stringForIndex(i));
            candidates.add(d);
        }
        

        // eliminate passing account numbers early
        if ( !Account.checksum(candidates) )
        {
            evaluateDigitsForAmbiguousness(candidates);
        }
        else
        {
            this.accountType = AccountType.OK;
            finalizeAccountNumber(candidates);
        }

    }

    private void evaluateDigitsForAmbiguousness(List<Digit> candidates)
    {

        List<Digit> unknownDigits = new ArrayList<Digit>();
        List<List<Digit>> ambiguousDigits = new ArrayList<List<Digit>>(); // there can be


        // get a subset of digits that are ambiguous
        for (Digit digit : candidates)
        {
            if ( digit == Digit.UNKNOWN )
            {
                unknownDigits.add(digit);
            }
            
            List<Digit> guesses = digit.guesses();
            
            if ( guesses.size() > 0 )
            {
                for (Digit guess : guesses)
                {
                    if ( guess != null )
                        ambiguousDigits.add(guesses);
                }

            }
            
        }

        if ( unknownDigits.size() > 0 )
        {
            evaluateUnknownDigitsAndCandidates(unknownDigits, candidates);
        }

        if ( ambiguousDigits.size() > 0)
        {
            evaluateAmbiguousDigitsAndCandidates(ambiguousDigits, candidates);
        }

        
    }

    private void evaluateAmbiguousDigitsAndCandidates( List<List<Digit>> ambiguousDigits, List<Digit> candidates)
    {
        for (List<Digit> list : ambiguousDigits)
        {
            evaluateUnknownDigitsAndCandidates(list, candidates);
        }
        
    }

    private void evaluateUnknownDigitsAndCandidates(List<Digit> unknownDigits, List<Digit> candidates)
    {
        // TODO: refactor into its own thing -- actually, we can probably handle
        // this through a recursive solution
        // if ( unknownDigits.size() == 1 )
            
        for (int h = 0, u = unknownDigits.size(); h < u; h++)
        {
            List<Digit> guesses = unknownDigits.get(h).guesses();

            // if there are no guesses end early
            // if ( guesses.isEmpty() )
            // {
            // this.accountType = AccountType.ILL;
            // finalizeAccountNumber(candidates);
            // }

            int index = candidates.indexOf(Digit.UNKNOWN);
            List<List<Digit>> matches = new ArrayList<List<Digit>>();

            for (int i = 0, n = guesses.size(); i < n; i++)
            {
                // clone candidates
                List<Digit> copy = new ArrayList<Digit>(Account.ACCT_WIDTH);

                // replace with guess
                for (int j = 0; j < Account.ACCT_WIDTH; j++)
                {
                    if ( j == index )
                    {
                        copy.add(guesses.get(i));
                    }
                    else
                    {
                        copy.add(candidates.get(j));
                    }
                }

                // test checksum
                if ( Account.checksum(copy) )
                {
                    // increment matches if checksum passes
                    matches.add(copy);
                }

            }

            MatchType matchType = MatchType.values()[matches.size()];

            switch (matchType)
            {
            case NONE:
                this.accountType = AccountType.ERR;
                break;

            case ONE:
                this.accountType = AccountType.OK;
                break;

            default:
                this.accountType = AccountType.AMB;
                break;
            }

            // finalize
            finalizeAccountNumber((this.accountType == AccountType.OK) ? matches
                    .get(0) : candidates);

        }

    }

    private void finalizeAccountNumber(List<Digit> candidates)
    {
        this.digits = candidates;

        String s = this.join(candidates);

        switch (this.accountType)
        {
        case OK:
            break;
        case ILL:
        case ERR:
        case AMB:
        default:
            s += Account.DELIMITER += this.accountType.toString();
            break;
        }

        this.accountNumber = s;

    }


    public static boolean checksum(List<Digit> hopefulAccountNumber)
    {
        int sum = 0;
        for (int i = 1; i <= Account.ACCT_WIDTH; i++)
        {
            sum += (hopefulAccountNumber.get(Account.ACCT_WIDTH - i).ordinal() * i);
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
