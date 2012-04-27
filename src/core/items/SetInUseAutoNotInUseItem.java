package core.items;

import com.golden.gamedev.GameObject;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * 
 * @author Kathleen Oshima
 *
 *For items that can be stored in the player's inventory and used at will, but expire after a set length of time
 */
public class SetInUseAutoNotInUseItem extends AutoNotInUseItem {

	public SetInUseAutoNotInUseItem(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
    }
	
	@Override
	public boolean canSetInUse() {
		return true;
	}
}
