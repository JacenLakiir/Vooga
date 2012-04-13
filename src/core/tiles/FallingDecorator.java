package core.tiles;

import com.golden.gamedev.object.Timer;

import core.characters.GameElement;

public class FallingDecorator extends TileDecorator {
	private Timer fallTimer;
	private boolean falling;

	public FallingDecorator(Tile decoratedPlatform, int timeToFall) {
		super(decoratedPlatform);
		fallTimer = new Timer(timeToFall);
		fallTimer.setActive(false);
	}

	public void afterHitFromTopBy(GameElement e){
		if(!falling){
			falling = true;
			fallTimer.setActive(true);
		}
		decoratedPlatform.afterHitFromTopBy(e);
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(falling && fallTimer.action(elapsedTime)){
			this.setMovable(true);
			this.addGravity();
			
		}
	}
}
