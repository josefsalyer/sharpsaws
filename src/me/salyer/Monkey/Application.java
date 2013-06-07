package me.salyer.Monkey;
/**
 * Koala
 */

/**
 * @author jsalyer
 *
 */
import me.salyer.Monkey.Models.Report;
public class Application {
    
    public static void main(String[] args) 
    {
        if ( args.length == 1 )
    	{
            Report report = new Report(args[0]);

            report.print();

    	}
        else
        {
            System.out
                    .println("Usage: /path/to/java Application.java \"report.dat\" ");
        }

	}

    public Application(String fileName)
    {
        Application.main(new String[] { fileName });

    }

}
