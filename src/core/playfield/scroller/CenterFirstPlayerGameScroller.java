/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

public class CenterFirstPlayerGameScroller extends GameScroller
{
    private final static long serialVersionUID = 6923542783717741471L;


    public void scroll ()
    {
        myBackground.setToCenter(myPlayers.getActiveSprite());
    }

}
