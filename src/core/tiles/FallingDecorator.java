package core.tiles;

import com.golden.gamedev.object.Timer;

import core.characters.GameElement;

public class FallingDecorator extends ActionDecorator {
	private Timer fallTimer;
	private boolean falling;

	public FallingDecorator(Tile decoratedPlatform, int timeToFall) {
		super(decoratedPlatform);
		fallTimer = new Timer(timeToFall);
		fallTimer.setActive(false);
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(falling && fallTimer.action(elapsedTime)){
			this.setMovable(true);
			
		}
	}

	@Override
	public void doAction() {
		if(!falling){
			falling = true;
			fallTimer.setActive(true);
		}
	}
}
