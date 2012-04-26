package demo.custom;

import com.golden.gamedev.GameObject;
import core.characters.Character;
import core.characters.GameElement;
import core.characters.ai.JumpState;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class HammerBrother extends Character
{
    private static final String IMAGE_FILE = "resources/HammerBrother.gif";
    
    public HammerBrother(GameObject game, PhysicsAttributes physicsAttribute) {
        super(game, physicsAttribute);
        setTag("HammerBrother");
        setImages(game.getImages(IMAGE_FILE, 1, 1));
        getPhysicsAttribute().setMovable(true);
        addPossibleState("Jump", new JumpState(this, 2, 150));
    }
    
    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        setDirection(-1);
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
        else if (tag.equals("Koopa")) {
            Koopa.handleSideCollision(this, (Koopa)e, false);
        }
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        setDirection(1);
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
        else if (tag.equals("Koopa")) {
            Koopa.handleSideCollision(this, (Koopa)e, true);
        }
    }

    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
        ((JumpState) getPossibleState("Jump")).notifyHitFromBottom();
    }
}
