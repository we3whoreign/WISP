package skillsplanner.testing;

import java.net.URISyntaxException;

import skillsplanner.skills.SkillsTemplate;
import skillsplanner.utils.jdom.DFOClassLoader;
import skillsplanner.utils.jdom.SkillLoader;

public class CLITest {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException, Exception {
		System.out.println("STARTING TESTING!");
		
		SkillLoader loader = new SkillLoader();
		
		// using the lass loader we can achieve optimum male to female ratio
		// too many lasses and you have a horrible experience, too little
		// and the penises take over. Let the lass loader be known as one
		// of the greatest tools mankind has ever known.
		DFOClassLoader classloader = new DFOClassLoader();
		
		loader.loadSkills();
		
		String[] skilllist = loader.getSkillList();
		
		System.out.println("Dumping skills...");
		
		for(int i = 0; i < skilllist.length; i++){
			SkillsTemplate st = loader.getSkill(skilllist[i]);
			
			System.out.println("Name: "+st.getName());
			System.out.println("Cast Time: "+st.getCastTime());
			System.out.println("Entry Cost: "+st.getEntrycost());
			st.dumpLevelInfo();
		}

	}

}
