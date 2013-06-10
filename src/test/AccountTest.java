package test;


import static org.junit.Assert.assertTrue;
import me.salyer.Monkey.Models.Account;

import org.junit.Test;

public class AccountTest 
{
    public String topham;
	public String middle;
	public String bottom;
	

    @Test
    public void testInvalidAccount()
    {
        topham = "    _  _     _  _  _  _  _ ";
        middle = "  | _| _||_||_ |_   ||_||_|";
        bottom = "  ||_  _|  | _||_|  ||_||_|";

        Account account = new Account(topham, middle, bottom);
        String expected = "123456788    AMB    ['123436788', '123459788', '123456738', '123456789']";
        assertTrue(expected.equals(account.getAccountNumber()));

    }

    @Test
    public void testValidAccount()
    {
        topham = "    _  _     _  _  _  _  _ ";
        middle = "  | _| _||_||_ |_   ||_||_|";
        bottom = "  ||_  _|  | _||_|  ||_| _|";

        Account account = new Account(topham, middle, bottom);
        String expected = "123456789";
        assertTrue(expected.equals(account.getAccountNumber()));

    }

    @Test
    public void testAnotherValidAccount()
    {
        // known valid 345882865

        topham = " _     _  _  _  _  _  _  _ ";
        middle = " _||_||_ |_||_| _||_||_ |_ ";
        bottom = " _|  | _||_||_||_ |_||_| _|";

        String expected = "345882865";

        Account account = new Account(topham, middle, bottom);

        assertTrue(expected.equals(account.getAccountNumber()));
    }

    @Test
    public void testSingleAmbiguousAccount()
    {

        topham = " _  _  _  _  _  _  _  _  _ ";
        middle = " _||_||_ |_||_| _||_||_ |_ ";
        bottom = " _|  | _||_||_||_ |_||_| _|";

        String expected = "345882865";

        Account account = new Account(topham, middle, bottom);

        assertTrue(expected.equals(account.getAccountNumber()));

    }

    @Test
    public void testCheckSum()
    {
        // 345882865
        topham = " _     _  _  _  _  _  _  _ ";
        middle = " _||_||_ |_||_| _||_||_ |_ ";
        bottom = " _|  | _||_||_||_ |_||_| _|";
        
        Account account = new Account(topham, middle, bottom);

        assertTrue(Account.checksum(account.digits));
    }

    @Test
    public void testMultipleAmbiguitiesInOneAccount()
    {
        topham = " _  _  _  _  _  _  _  _  _ ";
        middle = "|_||_||_||_||_||_||_||_||_|";
        bottom = "|_||_||_||_||_||_||_||_||_|";

        String expected = "888888888    AMB    ['888388888', '888886888', '888888988', '888888880']";

        Account account = new Account(topham, middle, bottom);

        assertTrue(expected.equals(account.getAccountNumber()));

    }

}
