package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import me.salyer.Monkey.Application;

import org.junit.Test;

public class ApplicationTest 
{

    @Test
    public void testApplication()
    {
        Application app = null;

        app = new Application("testdata.dat");
        

        assertNotNull(app);
    }

    @Test
    public void testApplicationCallWithNoArgs()
    {
        boolean expected = false;
        boolean actual = false;

        try
        {
            Application.main(new String[] { null });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actual = true;
        }

        assertEquals(expected, actual);
    }

    @Test
    public void testApplicationCallWithArgs()
    {
        boolean expected = false;
        boolean actual = false;

        try
        {
            Application.main(new String[] { "testdata.dat" });
        }
        catch (Exception e)
        {
            actual = true;
        }

        assertEquals(expected, actual);
    }
    
}