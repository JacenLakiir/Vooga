package game;

import java.awt.Point;
import java.util.*;

public class LevelState {
    public String myBackGroundSrc;
    public Map<Point, SpriteWrapper> mySpriteMap;
    
    public LevelState(String background, Map<Point, SpriteWrapper> spritemap) {
	mySpriteMap = new HashMap<Point, SpriteWrapper>(spritemap);
	myBackGroundSrc = background;
    }
    
    public void reconstruct() {
	for (SpriteWrapper sp: mySpriteMap.values())
	    sp.reconstruct();
    }
    
}