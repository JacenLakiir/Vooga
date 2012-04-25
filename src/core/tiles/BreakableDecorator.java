/**
 * @author Ian McMahon
 */
package core.tiles;

public class BreakableDecorator extends ActionDecorator {
	private boolean broken;
	private int blockStrength;

	public BreakableDecorator(Tile decoratedPlatform, int blockStrength) {
		super(decoratedPlatform);
		this.blockStrength = blockStrength;
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
