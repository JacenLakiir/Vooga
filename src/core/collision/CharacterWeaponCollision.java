package core.collision;


import com.golden.gamedev.object.Sprite;

import core.items.CollectibleItem;
import core.physicsengine.NewtonianCollision;


public class CharacterWeaponCollision extends NewtonianCollision {
	@Override
    public void collided(Sprite character, Sprite item) {
		super.collided(character, item);
	    character.setActive(true);
	    //character.updateHitPoints((CollectibleItem) item).getAttackPower())
	    item.setActive(false);
	    ((CollectibleItem) item).setIsInUse(true);
    }
}
