package core.characters;

import java.util.Map;

public class AttributeValues {
	
	private transient Map<String, Double> myAttributeValues, myBaseAttributeValues;
	
	public double getBaseValue(String attribute) {
        return myBaseAttributeValues.get(attribute);
    }
	
	public Double getAttributeValue(String attribute) {
        if (myAttributeValues.get(attribute) == null )
            return null;
        return myAttributeValues.get(attribute);
    }
	
	public void addAttribute(String attribute, double defaultValue) {
		myBaseAttributeValues.put(attribute, defaultValue);
		myAttributeValues.put(attribute, myBaseAttributeValues.get(attribute));
	}
	
	public void updateBaseValue(String attribute, double newValue) {
		if (!myBaseAttributeValues.containsKey(attribute)) {
			return;
		}
		myBaseAttributeValues.put(attribute, myBaseAttributeValues.get(attribute) + newValue);
		myAttributeValues.put(attribute, myBaseAttributeValues.get(attribute));
	}

	public void updateAttributeValue(String attribute, double newValue) {
		if (!myBaseAttributeValues.containsKey(attribute)) {
			return;
		}
		myAttributeValues.put(attribute, myBaseAttributeValues.get(attribute) + newValue);
	}
	
	
	
}
