/**
 * @author Michael Zhou (Dominator008)
 */
package editor;

import game.LevelState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.ImageIcon;

import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class VoogaUtilities {

    public static BufferedImage getImageFromString(String src) {
	BufferedImage toreturn = null;
	try {
	    toreturn = ImageUtil.getImage(new URL("file://" + 
	    new File(src).getCanonicalPath()), Color.MAGENTA);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return toreturn;
    }
    
    private static String readJsonFile(File file) {
	Scanner in = null;
	try {
	    in = new Scanner(new FileInputStream(file)).useDelimiter("\\Z+");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return in.next();
    }
    
    public static LevelState loadLevel(File file) {
	Gson gson = new Gson();
	LevelState toreturn = gson.fromJson(readJsonFile(file), LevelState.class);
	toreturn.reconstruct();
	return toreturn;
    }
    public static void saveLevel(LevelState state, String URL) {
	Gson gson = new GsonBuilder().setPrettyPrinting()
		.enableComplexMapKeySerialization().create();
	try {
	    BufferedWriter out = new BufferedWriter(new FileWriter(URL));
	    gson.toJson(state, out);
	    out.close();
	} catch (JsonIOException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	    }
	    image = new ImageIcon(image).getImage();
	    boolean hasAlpha = hasAlpha(image);
	    BufferedImage bimage = null;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        int transparency = Transparency.OPAQUE;
	        if (hasAlpha) {
	            transparency = Transparency.BITMASK;
	        }
	        GraphicsDevice gs = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gs.getDefaultConfiguration();
	        bimage = gc.createCompatibleImage(
	            image.getWidth(null), image.getHeight(null), transparency);
	    } catch (HeadlessException e) {}
	    if (bimage == null) {
	        int type = BufferedImage.TYPE_INT_RGB;
	        if (hasAlpha) {
	            type = BufferedImage.TYPE_INT_ARGB;
	        }
	        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	    }
	    Graphics g = bimage.createGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    return bimage;
	}
    
    public static boolean hasAlpha(Image image) {
	    if (image instanceof BufferedImage) {
	        BufferedImage bimage = (BufferedImage)image;
	        return bimage.getColorModel().hasAlpha();
	    }
	    PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
	    try {
	        pg.grabPixels();
	    } catch (InterruptedException e) {
	    }
	    ColorModel cm = pg.getColorModel();
	    return cm.hasAlpha();
	}
    
}