package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class FiringWeapon extends SetInUseSetNotInUseItem{

	public FiringWeapon(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	public SetInUseSetNotInUseItem useWeapon() {
		this.setActive(true);
		this.setLocation(getX(), getY());
		return this;
	}
}
