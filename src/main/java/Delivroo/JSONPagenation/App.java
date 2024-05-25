package Delivroo.JSONPagenation;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     //   System.out.println( "Hello World!" );
        String genre="Action, Adventure, Drama";
        String str =getHighestRatedShow(genre);
        System.out.println(str);
    }

	private static String getHighestRatedShow(String genre) {
		// TODO Auto-generated method stub
		JSONReader jread=new JSONReader();
		jread.readJSON();
		return jread.findShow(genre);
	}
}
