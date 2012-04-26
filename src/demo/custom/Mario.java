package demo.custom;

import leveleditor.VoogaUtilities;
import io.annotations.Modifiable;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;

import core.characters.GameElement;
import core.characters.Character;
import core.configuration.key.KeyAnnotation;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Kuang Han
 */
@SuppressWarnings("serial")
public class Mario extends Character {

    private static final String IMAGE_FILE = "resources/Mario1.png";
    private double strengthUp, strengthDown, strengthLeft, strengthRight;
    
    @Modifiable(classification = "Gameplay")
    private boolean jumpEnable;
    private Timer jumpTimer;
    @Modifiable(classification = "Gameplay")
    private int jumpTime;

    public Mario(GameObject game, PhysicsAttributes physicsAttribute) {
    	this(physicsAttribute);
    	setGame(game);
    }
    
    public Mario(PhysicsAttributes physicsAttribute) {
    	super(physicsAttribute);
        addDefaultBaseAttributeEntry("hitPoints", 1);
    	setImages(VoogaUtilities.getImages(IMAGE_FILE, 1, 1));
    	resetStrength();
    	setMaximumSpeedInX(0.8);
    	jumpTime = 250;
    	jumpTimer = new Timer(jumpTime);
    	jumpTimer.setActive(false);
    	setTag("Mario");
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
    	setImages(myGame.getImages("resources/Mushroom.png", 1, 1));
    }

    @KeyAnnotation(action = "up")
    public void keyUpPressed() {
        giveStrengthUp();
    }

    @KeyAnnotation(action = "down")
    public void keyDownPressed() {
        addAcceleration(0, strengthDown * -this.getPhysicsAttribute().getGravitationalAcceleration());
    }

    @KeyAnnotation(action = "left")
    public void keyLeftPressed() {
        addAcceleration(strengthLeft * -this.getPhysicsAttribute().getGravitationalAcceleration(), 0);
    }

    @KeyAnnotation(action = "right")
    public void keyRightPressed() {
        addAcceleration(strengthRight * this.getPhysicsAttribute().getGravitationalAcceleration(), 0);
    }

//    @KeyAnnotation(action = "space")
//    public SetInUseSetNotInUseItem keySpacePressed() {
//    	for (CollectibleItem item : this.getMyActiveInventory()) {
//    		if (item.canSetInUse() && item.isInUse()) {
//    			return ((FiringWeapon) item).useWeapon();
//    		}    
//    	}
//    	return null;
//    }

    public void specialSkill() {
    }
    
    public void giveStrengthUp() {
        if (!jumpEnable)
            return;
        else {
            if (jumpTimer.isActive() == false) {
                jumpTimer.setActive(true);
                jumpTimer.refresh();
            }
            addAcceleration(0, strengthUp * this.getPhysicsAttribute().getGravitationalAcceleration());
        }
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
        setStrengthDown(s);
        setStrengthLeft(s);
        setStrengthRight(s);
        setStrengthUp(s);
    }
    
    // this is only used for swimming
    public void resetStrength() {
        strengthUp = 2;
        strengthDown = 0;
        strengthLeft = strengthRight = 1;
    }

    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
    	jumpEnable = true;
    	jumpTimer.setActive(false);
    }

}