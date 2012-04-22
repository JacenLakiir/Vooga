package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class SetInUseSetNotInUseItem extends CollectibleItem {

	public SetInUseSetNotInUseItem(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
	}
	
	@Override
    public boolean canSetInUse() {
	    return true;
    }
	
	

}
