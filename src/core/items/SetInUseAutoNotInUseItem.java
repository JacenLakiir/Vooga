package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class SetInUseAutoNotInUseItem extends AutoNotInUseItem {

	public SetInUseAutoNotInUseItem(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	@Override
	public boolean canSetInUse() {
		return true;
	}
}
