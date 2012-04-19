/**
 * @author Kuang Han
 */

package core.characters;

import java.util.ArrayList;
import com.golden.gamedev.GameObject;
import core.items.CollectibleItem;

@SuppressWarnings("serial")
public class Player extends Character{
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    protected ArrayList<CollectibleItem> myInventory;
    protected double myAttackPower, myDefensePower, myLevel, myPoints;
    
    public Player(GameObject game) {
        super(game);
        myInventory = new ArrayList<CollectibleItem>();
    }
    
    @Override
    public void update(long milliSec) {
//        checkDead();
        updateAbilities();
        super.update(milliSec);  
        if (myHitPoints <= 0) {
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

    public ArrayList<CollectibleItem> getMyInventory() {
    	return myInventory;
    }
    
    public void updateMyInventory(CollectibleItem item) {
    	myInventory.remove(item);
    }
    
    public double getMyLevel() {
    	return myLevel;
    }
    
    public void setMyLevel(double level) {
    	myLevel += level;
    }
    
    public double getMyAttackPower() {
    	return myAttackPower;
    }
    
    public void setMyAttackPower(double power) {
    	myAttackPower += power;
    }
    
    public double getMyDefensePower() {
    	return myDefensePower;
    }
    
    public void setMyDefensePower(double power) {
    	myDefensePower += power;
    }
    
    public double getMyPoints() {
    	return myPoints;
    }
    
    public void setMyPoints(double value) {
    	myPoints += value;
    }
    
    
    public void updateAbilities() {
		for (CollectibleItem item : myInventory) {
			if (item.isInUse()) {
				item.decorate(this);
				System.out.println("Coins: " + myPoints + " Attack Power: " + myAttackPower
				        + " Defense Power: " + myDefensePower + " Hit Points: "
				        + myHitPoints + " Level: " + myLevel);
			}
		}
	}
    
}