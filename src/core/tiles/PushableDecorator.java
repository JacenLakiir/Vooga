package core.tiles;

import core.characters.GameElement;

public class PushableDecorator extends ActionDecorator{
	private boolean pushed,positiveDirection;
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
