<<<<<<< HEAD
<<<<<<< HEAD
import SkillsPlanner.*;

import java.io.File;
=======
import Skills.*;
import java.util.Map;
>>>>>>> 459f517d100c8b53434791ad5cd4be0282b48bb9
=======
import Skills.*;
import java.util.Map;
>>>>>>> 459f517d100c8b53434791ad5cd4be0282b48bb9

class main{

	public static void main(String args[]) throws Exception{
<<<<<<< HEAD
<<<<<<< HEAD
		Launcher launcher = new Launcher();
			
=======
=======
>>>>>>> 459f517d100c8b53434791ad5cd4be0282b48bb9
		SkillsLoader sk = new SkillsLoader();
		Map<String,SkillsTemplate> m = sk.getClassObjects();
		
		for(String s : m.keySet()){
			System.out.println(s + ":"+m.get(s));
			System.out.println(m.get(s).getName());
		}
<<<<<<< HEAD
>>>>>>> 459f517d100c8b53434791ad5cd4be0282b48bb9
=======
>>>>>>> 459f517d100c8b53434791ad5cd4be0282b48bb9
	}

}
