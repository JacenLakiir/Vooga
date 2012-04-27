package core.tiles;

import io.annotations.Decorator;

@Decorator(target = Tile.class)
public class FrictionlessDecorator extends TileDecorator{

	/**
     * 
     */
    private static final long serialVersionUID = 1003970101519434221L;

	public FrictionlessDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		this.getPhysicsAttribute().setCoefficientOfFrictionInX(0);
		this.getPhysicsAttribute().setCoefficientOfFrictionInY(0);
	}

}
