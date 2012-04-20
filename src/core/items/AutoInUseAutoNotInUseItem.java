package core.items;

import com.golden.gamedev.GameObject;

import core.characters.Player;

public class AutoInUseAutoNotInUseItem extends AutoNotInUseItem {

	public AutoInUseAutoNotInUseItem(GameObject game) {
	    super(game);
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	@Override
    public boolean canSetInUse() {
	    return false;
    }
}
