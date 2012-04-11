package items;

import charactersprites.Player;

import com.golden.gamedev.Game;

public class CollectibleInstantItem extends CollectibleItem {
	
//	private double attackPower;
	private double defensePower;
	private double hitPoints;
	private double level;
	
	public CollectibleInstantItem(Game game) {
	    super(game);
    }

	
	@Override
	public void decorate(Player player) {
		updatePlayerPoints(player);
		updatePlayerAttackPower(player);
		updatePlayerDefensePower(player);
		updatePlayerHitPoints(player);
		updatePlayerLevel(player);
		this.setIsInUse(false);
	}


	@Override
    public void updatePlayerAttackPower(Player player) {
	    player.setMyAttackPower(this.getAttackPower());
	    
    }

	@Override
    public void updatePlayerDefensePower(Player player) {
	    player.setMyDefensePower(this.getDefensePower());
    }

	@Override
    public void updatePlayerHitPoints(Player player) {
	    player.setMyHP(this.getHitPoints());
	    
    }

	@Override
    public void updatePlayerLevel(Player player) {
		player.setMyLevel(this.getLevel());
    }

	@Override
	public void updatePlayerPoints(Player player) {
		player.setMyPoints(this.getValue());
	}
	
	
	
}
