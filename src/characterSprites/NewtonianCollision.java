package characterSprites;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;


public class NewtonianCollision extends CollisionGroup{

    @Override
    public void collided(Sprite s1, Sprite s2) {
        if ((this.collisionSide & CollisionGroup.LEFT_RIGHT_COLLISION) != 0) {
            ((NewtonianSprite)s1).collidedLeftToRightWith((NewtonianSprite)s2);
            ((NewtonianSprite)s2).collidedRightToLeftWith((NewtonianSprite)s1);
        }
        if  ((this.collisionSide & CollisionGroup.RIGHT_LEFT_COLLISION) != 0) {
            ((NewtonianSprite)s1).collidedRightToLeftWith((NewtonianSprite)s2);
            ((NewtonianSprite)s2).collidedLeftToRightWith((NewtonianSprite)s1);
        }
        if ((this.collisionSide & CollisionGroup.BOTTOM_TOP_COLLISION) != 0) {
            ((NewtonianSprite)s1).collidedBottomToTopWith((NewtonianSprite)s2);
            ((NewtonianSprite)s2).collidedTopToBottomWith((NewtonianSprite)s1);
        }
        if ((this.collisionSide & CollisionGroup.TOP_BOTTOM_COLLISION) != 0) {
            ((NewtonianSprite)s1).collidedTopToBottomWith((NewtonianSprite)s2);
            ((NewtonianSprite)s2).collidedBottomToTopWith((NewtonianSprite)s1);
        }
    }


}
