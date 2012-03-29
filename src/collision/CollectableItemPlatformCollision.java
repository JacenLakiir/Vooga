package collision;

import setting.Platform;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class CollectableItemPlatformCollision extends CollisionGroup{

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		collided(arg0, ((Platform) arg1));
	}

	public void collided(Sprite collectableItem, Platform platform){
		//Don't know if platform needs updating, but the item probably needs something having to do with physics,
		//May have to add things later if these collectableItems include weapons (that would theoretically break platforms)
	}
}
