package demo.custom;

import com.golden.gamedev.GameObject;
import core.characters.Character;
import core.characters.GameElement;
import core.characters.ai.JumpState;
import leveleditor.VoogaUtilities;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class HammerBrother extends Character {
    private static final String IMAGE_FILE = "resources/HammerBrother.gif";

    public HammerBrother(GameObject game, PhysicsAttributes physicsAttribute) {
    this(physicsAttribute);
    setGame(game);
    }
    
    public HammerBrother(PhysicsAttributes physicsAttribute) {
	super(physicsAttribute);
	setTag("HammerBrother");
	addAttribute("hitPoints", 1);
	setImages(VoogaUtilities.getImages(IMAGE_FILE, 1, 1));
	getPhysicsAttribute().setMovable(true);
	addPossibleState("Jump", new JumpState(this, 2, 150));
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
	setDirection(-1);
	if (tag.equals("Mario")) {
	    ((Mario) e).updateAttributeValue("hitPoints",
		    -1 * ((Mario) e).getAttributeValue("hitPoints"));
	} else if (tag.equals("Koopa")) {
	    Koopa.handleSideCollision(this, (Koopa) e, false);
	}
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
	setDirection(1);
	if (tag.equals("Mario")) {
	    ((Mario) e).updateAttributeValue("hitPoints",
		    -1 * ((Mario) e).getAttributeValue("hitPoints"));
	} else if (tag.equals("Koopa")) {
	    Koopa.handleSideCollision(this, (Koopa) e, true);
	}
    }

    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
        if (!tag.equals("Item"))
            ((JumpState) getPossibleState("Jump")).notifyHitFromBottom();
    }
}
