package leveleditor;
import game.LevelState;
import game.SpriteWrapper;

import java.awt.Point;
import java.io.File;
import java.util.*;

public class VoogaLevelEditorModel {

    protected LevelState myLevelState;
    
    public VoogaLevelEditorModel() {}
    
    protected void loadLevel(File file) {
	myLevelState = VoogaUtilities.loadLevel(file);
    }
    
    protected void saveLevel(String path, String backgroundimgsrc,
	    Map<Point, SpriteWrapper> spritemap) {
	LevelState level = new LevelState(backgroundimgsrc, spritemap);
	VoogaUtilities.saveLevel(level, path);
    }
    
}