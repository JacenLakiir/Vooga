package items;

import charactersprites.Player;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
public class CollectibleTimelapseItem extends CollectibleItem {

	public double time;
	
	public CollectibleTimelapseItem(Game game) {
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
