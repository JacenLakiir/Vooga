/**
 * @author Michael Zhou (Dominator008)
 */
package levelio;

public class SpriteAttribute implements Comparable<SpriteAttribute>, java.io.Serializable {

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
    
}