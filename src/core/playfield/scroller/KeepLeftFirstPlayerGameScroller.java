/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

public class KeepLeftFirstPlayerGameScroller extends GameScroller {

	public void scroll() {
		myBackground.setLocation(myPlayers.getActiveSprite().getX()-100, 0);
	}

}
