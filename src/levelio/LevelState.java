/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import leveleditor.VoogaUtilities;

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
    
    public void saveLevel(String path) {
	VoogaUtilities.serialize(path, this);
    }
    
    public static LevelState loadLevel(File file) {
	LevelState state = (LevelState) VoogaUtilities.deserialize(file);
	state.linkExternalResource();
	return state;
    }

    private void linkExternalResource() {
	for (SpriteWrapper sp : mySprites)
	    sp.linkExternalResource();
    }
    
}