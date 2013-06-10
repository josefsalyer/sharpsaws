package me.salyer.Monkey.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Digit>          candidates;

    private void setCandidates()
    {
        candidates = new ArrayList<Digit>();

        for (int i = 0; i < Account.ACCT_WIDTH; i++)
        {
            Digit d = Digit.getDigit(stringForIndex(i));
            this.candidates.add(d);
        }
    }

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

        setCandidates();
        evaluateCandidates();

    }

    // account determination logic
	private void evaluateCandidates() 
	{

        // eliminate passing account numbers early
        if ( Account.checksum(this.candidates) )
        {
            this.accountType = AccountType.OK;
            finalizeAccountNumber(this.candidates, null);
        }
        else
        {
            evaluateDigitsForAmbiguousness();
        }

    }

    private void evaluateDigitsForAmbiguousness()
    {
        // there can be more than one set of guesses per candidate index
        Map<Integer, List<List<Digit>>> ambiguousDigits = new HashMap<Integer, List<List<Digit>>>();

        // get a subset of digits that are ambiguous or unknown
        for (int i = 0, n = this.candidates.size(); i < n; i++)
        {
            // i iz importantz!
            Digit digit = this.candidates.get(i);
            
            List<List<Digit>> allGuessesForThisCandidate = new ArrayList<List<Digit>>();
            
            // grab unknown characters
            if ( digit == Digit.UNKNOWN )
            {
                List<Digit> unknownDigits = digit.guesses();

                if ( unknownDigits.size() > 0 )
                    allGuessesForThisCandidate.add(unknownDigits);
            }

            // grab ambiguous characters
            List<Digit> guesses = digit.guesses();
            
            if ( guesses.size() > 0 )
            {
                for (Digit guess : guesses)
                {
                    if ( guess != null )
                        allGuessesForThisCandidate.add(guesses);
                }

            }
            
            ambiguousDigits.put(i, allGuessesForThisCandidate);

        }

        List<Digit> finalMatch = null;
        List<List<Digit>> totalMatches = evaluateListsAndCandidates(ambiguousDigits);


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
        finalizeAccountNumber(finalMatch, totalMatches);
    }

    private List<List<Digit>> evaluateListsAndCandidates(Map<Integer, List<List<Digit>>> ambiguousDigits)
    {
        List<List<Digit>> finalMatch = new ArrayList<List<Digit>>();
        List<Integer> hashes = new ArrayList<Integer>();

        for (int i = 0, n = Account.ACCT_WIDTH; i < n; i++)
        {
            // i haz my list of lists
            List<List<Digit>> currentDigitLists = ambiguousDigits.get(i);

            for (List<Digit> list : currentDigitLists)
            {
                // because i iz the current candidate index
                List<List<Digit>> matches = findMatches(list, i);

                for (List<Digit> match : matches)
                {
                    if ( match != null && match.size() != 0 && !match.contains(Digit.UNKNOWN) && !hashes.contains(match.hashCode()) )
                    {
                        finalMatch.add(match);
                        hashes.add(match.hashCode());
                    }
                }

            }

        }

        

        return finalMatch;
    }

    private List<List<Digit>> findMatches(List<Digit> unknownDigits, int currentCandidateIndex)
    {
        List<List<Digit>> matches = new ArrayList<List<Digit>>();

        for (int h = 0, u = unknownDigits.size(); h < u; h++)
        {
            List<Digit> guesses = unknownDigits.get(h).guesses();

            // for all the guesses of unknown
            for (int i = 0, n = guesses.size(); i < n; i++)
            {
                // copy candidates for testing
                List<Digit> copy = createCopy(guesses.get(i), currentCandidateIndex);

                // test checksum
                if ( Account.checksum(copy) )
                {
                    matches.add(copy);
                }

            }

        }

        return matches;

    }

    private List<Digit> createCopy(Digit currentGuess, int currentCandidateIndex)
    {
        List<Digit> copy = new ArrayList<Digit>();

        // replace digit with guessed digit

        for (int j = 0; j < Account.ACCT_WIDTH; j++)
        {
            if ( j == currentCandidateIndex )
            {
                copy.add(currentGuess);
            }
            else
            {
                copy.add(this.candidates.get(j));
            }
        }

        return copy;
    }

    // utilities
    private static String join(List<Digit> digits)
    {
        String joined = "";

        for (Digit digit : digits)
        {
            joined += digit.getNumberAsString();
        }

        return joined;
    }

    private boolean validateLine(String line)
    {
        boolean isValid = true;

        if ( line == null || line.length() != Digit.LINE_WIDTH )
        {
            isValid = false;
        }

        return isValid;
    }

    private AccountType determineAccountType(MatchType matchType)
    {

        switch (matchType)
        {
        case NONE:
            return AccountType.ILL;
        case ONE:
            return AccountType.OK;
        case MANY:
        default:
            return AccountType.AMB;
        }

    }

    private void finalizeAccountNumber(List<Digit> candidates, List<List<Digit>> totalMatches)
    {
        this.digits = candidates;

        String s = Account.join(candidates);

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

        if ( totalMatches != null && totalMatches.size() > 1 )
        {
            s += Account.DELIMITER;
            s += "[";

            for (int i = 0, n = totalMatches.size(); i < n; i++)
            {
                s += "'";
                s += Account.join(totalMatches.get(i)).toString();
                s += "'";

                if ( i != n - 1 )
                {
                    s += ", ";
                }
            }
            s += "]";
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
        // if ( "888388888".equals(Account.join(hopefulAccountNumber)) )
        // {
        // System.out.println(Account.join(hopefulAccountNumber) + " " + sum +
        // " % 11 = " + (sum % 11));
        // }

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
