package core.tiles;

import io.annotations.Modifiable;

import com.golden.gamedev.GameObject;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class Liquid extends Tile{
    
	@Modifiable(classification = "Gameplay", type = "Individual")
	private double strength = 0.5;
	
	public Liquid(GameObject owner,  PhysicsAttributes physicsAttribute, double strength) {
		this(physicsAttribute);
		setGame(owner);
		this.strength = strength;
	}
	
	public Liquid(PhysicsAttributes physicsAttribute) {
	    super(physicsAttribute);
	    this.getPhysicsAttribute().setPenetrable(true);
	}
	
	public double getStrength() {
	        return strength;
	}
	
	public void setStrength(double strength) {
	    this.strength = strength;
	}

}
