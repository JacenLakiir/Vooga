package core.items;

import java.awt.image.BufferedImage;



import com.golden.gamedev.Game;

import core.characters.GameElement;
import core.characters.Player;
import core.tiles.Tile;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItem extends GameElement {

	protected Game game;
	private boolean isInUse;
	private double attackPower, defensePower, hitPoints, level, value;
	
	//Constructor for a collectible item
	public CollectibleItem(Game game) {
		super();
		attackPower = 0;
	    defensePower = 0;
	    hitPoints = 0;
	    level = 0;
	    value = 0;
//		this.game = game;
	}
	
	public void decorate(Player player) {
		updatePlayerPoints(player);
		updatePlayerAttackPower(player);
		updatePlayerDefensePower(player);
		updatePlayerHitPoints(player);
		updatePlayerLevel(player);
	}

	public void set(BufferedImage[] images, double x, double y) {
        this.setImages(images);
        this.setLocation(x, y);
    }
	
	public void setAttackPower(double attackPower) {
		this.attackPower = attackPower;
	}

	public double getAttackPower() {
		return attackPower;
	}
	
	public void setDefensePower(double defensePower) {
		this.defensePower = defensePower;
	}
	
	public double getDefensePower() {
		return defensePower;
	}
	
	public void setHitPoints(double hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public double getHitPoints() {
		return hitPoints;
	}
	
	public void setLevel(double level) {
		this.level = level;
	}
	
	public double getLevel() {
		return level;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setIsInUse(boolean bool) {
		isInUse = bool;
	}
	
	public boolean isInUse() {
	    return isInUse;
    }

    public void updatePlayerAttackPower(Player player) {
	    player.setMyAttackPower(this.getAttackPower());
	    
    }

    public void updatePlayerDefensePower(Player player) {
	    player.setMyDefensePower(this.getDefensePower());
    }

    public void updatePlayerHitPoints(Player player) {
	    player.setMyHP(this.getHitPoints());
	    
    }

    public void updatePlayerLevel(Player player) {
		player.setMyLevel(this.getLevel());
    }

	public void updatePlayerPoints(Player player) {
		player.setMyPoints(this.getValue());
	}
	
}
