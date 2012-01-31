package ssc.mapper;

import org.jdom.Document;
import org.jdom.Element;

import skillsplanner.skills.SkillsTemplate;

public class SkillMapper {

	public static Document makeDocument(SkillsTemplate st) {
		Document d = new Document();
		Element rootElement = new Element("skillsplanner");
		Element skillElement = new Element("skill");
		Element name = new Element("name");
		Element casttime = new Element("casttime");
		Element spcost = new Element("spcost");
		Element entrycost = new Element("entrycost");
		Element description = new Element("description");
		Element dynamic = new Element("dynamic");
		
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
		
		
		skillElement.addContent(name);
		skillElement.addContent(casttime);
		skillElement.addContent(spcost);
		skillElement.addContent(entrycost);
		skillElement.addContent(description);
		skillElement.addContent(dynamic);
		
		rootElement.addContent(skillElement);
		
		d.setRootElement(rootElement);
		return d;
	}

}
