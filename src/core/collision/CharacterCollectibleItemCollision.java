package core.collision;

import com.golden.gamedev.object.Sprite;
import core.characters.Character;
import core.items.CollectibleItem;


/**
 * @author Kathleen Oshima
 */
public class CharacterCollectibleItemCollision extends GameElementCollision
{
    private final static long serialVersionUID = -7376348216875966173L;


    @Override
    public void collided (Sprite character, Sprite item)
    {
        super.collided(character, item);
        item.setActive(false);
        ((CollectibleItem) item).setIsInUse(true);
        ((Character) character).getInventory().add((CollectibleItem) item);
    }
}
