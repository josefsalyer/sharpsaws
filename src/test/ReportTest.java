package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import me.salyer.Monkey.Models.Report;

import org.junit.Test;

public class ReportTest
{
    String goodData = "testdata.dat";
    String badData  = "goodData.dat";

    @Test
    public void testReport()
    {
        Report report = new Report(goodData);
        assertNotNull(report);
    }

    @Test
    public void fileDoesNotOpen()
    {

        Boolean expected = false;
        Boolean actual = false;
        Report report = null;
        try
        {
            report = new Report(goodData);
            actual = true;
        }
        catch (Exception e)
        {
            actual = false;
            // e.printStackTrace();
        }

        assertEquals(expected, actual);
        assertNull(report);
    }


}
