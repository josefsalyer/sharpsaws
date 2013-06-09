/**
 * 
 */
package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import me.salyer.Monkey.Models.Digit;

import org.junit.Test;

/**
 * @author jsalyer
 *
 */
public class DigitTest {

    @Test
    public void testForZero()
    {
        String str = " _ " + "| |" + "|_|";

        // LEARN: Why JAVA primitive typing is vague
        int expectedInt = 0;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForOne()
    {
        String str = "   " + "  |" + "  |";

        int expectedInt = 1;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForTwo()
    {
        String str = " _ " + " _|" + "|_ ";

        int expectedInt = 2;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForThree()
    {
        String str = " _ " + " _|" + " _|";

        int expectedInt = 3;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForFour()
    {
        String str = "   " + "|_|" + "  |";

        int expectedInt = 4;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForFive()
    {
        String str = " _ " + "|_ " + " _|";

        int expectedInt = 5;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForSix()
    {
        String str = " _ " + "|_ " + "|_|";

        int expectedInt = 6;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForSeven()
    {
        String str = " _ " + "  |" + "  |";

        int expectedInt = 7;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForEight()
    {
        String str = " _ " + "|_|" + "|_|";

        int expectedInt = 8;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    @Test
    public void testForNine()
    {
        String str = " _ " + "|_|" + " _|";

        int expectedInt = 9;
        Digit digit = Digit.getDigit(str);

        assertEquals(digit.ordinal(), expectedInt);
        assertEquals(digit.getNumber(), digit.ordinal());
        assertTrue(digit.getString().equals(str));
        assertTrue(digit.getNumberAsString().equals(
                Integer.toString(expectedInt)));

    }

    // Ambivalent characters

    @Test
    public void testForPhoney()
    {
        String str = " __" + "|_|" + " _|";
        int expectedInt = -1;
        int expectedOrdinal = 10;
        String expected = "?";
        Digit digit = Digit.getDigit(str);

        assertNotNull(digit);
        assertEquals(expectedOrdinal, digit.ordinal());
        assertEquals(digit.getNumber(), expectedInt);
        assertTrue(expected.equals(digit.getNumberAsString()));

    }

    // this is mostly for my own edification
    @Test
    public void testKnownDistanceBetweenSomeSimilarNumbers()
    {
        // 0,8
        assertEquals(1, Digit.computeLevenshteinDistance(
                Digit.ZERO.getString(), Digit.EIGHT.getString()));

        // 9,8
        assertEquals(1, Digit.computeLevenshteinDistance(
                Digit.NINE.getString(), Digit.EIGHT.getString()));

        // 1,7
        assertEquals(1, Digit.computeLevenshteinDistance(Digit.ONE.getString(),
                Digit.SEVEN.getString()));
        // 3,9
        assertEquals(1, Digit.computeLevenshteinDistance(
                Digit.THREE.getString(), Digit.NINE.getString()));

        // 5,6
        assertEquals(1, Digit.computeLevenshteinDistance(
                Digit.FIVE.getString(), Digit.SIX.getString()));

        // 4,9
        assertEquals(2, Digit.computeLevenshteinDistance(
                Digit.FOUR.getString(), Digit.NINE.getString()));

        // 0,9
        assertEquals(2, Digit.computeLevenshteinDistance(
                Digit.ZERO.getString(), Digit.NINE.getString()));

    }

    @Test
    public void testGuessForString()
    {


        Digit digit = Digit.ZERO;

        List<Digit> guesses = digit.guesses();

        assertNotNull(guesses);
        
        int expected = 4;
        int actual = guesses.size();

        assertEquals(expected, actual);

    }

    @Test
    public void testGuessForAmbiguous()
    {
        String amb = "__ "+                           
                     "|_|"+
                     " _|"
                     ;
        Digit digit = Digit.getDigit(amb);

        int expected = 4;
        int actual = digit.guesses().size();
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testGuessForAnotherAmbiguous()
    {
        String amb = " _ "+                           
                     "|_|"+
                     "  |"
                     ;

        Digit digit = Digit.getDigit(amb);

        int expected = 6;
        int actual = digit.guesses().size();
        
        assertEquals(expected, actual);
        
    }
	
}
