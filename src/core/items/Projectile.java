package core.items;

import leveleditor.VoogaUtilities;
import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Projectile extends SetInUseSetNotInUseItem{

    private static final String IMAGE_FILE = "resources/Fireball.png";
    
	public Projectile(GameObject game, PhysicsAttributes physicsAttribute) {
	    super(game, physicsAttribute);
	    setImages(VoogaUtilities.getImages(IMAGE_FILE, 4, 1));
	    setLoopAnim(true);
	    setAnimate(true);
	    getPhysicsAttribute().setMovable(false);
	    setTag("Projectile");
	    addAttribute("hitPoints", -2);
	    setActive(false);
	}
	
}
