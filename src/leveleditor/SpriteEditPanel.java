package leveleditor;

import javax.swing.JFrame;

public class SpriteEditPanel extends JFrame {
    
    private static SpriteEditPanel myInstance;
    
    public static SpriteEditPanel getInstance(VoogaLevelEditorModel model, 
	    VoogaLevelEditor view, VoogaLevelEditorController controller) {
	if (myInstance != null)
	    myInstance.dispose();
	myInstance = new SpriteEditPanel(model, view, controller);
	myInstance.setVisible(true);
	return myInstance;
    }
    
    private SpriteEditPanel(VoogaLevelEditorModel model, VoogaLevelEditor view, 
	    VoogaLevelEditorController controller) {
	setSize(300, 200);
	setLocationRelativeTo(view);
	setTitle("Edit Sprite");
    }
    
}
