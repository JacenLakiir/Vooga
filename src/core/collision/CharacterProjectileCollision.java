package core.collision;

import com.golden.gamedev.object.Sprite;	
import core.characters.Character;
import core.items.CollectibleItem;
import core.items.Projectile;


/**
 * @author Kathleen Oshima	
 */

public class CharacterProjectileCollision extends GameElementCollision
{
    private final static long serialVersionUID = 3782144804557174691L;


    @Override
    public void collided (Sprite character, Sprite projectile)
    {
        super.collided(character, projectile);
        System.out.println("Bullet collided");
        CollectibleItem item = (CollectibleItem) projectile;
        if (!item.getTag().equals("Projectile"))
            return;
        Projectile p = (Projectile) item;
        p.updateAttributeValues((Character) character, "hitPoints",
                                p.getAttributeValue("hitPoints"));
        p.setIsInUse(false);
        p.setActive(false);
    }
}
