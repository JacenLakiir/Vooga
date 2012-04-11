/**
 * @author Kuang Han
 */

package charactersprites;

import items.CollectibleItem;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import keyconfiguration.Key;
import keyconfiguration.KeyAnnotation;
import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.Game;

@SuppressWarnings("serial")
public class Player extends Character {
	protected double strengthUp, strengthDown, strengthLeft, strengthRight;
	private List<Key> keyList;
	private SystemTimer timer = new SystemTimer();
	protected ArrayList<CollectibleItem> myInventory;
	protected double myHitPoints, myAttackPower, myDefensePower, myLevel;

	public Player(Game game) {
		super(game);
		myInventory = new ArrayList<CollectibleItem>();
	}

	@Override
	public void update(long milliSec) {
		checkKeyboardInput(timer.getTime());
		super.update(milliSec);
		updateAbilities();
		checkDead();
	}

	public void setKeyList(List<Key> list) {
		keyList = list;
	}

	public void checkKeyboardInput(long milliSec) {
		for (Key key : keyList) {
			if (key.isKeyDown(milliSec)) {
				key.notifyObserver();
			}
		}
		// if (myGame.keyDown(KeyEvent.VK_UP)) {
		// keyUpPressed();
		// }
		// if (myGame.keyDown(KeyEvent.VK_DOWN)) {
		// keyDownPressed();
		// }
		// if (myGame.keyDown(KeyEvent.VK_LEFT)) {
		// keyLeftPressed();
		// }
		// if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
		// keyRightPressed();
		// }
	}

	public void checkDead() {
	}

	@KeyAnnotation(action = "sequence")
	public void sequenceKey() {
		System.out.println("Sequential Key Triggered");
		this.setImages(myGame.getImages("resources/Mushroom.png", 1, 1));
	}

	@KeyAnnotation(action = "up")
	public void keyUpPressed() {
		this.addAcceleration(0, strengthUp * stdGravity);
	}

	@KeyAnnotation(action = "down")
	public void keyDownPressed() {
		this.addAcceleration(0, strengthDown * -stdGravity);
	}

	@KeyAnnotation(action = "left")
	public void keyLeftPressed() {
		this.addAcceleration(strengthLeft * -stdGravity, 0);
	}

	@KeyAnnotation(action = "right")
	public void keyRightPressed() {
		this.addAcceleration(strengthRight * stdGravity, 0);
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

	public ArrayList<CollectibleItem> getMyInventory() {
		return myInventory;
	}

	public double getMyHP() {
		return myHitPoints;
	}

	public double getMyLevel() {
		return myLevel;
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

	public void updateAbilities() {
		for (CollectibleItem item : myInventory) {
			if (item.isInUse()) {
				item.decorate(this);
				System.out.println("Attack Power: " + myAttackPower
				        + " Defense Power: " + myDefensePower + " Hit Points: "
				        + myHitPoints + " Level: " + myLevel);
			}
		}
	}

}