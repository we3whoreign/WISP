package com.xmleditor.io;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import skillsplanner.beans.DFOClass;
import skillsplanner.beans.Skill;
import skillsplanner.beans.SkillRequirement;
import skillsplanner.io.IOHandler;
import skillsplanner.resources.ClassManager;
import skillsplanner.resources.SkillManager;

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
			ClassManager.getInstance().addClass(dfoclass);
			
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
	
	private Document buildSkillDocument(Skill st) {
		Document d = new Document();
		Element rootElement = new Element("skillsplanner");
		Element skillElement = new Element("skill");
		Element name = new Element("name");
		Element casttime = new Element("casttime");
		Element spcost = new Element("spcost");
		Element entrycost = new Element("entrycost");
		Element description = new Element("description");
		Element dynamic = new Element("dynamic");
		Element requiredLevel = new Element("reqlevel");
		
		for(int i = 0; i < st.getMaxLevel(); i++){
			Element level = new Element("level");
			level.setAttribute("value",String.valueOf(i+1));
			dynamic.addContent(level);
		}
		
		name.addContent(st.getName());
		casttime.addContent(String.valueOf(st.getCastTime()));
		spcost.addContent(String.valueOf(st.getSpcost()));
		entrycost.addContent(String.valueOf(st.getEntrycost()));
		description.addContent("");
		requiredLevel.addContent(String.valueOf(st.getRequiredlevel()));
		
		
		skillElement.addContent(name);
		skillElement.addContent(casttime);
		skillElement.addContent(spcost);
		skillElement.addContent(entrycost);
		skillElement.addContent(requiredLevel);
		skillElement.addContent(description);
		
		if(st.getSkillRequirements() != null){
			for(SkillRequirement r:  st.getSkillRequirements()){
				Element req = new Element("skillreq");
				req.setAttribute("name",r.getName());
				req.setAttribute("level",String.valueOf(r.getLevel()));
				skillElement.addContent(req);
			}
		}
		
		skillElement.addContent(dynamic);
		
		rootElement.addContent(skillElement);
		
		d.setRootElement(rootElement);
		return d;
	}

	public boolean skillToXML(Skill skill, String folder) {
		XMLOutputter outputter = new XMLOutputter();
		
		try{
			Document doc = buildSkillDocument(skill);
			Format format = Format.getPrettyFormat();
			outputter.setFormat(format);
			outputter.output(doc, System.out);
			outputter.output(doc, IOHandler.getSkillOutputter(skill,folder));
			SkillManager.getInstance().addSkill(skill);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}
