package skilldumper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import skillsplanner.utils.*;

public class SkillDumper {
	public static final String DIR = "./libs/skills";
	
	public static List<File> getDirs(File f){
		File[] sublist = f.listFiles();
		
		List<File> ret = new ArrayList<File>();
		for(int i = 0; i < sublist.length; i++){
			if(sublist[i].isDirectory()){
				ret.add(sublist[i]);
			}
		}
		
		return ret;
	}
	
	public static void listfiles(File f){
		System.out.println("\n<!--"+f.getName().toUpperCase()+"-->\n");
		File[] flist = f.listFiles();
		
		for(int i = 0; i < flist.length; i++){
			if(flist[i].isDirectory()){
				listfiles(flist[i]);
			}
			else if(flist[i].getName().toLowerCase().endsWith(".xml")){
				System.out.println(flist[i].getName().substring(0,flist[i].getName().length()-4));
			}
		}
	}
	public static void main(String args[]) throws URISyntaxException, Exception{
		//FileUtils utils = new FileUtils();
		//URL u = utils.makePath(DIR);
		
		//List<File> subdirs = getDirs(utils.getFile(DIR));//new File(u.toURI()));
		
		//for(File dir : subdirs){
		//	listfiles(dir);
		//}
	
	}

}
