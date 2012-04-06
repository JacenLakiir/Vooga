/**
 * @author Ian McMahon
 */
package setting;

import voogaobject.GameElement;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class BreakableDecorator extends PlatformDecorator {
	private boolean broken;

	public BreakableDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
	}

	@Override
	public void afterHitFromBottomBy(GameElement e) {
		animateBreak();
		//decoratedPlatform.setActive(false);
		decoratedPlatform.afterHitFromBottomBy(e);
	}
	
	public void animateBreak(){
		if(!broken){
			broken = true;
			setImages(myGame.getImages("resources/Block2Break.png", 8, 1));
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
