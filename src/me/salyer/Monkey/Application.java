package me.salyer.Monkey;
/**
 * Koala
 */

/**
 * @author jsalyer
 *
 */
import java.io.*;
public class Application {

    public static int answer() 
    {
        return 42;
    }

    public static Boolean process(String fileName)
    {
    	FileInputStream fStream;
    	DataInputStream dStream;
    	BufferedReader 	reader = null;
    	
		try {
			fStream = new FileInputStream(fileName);
			dStream = new DataInputStream(fStream);
	    	reader  = new BufferedReader(new InputStreamReader(dStream));
	    	
	    	//do some awesome stuff here
	    	
	    	reader.close();
		} catch (Exception e) {
			
			return false;
			
		}
    	

        return (reader != null);
    	
    }
    
    public static void main(String[] args) 
    {
    
	}

}
