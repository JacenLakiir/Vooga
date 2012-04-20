/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.golden.gamedev.object.SpriteGroup;

public class LevelState implements Serializable {
    private static final long serialVersionUID = 3910981230998281239L;
    private String myBackgroundSrc;
    private ArrayList<GameElementWrapper> myPlayers;
    private ArrayList<GameElementWrapper> myCharacters;
    private ArrayList<GameElementWrapper> mySettings;
    private ArrayList<GameElementWrapper> myItems;
    
    public LevelState(String background, HashMap<Point, GameElementWrapper> spritemap) {
	//mySpriteMap = new HashMap<Point, SpriteWrapper>(spritemap);
	myBackgroundSrc = background;
    }

    public String getBackgroundImageSrc() {
	return myBackgroundSrc;
    }

    
    public HashMap<Point, GameElementWrapper> getSpriteMap() {
	return mySpriteMap;
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
	for (GameElementWrapper sp : mySpriteMap.values())
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
