package skillsplanner.utils.jdom;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import skillsplanner.classes.DFOClass;
import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.FileUtils;

/**
 * Loads the class xml files.
 * Never create an instance of this, only use Handler.getClassLoader()
 * @author ryzngard
 *
 */
public class DFOClassLoader {
	
	private List<File> classfiles;
	
	public DFOClassLoader(){
		try {
			classfiles = FileUtils.getDFOClassFiles();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the DFOClass object associated with the given class
	 * @param dfoclass
	 * @return
	 */
	public DFOClass getClass(String dfoclass){
		for(File file : classfiles){
			String name = file.getName();
			
			if(name.equals(dfoclass)){
				return createClassFromFile(file);
			}
		}
		
		return null;
	}
	
	/**
	 * Creates a DFO Class from File
	 * @param f
	 * @return
	 */
	private DFOClass createClassFromFile(File f){
		DFOClass dfoclass = new DFOClass();
		
		Document doc = Handler.openXMLFile(f.getAbsolutePath());
		
		//Start the magic
		
		//cast summon imp
		Element root = doc.getRootElement();
		
		// and the imp sings:
		Element classattr = root.getChild("dfoclass");
		
		// Mama...... oooohhhoooooooooo
		dfoclass.setDescription(classattr.getChildText("description"));
		
		// I don't wanna diiiiieeeeeee
		dfoclass.setName(classattr.getChildText("name"));
		
		// if I don't come back this time tomorrow carry onnnnn....
		for(Object v : classattr.getChildren("skill")){
			// iterate through every skill tag getting the name
			Element e = (Element) v;
			SkillsTemplate st = getSkillFromAttribute(e.getAttribute("name"));
			dfoclass.addSkill(st);
		}
		// carry on...
		try{
			Element skillchange = root.getChild("skill");
			SkillLoader sl = new SkillLoader();
			sl.addSkill(sl.getSkillFromElement(skillchange));
		}
		catch(Exception e){}
		
		
		// 'cuz nothing really matters
		return dfoclass;
	}

	private SkillsTemplate getSkillFromAttribute(Attribute attribute) {
		String skillname = attribute.getValue();
		
		return SkillLoader.getSkill(skillname);
	}
	
	
}
