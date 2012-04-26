/**
 * @author Kuang Han
 */

package core.collision;

import java.util.List;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsPlugin;


public class GameElementCollision extends CollisionGroup{
    List<PhysicsPlugin> physicsPlugins;

    public GameElementCollision() {
        super();
        this.pixelPerfectCollision = true;
        physicsPlugins = null;
    }
    
    public void setPhysicsPlugIns(List<PhysicsPlugin> list) {
        physicsPlugins = list;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {
        this.beforeCollided((GameElement)s1, (GameElement)s2);
        this.checkPhysics((GameElement)s1, (GameElement)s2);
        this.afterCollided((GameElement)s1, (GameElement)s2);
    }    

    protected void beforeCollided(GameElement s1, GameElement s2) {
        switch (this.collisionSide) {
        case RIGHT_LEFT_COLLISION:{
            s1.beforeHitFromRightBy(s2, s2.getTag());
            s2.beforeHitFromLeftBy(s1, s1.getTag());
            break;
        }
        case LEFT_RIGHT_COLLISION:{
            s1.beforeHitFromLeftBy(s2, s2.getTag());
            s2.beforeHitFromRightBy(s1, s1.getTag());
            break;
        }
        case BOTTOM_TOP_COLLISION:{
            s1.beforeHitFromBottomBy(s2, s2.getTag());
            s2.beforeHitFromTopBy(s1, s1.getTag());
            break;
        }
        case TOP_BOTTOM_COLLISION:{
            s1.beforeHitFromTopBy(s2, s2.getTag());
            s2.beforeHitFromBottomBy(s1, s1.getTag());
            break;
        }
        }
    }

    protected void afterCollided(GameElement s1, GameElement s2) {
        switch (this.collisionSide) {
        case RIGHT_LEFT_COLLISION:{
            s1.afterHitFromRightBy(s2, s2.getTag());
            s2.afterHitFromLeftBy(s1, s1.getTag());
            break;
        }
        case LEFT_RIGHT_COLLISION:{
            s1.afterHitFromLeftBy(s2, s2.getTag());
            s2.afterHitFromRightBy(s1, s1.getTag());
            break;
        }
        case BOTTOM_TOP_COLLISION:{
            s1.afterHitFromBottomBy(s2, s2.getTag());
            s2.afterHitFromTopBy(s1, s1.getTag());
            break;
        }
        case TOP_BOTTOM_COLLISION:{
            s1.afterHitFromTopBy(s2, s2.getTag());
            s2.afterHitFromBottomBy(s1, s1.getTag());
            break;
        }
        }    
    }

    protected void checkPhysics(GameElement s1, GameElement s2) {   
        if (physicsPlugins == null) {
            return;
        }
        for (PhysicsPlugin p:physicsPlugins) {
            p.setCollisionGroup(this);
            p.exertOn(s1, s2);
        }
    }

}
