/**
 * @author Michael Zhou (Dominator008)
 */
package levelio;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import leveleditor.VoogaUtilities;

import core.characters.GameElement;

public class SpriteWrapper implements Cloneable, Serializable {
    
    private static final long serialVersionUID = 8194097148022115858L;

    public static enum SpriteGroupIdentifier {
	PLAYER, CHARACTER, SETTING, ITEM
    }
    
    private String myName;
    private SpriteGroupIdentifier myGroup;
    private Class<?> myClass;
    private String myImagesrc;
    private Map<SpriteAttribute, Object> myAttributeMap;
    private GameElement myGameElement;
    
    public SpriteWrapper(String name, SpriteGroupIdentifier group, Class<?> clazz, String imgsrc) {
	myName = name;
	myGroup = group;
	myClass = clazz;
	myImagesrc = imgsrc;
	myGameElement = new GameElement();
	myAttributeMap = new HashMap<SpriteAttribute, Object>();
	Field[] allfields = clazz.getDeclaredFields();
	for (Field f: allfields) {
	    f.setAccessible(true);
	    if (f.isAnnotationPresent(Modifiable.class)) {
		String fname = f.getName();
		Class<?> ftype = f.getType();
		String fclassification = 
			((Modifiable) f.getAnnotation(Modifiable.class)).classification();
		myAttributeMap.put(new SpriteAttribute(fname, ftype, fclassification), new Object());

	    }
	}
	reconstruct();
    }
    
    public SpriteWrapper clone() {
	SpriteWrapper cloned = new SpriteWrapper(myName, myGroup, myClass, myImagesrc);
	cloned.myAttributeMap = new HashMap<SpriteAttribute, Object>(myAttributeMap);
	return cloned;
    }
    
    public void updateAttributeMap(SpriteAttribute sa, Object o) {
	myAttributeMap.put(sa, o);
    }
    
    public String getName() {
	return myName;
    }
    
    public String getImageSrc() {
	return myImagesrc;
    }
    
    public SpriteGroupIdentifier getGroup() {
	return myGroup;
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