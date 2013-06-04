package me.salyer.Monkey;
/**
 * 
 */

/**
 * @author jsalyer
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static int main(String[] args) {
		
		
		//handle args just in case
		if(args.length > 0)
		{
			// for now if we have args, we'll print them out
		
			for(String arg : args)
			{
				System.out.println(arg);
			}
			
			//and exit with the length
			return args.length;
		}
		
		//magical application stuff happens here
		
		return 0;
	
	}

}
