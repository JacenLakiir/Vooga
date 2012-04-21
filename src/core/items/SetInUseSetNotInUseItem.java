package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.DefaultPhysicsAttribute;

public class SetInUseSetNotInUseItem extends CollectibleItem {

	public SetInUseSetNotInUseItem(GameObject game, DefaultPhysicsAttribute physicsAttribute) {
	    super(game, physicsAttribute);
	}
	
	@Override
    public boolean canSetInUse() {
	    return true;
    }
	
	

}
