package items;

import charactersprites.CollectibleItem;

import com.golden.gamedev.Game;
/**
 * @author Kathleen Oshima
 */
public class HPAttribute extends AttributeDecorator {

	private boolean isInUse;
	private double hitPoints;
	private CollectibleItem collectibleItem;
	
	public HPAttribute(Game game) {
	    super(game);
	    isInUse = true;
    }

	@Override
    public boolean isInUse() {
	    return isInUse;
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
	    return hitPoints + collectibleItem.hitPoints();
    }
	

}
