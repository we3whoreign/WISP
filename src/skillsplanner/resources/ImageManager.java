package skillsplanner.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import skillsplanner.io.IOHandler;

/**
 * Manager for image files. Loads them on demand from file system resources rather than storing in memory 
 * as objects like SkillManager and ClassManager. An instance doesn't need to be made because of the lack of resources being stored.
 * @author ryzngard
 *
 */
public class ImageManager {
	
	private static final String[] FILETYPES = new String[]{
			".jpg",".png",".jpeg",".gif"
	};
	
	public static BufferedImage getImage(String name) throws IOException{
		name = nameToFileName(name);
		InputStream stream = IOHandler.getImageWithName(name);
		if(stream == null){
			stream = IOHandler.getImageWithName("default.png");
		}
		return ImageIO.read(stream);
	}
	
	private static String nameToFileName(String name){
		name = name.replaceAll("[\\s,-,:]", "_");
		name = name.replaceAll("'", "");
		name = name.toLowerCase();
		name = "/"+name;
		return name;
	}
}
