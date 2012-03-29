/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package setting;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public abstract class Platform extends Sprite {
	
	protected Game owner;
	
	/*
	 * Constructor for a Concrete Platform
	 */
	public Platform (Game owner, String imgSrc, double x, double y) {
		super(owner.getImage(imgSrc, true), x, y);
		this.owner = owner;
	}
	
	/*
	 * Constructor for a Platform Decorator 
	 */
	public Platform () {
	}
	
	public abstract void hitFromBottomAction();
	public abstract void hitFromTopAction();
	public abstract void hitFromLeftAction();
	public abstract void hitFromRightAction();

}
