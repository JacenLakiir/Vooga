package core.items;

import com.golden.gamedev.GameObject;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * 
 * @author Kathleen Oshima
 *
 *For items that can be stored in the player's inventory and used at will
 */
public class SetInUseSetNotInUseItem extends CollectibleItem {

	public SetInUseSetNotInUseItem(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
	}
	
	@Override
    public boolean canSetInUse() {
	    return true;
    }
	
}
