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
    protected ArrayList<CollectibleItem> myInventory;
    protected double myAttackPower, myDefensePower, myLevel, myPoints;
    protected HashMap<String, Double> myStateValues;
    
    public Player(GameObject game) {
        super(game);
        myInventory = new ArrayList<CollectibleItem>();
		myStateValues = new HashMap<String, Double>();
    }
    
    @Override
    public void update(long milliSec) {
     
        updateAbilities();
        super.update(milliSec);  
//        checkDead();
        if (myStateValues.get("hitPoints") <= 0) {
        	System.out.println("dead");
        }
    }   
   
    public void checkDead() {
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
		myStateValues.put(attribute, defaultValue);
	}

	public void updateStateValues(String attribute, double newValue) {
		if (!myStateValues.containsKey(attribute)) {
			myStateValues.put(attribute, (double) 0);
		}
		myStateValues.put(attribute, myStateValues.get(attribute) + newValue);
	}
	
	public double getMyStateValue(String attribute) {
    	return myStateValues.get(attribute);
    }
    
    public ArrayList<CollectibleItem> getMyInventory() {
    	return myInventory;
    }
    
    public void updateMyInventory(CollectibleItem item) {
    	myInventory.remove(item);
    }
    
    
    public void updateAbilities() {
		for (CollectibleItem item : myInventory) {
			if (item.isInUse()) {
				item.decorate(this);
				for (String state : myStateValues.keySet()) {
					System.out.print(state + myStateValues.get(state));
					System.out.println();	
				}
			}
		}
	}
    
}