package game;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;

public abstract class AbstractGameScroller {
	
	protected SpriteGroup myPlayers;
	protected Background myBackground;
	
	public AbstractGameScroller (SpriteGroup players, Background bkg) {
		myPlayers = players;
		myBackground = bkg;
	}
	
	public abstract void scroll ();

}
