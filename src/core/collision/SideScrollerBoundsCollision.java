package core.collision;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import core.characters.Character;

public class SideScrollerBoundsCollision extends CollisionBounds {

	public SideScrollerBoundsCollision(Background arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void collided (Sprite arg0)
    {
        if (isCollisionSide(LEFT_COLLISION))
        {
            revertPosition1();
        }
        else if (isCollisionSide(RIGHT_COLLISION))
        {
            revertPosition1();
        }
        else if (isCollisionSide(TOP_COLLISION))
        {

        }
        else if (isCollisionSide(BOTTOM_COLLISION))
        {
        	if (Character.class.isInstance(arg0))
        	{
        		Character c = (Character) arg0;
        		c.updateAttributeValue("hitPoints", -1 * c.getAttributeValue("hitPoints"));
        	}
        }
    }
}
