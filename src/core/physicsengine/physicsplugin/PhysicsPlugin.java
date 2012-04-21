/**
 * @author Kuang Han
 */

package core.physicsengine.physicsplugin;

import core.characters.GameElement;
import core.collision.GameElementCollision;

public abstract class PhysicsPlugin {
    protected GameElementCollision myCollision;

    public abstract void exertOn(GameElement s1, GameElement s2);
    
    public void setCollisionGroup(GameElementCollision c) {
        myCollision = c;
    }
}
