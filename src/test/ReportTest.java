package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import me.salyer.Monkey.Models.Report;

import org.junit.Test;

public class ReportTest
{
    String goodData = "testdata.dat";
    String badData  = "blerg.dat";

    @Test
    public void testReport()
    {
        Boolean expected = false;
        Boolean actual = false;
        Report report = null;

        try
        {
            report = new Report(goodData);
        }
        catch (FileNotFoundException e)
        {
            actual = true;
        }
        catch (IOException e)
        {

            actual = true;
        }

        assertEquals(expected, actual);
        assertNotNull(report);
        
    }
    
    @Test
    public void testAccountNumbers() throws IOException
    {
        Report report = new Report(goodData);
        assertNotNull(report.getAccountNumbers());
    }
    
    @Test
    public void testPrint() throws IOException
    {
        Report report = new Report(goodData);
        boolean expected = false;
        boolean actual = false;

        try
        {
            report.print();
        }
        catch (Exception e)
        {
            actual = true;
        }

        assertEquals(expected, actual);

    }

    @Test
    public void fileDoesNotOpen()
    {

        Boolean expected = true;
        Boolean actual = false;
        Report report = null;

        try
        {
            report = new Report(badData);
            actual = true;
        }
        catch (FileNotFoundException e)
        {
            actual = true;
        }
        catch (IOException e)
        {
            actual = true;
        }

        assertEquals(expected, actual);
        assertNull(report);
    }

}
