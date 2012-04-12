/**
 * @author Kuang Han
 */

package core.characters;



import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.Game;

import core.items.CollectibleItem;
import core.keyconfiguration.Key;
import core.keyconfiguration.KeyAnnotation;


@SuppressWarnings("serial")
public class Player extends Character{
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    private List<Key> keyList;
    private SystemTimer timer = new SystemTimer();
    protected ArrayList<CollectibleItem> myInventory;
    protected double myHitPoints, myAttackPower, myDefensePower, myLevel, myPoints;
    
    public Player(Game game) {
        super(game);
        myInventory = new ArrayList<CollectibleItem>();
        
    }
    
    @Override
    public void update(long milliSec) {
        super.update(milliSec);  
        updateAbilities();
        checkDead();
        if (myHitPoints <= 0) {
        	System.out.println("dead");
        }
    }
    
    public void setKeyList(List<Key> list){
        keyList = list;
    }
   
    public void checkDead() {
    }
    
    @KeyAnnotation(action = "sequence")
    public void sequenceKey(){
        System.out.println("Sequential Key Triggered");
        this.setImages(myGame.getImages("resources/Mushroom.png",1,1));
    }
    
    @KeyAnnotation(action = "up")
    public void keyUpPressed() {
        this.addAcceleration(0, strengthUp*stdGravity);
    }
    
    @KeyAnnotation(action = "down")
    public void keyDownPressed() {
        this.addAcceleration(0, strengthDown*-stdGravity);
    }
    
    @KeyAnnotation(action = "left")
    public void keyLeftPressed() {
        this.addAcceleration(strengthLeft*-stdGravity, 0);
    }
    
    @KeyAnnotation(action = "right")
    public void keyRightPressed() {
        this.addAcceleration(strengthRight*stdGravity, 0);
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
    
    public double getMyHP() {
    	return myHitPoints;
    }
 
    public void setMyHP(double hp) {
    	myHitPoints += hp;
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
    	if (!myInventory.isEmpty()) {
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
    
}