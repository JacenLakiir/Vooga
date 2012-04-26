package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class FiringWeapon extends SetInUseSetNotInUseItem{

	public FiringWeapon(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	public SetInUseSetNotInUseItem useWeapon() {
		System.out.println("hi");
		this.setLocation(0, 100);
		this.setActive(true);
		this.setSpeed(.2, 0);
		return this;
	}
}
