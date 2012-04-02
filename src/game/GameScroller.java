/**
 * @author Glenn Rivkees (grivkees)
 */
package game;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

public abstract class GameScroller {
	
	protected SpriteGroup myPlayers;
	protected Background myBackground;
	
	public GameScroller (SpriteGroup players, Background bkg) {
		myPlayers = players;
		myBackground = bkg;
	}
	
	public abstract void scroll ();

}
