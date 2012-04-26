/**
 * @author Glenn Rivkees (grivkees)
 */
package core.playfield.scroller;

public class KeepLeftFirstPlayerGameScroller extends GameScroller
{
    private final static long serialVersionUID = 3180414135401018121L;


    public void scroll ()
    {
        myBackground.setLocation(myPlayers.getActiveSprite().getX() - 100, 0);
    }

}
