package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class DigitMockTest 
{

	@Test
	public void testGenerateAccountNumber() 
	{
		int expected = 9;
		String accountNumber = DigitMock.generateAccountNumber();
		assertNotNull(accountNumber);
		assertEquals(expected, accountNumber.length());
	}

}
