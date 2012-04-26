package core.tiles;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Liquid extends Tile{
	private double strength;
	
	public Liquid(GameObject owner,  PhysicsAttributes physicsAttribute, double strength) {
		super(owner, physicsAttribute);
		this.getPhysicsAttribute().setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
