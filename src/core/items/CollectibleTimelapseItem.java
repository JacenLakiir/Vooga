package core.items;


import com.golden.gamedev.GameObject;

import core.characters.Player;

/**
 * @author Kathleen Oshima
 */
public class CollectibleTimelapseItem extends CollectibleItem {

	public double time;
	
	public CollectibleTimelapseItem(GameObject game) {
	    super(game);
    }

	@Override
	public void decorate(Player player) {
		updatePlayerPoints(player);
		updatePlayerAttackPower(player);
		updatePlayerDefensePower(player);
		updatePlayerHitPoints(player);
		updatePlayerLevel(player);
		if (time == 0) {
			this.setIsInUse(false);
		}
		time -= 1;
	}
	
	public void setTimer(double time) {
		this.time = time;
	}

	public double getTimer() {
		return time;
	}

}