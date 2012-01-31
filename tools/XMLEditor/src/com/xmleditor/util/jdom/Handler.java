package com.xmleditor.util.jdom;

import java.io.File;
import java.io.InputStream;

import org.jdom.*;
import org.jdom.input.SAXBuilder;

/**
 * This handles the general utilities for JDOM. Anything that isn't specific 
 * to a class or job is put here.
 * @author ryzngard
 *
 */
public class Handler {
	
	
	/**
	 * Opens an xml document and creates a JDOM Document object using
	 * SAXBuilder
	 * @param url
	 * @return JDOM Document Object
	 */
	public static Document openXMLFile(String url){
		try{
			// Build the document with SAX and Xerces, no validation
            SAXBuilder builder = new SAXBuilder();
            // Create the document
            Document doc = builder.build(new File(url));
            
            return doc;
		}
		catch(Exception ex){
			//ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Opens an xml document and creates a JDOM document using 
	 * SAXBuilder
	 * @param f
	 * @return
	 */
	public static Document openXMLFile(File f){
		try{
			// Build the document with SAX and Xerces, no validation
            SAXBuilder builder = new SAXBuilder();
            // Create the document
            Document doc = builder.build(f);
            
            return doc;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public static double getDouble(Element e,String childText) {
		try{
			double d; //just the way I like em
			
			String s = e.getChildText(childText);
			
			d = Double.parseDouble(s);
			
			return d;
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
		
		return 0;
	}
	
	public static String getString(Element e,String childText){
		try{
			String s;
			
			s = e.getChildText(childText);
			
			return s.trim();
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
		
		return "";
	}

	/**
	 * Parses an Element for child string and returns the integer value
	 * @param skillattr
	 * @param string
	 * @return int value
	 */
	public static int getInteger(Element skillattr, String string) {
		try{
			String s;
			
			s = skillattr.getChildText(string);
			
			return Integer.parseInt(s);
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
		
		return 0;
	}
	

	/**
	 * Opens an XML file given an inputstream
	 * @param stream
	 * @return Document object for use with jdom parsing
	 */
	public static Document openXMLFile(InputStream stream) {
		try{
			// Build the document with SAX and Xerces, no validation
            SAXBuilder builder = new SAXBuilder();
            // Create the document
            Document doc = builder.build(stream);
            
            return doc;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	

}
