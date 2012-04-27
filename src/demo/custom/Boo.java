package demo.custom;

import leveleditor.VoogaUtilities;

import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.Character;
import core.characters.ai.HomingState;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class Boo extends Character {

    private static final String IMAGE_FILE = "resources/Boo.png";
        
    public Boo(GameObject game, PhysicsAttributes physicsAttribute, Character toTrack) {
        this(physicsAttribute, toTrack);
        setGame(game);
    }
    
    public Boo(PhysicsAttributes physicsAttribute, Character toTrack) {
        super(physicsAttribute);
        addAttribute("hitPoints", 1);
        setTag("Boo");
        setImages(VoogaUtilities.getImages(IMAGE_FILE, 1, 1));
        getPhysicsAttribute().setMovable(false);
        addPossibleState("Homing", new HomingState(this, toTrack, 750, 1));
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
    }

    @Override
    public void afterHitFromTopBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
    }
    
    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            ((Mario)e).updateAttributeValue("hitPoints", -1 * ((Mario)e).getAttributeValue("hitPoints"));
        }
    }

}
