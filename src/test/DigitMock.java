package test;

import me.salyer.Monkey.Models.*;

public class DigitMock 
{
	public static String generateAccountNumber()
	{
		//todo add new lines
		return Digit.EIGHT.getNumberAsString() + 
			   Digit.SIX.getNumberAsString() +
			   Digit.SEVEN.getNumberAsString() +
			   Digit.FIVE.getNumberAsString() +
			   Digit.THREE.getNumberAsString() +
			   Digit.ZERO.getNumberAsString() +
			   Digit.NINE.getNumberAsString() +
			   Digit.NINE.getNumberAsString() +
			   Digit.NINE.getNumberAsString();
	}
}
