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
	private double attackPower, defensePower, hitPoints, level;
	
	//Constructor for a collectible item
	public CollectibleItem(Game game) {
		super();
//		this.game = game;
	}
	
	public void decorate(Player player) {
		attackPower();
		defensePower();
		hitPoints();
		level();
	}

	public void set(BufferedImage[] images, double x, double y) {
        this.setImages(images);
        this.setLocation(x, y);
    }
	
	public void setAttackPower(double attackPower) {
		this.attackPower = attackPower;
	}

	public void setDefensePower(double defensePower) {
		this.defensePower = defensePower;
	}
	
	public void setHitPoints(double hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public void setLevel(double level) {
		this.level = level;
	}
	
	public boolean setIsInUse() {
		return isInUse;
	}
	
	public boolean isInUse() {
	    return isInUse;
    }


	public abstract void attackPower();
	public abstract void defensePower();
	public abstract void hitPoints();
	public abstract void level();

}
