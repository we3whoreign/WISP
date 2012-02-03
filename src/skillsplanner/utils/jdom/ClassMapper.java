package skillsplanner.utils.jdom;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.jdom.Document;
import org.jdom.Element;

import skillsplanner.beans.DFOClass;
import skillsplanner.resources.SkillManager;


public class ClassMapper {

	/**
	 * Functions like createClassFromFile, it gets the doc object and reads the xml to map it to a dfoclass object.
	 * @param resource
	 * @return
	 */
	public static DFOClass createClassFromStream(InputStream resource,String parent) {
		DFOClass dfoclass = new DFOClass();
		
		Document doc = Handler.openXMLFile(resource);

		Element root = doc.getRootElement();
		
		dfoclass.setDescription(root.getChildText("description"));
		
		dfoclass.setName(root.getChildText("name"));
		
		dfoclass.setBaseClass(parent);
		
		for(Object v : root.getChildren("skill")){
			Element e = (Element) v;
			try {
				dfoclass.addSkill(SkillManager.getInstance().getSkill(e.getValue()));
			} catch (Exception e1){}
 		}
		
		return dfoclass;
	}
	
	

}
