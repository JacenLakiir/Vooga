package core.items;

import com.golden.gamedev.GameObject;

import core.characters.Player;
import core.physicsengine.physicsplugin.PhysicsAttributes;

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
