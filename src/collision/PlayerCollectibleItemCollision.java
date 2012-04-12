package collision;

import items.CollectibleItem;

import physicsengine.NewtonianCollision;

import charactersprites.Player;

import com.golden.gamedev.object.Sprite;

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
