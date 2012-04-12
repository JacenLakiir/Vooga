package core.items;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;

/**
 * @author Kathleen Oshima
 */
public class Weapon extends CollectibleItem {

	public Weapon(GameObject game) {
		super();
	}

	public Weapon useWeapon() {
		this.setActive(true);
		this.setSpeed(.2, 0);
		return this;
	}
}
