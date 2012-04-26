package core.tiles;

import com.golden.gamedev.GameObject;

public class Liquid extends Tile{
	private double strength;
	
	public Liquid(GameObject owner,  double strength) {
		super(owner, null);
		this.getPhysicsAttribute().setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
