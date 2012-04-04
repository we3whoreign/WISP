package skillsplanner.utils.jdom;

import java.io.File;
import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import skillsplanner.beans.DFOClass;

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

}
