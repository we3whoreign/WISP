package org.we3whoreign.wisp.utils;

import java.util.StringTokenizer;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class StringUtils {
	
	/**
	 * Camel case a string. Also replace all _ with spaces, since folder paths are used to derive names
	 * @param input
	 * @return
	 */
	public static String toCamelCase(String input){
		
		String breakchar = "_";
		if(input.contains(" ")){
			breakchar = " ";
		}
		String ret = "";
		
		StringTokenizer tokenizer = new StringTokenizer(input,breakchar);
		
		while(tokenizer.hasMoreElements()){
			ret += capitalizeFirst(tokenizer.nextElement().toString())+ " ";
		}
		
		//Trim the extra space at the end and return it
		return ret.trim();
	}
	
	/**
	 * Returns the first letter capitalized of input, and the rest lowercase
	 * @param input
	 * @return capitalize the first letter of each word
	 */
	public static String capitalizeFirst(String input){
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}

	/**
	 * @param source
	 * @return source with all spaces changed to _ and lowercase
	 */
	public static String toFileName(String source) {
		source = source.replaceAll("[':\\.]", "");
		return source.replaceAll(" ", "_").toLowerCase().trim();
	}

}

	
