package org.we3whoreign.wisp.utils.jdom;

import java.io.File;
import java.io.FileWriter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.we3whoreign.wisp.beans.DFOClass;
import org.we3whoreign.wisp.beans.Skill;
import org.we3whoreign.wisp.resources.ClassManager;
import org.we3whoreign.wisp.resources.SkillManager;
import org.we3whoreign.wisp.resources.StaticResources;
import org.we3whoreign.wisp.utils.skills.SkillHandler;

public class BuildMapper {
	
	public static boolean createFileFromDFOClass(File save, DFOClass dfoclass){
		if(save == null || dfoclass == null){
			return false;
		}
		XMLOutputter outputter = new XMLOutputter();
		//fix the file to have .xml if it doesn't already
		if(!save.getName().toLowerCase().endsWith(".xml")){
			save = new File(save.getAbsolutePath()+".xml");
		}
		try{
			Document doc = createDocument(dfoclass);
			Format format = Format.getPrettyFormat();
			outputter.setFormat(format);
			outputter.output(doc, new FileWriter(save));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static Document createDocument(DFOClass dfoclass) {
		Document doc = new Document();
		Element rootElement = new Element(Handler.ROOT_ELEMENT);
		Element buildElement = new Element("build");
		Element classElement = new Element("class");
		classElement.setText(dfoclass.getUniqueName());
		
		buildElement.addContent(classElement);
		for(String skill : dfoclass.getSkills().keySet()){
			Element skillElement = new Element("skill");
			skillElement.setAttribute("name",skill);
			skillElement.setText(String.valueOf(dfoclass.getSkills().get(skill)));
			buildElement.addContent(skillElement);
		}
		
		rootElement.addContent(buildElement);
		doc.setContent(rootElement);
		return doc;
	}

	public static boolean openFile(File selectedFile) {
		DFOClass dfoclass;
		Document doc = Handler.openXMLFile(selectedFile);
		Element root = doc.getRootElement().getChild("build");
		
		//saves the builds as uniquenames, which are parent/class
		String string = root.getChildText("class");
		dfoclass = ClassManager.getClassByName(string);
		if(dfoclass == null){
			return false;
		}
		
		StaticResources.getCharacter().setDFOClass(dfoclass);
		
		//fix the skill levels
		for(Object obj : root.getChildren("skill")){
			Element e = (Element) obj;
			String name = e.getAttribute("name").getValue();
			int level = Integer.parseInt(e.getText());
			Skill sk = SkillManager.getInstance().getSkill(name);
			//level up
			SkillHandler.setLevel(sk,level);
		}
		
		return true;
	}

}
