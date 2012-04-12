package collision;

import mario.Mario;
import physicsengine.NewtonianCollision;
import setting.PenetrableDecorator;
import setting.Platform;
import charactersprites.Character;
import com.golden.gamedev.object.Sprite;

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
			revertPosition1();
			if(collisionSide == BOTTOM_TOP_COLLISION){
			    ((Character) arg0).afterHitFromBottomBy((Platform) arg1);
				((Platform) arg1).afterHitFromTopBy((Character) arg0);
			}
			else if(collisionSide == TOP_BOTTOM_COLLISION){
	             ((Character) arg0).afterHitFromTopBy((Platform) arg1);
				((Platform) arg1).afterHitFromBottomBy((Character) arg0);
			}
			else if(collisionSide == LEFT_RIGHT_COLLISION){
	             ((Character) arg0).afterHitFromLeftBy((Platform) arg1);
				((Platform) arg1).afterHitFromRightBy((Character) arg0);
			}
			else if(collisionSide == RIGHT_LEFT_COLLISION){
	             ((Character) arg0).afterHitFromRightBy((Platform) arg1);
				((Platform) arg1).afterHitFromLeftBy((Character) arg0);
			}
		}		
	}
}
