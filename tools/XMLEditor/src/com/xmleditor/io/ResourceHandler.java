package com.xmleditor.io;

import java.io.InputStream;
import java.util.List;

import com.xmleditor.io.exceptions.FileIsNotDirectory;

/**
 * Define an interface for resource handlers. This will be used for Input/Output
 * @author ryzngard
 *
 */
public interface ResourceHandler {
	
	/**
	 * Method for getting all the objects created by the Resourcehandler
	 * @return
	 */
	public List<InputStream> getObjects();
	
	
	 /**
	  * Method for getting a resource as an inputstream
	  */
	public InputStream getResource(String resource);
	
	
	/**
	 * Method to get a List of strings relating to resources handled. Each resource should be able to be passed to getResource()
	 */
	public List<String> getResourceIdentifiers();
	
	/**
	 * Does a regex search of all the resources in getResourceIdentifiers() to see if the string passed is contained. 
	 * Returns the first success or null if failed
	 */
	public String resourceLookup(String search);


	/**
	 * Get all matches of the string. Iterates through all the resources and performs regex matching using pattern
	 * @param pattern regex pattern to look for
	 * @return list of all occurences that match this
	 */
	public List<String> getAllMatches(String pattern);

}
