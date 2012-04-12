package core.collision;




import com.golden.gamedev.object.Sprite;

import core.characters.Player;
import core.items.CollectibleItem;
import core.physicsengine.NewtonianCollision;

public class PlayerCollectibleItemCollision extends NewtonianCollision {

	@Override
    public void collided(Sprite character, Sprite item) {
		super.collided(character, item);
	    character.setActive(true);
	    item.setActive(false);
	    ((CollectibleItem) item).setIsInUse(true);
	    ((Player) character).getMyInventory().add((CollectibleItem) item);
    }
}
