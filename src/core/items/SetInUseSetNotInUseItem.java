package core.items;

import com.golden.gamedev.GameObject;

public class SetInUseSetNotInUseItem extends CollectibleItem {

	public SetInUseSetNotInUseItem(GameObject game) {
	    super(game);
	}
	
	@Override
    public boolean canSetInUse() {
	    return true;
    }
	
	

}
