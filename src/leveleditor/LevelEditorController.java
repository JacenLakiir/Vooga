/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.io.File;
import java.io.IOException;

import levelio.LevelState;

public class LevelEditorController {

    private LevelEditor myView;
    private LevelState myLevelState;
    
    public LevelEditorController() {
	myView = new LevelEditor(this);
    }
    
    protected void initialize(File file) {
	myLevelState = LevelState.loadLevel(file);
	myView.getCanvas().setUpBackground(myLevelState.getBackgroundImageSrc());
	myView.loadSprites(myLevelState.getSpriteMap());
    }
    
    protected void export(File file) {
	try {
	    myView.prepareForSave(file.getCanonicalPath());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
}