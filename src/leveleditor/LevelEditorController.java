/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import io.LevelState;
import io.SpriteWrapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class LevelEditorController {

    private LevelEditor myView;

    public LevelEditorController() {
	myView = new LevelEditor(this);
    }

    protected void initialize(File file) {
	LevelState levelstate = LevelState.load(file);
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
	    tosave.saveAttributes();
	    sprites.add(tosave);
	}
	try {
	    LevelState lvl = new LevelState(canvas.getBackgroundImageSrc(),
		    sprites);
	    lvl.save(file.getCanonicalPath());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}