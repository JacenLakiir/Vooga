package core.items;

import leveleditor.VoogaUtilities;
import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Projectile extends SetInUseSetNotInUseItem{

    private static final String IMAGE_FILE = "resources/Fireball.png";
    
	public Projectile(PhysicsAttributes physicsAttribute) {
	    super(physicsAttribute);
	    setActive(false);
	    useWeapon();
//	    setImages(VoogaUtilities.getImages(IMAGE_FILE, 4, 1));
//	    setLoopAnim(true);
//	    setAnimate(true);
//	    getPhysicsAttribute().setMovable(false);
//	    setTag("Projectile");
//	    addAttribute("hitPoints", -2);
//	    setActive(false);
	}
	
	public Projectile useWeapon() {
		this.setActive(true);
		this.setSpeed(.2,0);
		return this;
	}
	
}
