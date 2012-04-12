package core.items;


import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Timer;

import core.characters.Player;

/**
 * @author Kathleen Oshima
 */
public class CollectibleTimelapseItem extends CollectibleItem {

	Timer timer;
	Player player;
	long timePassed;
//	long endTimer = timePassed + 5000;
	
	public void update(long elapsedTime) {
		timePassed = elapsedTime;
	}
	
	public CollectibleTimelapseItem(GameObject game) {
	    super(game);
    }

	@Override
	public void decorate(Player player) {
		if (timer.action(timePassed))
		{
			updatePlayerPoints(player);
			updatePlayerAttackPower(player);
			updatePlayerDefensePower(player);
			updatePlayerHitPoints(player);
			updatePlayerLevel(player);
		}
//		if (timer.action(endTimer)) {
//			timer.setActive(false);
//		}
	}
	
	public void setTimer(int time) {
		this.timer = new Timer(time);
	}

}
