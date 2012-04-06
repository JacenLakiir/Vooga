package items;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
public class AttackAttribute extends AttributeDecorator {

	private boolean isInUse;

	public AttackAttribute(Game game) {
	    super(game);
	    isInUse = true;
	}

	private double attackPower;
	private CollectibleItem collectableItem;
	
	public void setAttackPower(double attackPower) {
		this.attackPower = attackPower;
	}
	
	@Override
    public double attackPower() {
		return attackPower + collectableItem.attackPower();
    }

	@Override
    public double defensePower() {
	    return collectableItem.defensePower();
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
