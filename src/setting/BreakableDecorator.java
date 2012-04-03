/**
 * @author Ian McMahon
 */
package setting;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class BreakableDecorator extends PlatformDecorator {
	private boolean broken;

	public BreakableDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
	}

	public void hitFromBottomAction() {
		animateBreak();
		//decoratedPlatform.setActive(false);
		decoratedPlatform.hitFromBottomAction();
	}
	
	public void animateBreak(){
		if(!broken){
			broken = true;
			setImages(owner.getImages("resources/Block2Break.png", 8, 1));
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
