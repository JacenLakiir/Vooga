package items;

import com.golden.gamedev.Game;

public class CollectibleInstantItem extends CollectibleItem {

	public CollectibleInstantItem(Game game) {
	    super(game);
    }

	@Override
    public boolean setIsInUse() {
	    return false;
    }


	@Override
    public double attackPower() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public double defensePower() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public double hitPoints() {
	    // TODO Auto-generated method stub
	    return 0;
    }

	
	
	
}
