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
		for (String state : myStateValues.keySet()) {
			updateStateValues(player, state, myStateValues.get(state));
		}
		this.setIsInUse(false);
	}
}
