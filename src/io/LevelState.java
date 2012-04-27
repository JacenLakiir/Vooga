/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package io;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import leveleditor.VoogaUtilities;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.background.ParallaxBackground;

public class LevelState implements Serializable {
    private static final long serialVersionUID = 3910981230998281239L;

    private String myBackgroundPath;
    private Background myBackground;
    private List<SpriteWrapper> mySprites;

    public LevelState(String backgroundPath, List<SpriteWrapper> sprites) {
	myBackgroundPath = backgroundPath;
	mySprites = new ArrayList<SpriteWrapper>(sprites);
	reconstruct();
    }
    
    public void save(String path) {
	VoogaUtilities.serialize(this, new File(path));
    }

    public static LevelState load(File file) {
	return (LevelState) VoogaUtilities.deserialize(file);
    }
    
    public List<SpriteWrapper> getSprites() {
	return mySprites;
    }
    
    public String getBackgroundImageSrc() {
	return myBackgroundPath;
    }
    
    public Background getBackground() {
	return myBackground;
    }
    
    private void reconstruct() {
	Background[] bkg = new Background[1];
	bkg[0] = new ImageBackground(VoogaUtilities.getImage(myBackgroundPath));
	myBackground = new ParallaxBackground(bkg);
    }
    
    private void readObject(ObjectInputStream stream) 
	    throws ClassNotFoundException, IOException {
	stream.defaultReadObject();
	reconstruct();
    }
    
}