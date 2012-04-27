/**
 * @author Ian McMahon
 */
package core.tiles;

import io.annotations.Decorator;


@Decorator(target = Tile.class)
public class BreakableDecorator extends ActionDecorator {
	private boolean broken;
	private int blockStrength = 1;

	public BreakableDecorator(Tile decoratedPlatform, int blockStrength) {
		super(decoratedPlatform);
		this.blockStrength = blockStrength;
		setAnimate(false);
	}
	
	public BreakableDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		setAnimate(false);
	}

	public void doAction(){
		blockStrength--;
		if(blockStrength<=0){
			broken = true;
			getAnimationTimer().setDelay(20);
			setAnimate(true);
		}
	}
	
	public void update(long elapsedTime) {
        super.update(elapsedTime);
        if (broken && !this.isAnimate()) {
                this.setActive(false);
        }
	}
}
