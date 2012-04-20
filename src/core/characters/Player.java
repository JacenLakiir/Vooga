/**
 * @author Kuang Han
 */

package core.characters;

import java.util.ArrayList;
import java.util.HashMap;

import com.golden.gamedev.GameObject;
import core.items.CollectibleItem;

@SuppressWarnings("serial")
public class Player extends Character{
    
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    protected ArrayList<CollectibleItem> myInventory, myActiveInventory;
    protected double myAttackPower, myDefensePower, myLevel, myPoints;
    protected HashMap<String, Double> myStateValues, myBaseValues;
    
    public Player(GameObject game) {
        super(game);
        myInventory = new ArrayList<CollectibleItem>();
        myActiveInventory = new ArrayList<CollectibleItem>();
        myBaseValues = new HashMap<String, Double>();
		myStateValues = new HashMap<String, Double>();
    }
    
    @Override
    public void update(long milliSec) {
     
        updateAbilities();
        super.update(milliSec);  
//        checkDead();
        if (myStateValues.get("hitPoints") <= 0) {
        	updateBaseValues("lives", -1);
        	addState("hitPoints", 10);
        	this.setLocation(0, 0);
        	System.out.println("dead");
        }
    }   
   
    public void checkDead() {
//    	if (myStateValues.get("hitPoints") <= 0) {
//        	updateStateValues("lives", -1);
//        	updateStateValues("hitPoints", 10);
//        	this.setLocation(0, 0);
//        	System.out.println("dead");
//        }
    }
    
    protected void giveStrengthUp() {
        this.addAcceleration(0, strengthUp*stdGravity);
    }

    public void keyAPressed() {
        shoot();
    }
    
    public void keyBPressed() {
        specialSkill();
    }
   
    public void shoot() {}
    
    public void specialSkill() {}
    
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
    
    public void addState(String attribute, double defaultValue) {
		myBaseValues.put(attribute, defaultValue);
    	myStateValues.put(attribute, myBaseValues.get(attribute));

	}
    
    public void updateBaseValues(String attribute, double newValue) {
    	if (!myBaseValues.containsKey(attribute)) {
			myBaseValues.put(attribute, (double) 0);
		}
    	myBaseValues.put(attribute, myBaseValues.get(attribute) + newValue);
    	myStateValues.put(attribute, myBaseValues.get(attribute));
    }

    public double getMyBaseValue(String attribute) {
    	return myBaseValues.get(attribute);
    }
    
	public void updateStateValues(String attribute, double newValue) {
		if (!myBaseValues.containsKey(attribute)) {
			myBaseValues.put(attribute, (double) 0);
		}
		myStateValues.put(attribute, myBaseValues.get(attribute) + newValue);
	}
	
	public double getMyStateValue(String attribute) {
    	return myStateValues.get(attribute);
    }
    
    public ArrayList<CollectibleItem> getMyInventory() {
    	return myInventory;
    }
    
    public ArrayList<CollectibleItem> getMyActiveInventory() {
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
			}
			else { 
				myActiveInventory.remove(item);
			} 
		}
		for (CollectibleItem item : myActiveInventory) {
			item.decorate(this);
			for (String state : myStateValues.keySet()) {
				System.out.print(state + myStateValues.get(state));
				System.out.println();	
			}
		}
	}
    
}