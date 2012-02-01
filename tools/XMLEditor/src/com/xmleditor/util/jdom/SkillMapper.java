package com.xmleditor.util.jdom;

import java.io.InputStream;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;


import com.xmleditor.beans.Skill;

public class SkillMapper {
	
	public static Skill createSkillFromStream(InputStream is){
		Document doc = Handler.openXMLFile(is);
		
		String baseURI = doc.getBaseURI();
		
		System.out.println(baseURI);
		
		Element root = doc.getRootElement();
		
		Element skillattr = root.getChild("skill");
		
		Skill st = getSkillFromElement(skillattr,baseURI);

		
		return st;
		
	}
	/**
	 * Create a SkillsTemplate object from a jdom Element
	 * @param skillattr
	 * @return SkillsTemplate object relating to the xml 
	 */
	public static Skill getSkillFromElement(Element skillattr,String basename){
		
		Skill st = new Skill();
		
		st.setTree(basename);
		
		st.setAnimationFrameTime(Handler.getDouble(skillattr,"frametime"));
		
		st.setCastTime(Handler.getDouble(skillattr, "casttime"));
		
		st.setName(Handler.getString(skillattr, "name"));
		
		st.setSpcost(Handler.getInteger(skillattr,"spcost"));
		
		st.setEntrycost(Handler.getInteger(skillattr,"entrycost"));
		
		st.setMinLevel(st.getEntrycost() < 0 ? 1 : 0);
				
		//Gets and adds all relevant skill requirements
		for(Object req : skillattr.getChildren("skillreq")){
			st.addSkillRequirement(((Element)req).getAttributeValue("name"), Integer.parseInt(((Element)req).getAttributeValue("level")));
		}
		
		Element dynamic = skillattr.getChild("dynamic");
		
		//For each level
		for(Object o : dynamic.getChildren()){
			Element e = (Element) o;
			
			ArrayList<String> dynamiclist = new ArrayList<String>();
			
			//For each attribute for that level
			for(Object attr : e.getChildren()){
				Element eattr = (Element) attr;
				String temp = eattr.getAttributeValue("name")+": "+eattr.getAttributeValue("value");
				dynamiclist.add(temp);
			}
			
			st.addDynamic(dynamiclist);
		}
		
		return st;
	}

}
