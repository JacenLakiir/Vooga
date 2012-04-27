package core.collision;

import com.golden.gamedev.object.Sprite;	
import core.characters.Character;
import core.items.CollectibleItem;


/**
 * @author Kathleen Oshima	
 */

public class CharacterBulletCollision extends GameElementCollision
{
    private final static long serialVersionUID = 3782144804557174691L;


    @Override
    public void collided (Sprite character, Sprite weapon)
    {
        super.collided(character, weapon);
        character.setActive(true);
        // implement this method with the npc
        ((CollectibleItem) weapon).updateAttributeValues((Character) character,
                                                         "hitPoints",
                                                         ((CollectibleItem) weapon).getAttributeValue("hitPoints"));
        ((CollectibleItem) weapon).setIsInUse(false);

        weapon.setActive(false);
    }
}
