package setting;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Background;

public abstract class PlatformDecorator extends Platform {
	
	protected final Platform decoratedPlatform;
	
	public PlatformDecorator(Platform decoratedPlatform) {
		this.decoratedPlatform = decoratedPlatform;
	}

	public abstract void hitFromBottomAction() ;
	
	/*
	 * The following methods allow the PlatformDecorator to act as a ConcretePlatform
	 * (Sprite) by allowing access to the inner sprites methods.
	 */
	
	public void setBackground(Background backgr) {
		decoratedPlatform.setBackground(backgr);
	}
	public Background getBackground() {
		return decoratedPlatform.getBackground();
	}
	
	public BufferedImage getImage() {
		return decoratedPlatform.getImage();
	}

}
