package collision;

import mario.Mario;
import setting.PenetrableDecorator;
import setting.Platform;

/**
 * @author Ian McMahon
 */

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class CharacterPlatformCollision extends NewtonianCollision{
	//Character groups should deal with what happens to the character after collision. 
	//May have to be separated with player/enemy distinctions.
	//Platforms will react depending on their type of decorating.
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		super.collided(arg0, arg1);
		if(((Platform) arg1).isPenetrable()){
            ((Mario)arg0).setStrengthUp(((PenetrableDecorator)arg1).getStrength());
            ((Mario)arg0).setStrengthDown(((PenetrableDecorator)arg1).getStrength());
            ((Mario)arg0).setStrengthLeft(((PenetrableDecorator)arg1).getStrength());
            ((Mario)arg0).setStrengthRight(((PenetrableDecorator)arg1).getStrength());
        }
		else{
			if(collisionSide == BOTTOM_TOP_COLLISION){
				((Platform) arg1).hitFromTopAction();
			}
			else if(collisionSide == TOP_BOTTOM_COLLISION){
				((Platform) arg1).hitFromBottomAction();
			}
			else if(collisionSide == LEFT_RIGHT_COLLISION){
				((Platform) arg1).hitFromRightAction();
			}
			else if(collisionSide == RIGHT_LEFT_COLLISION){
				((Platform) arg1).hitFromLeftAction();
			}
		}		
	}
}
