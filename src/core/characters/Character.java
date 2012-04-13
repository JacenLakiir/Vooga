/**
 * @author Kuang Han
 */

package core.characters;


import com.golden.gamedev.GameObject;


@SuppressWarnings("serial")
public abstract class Character extends GameElement{
	
	protected double myHitPoints;
	
    public Character(GameObject game) {
        super(game);
    }
    
    public Character() {
        super();
    }
    
    public double getMyHP() {
    	return myHitPoints;
    }
 
    public void setMyHP(double hp) {
    	myHitPoints += hp;
    }
}
