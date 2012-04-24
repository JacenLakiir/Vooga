package core.collision;




import com.golden.gamedev.object.Sprite;

import core.characters.Character;
import core.items.CollectibleItem;

public class CharacterCollectibleItemCollision extends GameElementCollision {

	@Override
    public void collided(Sprite character, Sprite item) {
		super.collided(character, item);
	    character.setActive(true);
	    item.setActive(false);
	    ((CollectibleItem) item).setIsInUse(true);
	    ((Character) character).getMyInventory().add((CollectibleItem) item);
    }
}
