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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private static List<File> skillArray;
	private static List<File> classArray;

	/**
	 * By default all paths have been made using linux conventions, at least all
	 * the hardcoded paths. In order to convert them for portability this method
	 * replaces all '/' with File.seperator then returns a URL, based on the Launcher
	 * class, to allow access within the jar file
	 */
	public static URL makePath(String path) {

		//Make the path go up a directory since we are using skillsplanner.Launcher for reference
		path = "../"+path;
		
		//Make the path OS independent
		path = path.replace("/", File.separator);
		path = path.replace("\\", File.separator);
		
		System.out.println(path);
		System.out.println(Launcher.class.getResource(path));
		
		return Launcher.class.getResource(path);
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
	public static List<File> getDFOClassFiles() throws URISyntaxException, Exception{
		if(classArray != null){
			return classArray;
		}
		
		URL path = makePath("libs/classes");
		
		classArray = traverseDirectoryForXML(new File(path.toURI()));
		
		return classArray;
	}
	
	/**
	 * Gets a list of all .xml files in the libs/skills directory.
	 * @return
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static List<File> getSkillFiles() throws URISyntaxException, Exception{
		if(skillArray != null){
			return skillArray;
		}
		URL path = makePath("libs/skills");
		
		skillArray = traverseDirectoryForXML(new File(path.toURI()));
		
		return skillArray;
	}
	
	/**
	 * traverse a directory looking for XML files to load
	 */
	public static List<File> traverseDirectoryForXML(File file) throws Exception {
		if (file == null) {
			throw new FileNotFoundException();
		} else if (!file.isDirectory()) {
			// we can't traverse this
			
			if(file.getName().endsWith(".xml") || file.getName().endsWith(".XML")){
				List<File> l = new ArrayList<File>();
				l.add(file);
				return l;
			}
			else{
				return new ArrayList<File>();
			}
		}

		String files[] = file.list();

		List<File> xmlist = new ArrayList<File>();
		
		if (files == null) {
			return xmlist;
		}

		for (int i = 0; i < files.length; i++) {
			File f = new File(file.getPath() + File.separator + files[i]);
			xmlist.addAll(traverseDirectoryForXML(f));
		}
		
		return xmlist;
	}

}
