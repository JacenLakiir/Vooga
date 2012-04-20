package core.tiles;

import com.golden.gamedev.GameObject;

public class Liquid extends Tile{
	private double strength;
	
	public Liquid(GameObject owner,  double strength) {
		super(owner);
		this.setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
