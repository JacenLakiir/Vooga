/**
 * @author Ian McMahon
 */
package tiles;


import charactersprites.GameElement;


public class BreakableDecorator extends TileDecorator {
	private boolean broken;

	public BreakableDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		setAnimate(false);
	}

	@Override
	public void afterHitFromBottomBy(GameElement e) {
		animateBreak();
		decoratedPlatform.afterHitFromBottomBy(e);
	}
	
	public void animateBreak(){
		if(!broken){
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
