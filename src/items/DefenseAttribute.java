package items;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
public class DefenseAttribute extends AttributeDecorator {

	private boolean isInUse;
	
	public DefenseAttribute(Game game) {
	    super(game);
	    isInUse = true;
	}

	private double defensePower;
	private CollectibleItem collectableItem;


	public void setDefensePower(double defensePower) {
		this.defensePower = defensePower;
	}
	
	@Override
    public double attackPower() {
	    return collectableItem.attackPower();
    }

	@Override
    public double defensePower() {
		return defensePower + collectableItem.defensePower();

    }

    @Override
    public void specialSkill ()
    {
        // TODO Auto-generated method stub
        
    }

	@Override
    public boolean setIsInUse() {
	   return isInUse;
    }

	@Override
    public double hitPoints() {
	    return myHitPoints;
    }

}
