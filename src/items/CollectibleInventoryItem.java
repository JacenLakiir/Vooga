package items;

import com.golden.gamedev.Game;

public class CollectibleInventoryItem extends CollectibleItem {

	private double attackPower;
	private double defensePower;
	private double hitPoints;
	private CollectibleItem collectibleItem;
	
	public CollectibleInventoryItem(Game game) {
	    super(game);
    }

	@Override
    public double attackPower() {
	    return attackPower + collectibleItem.attackPower();
    }

	@Override
    public double defensePower() {
	    return defensePower + collectibleItem.defensePower();
    }

	@Override
    public double hitPoints() {
	    return hitPoints + collectibleItem.hitPoints();
    }

}