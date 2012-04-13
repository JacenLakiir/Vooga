package core.collision;

import com.golden.gamedev.object.Sprite;

import core.characters.Character;
import core.tiles.Tile;

public class CharacterPlatformCollision extends GameElementCollision {
	// Character groups should deal with what happens to the character after
	// collision.
	// May have to be separated with player/enemy distinctions.
	// Platforms will react depending on their type of decorating.

	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		super.collided(arg0, arg1);
//		collided((Character)arg0, (Tile) arg1);
	}
	
//	public void collided(Character sprite1, Tile sprite2){
//		revertPosition1();
//		if (collisionSide == BOTTOM_TOP_COLLISION) {
//			sprite1.afterHitFromBottomBy(sprite2);
//			sprite2.afterHitFromTopBy(sprite1);
//		} else if (collisionSide == TOP_BOTTOM_COLLISION) {
//			sprite1.afterHitFromTopBy(sprite2);
//			sprite2.afterHitFromBottomBy(sprite2);
//		} else if (collisionSide == LEFT_RIGHT_COLLISION) {
//			sprite1.afterHitFromLeftBy(sprite2);
//			sprite2.afterHitFromRightBy(sprite1);
//		} else if (collisionSide == RIGHT_LEFT_COLLISION) {
//			sprite1.afterHitFromRightBy(sprite2);
//			sprite2.afterHitFromLeftBy(sprite1);
//		}
//	}
}
