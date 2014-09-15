package org.we3whoreign.wisp.utils;

import java.util.Comparator;

public class SubclassComparator implements Comparator<String>{

	private final String GENERAL = "general";
	
	/**
	 * The result is a negative integer if the first String object lexicographically precedes 
	 * the second string. The result is a positive integer if the first String object 
	 * lexicographically follows the second string. The result is zero if the strings are equal.
	 * The string 'general' always takes precedence. All comparisons are done ignoring case
	 * @param sk0
	 * @param sk1
	 * @return
	 */
	@Override
	public int compare(String sk0, String sk1) {
		if(GENERAL.compareToIgnoreCase(sk0) == 0){
			return -1;
		}
		else if(GENERAL.compareToIgnoreCase(sk1) == 0){
			return 1;
		}
		else{
			return sk0.compareToIgnoreCase(sk1);
		}
	}
}
