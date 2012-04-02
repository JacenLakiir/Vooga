/**
 * @author Kuang Han
 */

package characterSprites;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;


@SuppressWarnings("serial")
public class Player extends Character{
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    
    public Player(Game game) {
        super(game);
    }
    
    @Override
    public void update(long milliSec) {
        checkKeyboardInput();
        super.update(milliSec);  
        checkDead();
    }
    
    public void checkKeyboardInput() {
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            keyUpPressed();
        }
        if (myGame.keyDown(KeyEvent.VK_DOWN)) {
            keyDownPressed();
        }
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            keyLeftPressed();
        }
        if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
            keyRightPressed();
        }
    }
    
    public void checkDead() {
    }
    
    public void keyUpPressed() {
        this.addAcceleration(0, strengthUp*stdGravity);
    }
    public void keyDownPressed() {
        this.addAcceleration(0, strengthDown*-stdGravity);
    }
    public void keyLeftPressed() {
        this.addAcceleration(strengthLeft*-stdGravity, 0);
    }
    public void keyRightPressed() {
        this.addAcceleration(strengthRight*stdGravity, 0);
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

}
