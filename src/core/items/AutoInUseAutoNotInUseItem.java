package core.items;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * 
 * @author Kathleen Oshima
 *
 *For items whose effects are immediate and one-time
 */
public class AutoInUseAutoNotInUseItem extends AutoNotInUseItem {

	public AutoInUseAutoNotInUseItem(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	@Override
    public boolean canSetInUse() {
	    return false;
    }
}
