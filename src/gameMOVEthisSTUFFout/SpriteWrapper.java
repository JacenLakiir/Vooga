/**
 * @author Michael Zhou (Dominator008)
 */
package gameMOVEthisSTUFFout;

import leveleditor.VoogaUtilities;
import com.golden.gamedev.object.Sprite;

public class SpriteWrapper implements Comparable<SpriteWrapper> {
    
    private String myName;
    private String myImagesrc;
    private Sprite mySprite;
    
    public SpriteWrapper(Sprite sprite, String name, String imgsrc) {
	myName = name;
	mySprite = sprite;
	myImagesrc = imgsrc;
	reconstruct();
    }
    
    public SpriteWrapper(SpriteWrapper another) {
	myName = another.myName;
	myImagesrc = another.myImagesrc;
	mySprite = new Sprite();
	reconstruct();
    }
    
    public String getName() {
	return myName;
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

    public int compareTo(SpriteWrapper o) {
	return myName.compareTo(o.myName);
    }
    
}