/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.awt.Color;
import java.awt.Graphics;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import com.golden.gamedev.util.ImageUtil;

public class VoogaUtilities {
    
    public static BufferedImage getImage(String src) {
	BufferedImage toreturn = null;
	try {
	    toreturn = ImageUtil.getImage(new File(src).toURI().toURL(),
		    Color.MAGENTA);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return toreturn;
    }

    public static BufferedImage[] getImages(String src, int row, int col) {
	BufferedImage[] toreturn = null;
	try {
	    toreturn = ImageUtil.getImages(new File(src).toURI().toURL(), row, col, 
		    Color.MAGENTA);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	}
	return toreturn;
    }
    
    public static BufferedImage toBufferedImage(Image image) {
	if (image instanceof BufferedImage) {
	    return (BufferedImage) image;
	}
	image = new ImageIcon(image).getImage();
	boolean hasAlpha = hasAlpha(image);
	BufferedImage bimage = null;
	GraphicsEnvironment ge = GraphicsEnvironment
		.getLocalGraphicsEnvironment();
	try {
	    int transparency = Transparency.OPAQUE;
	    if (hasAlpha) {
		transparency = Transparency.BITMASK;
	    }
	    GraphicsDevice gs = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gs.getDefaultConfiguration();
	    bimage = gc.createCompatibleImage(image.getWidth(null),
		    image.getHeight(null), transparency);
	} catch (HeadlessException e) {
	}
	if (bimage == null) {
	    int type = BufferedImage.TYPE_INT_RGB;
	    if (hasAlpha) {
		type = BufferedImage.TYPE_INT_ARGB;
	    }
	    bimage = new BufferedImage(image.getWidth(null),
		    image.getHeight(null), type);
	}
	Graphics g = bimage.createGraphics();
	g.drawImage(image, 0, 0, null);
	g.dispose();
	return bimage;
    }

    public static boolean hasAlpha(Image image) {
	if (image instanceof BufferedImage) {
	    BufferedImage bimage = (BufferedImage) image;
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
    
    public static Object deserialize(File file) {
	Object res = null;
	try {
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream(
		    file));
	    res = in.readObject();
	    in.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return res;
    }
    
    public static void serialize(Object o, File file) {
	try {
	    ObjectOutputStream out = new ObjectOutputStream(
		    new FileOutputStream(file));
	    out.writeObject(o);
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
}