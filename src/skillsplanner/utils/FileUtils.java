package skillsplanner.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarFile;

import javax.swing.ImageIcon;

import skillsplanner.Launcher;

/**
 * A set of utilities specific to handling files
 */
public class FileUtils {
	
	/**
	 * By keeping a static list of all the files we can avoid having
	 * to search the directory each time, reducing read/write
	 */
	private static List<Object> skillArray;
	private static List<Object> classArray;
	
	public static String CLASSES_PATH = "../libs/classes";
	public static String SKILLS_PATH = "../libs/skills";
	
	public static boolean isJar;

	/**
	 * By default all paths have been made using linux conventions, at least all
	 * the hardcoded paths. In order to convert them for portability this method
	 * replaces all '/' with File.seperator then returns a URL, based on the Launcher
	 * class, to allow access within the jar file
	 */
	public static URL makePath(String path) {

		//Make the path go up a directory since we are using skillsplanner.Launcher for reference
		//path = "../"+path;
		//edited, changed the defaults to included ../
		
		//Make the path OS independent
		path = path.replace("//",FileUtils.getSeparator());
		path = path.replace("/", FileUtils.getSeparator());
		path = path.replace("\\", FileUtils.getSeparator());
		
		return Launcher.class.getResource(path);
	}
	
	public static InputStream getInputStream(String path){
		//path = "../"+path;
		
		path = path.replaceAll("/", File.separator);
		path = path.replaceAll("\\", File.separator);
		
		return Launcher.class.getResourceAsStream(path);
	}

	/**
	 * resizes an image into Dimension d
	 */
	public static BufferedImage resizeImage(BufferedImage image, int width,
			int height) {
		if (image == null) {
			return null;
		}

		BufferedImage resizedImage = new BufferedImage(width, height,
				image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
						: image.getType());

		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	/**
	 * Converts an Image to BufferedImage source:
	 * http://www.exampledepot.com/egs/java.awt.image/Image2Buf.html
	 */
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see Determining If an Image Has Transparent Pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	/**
	 * returns true if the specified image has transparent pixels
	 */
	public static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}

		// Get the image's color model
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}
	
	/**
	 * Gets a list of all the .xml files in the libs/classes directory.
	 * @return list of xml files for 
	 * @throws Exception 
	 * @throws URISyntaxException 
	 */
	public static List<Object> getDFOClassFiles() throws URISyntaxException, Exception{
		if(classArray != null){
			return classArray;
		}
		if(!isJar){
			URL path = makePath(CLASSES_PATH);
		
			classArray = traverseDirectoryForXML(new File(path.toURI()));
		
			return classArray;
		}
		else{
			return JarUtils.getDFOClassFiles();
		}
	}
	
	/**
	 * Gets a list of all .xml files in the libs/skills directory.
	 * @return
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static List<Object> getSkillFiles() throws URISyntaxException, Exception{
		if(skillArray != null && !isJar){
			return skillArray;
		}
		else if(!isJar){
			URL path = makePath(SKILLS_PATH);
			
			if(path != null){
				skillArray = traverseDirectoryForXML(new File(path.toURI()));
			}
			else{
				skillArray = traverseDirectoryForXML(new File(makeStringPath(SKILLS_PATH)));
			}
			
			return skillArray;
		}
		else{
			return null;
		}
	}
	
	private static String makeStringPath(String path) {
		// TODO Auto-generated method stub
		path = path.replace("//", FileUtils.getSeparator());
		path = path.replace("/", FileUtils.getSeparator());
		path = path.replace("\\", FileUtils.getSeparator());
		
		return path;
	}

	/**
	 * traverse a directory looking for XML files to load
	 */
	public static List<Object> traverseDirectoryForXML(File file) throws Exception {
		if (file == null || !file.exists()) {
			throw new FileNotFoundException();
		} else if (!file.isDirectory()) {
			// we can't traverse this
			
			if(file.getName().endsWith(".xml") || file.getName().endsWith(".XML")){
				List<Object> l = new ArrayList<Object>();
				l.add(file);
				return l;
			}
			else{
				return new ArrayList<Object>();
			}
		}

		String files[] = file.list();

		List<Object> xmlist = new ArrayList<Object>();
		
		if (files == null) {
			return xmlist;
		}

		for (int i = 0; i < files.length; i++) {
			File f = new File(file.getPath() + File.separator + files[i]);
			xmlist.addAll(traverseDirectoryForXML(f));
		}
		
		return xmlist;
	}
	
	/**
	 * Capitilizes the first letter of every word in the input string.
	 * Obsolete, migrated to StringUtils
	 * @param input
	 * @return
	 */
	public static String firstLetterCap(String input){
		
		return StringUtils.toCamelCase(input);
		/**if(input == null){
			return input;
		}
		
		StringTokenizer st = new StringTokenizer(input);
		
		input = "";
		
		while(st.hasMoreTokens()){
			String temp = st.nextToken();
			input += temp.substring(0,1).toUpperCase() + temp.substring(1).toLowerCase() + " ";
		}
		
		return input;**/
	}
	
	public static String getParentDir(File f){
		String path = f.getAbsolutePath();
		
		path = path.substring(0,path.lastIndexOf(File.separator));
		path = path.substring(path.lastIndexOf(File.separator)+1).replace("_", " ");
		
		return path;
	}
	
	public static String getParentDir(String path){
		path = path.substring(0,path.lastIndexOf(JarUtils.getSeparator()));
		path = path.substring(path.lastIndexOf(JarUtils.getSeparator())+1);
		
		return path;
	}

	/**
	 * Determins if the path points to a class file. Prepends the CLASSES_PATH to the beginning and appends .xml
	 * to the end.
	 * @param path
	 * @return
	 */
	public static boolean isClassFile(String path) {
		if(isJar){
			return JarUtils.isClassFile(path);
		}
		
		URL url = makePath(CLASSES_PATH + "/" + path + ".xml");
		try {
			if(url != null && new File(url.toURI()).isFile()){
				return true;
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static List<String> getSubclasses(String path) {
		System.out.println(path);
		if(isJar){
			return JarUtils.getSubclasses(path);
		}
		
		URL url = makePath(SKILLS_PATH + "/" + path);
		
		if(url == null){
			return null;
		}
		
		try {
			File f = new File(url.toURI());
			ArrayList<String> ret = new ArrayList<String>();
			
			for(File child : f.getParentFile().listFiles()){
				if(child.isDirectory()){
					ret.add(child.getName());
				}
			}
			
			return ret;
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getSeparator() {
		if(isJar){
			return JarUtils.getSeparator();
		}
		
		return File.separator;
	}

	/**
	 * Take in string path, held in the JTRee for class selection, and translates it into the string that can be loaded
	 * @param path
	 * @return
	 */
	public static String getDFOClass(String path) {
		if(!isJar){
			path = path.substring(path.lastIndexOf(JarUtils.getSeparator())+1,path.length());
			path+=".xml";
			return path;
		}
		else{
			return path;
		}
	}

	/**
	 * Parses a URI for the filename with extensions
	 * @param baseURI
	 * @return
	 */
	public static String getFileName(String baseURI) {
		return baseURI.substring(baseURI.lastIndexOf(JarUtils.getSeparator())+1);
	}

}
