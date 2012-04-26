package core.collision;

import com.golden.gamedev.object.Sprite;
import core.characters.Character;

import core.items.CollectibleItem;
import core.items.FiringWeapon;

/**
 * @author Kathleen Oshima
 */
public class CharacterBulletCollision extends GameElementCollision {
	@Override
	public void collided(Sprite character, Sprite weapon) {
		super.collided(character, weapon);
		character.setActive(true);
		// implement this method with the npc
		((CollectibleItem) weapon).updateAttributeValues((Character) character,
		        "hitPoints",
		        ((FiringWeapon) weapon).getAttributeValue("hitPoints")*(-1));
		((CollectibleItem) weapon).setIsInUse(false);

		weapon.setActive(false);
	}
}
