package core.collision;

import com.golden.gamedev.object.Sprite;

import core.characters.Character;
import core.tiles.PenetrableDecorator;
import core.tiles.Tile;
import demo.Mario;

public class CharacterPlatformCollision extends GameElementCollision{
	//Character groups should deal with what happens to the character after collision. 
	//May have to be separated with player/enemy distinctions.
	//Platforms will react depending on their type of decorating.
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		super.collided(arg0, arg1);
//		if(((Tile) arg1).isPenetrable()){
//            ((Mario)arg0).setStrengthUp(((PenetrableDecorator)arg1).getStrength());
//            ((Mario)arg0).setStrengthDown(((PenetrableDecorator)arg1).getStrength());
//            ((Mario)arg0).setStrengthLeft(((PenetrableDecorator)arg1).getStrength());
//            ((Mario)arg0).setStrengthRight(((PenetrableDecorator)arg1).getStrength());
//        }
//		else{
//			revertPosition1();
//			if(collisionSide == BOTTOM_TOP_COLLISION){
//			    ((Character) arg0).afterHitFromBottomBy((Tile) arg1);
//				((Tile) arg1).afterHitFromTopBy((Character) arg0);
//			}
//			else if(collisionSide == TOP_BOTTOM_COLLISION){
//	             ((Character) arg0).afterHitFromTopBy((Tile) arg1);
//				((Tile) arg1).afterHitFromBottomBy((Character) arg0);
//			}
//			else if(collisionSide == LEFT_RIGHT_COLLISION){
//	             ((Character) arg0).afterHitFromLeftBy((Tile) arg1);
//				((Tile) arg1).afterHitFromRightBy((Character) arg0);
//			}
//			else if(collisionSide == RIGHT_LEFT_COLLISION){
//	             ((Character) arg0).afterHitFromRightBy((Tile) arg1);
//				((Tile) arg1).afterHitFromLeftBy((Character) arg0);
//			}
//		}		
	}
}
