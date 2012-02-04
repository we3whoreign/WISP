package skillsplanner.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import skillsplanner.io.IOHandler;

/**
 * Manager for image files. Loads them on demand from file system resources rather than storing in memory 
 * as objects like SkillManager and ClassManager. An instance doesn't need to be made because of the lack of resources being stored.
 * @author ryzngard
 *
 */
public class ImageManager {
	
	private static HashMap<String,Image> imageCache = new HashMap<String,Image>();
	
	private static final String[] FILETYPES = new String[]{
			".jpg",".png",".jpeg",".gif"
	};
	
	/**
	 * In order to speed up processing, cache images as they are used. Possibly implement some sort of resouce
	 * cleanup, but for now this should be fine on memory.
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static Image getImage(String name) throws IOException{
		name = nameToFileName(name);
		if(imageCache.containsKey(name)){
			return imageCache.get(name);
		}
		
		InputStream stream = IOHandler.getImageWithName(name);
		if(stream == null){
			stream = IOHandler.getImageWithName("default.png");
		}
		Image image = ImageIO.read(stream);
		imageCache.put(name, image);
		return image;
	}
	
	private static String nameToFileName(String name){
		name = name.replaceAll("[\\s,-,:]", "_");
		name = name.replaceAll("'", "");
		name = name.toLowerCase();
		name = "/"+name;
		return name;
	}
}
