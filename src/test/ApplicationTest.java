package test;


import static org.junit.Assert.assertNotNull;
import me.salyer.Monkey.Application;

import org.junit.Test;

public class ApplicationTest 
{

    @Test
    public void testApplication()
    {
        Boolean expected = false;
        Boolean actual = false;
        Application app = null;
        try
        {
            app = new Application("testdata.dat");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            actual = true;
        }
        
        // FIXME: assertEquals(expected, actual);
        assertNotNull(app);
    }
    
    
}