package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.DefaultPhysicsAttribute;

public class SetInUseAutoNotInUseItem extends AutoNotInUseItem {

	public SetInUseAutoNotInUseItem(GameObject game, DefaultPhysicsAttribute physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	@Override
	public boolean canSetInUse() {
		return true;
	}
}
