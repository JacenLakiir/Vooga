package demo.custom;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;

import core.characters.GameElement;
import core.characters.Player;
import core.items.Weapon;
import core.keyconfiguration.KeyAnnotation;

/**
 * @author Kuang Han
 */
@SuppressWarnings("serial")
public class Mario extends Player{

    protected boolean jumpEnable;
    protected Timer jumpTimer;
    protected int jumpTime;

    public Mario(GameObject game) {
        super(game);
        resetStrength();
        maximunSpeedInX = 0.8;
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
    public void sequenceKey(){
        this.setImages(myGame.getImages("resources/Mushroom.png",1,1));
    }
    
    @KeyAnnotation(action = "up")
    public void keyUpPressed() {
        this.giveStrengthUp();
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
    
    @KeyAnnotation(action = "space")
    public Weapon keySpacePressed() {
      return Weapon.useWeapon();
    }


    // this is only used for swimming
    public void resetStrength() {
        strengthUp = 2;
        strengthDown = 0;
        strengthLeft = strengthRight = 1;
    }

    @Override
    public void specialSkill() {        
    }

    @Override
    public void afterHitFromRightBy (GameElement e) {
        super.afterHitFromRightBy(e);
    }

    @Override
    public void afterHitFromLeftBy (GameElement e) {
        super.afterHitFromLeftBy(e);
    }

    @Override
    public void afterHitFromTopBy (GameElement e) {
        super.afterHitFromTopBy(e);
    }

    @Override
    public void afterHitFromBottomBy (GameElement e) {
        super.afterHitFromTopBy(e);
        jumpEnable = true;
        jumpTimer.setActive(false);
    }

    @Override
    public void giveStrengthUp() {
        if (!jumpEnable) {
            return;
        }
        else {
            if (jumpTimer.isActive() == false) {
                jumpTimer.setActive(true);
                jumpTimer.refresh();
            }
            super.giveStrengthUp();
        }
    }

}
