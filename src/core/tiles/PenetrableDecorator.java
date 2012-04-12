package core.tiles;

public class PenetrableDecorator extends TileDecorator{
	private double strength;
	
	public PenetrableDecorator(Tile decoratedPlatform,  double strength) {
		super(decoratedPlatform);
		this.decoratedPlatform.setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
