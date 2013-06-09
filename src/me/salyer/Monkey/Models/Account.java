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
    NONE, ONE, MANY;
}


public class Account 
{
    // constants
    public static final Integer CHAR_WIDTH    = 3;
    public static final Integer ACCT_WIDTH    = 9;
    public static final String  DELIMITER     = "    "; // FIXME: I'd probably
                                                       // want to use a comma or
                                                       // \t in a real world
                                                       // situation

    // properties
    public List<String>         lines;
    public List<Digit>          digits;
    private AccountType         accountType;
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

        List<Digit> unknownDigits = null;
        List<List<Digit>> ambiguousDigits = new ArrayList<List<Digit>>(); // there can be


        // get a subset of digits that are ambiguous or unknown
        for (Digit digit : candidates)
        {
            // grab unknown characters
            if ( digit == Digit.UNKNOWN )
            {
                if ( unknownDigits == null )
                {
                    unknownDigits = new ArrayList<Digit>();
                }

                unknownDigits.add(digit);
            }
            
            // only add them if there are any
            if ( unknownDigits != null )
            {
                ambiguousDigits.add(unknownDigits);
            }

            // grab ambiguous characters
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

        List<Digit> finalMatch = null;

        List<List<Digit>> totalMatches = evaluateAmbiguousDigitsAndCandidates(ambiguousDigits, candidates);

        MatchType matchType;
        // fix for multiple matches types
        if ( totalMatches.size() > 1 )
        {
            matchType = MatchType.MANY;
        }
        else
        {
            matchType = MatchType.values()[totalMatches.size()];

        }
        
        this.accountType = determineAccountType(matchType);

        if ( matchType == MatchType.ONE )
        {
            finalMatch = totalMatches.get(0);
        }
        else
        {
            finalMatch = candidates;
        }
        finalizeAccountNumber(finalMatch);
    }

    private List<List<Digit>> evaluateAmbiguousDigitsAndCandidates(List<List<Digit>> ambiguousDigits, List<Digit> candidates)
    {
        List<List<Digit>> finalMatch = new ArrayList<List<Digit>>();
        for (List<Digit> list : ambiguousDigits)
        {
            List<List<Digit>> temp = evaluateUnknownDigitsAndCandidates(list, candidates);
            if ( temp != null )
            {
                finalMatch.addAll(temp);
            }

        }
        
        return finalMatch;
    }

    public List<List<Digit>> evaluateUnknownDigitsAndCandidates(List<Digit> unknownDigits, List<Digit> candidates)
    {

        List<List<Digit>> matches = new ArrayList<List<Digit>>();

        for (int h = 0, u = unknownDigits.size(); h < u; h++)
        {
            List<Digit> guesses = unknownDigits.get(h).guesses();

            int index = candidates.indexOf(Digit.UNKNOWN);

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

        }

        return matches;

    }

    private AccountType determineAccountType(MatchType matchType)
    {

        switch (matchType)
        {
        case NONE:
            return AccountType.ERR;
        case ONE:
            return AccountType.OK;
        case MANY:
        default:
            return AccountType.AMB;
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
            s += Account.DELIMITER;
            s += this.accountType.toString();
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
