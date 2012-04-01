package game;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

public class CenterFirstPlayerGameScroller extends AbstractGameScroller {

	public CenterFirstPlayerGameScroller(SpriteGroup chars, Background bkg) {
		super(chars, bkg);
	}

	public void scroll() {
		myBackground.setToCenter(myPlayers.getActiveSprite());
	}

}
