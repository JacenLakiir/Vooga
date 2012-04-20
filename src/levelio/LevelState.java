/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelState implements Serializable {
    private static final long serialVersionUID = 3910981230998281239L;
    private String myBackgroundSrc;
    private List<SpriteWrapper> mySprites;
    
    public LevelState(String background, List<SpriteWrapper> sprites) {
	mySprites = new ArrayList<SpriteWrapper>(sprites);
	myBackgroundSrc = background;
    }
    
    public void setBackgroundImageSrc(String src) {
	myBackgroundSrc = src;
    }
    
    public String getBackgroundImageSrc() {
	return myBackgroundSrc;
    }
    
    public List<SpriteWrapper> getSprites() {
	return mySprites;
    }

    public static LevelState loadLevel(File file) {
	LevelState level = null;
	try {
	    ObjectInputStream in = new ObjectInputStream(new FileInputStream(
		    file));
	    level = (LevelState) in.readObject();
	    level.reconstruct();
	    in.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return level;
    }

    private void reconstruct() {
	for (SpriteWrapper sp : mySprites)
	    sp.reconstruct();
    }

    public void save(String url) {
	try {
	    ObjectOutputStream out = new ObjectOutputStream(
		    new FileOutputStream(url));
	    out.writeObject(this);
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
}