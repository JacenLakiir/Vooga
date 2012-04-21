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
            s1.reflectionCalled(s2, "beforeHitFromRightBy");
            s2.reflectionCalled(s1, "beforeHitFromLeftBy");
            break;
        }
        case LEFT_RIGHT_COLLISION:{
            s1.reflectionCalled(s2, "beforeHitFromLeftBy");
            s2.reflectionCalled(s1, "beforeHitFromRightBy");
            break;
        }
        case BOTTOM_TOP_COLLISION:{
            s1.reflectionCalled(s2, "beforeHitFromBottomBy");
            s2.reflectionCalled(s1, "beforeHitFromTopBy");
            break;
        }
        case TOP_BOTTOM_COLLISION:{
            s1.reflectionCalled(s2, "beforeHitFromTopBy");
            s2.reflectionCalled(s1, "beforeHitFromBottomBy");
            break;
        }
        }
    }

    protected void afterCollided(GameElement s1, GameElement s2) {
        switch (this.collisionSide) {
        case RIGHT_LEFT_COLLISION:{
            s1.reflectionCalled(s2, "afterHitFromRightBy");
            s2.reflectionCalled(s1, "afterHitFromLeftBy");
            break;
        }
        case LEFT_RIGHT_COLLISION:{
            s1.reflectionCalled(s2, "afterHitFromLeftBy");
            s2.reflectionCalled(s1, "afterHitFromRightBy");
            break;
        }
        case BOTTOM_TOP_COLLISION:{
            s1.reflectionCalled(s2, "afterHitFromBottomBy");
            s2.reflectionCalled(s1, "afterHitFromTopBy");
            break;
        }
        case TOP_BOTTOM_COLLISION:{
            s1.reflectionCalled(s2, "afterHitFromTopBy");
            s2.reflectionCalled(s1, "afterHitFromBottomBy");
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
