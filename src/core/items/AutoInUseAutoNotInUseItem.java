package core.items;

import com.golden.gamedev.GameObject;

import core.characters.Player;

public class AutoInUseAutoNotInUseItem extends AutoNotInUseItem {

	public AutoInUseAutoNotInUseItem(GameObject game) {
	    super(game);
	}

	@Override
	public void decorate(Player player) {
		for (String state : myStateValues.keySet()) {
			updateBaseValues(player, state, this.getStateValue(state));
		}
		this.setIsInUse(false);
	}
	
	@Override
    public boolean canSetInUse() {
	    return false;
    }
}
