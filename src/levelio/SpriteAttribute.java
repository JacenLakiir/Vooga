package levelio;

import levelio.Modifiable.Classification;

public class SpriteAttribute {

    private String myName;
    private Class<?> myType;
    private Classification myClassification;

    
    public SpriteAttribute(String name, Class<?> type, Classification classification) {
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
    
    public Classification getClassification() {
	return myClassification;
    }
    
    public int hashCode() {
	return myName.hashCode();
    }
    
    public boolean equals(Object o) {
	SpriteAttribute s = (SpriteAttribute) o;
	return myName.equals(s.myName) && myType.equals(s.myType);
    }
}