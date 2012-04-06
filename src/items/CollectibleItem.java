package items;

import java.awt.image.BufferedImage;

import physicsengine.NewtonianSprite;

import charactersprites.Player;

import com.golden.gamedev.Game;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItem extends NewtonianSprite {

	protected Game game;
	private boolean isInUse;
	private double attackPower;
	private double defensePower;
	
	//Constructor for a collectible item
	public CollectibleItem(Game game) {
		super();
		this.game = game;
	}
	

	public void decorate() {
		attackPower();
		defensePower();
		hitPoints();
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
	
	public boolean setIsInUse() {
		return isInUse;
	}
	
	public boolean isInUse() {
	    return isInUse;
    }


	public abstract double attackPower();
	public abstract double defensePower();
	public abstract double hitPoints();

}
