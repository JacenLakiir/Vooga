/**
 * @author Kuang Han
 */

package charactersprites;

import java.awt.event.KeyEvent;
import java.util.List;

import keyconfiguration.Key;
import keyconfiguration.KeyAnnotation;

import com.golden.gamedev.Game;


@SuppressWarnings("serial")
public class Player extends Character{
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;
    private List<Key> keyList;
    public Player(Game game) {
        super(game);
    }
    
    @Override
    public void update(long milliSec) {
        checkKeyboardInput();
        super.update(milliSec);  
        checkDead();
    }
    
    public void setKeyList(List<Key> list){
        keyList = list;
    }
    
    public void checkKeyboardInput() {
        for(Key key : keyList){
            if(key.isKeyDown()){
                key.notifyObserver();
            }
        }
    }
    
    public void checkDead() {
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
