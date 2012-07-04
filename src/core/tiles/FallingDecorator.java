package core.tiles;

import io.annotations.Modifiable;
import com.golden.gamedev.object.Timer;

public class FallingDecorator extends ActionDecorator {
	/**
     * 
     */
    private static final long serialVersionUID = -3657189131846894696L;

	private Timer fallTimer;
	
	@Modifiable(classification = "Gameplay", type = "Individual")
	private boolean falling;
	
	public FallingDecorator(Tile decoratedPlatform){
		super(decoratedPlatform);
		fallTimer = new Timer(1000);
		fallTimer.setActive(false);
	}

	public FallingDecorator(Tile decoratedPlatform, int timeToFall) {
		super(decoratedPlatform);
		fallTimer = new Timer(timeToFall);
		fallTimer.setActive(false);
	}
	
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(falling && fallTimer.action(elapsedTime)){
			this.getPhysicsAttribute().setMovable(true);
			
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
