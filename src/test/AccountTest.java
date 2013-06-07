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
        String expected = "123456788    ILL";
        assertTrue(expected.equals(account.getAccountNumber()));


	}

    @Test
    public void testValidAccount()
    {
        // known valid 345882865

        topham = " _     _  _  _  _  _  _  _ ";
        middle = " _||_||_ |_||_| _||_||_ |_ ";
        bottom = " _|  | _||_||_||_ |_||_| _|";

        String expected = "345882865";

        Account account = new Account(topham, middle, bottom);

        assertTrue(expected.equals(account.getAccountNumber()));
    }

}
