/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package io;

import io.annotations.Modifiable;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    private Map<SpriteAttribute, Serializable> myModifiableAttributeMap;
    private Map<SpriteAttribute, Serializable> myPhysicsAttributeMap;
    private Map<SpriteAttribute, Serializable> myGamePlayAttributeMap;
    
    private GameElement myGameElement;

    public SpriteWrapper(String name, SpriteGroupIdentifier group,
	    Class<?> clazz, Map<SpriteAttribute, Serializable> attributes,
	    String imgsrc) {
	System.out.println(attributes);
	myName = name;
	myGroup = group;
	myClass = clazz;
	myImagesrc = imgsrc;
	myGameElement = new GameElement();
	myPhysicsAttributeMap = new HashMap<SpriteAttribute, Serializable>(
		attributes);
	buildAttributeMap(clazz, null);
	reconstruct();
    }

    public SpriteWrapper(String name, SpriteGroupIdentifier group,
	    Map<SpriteAttribute, Serializable> attributes, String imgsrc,
	    GameElement sprite) {
	myName = name;
	myGroup = group;
	myClass = sprite.getClass();
	myImagesrc = imgsrc;
	myGameElement = sprite;
	myPhysicsAttributeMap = new HashMap<SpriteAttribute, Serializable>(
		attributes);
	buildAttributeMap(sprite.getClass(), sprite);
	reconstruct();
    }

    private void buildAttributeMap(Class<?> clazz, GameElement sprite) {
	myModifiableAttributeMap = new HashMap<SpriteAttribute, Serializable>();
	Set<Field> fieldset = new HashSet<Field>();
	Class<?> curr = clazz;
	while (true) {
	    for (Field f : curr.getDeclaredFields()) {
		f.setAccessible(true);
		if (f.isAnnotationPresent(Modifiable.class)) {
		    String fname = f.getName();
		    Class<?> ftype = f.getType();
		    String fclassification = ((Modifiable) f
			    .getAnnotation(Modifiable.class)).classification();
		    try {
			if (sprite != null)
			    myModifiableAttributeMap.put(new SpriteAttribute(
				    fname, ftype, fclassification),
				    (Serializable) f.get(sprite));
			else
			    myModifiableAttributeMap.put(new SpriteAttribute(
				    fname, ftype, fclassification), null);
		    } catch (IllegalArgumentException e) {
			e.printStackTrace();
		    } catch (IllegalAccessException e) {
			e.printStackTrace();
		    }
		    fieldset.add(f);
		}
	    }
	    if (curr.equals(GameElement.class))
		return;
	    curr = curr.getSuperclass();
	}
    }

    public SpriteWrapper clone() {
	SpriteWrapper cloned = new SpriteWrapper(myName, myGroup,
		myPhysicsAttributeMap, myImagesrc, myGameElement);
	cloned.myModifiableAttributeMap = new HashMap<SpriteAttribute, Serializable>(
		myModifiableAttributeMap);
	return cloned;
    }

    public void updateAttributeMap(SpriteAttribute sa, Serializable o) {
	myModifiableAttributeMap.put(sa, o);
    }

    public void updatePhysicsAttributeMap(SpriteAttribute sa, Serializable o) {
	myPhysicsAttributeMap.put(sa, o);
    }

    public Class<?> getSpriteClass() {
	return myClass;
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

    public Map<SpriteAttribute, Serializable> getAttributeMap() {
	return Collections.unmodifiableMap(myModifiableAttributeMap);
    }

    public Map<SpriteAttribute, Serializable> getPhysicsAttributeMap() {
	return Collections.unmodifiableMap(myPhysicsAttributeMap);
    }
    
    private void reconstruct() {
	BufferedImage[] dummyimages = new BufferedImage[1];
	dummyimages[0] = VoogaUtilities.getImage(myImagesrc);
	myGameElement.setImages(dummyimages);
    }
    
    private void readObject(ObjectInputStream stream) 
	    throws ClassNotFoundException, IOException {
	stream.defaultReadObject();
	reconstruct();
    }
    
}