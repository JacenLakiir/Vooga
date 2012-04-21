package demo.custom;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;

import core.characters.GameElement;
import core.characters.Player;
import core.keyconfiguration.KeyAnnotation;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Kuang Han
 */
@SuppressWarnings("serial")
public class Mario extends Player {

    private static final String IMAGE_FILE = "resources/Mario1.png";

    protected boolean jumpEnable;
    protected Timer jumpTimer;
    protected int jumpTime;

    public Mario(GameObject game, PhysicsAttributes physicsAttribute) {
	super(game, physicsAttribute);
	setImages(game.getImages(IMAGE_FILE, 1, 1));
	resetStrength();
	this.setMaximumSpeedInX(0.8);
	jumpTime = 250;
	jumpTimer = new Timer(jumpTime);
	jumpTimer.setActive(false);
    }

    @Override
    public void update(long t) {
	super.update(t);
	resetStrength();
	if (jumpTimer.action(t)) {
	    jumpTimer.setActive(false);
	    jumpEnable = false;
	}
    }

    @KeyAnnotation(action = "sequence")
    public void sequenceKey() {
	this.setImages(myGame.getImages("resources/Mushroom.png", 1, 1));
    }

    @KeyAnnotation(action = "up")
    public void keyUpPressed() {
	this.giveStrengthUp();
    }

    @KeyAnnotation(action = "down")
    public void keyDownPressed() {
	this.addAcceleration(0,
		strengthDown * -this.getGravitationalAcceleration());
    }

    @KeyAnnotation(action = "left")
    public void keyLeftPressed() {
	this.addAcceleration(strengthLeft * -getGravitationalAcceleration(), 0);
    }

    @KeyAnnotation(action = "right")
    public void keyRightPressed() {
	this.addAcceleration(strengthRight * getGravitationalAcceleration(), 0);
    }

    @KeyAnnotation(action = "space")
    // public Weapon keySpacePressed() {
    // return Weapon.useWeapon();
    // }
    // this is only used for swimming
    public void resetStrength() {
	strengthUp = 2;
	strengthDown = 0;
	strengthLeft = strengthRight = 1;
    }

    @Override
    public void specialSkill() {
    }

    public void afterHitFromBottomBy(GameElement e) {
	jumpEnable = true;
	jumpTimer.setActive(false);
    }

    @Override
    public void giveStrengthUp() {
	if (!jumpEnable) {
	    return;
	} else {
	    if (jumpTimer.isActive() == false) {
		jumpTimer.setActive(true);
		jumpTimer.refresh();
	    }
	    super.giveStrengthUp();
	}
    }

}
