/**
 * @author Ian McMahon
 */
package setting;

public class BreakableDecorator extends PlatformDecorator {

	public BreakableDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
	}

	public void hitFromBottomAction() {
		decoratedPlatform.setActive(false);
		decoratedPlatform.hitFromBottomAction();
	}
}
