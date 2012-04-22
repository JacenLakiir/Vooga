/**
 * @author Michael Zhou (Dominator008)
 */
package io;

import java.io.Serializable;
import java.util.*;

public class SpriteAttribute implements Comparable<SpriteAttribute>, Serializable {

    private static final long serialVersionUID = -9194012867669765397L;
    
    private String myName;
    private Class<?> myType;
    private String myClassification;

    
    public SpriteAttribute(String name, Class<?> type, String classification) {
	myName = name;
	myType = type;
	myClassification = classification;
    }
    
    public String getName() {
	return myName;
    }
    
    public Class<?> getAttributeType() {
	return myType;
    }
    
    public String getClassification() {
	return myClassification;
    }
    
    public int hashCode() {
	return myName.hashCode();
    }
    
    public boolean equals(Object o) {
	SpriteAttribute s = (SpriteAttribute) o;
	return myName.equals(s.myName) && myType.equals(s.myType);
    }

    public int compareTo(SpriteAttribute o) {
	return myName.compareTo(o.myName);
    }
    
    public static Map<SpriteAttribute, Serializable> 
    	convertkeyToAttribute(Map<String, Serializable> dummymap, String classification) {
	Map<SpriteAttribute, Serializable> realmap = new HashMap<SpriteAttribute, Serializable>();
	for (String str: dummymap.keySet()) {
	    SpriteAttribute attr = new SpriteAttribute(str, 
		    dummymap.get(str).getClass(), classification);
	    realmap.put(attr, dummymap.get(str));
	}
	return realmap;
    }
    
    public static Map<String, Serializable> 
	convertkeyToString(Map<SpriteAttribute, Serializable> realmap) {
	Map<String, Serializable> dummymap = new HashMap<String, Serializable>();
	for (SpriteAttribute attr: realmap.keySet()) {
	    String str = attr.getName();
	    dummymap.put(str, dummymap.get(str));
	}
	return dummymap;
    }
    
}
