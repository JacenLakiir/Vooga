package leveleditor;

import java.io.File;
import java.io.IOException;

public class VoogaLevelEditorController {

    private VoogaLevelEditor myView;
    private VoogaLevelEditorModel myModel;
    
    public VoogaLevelEditorController() {
	myModel = new VoogaLevelEditorModel();
	myView = new VoogaLevelEditor(myModel, this);
    }
    
    protected void initialize(File file) {
	myModel.loadLevel(file);
	myView.setUpBackground(myModel.myLevelState.myBackGroundSrc);
	myView.loadSprites(myModel.myLevelState.mySpriteMap);
    }
    
    protected void export(File file) {
	try {
	    myView.prepareForSave(file.getCanonicalPath());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public static void main(String[] args) {
	new VoogaLevelEditorController();
    }
    
}