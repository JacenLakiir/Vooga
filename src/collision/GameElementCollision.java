/**
 * @author Kuang Han
 */

 /**
 *  Feel free to extend this class for any collisions.
 */

package collision;
import voogaobject.GameElement;

import com.golden.gamedev.object.Sprite;

import physicsengine.NewtonianCollision;

public class GameElementCollision extends NewtonianCollision{
    
    @Override
    public void collided(Sprite s1, Sprite s2) {
        switch (this.collisionSide) {
        case RIGHT_LEFT_COLLISION:{
            ((GameElement)s1).beforeCollidedWith((GameElement)s2, RIGHT_LEFT_COLLISION);
            ((GameElement)s2).beforeCollidedWith((GameElement)s1, LEFT_RIGHT_COLLISION);
            super.collided(s1, s2);
            ((GameElement)s1).afterCollidedWith((GameElement)s2, RIGHT_LEFT_COLLISION);
            ((GameElement)s2).afterCollidedWith((GameElement)s1, LEFT_RIGHT_COLLISION);
            break;
        }
        case LEFT_RIGHT_COLLISION:{
            ((GameElement)s1).beforeCollidedWith((GameElement)s2, LEFT_RIGHT_COLLISION);
            ((GameElement)s2).beforeCollidedWith((GameElement)s1, RIGHT_LEFT_COLLISION);
            super.collided(s1, s2);
            ((GameElement)s1).afterCollidedWith((GameElement)s2, LEFT_RIGHT_COLLISION);
            ((GameElement)s2).afterCollidedWith((GameElement)s1, RIGHT_LEFT_COLLISION);
            break;
        }
        case BOTTOM_TOP_COLLISION:{
            ((GameElement)s1).beforeCollidedWith((GameElement)s2, BOTTOM_TOP_COLLISION);
            ((GameElement)s2).beforeCollidedWith((GameElement)s1, TOP_BOTTOM_COLLISION);
            super.collided(s1, s2);
            ((GameElement)s1).afterCollidedWith((GameElement)s2, BOTTOM_TOP_COLLISION);
            ((GameElement)s2).afterCollidedWith((GameElement)s1, TOP_BOTTOM_COLLISION);
            break;
        }
        case TOP_BOTTOM_COLLISION:{
            ((GameElement)s1).beforeCollidedWith((GameElement)s2, TOP_BOTTOM_COLLISION);
            ((GameElement)s2).beforeCollidedWith((GameElement)s1, BOTTOM_TOP_COLLISION);
            super.collided(s1, s2);
            ((GameElement)s1).afterCollidedWith((GameElement)s2, TOP_BOTTOM_COLLISION);
            ((GameElement)s2).afterCollidedWith((GameElement)s1, BOTTOM_TOP_COLLISION);
            break;
        }
        }
    }    
}
