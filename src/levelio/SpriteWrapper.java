/**
 * @author Michael Zhou (Dominator008)
 */
package levelio;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;

import leveleditor.VoogaUtilities;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;

import core.characters.GameElement;

public class SpriteWrapper implements Comparable<SpriteWrapper> {
    
    private String myName;
    private Class<?> myClass;
    private String myImagesrc;
    private HashMap<String, Object> myAttributes;
    private AdvanceSprite mySprite;
    
    public SpriteWrapper(String name, Class<?> clazz, String imgsrc) {
	myName = name;
	myClass = clazz;
	//myGameElement = element;
	myImagesrc = imgsrc;
	myAttributes = new HashMap<String, Object>();
	reconstruct();
    }
    
    public SpriteWrapper(SpriteWrapper another) {
	myName = another.myName;
	myClass = another.myClass;
	myImagesrc = another.myImagesrc;
	mySprite = new AdvanceSprite();
	reconstruct();
    }
    
    public String getName() {
	return myName;
    }
    
    public String getImageSrc() {
	return myImagesrc;
    }
    
    public AdvanceSprite getSprite() {
	return mySprite;
    }
    
    public void reconstruct() {
	mySprite.setImage(VoogaUtilities.getImageFromString(myImagesrc));
    }

    public int compareTo(SpriteWrapper o) {
	return myName.compareTo(o.myName);
    }
    
/*    public boolean equals(Object o) {
	SpriteWrapper another = (SpriteWrapper) o;
	AdvanceSprite anothersprite = another.mySprite;
	Point2D p = new Point2D.Double(mySprite.getX(), mySprite.getY());
	Point2D anotherp = new Point2D.Double(anothersprite.getX(), anothersprite.getY());
	return myName.equals(another.myName) && p.equals(anotherp) 
		&& mySprite.getLayer() == anothersprite.getLayer();
    }*/
    
    public boolean equals(Object o) {
	SpriteWrapper another = (SpriteWrapper) o;
	return myName.equals(another.myName);
    }
    
    public int hashCode() {
	return myName.hashCode() << 1 + 
		new Point2D.Double(mySprite.getX(), mySprite.getY()).hashCode();
    }
    
    public void save(String path) {
	
    }
    
}