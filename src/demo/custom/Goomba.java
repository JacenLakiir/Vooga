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
    }

    public Goomba(GameObject game, PhysicsAttributes physicsAttribute, List<State> possibleStates) {
	super(game, physicsAttribute);
    }

    public void afterHitFromRightBy(GameElement e) {
	setDirection(-1);
    }

    public void afterHitFromLeftBy(GameElement e) {
	setDirection(1);
    }

    public void afterHitFromTopBy(Mario m) {
	addPossibleState(new DeadState(this));
    }

    public void afterHitFromRightBy(Mario m) {
	m.updateAttributeValue("hitPoints", -1 * m.getMyAttributeValue("hitPoints"));
    }

    public void afterHitFromLeftBy(Mario m) {
	m.updateAttributeValue("hitPoints", -1 * m.getMyAttributeValue("hitPoints"));
    }

    public void afterHitFromRightBy(Koopa k) {
	handleKoopaSideCollision(k, false);
    }

    public void afterHitFromLeftBy(Koopa k) {
	handleKoopaSideCollision(k, true);
    }

    private void handleKoopaSideCollision(Koopa k, boolean isHitOnLeft) {
	if (k.isInShellState() && k.getShellSpeed() != 0)
	    addPossibleState(new DeadState(this));
	else
	    setDirection(isHitOnLeft ? 1 : -1);
    }

}
