package core.tiles;

public class Liquid extends Tile{
	private double strength;
	
	public Liquid(Tile decoratedPlatform,  double strength) {
		this.setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
