package core.tiles;

public class FrictionlessDecorator extends TileDecorator{

	public FrictionlessDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		this.setCoefficientOfFrictionInX(0);
		this.setCoefficientOfFrictionInY(0);
	}

}
