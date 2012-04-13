package core.items;


import com.golden.gamedev.GameObject;

import core.characters.Player;
/**
 * @author Kathleen Oshima
 */
public class CollectibleInstantItem extends CollectibleItem {
	
	public CollectibleInstantItem(GameObject game) {
	    super(game);
    }

	@Override
	public void decorate(Player player) {
		updatePlayerPoints(player);
		updatePlayerAttackPower(player);
		updatePlayerDefensePower(player);
		updatePlayerHitPoints(player);
		updatePlayerLevel(player);
		this.setIsInUse(false);
	}
}
