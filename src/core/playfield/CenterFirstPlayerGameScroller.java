/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

public class CenterFirstPlayerGameScroller extends GameScroller {

	public void scroll() {
		myBackground.setToCenter(myPlayers.getActiveSprite());
	}

}
