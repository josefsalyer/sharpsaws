package me.salyer.Monkey;
/**
 * Koala
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import me.salyer.Monkey.Models.Report;
/**
 * @author jsalyer
 *
 */
public class Application {
    
    public static void main(String[] args) 
    {
        if ( args.length == 1 )
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
            catch (IOException e)
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
            System.out
                    .println("Usage: /path/to/java Application.java \"report.dat\" ");
        }

	}

    private static void handleException(Exception e)
    {
        System.out.print("An error occurred while running: "
                + e.getLocalizedMessage());
        e.printStackTrace();
    }

    public Application(String fileName)
    {
        Application.main(new String[] { fileName });

    }

}
