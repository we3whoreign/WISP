import Skills.*;
import java.util.Map;

class main{

	public static void main(String args[]) throws Exception{
		SkillsLoader sk = new SkillsLoader();
		Map<String,SkillsTemplate> m = sk.getClassObjects();
		
		for(String s : m.keySet()){
			System.out.println(s + ":"+m.get(s));
			System.out.println(m.get(s).getName());
		}
	}

}
