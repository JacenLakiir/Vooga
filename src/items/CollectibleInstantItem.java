package items;

import charactersprites.Player;

import com.golden.gamedev.Game;

public class CollectibleInstantItem extends CollectibleItem {
	
	private double attackPower;
	private double defensePower;
	private double hitPoints;
	private double level;
	
	public CollectibleInstantItem(Game game) {
	    super(game);
    }

	
	@Override
	public void decorate(Player player) {
		player.setMyAttackPower(attackPower(player.getMyAttackPower()));
		defensePower(player.getMyDefensePower());
		hitPoints(player.getMyHP());
		level(player.getMyLevel());
	}


    public double attackPower(double currPower) {
	    
    	return attackPower + currPower;
    }

    public double defensePower(double currPower) {
	    return defensePower + currPower;
    }

    public double hitPoints(double currHP) {
	    return hitPoints + currHP;
    }

    public double level(double currLevel) {
		return level + currLevel;
    }

	@Override
    public void attackPower() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void defensePower() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void hitPoints() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void level() {
	    // TODO Auto-generated method stub
	    
    }

	
	
	
}
