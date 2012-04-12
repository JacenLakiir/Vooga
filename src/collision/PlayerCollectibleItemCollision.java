package collision;

import items.CollectibleItem;

import java.awt.Graphics2D;

import physicsengine.NewtonianCollision;

import voogaobject.MergedCollision;

import charactersprites.GameElement;
import charactersprites.Player;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

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
