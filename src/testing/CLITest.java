package testing;

import java.net.URISyntaxException;

import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.jdom.SkillLoader;

public class CLITest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException, Exception {
		SkillLoader loader = new SkillLoader();
		loader.loadSkills();
		
		String[] skilllist = loader.getSkillList();
		
		System.out.println("Dumping skills...");
		
		for(int i = 0; i < skilllist.length; i++){
			SkillsTemplate st = loader.getSkill(skilllist[i]);
			
			System.out.println("Name: "+st.getName());
			System.out.println("Cast Time: "+st.getCastTime());
			st.dumpLevelInfo();
		}

	}

}
