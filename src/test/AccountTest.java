package test;

import static org.junit.Assert.assertTrue;
import me.salyer.Monkey.Models.Account;

import org.junit.Test;

public class AccountTest 
{
	public String top;
	public String middle;
	public String bottom;

	
	@Test
    public void testAccount()
	{
        top    = "    _  _     _  _  _  _  _ ";
        middle = "  | _| _||_||_ |_   ||_||_|";
        bottom = "  ||_  _|  | _||_|  ||_| _|";

		String expected = "123456789";

        Account account = new Account(top, middle, bottom);

        System.out.print(account.getAccountNumber());
        assertTrue(expected.equals(account.getAccountNumber()));

	}

}
