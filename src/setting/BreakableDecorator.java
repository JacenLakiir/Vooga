package setting;

public class BreakableDecorator extends PlatformDecorator {

	public BreakableDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
	}

	public void hitFromBottomAction() {
		decoratedPlatform.setActive(false);
		decoratedPlatform.hitFromBottomAction();
	}

	public void hitFromTopAction() {	}

	public void hitFromLeftAction() {	}

	public void hitFromRightAction() {	}

}
