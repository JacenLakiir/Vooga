/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTree;

import levelio.LevelState;
import levelio.SpriteWrapper;

public class LevelEditorController {

    private LevelEditor myView;

    public LevelEditorController() {
	myView = new LevelEditor(this);
    }

    protected void initialize(File file) {
	LevelState levelstate = LevelState.loadLevel(file);
	myView.getCanvas().setUpBackground(levelstate.getBackgroundImageSrc());
	myView.loadSprites(levelstate.getSprites());
    }

    protected void export(File file) {
	Canvas canvas = myView.getCanvas();
	if (canvas.getBackgroundImageSrc() == null)
	    return;
	List<SpriteWrapper> sprites = new ArrayList<SpriteWrapper>();
	for (JLabel l : canvas.getLabelWrapperMap().keySet()) {
	    SpriteWrapper tosave = canvas.getLabelWrapperMap().get(l).clone();
	    tosave.getGameElement().setX(l.getX());
	    tosave.getGameElement().setY(l.getY());
	    sprites.add(tosave);
	}
	try {
	    new LevelState(canvas.getBackgroundImageSrc(), sprites).save(file
		    .getCanonicalPath());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
