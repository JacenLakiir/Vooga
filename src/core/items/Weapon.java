package core.items;

import com.golden.gamedev.GameObject;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Weapon extends SetInUseSetNotInUseItem
{
    
    public Weapon (PhysicsAttributes physicsAttribute)
    {
        super(physicsAttribute);
        setIsInUse(true);
    }
    
    public void useWeapon(double x, double y) {
    	System.out.println(x+ "moving!" +y);
    	setActive(true);
    	setLocation(x,y);
    	setSpeed(.2,0);
    	setLoopAnim(true);
    	setAnimate(true);
    	getPhysicsAttribute().setMovable(false);
    	
    }

}
