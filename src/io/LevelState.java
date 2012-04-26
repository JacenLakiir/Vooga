/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import leveleditor.VoogaUtilities;
import core.playfield.AdvancedPlayField;

public class LevelState implements Serializable {
    private static final long serialVersionUID = 3910981230998281239L;

    private String myBackgroundPath;
    private List<SpriteWrapper> mySprites;

    public LevelState(String backgroundPath, List<SpriteWrapper> sprites) {
	myBackgroundPath = backgroundPath;
	mySprites = new ArrayList<SpriteWrapper>(sprites);
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

}