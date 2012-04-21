/**
 * @author Kuang Han
 */

package core.characters;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.GameObject;
import core.items.CollectibleItem;

@SuppressWarnings("serial")
public class Player extends Character {

    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    protected List<CollectibleItem> myInventory, myActiveInventory;

    public Player() {
	super();
	myInventory = new ArrayList<CollectibleItem>();
	myActiveInventory = new ArrayList<CollectibleItem>();
    }
    
    public Player(GameObject game) {
	super(game);
	myInventory = new ArrayList<CollectibleItem>();
	myActiveInventory = new ArrayList<CollectibleItem>();
    }

    @Override
    public void update(long milliSec) {

	updateAbilities();
	super.update(milliSec);

	// check dead
	if (myStateValues.get("hitPoints") <= 0) {
	    updateBaseValues("lives", -1);
	    addState("hitPoints", 10);
	    this.setLocation(0, 0);
	    System.out.println("dead");
	}
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
	    item.decorate(this);
	    for (String state : myStateValues.keySet()) {
		System.out.print(state + myStateValues.get(state));
		System.out.println();
	    }
	}
    }

}