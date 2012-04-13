package core.items;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;

/**
 * @author Kathleen Oshima
 */
public class Weapon extends CollectibleItem {

	static Weapon gun;
	
	public Weapon(GameObject game) {
		super();
	}

	public static Weapon useWeapon() {
		gun.setActive(true);
		gun.setSpeed(.2, 0);
		return gun;
	}
}
