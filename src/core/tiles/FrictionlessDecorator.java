package core.tiles;

public class FrictionlessDecorator extends TileDecorator{

	public FrictionlessDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		this.getPhysicsAttribute().setCoefficientOfFrictionInX(0);
		this.getPhysicsAttribute().setCoefficientOfFrictionInY(0);
	}

}
