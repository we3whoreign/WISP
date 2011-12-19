package skillsplanner.utils;

import java.util.StringTokenizer;

public class StringUtils {
	
	/**
	 * Camel case a string. Also replace all _ with spaces, since folder paths are used to derive names
	 * @param input
	 * @return
	 */
	public static String toCamelCase(String input){
		String ret = "";
		
		StringTokenizer tokenizer = new StringTokenizer(input,"_");
		
		while(tokenizer.hasMoreElements()){
			ret += capitalizeFirst(tokenizer.nextElement().toString())+ " ";
		}
		
		//Trim the extra space at the end and return it
		return ret.trim();
	}
	
	/**
	 * Returns the first letter capitalized of input, and the rest lowercase
	 * @param input
	 * @return
	 */
	public static String capitalizeFirst(String input){
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

}

	
