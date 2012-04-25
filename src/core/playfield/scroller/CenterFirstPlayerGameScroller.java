/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

public class CenterFirstPlayerGameScroller extends GameScroller {

	public void scroll() {
		myBackground.setToCenter(myPlayers.getActiveSprite());
	}

}
