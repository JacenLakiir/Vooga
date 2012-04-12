package core.collision;


import com.golden.gamedev.object.Sprite;

import core.items.CollectibleItem;
import core.physicsengine.NewtonianCollision;

/**
 * @author Kathleen Oshima
 */
public class CharacterWeaponCollision extends NewtonianCollision {
	@Override
    public void collided(Sprite character, Sprite weapon) {
		super.collided(character, weapon);
	    character.setActive(true);
	    //implement this method with the npc 
	    //character.updateHitPoints((CollectibleItem) item).getAttackPower())
	    weapon.setActive(false);
//	    ((CollectibleItem) item).setIsInUse(true);
    }
}
