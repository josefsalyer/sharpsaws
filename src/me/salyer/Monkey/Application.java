package me.salyer.Monkey;
/**
 * Koala
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import me.salyer.Monkey.Models.Report;

public class Application {
    
    public static void main(String[] args) throws IOException
    {
        if ( args.length == 1 && args[0] != null && args[0].length() > 0 )
    	{
            Report report = null;
            try
            {
                report = new Report(args[0]);
            }
            catch (FileNotFoundException e)
            {
                Application.handleException(e);
            }

            if ( report != null )
            {
                report.print();
            }

    	}
        else
        {
            System.out.println("Usage: /path/to/java Application.java \"report.dat\" ");
        }

	}

    public static void handleException(Exception e)
    {
        System.out.print("An error occurred while running: " + e.getLocalizedMessage());
        e.printStackTrace();
    }

    public Application(String fileName) throws IOException
    {
        Application.main(new String[] { fileName });
    }

}
