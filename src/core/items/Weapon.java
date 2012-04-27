package core.items;

import com.golden.gamedev.GameObject;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Weapon extends SetInUseSetNotInUseItem
{
    
    public Weapon (GameObject game, PhysicsAttributes physicsAttribute)
    {
        super(game, physicsAttribute);
        setIsInUse(true);
    }
    
    public Projectile useWeapon() {
        return new Projectile(getGame(), new PhysicsAttributes());
    }

}
