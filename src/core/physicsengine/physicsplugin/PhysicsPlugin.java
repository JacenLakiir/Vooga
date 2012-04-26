/**
 * @author Kuang Han
 */

package core.physicsengine.physicsplugin;

import java.io.Serializable;
import core.characters.GameElement;
import core.collision.GameElementCollision;


public abstract class PhysicsPlugin implements Serializable
{
    private final static long serialVersionUID = 973349580079490596L;
    protected GameElementCollision myCollision;


    public abstract void exertOn (GameElement s1, GameElement s2);


    public void setCollisionGroup (GameElementCollision c)
    {
        myCollision = c;
    }
}
