package me.salyer.Monkey;
/**
 * Koala
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
			return exit(args.length);
		}
		
		//magical application stuff happens here
		
		
		//if all went well then return 0;
		return exit(0);
	
	}
	
	public static int exit(int i)
	{
		//add any exit reporting here
		
		return i;
	}

}
