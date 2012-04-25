/**
 * @author Kathleen Oshima
 * @author Eric Mercer
 */

package core.characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.golden.gamedev.GameObject;

import core.characters.ai.State;
import core.items.CollectibleItem;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Character extends GameElement {
	
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
	private transient List<CollectibleItem> myInventory, myActiveInventory;
	private transient List<State> myPossibleStates;

	protected transient Map<String, Double> myAttributeValues, myBaseAttributeValues;

	public Character(GameObject game, PhysicsAttributes physicsAttribute) {
		super(game, physicsAttribute);
		myBaseAttributeValues = new HashMap<String, Double>();
		myAttributeValues = new HashMap<String, Double>();
		myInventory = new ArrayList<CollectibleItem>();
		myActiveInventory = new ArrayList<CollectibleItem>();
		myPossibleStates = new ArrayList<State>();
	}

	public Character() {
		super();
		myBaseAttributeValues = new HashMap<String, Double>();
		myAttributeValues = new HashMap<String, Double>();
		myInventory = new ArrayList<CollectibleItem>();
		myActiveInventory = new ArrayList<CollectibleItem>();
		myPossibleStates = new ArrayList<State>();
	}

	
	protected void giveStrengthUp() {
		this.addAcceleration(0,
			strengthUp * this.getGravitationalAcceleration());
	    }

	    public void keyAPressed() {
		shoot();
	    }

	    public void keyBPressed() {
		specialSkill();
	    }

	    public void shoot() {
	    }

	    public void specialSkill() {
	    }

	    public void setStrengthUp(double s) {
		strengthUp = s;
	    }

	    public void setStrengthDown(double s) {
		strengthDown = s;
	    }

	    public void setStrengthLeft(double s) {
		strengthLeft = s;
	    }

	    public void setStrengthRight(double s) {
		strengthRight = s;
	    }

	    public void setStrength(double s) {
		this.setStrengthDown(s);
		this.setStrengthLeft(s);
		this.setStrengthRight(s);
		this.setStrengthUp(s);
	    }
	
	public void addAttribute(String attribute, double defaultValue) {
		myBaseAttributeValues.put(attribute.toLowerCase(), defaultValue);
		myAttributeValues.put(attribute.toLowerCase(), myBaseAttributeValues.get(attribute));

	}

	public void updateBaseValues(String attribute, double newValue) {
		String sanitizedAttribute = attribute.toLowerCase();
		if (!myBaseAttributeValues.containsKey(sanitizedAttribute)) {
			return;
		}
		myBaseAttributeValues.put(sanitizedAttribute, myBaseAttributeValues.get(sanitizedAttribute) + newValue);
		myAttributeValues.put(sanitizedAttribute, myBaseAttributeValues.get(sanitizedAttribute));
	}

	public double getMyBaseValue(String attribute) {
		return myBaseAttributeValues.get(attribute.toLowerCase());
	}

	public void updateAttributeValues(String attribute, double newValue) {
		if (!myBaseAttributeValues.containsKey(attribute.toLowerCase())) {
//			myBaseAttributeValues.put(attribute.toLowerCase(), (double) 0);
			return;
		}
		myAttributeValues.put(attribute.toLowerCase(), myBaseAttributeValues.get(attribute.toLowerCase()) + newValue);
	}

	public Double getMyAttributeValue(String attribute) {
		if (myAttributeValues.get(attribute.toLowerCase()) == null )
			return null;
		return myAttributeValues.get(attribute.toLowerCase());
	}

	public List<CollectibleItem> getMyInventory() {
    	return myInventory;
    }

	public List<CollectibleItem> getMyActiveInventory() {
    	return myActiveInventory;
    }

	public void updateMyInventory(CollectibleItem item) {
    	myInventory.remove(item);
    }

	public void useInventoryItem(CollectibleItem item) {
    	item.setActive(true);
    }

	public void unuseInventoryItem(CollectibleItem item) {
    	item.setActive(false);
    }

	public void updateAbilities() {
    	for (CollectibleItem item : myInventory) {
    		if (item.isInUse()) {
    			myActiveInventory.add(item);
    		} else {
    			myActiveInventory.remove(item);
    		}
    	}
    	for (CollectibleItem item : myActiveInventory) {
    		item.updatePlayerAttributes(this);
    		for (String state : myAttributeValues.keySet()) {
    			System.out.print(state + myAttributeValues.get(state));
    			System.out.println();
    		}
    	}
    }
	
	public void addPossibleState(State state) {
		myPossibleStates.add(state);
	}

	public void deactivateAllOtherStates(State toRemainActive) {
		for (State s : myPossibleStates)
			if (!s.equals(toRemainActive))
				s.setActive(false);
	}

	public List<State> getPossibleStates() {
		return myPossibleStates;
	}

	@Override
    public void update(long milliSec) {
    
    	updateAbilities();
    	
		List<State> myCurrentStates = new ArrayList<State>();
		for (State s : myPossibleStates)
			if (s.isActive())
				myCurrentStates.add(s);

		for (State s : myCurrentStates)
			s.execute(milliSec);
		myCurrentStates.clear();

				
    	super.update(milliSec);
    	
    	// check dead
    	Double currHP = getMyAttributeValue("hitPoints");
    	if (currHP != null && currHP <= 0) {
    		updateBaseValues("lives", -1);
    		addAttribute("hitPoints", 10);
    		this.setLocation(0, 0);
    		System.out.println("dead");
    	}
    }

}
