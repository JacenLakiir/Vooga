package core.items;

import com.golden.gamedev.GameObject;

public class SetInUseAutoNotInUseItem extends AutoNotInUseItem {

	public SetInUseAutoNotInUseItem(GameObject game) {
	    super(game);
    }
	
	@Override
	public boolean canSetInUse() {
		return true;
	}
}
