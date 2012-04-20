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
	    updateBaseValues(player, state, this.getStateValue(state));
	}
	this.setIsInUse(false);
    }
}
