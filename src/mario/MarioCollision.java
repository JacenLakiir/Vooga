/**
 * @author Kuang Han
 */

package mario;

import physicsengine.NewtonianCollision;
import charactersprites.Brick;

import com.golden.gamedev.object.Sprite;


public class MarioCollision extends NewtonianCollision{
    
    @Override
    public void collided(Sprite mario, Sprite brick) {        
        super.collided(mario, brick);
        if (((Brick)brick).isPenetrable()) {
            ((Mario)mario).setStrengthUp(((Water)brick).getStrengthForMario());
            ((Mario)mario).setStrengthDown(((Water)brick).getStrengthForMario());
            ((Mario)mario).setStrengthLeft(((Water)brick).getStrengthForMario());
            ((Mario)mario).setStrengthRight(((Water)brick).getStrengthForMario());
        }
    }
}
