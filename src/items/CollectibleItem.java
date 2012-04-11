package items;

import java.awt.image.BufferedImage;

import setting.Platform;

import charactersprites.GameElement;
import charactersprites.Player;

import com.golden.gamedev.Game;

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


	public abstract void updatePlayerAttackPower(Player player);
	public abstract void updatePlayerDefensePower(Player player);
	public abstract void updatePlayerHitPoints(Player player);
	public abstract void updatePlayerLevel(Player player);
	public abstract void updatePlayerPoints(Player player);

}
