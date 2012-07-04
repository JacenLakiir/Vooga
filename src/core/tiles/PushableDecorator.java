package core.tiles;

import io.annotations.Decorator;
import io.annotations.Modifiable;
import core.characters.GameElement;
@Decorator(target = Tile.class)
public class PushableDecorator extends ActionDecorator {
    
	/**
     * 
     */
    private static final long serialVersionUID = 5926869970709503396L;

	@Modifiable(classification = "Gameplay", type = "Individual")
	private boolean pushed,positiveDirection;
	
	@Modifiable(classification = "Gameplay", type = "Individual")
	private double pushToX;
	
	public PushableDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
	}

	@Override
	public void afterHitFromRightBy(GameElement e, String tag){
		positiveDirection = false;
		super.afterHitFromRightBy(e, tag);
	}
	
	@Override
	public void afterHitFromLeftBy(GameElement e, String tag){
		positiveDirection = true;
		super.afterHitFromLeftBy(e, tag);
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(pushed){
			if(this.moveTo(elapsedTime, pushToX, getY(), 0.05)){
				pushed = false;
			}
		}
	}

	@Override
	public void doAction() {
		if(!pushed){
			pushToX = getX()+(positiveDirection ? 1 : -1)*getWidth();
			pushed = true;
		}
	}
}
