package demo.custom;

import java.util.List;
import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.Character;
import core.characters.ai.DeadState;
import core.characters.ai.State;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class Goomba extends Character {

    private static final String IMAGE_FILE = "resources/Goomba.png";

    public Goomba(GameObject game, PhysicsAttributes physicsAttribute) {
        super(game, physicsAttribute);
        setImages(game.getImages(IMAGE_FILE, 1, 1));
        setMovable(true);
        setTag("Goomba");
    }

    public Goomba(GameObject game, PhysicsAttributes physicsAttribute, List<State> possibleStates) {
        super(game, physicsAttribute);
        setTag("Goomba");
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        setDirection(-1);
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getMyAttributeValue("hitPoints"));
        }
        else if (tag.equals("Koopa")) {
            handleKoopaSideCollision((Koopa)e, false);
        }
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        setDirection(1);
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getMyAttributeValue("hitPoints"));
        }
        else if (tag.equals("Koopa")) {
            handleKoopaSideCollision((Koopa)e, true);
        }
    }

    @Override
    public void afterHitFromTopBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            addPossibleState(new DeadState(this));
        }
    }

    private void handleKoopaSideCollision(Koopa k, boolean isHitOnLeft) {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            addPossibleState(new DeadState(this));
        else
            setDirection(isHitOnLeft ? 1 : -1);
    }

}
