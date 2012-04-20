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

public class GameElementWrapper implements Cloneable {
    
    private String myName;
    private Class<?> myClass;
    private String myImagesrc;
    private GameElement myGameElement;
    
    public GameElementWrapper(String name, Class<?> clazz, String imgsrc) {
	myName = name;
	myClass = clazz;
	//myGameElement = element;
	myImagesrc = imgsrc;
	reconstruct();
    }
    
    public GameElementWrapper clone() {
	GameElementWrapper cloned = new GameElementWrapper(myName, myClass, myImagesrc);
    }
    
    public String getName() {
	return myName;
    }
    
    public String getImageSrc() {
	return myImagesrc;
    }
    
    public GameElement getGameElement() {
	return myGameElement;
    }
    
    public void reconstruct() {
	myGameElement.setImage(VoogaUtilities.getImageFromString(myImagesrc));
    }

//    public int compareTo(SpriteWrapper o) {
//	return myName.compareTo(o.myName);
//    }
    
/*    public boolean equals(Object o) {
	SpriteWrapper another = (SpriteWrapper) o;
	AdvanceSprite anothersprite = another.mySprite;
	Point2D p = new Point2D.Double(mySprite.getX(), mySprite.getY());
	Point2D anotherp = new Point2D.Double(anothersprite.getX(), anothersprite.getY());
	return myName.equals(another.myName) && p.equals(anotherp) 
		&& mySprite.getLayer() == anothersprite.getLayer();
    }*/
    
//    public boolean equals(Object o) {
//	SpriteWrapper another = (SpriteWrapper) o;
//	return myName.equals(another.myName);
//    }
    
    public void save(String path) {
	
    }
    
}