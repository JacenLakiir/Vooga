package core.collision;

import com.golden.gamedev.object.Sprite;
import core.characters.Character;
import core.characters.Player;

import core.items.CollectibleItem;

/**
 * @author Kathleen Oshima
 */
public class CharacterWeaponCollision extends GameElementCollision {
	@Override
	public void collided(Sprite character, Sprite weapon) {
		super.collided(character, weapon);
		character.setActive(true);
		// implement this method with the npc
		((CollectibleItem) weapon).updateStateValues((Player) character,
		        "hitPoints",
		        ((CollectibleItem) weapon).getStateValue("hitPoints"));
		((CollectibleItem) weapon).setIsInUse(false);

		weapon.setActive(false);
	}
}
