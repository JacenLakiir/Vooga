/**
 * @author Michael Zhou (Dominator008)
 */
package game;

import levelEditor.VoogaUtilities;
import com.golden.gamedev.object.Sprite;

public class SpriteWrapper {
    
    private String myImagesrc;
    private Sprite mySprite;
    
    public SpriteWrapper(Sprite sprite, String imgsrc) {
	mySprite = sprite;
	myImagesrc = imgsrc;
	reconstruct();
    }
    
    public String getImageSrc() {
	return myImagesrc;
    }
    
    public Sprite getSprite() {
	return mySprite;
    }
    
    public void reconstruct() {
	mySprite.setImage(VoogaUtilities.getImageFromString(myImagesrc));
    }
    
}