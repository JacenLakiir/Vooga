package core.tiles;

import core.characters.GameElement;

public class PushableDecorator extends TileDecorator{
	private boolean pushed;
	private double pushToX;
	
	public PushableDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		
	}

	public void afterHitFromRightBy(GameElement e){
		super.afterHitFromRightBy(e);
		if(!pushed){
			pushToX = getX()-getWidth();
			pushed = true;
		}
	}
	
	public void afterHitFromLeftBy(GameElement e){
		super.afterHitFromLeftBy(e);
		if(!pushed){
			pushToX = getX()+getWidth();
			pushed = true;
		}
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(pushed){
			if(this.moveTo(elapsedTime, pushToX, getY(), 0.05)){
				pushed = false;
			}
		}
	}
}
