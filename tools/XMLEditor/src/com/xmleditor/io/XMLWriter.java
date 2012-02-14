package com.xmleditor.io;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import skillsplanner.beans.DFOClass;
import skillsplanner.io.IOHandler;

public class XMLWriter {
	private static XMLWriter writer;
	
	public static XMLWriter getInstance(){
		if(writer == null){
			writer = new XMLWriter();
		}
		
		return writer;
	}
	
	public boolean classToXML(DFOClass dfoclass){
		XMLOutputter outputter = new XMLOutputter();
		
		try{
			Document doc = buildClassDocument(dfoclass);
			Format format = Format.getPrettyFormat();
			outputter.setFormat(format);
			outputter.output(doc, System.out);
			outputter.output(doc, IOHandler.getClassOutputter(dfoclass));
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	private Document buildClassDocument(DFOClass dfoclass){
		Document doc = new Document();
		
		Element rootElement = new Element("skillplanner");
		
		Element description = new Element("description");
		Element name = new Element("name");
		
		description.setText(dfoclass.getDescription());
		name.setText(dfoclass.getName());
		
		rootElement.addContent(description);
		rootElement.addContent(name);
		
		for(String sk : dfoclass.getSkills().keySet()){
			Element skill = new Element("skill");
			skill.setText(sk);
			rootElement.addContent(skill);
		}
		
		doc.setContent(rootElement);
		
		return doc;
	}

}
