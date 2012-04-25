package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class FiringWeapons extends SetInUseSetNotInUseItem{

	public FiringWeapons(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	public SetInUseSetNotInUseItem useWeapon() {
		this.setActive(true);
		this.setLocation(getX(), getY());
		return this;
	}
}
